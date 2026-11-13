package io.github.currencortex.music.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.song.Song
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

data class SongMenu(val song: Song, val play: () -> Unit, val next: () -> Unit, val queue: () -> Unit,
    val roomRequest: Boolean = false, val extra: (@Composable () -> Unit)? = null, val transport: Boolean = true,
    val previewAnchor: androidx.compose.ui.geometry.Rect? = null, val shareInitially: Boolean = false,
    val onMultiSelect: (() -> Unit)? = null)
data class RoomSongRequest(val request: (Song) -> Unit, val pending: (Long) -> Boolean)
val LocalSongMenu = staticCompositionLocalOf<((SongMenu) -> Unit)?> { null }
val LocalSongDownload = staticCompositionLocalOf<((Song) -> Unit)?> { null }
val LocalDismissSongMenu = staticCompositionLocalOf<() -> Unit> { {} }
val LocalRoomSongRequest = staticCompositionLocalOf<RoomSongRequest?> { null }

@Composable fun SongActionsSheet(menu: SongMenu, onDownload: ((Song) -> Unit)? = null, onDismiss: () -> Unit) {
    var sharing by remember(menu.song) { mutableStateOf(menu.shareInitially) }
    if (sharing) {
        io.github.currencortex.music.feature.share.SongShareDialog(menu.song, onDismiss)
        return
    }
    MusicChoiceDialog(menu.song.name, onDismiss) {
        Column(Modifier.heightIn(max = 420.dp).verticalScroll(rememberScrollState()).testTag("song_actions_sheet")) {
            Text(menu.song.artists, Modifier.padding(horizontal = 16.dp, vertical = 8.dp), fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
            if (menu.transport) MusicDestinationRow(if (menu.roomRequest) "Yêu cầu bài hát cho phòng" else "Phát ngay", { onDismiss(); menu.play() }, chevron = false)
            if (menu.transport && !menu.roomRequest) {
                MusicDestinationRow("Phát tiếp theo", { onDismiss(); menu.next() }, chevron = false)
                MusicDestinationRow("Thêm vào hàng đợi", { onDismiss(); menu.queue() }, chevron = false)
            }
            if (!menu.song.video && menu.song.musicSource != io.github.currencortex.music.data.song.MusicSource.LOCAL && onDownload != null) MusicDestinationRow("Tải bài hát", {
                onDismiss(); onDownload(menu.song)
            }, modifier = Modifier.testTag("song_download"), summary = "Lưu thông tin bài hát, ảnh bìa và lời bài hát", chevron = false)
            if (!menu.song.video && menu.song.musicSource != io.github.currencortex.music.data.song.MusicSource.LOCAL) MusicDestinationRow("Chia sẻ bài hát", { sharing = true },
                modifier = Modifier.testTag("song_share"), summary = "Tạo áp phích, lưu ảnh hoặc sao chép liên kết", chevron = false)
            CompositionLocalProvider(LocalDismissSongMenu provides onDismiss) { menu.extra?.invoke() }
            menu.onMultiSelect?.let { select ->
                MusicDestinationRow("Chọn nhiều", { onDismiss(); select() },
                    modifier = Modifier.testTag("playlist_multi_select"), chevron = false)
            }
        }
    }
}
