package io.github.currencortex.music.feature.library

import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.library.PlaylistLikeImportState
import io.github.currencortex.music.ui.component.MusicDialog
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun PlaylistLikeImportDialog(state: PlaylistLikeImportState,
    onClose: () -> Unit, onStop: () -> Unit, onRetry: () -> Unit) {
    MusicDialog(if (state.running) "Đang nhập bài hát yêu thích từ Nhạc Vân NetEase" else "Kết quả nhập từ Nhạc Vân NetEase", onDismiss = onClose) {
        Column(Modifier.testTag("playlist_like_import").heightIn(max = 340.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(state.name, fontSize = 18.sp)
            Text(if (state.running && state.total == 0) "Đang đọc danh sách phát và tài khoản Nhạc Vân NetEase…"
                else "${state.processed} / ${state.total} bài", fontSize = 14.sp)
            if (state.running) Box(Modifier.fillMaxWidth().height(6.dp)
                .background(MiuixTheme.colorScheme.onSurface.copy(alpha = .08f), RoundedCornerShape(3.dp))) {
                val progress = if (state.total > 0) state.processed.toFloat() / state.total else 0f
                Box(Modifier.fillMaxWidth(progress.coerceIn(0f, 1f)).fillMaxHeight()
                    .background(MiuixTheme.colorScheme.primary, RoundedCornerShape(3.dp)))
            }
            Text("Thành công ${state.imported} · Đã bỏ qua ${state.skipped} · Thất bại ${state.failed}", fontSize = 15.sp)
            Text("Bỏ qua bài đã yêu thích, bài trùng lặp và bài không thuộc Nhạc Vân NetEase.", fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
            state.error?.let { Text(it, fontSize = 14.sp) }
            if (state.processed < state.total && !state.running)
                Text("Còn ${state.total - state.processed} bài chưa xử lý, có thể thử lại để nhập bổ sung.", fontSize = 13.sp)
            state.failures.forEach { Text(it, fontSize = 13.sp) }
        }
        if (state.running) {
            Text("Quá trình nhập vẫn tiếp tục sau khi đóng bảng; vui lòng giữ ứng dụng đang chạy.", fontSize = 13.sp)
            MusicDialogActions(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MusicDialogButton("Nhập trong nền", onClick = onClose, modifier = Modifier.weight(1f).testTag("playlist_import_background"))
                MusicDialogButton("Dừng nhập", onClick = onStop, modifier = Modifier.weight(1f).testTag("playlist_import_stop"))
            }
        } else {
            MusicDialogActions(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MusicDialogButton("Đóng", onClick = onClose, modifier = Modifier.weight(1f))
                if (state.error != null || state.failed > 0)
                    MusicDialogButton("Thử lại để nhập bổ sung", onClick = onRetry, modifier = Modifier.weight(1f).testTag("playlist_import_retry"))
            }
        }
    }
}
