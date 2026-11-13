# Quy tắc giữ lại bản phát hành CI của CurrentMusicX

## Mục tiêu

**Trong GitHub Releases, luôn chỉ giữ lại phiên bản CurrentMusic CI hoàn chỉnh mới nhất**. Các bản phát hành chính thức (như `v1.3.0`, `v1.3.1`, `v1.3.2`) phải luôn được loại khỏi phạm vi tự động dọn dẹp.

## Thời điểm phát hành bản CI

Trước khi hợp nhất PR, các bước kiểm tra mã nguồn và cộng tác chạy song song. Bản dựng Android Release và Lint được tập trung vào nhánh chính. Chừng nào vẫn còn PR đang mở có nhánh đích là `main`, kho lưu trữ sẽ hoãn xây dựng và phát hành bản CI; PR ở trạng thái nháp và PR có nhãn `bot: skip` cũng được tính. Việc hợp nhất trong thời gian này chỉ kích hoạt bước kiểm tra nhẹ **Check pending CI release**.

Sau khi PR cuối cùng được hợp nhất, đóng hoặc chuyển sang nhánh khác, kiểm tra các thay đổi tích lũy giữa `main` mới nhất và bản CI hoàn chỉnh trước đó. Nếu có thay đổi ứng dụng thì xây dựng và phát hành một bản CI. Nếu chỉ thay đổi tài liệu hoặc CI thì bỏ qua bước đóng gói. Quy trình phát hành có ký sẽ kiểm tra lại trạng thái PR và nhánh chính.

Sau khi Android CI xây dựng thành công và tải APK lên, khởi chạy rõ ràng **Publish CI build** thông qua `workflow_dispatch`, truyền vào `run_id` và `run_attempt` của bản dựng để tránh phụ thuộc vào sự kiện `workflow_run`, vốn có thể không được kích hoạt tiếp sau khi bộ lập lịch bot chạy. Phía phát hành xác minh nguồn, số lần chạy, trạng thái hoàn tất và các bước xây dựng/tải APK lên thông qua GitHub API; nếu tác vụ thông báo chưa kết thúc thì chờ tối đa hai phút, quá thời hạn sẽ báo lỗi. Việc ký vẫn chỉ được thực hiện trong tác vụ riêng biệt thuộc môi trường `ci-signing`.

Nếu cần phát hành lại bản dựng hiện có, có thể chạy thủ công **Publish CI build** trên `main` và điền ID lần chạy cùng số lần chạy của Android CI ban đầu. Việc phát hành lại cũng phải đáp ứng các điều kiện phát hành như không có PR đang mở, commit của bản dựng vẫn là nhánh chính mới nhất và có thay đổi ứng dụng tích lũy; đồng thời không được thay thế phiên bản cùng tên đã phát hành.

## Quy tắc xác định

- Khi chỉ sửa quy trình làm việc GitHub hoặc tập lệnh CI, bước kiểm tra cộng tác sẽ chạy kiểm tra cú pháp tập lệnh và xác thực quy trình làm việc; bản dựng tự động sẽ bỏ qua bộ công cụ Android, APK và bản phát hành trước. Sau khi hàng đợi được giải phóng, có thể khởi chạy Android CI thủ công để xác minh bộ công cụ; việc có phát hành hay không vẫn tùy thuộc vào các thay đổi tích lũy.
- Ghi chú cập nhật tự động của cả bản CI và bản chính thức đều loại trừ PR chỉ sửa CI. Nếu cùng PR đó còn sửa mã ứng dụng, vẫn giữ lại mục cập nhật của PR.

- Chỉ khớp với tag có định dạng `vX.Y.Z-ci.N`, tên `CurrentMusic CI #N`, `prerelease=true` và `draft=false`.
- Phải có cả hai tệp đính kèm `CurrentMusic-Android-vX.Y.Z-ci.N.apk` và `SHA256SUMS.txt`; Release không đáp ứng tiêu chuẩn, bản nháp và các bản phát hành trước khác sẽ không bị tự động xóa.
- So sánh số bản dựng CI `N` giữa các phiên bản và giữ lại bản có số lớn nhất. **Không dựa vào thứ tự hiển thị của danh sách GitHub Releases**.
- Nếu phát hiện số bản dựng trùng lặp, danh sách bất thường hoặc lỗi quyền, hãy dừng hoặc báo lỗi; không thực hiện thao tác xóa khi chưa xác định chắc chắn.
- Sau khi dọn dẹp CI Release cũ, xóa Git tag `vX.Y.Z-ci.N` tương ứng; nếu xóa Release thất bại thì không xóa tag của Release đó.
- Sau khi tác vụ phát hành thành công, lên lịch dọn dẹp một cách tường minh; đầu vào `workflow_run` được giữ lại để tương thích cũng yêu cầu quy trình phát hành và tác vụ `publish` của lần chạy đó phải thành công. Nếu bỏ qua tác vụ phát hành thì không lên lịch dọn dẹp.
- Khi quy tắc lần đầu được hợp nhất vào `main`, tự động chạy một lần để dọn dẹp các CI Release cũ hiện có; cũng có thể chạy thủ công từ Actions.

## Giới hạn an toàn

- `.github/workflows/ci-prune.yml` chỉ checkout tập lệnh đáng tin cậy từ `main`, không checkout hoặc thực thi tệp từ Fork PR.
- Tác vụ dọn dẹp dùng `actions: read` để xác minh tác vụ phát hành, và dùng `contents: write` để quản lý CI Release cùng tag.
- **Không xóa Release hoặc tag của bản phát hành chính thức**, cũng không tự động xóa bản nháp, bản phát hành trước của dự án khác hoặc bản dựng CI chưa được phát hành hoàn chỉnh.
- Khi bản CI mới chưa được công khai phát hành thành công, vẫn giữ lại phiên bản CI mới nhất cũ để đảm bảo lối vào cập nhật ứng dụng luôn khả dụng.
