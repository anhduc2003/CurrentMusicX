package io.github.currencortex.music.feature.lyrics.desktop

import androidx.compose.runtime.Composable
import io.github.currencortex.music.ui.component.nativeui.SettingsColorPreference

private val desktopPalette = listOf("Trắng" to 0xFFFFFFFFL, "Xám nhạt" to DESKTOP_DEFAULT_GLOW_COLOR, "Xanh băng" to 0xFF80DEFFL, "Bạc hà" to 0xFF9DE6BAL,
    "Vàng ấm" to 0xFFFFD58AL, "Hồng anh đào" to 0xFFFFAACCL, "Tím nhạt" to 0xFFC5B5FFL, "Đen" to 0xFF20232AL)

@Composable internal fun DesktopLyricsColorControl(title: String, value: Long, tag: String,
    defaultColor: Long = 0xFFFFFFFFL, save: (Long) -> Unit) {
    SettingsColorPreference(title, value, defaultColor, desktopPalette, tag, save)
}
