# Bộ điều hợp đầu ra USB độc quyền nguyên bản của Nhạc Vân NetEase

## Nguồn và phạm vi

Được trích xuất trực tiếp từ bản 9.6.15 / 9006015 của `com.netease.cloudmusic` trên thiết bị PLR110 của người dùng, arm64-v8a.
SHA-256 của điện thoại và tệp base.apk cục bộ đều là
`e2b52565f3ca037277ba9850d61750d28102c03abba02219b9839f0136ed06c9`。
APK gốc được giữ trong thư mục phân tích tạm thời, không được đưa vào dự án. Ứng dụng chỉ tích hợp
`libncmusbaudio.so` chưa chỉnh sửa, có SHA-256 là
`150daa62df3b2f5a9b9bb5d54a3108e90495e50853c4b5ac7df3f63137f70a7a`。
Trình điều khiển gốc thuộc sở hữu của NetEase và tác giả các thành phần; việc điều chỉnh không có nghĩa là NetEase cấp phép hoặc xác nhận.

Hash của `libc++_shared.so` trong cùng gói trùng khớp với thư viện thời gian chạy hiệu ứng âm thanh đã tích hợp trong dự án:
`3e6d529147ffd66bd31fab85c8902b526abd1c06e43554ce64b97519e255e4b3`。
Bộ điều hợp JNI mới được liên kết riêng với thư viện thời gian chạy dùng chung này; backend Oboe gốc tiếp tục dùng thư viện thời gian chạy tĩnh ban đầu.
Trước khi khởi chạy, xác minh bản tóm tắt của trình điều khiển và tất cả ký hiệu xuất bắt buộc; không thể tương thích với các thư viện thay thế tùy ý thuộc phiên bản khác.

Đây là đường dẫn USB Host độc lập, không gọi Android MixerAttributes, không tải toàn bộ trình phát của Nhạc Vân NetEase,
FFmpeg, mô-đun tài khoản hoặc giao diện mạng; không né tránh xác minh chữ ký, đăng nhập, cấp phép trả phí hoặc quyền truy cập thiết bị.
Chế độ này hoạt động song song với chế độ Android Bit-perfect nghiêm ngặt hiện có.

## Bằng chứng phân tích tĩnh

Trong gói tham chiếu 32 bit 9.6.15 ban đầu, `im0.b.l(UsbDevice)` tạm dừng trình phát trước, sau đó gọi
`UsbManager.openDevice()`, chuyển `getFileDescriptor()` cho
`NCMNativeAudioPlayer._useUSBDevice(int)` và thiết lập loại thiết bị.
Bản triển khai cuối cùng sử dụng thư viện native arm64 thực tế được cài trên điện thoại và kiểm tra lại giao diện của thư viện:

| Hàm arm64 | Địa chỉ | Quan sát và điều chỉnh |
| --- | --- | --- |
| `DeviceManager::CreateDevice(int)` | `0x338ac` | Bọc thiết bị bằng FD đã được cấp quyền; trả về `shared_ptr<AudioDevice>` không tầm thường, giữ lại hàm hủy ban đầu |
| `AudioDeviceImpl::IsSupportAudioFormat` | `0x22be8` | Bỏ qua ứng viên có độ sâu bit 24; truy vấn số nguyên 16/32 bằng enum gốc 1/2 |
| `AudioDeviceImpl::SelectAudioFormat` | `0x23314` | claim giao diện, chọn alternate setting, thực hiện điều khiển tần số lấy mẫu UAC; ring tối thiểu 512 KiB |
| `AudioDeviceImpl::Push` | `0x28428` | Trả về boolean; ring tràn vẫn có thể trả về thành công, vì vậy lớp điều hợp cần thực hiện kiểm soát áp lực ngược |
| `AudioDeviceImpl::Start` | `0x28a50` | Xóa ring và bật hiệu ứng tăng âm lượng khi khởi động của trình điều khiển gốc; không thể xem đây là đầu ra nghiêm ngặt từng bit |
| `AudioDeviceImpl::GetXferStatus` | `0x27518` | Truy vấn lỗi truyền; dừng khi thất bại |
| `AudioDeviceImpl::GetLeftDataTime` | `0x2898c` | Ước tính thời lượng hàng đợi, độ chính xác mili giây; không phải dấu thời gian phần cứng DAC |

ABI của `AudioFormat` dài 12 byte: channel/bit mỗi trường một byte, hai byte dự phòng, tần số lấy mẫu 32 bit,
định dạng lấy mẫu 32 bit. Trên arm64, dữ liệu được truyền qua hai thanh ghi đa dụng; mã có các khẳng định tĩnh về kích thước và độ lệch trường.
Thích ứng thông qua các hàm được xuất, không sửa đổi nhị phân, không sử dụng độ lệch trường riêng tư của đối tượng.

## Quy tắc phát và định dạng

1. Chọn thiết bị USB Host trong phần cài đặt; dùng receiver tường minh, không thể xuất để yêu cầu hệ thống cấp quyền USB.
2. Khởi tạo engine gốc bằng FD của thiết bị đã được cấp quyền, dup riêng FD và giữ Java connection trong toàn bộ vòng đời.
3. Truy vấn định dạng, bắt buộc tìm được định dạng cùng tần số lấy mẫu và số kênh mà engine gốc báo hỗ trợ; dừng nếu thương lượng thất bại.
4. Gọi Start rồi Pause để khởi tạo hàng đợi trống; sau khi ghi, chỉ gọi Resume để phát, tránh Start làm mất dữ liệu đệm trước.
5. Một trình sản xuất gốc, tối đa 8192 byte mỗi lần; không tiêu thụ đầu vào khi hàng đợi đạt 100 ms. Bộ thích ứng giới hạn ở
   8–192 kHz, 1–2 kênh, số nguyên tuyến tính 16/32 bit; giới hạn trên của hàng đợi thấp hơn dung lượng tối thiểu mà engine gốc đảm bảo.
6. Chuyển đổi rõ ràng, không mất dữ liệu từ packed 24-bit sang signed 32-bit bằng cách căn trái: `b0 b1 b2` → `00 b0 b1 b2`,
   không qua float, không lấy mẫu lại. Thiết bị phải cung cấp cấu hình cùng tần số lấy mẫu, cùng số kênh, khe con 4 byte và ít nhất 24 bit dữ liệu hợp lệ.
   Chấp nhận cả 24 valid bits / 4 bytes và 32 valid bits / 4 bytes, không bắt buộc phải có 32 bit hợp lệ.
   Chẩn đoán ghi riêng định dạng giải mã, định dạng yêu cầu và phép chuyển đổi; tuyệt đối không coi hai kiểu vùng chứa là cùng một định dạng.
7. Tắt hiệu ứng âm thanh ứng dụng, thay đổi tốc độ, bỏ qua đoạn im lặng và chuyển tiếp. Trước khi ghi USB PCM, áp dụng kết hợp âm lượng phương tiện hệ thống và âm lượng trình phát,
   có thể điều chỉnh bằng phím âm lượng điện thoại và xuất âm thanh im lặng; đọc âm lượng phương tiện ngay lần ghi đầu tiên, sau đó cập nhật mỗi 50 ms trong khi phát.
   Trên Android 9 trở lên, ưu tiên dùng đường cong âm lượng tai nghe USB của hệ thống; nếu không khả dụng, dùng đường cong suy giảm bậc ba.
   Nếu truy vấn thất bại, xuất âm thanh im lặng, không trở về mức âm lượng tối đa; việc tạm dừng do mất quyền ưu tiên âm thanh vẫn do dịch vụ phát quản lý.
   Suy giảm số nguyên 16/24/32-bit trong bộ đệm riêng, không sửa đổi PCM nguồn đang chờ thử lại; giữ nguyên quy tắc mở rộng 24-bit.
   Không ghi lại âm thanh đã được gửi đến driver; độ trễ phản hồi âm lượng bao gồm hàng đợi hiện có khoảng 100 ms và độ trễ của thiết bị.
   Quá trình tăng âm lượng, điều chỉnh âm lượng và xử lý âm cuối của driver gốc vẫn còn; chế độ này **không tuyên bố Bit-perfect**.
   Chế độ Bit-perfect nghiêm ngặt của Android tiếp tục tạm dừng khi trình phát tắt tiếng hoặc suy giảm âm lượng, không áp dụng âm lượng phần mềm.
8. Tiến độ sử dụng hàng đợi của trình điều khiển gốc và thời lượng đang truyền để ước tính. Khi hàng đợi bằng 0, chỉ báo đã xả hết sau khi ổn định thêm 20 ms,
   tránh việc làm tròn mili giây khiến gói USB cuối chưa đủ một mili giây bị đóng quá sớm.
9. Dừng và giải phóng khi rút thiết bị, bị thu hồi quyền, ghi thất bại hoặc dữ liệu chờ xuất liên tục mà không tiến triển.
   Khi đổi bài làm thay đổi định dạng hoặc khi flush, hủy rồi tạo lại; không dùng AudioTrack làm phương án dự phòng.

Định dạng do thư viện gốc báo cáo và yêu cầu điều khiển thành công không đồng nghĩa với việc đã xác minh đồng hồ DAC thực tế hoặc truyền tải từng bit.
Không quy lỗi cho việc phần cứng không hỗ trợ; lỗi có thể xuất phát từ phiên bản trình điều khiển gốc hiện tại hoặc cấu hình thiết bị.

## Sửa lỗi chọn định dạng 192 kHz / 24-bit

Nhật ký người dùng cho thấy ủy quyền USB thành công, bộ giải mã thực tế xuất 192000 Hz / packed24, nhưng kiểm tra định dạng nhanh từ chối cấu hình 32-bit được yêu cầu.
Đối chiếu thêm `FormatType`, `GetStreamingInfo` và trình phát đầy đủ của Nhạc Vân NetEase:

- `GetStreamingInfo` trả về riêng `validBits` và `subslotBytes`; `IsSupportAudioFormat` kiểm tra nhanh bỏ qua vô điều kiện số bit hợp lệ 24.
- Trình phát đầy đủ của Nhạc Vân NetEase gọi `GetSuggestAudioFormat` sau khi kiểm tra nhanh thất bại; đề xuất này có thể thay đổi độ sâu bit, số kênh hoặc tần số lấy mẫu.
  Vì vậy, việc “Nhạc Vân NetEase phát được” tự nó không chứng minh ứng dụng đang dùng tần số lấy mẫu và độ chính xác của nguồn âm thanh hiện tại.
- Bộ điều hợp hiện tại gọi các hàm công khai `getStreamingInterface`, `IsOutput`,
  `GetInfos` theo thứ tự giao diện gốc trong cấu hình active của libusb, tái hiện thứ tự chỉ số ứng viên của engine. Dùng ABI giá trị trả về công khai đã được đối chiếu, không đọc bộ nhớ riêng của đối tượng thiết bị.
- Xác minh độc lập PCM số nguyên, tần số lấy mẫu là thành viên trong danh sách thực tế, số kênh, kích thước vùng chứa và số bit hợp lệ. Nguồn âm thanh 32-bit không được phép chọn 24 bit hợp lệ;
  còn packed24 có thể dùng vùng chứa 4 byte với 24 bit hợp lệ. Chỉ sau khi từng mục đều đạt mới giao cho engine gốc thương lượng ứng viên được chỉ định.
- Mọi ứng viên gốc đều được ghi vào `CurrentMusicAudio` bằng văn bản chẩn đoán JNI, bao gồm giao diện, alternate, số bit hợp lệ, subslot, loại PCM và tần số lấy mẫu.
  Chỉ ghi nhận đề xuất hạ cấp của trình điều khiển gốc dưới dạng `applied=false`, không tự động áp dụng.

Nhật ký hiện tại không có danh sách ứng viên nên chưa thể xác nhận thiết bị của người dùng có hỗ trợ 24 valid bits / 4 bytes hay không; chẩn đoán mới sẽ giúp xác định thêm.

Bản sửa này đã vượt qua: 1001 bài kiểm thử JVM, 14 bài kiểm thử thiết bị cô lập RMX5060 (bao gồm các trường hợp vùng chứa,
số bit hợp lệ, tần số lấy mẫu, số kênh và loại PCM của hàm chọn gốc dùng trong bản phát hành), quá trình dựng Debug APK / AndroidTest APK và Lint.
Các kiểm tra này không bao gồm việc đọc cấu hình, thương lượng hoặc phát trên DAC thực, nên không thể dựa vào đó để tuyên bố thiết bị của người dùng đã được khắc phục.

## Phạm vi xác minh

### Sửa lỗi nhận định nhầm USB_TRANSFER sau khi khởi động

Phiên bản trước xác định lỗi trong `stream()` bằng `GetXferStatus()!=0`, điều này không phù hợp với enum trạng thái của engine gốc:

| Giá trị | Ý nghĩa gốc | Bằng chứng |
| --- | --- | --- |
| 0 | Nhàn rỗi／luồng đã dừng | Hàm tạo và stopStreamingThread đặt về 0 |
| 1 | Luồng đang chạy | startStreamingThread ghi rõ giá trị 1 tại `0x2661c` |
| 2 | Tất cả truyền dữ liệu đều hết thời gian chờ | handleTransferCompleteCallback ghi giá trị 2 tại `0x27a10` |
| 3 | Lỗi truyền dữ liệu | Cùng hàm đó ghi giá trị 3 tại `0x27a3c` |

Trình phát Nhạc Vân NetEase hoàn chỉnh cũng chỉ gửi thông báo hết thời gian chờ/lỗi truyền dữ liệu khi trạng thái là 2/3. Bộ điều hợp hiện tại giữ nguyên 0/1 là trạng thái bình thường,
còn 2/3, mất thiết bị và trạng thái không xác định thì vẫn dừng và giải phóng. Bổ sung kiểm tra hồi quy bằng cách gọi trực tiếp hàm xác định trạng thái gốc được dùng trong sản phẩm.
Đây không phải enum trạng thái của một transfer riêng lẻ trong libusb, cũng không phải giá trị trả về báo thành công của Start/Resume; không thể dùng lẫn hai loại này.

Nhật ký mới bao gồm `adapter=ncm-usb-v3-status-init`, `transfer stage=... raw=... state=... detached=...`;
hàng đợi chẩn đoán được lấy ra từng mục, bao quát khởi chạy, tiếp tục sau khi tạm dừng, ghi dữ liệu và kiểm tra tiến độ; không còn dùng USB_TRANSFER chung chung để che giấu giá trị trạng thái.
Nhật ký mới nhất của người dùng không có thông tin precision / adapter / supported; cần kiểm tra APK đã cài đặt, không thể chỉ dựa vào dấu thời gian để kết luận đó là phiên bản mới nhất.
Xác minh bản sửa trạng thái v3: 1001 bài kiểm tra JVM, 16 bài kiểm tra thiết bị cách ly RMX5060 và Lint đều đạt.
Trong đó có các kiểm tra hồi quy mới cho trạng thái đang chạy 1, hết thời gian chờ 2, lỗi 3, ngắt kết nối và trạng thái không xác định, cùng phiên bản chẩn đoán và việc tiêu thụ hàng đợi.
Chưa xác minh phát nhạc bằng DAC thực tế.

Kiểm thử JVM dùng driver có thể tiêm và đối tượng kết nối thay thế để xác minh định dạng, điều tiết ngược, bảo toàn từng byte, lỗi cấp quyền,
ngắt kết nối, lỗi gốc, tiếp tục sau khi tạm dừng, dựng lại và giải phóng tài nguyên. Kiểm thử thiết bị chỉ xác minh việc tải thư viện thực tế,
giao diện xuất và không có lỗi khi không kết nối DAC; không yêu cầu quyền USB, không phát nhạc, không đọc tài khoản hoặc hàng đợi phát.
Thiết bị thử nghiệm không có TANCHJIM BUNNY DSP / NICEHCK NK1 MAX; vẫn cần người dùng thiết bị xác minh: cấp quyền,
phát lần đầu, đầu ra mở rộng 192 kHz/24-bit, tiếp tục sau khi tạm dừng, chuyển bài, phần âm cuối và rút nóng.

Xác minh lần này: đã xây dựng thành công APK Debug hoàn chỉnh / APK AndroidTest, 1001 bài kiểm tra JVM đạt;
Lint có 0 lỗi, 115 cảnh báo, 7 gợi ý. Trong gói cách ly trên RMX5060 (API 36, trang bộ nhớ 4 KiB),
4 bài kiểm tra driver USB và 9 bài kiểm tra Bit-perfect hiện có, tổng cộng 13 bài, đều đạt.
Trong đó đã thực hiện kiểm tra tổng kiểm tra của thư viện gốc, tải thư viện và giao diện xuất; không kết nối DAC, không phát qua USB.
LOAD alignment của ELF trong thư viện gốc là 4 KiB; vẫn cần xác minh khả năng tương thích với hệ thống bộ nhớ trang 16 KiB,
không thể giả vờ tương thích bằng cách thay đổi ELF marker hoặc bỏ qua lỗi tải.
Xem thêm bản ghi bàn giao lần này để biết gói cài đặt Release và kết quả ký. Kiểm thử mô phỏng không được xem là đã vượt qua kiểm thử phần cứng.
