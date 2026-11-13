package io.github.currencortex.music.feature.player

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.settings.LyricsTypography
import io.github.currencortex.music.data.settings.LyricsWeight
import io.github.currencortex.music.data.settings.LyricsDisplayOptions
import io.github.currencortex.music.data.settings.LyricsStyle
import io.github.currencortex.music.feature.lyrics.ui.lyricsFontWeight
import io.github.currencortex.music.ui.component.MusicDestinationRow
import io.github.currencortex.music.ui.component.AppleMusicLyricsControls
import io.github.currencortex.music.feature.lyrics.ui.currentLyricsFontFamily
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

@Composable internal fun LyricsDisplaySettings(fontSize: Float, onFontSize: (Float) -> Unit, weightMode: LyricsWeight, onWeight: () -> Unit,
    display: LyricsDisplayOptions, onDisplay: ((LyricsDisplayOptions) -> LyricsDisplayOptions) -> Unit,
    onKaraokeScope: () -> Unit,
    font: io.github.currencortex.music.data.settings.LyricsFont = io.github.currencortex.music.data.settings.LyricsFont.SYSTEM,
    fontFile: String = "", onFont: (io.github.currencortex.music.data.settings.LyricsFont) -> Unit = {},
    onFontFile: suspend (String) -> Unit = {}, previewText: String = "Chưa có lời bài hát") {
    val ink = MiuixTheme.colorScheme.onSurface
    val classic = display.style == LyricsStyle.CLASSIC
    val staged = classic && (display.smoke || display.particles)
    var preview by remember(fontSize) { mutableFloatStateOf(fontSize) }
    var strength by remember(display.fontWeight) { mutableIntStateOf(display.fontWeight) }
    Column(Modifier.fillMaxWidth().heightIn(max = 420.dp).verticalScroll(rememberScrollState())
        .testTag("lyrics_display_settings")) {
        Column(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
            Text(previewText, fontFamily = currentLyricsFontFamily(), fontSize = preview.sp, fontWeight = lyricsFontWeight(weightMode, true, strength),
                maxLines = 1, softWrap = false, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                color = ink, modifier = Modifier.fillMaxWidth().testTag("lyrics_font_preview"))
            LyricsFontSelector(font, fontFile, onFont, onFontFile)
            Row(Modifier.fillMaxWidth().padding(top = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("Cỡ chữ ${preview.roundToInt()}", fontSize = 16.sp, color = ink, modifier = Modifier.weight(1f))
                Text("Khôi phục mặc định", fontSize = 13.sp, color = ink.copy(alpha = .65f),
                    modifier = Modifier.testTag("lyrics_font_reset").clickable(role = Role.Button) {
                        preview = LyricsTypography.DEFAULT_SIZE; onFontSize(preview)
                    }.padding(start = 12.dp, top = 12.dp, bottom = 12.dp))
            }
            Slider(value = preview, onValueChange = { preview = it.roundToInt().toFloat() },
                onValueChangeFinished = { onFontSize(preview) },
                valueRange = LyricsTypography.MIN_SIZE..LyricsTypography.MAX_SIZE,
                modifier = Modifier.testTag("lyrics_font_size").semantics {
                    contentDescription = "Cỡ chữ lời bài hát"
                    // Miuix's SetProgress callback does not call onValueChangeFinished.
                    setProgress { target ->
                        preview = LyricsTypography.normalize(target).roundToInt().toFloat()
                        onFontSize(preview); true
                    }
                })
        }
        if (!classic) AppleMusicLyricsControls(display, onDisplay)
        if (!staged) LyricsToggle("Căn giữa lời bài hát", display.centered, { value -> onDisplay { it.copy(centered = value) } }, "lyrics_centered")
        Column(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
            Text("Độ đậm phông chữ $strength", fontSize = 16.sp, color = ink)
            Slider(value = strength.toFloat(), onValueChange = { strength = ((it / 100).roundToInt() * 100).coerceIn(400, 900) },
                onValueChangeFinished = { onDisplay { it.copy(fontWeight = strength) } }, valueRange = 400f..900f,
                modifier = Modifier.testTag("lyrics_font_strength").semantics {
                    contentDescription = "Độ đậm phông chữ lời bài hát"
                    setProgress { target ->
                        if (!target.isFinite()) false else {
                            strength = ((target / 100).roundToInt() * 100).coerceIn(400, 900)
                            onDisplay { it.copy(fontWeight = strength) }; true
                        }
                    }
                })
            Text("400 Thường · 500 Trung bình · 600–900 Đậm tổng hợp", fontSize = 12.sp, lineHeight = 18.sp,
                color = ink.copy(alpha = .6f), modifier = Modifier.padding(top = 4.dp))
        }
        MusicDestinationRow("Độ đậm chữ lời bài hát", summary = weightMode.label, onClick = onWeight, modifier = Modifier.testTag("open_lyrics_weight"))
        if (classic && android.os.Build.VERSION.SDK_INT >= 31) LyricsToggle("Làm mờ khung lời bài hát", display.blur,
            { value -> onDisplay { it.copy(blur = value) } }, "lyrics_blur")
        LyricsToggle("Hiệu ứng cuộn xen kẽ", display.stagger, { value -> onDisplay { it.copy(stagger = value) } }, "lyrics_stagger")
        if (classic && !staged) LyricsToggle("Lời bài hát nghiêng 3D", display.perspective, { value -> onDisplay { it.copy(perspective = value) } },
            "lyrics_perspective", "Phối cảnh 3D theo chiều ngang và dọc, mặt phẳng lời bài hát gần lớn xa nhỏ")
        if (classic) LyricsToggle("Ánh sáng lời bài hát", display.glow, { value -> onDisplay { it.copy(glow = value) } },
            "lyrics_glow", "Sáng dần ở nốt ngân và đoạn nhấn, nốt ngắn vẫn được tô sáng bình thường")
        if (classic) {
            LyricsToggle("Lời bài hát khói bay", display.smoke, { value -> onDisplay { it.copy(smoke = value, particles = if (value) false else it.particles).normalized() } },
                "lyrics_smoke", "Tự động tắt 3D và căn giữa câu hiện tại, xem trước câu tiếp theo phía trên thanh tiến trình; màu sắc thay đổi theo ảnh bìa")
            LyricsToggle("Lời bài hát bụi sao", display.particles, { value -> onDisplay { it.copy(particles = value, smoke = if (value) false else it.smoke).normalized() } },
                "lyrics_particles", "Chữ kết tụ từng ký tự rồi tan thành bụi sao, màu sắc thay đổi theo ảnh bìa; không dùng đồng thời với hiệu ứng khói bay và 3D")
        }
        LyricsToggle("Lời dịch", display.translation, { value -> onDisplay { it.copy(translation = value) } },
            "lyrics_translation", if (classic) "Chỉ hiển thị bản dịch của lời bài hát đang phát" else "Hiển thị bản dịch của từng câu, cuộn cùng lời gốc")
        LyricsToggle("Phiên âm La-tinh", display.romanization, { value -> onDisplay { it.copy(romanization = value) } },
            "lyrics_romanization", if (classic) "Chỉ hiển thị phiên âm La-tinh của lời bài hát đang phát" else "Hiển thị phiên âm từng từ, chú âm hoặc phiên âm La-tinh cả dòng do lời bài hát cung cấp")
        LyricsToggle("Hoạt ảnh từng chữ", display.wordAnimation, { value -> onDisplay { it.copy(wordAnimation = value) } }, "lyrics_word_animation")
        if (display.wordAnimation) LyricsToggle("Nâng nhẹ từng chữ", display.wordLift, { value -> onDisplay { it.copy(wordLift = value) } },
            "lyrics_word_lift", "Chữ đang được hát sẽ được nâng nhẹ lên, sau khi hát xong vẫn giữ nguyên vị trí cao"
        if (classic) MusicDestinationRow("Chiến lược tương thích hiệu ứng lời bài hát karaoke (từng chữ)",
            summary = display.karaokeScope.label,
            onClick = onKaraokeScope, modifier = Modifier.testTag("open_karaoke_scope"))
         LyricsToggle("Tự động ẩn bảng điều khiển trên trang lời bài hát", display.hideControls, { value -> onDisplay { it.copy(hideControls = value) } },
             "lyrics_hide_controls", "Ẩn sau 3 giây không thao tác; nhấn nút dưới cùng để hiện lại"
    }
}

@Composable private fun LyricsToggle(title: String, checked: Boolean, onChange: (Boolean) -> Unit, tag: String,
    summary: String? = null, enabled: Boolean = true) {
    val ink = MiuixTheme.colorScheme.onSurface
    Row(Modifier.fillMaxWidth().heightIn(min = 56.dp).testTag(tag).clickable(enabled = enabled, role = Role.Switch) { onChange(!checked) }
        .semantics { toggleableState = if (checked) ToggleableState.On else ToggleableState.Off; if (!enabled) disabled() }
        .padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f).padding(end = 10.dp)) {
            Text(title, fontSize = 16.sp, color = ink)
            if (summary != null) Text(summary, fontSize = 12.sp, lineHeight = 18.sp,
                color = ink.copy(alpha = .6f), modifier = Modifier.padding(top = 3.dp))
        }
        Switch(checked = checked, onCheckedChange = onChange, enabled = enabled)
    }
}
