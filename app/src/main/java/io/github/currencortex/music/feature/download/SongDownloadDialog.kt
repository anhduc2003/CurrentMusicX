package io.github.currencortex.music.feature.download

import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import android.content.Intent
import android.net.Uri
import android.os.Build
import io.github.currencortex.music.ui.util.showMusicMessage
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.provider.DocumentsContract
import androidx.work.WorkInfo
import io.github.currencortex.music.ui.util.showMusicError
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.player.AudioQualitySheet
import io.github.currencortex.music.ui.component.MusicDestinationRow
import io.github.currencortex.music.ui.component.MusicDialog
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun SongDownloadDialog(song: Song, container: AppContainer, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val manager = container.songDownloads
    val directory by manager.directory.collectAsStateWithLifecycle()
    val info by remember(manager, song.id, song.musicSource) { manager.observe(song) }.collectAsStateWithLifecycle(null)
    var quality by rememberSaveable(song.id) { mutableStateOf(container.musicSettings.state.value.quality.value) }
    var choosingQuality by rememberSaveable { mutableStateOf(false) }
    var afterFolder by rememberSaveable { mutableStateOf(false) }
    var deleting by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val active = info?.state in setOf(WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING, WorkInfo.State.BLOCKED)
    val saved = info?.state == WorkInfo.State.SUCCEEDED
    fun start() {
        try {
            if (song.emby != null) {
                val account = container.emby.state.value
                if (!account.download) { context.showMusicError("Tài khoản Emby này không được phép tải xuống"); return }
                manager.start(song, AudioQuality.from(quality), 0, account.server, "emby:" + account.identity,
                    navidromeRevision = account.revision)
            } else if (song.navidrome != null) {
                val account = container.navidrome.state.value
                manager.start(song, AudioQuality.from(quality), 0, account.server, "navidrome:" + account.identity,
                    navidromeRevision = account.revision)
            } else manager.start(song, AudioQuality.from(quality), container.accountRepository.state.value.account?.id ?: 0L,
                container.accountRepository.server, container.audioSettings.access().identity,
                if (container.nativeNetease != null) container.neteaseSessions.state.value.profile?.uid ?: 0L else -1)
        } catch (cancelled: CancellationException) { throw cancelled }
        catch (_: Exception) { context.showMusicError("Không thể bắt đầu tải xuống, vui lòng thử lại") }
    }
    // Deletes only the URIs this task published; a stale/missing file must not abort the rest.
    fun deleteSaved() {
        val completed = info?.takeIf { it.state == WorkInfo.State.SUCCEEDED } ?: return
        if (deleting) return
        val output = completed.outputData
        val uris = (output?.getStringArray("files").orEmpty().toList() + listOfNotNull(output?.getString("uri")))
            .filter { it.isNotBlank() }.mapNotNull { runCatching { Uri.parse(it) }.getOrNull() }.distinct()
        deleting = true
        scope.launch(Dispatchers.Main.immediate) {
            try {
                val failed = withContext(Dispatchers.IO) { uris.filter { uri -> runCatching {
                    if (DocumentsContract.isDocumentUri(context, uri)) DocumentsContract.deleteDocument(context.contentResolver, uri)
                    else { context.contentResolver.delete(uri, null, null); true }
                }.getOrDefault(false).not() } }
                if (failed.isEmpty()) withContext(Dispatchers.IO) { manager.hideCompleted(completed.id) }
                if (failed.isEmpty()) context.showMusicMessage("Đã xóa tệp đã tải xuống", longDuration = true)
                else context.showMusicError("Không thể xóa một số tệp, vui lòng thử lại hoặc kiểm tra tại vị trí lưu")
            } catch (cancelled: CancellationException) { throw cancelled }
              catch (_: Exception) { context.showMusicError("Không thể cập nhật lịch sử tải xuống, vui lòng thử lại") }
              finally { deleting = false }
        }
    }
    val folderLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        if (uri != null) try { manager.setDirectory(uri); if (afterFolder) start() }
        catch (cancelled: CancellationException) { throw cancelled }
        catch (_: Exception) { context.showMusicError("Không thể ghi vào thư mục này, vui lòng chọn lại") }
        afterFolder = false
    }
    // Reuse the playback picker so both quality sheets stay visually identical.
    if (choosingQuality) AudioQualitySheet(AudioQuality.from(quality), { quality = it.value },
        { choosingQuality = false }, enabled = !active, title = "Chất lượng tải xuống")
    else MusicDialog("Tải bài hát xuống", onDismiss) {
        Column(Modifier.testTag("song_download_dialog").heightIn(max = 360.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(song.name, fontSize = 18.sp)
            Text(song.artists, fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
            if (song.privateLibrary) Text(song.originalQualityLabel) else MusicDestinationRow("Chất lượng tải xuống", { choosingQuality = true }, summary = AudioQuality.from(quality).label,
                enabled = !active && !deleting, modifier = Modifier.testTag("song_download_quality"))
            val location = directory?.let { Uri.decode(it.lastPathSegment.orEmpty()).substringAfter(':') }
                ?: if (Build.VERSION.SDK_INT >= 29) "Download/CurrentMusic" else "Vui lòng chọn thư mục"
            MusicDestinationRow("Vị trí lưu", { afterFolder = false; folderLauncher.launch(directory) }, summary = location,
                enabled = !active && !deleting, modifier = Modifier.testTag("song_download_directory"))
            Text("Lưu ở định dạng gốc, nhúng thông tin bài hát, ảnh bìa và lời bài hát; đồng thời lưu ảnh bìa cùng tên và tệp LRC.", fontSize = 12.sp,
                color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
            if (active) {
                val data = info?.progress
                val received = data?.getLong("received", 0) ?: 0
                val total = data?.getLong("total", -1) ?: -1
                Text(data?.getString("phase") ?: "Đang chờ tải xuống", Modifier.testTag("song_download_phase"))
                if (total > 0) {
                    LinearProgressIndicator(progress = (received.toDouble() / total).toFloat().coerceIn(0f, 1f), modifier = Modifier.fillMaxWidth())
                    Text("${downloadSize(received)} / ${downloadSize(total)}", fontSize = 12.sp)
                } else { LinearProgressIndicator(Modifier.fillMaxWidth()); if (received > 0) Text(downloadSize(received), fontSize = 12.sp) }
            }
            when (info?.state) {
                WorkInfo.State.SUCCEEDED -> {
                    Text("Tải xuống hoàn tất · ${info?.outputData?.getString("quality").orEmpty()}", Modifier.testTag("song_download_complete"))
                    Text(info?.outputData?.getString("name").orEmpty(), fontSize = 12.sp)
                    info?.outputData?.getString("warning")?.takeIf { it.isNotBlank() }?.let { Text(it, fontSize = 12.sp) }
                    MusicDestinationRow("Tải xuống lại", ::start, summary = "Lưu thêm một bản theo chất lượng đang chọn", enabled = !deleting, chevron = false)
                }
                WorkInfo.State.FAILED -> Text(info?.outputData?.getString("error") ?: "Tải xuống thất bại, vui lòng thử lại", Modifier.testTag("song_download_error"))
                WorkInfo.State.CANCELLED -> Text("Đã hủy tải xuống")
                else -> Unit
            }
        }
// Three buttons must still fit the longest label ("Mở bài hát") on one line, so the primary        // action keeps the extra width instead of splitting the row into equal thirds.
        MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MusicDialogButton("Đóng", onClick = onDismiss, modifier = Modifier.weight(0.9f))
            if (saved) MusicDialogButton(if (deleting) "Đang xóa" else "Xóa", onClick = { deleteSaved() }, enabled = !deleting,
                destructive = true,
                modifier = Modifier.weight(0.9f).testTag("song_download_delete"))
            MusicDialogButton(when { active -> "Hủy tải xuống"; info?.state == WorkInfo.State.FAILED -> "Thử tải lại"
                saved -> "Mở bài hát"; else -> "Bắt đầu tải xuống" }, onClick = {
                when {
                    active -> manager.cancel(song)
                    saved -> try {
                        context.startActivity(Intent(Intent.ACTION_VIEW).setDataAndType(Uri.parse(info?.outputData?.getString("uri")), "audio/*")
                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION))
                    } catch (cancelled: CancellationException) { throw cancelled }
                    catch (_: Exception) { context.showMusicError("Không thể mở tệp, vui lòng kiểm tra tại vị trí lưu") }
                    Build.VERSION.SDK_INT < 29 && directory == null -> { afterFolder = true; folderLauncher.launch(null) }
                    else -> start()
                }
            }, enabled = !deleting, modifier = Modifier.weight(1.45f).testTag("song_download_start"))
        }
    }
}

private fun downloadSize(bytes: Long) = String.format(java.util.Locale.ROOT, "%.1f MB", bytes / (1024.0 * 1024))
