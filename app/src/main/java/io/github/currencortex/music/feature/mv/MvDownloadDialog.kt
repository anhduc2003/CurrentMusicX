package io.github.currencortex.music.feature.mv

import io.github.currencortex.music.ui.component.MusicChoiceDialog
import io.github.currencortex.music.ui.component.MusicDialogChoiceRow

import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import android.content.Intent
import android.net.Uri
import android.os.Build
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
import androidx.work.WorkInfo
import io.github.currencortex.music.ui.util.showMusicError
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.MusicDestinationRow
import io.github.currencortex.music.ui.component.MusicDialog
import kotlinx.coroutines.CancellationException
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

internal fun WorkInfo?.mvActive() = this?.state in listOf(WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING, WorkInfo.State.BLOCKED)
internal fun WorkInfo?.mvLabel(): String = when {
    mvActive() -> {
        val total = this?.progress?.getLong("total", -1) ?: -1
        if (total > 0) "Đang tải ${((this?.progress?.getLong("received", 0) ?: 0).toDouble() / total * 100).toInt().coerceIn(0, 100)}%" else "Đang chờ tải xuống"
    }
    this?.state == WorkInfo.State.SUCCEEDED -> "Đã tải xuống"
    this?.state == WorkInfo.State.FAILED -> "Tải xuống thất bại"
    else -> "Tải MV xuống"
}

@Composable internal fun MvQualityDialog(current: Int, onSelect: (Int) -> Unit, onDismiss: () -> Unit) {
    MusicChoiceDialog("Chất lượng", onDismiss) {
        Text("Ưu tiên chất lượng đã chọn; chất lượng thực tế phụ thuộc vào nguồn video.", Modifier.padding(horizontal = 24.dp, vertical = 12.dp), fontSize = 13.sp)
        listOf(1080, 720, 480, 240).forEach { value ->
            MusicDialogChoiceRow("${value}P", current == value, { onSelect(value); onDismiss() },
                modifier = Modifier.testTag("mv_quality_$value"))
        }
    }
}

@Composable fun MvDownloadDialog(song: Song, container: AppContainer, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val manager = container.mvDownloads
    val directory by manager.directory.collectAsStateWithLifecycle()
    val items by remember(manager, song.mv) { manager.observe(song) }.collectAsStateWithLifecycle(emptyList())
    val info = items.lastOrNull()
    val active = info.mvActive()
    val saved = info?.state == WorkInfo.State.SUCCEEDED
    var quality by rememberSaveable(song.mv) { mutableIntStateOf(song.videoQuality) }
    var choosing by rememberSaveable { mutableStateOf(false) }
    var afterFolder by rememberSaveable { mutableStateOf(false) }
    fun start() {
        try {
            manager.start(song, quality, container.accountRepository.state.value.account?.id ?: 0L,
                container.musicSession().server,
                if (container.nativeNetease != null) container.neteaseSessions.state.value.profile?.uid ?: 0L else -1)
        } catch (cancelled: CancellationException) { throw cancelled }
         catch (_: Exception) { context.showMusicError("Không thể bắt đầu tải MV xuống, vui lòng thử lại") }
    }
    val folder = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        if (uri != null) try { manager.setDirectory(uri); if (afterFolder) start() }
        catch (cancelled: CancellationException) { throw cancelled }
         catch (_: Exception) { context.showMusicError("Không thể ghi vào thư mục này, vui lòng chọn lại") }
        afterFolder = false
    }
    if (choosing) MvQualityDialog(quality, { quality = it }, { choosing = false })
     else MusicDialog("Tải MV xuống", onDismiss) {
        Column(Modifier.heightIn(max = 370.dp).verticalScroll(rememberScrollState()).testTag("mv_download_dialog"),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(song.name, fontSize = 20.sp)
            Text(song.artists, fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
             MusicDestinationRow("Chất lượng tải xuống", { choosing = true }, summary = "Ưu tiên ${quality}P", enabled = !active,
                modifier = Modifier.testTag("mv_download_quality"))
             MusicDestinationRow("Vị trí lưu", { afterFolder = false; folder.launch(directory) }, enabled = !active,
                summary = directory?.let { Uri.decode(it.lastPathSegment.orEmpty()).substringAfter(':') }
                     ?: if (Build.VERSION.SDK_INT >= 29) "Download/CurrentMusic" else "Vui lòng chọn thư mục")
             Text("Lưu video MP4; tiếp tục tải xuống sau khi rời khỏi trang phát.", fontSize = 12.sp,
                color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
            if (active) {
                val received = info?.progress?.getLong("received", 0) ?: 0
                val total = info?.progress?.getLong("total", -1) ?: -1
                 Text(info?.progress?.getString("phase") ?: "Đang chờ mạng…")
                if (total > 0) LinearProgressIndicator(progress = (received.toDouble() / total).toFloat().coerceIn(0f, 1f), modifier = Modifier.fillMaxWidth())
                else LinearProgressIndicator(Modifier.fillMaxWidth())
                if (received > 0) Text("${mvSize(received)}${if (total > 0) " / ${mvSize(total)}" else ""}", fontSize = 12.sp)
            }
            when (info?.state) {
                WorkInfo.State.SUCCEEDED -> {
                     Text("Tải xuống hoàn tất · ${info.outputData.getInt("height", 0)}P", Modifier.testTag("mv_download_complete"))
                    Text(info.outputData.getString("name").orEmpty(), fontSize = 12.sp)
                     MusicDestinationRow("Tải xuống lại", ::start, summary = "Lưu thêm một bản ở chất lượng đã chọn", chevron = false)
                }
                 WorkInfo.State.FAILED -> Text(info.outputData.getString("error") ?: "Tải xuống thất bại, vui lòng thử lại", Modifier.testTag("mv_download_error"))
                 WorkInfo.State.CANCELLED -> Text("Đã hủy tải xuống")
                else -> Unit
            }
        }
        MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
             MusicDialogButton("Đóng", onDismiss, Modifier.weight(1f))
             MusicDialogButton(when { active -> "Hủy tải xuống"; saved -> "Mở video"; info?.state == WorkInfo.State.FAILED -> "Thử tải xuống lại"; else -> "Bắt đầu tải xuống" }, {
                when {
                    active -> manager.cancel(song)
                    saved -> try {
                        context.startActivity(Intent(Intent.ACTION_VIEW).setDataAndType(Uri.parse(requireNotNull(info?.outputData?.getString("uri"))), "video/mp4")
                            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION))
                    } catch (cancelled: CancellationException) { throw cancelled }
                     catch (_: Exception) { context.showMusicError("Tệp không khả dụng hoặc không có trình phát video; bạn có thể xem trong vị trí lưu hoặc tải xuống lại") }
                    Build.VERSION.SDK_INT < 29 && directory == null -> { afterFolder = true; folder.launch(null) }
                    else -> start()
                }
            }, Modifier.weight(1f).testTag("mv_download_start"))
        }
    }
}

private fun mvSize(value: Long) = String.format(java.util.Locale.ROOT, "%.1f MB", value / 1048576.0)
