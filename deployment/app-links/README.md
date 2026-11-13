# Liên kết web Android

Ứng dụng tiếp nhận liên kết HTTP/HTTPS, có thể mở khi khởi động nguội hoặc khi đang chạy:

- Bài hát, danh sách phát, album, nghệ sĩ và MV trên `music.163.com`, `y.music.163.com` của Nhạc Vân NetEase; hỗ trợ các dạng như `/#/song?id=…`, `/song?id=…`, `/m/song?id=…`.
- Liên kết ngắn `163cn.tv`: yêu cầu ẩn danh, theo tối đa 5 lần chuyển hướng HTTP và kiểm tra tên miền ở từng bước; không chạy tập lệnh web, không truy cập đích chuyển hướng tùy ý.
- Lời mời nghe cùng nhau trên Nhạc Vân NetEase: `https://st.music.163.com/listen-together/share/index.html?roomId=…&inviterId=…` và liên kết ngắn `163cn.tv` chuyển hướng đến địa chỉ này. Mở “Nghe cùng nhau hai người” và điền sẵn lời mời; người dùng xác nhận rồi mới tham gia. Nếu chưa đăng nhập thì đăng nhập trước; nếu đang ở phiên khác thì thoát trước. Người dùng cũng cần cho phép `st.music.163.com` trong cài đặt mở mặc định của hệ thống.
- Các đường dẫn `/app/#/song/…`, `/app/#/ncmpl/…`, `/app/#/pl/…`, `/app/#/album/…`, `/app/#/artist/…`, `/app/#/mv/…` và trang chủ của bốn trang web tích hợp sẵn trong CurrentMusic. Các liên kết hash cùng loại ở thư mục gốc của trang web cũng được hỗ trợ.

Liên kết bài hát sẽ tải thông tin bài hát, phát nhạc và mở rộng trình phát; danh sách phát, album, nghệ sĩ và MV sẽ mở trang chi tiết tương ứng. Liên kết không đăng nhập, thay đổi máy chủ hoặc tự động tham gia phòng; trong lúc nghe cùng nhau, bài hát hiện tại sẽ không bị thay thế. Các tuyến đường không được hỗ trợ sẽ hiển thị thông báo.

## Xác minh trang web

`assetlinks.json` là tệp triển khai trên trang web, không phải tài nguyên APK. **Hợp nhất** các liên kết trong tệp này vào `https://tên-miền/.well-known/assetlinks.json` của từng trang web sau đây, đồng thời giữ lại các mục hợp lệ khác:

- `music.20110208.xyz`
- `cm.bileizhen.top`
- `currentmusic.geekertao.com`
- `music.ctcehs.com`

Yêu cầu HTTPS trả về mã 200, `Content-Type: application/json` và không chuyển hướng. Ví dụ sử dụng dấu vân tay chữ ký phát hành CurrentMusicX được các trang chính thức hiện có công bố; trước khi triển khai, hãy đối chiếu với chữ ký của APK phát hành thực tế. Giữ lại `common.get_login_creds` để tiếp tục hỗ trợ khóa truy cập và thêm `common.handle_all_urls`. Gói gỡ lỗi và gói kiểm thử `.verification` không tự động được xác minh cho các tên miền chính thức.

Chỉ sửa APK không thể hoàn tất liên kết trang web. Khi kiểm tra vào ngày 2026-11-09, trang chính và trang geekertao chỉ có liên kết khóa truy cập; hai trang còn lại trả về 404. Cấu hình trong kho lưu trữ này sẽ không tự động được triển khai lên các trang web đó.

Các tên miền của Nhạc Vân NetEase do bên thứ ba kiểm soát, dự án này không thể thêm tệp xác minh. Trên Android 12 trở lên, vào ứng dụng “Cài đặt → Mở liên kết mặc định” và cho phép các tên miền tương ứng trên trang hệ thống; đường dẫn cụ thể thay đổi tùy hệ thống, và ứng dụng chính thức của Nhạc Vân NetEase có thể đã chiếm liên kết mặc định.

## Kiểm thử nghiệm thu

Sau khi cài APK có chữ ký phát hành, trước tiên hãy xác minh liên kết trang web:

```sh
adb shell pm verify-app-links --re-verify com.bileizhen.currentmusic
adb shell pm get-app-links com.bileizhen.currentmusic
```

Chờ hệ thống xác minh, sau đó xác nhận các tên miền CurrentMusic hiển thị `verified`. Người dùng cần cho phép các tên miền Nhạc Vân NetEase; không được ghi nhận chúng là đã tự động xác minh thành công.

Nhấn vào các liên kết sau trong ứng dụng trò chuyện hoặc trình duyệt để kiểm tra khi ứng dụng đã đóng, đang chạy, khi nhấn liên tục các liên kết khác nhau, xoay màn hình và dùng nút quay lại; cần có kết nối mạng và nguồn nhạc hiện tại hỗ trợ bài hát:

- `https://music.163.com/song?id=347230`
- `https://music.163.com/#/playlist?id=3778678`
- `https://y.music.163.com/m/album?id=32311`
- `https://music.20110208.xyz/app/#/song/347230`
- `https://cm.bileizhen.top/app/#/ncmpl/3778678`
- Dùng lời mời nghe cùng nhau trên Nhạc Vân NetEase còn hiệu lực để kiểm thử: xác nhận sau khi mở, lời mời chỉ được điền sẵn chứ không tự động tham gia; sau khi đăng nhập, quay lại, xoay màn hình hoặc mở liên tiếp các lời mời khác nhau, nội dung vẫn hiển thị chính xác. Nếu đang ở phiên khác, sau khi thoát lời mời mới vẫn được giữ lại; lời mời hết hạn sẽ được quy trình tham gia hiện có thông báo lỗi.

Lệnh `adb shell am start -a android.intent.action.VIEW -d 'URL' -p com.bileizhen.currentmusic` có chỉ định tên gói chỉ có thể xác minh quy trình xử lý của ứng dụng, không chứng minh được việc phân phối mặc định của hệ thống hoặc xác minh tên miền thành công. Khi nghiệm thu trên thiết bị thật, cần thử thêm thao tác mở liên kết mà không chỉ định tên gói.
