// Adapted from XBlocker data/AppUpdates.kt (MIT, Copyright 2026 XBlocker contributors).
package io.github.currencortex.music.core.update

import java.io.File
import java.io.IOException
import java.security.MessageDigest
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

fun interface UpdateDownload {
    suspend fun download(release: AppRelease, source: UpdateSource, onProgress: (Long, Long) -> Unit): File
}

/** Clean client, HTTPS-only redirects, bounded files and official GitHub asset SHA-256. */
class UpdateDownloader(client: OkHttpClient, private val directory: File,
    private val nanoTime: () -> Long = System::nanoTime,
    private val cache: UpdateArtifactCache? = null,
    private val urlFor: (AppRelease, UpdateSource) -> String = { release, source -> source.url(release) },
) : UpdateDownload {
    private val http = client.newBuilder().followRedirects(false).followSslRedirects(false)
        .retryOnConnectionFailure(false).connectTimeout(8, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS).callTimeout(0, TimeUnit.SECONDS).build()
    // Permit alternate DNS routes when the dedicated mirror's first TLS connection fails.
    private val privateMirrorHttp = http.newBuilder().retryOnConnectionFailure(true)
        .dns(object : okhttp3.Dns {
            override fun lookup(hostname: String) = http.dns.lookup(hostname).let { addresses ->
                if (hostname == "music.20110208.xyz") addresses.sortedBy { if (it is java.net.Inet4Address) 0 else 1 }
                else addresses
            }
        }).build()

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun download(release: AppRelease, source: UpdateSource, onProgress: (Long, Long) -> Unit): File = withContext(Dispatchers.IO) {
        require(release.size in 1..MAX_APK_BYTES && Regex("[a-f0-9]{64}").matches(release.sha256)) { "Thông tin xác minh gói cài đặt không hợp lệ" }
        // The digest, rather than the server filename, is the safe cache identity.
        val target = File(directory, "update-${release.sha256}.apk")
        val lease = cache?.lease(target)
        try {
            check(directory.isDirectory || directory.mkdirs()) { "Không thể tạo thư mục cập nhật" }
            lease?.ensureSafe()
            if (target.isFile && matches(target, release)) {
                onProgress(release.size, release.size)
                lease?.completed()
                return@withContext target
            }
            target.delete()
            val temporary = File(directory, "${target.name}.part")
            temporary.delete()
            var url = urlFor(release, source).toHttpUrl()
            try {
                for (redirect in 0..MAX_REDIRECTS) {
                     remoteCheck(url.scheme == "https" && url.username.isBlank() && url.password.isBlank(), "Nguồn tải xuống phải sử dụng HTTPS")
                    val request = Request.Builder().url(url)
                        .header("Accept", "application/vnd.android.package-archive, application/octet-stream")
                        .header("User-Agent", "CurrentMusic updater")
                    val call = (if (source == UpdateSource.CURRENTMUSIC) privateMirrorHttp else http).newCall(request.build())
                    val cancel = currentCoroutineContext().job.invokeOnCompletion(onCancelling = true, invokeImmediately = true) { if (it != null) call.cancel() }
                    try {
                        call.execute().use { response ->
                            if (response.code in listOf(301, 302, 303, 307, 308)) {
                                 remoteCheck(redirect < MAX_REDIRECTS, "Số lần chuyển hướng tải xuống quá nhiều")
                                 url = response.header("Location")?.let(url::resolve) ?: throw IOException("Địa chỉ chuyển hướng tải xuống không hợp lệ")
                            } else {
                                 remoteCheck(response.isSuccessful, "Tải xuống thất bại (HTTP ${response.code})")
                                 val body = response.body ?: throw IOException("Nội dung tải xuống trống")
                                remoteCheck(body.contentType()?.let { "${it.type}/${it.subtype}" } !in
                                     setOf("text/html", "application/xhtml+xml", "application/json"), "Nguồn tải xuống trả về trang web hoặc thông tin lỗi")
                                 remoteCheck(body.contentLength() <= 0 || body.contentLength() == release.size, "Kích thước gói cài đặt không khớp với thông tin bản phát hành")
                                remoteCheck(body.source().peek().readByteArray(4).contentEquals(byteArrayOf(0x50, 0x4b, 0x03, 0x04)),
                                     "Nguồn tải xuống không trả về tệp APK")
                                var received = 0L
                                var checkpoint = nanoTime()
                                var checkpointBytes = 0L
                                body.byteStream().use { input -> temporary.outputStream().buffered().use { output ->
                                    val buffer = ByteArray(64 * 1024)
                                    while (true) {
                                        currentCoroutineContext().ensureActive()
                                        val count = input.read(buffer)
                                        if (count < 0) break
                                        received += count
                                         remoteCheck(received <= release.size, "Gói cài đặt vượt quá kích thước dự kiến")
                                        output.write(buffer, 0, count)
                                        onProgress(received, release.size)
                                        // The dedicated service is verified independently and can be slow on
                                        // mobile networks. Keep its progress; readTimeout still detects stalls.
                                        if (source != UpdateSource.GITHUB && source != UpdateSource.CURRENTMUSIC && received < release.size) {
                                            val now = nanoTime()
                                            val elapsed = now - checkpoint
                                            if (elapsed >= TimeUnit.SECONDS.toNanos(16)) {
                                                remoteCheck((received - checkpointBytes).toDouble() * 1_000_000_000 / elapsed >= 32 * 1024,
                                                     "Tốc độ tải xuống từ máy chủ phản chiếu quá chậm, vui lòng dùng nguồn tải xuống khác")
                                                checkpoint = now
                                                checkpointBytes = received
                                            }
                                        }
                                    }
                                } }
                                 remoteCheck(matches(temporary, release), "Gói cài đặt không đầy đủ hoặc xác minh SHA-256 thất bại")
                                 check(temporary.renameTo(target)) { "Không thể lưu tệp cập nhật" }
                                lease?.completed()
                                return@withContext target
                            }
                        }
                    } finally { cancel.dispose() }
                }
                 throw IOException("Số lần chuyển hướng tải xuống quá nhiều")
            } catch (failure: Exception) {
                temporary.delete()
                currentCoroutineContext().ensureActive()
                throw failure
            }
        } finally { lease?.close() }
    }

    companion object {
        private fun remoteCheck(valid: Boolean, message: String) { if (!valid) throw IOException(message) }
        const val MAX_APK_BYTES = 128L * 1024 * 1024
        private const val MAX_REDIRECTS = 8
        fun matches(file: File, release: AppRelease): Boolean = file.isFile && file.length() == release.size &&
            sha256(file) == release.sha256
        fun sha256(file: File): String {
            val digest = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { input ->
                val buffer = ByteArray(64 * 1024)
                while (true) { val read = input.read(buffer); if (read < 0) break; digest.update(buffer, 0, read) }
            }
            return digest.digest().joinToString("") { "%02x".format(it) }
        }
    }
}

