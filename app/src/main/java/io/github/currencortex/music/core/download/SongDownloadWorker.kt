package io.github.currencortex.music.core.download

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.*
import io.github.currencortex.music.CurrentMusicApplication
import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.core.network.ApiJson
import io.github.currencortex.music.core.network.RequestSession
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.decodeFromString
import java.io.File

open class SongDownloadWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val song = try { ApiJson.decodeFromString<Song>(inputData.getString("song") ?: return Result.failure()) }
            catch (_: Exception) { return Result.failure(workDataOf("error" to "Thông tin bài hát không hợp lệ")) }
        return try {
            setForeground(notification(song, SongDownloadProgress("Đang chuẩn bị tải xuống")))
            val engine = createEngine()
            val result = engine.download(song, AudioQuality.from(inputData.getString("quality").orEmpty()),
                inputData.getString("directory")?.let(Uri::parse), id.toString()) { progress ->
                setProgress(workDataOf("phase" to progress.phase, "received" to progress.received, "total" to progress.total))
                setForeground(notification(song, progress))
            }
            Result.success(workDataOf("uri" to result.saved.audioUri.toString(), "name" to result.saved.name,
                "warning" to result.warning, "quality" to result.quality,
                "files" to result.saved.files.map(Uri::toString).toTypedArray()))
        } catch (e: CancellationException) { throw e }
          catch (e: Exception) { Result.failure(workDataOf("error" to (e.message ?: "Tải xuống thất bại, vui lòng thử lại").take(300))) }
    }
    protected open suspend fun createEngine(): SongDownloadEngine {
        val container = (applicationContext as CurrentMusicApplication).awaitContainer()
        container.ready.await(); container.sessionRestored.await()
        val song = ApiJson.decodeFromString<Song>(requireNotNull(inputData.getString("song")))
        val nav = song.navidrome != null
        val emby = song.emby != null
        fun verifySession() {
            if (emby) {
                val account = container.emby.state.value
                check(container.emby.accepts(song) && inputData.getString("server") == account.server &&
                    inputData.getString("provider") == "emby:" + account.identity && inputData.getString("navidrome_revision") == account.revision) {
                    "Tài khoản Emby đã thay đổi, vui lòng bắt đầu tải xuống lại"
                }
                return
            }
            if (nav) {
                val account = container.navidrome.state.value
                check(container.navidrome.accepts(song) && inputData.getString("server") == account.server &&
                    inputData.getString("provider") == "navidrome:" + account.identity && inputData.getString("navidrome_revision") == account.revision) {
                    "Tài khoản Navidrome đã thay đổi, vui lòng bắt đầu tải xuống lại"
                }
                return
            }
            check(inputData.getLong("account", -1) == (container.accountRepository.state.value.account?.id ?: 0L) &&
                inputData.getString("server") == container.accountRepository.server &&
                inputData.getString("provider") == container.audioSettings.access().identity &&
                (inputData.getLong("netease_uid", -1) < 0 || inputData.getLong("netease_uid", -1) ==
                    (container.neteaseSessions.state.value.profile?.uid ?: 0L))) {
                "Tài khoản hoặc nguồn nhạc đã thay đổi, vui lòng bắt đầu tải xuống lại"
            }
        }
        verifySession()
        val session = if (emby) container.emby.session() else if (nav) container.navidrome.session() else container.musicSession()
        return SongDownloadEngine(File(applicationContext.cacheDir, "song-downloads"),
            resolve = { track, quality -> if (emby) container.emby.source(track, download = true) else if (nav) container.navidrome.source(track, quality, download = true) else container.musicRepository.source(track, quality, container.audioSession()) },
            detail = { track -> container.musicRepository.detail(track) },
            lyrics = { track -> container.lyricsRepository.load(track).document },
            store = SongDownloadStore(applicationContext),
            guard = { verifySession(); check(session == if (emby) container.emby.session() else if (nav) container.navidrome.session() else container.musicSession()) { "Tài khoản đã thay đổi, vui lòng bắt đầu tải xuống lại" } },
            http = container.navidromeMedia)
    }
    private fun notification(song: Song, progress: SongDownloadProgress): ForegroundInfo {
        val manager = applicationContext.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(NotificationChannel("song-downloads", "Tải xuống bài hát", NotificationManager.IMPORTANCE_LOW))
        val percent = if (progress.total > 0) (progress.received.toDouble() / progress.total * 100).toInt().coerceIn(0, 100) else 0
        val notification = NotificationCompat.Builder(applicationContext, "song-downloads")
            .setSmallIcon(android.R.drawable.stat_sys_download).setContentTitle(song.name)
            .setContentText(progress.phase).setOnlyAlertOnce(true).setOngoing(true)
            .setProgress(100, percent, progress.total <= 0)
            .addAction(0, "Hủy", WorkManager.getInstance(applicationContext).createCancelPendingIntent(id)).build()
        val notificationId = 0x40000000 or (id.hashCode() and 0x0fffffff)
        return if (Build.VERSION.SDK_INT >= 29) ForegroundInfo(notificationId, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
            else ForegroundInfo(notificationId, notification)
    }
}
