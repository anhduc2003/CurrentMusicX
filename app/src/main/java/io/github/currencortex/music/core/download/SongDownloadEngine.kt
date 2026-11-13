package io.github.currencortex.music.core.download

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMuxer
import android.media.MediaCodec
import android.net.Uri
import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.data.song.AudioSource
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.lyrics.model.LyricsDocument
import kotlinx.coroutines.*
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class SongDownloadProgress(val phase: String, val received: Long = 0, val total: Long = -1)
data class SongDownloadResult(val saved: SavedSongDownload, val warning: String, val quality: String)

class SongDownloadEngine(
    private val cache: File,
    private val resolve: suspend (Song, AudioQuality) -> AudioSource,
    private val detail: suspend (Song) -> Song,
    private val lyrics: suspend (Song) -> LyricsDocument,
    private val store: SongDownloadStore,
    // Re-checked before publishing: account/audio-source changes must not produce a mixed file.
    private val guard: suspend () -> Unit = {},
    // Audio/image hosts never receive account headers or API interceptors.
    private val http: OkHttpClient = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS).build(),
) {
    suspend fun download(song: Song, quality: AudioQuality, tree: Uri?, attempt: String,
        progress: suspend (SongDownloadProgress) -> Unit): SongDownloadResult = withContext(Dispatchers.IO) {
        require(attempt.matches(Regex("[a-zA-Z0-9-]+")))
        require(!song.video) { "Hiện chưa hỗ trợ tải bài hát dạng video" }
        val folder = File(cache, attempt).apply { check(mkdirs() || isDirectory) { "Bộ nhớ đệm tải xuống không khả dụng" } }
        val warnings = mutableListOf<String>()
        try {
            progress(SongDownloadProgress("Đang lấy bài hát và chất lượng âm thanh"))
            val source = resolve(song, quality)
            val info = try { detail(song) } catch (e: CancellationException) { throw e }
                catch (_: Exception) { warnings += "Một phần thông tin bài hát được lấy từ dữ liệu danh sách"; song }
            progress(SongDownloadProgress("Đang tải âm thanh"))
            val raw = File(folder, "audio.download")
            fetch(source.url, raw, 512L * 1024 * 1024) { received, total -> progress(SongDownloadProgress("Đang tải âm thanh", received, total)) }
            if (source.md5.matches(Regex("[a-fA-F0-9]{32}"))) {
                val digest = MessageDigest.getInstance("MD5")
                raw.inputStream().use { input -> val buffer = ByteArray(65536)
                    while (true) { currentCoroutineContext().ensureActive(); val count = input.read(buffer); if (count < 0) break; digest.update(buffer, 0, count) } }
                check(digest.digest().joinToString("") { "%02x".format(it) }.equals(source.md5, true)) { "Xác minh âm thanh thất bại, vui lòng tải xuống lại" }
            }
            val format = AudioDownloadFormat.detect(raw)
            val audio = File(folder, "audio.${if (format == "aac") "m4a" else format}")
            if (format == "aac") remuxAac(raw, audio) else check(raw.renameTo(audio)) { "Chuẩn bị tệp âm thanh thất bại" }
            progress(SongDownloadProgress("Đang lấy ảnh bìa và lời bài hát"))
            val document = try { lyrics(info) } catch (e: CancellationException) { throw e }
                catch (_: Exception) { warnings += "Không lấy được lời bài hát"; LyricsDocument() }
            val lrc = DownloadLyrics.lrc(info, document)
            if (lrc.isBlank() && "Không lấy được lời bài hát" !in warnings) warnings += "Hiện chưa lấy được lời bài hát"
            val cover = if (info.cover.isBlank()) { warnings += "Bài hát này hiện chưa có ảnh bìa"; null } else try {
                val image = File(folder, "cover.download")
                fetch(info.cover, image, 12L * 1024 * 1024) { _, _ -> }
                normalizeCover(image)
            } catch (e: CancellationException) { throw e }
                catch (_: Exception) { warnings += "Không lấy được ảnh bìa"; null }
            currentCoroutineContext().ensureActive()
            guard()
            progress(SongDownloadProgress("Đang ghi thông tin bài hát"))
            SongTagWriter.write(audio, info, lrc, cover)
            val files = mutableListOf(SongDownloadFile(audio, AudioDownloadFormat.mime(audio.extension)))
            if (lrc.isNotBlank()) files += SongDownloadFile(File(folder, "lyrics.lrc").apply { writeText(lrc, Charsets.UTF_8) }, "application/octet-stream")
            if (cover != null) files += SongDownloadFile(File(folder, "cover.jpg").apply { writeBytes(cover.bytes) }, "image/jpeg")
            progress(SongDownloadProgress("Đang lưu vào thư mục tải xuống"))
            val saved = store.save(SongDownloadNames.base(info), files, tree)
            SongDownloadResult(saved, warnings.joinToString("；"), source.qualityLabel)
        } finally {
            // Only this attempt's private directory, never the user's download folder.
            folder.deleteRecursively()
        }
    }

    private suspend fun fetch(url: String, target: File, limit: Long, progress: suspend (Long, Long) -> Unit) =
        MediaDownloadTransfer(http).fetch(url, target, limit, progress)

    private fun normalizeCover(file: File): DownloadCover {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        BitmapFactory.decodeFile(file.path, bounds)
        require(bounds.outWidth > 0 && bounds.outHeight > 0) { "Định dạng ảnh bìa không hợp lệ" }
        var sample = 1
        while (maxOf(bounds.outWidth, bounds.outHeight) / sample > 1200) sample *= 2
        val bitmap = BitmapFactory.decodeFile(file.path, BitmapFactory.Options().apply { inSampleSize = sample })
            ?: throw IOException("Không thể đọc ảnh bìa")
        return try {
            val output = ByteArrayOutputStream()
            check(bitmap.compress(Bitmap.CompressFormat.JPEG, 92, output))
            DownloadCover(output.toByteArray(), bitmap.width, bitmap.height)
        } finally { bitmap.recycle() }
    }

    private suspend fun remuxAac(input: File, output: File) {
        val extractor = MediaExtractor()
        var muxer: MediaMuxer? = null
        try {
            extractor.setDataSource(input.path)
            val track = (0 until extractor.trackCount).firstOrNull {
                extractor.getTrackFormat(it).getString(MediaFormat.KEY_MIME) == "audio/mp4a-latm"
            } ?: throw IOException("Không thể đọc âm thanh AAC")
            val format = extractor.getTrackFormat(track)
            extractor.selectTrack(track)
            val activeMuxer = MediaMuxer(output.path, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4).also { muxer = it }
            val target = activeMuxer.addTrack(format); activeMuxer.start()
            val size = if (format.containsKey(MediaFormat.KEY_MAX_INPUT_SIZE)) format.getInteger(MediaFormat.KEY_MAX_INPUT_SIZE) else 1024 * 1024
            val buffer = ByteBuffer.allocate(size.coerceIn(1024 * 1024, 16 * 1024 * 1024))
            val info = MediaCodec.BufferInfo()
            while (true) {
                currentCoroutineContext().ensureActive(); buffer.clear()
                val count = extractor.readSampleData(buffer, 0)
                if (count < 0) break
                check(extractor.sampleFlags and MediaExtractor.SAMPLE_FLAG_ENCRYPTED == 0) { "Không thể lưu nguồn âm thanh hiện tại, vui lòng thử chất lượng âm thanh khác" }
                val flags = if (extractor.sampleFlags and MediaExtractor.SAMPLE_FLAG_SYNC != 0) MediaCodec.BUFFER_FLAG_KEY_FRAME else 0
                info.set(0, count, extractor.sampleTime, flags)
                activeMuxer.writeSampleData(target, buffer, info); extractor.advance()
            }
            activeMuxer.stop()
        } finally { extractor.release(); muxer?.release() }
    }
}

object AudioDownloadFormat {
    fun detect(file: File): String {
        val head = file.inputStream().use { input -> ByteArray(64).let { bytes ->
            bytes.copyOf(input.read(bytes).coerceAtLeast(0))
        } }
        fun starts(text: String) = head.take(text.length).toByteArray().contentEquals(text.toByteArray(Charsets.US_ASCII))
        return when {
            starts("fLaC") -> "flac"
            starts("OggS") -> {
                if (head.toString(Charsets.ISO_8859_1).contains("OpusHead"))
                    throw IOException("Hiện chưa hỗ trợ ghi thông tin bài hát cho định dạng Opus, vui lòng chọn chất lượng tiêu chuẩn hoặc không mất dữ liệu")
                "ogg"
            }
            starts("RIFF") && head.size >= 12 && head.copyOfRange(8, 12).toString(Charsets.US_ASCII) == "WAVE" -> "wav"
            head.size >= 8 && head.copyOfRange(4, 8).toString(Charsets.US_ASCII) == "ftyp" -> "m4a"
            starts("ID3") -> "mp3"
            head.size >= 2 && head[0].toInt() and 255 == 255 && head[1].toInt() and 246 == 240 -> "aac"
            head.size >= 2 && head[0].toInt() and 255 == 255 && head[1].toInt() and 224 == 224 && head[1].toInt() and 6 != 0 -> "mp3"
            else -> throw IOException("Hiện chưa hỗ trợ tải định dạng âm thanh này, vui lòng thử chất lượng tiêu chuẩn hoặc không mất dữ liệu")
        }
    }
    fun mime(format: String) = when (format.lowercase(Locale.ROOT)) {
        "mp3" -> "audio/mpeg"; "flac" -> "audio/flac"; "m4a" -> "audio/mp4"
        "ogg", "opus" -> "audio/ogg"; "wav" -> "audio/wav"; else -> "application/octet-stream"
    }
}
