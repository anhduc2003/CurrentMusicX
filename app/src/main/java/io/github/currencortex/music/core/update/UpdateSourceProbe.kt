package io.github.currencortex.music.core.update

import java.io.IOException
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.job
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

enum class UpdateSourceStatus { CHECKING, AVAILABLE, LIMITED, TIMEOUT, UNAVAILABLE, UNKNOWN }

data class UpdateSourceHealth(
    val status: UpdateSourceStatus,
    val latencyMs: Long? = null,
    val detail: String = "",
)

fun interface UpdateSourceCheck {
    suspend fun check(release: AppRelease, source: UpdateSource): UpdateSourceHealth
}

/** Checks the actual APK response, reading only its four-byte ZIP signature. No full-file verification. */
class UpdateSourceProbe(
    client: OkHttpClient = OkHttpClient(),
    private val urlFor: (AppRelease, UpdateSource) -> String = { release, source -> source.url(release) },
    private val nanoTime: () -> Long = System::nanoTime,
) : UpdateSourceCheck {
    private val http = client.newBuilder().followRedirects(false).followSslRedirects(false)
        .connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(8, TimeUnit.SECONDS).retryOnConnectionFailure(false).build()
    private val privateMirrorHttp = http.newBuilder().retryOnConnectionFailure(true)
        .dns(object : okhttp3.Dns {
            override fun lookup(hostname: String) = http.dns.lookup(hostname).let { addresses ->
                if (hostname == "music.20110208.xyz") addresses.sortedBy { if (it is java.net.Inet4Address) 0 else 1 }
                else addresses
            }
        }).build()

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun check(release: AppRelease, source: UpdateSource): UpdateSourceHealth = withContext(Dispatchers.IO) {
        val started = nanoTime()
        fun result(status: UpdateSourceStatus, detail: String = "") = UpdateSourceHealth(
            status, TimeUnit.NANOSECONDS.toMillis(nanoTime() - started).coerceAtLeast(1), detail)
        try {
            var url = urlFor(release, source).toHttpUrl()
            repeat(9) { redirect ->
                check(url.scheme == "https" && url.username.isEmpty() && url.password.isEmpty()) { "Nguồn tải xuống phải sử dụng HTTPS" }
                val remaining = TimeUnit.SECONDS.toNanos(8) - (nanoTime() - started)
                if (remaining <= 0) return@withContext result(UpdateSourceStatus.TIMEOUT, "Hết thời gian kiểm tra")
                val call = (if (source == UpdateSource.CURRENTMUSIC) privateMirrorHttp else http)
                    .newCall(Request.Builder().url(url).header("Range", "bytes=0-3")
                    .header("Accept-Encoding", "identity")
                    .header("Accept", "application/vnd.android.package-archive, application/octet-stream")
                    .header("User-Agent", "CurrentMusic updater").build())
                call.timeout().timeout(remaining, TimeUnit.NANOSECONDS)
                val cancel = currentCoroutineContext().job.invokeOnCompletion(onCancelling = true, invokeImmediately = true) {
                    if (it != null) call.cancel()
                }
                try {
                    call.execute().use { response ->
                        if (response.code in listOf(301, 302, 303, 307, 308)) {
                            check(redirect < 8) { "Quá nhiều lần chuyển hướng" }
                            url = response.header("Location")?.let(url::resolve) ?: error("Địa chỉ chuyển hướng không hợp lệ")
                        } else {
                            if (response.code == 429) return@withContext result(UpdateSourceStatus.LIMITED, "HTTP 429")
                            if (!response.isSuccessful) return@withContext result(UpdateSourceStatus.UNAVAILABLE, "HTTP ${response.code}")
                            val body = response.body ?: return@withContext result(UpdateSourceStatus.UNAVAILABLE, "Phản hồi trống")
                            val type = body.contentType()?.let { "${it.type}/${it.subtype}" }
                            check(type !in setOf("text/html", "application/xhtml+xml", "application/json")) { "Đã trả về trang web hoặc thông tin lỗi" }
                            if (response.code == 206) {
                                val range = Regex("bytes 0-([0-9]+)/([0-9]+)").matchEntire(response.header("Content-Range").orEmpty())
                                check(range != null && range.groupValues[1].toLong() >= 3 &&
                                    (release.size <= 0 || range.groupValues[2].toLong() == release.size)) { "Phạm vi hoặc kích thước gói cài đặt không khớp" }
                            } else {
                                check(release.size <= 0 || body.contentLength() <= 0 || body.contentLength() == release.size) { "Kích thước gói cài đặt không khớp" }
                            }
                            val signature = ByteArray(4)
                            val input = body.byteStream()
                            var received = 0
                            while (received < signature.size) {
                                val count = input.read(signature, received, signature.size - received)
                                if (count < 0) break
                                received += count
                            }
                            check(received == 4 && signature.contentEquals(byteArrayOf(0x50, 0x4b, 0x03, 0x04))) { "Không trả về tệp APK" }
                            return@withContext result(UpdateSourceStatus.AVAILABLE, "Có thể đọc phần đầu tệp APK; vẫn cần xác minh SHA-256 sau khi tải xuống đầy đủ")
                        }
                    }
                } finally { cancel.dispose() }
            }
            result(UpdateSourceStatus.UNAVAILABLE, "Quá nhiều lần chuyển hướng")
        } catch (failure: Exception) {
            currentCoroutineContext().ensureActive()
            result(if (failure is InterruptedIOException) UpdateSourceStatus.TIMEOUT else UpdateSourceStatus.UNAVAILABLE,
                if (failure is IOException) "Kết nối thất bại: ${failure.message.orEmpty()}" else failure.message.orEmpty())
        }
    }
}
