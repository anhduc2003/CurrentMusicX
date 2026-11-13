package io.github.currencortex.music.feature.settings

import io.github.currencortex.music.ui.component.nativeui.SettingsPage
import io.github.currencortex.music.ui.component.nativeui.SettingsUiScope
import io.github.currencortex.music.ui.component.nativeui.SettingsGroup as Card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.ui.component.MusicTextAction
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.util.Locale

private fun formatSize(bytes: Long): String = when {
    bytes >= 1L shl 30 -> String.format(Locale.ROOT, "%.2f GB", bytes / (1L shl 30).toDouble())
    bytes >= 1L shl 20 -> String.format(Locale.ROOT, "%.1f MB", bytes / (1L shl 20).toDouble())
    bytes >= 1L shl 10 -> String.format(Locale.ROOT, "%.0f KB", bytes / (1L shl 10).toDouble())
    else -> "$bytes B"
}

@Composable fun StorageScreen(vm: StorageViewModel) {
    SettingsUiScope {
    val state by vm.state.collectAsStateWithLifecycle()
    val usage = state.usage
    LaunchedEffect(vm) { vm.refresh() }
    val colors = MiuixTheme.colorScheme
    val hint = colors.onSurfaceVariantSummary
    // One hue stepped down: the bar reads as a single system instead of borrowing another app's palette.
    val ramp = listOf(colors.primary, colors.primary.copy(alpha = .5f), colors.primary.copy(alpha = .28f),
        colors.onSurface.copy(alpha = .14f))
    SettingsPage("Dung lượng lưu trữ","storage_screen") {
        item { Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("CurrentMusic chiếm dụng", fontSize = 13.sp, color = hint)
            Text(formatSize(usage.total), fontSize = 32.sp, fontWeight = FontWeight.SemiBold,
                modifier = Modifier.testTag("storage_total"))
            StorageBar(listOf(usage.audio, usage.data, usage.downloads, usage.essential), ramp)
            Row(Modifier.fillMaxWidth()) {
                listOf("Bộ nhớ đệm nhạc" to usage.audio, "Bộ nhớ đệm dữ liệu" to usage.data,
                    "Tệp đã tải xuống" to usage.downloads, "Tệp cần thiết" to usage.essential).forEachIndexed { index, (label, _) ->
                    Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(Modifier.size(7.dp).clip(RoundedCornerShape(4.dp)).background(ramp[index]))
                        Text(label, fontSize = 11.sp, color = hint, maxLines = 1)
                    }
                }
            }
        } } }

        item { Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Bộ nhớ đệm nhạc")
                    Text(if (usage.audioTracks > 0) "${formatSize(usage.audio)} · ${usage.audioTracks} bản nhạc"
                        else formatSize(usage.audio), fontSize = 20.sp, fontWeight = FontWeight.Medium,
                        modifier = Modifier.testTag("storage_audio_size"))
                }
                MusicTextAction(if (state.busy) "Đang dọn dẹp…" else "Dọn dẹp", { vm.clearAudio() },
                    Modifier.testTag("clear_audio_storage"), enabled = !state.busy && usage.audio > 0, destructive = true)
            }
            Text("Âm thanh được lưu đầy đủ trong bộ nhớ đệm có thể phát lại ngoại tuyến; sau khi bị thu hồi hoặc dọn dẹp, cần tải lại.", fontSize = 12.sp, color = hint)
            Text("Giới hạn mặc định là 1 GiB, tự động thu hồi theo mức độ sử dụng gần đây. Chỉ dọn dẹp bộ nhớ đệm phát nhạc, không ảnh hưởng đến tệp đã tải xuống.",
                fontSize = 12.sp, color = hint)
        } } }

        item { Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Bộ nhớ đệm dữ liệu")
                    Text(formatSize(usage.data), fontSize = 20.sp, fontWeight = FontWeight.Medium,
                        modifier = Modifier.testTag("storage_data_size"))
                }
                MusicTextAction(if (state.busy) "Đang dọn dẹp…" else "Dọn dẹp", { vm.clearData() },
                    Modifier.testTag("clear_data_storage"), enabled = !state.busy && usage.data > 0, destructive = true)
            }
            Text("Dữ liệu tạm được tạo khi tải bìa album, lời bài hát và trang; dọn dẹp không ảnh hưởng đến tài khoản và nhạc đã tải xuống.",
                fontSize = 12.sp, color = hint)
        } } }

        item { Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Tài nguyên đã tải xuống")
            Text(if (usage.downloadCount > 0) "${formatSize(usage.downloads)} · ${usage.downloadCount} mục"
                else formatSize(usage.downloads), fontSize = 20.sp, fontWeight = FontWeight.Medium,
                modifier = Modifier.testTag("storage_download_size"))
            Text("Thống kê thư mục hệ thống «Tải xuống/CurrentMusic»; có thể xóa trong cửa sổ tải xuống ở thao tác bài hát hoặc bằng trình quản lý tệp.",
                fontSize = 12.sp, color = hint)
        } } }

        item { Card { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text("Tệp cần thiết")
            Text(formatSize(usage.essential), fontSize = 20.sp, fontWeight = FontWeight.Medium,
                modifier = Modifier.testTag("storage_essential_size"))
            Text("Ứng dụng, thư viện chạy và dữ liệu cục bộ (bao gồm thông tin đăng nhập). Dọn dẹp sẽ đăng xuất và cần tải lại ứng dụng.",
                fontSize = 12.sp, color = hint)
        } } }

        state.message?.let { item { Text(it, fontSize = 13.sp, color = hint, modifier = Modifier.testTag("storage_message")) } }
    }
    }
}

/** A single track that splits proportionally; zero-sized parts simply do not occupy a segment. */
@Composable private fun StorageBar(segments: List<Long>, colors: List<Color>) {
    Box(Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(5.dp))
        .background(MiuixTheme.colorScheme.onSurface.copy(alpha = .08f))) {
        Row(Modifier.fillMaxSize()) {
            segments.forEachIndexed { index, value ->
                if (value > 0L) Box(Modifier.weight(value.toFloat()).fillMaxHeight().background(colors[index]))
            }
        }
    }
}
