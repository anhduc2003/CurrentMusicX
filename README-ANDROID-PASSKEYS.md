# Khóa truy cập gốc trên Android: phương án điều chỉnh backend

Phương án này áp dụng cho lối đăng nhập gốc của CurrentMusicX. Ứng dụng khách sử dụng Android Credential Manager, **không mở trang web, không giả mạo Origin của trang HTTPS**. Trước khi triển khai các thay đổi dưới đây ở backend, tính năng đăng nhập bằng khóa truy cập gốc vẫn chưa dùng được trong môi trường production; đăng nhập bằng mật khẩu, mã xác minh email, câu hỏi bảo mật và ứng dụng xác thực không bị ảnh hưởng.

## 1. Khác biệt hiện tại và phạm vi ứng dụng khách lần này

Căn cứ đối chiếu: `b51e197b13a83cd418e915bd660aeb2a2ff07a34` của backend `backrooms-yrc/CurrentMusic`.

- [`backend/cm_passkeys.py`](https://github.com/backrooms-yrc/CurrentMusic/blob/b51e197b13a83cd418e915bd660aeb2a2ff07a34/backend/cm_passkeys.py)：`configured_origins()` chỉ cho phép HTTPS; `origin_check()` suy ra RP ID từ hostname URL; cả `begin()` và `finish()` đều phụ thuộc vào hàm này.
- [`backend/cm_server.py`](https://github.com/backrooms-yrc/CurrentMusic/blob/b51e197b13a83cd418e915bd660aeb2a2ff07a34/backend/cm_server.py)：các tuyến khóa truy cập đọc nguồn từ tiêu đề HTTP `Origin`, rồi chuyển cho `cm_passkeys` xử lý.
- Lần kiểm tra này, `/.well-known/assetlinks.json` trên trang chính trả về 404; gọi options mà không có Origin của trang web trả về 403 và yêu cầu sử dụng lối vào trang HTTPS.

Phần triển khai ở ứng dụng khách lần này: **đọc khóa truy cập hiện có để đăng nhập**. Không bao gồm việc tạo/thu hồi khóa truy cập gốc; thông tin xác thực hiện có phải thuộc cùng RP ID nêu dưới đây. Thông tin xác thực được tạo dưới tên miền CDN khác sẽ không tự động được chuyển.

## 2. Danh tính, tên miền và chứng chỉ cố định

| Hạng mục | Giá trị |
| --- | --- |
| Tên gói chính thức | `com.bileizhen.currentmusic` |
| API xác thực của tuyến tích hợp sẵn | `https://music.20110208.xyz/cm/` |
| RP ID | `music.20110208.xyz` |
| SHA-256 chữ ký chính thức | `FC:3E:63:07:9B:8E:69:AE:A7:AB:73:48:28:46:DA:09:39:D6:CD:12:DA:F0:AF:FF:AA:9A:2E:93:FB:FC:CF:35` |
| Android Origin | `android:apk-key-hash:_D5jB5uOaa6nq3NIKEbaCTnWzRLa8K__qpouk_v8zzU` |

Dấu vân tay lấy từ `signingReport` trong cấu hình ký bản phát hành Gradle cục bộ; đây là thông tin chứng chỉ công khai. Trước khi phát hành, phải kiểm tra lại bằng `apksigner verify --print-certs` trên APK phát hành thực tế; nếu chữ ký phân phối thực tế khác, cần cập nhật cả DAL lẫn danh sách cho phép nguồn ở backend.

Hậu tố Origin là **bản tóm tắt SHA-256 của byte DER chứng chỉ, được mã hóa Base64URL không đệm**, không phải Base64 của chuỗi thập lục phân. Ứng dụng khách đọc chữ ký APK hiện tại để tính giá trị này; Credential Manager cũng tạo `clientDataJSON.origin` bằng chữ ký thực tế.

Các yêu cầu khóa truy cập của trang chính/CDN tích hợp sẵn luôn được gửi đến trang chính; các yêu cầu nhạc khác vẫn chọn tuyến theo cách hiện tại. Máy chủ tùy chỉnh giữ nguyên địa chỉ HTTPS và hostname của máy chủ đó làm RP ID; bạn cần tự cấu hình liên kết và ánh xạ nguồn ở backend. Máy chủ tùy chỉnh dùng HTTP thông thường không hỗ trợ lối vào này.

## 3. Phát hành Digital Asset Links

Phát hành nội dung sau tại `https://music.20110208.xyz/.well-known/assetlinks.json` ở **đường dẫn gốc của tên miền**, không đặt dưới `/cm/`：

```json
[
  {
    "relation": ["delegate_permission/common.get_login_creds"],
    "target": {
      "namespace": "android_app",
      "package_name": "com.bileizhen.currentmusic",
      "sha256_cert_fingerprints": [
        "FC:3E:63:07:9B:8E:69:AE:A7:AB:73:48:28:46:DA:09:39:D6:CD:12:DA:F0:AF:FF:AA:9A:2E:93:FB:FC:CF:35"
      ]
    }
  }
]
```

Yêu cầu chứng chỉ HTTPS đáng tin cậy, trả về trực tiếp mã 200, có `Content-Type: application/json`, không cần đăng nhập và không chuyển hướng qua CAPTCHA/đăng nhập. Không thêm chữ ký debug dùng chung vào tên miền chính thức; gói kiểm thử riêng `com.bileizhen.currentmusic.verification` nên dùng tên miền kiểm thử và liên kết riêng.

Tham khảo: [Yêu cầu cần đáp ứng trước khi sử dụng Android Credential Manager](https://developer.android.com/identity/credential-manager/prerequisites)。

## 4. Vị trí thay đổi backend

### `backend/cm_passkeys.py`

Giữ nguyên cấu hình nguồn trang web hiện có, đồng thời thêm **ánh xạ chính xác riêng từ Android Origin → RP ID**. Ví dụ, cấu hình `CM_PASSKEY_ANDROID_ORIGINS` dưới dạng từ điển JSON; không trộn chuỗi Android vào `CM_PASSKEY_ORIGINS`, vốn chỉ cho phép HTTPS.

```python
# Ví dụ: thực tế cần đọc từ cấu hình phía máy chủ đã được kiểm tra nghiêm ngặt.
ANDROID_ORIGINS = {
    "android:apk-key-hash:_D5jB5uOaa6nq3NIKEbaCTnWzRLa8K__qpouk_v8zzU":
        "music.20110208.xyz",
}

def origin_check(origin):
    if origin in ANDROID_ORIGINS:
        return ANDROID_ORIGINS[origin]
    if origin not in ORIGINS:
        raise Error("Ứng dụng hoặc trang web này chưa được phép sử dụng khóa truy cập", 403)
    return urlparse(origin).hostname
```

Đây là hướng thích ứng; không thể dùng `startswith('android:')` để mở quyền cho tất cả ứng dụng, cũng không thể nhận RP ID tùy ý từ phần thân yêu cầu. Khi tải cấu hình, hãy xác minh hàm băm là Base64URL không đệm của bản tóm tắt 32 byte, RP ID là tên miền được cho phép rõ ràng và không chấp nhận ký tự đại diện. Nếu chưa cấu hình ánh xạ Android thì tiếp tục từ chối.

`begin()` trả về `publicKey.rpId` chính xác theo ánh xạ. `finish()` phải đồng thời xác minh:

1. Nguồn của yêu cầu hiện tại thuộc danh sách cho phép và khớp với nguồn đã lưu khi tạo thử thách.
2. `clientDataJSON.origin` khớp chính xác với Android Origin được liên kết với thử thách; dùng `expected_origin` của thư viện WebAuthn để xác minh.
3. `expected_challenge`, `expected_rp_id`, chữ ký, credential ID, user handle, trạng thái tài khoản và bộ đếm chữ ký tuân theo các quy tắc hiện có.
4. Giữ nguyên `require_user_verification=True`, không hạ cấp xuống chỉ xác nhận bằng thao tác chạm.
5. Giữ nguyên thời hạn hiệu lực 5 phút, cơ chế chỉ sử dụng một lần, xử lý giao dịch và liên kết tài khoản／phiên trong quá trình đăng ký.

HTTP `Origin` chỉ dùng để chọn và liên kết quy trình, **không phải thông tin xác thực danh tính**; kết luận về độ tin cậy đến từ việc xác minh đầy đủ dữ liệu liên kết và chữ ký. Tiếp tục lưu Android Origin thực tế vào cột `origin` của thử thách; không cần chuyển đổi nó thành nguồn trang web. Nên lưu thêm RP ID tại thời điểm tạo thử thách; nếu dùng ánh xạ động, hãy xóa các thử thách cũ khi thay đổi cấu hình, không để cùng một thử thách chuyển đổi giữa RP cũ và mới.

Ánh xạ nêu trên cũng áp dụng cho các hàm đăng ký và xác minh hiện có ở backend; lần này client chỉ gọi API đăng nhập. Không cần sửa định dạng token đăng nhập tài khoản hoặc thêm 2FA／câu hỏi bảo mật sau khi đăng nhập bằng passkey đã hoàn tất xác minh người dùng.

### `backend/cm_server.py` và proxy ngược

Tiếp tục dùng `/auth/passkeys/login/options` và `/auth/passkeys/login/verify` hiện có. Xác nhận proxy ngược chuyển tiếp `Origin: android:apk-key-hash:…`, không thay thế nó bằng HTTPS Origin của trang chính và không từ chối yêu cầu gốc theo danh sách cho phép CORS của trang web. Chỉ cho phép chính xác các nguồn nêu trên, không nới lỏng chính sách truy cập chéo nguồn của trang web.

Nếu cần trả về mã lỗi ổn định mới thống nhất, có thể thêm `errorCode: "native_passkey_not_configured"`, nhưng vẫn phải giữ lại trường `error` dễ đọc. Client lần này tương thích với văn bản 403 của backend hiện tại; sau khi nâng cấp mã lỗi, cần đồng bộ bổ sung ánh xạ ở client.

## 5. Hợp đồng yêu cầu thống nhất với client

### Lấy tùy chọn xác thực

```http
POST /cm/auth/passkeys/login/options
Content-Type: application/json
Origin: android:apk-key-hash:_D5jB5uOaa6nq3NIKEbaCTnWzRLa8K__qpouk_v8zzU

{"platform":"app"}
```

Giữ nguyên cấu trúc hiện có của phản hồi, có thể trả thêm `expiresIn`:

```json
{
  "challengeToken": "<thông tin xác thực thử thách dùng một lần, không trong suốt>",
  "expiresIn": 300,
  "publicKey": {
    "challenge": "<thử thách ngẫu nhiên Base64URL>",
    "rpId": "music.20110208.xyz",
    "userVerification": "required",
    "allowCredentials": []
  }
}
```

Client chỉ gọi Credential Manager của hệ thống khi RP ID khớp với tên miền xác thực cố định và yêu cầu xác minh người dùng. Nếu không trả về `expiresIn`, hãy dùng 300 giây theo giao thức hiện có; giá trị trả về tối đa là 300 giây.

### Gửi thông tin xác thực

```http
POST /cm/auth/passkeys/login/verify
Content-Type: application/json
Origin: android:apk-key-hash:_D5jB5uOaa6nq3NIKEbaCTnWzRLa8K__qpouk_v8zzU

{
  "challengeToken": "<giá trị trả về ở bước trước>",
  "credential": {
    "id": "<credential ID>",
    "rawId": "<Base64URL credential ID>",
    "type": "public-key",
    "response": {
      "clientDataJSON": "<Base64URL clientDataJSON>",
      "authenticatorData": "<Base64URL authenticatorData>",
      "signature": "<Base64URL signature>",
      "userHandle": "<Base64URL userHandle>"
    }
  },
  "device": "<mô tả thiết bị hiện có>",
  "platform": "app"
}
```

Sau khi xác minh chữ ký thành công, vẫn trả về `{ "token": "…", "user": { "id": 123, ... } }`. Cả hai API đều là yêu cầu đăng nhập ẩn danh, không gửi Bearer token của tài khoản hiện có; client chỉ lưu phiên mới sau khi phản hồi xác minh chữ ký hợp lệ. Không được lưu phiên khi người dùng hủy, hết thời gian chờ, chuyển đổi tài khoản／máy chủ hoặc xác thực thất bại.

Tham khảo: [Đăng nhập bằng mật khẩu khóa gốc Android](https://developer.android.com/identity/passkeys/sign-in-with-passkeys).

## 6. Kiểm thử tích hợp và nghiệm thu trước khi phát hành

Trước tiên, bổ sung kiểm thử cho backend, sau đó triển khai môi trường kiểm thử:

- Origin Android trong danh sách cho phép chính thức có thể tạo thử thách; mọi chữ ký khác, mọi tiền tố Android khác và trường hợp thiếu Origin đều thất bại.
- Xác minh chữ ký Android hợp lệ thành công; Origin HTTP giả mạo nhưng `clientDataJSON.origin` không khớp, RP ID sai, challenge sai, không có UV, chữ ký sai và handle người dùng khác đều thất bại.
- Thử thách hết hạn, gửi lặp lại, dùng thay thế thử thách web và Android, thông tin xác thực／tài khoản khác, tài khoản bị vô hiệu hóa và bộ đếm bị giảm đều bị từ chối theo quy tắc hiện hành.
- Việc liên kết／đăng nhập từ bốn nguồn HTTPS trên web hiện có vẫn hoạt động bình thường; hỗ trợ nguồn mới không mở rộng phạm vi cho phép trên web.
- Proxy ngược giữ nguyên Android Origin; DAL trả về trực tiếp với loại nội dung chính xác và mã 200.

Sau đó, dùng **APK ký chính thức** và nhà cung cấp thông tin xác thực trên thiết bị đáng tin cậy để nghiệm thu đầu cuối:

1. Có sẵn khóa truy cập có thể phát hiện dưới RP của trang chính; hệ thống có thể hiển thị và hoàn tất đăng nhập.
2. Trang chính và từng CDN tích hợp đều sử dụng cùng một RP khi được chọn; máy chủ tùy chỉnh không dùng lẫn tài khoản của trang chính.
3. Hủy cửa sổ bật lên của hệ thống, không có thông tin xác thực, thiết bị không hỗ trợ, mất kết nối mạng, khôi phục ứng dụng chạy nền hoặc chuyển đổi tài khoản không được khiến người dùng vô tình đăng nhập hay làm lộ thông tin xác thực.
4. Đăng nhập bằng mật khẩu, mã xác minh email và bước thứ hai bằng câu hỏi bảo mật／ứng dụng xác thực vẫn hoạt động.

Kiểm thử phía máy khách mô phỏng máy chủ có thể xác minh giao thức, việc cách ly trạng thái và xử lý thao tác hủy, nhưng không thể thay thế nghiệm thu thực tế đối với Credential Manager, DAL và việc xác minh chữ ký phía backend nêu trên. Chỉ thêm lối vào không có nghĩa là tính năng đăng nhập bằng khóa truy cập gốc trên môi trường trực tuyến đã khả dụng.
