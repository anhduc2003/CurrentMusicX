'use strict';
const {classify}=require('./pr-risk.cjs');
function summarize(files, threads) {
  const risk=classify(files);
  const open=(threads||[]).filter(t=>!t.isResolved && !t.isOutdated);
  const high=open.filter(t=>(t.comments?.nodes||[]).some(c=>/\bP[01]\b/.test(c.body||'')));
  return {risk,open:open.length,high:high.length,needsHuman:risk.level==='high'||open.length>0};
}
function markdown(result) {
  const level={low:'Thấp',medium:'Trung bình',high:'Cao'}[result.risk.level];
  return ['## Tổng quan đánh giá PR','',`- Rủi ro thay đổi: **${level}**`,`- Thảo luận đánh giá cấp dòng hợp lệ chưa được giải quyết: **${result.open}**`,`- Trong đó có gắn nhãn P0/P1: **${result.high}**`,`- Đề xuất: **${result.needsHuman?'Kiểm tra thủ công':'Tiếp tục đánh giá theo quy tắc bảo vệ nhánh hiện tại'}**`,'','> Báo cáo này chỉ tổng hợp các thảo luận đánh giá hiện có trên GitHub, không có nghĩa là Codex đã hoàn tất đánh giá lần này và sẽ không tự động phê duyệt hoặc hợp nhất.',''].join('\n');
}
module.exports={summarize,markdown};
