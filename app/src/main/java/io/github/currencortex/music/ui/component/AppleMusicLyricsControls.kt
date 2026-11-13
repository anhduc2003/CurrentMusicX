package io.github.currencortex.music.ui.component

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.settings.AppleMusicLyricsOptions
import io.github.currencortex.music.data.settings.LyricsDisplayOptions
import io.github.currencortex.music.data.settings.LyricsTypography
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.util.Locale
import kotlin.math.roundToInt

/** Each completed gesture commits only its own field, through the shared settings repository. */
@Composable internal fun AppleMusicLyricsControls(display: LyricsDisplayOptions,
    onDisplay: ((LyricsDisplayOptions) -> LyricsDisplayOptions) -> Unit, modifier: Modifier = Modifier) {
    val options = display.appleMusic.normalized()
    val ink = MiuixTheme.colorScheme.onSurface
    Column(modifier.fillMaxWidth().testTag("apple_lyrics_controls")) {
        Row(Modifier.fillMaxWidth().padding(top = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("Hiệu ứng Apple Music", fontSize = 16.sp, color = ink, modifier = Modifier.weight(1f))
            Text("Đặt lại thông số", fontSize = 13.sp, color = MiuixTheme.colorScheme.primary,
                modifier = Modifier.testTag("apple_lyrics_reset").clickable(role = Role.Button) {
                    onDisplay { it.copy(appleMusic = AppleMusicLyricsOptions()) }
                }.padding(start = 12.dp, top = 14.dp, bottom = 14.dp))
        }
        LyricsParameterSlider("Khoảng cách lời bài hát", options.lineSpacing, AppleMusicLyricsOptions.LINE_SPACING_RANGE,
            "apple_lyrics_spacing", "Điều chỉnh khoảng trống giữa hai dòng lời bài hát", { "${(it * 100).roundToInt()}%" }) { value ->
            onDisplay { it.copy(appleMusic = it.appleMusic.copy(lineSpacing = value)) }
        }
        LyricsParameterSlider("Chiều cao dòng khi ngắt dòng", options.lineHeight, AppleMusicLyricsOptions.LINE_HEIGHT_RANGE,
            "apple_lyrics_line_height", "Điều chỉnh khoảng cách giữa các dòng sau khi lời bài hát được ngắt dòng", ::multiplier) { value ->
            onDisplay { it.copy(appleMusic = it.appleMusic.copy(lineHeight = value)) }
        }
        LyricsParameterSlider("Tốc độ hoạt ảnh", options.animationSpeed, AppleMusicLyricsOptions.ANIMATION_SPEED_RANGE,
            "apple_lyrics_speed", "Giá trị càng lớn thì tốc độ càng nhanh; tốc độ bài hát và đồng bộ từng từ không thay đổi", ::multiplier) { value ->
            onDisplay { it.copy(appleMusic = it.appleMusic.copy(animationSpeed = value)) }
        }
        AppleEffectToggle("Quầng sáng lời bài hát", display.glow, "lyrics_glow") { enabled ->
            onDisplay { it.copy(glow = enabled) }
        }
        if (display.glow) LyricsParameterSlider("Độ sáng quầng sáng", options.glowIntensity, AppleMusicLyricsOptions.GLOW_INTENSITY_RANGE,
            "apple_lyrics_glow", "Điều chỉnh quầng sáng ở các nốt ngân và đoạn nhấn, không làm thay đổi độ sáng chữ", { "${(it * 100).roundToInt()}%" }) { value ->
            onDisplay { it.copy(appleMusic = it.appleMusic.copy(glowIntensity = value)) }
        }
        if (Build.VERSION.SDK_INT >= 31) {
            AppleEffectToggle("Làm mờ chế độ xem lời bài hát", display.blur, "lyrics_blur") { enabled ->
                onDisplay { it.copy(blur = enabled) }
            }
            if (display.blur) LyricsParameterSlider("Cường độ làm mờ", options.blurStrength, AppleMusicLyricsOptions.BLUR_STRENGTH_RANGE,
                "apple_lyrics_blur", "Càng xa câu hiện tại thì càng mờ; 0% là hiển thị rõ nét",
                { "${(it * 100).roundToInt()}%" }) { value ->
                onDisplay { it.copy(appleMusic = it.appleMusic.copy(blurStrength = value)) }
            }
        }
    }
}

@Composable internal fun LyricsSizeSlider(value: Float, onChange: (Float) -> Unit) {
    LyricsParameterSlider("Cỡ chữ", LyricsTypography.normalize(value), LyricsTypography.MIN_SIZE..LyricsTypography.MAX_SIZE,
        "lyrics_font_size", "Đồng bộ với cỡ chữ lời bài hát trong trình phát", { it.roundToInt().toString() }, step = 1f, onCommit = onChange)
}

@Composable private fun LyricsParameterSlider(title: String, value: Float, range: ClosedFloatingPointRange<Float>,
    tag: String, summary: String, format: (Float) -> String, enabled: Boolean = true, step: Float = .05f,
    onCommit: (Float) -> Unit) {
    var preview by remember(value) { mutableFloatStateOf(value) }
    val ink = MiuixTheme.colorScheme.onSurface
    fun rounded(target: Float) = ((target / step).roundToInt() * step).coerceIn(range.start, range.endInclusive)
    Column(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(title, fontSize = 16.sp, color = ink.copy(alpha = if (enabled) 1f else .45f), modifier = Modifier.weight(1f))
            Text(format(preview), fontSize = 14.sp, color = ink.copy(alpha = if (enabled) .75f else .4f))
        }
        Slider(value = preview, onValueChange = { if (it.isFinite()) preview = rounded(it) },
            onValueChangeFinished = { onCommit(preview) }, valueRange = range, enabled = enabled,
            modifier = Modifier.testTag(tag).semantics {
                contentDescription = title
                stateDescription = format(preview)
                if (!enabled) disabled()
                setProgress { target ->
                    if (!enabled || !target.isFinite()) false else {
                        preview = rounded(target); onCommit(preview); true
                    }
                }
            })
        Text(summary, fontSize = 12.sp, lineHeight = 18.sp, color = ink.copy(alpha = .6f))
    }
}

@Composable private fun AppleEffectToggle(title: String, checked: Boolean, tag: String, enabled: Boolean = true,
    onChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().heightIn(min = 56.dp).testTag(tag)
        .clickable(enabled = enabled, role = Role.Switch) { onChange(!checked) }
        .semantics { toggleableState = if (checked) ToggleableState.On else ToggleableState.Off; if (!enabled) disabled() }
        .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontSize = 16.sp, color = MiuixTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f).padding(end = 12.dp))
        Switch(checked = checked, onCheckedChange = onChange, enabled = enabled)
    }
}

private fun multiplier(value: Float) = String.format(Locale.ROOT, "%.2f×", value)
