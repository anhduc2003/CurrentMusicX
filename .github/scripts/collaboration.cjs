// Repository metadata only. Never load or execute files from a pull request head.
const {STATUS_LABELS,labelColor}=require('./label-palette.cjs');
const types = {
  feat: 'enhancement', fix: 'bug', perf: 'performance', docs: 'documentation',
  deps: 'dependencies', chore: 'maintenance', ci: 'maintenance', build: 'maintenance',
  refactor: 'maintenance', test: 'maintenance', release: 'maintenance',
};
const modules = {
'Phát lại và hiệu ứng âm thanh': 'module: playback', 'Lời bài hát': 'module: lyrics','Nguồn âm thanh và mạng': 'module: network', 'Giao diện và giao diện bên ngoài': 'module: appearance','Tải xuống và bộ nhớ đệm': 'module: storage', 'Khác': 'module: general',};
const managedLabels = {
  ...Object.fromEntries([...new Set(Object.values(types))].map(name => [name, 'Phân loại phát hành'])),
  ...Object.fromEntries(Object.values(modules).map(name => [name, 'Mô-đun liên quan đến vấn đề'])),
  'breaking-change': 'Thay đổi không tương thích', 'needs-info': 'Chờ tác giả bổ sung thông tin',
  stale: 'Không có hoạt động trong thời gian dài', 'bot: skip': 'Bỏ qua tự động phân loại và xử lý quá hạn',
  'bot: auto-closed': 'Tự động đóng; quản trị viên có thể mở lại sau khi thông tin được bổ sung',
  'scope too large': 'Đề nghị chia nhỏ PR quá lớn', 'announcement': 'Thông báo của nhóm duy trì',
  'needs follow-up': 'Cần tiếp tục theo dõi, không tự động đóng',
};
const names = issue => issue.labels.map(label => typeof label === 'string' ? label : label.name);
const maintainer = item => ['OWNER', 'MEMBER', 'COLLABORATOR'].includes(item.author_association);
const human = item => item.user?.type !== 'Bot' && !item.user?.login?.endsWith('[bot]');
function section(body, heading) {
  const lines = String(body ?? '').split(/\r?\n/);
  const start = lines.indexOf(`### ${heading}`);
  if (start < 0) return '';
  const end = lines.findIndex((line, i) => i > start && line.startsWith('### '));
  const value = lines.slice(start + 1, end < 0 ? undefined : end).join('\n').trim();
return /^(_No response_|Không|N\/A)$/i.test(value) ? '' : value;}
function prLabels(title, body = '') {
  const match = /^(\w+)(?:\([^)]*\))?(!)?:\s*\S/.exec(title.trim());
  return [...new Set([
    match && types[match[1].toLowerCase()],
    (match?.[2] || /^BREAKING CHANGE:/m.test(body)) && 'breaking-change',
  ].filter(Boolean))];
}
function obsoleteBotLabels(labels, desired, events) {
  const typeLabels = new Set([...Object.values(types), 'breaking-change']);
  return labels.filter(label => {
    if (!typeLabels.has(label) || desired.includes(label)) return false;
    const last = events.filter(e => ['labeled','unlabeled'].includes(e.event) && e.label?.name === label).at(-1);
    return last?.event === 'labeled' && last.actor?.login === 'github-actions[bot]';
  });
}
function issuePlan(issue) {
  const labels = names(issue);
  if (issue.state !== 'open' || labels.includes('bot: skip') || labels.includes('announcement')) return null;
const module = modules[section(issue.body, 'Mô-đun vấn đề')];  // Free-form issues are allowed: do not claim their information is missing merely
  // because headings differ from the form. Maintainer reports need no questionnaire.
const form = labels.includes('bug') && String(issue.body).includes('### Phiên bản ứng dụng');  const missing = form && !maintainer(issue)
? ['Phiên bản ứng dụng', 'Thiết bị và phiên bản Android', 'Các bước tái hiện', 'Kết quả thực tế và kết quả mong đợi'].filter(h => !section(issue.body, h)) : [];  return { module: labels.some(l => l.startsWith('module: ')) ? null : module, missing, form };
}
function staleDecision(issue, comments, events, now = Date.now()) {
  const labels = names(issue);
  if (labels.some(label => Object.hasOwn(STATUS_LABELS,label))) return null;
  if (issue.state !== 'open' || issue.locked || issue.assignees?.length || issue.milestone || maintainer(issue) ||
      labels.some(l => ['bot: skip', 'announcement', 'needs follow-up', 'help wanted', 'priority: high', 'provider-request'].includes(l))) return null;
  const activity = Math.max(Date.parse(issue.created_at), ...comments.filter(human).map(c => Date.parse(c.updated_at ?? c.created_at)));
  const labelAt = label => events.filter(e => e.event === 'labeled' && e.label?.name === label).at(-1)?.created_at;
  const age = timestamp => (now - Date.parse(timestamp)) / 86400000;
  const reopened = events.some(e => e.event === 'reopened');
  const mayClose = !issue.pull_request && !labels.includes('enhancement') && !reopened &&
    !labels.includes('bot: auto-closed') && labels.some(l => ['bug', 'question'].includes(l));
  const infoAt = labelAt('needs-info');
  if (labels.includes('needs-info') && infoAt && age(infoAt) >= 14 && activity <= Date.parse(infoAt) && mayClose) return 'close-info';
  const staleAt = labelAt('stale');
  if (labels.includes('stale')) {
    if (!staleAt) return null;
    if (activity > Date.parse(staleAt)) return 'revive';
    return mayClose && age(staleAt) >= 30 ? 'close-stale' : null;
  }
  return (now - activity) / 86400000 >= 60 ? 'mark-stale' : null;
}
async function run({ github, context, core }, mode = 'event') {
  const repo = context.repo;
  if (process.env.TRIAGE_MODE && !['off', 'dry-run', 'live'].includes(process.env.TRIAGE_MODE)) throw new Error('Invalid TRIAGE_MODE');
  const dry = process.env.TRIAGE_MODE === 'off' ? null : process.env.TRIAGE_DRY_RUN === 'true' || process.env.TRIAGE_MODE === 'dry-run';
  if (dry === null) return;
  const mutate = async (description, call) => { core.info(description); if (!dry) await call(); };
  const ensureLabel = async name => {
    try { await github.rest.issues.getLabel({ ...repo, name }); }
    catch (error) { if (error.status !== 404) throw error; await mutate(`Create label ${name}`, () => github.rest.issues.createLabel({ ...repo, name, color: labelColor(name), description: managedLabels[name] ?? name })); }
  };
  const add = async (number, labels) => {
    if (!labels.length) return;
    for (const label of labels) await ensureLabel(label);
    await mutate(`Label #${number}: ${labels.join(', ')}`, () => github.rest.issues.addLabels({ ...repo, issue_number: number, labels }));
  };
  const remove = async (number, name) => mutate(`Remove ${name} from #${number}`, async () => {
    try { await github.rest.issues.removeLabel({ ...repo, issue_number: number, name }); }
    catch (error) { if (error.status !== 404) throw error; }
  });
  const commentsFor = number => github.paginate(github.rest.issues.listComments, { ...repo, issue_number: number, per_page: 100 });
  const comment = async (number, text, kind) => {
    const marker = `<!-- currentmusic:${kind} -->`;
    const existing = (await commentsFor(number)).find(c => c.user.login === 'github-actions[bot]' && c.body?.startsWith(marker));
    const body = `${marker}\n${text}`;
    if (existing?.body === body) return;
    await mutate(`Update ${kind} comment #${number}`, () => existing
      ? github.rest.issues.updateComment({ ...repo, comment_id: existing.id, body })
      : github.rest.issues.createComment({ ...repo, issue_number: number, body }));
  };
  if (mode === 'stale') {
    const issues = await github.paginate(github.rest.issues.listForRepo, { ...repo, state: 'open', per_page: 100 });
    let closed = 0;
    for (const item of issues) {
      // Re-fetch before acting, rather than relying on the earlier collection snapshot.
      const issue = (await github.rest.issues.get({ ...repo, issue_number: item.number })).data;
      const comments = await commentsFor(issue.number);
      const events = await github.paginate(github.rest.issues.listEventsForTimeline, { ...repo, issue_number: issue.number, per_page: 100 });
      const action = staleDecision(issue, comments, events);
      if (action === 'revive') await remove(issue.number, 'stale');
      if (action === 'mark-stale') {
         await comment(issue.number, 'Mục này đã 60 ngày không có hoạt động thảo luận. Nếu vẫn có thể tái hiện hoặc vẫn còn nhu cầu, vui lòng bổ sung thông tin. Đề xuất tính năng và PR sẽ không tự động đóng do không hoạt động trong thời gian dài; Bug/vấn đề đủ điều kiện sẽ đóng sau 30 ngày kể từ khi được gắn nhãn.', 'stale');
        await add(issue.number, ['stale']);
      }
      if (action?.startsWith('close-') && closed < 2) {
        await comment(issue.number, action === 'close-info'
           ? 'Đã quá 14 ngày chờ bổ sung thông tin, tạm thời đóng; điều này không có nghĩa là vấn đề đã được khắc phục. Sau khi bổ sung thông tin, bạn có thể đề nghị quản trị viên mở lại.'
           : 'Đã quá 30 ngày kể từ khi được đánh dấu không hoạt động trong thời gian dài, tạm thời đóng; điều này không có nghĩa là vấn đề đã được khắc phục. Nếu vẫn gặp vấn đề, vui lòng bổ sung thông tin và đề nghị quản trị viên mở lại.', 'closed');
        await add(issue.number, ['bot: auto-closed']);
        await mutate(`Close #${issue.number}`, () => github.rest.issues.update({ ...repo, issue_number: issue.number, state: 'closed', state_reason: 'not_planned' }));
        closed++;
      }
    }
    return;
  }
  if (context.payload.sender?.type === 'Bot') return;
  const number = context.payload.pull_request?.number ?? context.payload.issue?.number;
  if (!number) return;
  const issue = (await github.rest.issues.get({ ...repo, issue_number: number })).data;
  if (names(issue).includes('bot: skip')) return;
  if (issue.pull_request) {
    // issue_comment events must never process PRs with the issue questionnaire.
    if (context.eventName !== 'pull_request_target' || issue.state !== 'open') return;
    const desired = prLabels(issue.title, issue.body);
    const events = await github.paginate(github.rest.issues.listEventsForTimeline, { ...repo, issue_number: number, per_page: 100 });
    for (const label of obsoleteBotLabels(names(issue), desired, events)) await remove(number, label);
    await add(number, desired);
    const pr = (await github.rest.pulls.get({ ...repo, pull_number: number })).data;
    if (!maintainer(issue) && (pr.changed_files > 40 || pr.additions + pr.deletions > 3000)) {
      await add(number, ['scope too large']);
       await comment(number, 'PR này có nhiều thay đổi. Vui lòng kiểm tra xem có thể chia thành các phần theo chức năng độc lập hay không, đồng thời bổ sung phạm vi kiểm thử. Lời nhắc này không có nghĩa là việc đánh giá mã đã được thông qua.', 'scope');
    }
    return;
  }
  if (context.eventName === 'issue_comment' && issue.state === 'open') {
    if (names(issue).includes('stale')) await remove(number, 'stale');
    if (context.payload.comment?.user.login === issue.user.login && names(issue).includes('needs-info')) {
      await remove(number, 'needs-info');
      await add(number, ['needs follow-up']);
    }
    return;
  }
  const plan = issuePlan(issue);
  if (!plan) return;
  if (context.payload.action === 'edited' && names(issue).includes('stale')) await remove(number, 'stale');
  if (plan.module) await add(number, [plan.module]);
  if (plan.missing.length) {
     await comment(number, `Vui lòng bổ sung: ${plan.missing.join('、')}. Không công khai thông tin xác thực hoặc nhật ký chưa được ẩn dữ liệu nhạy cảm. Nếu sau 14 ngày vẫn chưa bổ sung và không ai theo dõi, mục này sẽ tạm thời đóng; quản trị viên có thể mở lại sau đó.`, 'info');
    if (names(issue).includes('needs-info') && context.payload.action === 'edited') await remove(number, 'needs-info');
    if (!names(issue).includes('needs-info') || context.payload.action === 'edited') await add(number, ['needs-info']);
  } else if (plan.form && names(issue).includes('needs-info')) {
    await remove(number, 'needs-info');
    await add(number, ['needs follow-up']);
     await comment(number, 'Đã bổ sung thông tin theo mẫu, đang chờ quản trị viên kiểm tra thêm.', 'info');
  }
}
module.exports = { prLabels, obsoleteBotLabels, section, issuePlan, staleDecision, run };
