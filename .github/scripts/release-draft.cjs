const fs = require('node:fs');
function validateRange(tag, previous, version) {
  const pattern = /^v(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)$/;
  if (!pattern.test(tag) || !pattern.test(previous)) throw new Error('Tag phiên bản ổn định phải có định dạng: vX.Y.Z');
  if (tag === previous || tag !== `v${version}`) throw new Error('Phạm vi phiên bản hoặc app versionName không khớp');
  const a = tag.slice(1).split('.').map(Number), b = previous.slice(1).split('.').map(Number);
  const different = a.findIndex((part, i) => part !== b[i]);
  if (different < 0 || a[different] < b[different]) throw new Error('Phiên bản hiện tại phải cao hơn phiên bản trước');
}
async function prepare({ github, context, core }) {
  const repo = context.repo;
  const tag = process.env.RELEASE_TAG;
  const previous = process.env.PREVIOUS_TAG;
  // Validate before using refs in URLs; git refs are never interpolated into shell.
  validateRange(tag, previous, tag?.slice(1));
  const target = (await github.rest.repos.getCommit({...repo,ref:tag})).data.sha;
  const base = (await github.rest.repos.getCommit({...repo,ref:previous})).data.sha;
  const ancestry = (await github.rest.repos.compareCommitsWithBasehead({...repo,basehead:`${target}...main`})).data;
  if (!['ahead','identical'].includes(ancestry.status)) throw new Error('Tag đích không nằm trong lịch sử main');
  const range = (await github.rest.repos.compareCommitsWithBasehead({...repo,basehead:`${base}...${target}`})).data;
  if (range.status !== 'ahead') throw new Error('Tag trước phải là tổ tiên của tag hiện tại và bao gồm các commit mới');
  const config = (await github.rest.repos.getContent({...repo,path:'app/build.gradle.kts',ref:target})).data;
  const version = Buffer.from(config.content,'base64').toString('utf8').match(/versionName\s*=\s*"([^"]+)"/)?.[1];
  validateRange(tag, previous, version);
  const previousRelease = (await github.rest.repos.getReleaseByTag({...repo,tag:previous})).data;
  if (previousRelease.draft || previousRelease.prerelease) throw new Error('Tag trước phải tương ứng với một phiên bản chính thức đã phát hành');
  const releases = await github.paginate(github.rest.repos.listReleases,{...repo,per_page:100});
  if (releases.some(release => release.tag_name === tag)) throw new Error('Phiên bản này đã có Release, không ghi đè ghi chú và tệp đính kèm hiện có');
  const runs = await github.paginate(github.rest.actions.listWorkflowRuns,{...repo,workflow_id:'android.yml',head_sha:target,per_page:100});
  const latest = runs.filter(run => run.head_branch === 'main' && ['push','workflow_dispatch'].includes(run.event))
    .sort((a,b) => b.id - a.id)[0];
  if (latest?.conclusion !== 'success' || latest.status !== 'completed' ||
       latest.head_sha !== target) throw new Error('CI Android main gần nhất của commit đích chưa đạt');
  // Require the actual Release APK and lint; a lightweight
  // skipped build must never authorize a stable release draft.
  const jobs = await github.paginate(github.rest.actions.listJobsForWorkflowRun,
    {...repo, run_id:latest.id, filter:'latest', per_page:100});
  const requiredSteps = ['Build and verify unsigned CI release', 'Upload unsigned CI APK'];
  if (!jobs.some(job => job.name === 'build' && job.conclusion === 'success' &&
      requiredSteps.every(name => job.steps?.some(step =>
        step.name === name && step.conclusion === 'success'))))
     throw new Error('Commit đích thiếu bản dựng Release hoàn chỉnh hoặc xác minh Lint');
  const artifacts = await github.paginate(github.rest.actions.listWorkflowRunArtifacts,
    {...repo, run_id:latest.id, per_page:100});
  if (!artifacts.some(artifact => artifact.name === 'ci-unsigned' &&
      !artifact.expired && artifact.size_in_bytes > 0))
     throw new Error('Không tồn tại sản phẩm CI Release của commit đích hoặc sản phẩm đã hết hạn');
  const notes = (await github.rest.repos.generateReleaseNotes({ ...repo,tag_name:tag,previous_tag_name:previous,target_commitish:target,configuration_file_path:'.github/release.yml' })).data;
  // Preserve GitHub's author, PR, new contributor and full changelog links verbatim.
   const body = `## Ghi chú cập nhật\n\n${process.env.RELEASE_SUMMARY.trim()}\n\n${notes.body}\n\n## Installation & Verification\n\n- Bản nháp này dựa trên ${target}; đã xác minh bản dựng Release Android, Lint và sản phẩm CI (lần chạy #${latest.run_number}).\n- Chờ tải lên APK ký chính thức được dựng từ cùng commit và tệp kiểm tra SHA-256.\n- Việc kiểm tra trên thiết bị thật, chữ ký và xác minh tải xuống vẫn cần người bảo trì điền; CI không thay thế việc kiểm tra trên thiết bị thật.\n`;
  fs.writeFileSync('release-draft.txt',body);
  core.setOutput('target',target);
  core.setOutput('tag',tag);
}
async function create({github,context,core}) {
  const repo=context.repo;
  const tag=process.env.RELEASE_TAG;
  // Recheck before publication; never overwrite a previously created release.
  const releases=await github.paginate(github.rest.repos.listReleases,{...repo,per_page:100});
   if (releases.some(release=>release.tag_name===tag)) throw new Error('Phiên bản này đã tồn tại, dừng tạo');
  const release=await github.rest.repos.createRelease({...repo,tag_name:tag,target_commitish:process.env.RELEASE_TARGET,name:`CurrentMusic ${tag}`,body:fs.readFileSync('release-draft.txt','utf8'),draft:true,prerelease:false});
  core.info(`Created draft ${release.data.html_url}`);
}
module.exports={validateRange,prepare,create};
