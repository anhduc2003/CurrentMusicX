package io.github.currencortex.music.core.download

import android.media.MediaExtractor
import android.media.MediaFormat
import android.net.Uri
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.*
import java.io.File

data class MvDownloadResult(val saved: SavedSongDownload, val height: Int)

class MvDownloadEngine(private val cache: File, private val resolve: suspend (Long, Int) -> String,
    private val store: SongDownloadStore, private val guard: suspend () -> Unit = {}) {
    suspend fun download(song: Song, resolution: Int, directory: Uri?, attempt: String,
        progress: suspend (SongDownloadProgress) -> Unit): MvDownloadResult = withContext(Dispatchers.IO) {
        require(song.video && song.mv > 0)
        require(resolution in listOf(240, 480, 720, 1080))
        require(attempt.matches(Regex("[a-zA-Z0-9-]+")))
        val folder = File(cache, attempt).apply { check(mkdirs() || isDirectory) { "Bộ nhớ đệm tải xuống không khả dụng" } }
        try {
            guard()
            progress(SongDownloadProgress("Đang lấy URL MV"))
            val url = resolve(song.mv, resolution)
            val file = File(folder, "video.mp4")
            MediaDownloadTransfer().fetch(url, file, 2L * 1024 * 1024 * 1024) { received, total ->
                progress(SongDownloadProgress("Đang tải MV", received, total))
            }
            progress(SongDownloadProgress("Đang kiểm tra tệp video"))
            val header = file.inputStream().use { input -> ByteArray(12).also { check(input.read(it) == 12) { "Tệp MV không hoàn chỉnh" } } }
            check(header.copyOfRange(4, 8).toString(Charsets.US_ASCII) == "ftyp") { "MV này không phải video MP4 có thể tải xuống" }
            val extractor = MediaExtractor()
            val height = try {
                extractor.setDataSource(file.path)
                val video = (0 until extractor.trackCount).map(extractor::getTrackFormat)
                    .firstOrNull { it.getString(MediaFormat.KEY_MIME)?.startsWith("video/") == true }
                check(video != null) { "Nội dung tải xuống không có video phát được" }
                video.getInteger(MediaFormat.KEY_HEIGHT)
            } finally { extractor.release() }
            currentCoroutineContext().ensureActive(); guard()
            progress(SongDownloadProgress("Đang lưu vào thư mục tải xuống"))
            val saved = store.save("${SongDownloadNames.base(song.copy(id = song.mv))} - MV ${height}P",
                listOf(SongDownloadFile(file, "video/mp4")), directory)
            MvDownloadResult(saved, height)
        } finally { folder.deleteRecursively() }
    }
}
