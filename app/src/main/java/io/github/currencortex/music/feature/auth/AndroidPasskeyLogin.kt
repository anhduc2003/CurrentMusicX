package io.github.currencortex.music.feature.auth

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Base64
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.*
import io.github.currencortex.music.core.network.ApiException
import io.github.currencortex.music.core.network.ErrorKind
import kotlinx.coroutines.CancellationException
import java.security.MessageDigest

internal object AndroidPasskeyLogin {
    fun origin(context: Context): String {
        if (Build.VERSION.SDK_INT < 28) throw ApiException(ErrorKind.Forbidden,
            userMessage = "Khóa truy cập cần Android 9 trở lên")
        @Suppress("DEPRECATION")
        val signatures = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            .signingInfo?.apkContentsSigners.orEmpty()
        require(signatures.size == 1)
        val hash = MessageDigest.getInstance("SHA-256").digest(signatures.single().toByteArray())
        return "android:apk-key-hash:" + Base64.encodeToString(hash, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }
    suspend fun credential(context: Context, requestJson: String): String {
        try {
            // Origin is assigned by Android from the actual APK signature. Never impersonate a web origin.
            val request = GetCredentialRequest(listOf(GetPublicKeyCredentialOption(requestJson)))
            val result = CredentialManager.create(context).getCredential(context, request).credential
            return (result as? PublicKeyCredential)?.authenticationResponseJson
                ?: throw ApiException(ErrorKind.Parse, userMessage = "Hệ thống không trả về khóa truy cập, vui lòng thử lại")
        } catch (e: GetCredentialCancellationException) {
            throw CancellationException("Passkey selection cancelled")
        } catch (e: NoCredentialException) {
            throw ApiException(ErrorKind.NotFound, userMessage = "Không tìm thấy khóa truy cập khả dụng, vui lòng kiểm tra trình quản lý thông tin xác thực hoặc dùng cách đăng nhập khác")
        } catch (e: GetCredentialProviderConfigurationException) {
            throw ApiException(ErrorKind.Forbidden, userMessage = "Thiết bị này chưa cấu hình trình quản lý thông tin xác thực khả dụng, vui lòng dùng cách đăng nhập khác")
        } catch (e: GetCredentialUnsupportedException) {
            throw ApiException(ErrorKind.Forbidden, userMessage = "Thiết bị này hiện chưa hỗ trợ khóa truy cập, vui lòng dùng cách đăng nhập khác")
        } catch (e: GetCredentialException) {
            throw ApiException(ErrorKind.Unknown, userMessage = "Không thể dùng khóa truy cập, vui lòng xác nhận máy chủ đã hoàn tất liên kết ứng dụng hoặc dùng cách đăng nhập khác")
        }
    }
}
