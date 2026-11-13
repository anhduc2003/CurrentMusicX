package io.github.currencortex.music.feature.playlistimport

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun ImportStep(number: Int, label: String) {
    val colors = MiuixTheme.colorScheme
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(number.toString().padStart(2, '0'), color = colors.primary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.background(colors.primary.copy(alpha = .1f), RoundedCornerShape(8.dp)).padding(7.dp))
        Text(label, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
    }
}

/** The record motif shares the player's visual language and follows the user's theme. */
@Composable internal fun PlaylistImportHero(mode: PlaylistImportMode) {
    val colors = MiuixTheme.colorScheme
    val title = when (mode) {
        PlaylistImportMode.LINKS -> "Mang danh sách phát sang đây"
        PlaylistImportMode.TEXT -> "Biến tên bài hát thành danh sách phát"
        PlaylistImportMode.IMAGES -> "Bắt đầu từ một ảnh chụp màn hình"
        PlaylistImportMode.LOCAL -> "Nhập bộ sưu tập trên thiết bị"
    }
    val summary = when (mode) {
        PlaylistImportMode.LINKS -> "Sao chép liên kết để tiếp tục nghe bộ sưu tập của bạn."
        PlaylistImportMode.TEXT -> "Tìm bài hát dựa trên tên bài hát và ca sĩ."
        PlaylistImportMode.IMAGES -> "Nhận diện ảnh chụp màn hình để lưu các bài hát yêu thích."
        PlaylistImportMode.LOCAL -> "Chỉ đối chiếu thông tin, tệp âm thanh vẫn ở trên thiết bị."
    }
    SettingsGroup { SettingsPreference(title, summary) }

}

@Composable internal fun PlaylistImportGuide() {
    val colors = MiuixTheme.colorScheme
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        GuideRow("↗", "Chia sẻ danh sách phát trong ứng dụng gốc", "Sao chép liên kết danh sách phát, trang cá nhân hoặc bài viết.")
        Box(Modifier.padding(start = 19.dp).width(1.dp).height(12.dp).background(colors.onSurfaceVariantSummary.copy(alpha = .2f)))
        GuideRow("↙", "Quay lại đây để dán", "Hỗ trợ toàn bộ nội dung chia sẻ, tối đa 10 liên kết mỗi lần.")
    }
}

@Composable private fun GuideRow(symbol: String, title: String, detail: String) {
    val colors = MiuixTheme.colorScheme
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Box(Modifier.size(40.dp).background(colors.surfaceContainer, CircleShape), contentAlignment = Alignment.Center) {
            Text(symbol, fontSize = 21.sp, color = colors.primary)
        }
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(detail, fontSize = 12.sp, lineHeight = 18.sp, color = colors.onSurfaceVariantSummary)
        }
    }
}
