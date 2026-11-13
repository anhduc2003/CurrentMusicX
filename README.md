<div align="center">

<img src=".github/img/icon.svg" width="88" height="88" alt="Biểu tượng nốt nhạc bo tròn màu xanh CurrentMusicX">

# CurrentMusicX

**Tiếng Trung giản thể** · [Tiếng Anh](README-en.md)

**Để bìa album, lời bài hát và âm nhạc cùng hòa quyện**

Thư viện Nhạc Vân NetEase · Thư viện nhạc tự lưu trữ Navidrome / Emby · Cùng nghe nhạc đôi

Kotlin / Jetpack Compose / Ứng dụng Android gốc Miuix

<p>
  <a href="https://github.com/bileizhen/CurrentMusicX/releases/latest"><img src="https://img.shields.io/github/v/release/bileizhen/CurrentMusicX?style=flat-square&color=1677FF" alt="Phiên bản mới nhất"></a>
<a href="#Tính tương thích"><img src="https://img.shields.io/badge/Android-8.0%2B-1677FF?style=flat-square" alt="Android 8.0 trở lên"></a><a href="#Mã nguồn mở và ghi nhận"><img src="https://img.shields.io/badge/License-GPL--3.0_%2B_AGPL--3.0-555555?style=flat-square" alt="Các thành phần GPL-3.0 và AGPL-3.0"></a></p>

**[Tải ứng dụng](https://github.com/bileizhen/CurrentMusicX/releases/latest)** · [Bắt đầu sử dụng](#Bắt đầu sử dụng) · [Lịch sử cập nhật](https://github.com/bileizhen/CurrentMusicX/releases) · [Báo cáo sự cố](https://github.com/bileizhen/CurrentMusicX/issues)
</div>

CurrentMusicX là ứng dụng Android gốc của [CurrentMusic](https://github.com/backrooms-yrc/CurrentMusic). Bìa album hình tròn, màu sắc động, lời bài hát tô sáng từng chữ và trình phát nổi giúp thư viện nhạc, khám phá và phát nhạc kết nối liền mạch, đồng thời hỗ trợ bố cục dọc, ngang và màn hình rộng.

## Xem trước giao diện

<p align="center">
  <img src=".github/img/home.jpg" width="320" alt="Trang chủ: gợi ý hằng ngày, lối vào thư viện nhạc và trình phát nổi">
  <img src=".github/img/player.jpg" width="320" alt="Trình phát: bìa album đắm chìm, nền đổi màu động và lời bài hát">
</p>

<p align="center"><sub>Thiết bị thật RMX5060 · Bản chính thức 1.3.1 · Chụp ngày 2026-11-03. Giao diện có thể khác tùy phiên bản cài đặt thực tế; nhấp vào ảnh để xem ảnh gốc.</sub></p>

<details>
<summary>Thêm giao diện: lời bài hát, tìm kiếm, trang nghệ sĩ và khám phá</summary>

<p align="center">
  <img src=".github/img/lyrics.jpg" width="280" alt="Lời bài hát: hoạt ảnh Monet, tô sáng từng chữ và bản dịch">
  <img src=".github/img/search.jpg" width="280" alt="Tìm kiếm: kết quả bài hát, ca sĩ và album">
</p>
<p align="center">
  <img src=".github/img/artist.jpg" width="280" alt="Trang nghệ sĩ: tác phẩm và thông tin ca sĩ">
  <img src=".github/img/discover.jpg" width="280" alt="Khám phá: thể loại nhạc và lối vào mục gợi ý">
</p>

</details>

## Bắt đầu sử dụng

1. **Tải xuống và cài đặt.** Truy cập [Releases](https://github.com/bileizhen/CurrentMusicX/releases/latest), tải `CurrentMusic-Android-vsố phiên bản.apk` rồi cài đặt theo hướng dẫn của hệ thống. Bản chính thức cùng chữ ký có thể cài đè để nâng cấp.2. **Chọn nguồn.** Trong「Cài đặt → Nguồn nhạc và mạng」, chọn riêng nguồn nhạc và nguồn lời bài hát. Mặc định, lời bài hát được **tự động chọn nguồn phù hợp nhất: ưu tiên từng chữ, dự phòng từng dòng**.
3. **Kết nối thư viện nhạc.** Tùy nhu cầu, đăng nhập CurrentMusic, liên kết với Nhạc Vân NetEase hoặc kết nối Navidrome / Emby trong「Cài đặt → Nguồn nhạc và mạng → Thư viện nhạc tự lưu trữ」để tìm kiếm, lưu vào danh sách yêu thích và phát nhạc. Sau khi kết nối, lối vào thư viện nhạc sẽ xuất hiện trong「Của tôi → Dịch vụ âm nhạc」và bị ẩn sau khi đăng xuất.

Tính khả dụng của bài hát, chất lượng âm thanh, hiệu ứng âm thanh dành cho thành viên và nội dung gợi ý tùy thuộc vào dịch vụ tương ứng và quyền của tài khoản. Trang này giới thiệu mã nguồn `main` hiện tại; xem các thay đổi và phạm vi nghiệm thu của những phiên bản đã phát hành tại [GitHub Releases](https://github.com/bileizhen/CurrentMusicX/releases).

[Tổng quan tính năng](#Tổng quan tính năng) · [Nguồn nhạc và lời bài hát](#Nguồn nhạc và lời bài hát) · [Nguyên lý hoạt động của dự án](#Nguyên lý hoạt động của dự án) · [Tính tương thích](#Tính tương thích) · [Câu hỏi thường gặp](#Câu hỏi thường gặp) · [Build và kiểm thử](#Build từ mã nguồn và kiểm thử trên thiết bị) · [Đóng góp](#issues, PR và phát hành phiên bản)
## Tổng quan tính năng

### Trải nghiệm nghe nhạc mở rộng theo từng giai điệu

- **Phát nhạc và điều khiển**：Điều khiển trong nền, thanh thông báo, màn hình khóa và qua Bluetooth; hàng đợi, lặp, phát ngẫu nhiên, chế độ rung động trái tim, hẹn giờ tắt và MV; nhấn giữ trình phát thu nhỏ để xác định vị trí bài đang phát trong danh sách hiện tại.
- **Lời bài hát và hiệu ứng động**：Tự động khớp từ sáu nguồn, tô sáng từng chữ, bản dịch và phiên âm; hiệu ứng động Monet, khói cuộn, bụi sao và phối cảnh; chỉnh độ lệch riêng cho từng bài, nhập nhiều định dạng và xuất FIA / LRC.
- **Hiệu ứng âm thanh và chuyển tiếp**：Bộ cân bằng âm thanh trên thiết bị, hiệu ứng âm thanh tiêu chuẩn nguyên bản và thông minh, AI điều chỉnh âm thanh theo cảm xúc, AI chuyên gia tinh chỉnh âm thanh và chế độ phát loa ngoài; chuyển tiếp thông minh hoặc mờ dần.
- **Tải xuống và chia sẻ**：Chọn chất lượng âm thanh và thư mục, theo dõi tiến trình nền, hủy và thử lại; ghi thẻ âm thanh, ảnh bìa và lời bài hát; tạo poster, liên kết phát nhạc và mã QR.
- **Mức chất lượng âm thanh**：Hi-Res (`hires`), âm thanh độ nét cao tinh tuyển (`jyeffect`) và âm thanh vòm tinh tuyển (`vivid`) được hiển thị riêng; có thể chọn mức cao hơn và Dolby Atmos. Mức đã chọn là chất lượng yêu cầu từ nguồn nhạc, không có nghĩa tệp cuối cùng hoặc âm thanh đầu ra của thiết bị đã đạt chất lượng đó; khả năng sử dụng thực tế phụ thuộc vào nguồn nhạc, bài hát và quyền của tài khoản.

### Thư viện nhạc của riêng bạn, cùng nhau thưởng thức

- **Thư viện nhạc**：Quản lý mục Nhạc yêu thích và danh sách phát của Nhạc Vân NetEase (bao gồm lưu danh sách phát của người khác), danh sách phát tự tạo bằng CurrentMusic, album và nghệ sĩ từ Navidrome / Emby; hỗ trợ yêu thích, tìm kiếm và phát nhạc ở chất lượng gốc.
- **Nhạc trên thiết bị**：Mở「Nhạc trên thiết bị → Phạm vi quét → Chọn thư mục」để chỉ quét thư mục đã chọn và các thư mục con, đồng thời ghi nhớ vị trí đã chọn; thông báo chọn lại khi quyền truy cập hết hiệu lực. Muốn quét nhạc trên toàn thiết bị, cần chủ động chọn và cấp quyền âm thanh; thay đổi phạm vi quét không thay thế hàng đợi phát hiện tại.
- **Khám phá âm nhạc**：Tìm kiếm theo danh mục, tìm bài hát bằng lời, từ khóa gợi ý, đề xuất hằng ngày, tìm kiếm thịnh hành và phong cách âm nhạc; chế độ khám phá riêng tư hỗ trợ các chế độ mặc định, quen thuộc, khám phá, ghép hình và bối cảnh động; có thể khôi phục hàng đợi ban đầu sau khi kết thúc.
- **Cùng nghe nhạc**：Lời mời gốc và liên kết ngắn của Nhạc Vân NetEase, hàng đợi chia sẻ, đồng bộ chuyển bài và khôi phục khi mất kết nối; lịch sử nghe cùng nhau, bảng xếp hạng, báo cáo kết thúc và báo cáo duyên phận.
- **Tài khoản và giao lưu**：Nhiều tài khoản, cấp độ và quá trình phát triển tài khoản (bao gồm thử thách cấp độ), quản lý hồ sơ, đổi mật khẩu và khôi phục qua email; đăng nhập còn hỗ trợ mã xác minh email, câu hỏi bảo mật và khóa truy cập (Android 9 trở lên), đồng thời có thể quản lý ứng dụng xác thực và bảo vệ bằng câu hỏi bảo mật;「Của tôi → Thời lượng nghe nhạc」tổng hợp lịch sử nghe nhạc từ thiết bị này và Nhạc Vân NetEase, cung cấp báo cáo tuần / tháng / năm và xuất tường đĩa nhạc; thông báo, bài đăng, trò chuyện công khai và hội thoại AI; có thể bật trung tâm nhiệm vụ Nhạc Vân NetEase và tự động điểm danh khi ứng dụng ở foreground, mặc định tắt.

### Tùy chỉnh theo sở thích

Giao diện sáng và tối, chế độ đen tuyền và chọn màu Monet; ảnh bìa hình tròn hoặc đắm chìm, hình nền phát nhạc tùy chỉnh, kính lỏng, thanh điều hướng nổi và lời bài hát trên màn hình chính. Trang giao diện cho phép xem trước chủ đề theo thời gian thực và điều chỉnh màu sắc, phông chữ, tỷ lệ chữ, hoạt ảnh khởi chạy và kiểu danh sách phát.

<details>
<summary>Chi tiết về giao diện và tương tác</summary>

- **Chủ đề và phông chữ**：Xem trước trang chủ, khám phá, của tôi, cài đặt và trình phát thu nhỏ; hỗ trợ bảng màu, chọn màu dạng lưới / quang phổ / HSV, tìm kiếm phông chữ hệ thống và nhập TTF / OTF / TTC; phông chữ nhập vào được sao chép và lưu trong ứng dụng.
- **Chất liệu kính**：「Cài đặt → Giao diện → Kính lỏng」cho phép chọn kiểu tiêu chuẩn hoặc kính lỏng; kiểu kính lỏng lấy cảm hứng từ hiệu ứng truyền sáng, viền khúc xạ và bóng mềm của FlClash; công tắc làm mờ điều khiển riêng mức độ kính mờ. Trên Android dưới phiên bản 13 hoặc khi không thể lấy mẫu nền, ứng dụng sẽ dùng bảng thông thường.
- **Hoạt ảnh khởi chạy**：Cổ điển (mặc định), quầng sáng, chữ ký và không hoạt ảnh; có thể xem trước trong cài đặt. Kiểu chữ ký viết tên người dùng hiện tại khi ngoại tuyến; nếu tên người dùng trống, hiển thị「Chào mừng bạn lắng nghe」; tên dài sẽ tự động xuống dòng và thu nhỏ, các nét chữ chưa được vẽ sẽ dần hiện ra; khi hệ thống tắt hoạt ảnh, ứng dụng chuyển thẳng sang trạng thái hoàn tất.
- **Điều hướng và bố cục**：Thanh trên cố định với hiệu ứng mờ dần, thanh điều hướng nổi, danh sách cài đặt bo góc theo nhóm và chuyển đổi vùng chứa khi quay lại; Chat dùng vùng nhập nhiều dòng và menu thêm, giữ lại bản nháp khi gửi thất bại và cho phép thử lại rõ ràng; thanh điều hướng dưới được ẩn khi mở.
- **Trình phát ngang**：Tuân theo lựa chọn ảnh bìa trên trang giao diện; ảnh bìa đắm chìm phủ kín trang phát, khu vực lời bài hát và điều khiển bên phải dùng nền tối dần; vuốt trái hoặc phải vẫn chuyển đổi giữa lời bài hát và điều khiển phát nhạc.
- **Duyệt danh sách phát**：Dải ruy-băng 3D Depth Ribbon hoặc giá đĩa CD; giá đĩa hỗ trợ chọn bài ngẫu nhiên, nghe thử đoạn cao trào hoặc toàn bộ bài, phát lặp và tự động phát bài tiếp theo; có thể hoàn tác sau khi bỏ yêu thích.

</details>

<details>
<summary>Hướng dẫn về phát nhạc, lời bài hát và hiệu ứng âm thanh</summary>

- Khi nhấn vào một bài hát trong kết quả tìm kiếm, chỉ bài đó được thêm vào và phát, hàng đợi hiện tại được giữ nguyên; danh sách phát và album hỗ trợ phát toàn bộ danh sách.
- Chỉ bật một trong hai tùy chọn: chuyển tiếp thông minh khi tự động chuyển bài hoặc hiệu ứng mờ dần; mặc định cả hai đều tắt. Có thể điều chỉnh hiệu ứng mờ dần từ 1–12 giây. Tùy chọn cho phép phát đồng thời với ứng dụng khác được cài đặt riêng.
- Chọn 「Menu → Nền phát」 ở góc trên bên phải trình phát để chọn ảnh từ hệ thống, xem trước và điều chỉnh độ mờ từ 0–100%; ảnh chỉ được lưu trên thiết bị và vẫn được giữ sau khi khởi động lại. Có thể khôi phục nền mặc định lấy màu từ ảnh bìa.- Sau khi bật 「Của tôi → Tài khoản NetEase Cloud Music → Nhiệm vụ NetEase Cloud Music」, trang “Của tôi” sẽ hiển thị lối vào nhiệm vụ bên dưới tài khoản NetEase Cloud Music. Cần đăng nhập NetEase Cloud Music trên thiết bị; phải bật riêng tính năng tự động điểm danh, chỉ thực hiện điểm danh hằng ngày và điểm danh Yunbei khi ứng dụng đang ở tiền cảnh. Tiến độ nhiệm vụ và phần thưởng do NetEase Cloud Music xác nhận; việc nghe nhạc được ghi nhận dựa trên lịch sử phát thực tế.- Ưu tiên dùng mốc thời gian từng chữ thực tế cho lời bài hát và lọc bỏ trục thời gian ước tính từ máy chủ. Khi chọn chiến lược tương thích karaoke là 「Luôn luôn」, có thể tạo hiệu ứng tô sáng gần đúng cho lời bài hát thông thường hiển thị theo từng dòng; đây chỉ là hiệu ứng hiển thị trên thiết bị. Tùy chọn 「Tự động ẩn bảng điều khiển trên trang lời bài hát」 mặc định tắt; khi bật, khu vực điều khiển trên trang lời bài hát sẽ ẩn sau 3 giây không thao tác. Nhấn nút ở dưới cùng để hiện lại.- Trang hiệu ứng âm thanh sử dụng Compose gốc; đã tích hợp sẵn engine tương thích ARM64, không cần cài thêm NetEase. Quyền lợi thành viên được máy chủ xác minh; các thuật toán chưa được tích hợp sẽ hiển thị thông báo rõ ràng. Trong trường hợp phát qua loa ngoài, thiết lập chỉ được giữ trong phiên chạy hiện tại.- Ứng dụng không hiển thị thường trực lời bài hát / FPS trên đảo động, mà chỉ hiển thị tạm thời hiệu ứng âm thanh, nội dung tải xuống và phản hồi lỗi. Lời bài hát nổi trên màn hình chính cần được hệ thống cấp quyền; cửa sổ luôn được khóa. Có thể điều chỉnh chiều cao, cỡ chữ và bản dịch trong phần cài đặt; vẫn hỗ trợ nhấn để chuyển bài và vuốt trái/phải để đổi bài.

</details>

<details>
<summary>Hướng dẫn về thư viện nhạc, tìm kiếm và nghe cùng nhau</summary>

- Tài khoản CurrentMusic hỗ trợ đăng nhập bằng mật khẩu, mã xác minh email, câu hỏi bảo mật và khóa truy cập. Xem [README-ANDROID-PASSKEYS.md](README-ANDROID-PASSKEYS.md) để biết thông tin về khả năng tương thích khóa truy cập trên máy chủ. Để đăng nhập NetEase Cloud Music, trước tiên hãy đăng ký và đăng nhập tài khoản CurrentMusic, sau đó dùng mã QR hoặc mã xác minh điện thoại. Sau khi đăng nhập trên thiết bị, ứng dụng vẫn kết nối trực tiếp với NetEase Cloud Music để tìm kiếm, truy cập danh sách phát, mục yêu thích, nội dung đề xuất và bình luận. Khi chưa đăng nhập trên thiết bị, một số dịch vụ có thể dùng lại liên kết đám mây của tài khoản CurrentMusic; thông tin xác thực trên đám mây sẽ không được tải xuống thiết bị.- 「NetEase Cloud làm thư viện nhạc chính」 quyết định lối vào thư viện nhạc mặc định; 「Cài đặt → Phát → Vị trí mặc định của bài hát yêu thích」 điều khiển riêng nơi lưu bài hát yêu thích. Bài hát trên Navidrome sử dụng mục yêu thích riêng của máy chủ đó.- Danh sách phát hỗ trợ tìm kiếm, sắp xếp, tạo và quản lý. Trong danh sách phát và trang 「Bài hát yêu thích」, có thể chọn nhiều bài hát để thêm hoặc xóa khỏi mục yêu thích hàng loạt; danh sách phát của người khác trên NetEase Cloud Music hỗ trợ lưu để theo dõi. Có thể chọn duyệt bằng dải ruy-băng 3D Depth Ribbon hoặc giá đĩa CD. Giá đĩa hỗ trợ tìm kiếm và thu gọn, chọn bài ngẫu nhiên, nghe thử đoạn cao trào hoặc toàn bộ bài, phát lặp và tự động phát bài tiếp theo; có thể hoàn tác việc bỏ thích trên đảo động. Danh sách phát tự tạo trên CurrentMusic có thể nhập từ 「Bài hát yêu thích」 của NetEase Cloud Music. Chuyển thư viện nhạc sẽ không xóa các danh sách phát hiện có.- Trang chủ tìm kiếm hỗ trợ thu gọn lịch sử, hiển thị từ khóa đề xuất thành hai cột và bảng xếp hạng tìm kiếm nóng của NetEase Cloud Music có thể vuốt ngang (bao gồm các danh mục như ACG, tiếng Nhật). Khi nhập một phần từ khóa, các từ khóa gợi ý sẽ hiện ra; nhấn để tìm kiếm ngay. Danh mục 「lời bài hát」 hỗ trợ tìm bài hát bằng lời bạn nhớ và hiển thị đoạn lời khớp.- Danh mục 「danh sách phát」 trên trang tìm kiếm hiển thị ảnh bìa, người tạo và số bài hát trong danh sách phát trên NetEase Cloud Music; hỗ trợ phân trang và thử lại khi có lỗi. Nhấn vào danh sách để mở trang chi tiết như trước; kết quả và vị trí cuộn được giữ nguyên khi chuyển danh mục hoặc quay lại.- Nhấn vào bài hát, danh sách phát, album, ca sĩ, MV, liên kết rút gọn `163cn.tv`, lời mời nghe cùng nhau hoặc liên kết `/app/` trên trang CurrentMusic trong ứng dụng trò chuyện hay trình duyệt để mở trong ứng dụng: sau khi tải thông tin, bài hát sẽ được phát và trình phát mở rộng; các nội dung khác sẽ mở trang chi tiết tương ứng. Lời mời nghe cùng nhau chỉ điền sẵn thông tin; bạn cần xác nhận mới tham gia. Tên miền CurrentMusic cần được xác minh liên kết ứng dụng của trang web; với tên miền NetEase, cần cho phép trong mục hệ thống 「Mở liên kết mặc định」. Các đường dẫn chưa được hỗ trợ sẽ hiển thị thông báo.- Tính năng nghe cùng nhau trên NetEase Cloud Music dùng chung chức năng tìm kiếm và hàng đợi phát hiện có, hỗ trợ đề xuất theo sở thích của cả hai bên, xóa bài, xóa toàn bộ, tải xuống toàn bộ hàng đợi và thêm vào mục yêu thích; các mục đề xuất, lịch sử, bảng xếp hạng và báo cáo phụ thuộc vào quyền do máy chủ cấp.- Khi khởi động nguội, ứng dụng kiểm tra và khôi phục phiên NetEase Cloud Music bị gián đoạn. Nếu chủ động bắt đầu phát nội dung khác khi khởi động, lần khôi phục tự động này sẽ bị hủy. Sau khi kết thúc phiên, báo cáo chỉ được hiển thị khi đã xác nhận phiên từ xa kết thúc; bạn có thể lưu bài hát yêu thích và xuất áp phích để chia sẻ.- Phòng trên máy chủ CurrentMusic còn hỗ trợ mật khẩu, phê duyệt yêu cầu bài hát và quyền thành viên. Thư viện nhạc riêng trên Navidrome / Emby hiện chỉ phát trên thiết bị này, không tham gia phòng CurrentMusic.

</details>

## Nguồn nhạc và lời bài hát

**Cài đặt riêng** nguồn nhạc và nguồn lời bài hát tại 「Cài đặt → 音源与网络」. Thay đổi nguồn lời bài hát sẽ tải lại lời bài hát hiện tại; bộ nhớ đệm được phân tách theo nguồn.
### Nhạc đến từ đâu

| Nguồn nhạc | Cách sử dụng |
| --- | --- |
| **CurrentMusic（Mặc định）** | Phát nhạc trực tuyến từ máy chủ CurrentMusic đã cấu hình; cần tài khoản trên trang web || NetEase Cloud nguyên bản | Kết nối trực tiếp bằng điện thoại, sử dụng quyền của tài khoản NetEase Cloud trên thiết bị; không tự động chuyển sang phát nhạc trực tuyến từ máy chủ || LeiZ API | Sử dụng dịch vụ tương ứng sau khi cấu hình API Key; khóa được mã hóa và lưu trên thiết bị |
| Nguồn nhạc Lạc Tuyết | Nhập tập lệnh từ tệp `.js` hoặc liên kết; có thể nhập nhiều tập lệnh và bật「Tự động chuyển đổi thông minh giữa các nguồn」: ưu tiên tập lệnh lấy URL nhanh hơn, khi thất bại sẽ tự động thử các nguồn đã nhập khác; hiện chỉ phân tích bài hát có ID NetEase Cloud Music hợp lệ, tìm kiếm và danh sách phát vẫn dùng nguồn hiện tại |
Tập lệnh Lạc Tuyết sẽ gửi thông tin bài hát đến nhà cung cấp dịch vụ của tập lệnh; chỉ nhập từ nguồn đáng tin cậy. Khi thử lại sau lỗi trong chế độ chuyển đổi thông minh, thông tin bài hát tương tự cũng sẽ được gửi đến nhà cung cấp dịch vụ của các tập lệnh đã nhập khác. Kết nối Navidrome tại「Cài đặt → Nguồn nhạc và mạng → Thư viện nhạc tự quản」; âm thanh được phát trực tiếp từ máy chủ của bạn.

Có thể kết nối Emby tại「Cài đặt → Nguồn nhạc và mạng → Thư viện nhạc tự quản → Emby」. Sau khi kết nối,「Thư viện nhạc Emby」sẽ hiển thị trong「Của tôi → Dịch vụ âm nhạc」. Mặc định mở mục bài hát; thanh dưới cùng có cách tương tác giống trang chủ, cho phép chuyển giữa bài hát, nghệ sĩ, danh sách phát và mục yêu thích; album được mở từ trang nghệ sĩ. Tìm kiếm và làm mới được gộp thành một nhóm biểu tượng ở trên cùng; cách mở rộng tìm kiếm giống trang danh sách phát. Nhập địa chỉ máy chủ (hỗ trợ đường dẫn con của reverse proxy), tên người dùng và mật khẩu; sau khi đăng nhập, chỉ mã thông báo được lưu dưới dạng mã hóa, không lưu mật khẩu. Album, bài hát, nghệ sĩ, danh sách phát và mục yêu thích được tải theo từng trang, mỗi trang 40 mục; hỗ trợ tìm kiếm bài hát, mục yêu thích trên máy chủ, phát theo hàng đợi, lưu bộ nhớ đệm với chất lượng âm thanh gốc và tải trước. Danh sách phát hiện chỉ đọc; tải xuống tuân theo quyền của tài khoản máy chủ. Không hỗ trợ chuyển mã, nghe cùng nhau hoặc báo cáo lịch sử phát lên máy chủ. Lời bài hát tiếp tục sử dụng tính năng tự động khớp, khớp thủ công và nhập từ thiết bị; hiện chưa đọc lời bài hát từ máy chủ Emby.


### Cách chọn lời bài hát

- **Tự động chọn phù hợp nhất (mặc định)**: tìm theo thứ tự QQ → Nhạc Vân NetEase → AMLL → KuGou → Soda Music → CurrentMusic; kết quả tham gia lựa chọn khi thông tin bài hát khớp ≥75%. Nếu tìm thấy lời bài hát từng chữ thực sự thì dùng lời đó; nếu không, giữ lại lời bài hát từng dòng khả dụng đầu tiên.
- **Chọn nguồn**: có thể chọn CurrentMusic (bao gồm dữ liệu LDDC từ máy chủ), YRC / LRC từ Nhạc Vân NetEase kết nối trực tiếp bằng điện thoại, hoặc lời bài hát nhúng trong tệp cục bộ.
- **Chọn bài hát cụ thể**: tìm kiếm, xem trước, lưu hoặc nhập trong「Quản lý lời bài hát」ở trang phát nhạc; lời bài hát được lưu hoặc nhập thủ công cho từng bài, cùng lời bài hát nhúng trong tệp cục bộ, được ưu tiên hơn lựa chọn tự động.

**Muốn kết nối trực tiếp Nhạc Vân NetEase cho cả bài hát lẫn lời bài hát? Hãy chọn đồng thời nguồn nhạc「Nhạc Vân NetEase nguyên bản」và「Lời bài hát Nhạc Vân NetEase」.** Thư viện Nhạc Vân NetEase trên thiết bị và tính năng nghe cùng nhau hai người nguyên bản cũng kết nối trực tiếp độc lập với Nhạc Vân NetEase.

<details>
<summary>Khớp lời bài hát, độ lệch và nhập/xuất</summary>

- Hỗ trợ nhập LRC, TTML, FIA, VTT, YRC, QRC dạng văn bản thuần và KRC.
- Tiếp tục sử dụng cách chấm điểm thông tin bài hát của Folia: tên bài hát 45, ca sĩ 25, album 30, sau đó giảm điểm theo chênh lệch thời lượng; mỗi nguồn lấy ứng viên đáng tin cậy khớp nhất trong 10 kết quả đầu tiên, ngưỡng tự động là 75 điểm. Không tự động chọn dựa trên độ tương đồng của nội dung lời bài hát hoặc dòng thời gian.
- AMLL sử dụng ID bài hát Nhạc Vân NetEase đã khớp để truy vấn, không yêu cầu lời bài hát chuẩn của Nhạc Vân NetEase phải khả dụng; bỏ qua nguồn chậm khi hết thời gian chờ, còn xem trước thủ công vẫn giữ căn chỉnh thích ứng.
- Giữ nguyên thời gian gốc của lời bài hát từng chữ; hiệu chỉnh ở lớp hiển thị được dùng chung cho trang phát nhạc, trình phát mini và lời bài hát trên màn hình máy tính. Độ lệch dương cho từng bài làm lời xuất hiện muộn hơn, độ lệch âm làm lời xuất hiện sớm hơn; có thể cộng thêm sau khi đã căn chỉnh hoặc đặt riêng về 0.
- Xuất FIA / LRC giữ nguyên dòng thời gian gốc, không ghi phần tô sáng ước tính thành dữ liệu từng chữ thực; FIA lưu riêng và khôi phục thông tin căn chỉnh thích ứng.

</details>

<details>
<summary>Máy chủ CurrentMusic và tuyến tải bản cập nhật</summary>

「Cài đặt → Nguồn nhạc và mạng → Máy chủ CurrentMusic」cung cấp các tùy chọn theo thứ tự cố định: tự động, máy chủ chính `music.20110208.xyz`, CDN `cm.bileizhen.top`, CDN `currentmusic.geekertao.com`, CDN `music.ctcehs.com` và tùy chỉnh; đồng thời hiển thị độ trễ phản hồi của API thông báo, không sắp xếp theo độ trễ. Cài đặt mới mặc định là tự động; khi khởi động, ứng dụng đo tốc độ và chọn tuyến có sẵn nhanh hơn trong các tuyến đặt trước. Bạn cũng có thể kiểm tra lại thủ công; cấu hình máy chủ hiện có giữ nguyên lựa chọn trước đó. Chuyển đổi giữa máy chủ chính và CDN vẫn giữ đăng nhập; địa chỉ tùy chỉnh được lưu riêng, chuyển sang máy chủ khác cần đăng nhập lại. Đường dẫn API đầy đủ là `/cm/`. Nguồn chuyên dụng CurrentMusic trong mục tải bản cập nhật tự đồng bộ theo CDN tích hợp được chọn tự động hoặc thủ công; dò tìm và tải nền sử dụng cùng một tuyến. Gói cài đặt vẫn được kiểm tra kích thước và SHA-256 theo thông tin phát hành GitHub.

Địa chỉ này được dùng cho tài khoản trong ứng dụng, nguồn nhạc và lời bài hát CurrentMusic, liên kết đám mây, hồ sơ cá nhân, cộng đồng và phòng máy chủ. Nhạc Vân NetEase nguyên bản, LeiZ API, âm thanh bên ngoài và các nguồn lời bài hát tiếp tục sử dụng địa chỉ riêng; âm thanh Navidrome được phát trực tiếp từ máy chủ của bạn.

</details>

## Nguyên lý hoạt động của dự án

[![Sơ đồ nguyên lý hoạt động của CurrentMusic](.github/img/architecture.png)](.github/img/architecture.png)

### Ứng dụng khách định tuyến như thế nào

- **Web Đảo Thanh Âm** mặc định gửi thống nhất các API nghiệp vụ như tìm kiếm, chi tiết bài hát, lời bài hát, lấy URL phát, tài khoản và cộng đồng đến `/cm/` của Động cơ Thanh Âm. Nguồn nhạc LeiZ tùy chọn sẽ gửi yêu cầu trực tiếp đến LeiZ. Với tải lên theo từng bước lên ổ đĩa đám mây, công cụ sẽ yêu cầu mã thông báo và đăng ký tệp; dữ liệu tệp trung gian có thể được truyền trực tiếp đến bộ lưu trữ đối tượng.- **CurrentMusicX** chọn tuyến theo chức năng, cài đặt nguồn nhạc và trạng thái đăng nhập trên ứng dụng khách. Với nguồn nhạc mặc định, lấy URL phát, tài khoản và danh sách phát trong ứng dụng, cộng đồng, phòng trong ứng dụng và liên kết đám mây đều đi qua công cụ. Tìm kiếm, bình luận và tính năng nghe cùng nhau hai người nguyên bản của Nhạc Vân NetEase có thể kết nối trực tiếp với Nhạc Vân NetEase. Khi đã đăng nhập Nhạc Vân NetEase trên điện thoại, danh sách phát cá nhân, mục yêu thích và đề xuất thường cũng kết nối trực tiếp. Nếu không có phiên Nhạc Vân NetEase trên thiết bị, một số chức năng tài khoản có thể đi qua công cụ để dùng lại liên kết đám mây. Khi chọn nguồn「Nhạc Vân NetEase nguyên bản」, việc lấy URL phát cũng được thực hiện trực tiếp trên điện thoại; có thể chọn riêng nguồn lời bài hát.

### Cách bộ máy âm thanh luồng xử lý yêu cầu
1. Reverse proxy HTTPS chuyển tiếp `/cm/` đến backend Python và ghi lại IP đáng tin cậy của ứng dụng khách. Backend xử lý xác thực đăng nhập, giới hạn tốc độ và bộ nhớ đệm theo từng tuyến. Khách có thể tra cứu công khai nội dung tìm kiếm, chi tiết và lời bài hát; để lấy URL phương tiện, cần đăng nhập CurrentMusic. Khi tìm kiếm và lấy URL phát, IP ứng dụng khách đáng tin cậy có thể được gửi đến dịch vụ nguồn và dùng để phân tách bộ nhớ đệm.
2. Tài khoản trong ứng dụng, danh sách phát, cộng đồng và phòng do backend xử lý; phiên đăng nhập và dữ liệu nghiệp vụ được lưu trong SQLite. Phòng trò chuyện và phòng nghe nhạc có thể nhận cập nhật qua Server-Sent Events (SSE). Khi cần truy cập Nhạc Vân NetEase, backend gọi NCM API được triển khai độc lập (Node.js / api-enhanced) thông qua `CM_NCM_BASE`; API này gửi yêu cầu giao thức đến Nhạc Vân NetEase và trả về kết quả.
3. Các thao tác cá nhân trên Nhạc Vân NetEase sử dụng một luồng xác thực riêng. Engine xác thực phiên CurrentMusic, sau đó đọc Cookie Nhạc Vân NetEase được liên kết với người dùng đó để truy cập danh sách phát, mục yêu thích và các dữ liệu khác của họ. Sau khi đăng nhập Nhạc Vân NetEase trên thiết bị, ứng dụng X có thể đồng bộ thông tin xác thực với engine qua `/cm/ncmbind/import`; máy chủ sẽ xác minh quyền sở hữu tài khoản. Cookie được engine lưu chỉ dùng cho các yêu cầu từ máy chủ.

### Cách lấy URL bằng SVIP ẩn danh

1. Sau khi đăng nhập **CurrentMusic**, người dùng dùng mã thông báo trong ứng dụng để yêu cầu `GET /cm/ncm/song/url`, gửi ID bài hát và mức chất lượng âm thanh. Ở đây, “ẩn danh” nghĩa là có thể lấy URL mà không cần liên kết tài khoản Nhạc Vân NetEase cá nhân.
2. Backend đọc tệp được chỉ định bởi `CM_NCM_COOKIE`, chèn Cookie SVIP Nhạc Vân NetEase do máy chủ cấu hình vào yêu cầu lấy URL chính thức `/song/url/v1` gửi đến NCM API. Nhạc Vân NetEase trả kết quả dựa trên quyền lợi của tài khoản máy chủ, giới hạn của bài hát và mức chất lượng được yêu cầu. Mã thông báo trong ứng dụng dùng để xác thực với engine; Cookie máy chủ dùng để xác thực với dịch vụ thượng nguồn.
3. Engine chọn chất lượng âm thanh khả dụng và kiểm tra phần đầu âm thanh của URL chính thức cuối cùng. Nếu lấy URL chính thức thất bại hoặc chỉ trả về bản nghe thử, engine có thể tìm các nguồn âm thanh ứng viên thông qua bộ đối sánh đã triển khai, rồi xác minh âm thanh và thời lượng. Yêu cầu đối sánh sử dụng thông tin bài hát công khai; yêu cầu gửi đến bộ đối sánh không kèm hai loại Cookie Nhạc Vân NetEase này.
4. Backend trả về địa chỉ phát và thông tin âm thanh có thể xác minh. Khi phát thông thường, Web và X đọc âm thanh trực tiếp từ địa chỉ CDN hoặc nguồn âm thanh đối sánh được trả về. Lời bài hát sử dụng API riêng. Engine ưu tiên YRC chính thức; nếu thiếu dữ liệu từng chữ, LDDC có thể bổ sung; cuối cùng chuyển về LRC và đánh dấu thời gian từng chữ là ước tính. X có thể chọn riêng kết nối trực tiếp trên thiết bị hoặc tự động đối sánh nhiều nguồn.

Ghi chép triển khai cũng đề cập đến một điểm truy cập `ncm-inject` độc lập. Điểm này bổ sung Cookie SVIP của máy chủ cho các yêu cầu phù hợp với quy tắc chèn mà bên gọi không gửi kèm Cookie, rồi chuyển tiếp đến NCM API. Luồng yêu cầu mặc định của Web và X trong sơ đồ trên sử dụng `/cm/`.

[Định tuyến yêu cầu của ứng dụng X](app/src/main/java/io/github/currencortex/music/core/network/ApiClient.kt) · [Backend của engine](https://github.com/backrooms-yrc/CurrentMusic/tree/main/backend) · [Lấy URL phát](https://github.com/backrooms-yrc/CurrentMusic/blob/main/docs/playback-sources.md) · [Liên kết tài khoản](https://github.com/backrooms-yrc/CurrentMusic/blob/main/docs/third-party-ncm-sync.md) · [Ghi chép triển khai](https://github.com/backrooms-yrc/CurrentMusic/blob/main/docs/api-5-phase-plan.md#1-sơ đồ thực tế)
## Khả năng tương thích

| Hạng mục | Yêu cầu |
| --- | --- |
| Hệ điều hành | Android 8.0 / API 26 trở lên; mục tiêu Android 16 / API 36 |
| Kiến trúc | Gói cài đặt chính thức dành cho ARM64 (arm64-v8a), bao gồm engine hiệu ứng âm thanh nguyên bản đã được điều chỉnh |
| Bố cục | Điều hướng dọc, ngang và màn hình rộng |
| Màu động | Monet yêu cầu Android 12 / API 31 trở lên |
| Làm mờ thời gian thực | Android 13 / API 33 trở lên; trên phiên bản thấp hơn hoặc khi tắt, sử dụng chất liệu thông thường |
| Thư mục tải xuống | Bộ chọn thư mục hệ thống / SAF; từ Android 10 trở lên, mặc định là `Download/CurrentMusic` |

Một số thư viện gốc của nhà cung cấp vẫn chưa đáp ứng yêu cầu về trang 16 KiB; không thể xem là đã hoàn tất xác minh khả năng tương thích với loại thiết bị này.

## Câu hỏi thường gặp

<details>
<summary>Tệp bài hát, ảnh bìa và lời bài hát đã tải xuống được lưu ở đâu?</summary>

Khi tải xuống, bạn có thể chọn thư mục và cấp quyền truy cập thư mục đó. Thông tin bài hát, ảnh bìa và lời bài hát được ghi vào tệp âm thanh; đồng thời lưu kèm tệp LRC và ảnh bìa. Hỗ trợ MP3, FLAC, M4A, Vorbis OGG, WAV; AAC thô được đóng gói không mất dữ liệu thành M4A. Khả năng đọc lời bài hát nhúng và hiển thị từng chữ trong các trình phát khác tùy thuộc vào định dạng và mức hỗ trợ riêng của trình phát.

</details>

<details>
<summary>Cập nhật như thế nào? Bản tự dựng có thể ghi đè bản chính thức không?</summary>

Vào “Cài đặt → Kiểm tra cập nhật” để xem phiên bản mới; bạn có thể điều chỉnh việc kiểm tra khi khởi động, kênh cập nhật và nguồn tải xuống. Khi phát hiện phiên bản mới lúc khởi động, bạn có thể bỏ qua trước hoặc xem chi tiết; danh sách nguồn hiển thị trạng thái và độ trễ, mirror chuyên dụng được ghim lên đầu, các nguồn khả dụng còn lại được sắp xếp theo độ trễ. Tải xuống nền hỗ trợ hủy, thử lại và đổi nguồn; nếu mirror mặc định thất bại hoặc kích thước tệp, SHA-256 không khớp, ứng dụng sẽ thử nguồn dự phòng. Sau khi xác minh tên gói và chữ ký, Android sẽ yêu cầu xác nhận cài đặt; lần đầu có thể cần cho phép cài ứng dụng không rõ nguồn gốc.

Nếu chữ ký của bản tự dựng khác với bản chính thức, bạn không thể cài đè trực tiếp. Khi phát triển và kiểm thử trên thiết bị, hãy dùng [bản kiểm thử riêng](#Build từ mã nguồn và kiểm thử trên thiết bị), tách biệt với bản cài đặt sử dụng hằng ngày.
</details>

<details>
<summary>Cách phản hồi sự cố?</summary>

Vui lòng cung cấp phiên bản ứng dụng, mẫu điện thoại, phiên bản Android, các bước tái hiện và hiện tượng tại [Issues](https://github.com/bileizhen/CurrentMusicX/issues). Bạn có thể xuất nhật ký chẩn đoán trong phần cài đặt; hãy kiểm tra và che thông tin cá nhân trước khi chia sẻ.

</details>

## Quyền riêng tư và phát triển

Thông tin đăng nhập tài khoản trên thiết bị được mã hóa khi lưu trữ. Các dịch vụ lần lượt gửi yêu cầu đến API của Nhạc Vân NetEase, CurrentMusic, Navidrome hoặc Emby; âm thanh, MV và hình ảnh được tải từ địa chỉ do dịch vụ tương ứng trả về, còn tệp tải xuống được lưu trong thư mục do người dùng chọn. Xem [Thông tin quyền riêng tư](PRIVACY.md) để biết mục đích sử dụng và cách lưu trữ dữ liệu.

Hoan nghênh gửi Issue hoặc Pull Request. Vui lòng nêu rõ vấn đề được giải quyết và kết quả xác minh thực tế; hãy chia nhỏ các thay đổi thành từng tính năng hoặc bản sửa lỗi riêng biệt.

## Xây dựng từ mã nguồn và kiểm thử trên thiết bị

Chuẩn bị JDK 17 trở lên, Android SDK Platform 37.0 và Build Tools 35.0.0. Chỉ định đường dẫn SDK qua `ANDROID_HOME` hoặc `sdk.dir` trong `local.properties` trên máy.

Windows / PowerShell：

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
```

Trên Linux / macOS, dùng `./gradlew` để thực hiện các tác vụ tương tự. APK Debug nằm tại `app/build/outputs/apk/debug/app-debug.apk`.

Sau khi kết nối thiết bị, bạn có thể dùng gói kiểm thử độc lập, với dữ liệu được tách biệt khỏi bản cài đặt dùng hằng ngày:

```powershell
.\gradlew.bat -PisolatedDebug=true :app:assembleDebug :app:connectedDebugAndroidTest
```

Tên gói Debug độc lập là `com.bileizhen.currentmusic.verification`. Việc kiểm thử trên thiết bị có thể cần đăng nhập, mạng, quyền hệ thống hoặc tài nguyên cụ thể; xây dựng và kiểm thử đơn vị thành công không có nghĩa là API và giao diện thực tế đã được xác minh.

Bản phát hành chính thức được xây dựng bằng `:app:assembleRelease`. Thông tin ký được đọc từ `storeFile`, `storePassword`, `keyAlias` và `keyPassword` trong `keystore.properties` trên máy, tệp này không được đưa vào kho mã; nếu chưa cấu hình, gói chưa được ký sẽ được tạo. Khóa, cấu hình tài khoản và sản phẩm xác minh không được đưa vào kho mã.

<details>
<summary>Dung lượng bản Release, Baseline Profile và hồi quy hiệu năng</summary>

Bản Release bật R8 và nén tài nguyên, nhưng vẫn phát hành một APK chung chứa các ABI hiện có; giữ lại hiệu ứng âm thanh nguyên bản của Nhạc Vân NetEase, MNN và tên JNI tương ứng. Có thể tạo báo cáo dung lượng có thể tái lập bằng lệnh: `python scripts/apk_size_report.py app/build/outputs/apk/release/app-release.apk --json output/apk-size.json`. Báo cáo phân biệt dung lượng nén ZIP, dung lượng giải nén của từng ABI và căn chỉnh trang ELF; không coi thay đổi dung lượng là cải thiện tốc độ chạy. Một số thư viện gốc hiện có từ nhà cung cấp vẫn chưa đáp ứng yêu cầu trang 16 KiB.

Kiểm thử hiệu năng sử dụng ứng dụng riêng `com.bileizhen.currentmusic.benchmark`, không truy cập tài khoản người dùng hoặc dịch vụ trực tuyến. Các mô-đun `baselineprofile` và `benchmark` bao phủ khởi động nguội, trang chủ, trình phát, phát nhạc, lời bài hát, danh sách phát, cài đặt và chế độ đầu ra; chạy `:app:generateBaselineProfile` để chủ động thu thập, sau đó dùng `:benchmark:connectedPerformanceAndroidTest` so sánh `compilation=none` với `compilation=baseline`. Nếu chưa thực sự tạo Profile, trường hợp sau phải thất bại; không coi Profile có sẵn trong framework hoặc phần phụ thuộc là đã hoàn tất việc thu thập của dự án này. Thiết bị cần hỗ trợ AndroidX Macrobenchmark (mô-đun kiểm thử yêu cầu Android 9 trở lên; không thay đổi phiên bản Android 8 tối thiểu của ứng dụng chính thức).

Có thể chạy riêng kiểm thử hồi quy runtime đã nén bằng `:benchmark:connectedPerformanceAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=io.github.currencortex.music.benchmark.MinifiedRuntimeSmokeTest` để xác minh script, tuần tự hóa, Room, tải JNI và đọc/ghi thẻ âm thanh cục bộ; kiểm thử này không chứng minh Bit-perfect trên USB DAC, Bluetooth hoặc phần cứng cuối cùng thực tế. Dịch vụ chạy nền tiền cảnh im lặng của APK kiểm thử chỉ nhằm ngăn nhà sản xuất đóng băng tiến trình kiểm thử, không có trong APK chính thức. Dữ liệu hiệu năng, bản ghi màn hình và nhật ký chẩn đoán được lưu trên máy, không đưa vào kho mã.

</details>

## Issues, PR và phát hành phiên bản

Dùng [biểu mẫu Issue](https://github.com/bileizhen/CurrentMusicX/issues/new/choose) để báo cáo sự cố hoặc đề xuất ý tưởng; với PR, hãy mô tả thay đổi và cách xác minh theo [mẫu của kho mã](.github/pull_request_template.md). Khi giải quyết triệt để một sự cố, dùng `Fixes #số`; khi chỉ liên kết thảo luận, dùng `Refs #số`.
Quy trình phát triển: **nhánh tính năng riêng → PR → CI → hợp nhất vào `main`**. Mỗi PR ứng với một thay đổi cụ thể; nhánh tính năng dùng tiền tố `bileizhen/`, đẩy lên `releases`, với đích là `bileizhen/CurrentMusicX:main`. Giữ các commit nguyên tử; việc chuẩn bị phát hành như cập nhật số phiên bản được thực hiện trong một PR cuối cùng riêng.

<details>
<summary>Phân loại Issue, xử lý quá hạn và kiểm tra PR</summary>

- Issues cung cấp các biểu mẫu «Báo cáo sự cố» và «Đề xuất tính năng». Với lỗi, vui lòng điền phiên bản, thiết bị và phiên bản Android, các bước tái hiện và kết quả mong đợi; nhật ký và ảnh chụp màn hình phải được ẩn thông tin nhạy cảm. Việc tạo Issue mới không có nghĩa là sự cố đã được xác nhận hoặc việc triển khai đã được cam kết.
- Tự động phân loại sẽ đặt nhãn `module: *` dựa trên lựa chọn trong biểu mẫu; nếu thiếu thông tin bắt buộc, hệ thống dùng `needs-info` và cập nhật cùng một bình luận hỏi thêm. Các vấn đề ở định dạng tự do được giữ lại để con người đánh giá; không dùng AI để phỏng đoán vấn đề trùng lặp hoặc tự động đóng Issue. Biến kho mã `TRIAGE_MODE` hỗ trợ `live` (mặc định), `dry-run`, `off`.
- Hằng ngày lúc 03:00 giờ Bắc Kinh, hệ thống kiểm tra các mục quá hạn. `STALE_MODE` mặc định là `dry-run`; sau khi xem nhật ký Actions, người bảo trì có thể đặt thành `live`: các mục thiếu thông tin sau 14 ngày và không có hoạt động sau 60 ngày sẽ được đánh dấu stale, rồi chờ thêm 30 ngày; mỗi lần chỉ đóng tối đa hai Bug/sự cố đủ điều kiện. Đề xuất tính năng, PR, mục đã có người phụ trách, có mốc thời gian hoặc từng được mở lại sẽ được bảo vệ; các nhãn như `bot: skip`, `needs follow-up` có thể miễn trừ. Lý do đóng là hiện chưa có kế hoạch xử lý, không có nghĩa là đã sửa xong. Tác giả phản hồi sẽ chuyển mục sang trạng thái chờ theo dõi; thảo luận thông thường sẽ gỡ trạng thái stale.
- Phát triển theo quy trình nhánh tính năng riêng → PR → CI → hợp nhất vào `main`. Tiêu đề PR dùng các tiền tố như `feat:`, `fix:`, `perf:`, `docs:`, `deps:` hoặc `chore:`; bot ánh xạ chúng sang các nhãn phân loại phát hành hiện có. Nhãn do người bảo trì thêm thủ công sẽ không bị tự động gỡ. Mỗi PR ứng với một thay đổi cụ thể; khi giải quyết triệt để một sự cố, dùng `Fixes #số`; khi chỉ liên kết thảo luận, dùng `Refs #số`. Không tạo PR trống chỉ để tạo mục phát hành.- Mỗi PR chạy riêng hai bước kiểm tra nhanh. `build` dùng Python để kiểm tra các ràng buộc của mã nguồn gốc, tệp pháp lý đi kèm gói và tập lệnh phân tích APK; `test` chạy kiểm thử tập lệnh cộng tác và actionlint. Kiểm tra PR không khởi chạy Gradle hoặc tạo APK. Tự động hóa siêu dữ liệu PR chỉ sử dụng các tập lệnh đáng tin cậy từ nhánh mặc định.
- Sau khi xử lý xong tất cả PR công khai hướng đến `main`, Android CI sẽ quyết định có đóng gói hay không dựa trên các thay đổi tích lũy. Nếu có thay đổi ứng dụng, CI sẽ xây dựng Release APK, chạy kiểm thử đơn vị và Lint cho Release, sau đó chuyển sang quy trình phát hành có ký.
- Kho lưu trữ cho phép bật Auto-merge đối với PR cụ thể đã được xem xét; chỉ sử dụng Merge commit để giữ nguyên tính nguyên tử của các commit. Trước khi hợp nhất, PR phải vượt qua `build` và `test`, đồng thời đáp ứng các yêu cầu hiện có về đồng bộ nhánh và xem xét.
- Sau khi hợp nhất PR, GitHub sẽ tự động xóa nhánh tính năng từ xa; bản ghi PR và lịch sử commit vẫn được giữ lại. Nhánh cục bộ và worktree không tự động bị xóa; trước khi dọn dẹp, hãy xác nhận không có công việc nào chưa commit hoặc chưa hợp nhất.
- `Update PR branches` tự động hợp nhất `main` vào các PR không phải bản nháp bị tụt sau khi nhánh chính có tiến triển, PR được mở hoặc cập nhật. Chỉ xử lý các nhánh không có xung đột và cho phép người bảo trì cập nhật; sử dụng head SHA chính xác để tránh ghi đè đồng thời; nhánh đã đồng bộ sẽ không được cập nhật lại, còn `bot: skip` sẽ bỏ qua xử lý tự động. Workflow chỉ chạy các tập lệnh đáng tin cậy trên nhánh chính, không checkout mã PR, không force-push, không thay thế việc xem xét hoặc tự động phê duyệt CI. Sau khi cập nhật bằng `GITHUB_TOKEN` tích hợp, GitHub có thể yêu cầu người bảo trì phê duyệt các kiểm tra mới; khi quyền của fork không đủ, có xung đột hoặc quy tắc bảo vệ nhánh ngăn cập nhật, nhật ký sẽ hiển thị thông báo và cần xử lý thủ công.
- Cài đặt cập nhật cung cấp lựa chọn “Bản chính thức / Bản CI”. Lựa chọn “Bản xem trước” cũ sẽ tự động được chuyển thành CI; CI chỉ nhận các bản dựng đã ký đầy đủ có dạng `vX.Y.Z-ci.N`, không nhận beta/rc thông thường hoặc sản phẩm PR Debug. Khi ẩn kênh CI, nguồn chuyên dụng CurrentMusic chỉ trả về gói chính thức; GitHub và các nguồn proxy khác vẫn được giữ lại, đồng thời tiếp tục kiểm tra SHA-256, kích thước, tên gói, phiên bản và chữ ký.
- `Publish CI build` chỉ tải xuống sản phẩm `ci-unsigned` mà **Android CI của main thực sự đã xây dựng, xác minh và tải lên**, được ràng buộc nghiêm ngặt với ID lần chạy ở bước trước; không gọi Gradle hoặc biên dịch APK lần nữa. Sử dụng chữ ký chính thức hiện có thông qua `SIGNING_KEYSTORE_BASE64`, `SIGNING_STORE_PASSWORD`, `SIGNING_KEY_ALIAS`, `SIGNING_KEY_PASSWORD` trong GitHub Environment `ci-signing`. Environment này giới hạn ở main; job ký không mở cho PR, khóa không được đưa vào kho lưu trữ hoặc tải lên dưới dạng sản phẩm. Các điều chỉnh chỉ liên quan đến tài liệu hoặc CI nhẹ sẽ bỏ qua việc phân phối gói cài đặt; nếu thiếu chữ ký thì quy trình thất bại, không phát hành gói chưa ký.
- CI được phân phối qua tệp đính kèm prerelease công khai của GitHub, không cần người dùng cung cấp Token. Phiên bản có dạng `X.Y.Z-ci.N`, trong đó N lấy từ số lần chạy Android CI trên nhánh chính; mỗi lần sử dụng một tag riêng, không ghi đè tệp đính kèm đã có và không sửa đổi Release chính thức mới nhất. Nếu nhánh chính đã tiến lên khi phân phối, bản dựng lỗi thời sẽ bị bỏ qua. Ghi chú tự động nêu phạm vi kiểm tra, không thay thế việc nghiệm thu trên thiết bị thật.
- Mã phiên bản cài đặt Android sử dụng `versionCode cơ sở × 100000 + N`; N của bản chính thức là 0. Các bản phát hành chính thức sau đó phải tăng versionCode cơ sở và số phiên bản để cao hơn tất cả CI của phiên bản trước. CI có thể giữ lại dữ liệu và ghi đè bản chính thức hiện tại, nhưng khi quay lại bản chính thức phải chờ phiên bản cao hơn tiếp theo; không xử lý bằng cách gỡ cài đặt hoặc bỏ qua kiểm tra hạ cấp của hệ thống. Phạm vi số thứ tự CI là 1–99999; nếu vượt quá, quá trình xây dựng sẽ thất bại và người bảo trì sẽ chuyển đổi quy tắc đánh số.

</details>

<details>
<summary>Bản nháp phát hành, tệp đính kèm đã ký và ghi chú phát hành</summary>

- Sau khi commit riêng số phiên bản và hợp nhất qua PR, gắn tag `vX.Y.Z` cho commit trên nhánh chính đã được xác minh. Chạy **Prepare release draft**, điền tag hiện tại, tag của bản chính thức trước đó và phần tóm tắt bằng tiếng Trung. Quy trình xác minh số phiên bản, quan hệ tổ tiên của commit, Release hiện có và Android CI của main, sau đó dùng API gốc của GitHub để tạo danh sách phân loại theo `.github/release.yml`, giữ lại `by @tác giả in #PR`, người đóng góp mới và liên kết `Full Changelog`; bản nháp chỉ được tạo sau khi bản dựng Release, kiểm thử đơn vị và Lint đều đạt.- Việc phát hành vẫn sử dụng một APK chung, không chia gói theo ABI. Workflow bản nháp chính thức chỉ xác minh bản dựng Release, không phát hành APK chưa ký; người bảo trì tải lên APK chính thức đã ký và tệp SHA-256 từ cùng một commit, bổ sung nghiệm thu trên thiết bị thật và xác minh tải xuống đầy đủ rồi mới phát hành bản chính thức. CI sử dụng Environment được bảo vệ nêu trên để ký; khóa không được đưa vào mã nguồn hoặc sản phẩm bản dựng. Release hiện có sẽ không bị ghi đè và bản chính thức không được tự động phát hành.
- Ảnh đầu trang phát hành được tải lên làm tệp đính kèm của Release tương ứng và nhúng ở đầu ghi chú phát hành, không commit vào kho mã nguồn. Khi di chuyển ảnh đầu trang cũ, trước tiên hãy kiểm tra tính toàn vẹn của tệp đính kèm, cập nhật liên kết ảnh trong ghi chú phát hành rồi mới xóa tệp khỏi kho lưu trữ; biểu tượng ứng dụng và ảnh chụp tính năng trong README không bị ảnh hưởng.
- Các thay đổi được commit trực tiếp trong quá khứ mà không có PR liên quan sẽ được bổ sung trung thực vào phần tóm tắt tiếng Trung; không bịa tên tác giả hoặc số PR. Chỉ duy trì ghi chú cập nhật đầy đủ trên GitHub Releases, không tạo CHANGELOG cục bộ mới.

</details>

## Mã nguồn mở và lời cảm ơn

[![Được vận hành mạnh mẽ bởi Động cơ Âm thanh Dòng chảy](https://music.20110208.xyz/cm/badges/powered-by.svg?v=3)](https://music.20110208.xyz/home/engine/)
Mã hiện có được phân phối theo [GPL-3.0-only](LICENSE); các thành phần hoạt ảnh lời bài hát Folia Monet và hiệu ứng chuyển sắc khi phát/tạm dừng được chuyển thể theo [AGPL-3.0-only](app/src/main/assets/legal/AGPL-3.0.txt); việc kết hợp tuân theo Mục 13 của cả hai giấy phép. Mỗi thành phần từ dự án thượng nguồn giữ nguyên bản quyền và giấy phép riêng; xem [Thông báo bên thứ ba](THIRD_PARTY_NOTICES.md) để biết đầy đủ.

Cảm ơn [CurrentMusic](https://github.com/backrooms-yrc/CurrentMusic), [Folia](https://github.com/chthollyphile/folia-major), [Miuix](https://github.com/compose-miuix-ui/miuix), [SukiSU-Ultra](https://github.com/SukiSU-Ultra/SukiSU-Ultra), [AndroidLiquidGlass](https://github.com/Kyant0/AndroidLiquidGlass), [LeiFetch](https://github.com/bileizhen/LeiFetch), [123PanX](https://github.com/bileizhen/123PanX), [XBlocker](https://github.com/bileizhen/XBlocker), [AMLL TTML DB](https://github.com/amll-dev/amll-ttml-db), [LXGW WenKai](https://github.com/lxgw/LxgwWenKai), cùng các dự án mã nguồn mở như AndroidX, Media3, Coil, Jaudiotagger và ZXing.

<details>
<summary>Lượt xem</summary>

<div align="center">

![Thống kê lượt truy cập](https://count.getloli.com/@bileizhen_CurrentMusicX?name=bileizhen_CurrentMusicX&theme=original-new&padding=7&offset=0&align=center&scale=1&pixelated=1&darkmode=auto)

</div>

</details>
