# Quy chuẩn đánh giá PR của CurrentMusicX

## Mục tiêu

Ưu tiên tái sử dụng khả năng đánh giá mã hiện có của Codex trong kho lưu trữ, không bổ sung API AI trả phí. Nhận xét đánh giá của Codex nên theo ngôn ngữ tự nhiên chính trong phần nội dung mô tả của người gửi PR: PR bằng tiếng Trung giản thể dùng tiếng Trung giản thể, PR bằng tiếng Trung phồn thể dùng tiếng Trung phồn thể, PR bằng tiếng Anh dùng tiếng Anh; nếu thiếu mô tả hoặc không xác định rõ ngôn ngữ, hãy tham khảo tiêu đề; nếu vẫn không rõ thì mặc định dùng tiếng Trung giản thể. Giữ nguyên thuật ngữ kỹ thuật, đường dẫn tệp, định danh mã và các nhãn như P0–P3. Xem hướng dẫn cụ thể dành cho tác nhân trong `AGENTS.md` ở thư mục gốc của kho lưu trữ. Quy tắc này là hướng dẫn cho Codex; việc Codex Review trên GitHub có tự động tuân theo hay không phụ thuộc vào việc tích hợp đó có đọc hướng dẫn trong kho lưu trữ hay không.

## Phạm vi tự động hóa

- `pr-risk.yml` phân loại mức độ rủi ro dựa trên đường dẫn tệp được thay đổi, không thể thay thế việc đánh giá mã đầy đủ.
- `pr-review-summary.yml` chỉ đọc các cuộc thảo luận đánh giá theo dòng đã có trên GitHub và xuất Actions Summary, không gọi AI, không tuyên bố rằng Codex đã đánh giá nội dung gửi lên hiện tại.
- Tóm tắt đánh giá không phải là điều kiện để hợp nhất; các quy tắc bảo vệ nhánh, CI hiện có và quyết định của người bảo trì vẫn có hiệu lực.
- Nên kiểm tra thủ công các cuộc thảo luận chưa được giải quyết, tệp nhạy cảm, quyền, chữ ký và thay đổi đường dẫn âm thanh.
- Không tự động giải quyết các cuộc thảo luận đánh giá, không tự động phê duyệt, không tự động hợp nhất, không cung cấp khóa bí mật cho PR từ fork.
- Việc đánh giá AI có tự động được kích hoạt hay không và hạn mức sử dụng phụ thuộc vào cài đặt kết nối Codex/GitHub; cần xác nhận riêng trong GitHub.

## Kiểm tra hợp nhất PR

Các PR hướng đến `main` có thể được xác minh đồng thời. `build` thực hiện kiểm tra cú pháp mã nguồn Python của kho lưu trữ, `test` thực hiện kiểm tra cú pháp tập lệnh cộng tác và chạy actionlint. Sau khi cả hai bước kiểm tra đều đạt, người bảo trì sẽ hợp nhất theo quy tắc bảo vệ nhánh.

`Update PR branches` sẽ đồng bộ các nhánh không phải bản nháp bị tụt hậu, bỏ qua các PR có xung đột hoặc có nhãn `bot: skip`. Khi cần, có thể chạy thủ công từ Actions. Sau khi bot cập nhật nhánh, GitHub có thể yêu cầu người bảo trì phê duyệt các bước kiểm tra mới.

## Danh sách kiểm tra thủ công

1. Âm thanh: tần số lấy mẫu, bộ đệm, vòng đời phát lại, Bit-perfect và phát trong nền.
2. Android: hủy coroutine, tái cấu trúc Compose, vòng đời, quyền.
3. Bảo mật: Token, xác minh tải xuống, yêu cầu mạng, di chuyển dữ liệu.
4. Phát hành: môi trường ký, nguồn bản dựng, mã băm APK, số phiên bản.
5. Xác minh: kết quả CI, các bước tái hiện, phạm vi kiểm thử trên thiết bị thật và nội dung chưa được xác minh.
