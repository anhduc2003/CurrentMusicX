package io.github.currencortex.music.feature.library

import android.content.Intent
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.network.AppResult
import io.github.currencortex.music.core.network.appResult
import androidx.compose.ui.geometry.Rect
import io.github.currencortex.music.data.library.Playlist
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.MusicDialogButton
import io.github.currencortex.music.ui.component.nativeui.SettingsUiScope
import io.github.currencortex.music.ui.component.nativeui.GlassMenuItem
import io.github.currencortex.music.ui.component.nativeui.MusicArtworkPreview
import top.yukonga.miuix.kmp.basic.Text

@Composable internal fun PlaylistArtworkPreview(
    vm: LibraryViewModel, playlist: Playlist, anchor: Rect, canPlay: Boolean, onPlay: (shuffle: Boolean) -> Unit,
    onOpen: () -> Unit, onDismiss: () -> Unit,
) {
    val binding by vm.container.bindingRepository.state.collectAsStateWithLifecycle()
    val session by vm.container.neteaseSessions.state.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
    var info by remember(playlist.id, playlist.source, session.revision) { mutableStateOf<Playlist?>(null) }
    val native = playlist.source == "netease"
    LaunchedEffect(playlist.id, playlist.source, session.revision) {
        if (native) when (val result = appResult { vm.container.neteaseLibrary.playlistInfo(playlist.id) }) {
            is AppResult.Success -> info = result.value
            is AppResult.Failure -> vm.container.errorNotices.show(result.message)
        }
    }
    val target = info
    val canSubscribe = native && !playlist.nativeOwned && !playlist.nativeLiked &&
        target?.nativeOwned != true && target?.nativeLiked != true
    val subscribed = target?.nativeSubscribed ?: playlist.nativeSubscribed
    val display = target ?: playlist
    val context = LocalContext.current
    var infoOpen by remember(playlist.id, playlist.source) { mutableStateOf(false) }
    fun share() {
        val text = buildString {
            appendLine("《${display.name}》 · ${display.count} bài hát")
            if (display.description.isNotBlank()) appendLine(display.description)
            if (native) appendLine("https://music.163.com/#/playlist?id=${playlist.id}")
            append("từ CurrentMusic")
        }
        runCatching {
            context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, text), "Chia sẻ danh sách phát")
        }.onFailure { vm.container.errorNotices.show("Hiện chưa thể mở chia sẻ") }
    }
    if (infoOpen) SettingsUiScope {
        MusicDialog("Thông tin danh sách phát", onDismiss = onDismiss) {
            Text(display.name, Modifier.testTag("playlist_preview_info_name"), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Text(if (display.source in setOf("netease", "ncm")) "Nhạc Vân NetEase" else "CurrentMusic", fontSize = 13.sp)
            Text("${display.count} bài hát", fontSize = 13.sp)
            Text(display.description.ifBlank { "Chưa có giới thiệu về danh sách phát" },
                Modifier.heightIn(max = 240.dp).verticalScroll(rememberScrollState()), fontSize = 14.sp)
            MusicDialogButton("Đóng", onClick = onDismiss)
        }
    } else MusicArtworkPreview(
        cover = playlist.cover.ifBlank { playlist.songs.firstOrNull()?.cover.orEmpty() },
        title = playlist.name, anchor = anchor,
        actions = buildList {
            add(GlassMenuItem("play", "Phát tất cả", MusicIcons.Play, enabled = canPlay, onClick = { onPlay(false) }))
            if (canSubscribe) add(GlassMenuItem("subscribe", if (subscribed) "Đã lưu vào thư viện" else "Lưu vào thư viện",
                if (subscribed) Icons.Default.Star else MusicIcons.Star,
                enabled = target != null && !busy && binding?.bound == true && binding?.stale == false,
                keepOpen = true, onClick = {
                    vm.action(if (subscribed) "Đã bỏ lưu danh sách phát" else "Đã lưu danh sách phát") {
                        if (subscribed) vm.container.neteaseLibrary.unsubscribe(playlist.id)
                        else vm.container.neteaseLibrary.subscribe(playlist.id)
                        info = target?.copy(nativeSubscribed = !subscribed)
                    }
                }))
            add(GlassMenuItem("shuffle", "Phát ngẫu nhiên", MusicIcons.Shuffle, enabled = canPlay, onClick = { onPlay(true) }))
        },
        destinations = listOf(
            GlassMenuItem("open_playlist", "Mở danh sách phát", onClick = onOpen),
            GlassMenuItem("playlist_info", "Thông tin danh sách phát", keepOpen = true, onClick = { infoOpen = true }),
            GlassMenuItem("share_playlist", "Chia sẻ", onClick = ::share),
        ),
        onDismiss = onDismiss, previewKind = "Danh sách phát",
    )
}
