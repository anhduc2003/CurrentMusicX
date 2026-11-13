package io.github.currencortex.music.feature.settings

import io.github.currencortex.music.ui.component.nativeui.*

import io.github.currencortex.music.ui.component.nativeui.GlassSlider as Slider

import io.github.currencortex.music.ui.component.nativeui.SettingsChoiceButton

import io.github.currencortex.music.ui.component.nativeui.SettingsPage
import io.github.currencortex.music.ui.component.nativeui.SettingsUiScope
import io.github.currencortex.music.ui.component.nativeui.SettingsGroup as Card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.settings.*
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt
import java.util.Locale

@Composable fun EqualizerScreen(vm: EqualizerViewModel, onBack: (() -> Unit)? = null) {
    val settings by vm.settings.collectAsStateWithLifecycle()
    val runtime by vm.runtime.collectAsStateWithLifecycle()
    val soundPreferences by vm.soundPreferences.collectAsStateWithLifecycle()
    val hint = MiuixTheme.colorScheme.onSurfaceVariantSummary
    val accent = MiuixTheme.colorScheme.primary
    var preview by remember(settings.gains) { mutableStateOf(settings.gains) }
    val status = when {
        !runtime.supported -> "Thiết bị này không hỗ trợ bộ cân bằng âm thanh"
        runtime.error != null -> runtime.error!!
        soundPreferences.smartEnabled -> "Đang dùng điều chỉnh âm thanh thông minh; bên dưới là các thông số thủ công, hãy chuyển về thủ công sau khi điều chỉnh"
        !settings.enabled -> "Đã tắt, giữ nguyên các thông số bạn đã điều chỉnh"
        runtime.active -> "Đã kết nối với trình phát nhạc trên thiết bị này"
        else -> "Tự động có hiệu lực khi bắt đầu phát nhạc trên thiết bị này"
    }
    SettingsUiScope {
         SettingsPage("Bộ cân bằng âm thanh","equalizer_screen", onBack = onBack) {
            item { Card {
                SettingsSwitch("Bật bộ cân bằng âm thanh", settings.enabled, vm::enabled, summary = status,
                    enabled = runtime.supported, modifier = Modifier.testTag("equalizer_enabled"))
            } }
            item { SettingsSection("Cài đặt sẵn và đường cong", MusicIcons.Headphones) {
                SettingsOptionsRow("Cài đặt sẵn", settings.preset.label,
                    EqualizerPreset.entries.filter { it != EqualizerPreset.CUSTOM }.map { preset ->
                        SettingsOption("equalizer_preset_${preset.name.lowercase()}", preset.label, settings.preset == preset,
                            onSelect = { vm.preset(preset) })
                    }, Modifier.testTag("equalizer_selected"))
                SettingsDivider()
                SettingsPreference("Khôi phục mức phẳng", "Đưa các dải tần về 0 dB", Modifier.testTag("equalizer_reset"),
                    onClick = { vm.preset(EqualizerPreset.FLAT) }, enabled = settings.preset != EqualizerPreset.FLAT)
                Box(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Canvas(Modifier.fillMaxWidth().height(96.dp).testTag("equalizer_curve")) {
                    val inset = 10.dp.toPx()
                    val width = size.width - inset * 2
                    val height = size.height - inset * 2
                    listOf(0f, .5f, 1f).forEach { fraction ->
                        val y = inset + height * fraction
                        drawLine(hint.copy(alpha = .18f), Offset(inset, y), Offset(size.width - inset, y), 1.dp.toPx())
                    }
                    val path = Path()
                    preview.forEachIndexed { index, gain ->
                        val point = Offset(inset + width * index / 4f, inset + height * (1200 - gain) / 2400f)
                        if (index == 0) path.moveTo(point.x, point.y) else path.lineTo(point.x, point.y)
                        drawCircle(accent, 3.dp.toPx(), point)
                    }
                    drawPath(path, accent, style = Stroke(2.dp.toPx()))
                }
                }
            } }
            item { SettingsSection("Điều chỉnh dải tần", MusicIcons.Scale) { Column(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                EqualizerSettings.FREQUENCIES.forEachIndexed { index, frequency ->
                    Row(Modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(if (frequency >= 1000) String.format(Locale.ROOT, "%.1f kHz", frequency / 1000f) else "$frequency Hz",
                            Modifier.weight(1f), fontSize = 14.sp)
                        Text(String.format(Locale.ROOT, "%+.1f dB", preview[index] / 100f), color = hint, fontSize = 13.sp)
                    }
                    Slider(value = preview[index] / 100f, valueRange = -12f..12f,
                        enabled = settings.enabled && runtime.supported,
                        onValueChange = { value ->
                            val gain = value.roundToInt() * 100
                            preview = preview.toMutableList().also { it[index] = gain }
                            vm.previewBand(index, gain)
                        },
                        onValueChangeFinished = { vm.saveBand(index, preview[index]) },
                        modifier = Modifier.fillMaxWidth().testTag("equalizer_band_$index").semantics {
                            contentDescription = "$frequency Hz bộ cân bằng âm thanh"
                            setProgress { value ->
                                if (!settings.enabled || !runtime.supported) false else {
                                    val gain = value.coerceIn(-12f, 12f).roundToInt() * 100
                                    preview = preview.toMutableList().also { it[index] = gain }
                                    vm.saveBand(index, gain); true
                                }
                            }
                        })
                }
            } } }
        }
    }
}
