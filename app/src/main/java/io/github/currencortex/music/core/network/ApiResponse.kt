package io.github.currencortex.music.core.network

import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import java.io.IOException
import java.net.SocketTimeoutException

enum class ErrorKind(val message: String) {
    Network("Kết nối mạng thất bại"), Timeout("Yêu cầu quá thời gian, vui lòng thử lại"), Unauthorized("Phiên đăng nhập CurrentMusic đã hết hạn, vui lòng đăng nhập lại"),
    Forbidden("Không có quyền truy cập"), NotFound("Nội dung không tồn tại"), RateLimited("Yêu cầu quá thường xuyên"),
    Server("Máy chủ gặp sự cố"), Parse("Định dạng dữ liệu bất thường"), Unknown("Thao tác thất bại, vui lòng thử lại"),
    AudioKeyRequired("Vui lòng nhập LeiZ API Key trong phần cài đặt mạng và phát nhạc"),
    AudioKeyRejected("LeiZ API Key không hợp lệ hoặc không có quyền truy cập, vui lòng kiểm tra cài đặt nguồn nhạc"),
    AudioUnavailable("LeiZ không cung cấp nguồn phát cho bài hát này, có thể bị giới hạn quyền VIP"),
    AudioSourceChanged("Cài đặt nguồn nhạc đã thay đổi, vui lòng phát lại"),
    CurrentMusicAccountRequired("Vui lòng đăng ký và đăng nhập tài khoản CurrentMusic trước, sau đó đăng nhập Nhạc Vân NetEase"),
    NeteaseBindingRequired("Vui lòng liên kết tài khoản Nhạc Vân NetEase trước; nếu liên kết hết hiệu lực, hãy đăng nhập lại Nhạc Vân NetEase"),
    NeteaseAudioUnavailable("Nhạc Vân NetEase không cung cấp nguồn phát được, vui lòng kiểm tra quyền tài khoản hoặc khu vực khả dụng của bài hát"),
    NeteaseSessionChanged("Tài khoản Nhạc Vân NetEase đã chuyển đổi, vui lòng thử lại"),
    NeteaseLikedPlaylistUnavailable("Không thể đọc danh sách phát “Nhạc tôi thích” của Nhạc Vân NetEase, vui lòng kiểm tra liên kết Nhạc Vân NetEase"),
    NeteaseHeartNoRecommendations("Hiện chưa có đề xuất bài hát tâm đầu ý hợp, vui lòng đổi bài hát rồi thử lại"),
    NeteaseSongAlreadyInPlaylist("Bài hát này đã tồn tại"),
    NeteaseRateLimited("Thao tác trên Nhạc Vân NetEase quá thường xuyên, vui lòng thử lại sau")
}
class ApiException(val kind: ErrorKind, val status: Int = 0, val userMessage: String? = null) : IOException(userMessage ?: kind.message)
sealed interface AppResult<out T> {
    data class Success<T>(val value: T) : AppResult<T>
    data class Failure(val kind: ErrorKind, val status: Int = 0, val message: String = kind.message) : AppResult<Nothing>
}
suspend fun <T> appResult(block: suspend () -> T): AppResult<T> = try {
    AppResult.Success(block())
} catch (e: CancellationException) { throw e
} catch (e: Exception) {
    val kind = when (e) {
        is ApiException -> e.kind
        is SocketTimeoutException -> ErrorKind.Timeout
        is SerializationException -> ErrorKind.Parse
        is IOException -> ErrorKind.Network
        else -> ErrorKind.Unknown
    }
    AppResult.Failure(kind, status = (e as? ApiException)?.status ?: 0,
        message = (e as? ApiException)?.userMessage ?: kind.message)
}
