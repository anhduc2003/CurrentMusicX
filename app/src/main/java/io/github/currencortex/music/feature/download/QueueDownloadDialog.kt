package io.github.currencortex.music.feature.download

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton

/** Submit the captured shared list to the existing download manager only after confirmation. */
@Composable internal fun QueueDownloadDialog(container: AppContainer, songs: List<Song>, show: Boolean, onDismiss: () -> Unit) {
    val directory by container.songDownloads.directory.collectAsStateWithLifecycle()
    val settings by container.musicSettings.state.collectAsStateWithLifecycle()
    val quality = AudioQuality.from(settings.quality.value)
    var error by remember { mutableStateOf<String?>(null) }
    val folder = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        if (uri != null) try { container.songDownloads.setDirectory(uri); error = null }
         catch (_: Exception) { error = "Không thể ghi vào thư mục này, vui lòng chọn lại" }
    }
    LaunchedEffect(show) { if (show) error = null }
     MusicOverlayDialog(show = show, title = "Tải ${songs.size} bài hát", onDismissRequest = onDismiss) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
             Text("Chất lượng tải xuống：${quality.label}")
             MusicDestinationRow("Vị trí lưu", { folder.launch(directory) }, summary = directory?.let { android.net.Uri.decode(it.lastPathSegment.orEmpty()) }
                 ?: if (Build.VERSION.SDK_INT >= 29) "Download/CurrentMusic" else "Vui lòng chọn thư mục")
             Text("Thêm tác vụ theo cài đặt tải xuống hiện tại; các bài hát đang được tải sẽ giữ nguyên tác vụ hiện có.")
            error?.let { Text(it) }
            MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                 MusicDialogButton("Hủy", onDismiss, modifier = Modifier.weight(1f))
                 MusicDialogButton("Bắt đầu tải xuống", {
                    try {
                        val account = container.accountRepository.state.value.account?.id ?: 0L
                        val server = container.accountRepository.server
                        val provider = container.audioSettings.access().identity
                        val uid = if (container.nativeNetease != null) container.neteaseSessions.state.value.profile?.uid ?: 0L else -1L
                        songs.distinctBy { it.id }.forEach { container.songDownloads.start(it, quality, account, server, provider, uid) }
                        onDismiss()
                     } catch (_: Exception) { error = "Không thể tạo một số tác vụ tải xuống, vui lòng thử lại" }
                }, enabled = songs.isNotEmpty() && (Build.VERSION.SDK_INT >= 29 || directory != null),
                    modifier = Modifier.weight(1f).testTag("queue_download_confirm"))
            }
        }
    }
}
