// SPDX-License-Identifier: GPL-3.0-only
// ThemeView section order, PreviewChoiceGroup, palette, font and text-scale layouts ported
// from chen08209's upstream project lib/views/theme.dart @ 7fd417fb9a9a277c6a9339a8ac87f907df7c25f7.
package io.github.currencortex.music.feature.settings

import android.os.Build
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.settings.*
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.theme.rememberAppearanceColors
import kotlinx.coroutines.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

@Composable internal fun AppearanceSettingsPage(viewModel: SettingsViewModel, onBack: () -> Unit,
    onOpenScale: () -> Unit, onPreviewLaunch: () -> Unit, onOpenFont: () -> Unit) {
    val config by viewModel.settings.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val fontLabel by produceState("Mặc định", config.customFont) {
        value = withContext(Dispatchers.IO) {
            io.github.currencortex.music.ui.theme.appearanceFontLabel(context, config.customFont)
        }
    }
    val light = rememberAppearanceColors(config, false)
    val dark = rememberAppearanceColors(config, true)
    val standardDark = rememberAppearanceColors(config.copy(pureBlack = false), true)
    val black = rememberAppearanceColors(config.copy(pureBlack = true), true)
    val colors = MiuixTheme.colorScheme
    val glass = config.liquidGlass && Build.VERSION.SDK_INT >= 33
    SettingsUiScope {
        SettingsPage("Giao diện", "appearance_screen", onBack, padding = PaddingValues(bottom = 32.dp)) {
            item("live_preview") { AppearanceLivePreview(config) }
            item("theme_mode") {
                SettingsPreviewChoiceGroup("Chế độ giao diện", MusicIcons.Sun, listOf("Tự động", "Sáng", "Tối"),
                    config.themeMode.ordinal, { index -> viewModel.edit { it.copy(themeMode = ThemeMode.entries[index]) } }, "appearance_theme") { index ->
                    AppearanceMiniThumb(if (index == 2) dark else light, config.floatingBar, glass, split = if (index == 0) dark else null)
                }
            }
            item("pure_black") {
                SettingsPreviewChoiceGroup("Chế độ đen tuyền", MusicIcons.PureBlack, listOf("Tiêu chuẩn", "Đen tuyền"),
                    if (config.pureBlack) 1 else 0, { index -> viewModel.edit { it.copy(pureBlack = index == 1) } }, "appearance_black") { index ->
                    AppearanceMiniThumb(if (index == 0) standardDark else black, config.floatingBar, glass)
                }
            }
            item("primary_color") { AppearancePalette(config, viewModel) }
            item("liquid_glass") {
                SettingsPreviewChoiceGroup("Kính lỏng", MusicIcons.Drop, listOf("Tiêu chuẩn", "Kính lỏng"),
                    if (config.liquidGlass) 1 else 0, { index -> viewModel.edit { it.copy(liquidGlass = index == 1) } },
                    "setting_liquid", enabled = { index -> index == 0 || Build.VERSION.SDK_INT >= 33 }) { index ->
                    AppearanceMiniThumb(colors, true, index == 1, selected = 1)
                }
                if (Build.VERSION.SDK_INT < 33) SettingsHint("Hiệu ứng kính lỏng yêu cầu Android 13 trở lên")
            }
            item("launch_animation") {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SettingsPreviewChoiceGroup("Hoạt ảnh mở ứng dụng", MusicIcons.Play, LaunchStyle.entries.map { it.label },
                        config.launchStyle.ordinal, { index -> viewModel.edit { it.copy(launchStyle = LaunchStyle.entries[index]) } }, "setting_launch_style") { index ->
                        AppearanceLaunchThumb(LaunchStyle.entries[index], config)
                    }
                    SettingsGroup(Modifier.padding(horizontal = 16.dp)) {
                        SettingsPreference("Xem trước hoạt ảnh mở ứng dụng", "Xem hiệu ứng kiểu hiện tại và tên người dùng", Modifier.testTag("setting_launch_preview"),
                            enabled = config.launchStyle != LaunchStyle.NONE, onClick = onPreviewLaunch)
                    }
                }
            }
            item("player_cover") {
                SettingsPreviewChoiceGroup("Ảnh bìa trình phát", MusicIcons.Image, PlayerCoverStyle.entries.map { it.label },
                    config.playerCoverStyle.ordinal, { index -> viewModel.edit {
                        it.copy(playerCoverStyle = PlayerCoverStyle.entries[index])
                    } }, "appearance_player_cover") { index -> AppearancePlayerCoverThumb(PlayerCoverStyle.entries[index]) }
            }
            item("font_family") {
                AppearanceSection("Phông chữ", MusicIcons.Font) {
                    val source = rememberPageNavigationSource("Phông chữ", fontLabel, MusicIcons.Font)
                    SettingsPreference(fontLabel, modifier = Modifier.testTag("setting_custom_font").then(source.modifier),
                        onClick = { source.open(onOpenFont) }, leading = {
                            Text("Aa", fontSize = 17.sp, lineHeight = 22.sp, fontWeight = FontWeight.SemiBold)
                        }, trailing = { Icon(MusicIcons.Forward, null, Modifier.size(20.dp), tint = colors.onSurfaceVariantSummary) })
                }
            }
            item("text_scale") { AppearanceTextScale(config, viewModel) }
            item("interface_effects") {
                AppearanceSection("Hiệu ứng giao diện", MusicIcons.Blur) {
                    SettingsSwitch("Làm mờ", config.blur, { value -> viewModel.edit { it.copy(blur = value) } },
                        enabled = Build.VERSION.SDK_INT >= 31, summary = when {
                            Build.VERSION.SDK_INT >= 33 -> "Làm mờ nền thanh điều hướng nổi và các trang bị che trong quá trình chuyển cảnh"
                            Build.VERSION.SDK_INT >= 31 -> "Làm mờ các trang bị che trong quá trình chuyển cảnh"
                            else -> "Yêu cầu Android 12 trở lên"
                        },
                        modifier = Modifier.testTag("setting_blur"))
                    SettingsSwitch("Hiệu ứng ánh sáng nhịp theo nhạc ở thanh điều hướng", config.navigationGlow, { value -> viewModel.edit { it.copy(navigationGlow = value) } },
                        summary = "Sương màu từ ảnh bìa nhẹ nhàng chuyển động theo nhạc", modifier = Modifier.testTag("setting_navigation_glow"))
                    SettingsSwitch("Cử chỉ quay lại dự đoán", config.predictiveBack, { value -> viewModel.edit { it.copy(predictiveBack = value) } },
                        enabled = Build.VERSION.SDK_INT >= 34, modifier = Modifier.testTag("setting_predictive"))
                }
            }
            item("display_scale") {
                AppearanceSection("Tỷ lệ hiển thị", MusicIcons.Scale) {
                    var draft by remember(config.uiScale) { mutableFloatStateOf(config.uiScale) }
                    SettingsPreference("Tỷ lệ hiển thị", "Điều chỉnh tỷ lệ tổng thể của giao diện", onClick = onOpenScale,
                        trailing = { Text("${(config.uiScale * 100).roundToInt()}%", color = colors.onSurfaceVariantSummary) })
                    // Keep the slider outside the header's button semantics and tap target.
                    Box(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 12.dp)) {
                            GlassSlider(draft, { draft = it }, onValueChangeFinished = { viewModel.edit { it.copy(uiScale = draft) } },
                                valueRange = AppearanceSettings.MIN_SCALE..AppearanceSettings.MAX_SCALE, showKeyPoints = true,
                                keyPoints = listOf(.8f, .9f, 1f, 1.1f, 1.2f), magnetThreshold = .01f,
                                modifier = Modifier.testTag("setting_scale").semantics { setProgress { value ->
                                    draft = value.coerceIn(AppearanceSettings.MIN_SCALE, AppearanceSettings.MAX_SCALE)
                                    viewModel.edit { it.copy(uiScale = draft) }; true
                                } })
                    }
                }
            }
            item("refresh_rate") {
                val display by rememberDisplayRefreshStatus()
                AppearanceSection("Tần số quét", MusicIcons.Update) {
                    SettingsPreference("Tần số quét ứng dụng", "Liên tục yêu cầu tần số quét đã chọn; chế độ tiết kiệm pin và kiểm soát nhiệt của hệ thống vẫn có thể giới hạn, tần số quét cao sẽ tiêu tốn nhiều pin hơn.",
                        Modifier.testTag("setting_refresh_rate"), footer = {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                RefreshRateSlider(config.refreshRate, display.supported) { option -> viewModel.edit { it.copy(refreshRate = option) } }
                                Text("Tần số quét hiện tại: ${display.current.roundToInt()} Hz · Màn hình hỗ trợ: ${display.supported.joinToString(" / ")} Hz",
                                    Modifier.testTag("refresh_rate_actual"), fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                            }
                        })
                }
            }
        }
    }
}

@Composable private fun AppearanceSection(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable ColumnScope.() -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SettingsInfoHeader(title, icon)
        SettingsGroup(Modifier.padding(horizontal = 16.dp), content)
    }
}

@Composable private fun AppearancePalette(config: AppearanceSettings, vm: SettingsViewModel) {
    var showEditor by remember { mutableStateOf(false) }
    var editing by remember { mutableStateOf<Long?>(null) }
    var showReset by remember { mutableStateOf(false) }
    val colors = MiuixTheme.colorScheme
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SettingsInfoHeader("Màu chủ đề", MusicIcons.Palette, actions = {
            SettingsFadingReset(config.monet || config.primaryColors != DefaultThemeColors || config.primaryColor != DefaultThemeColors.first() || config.colorStyle != ThemeColorStyle.VIVID,
                { showReset = true }, "Đặt lại màu chủ đề", Modifier.testTag("appearance_color_reset"))
        })
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AppearanceColorSwatches(config,
                onSelect = { color -> vm.edit { it.copy(monet = color == null, primaryColor = color ?: it.primaryColor) } },
                onEdit = { color -> editing = color; showEditor = true },
                onAdd = { editing = null; showEditor = true })
            SettingsGroup {
                SettingsOptionsRow("Kiểu phối màu", config.colorStyle.label, ThemeColorStyle.entries.map { style ->
                    SettingsOption("appearance_color_style_${style.name}", style.label, style == config.colorStyle,
                        onSelect = { vm.edit { it.copy(colorStyle = style) } })
                }, Modifier.testTag("appearance_color_style"))
            }
        }
    }
    if (showEditor) {
        AppearanceColorSheet(config, editing ?: config.primaryColor, onClosed = { showEditor = false },
            onConfirmed = { value ->
                vm.edit { it.copy(monet = false, primaryColor = value,
                    primaryColors = (if (editing == null) it.primaryColors + value else it.primaryColors.map { old -> if (old == editing) value else old }).distinct()) }
            }, onDeleted = if (editing == null) null else { {
                vm.edit { val palette = it.primaryColors.filterNot { color -> color == editing }.ifEmpty { DefaultThemeColors }
                    it.copy(primaryColors = palette, primaryColor = if (it.primaryColor == editing) palette.first() else it.primaryColor) }
            } })
    }
    if (showReset) MusicDialog("Đặt lại màu chủ đề?", { showReset = false }) {
        Text("Khôi phục màu sắc và kiểu phối màu mặc định.")
        MusicDialogButton("Đặt lại", onClick = { vm.edit { it.copy(monet = false, primaryColor = DefaultThemeColors.first(), primaryColors = DefaultThemeColors, colorStyle = ThemeColorStyle.VIVID) }; showReset = false },
            modifier = Modifier.testTag("appearance_color_reset_confirm"))
    }
}

@Composable private fun AppearanceTextScale(config: AppearanceSettings, vm: SettingsViewModel) {
    var draft by remember(config.textScale, config.customTextScale) { mutableFloatStateOf(config.textScale) }
    val systemScale = LocalConfiguration.current.fontScale.coerceIn(.8f, 1.4f)
    val value = if (config.customTextScale) draft else systemScale
    val colors = MiuixTheme.colorScheme
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
         SettingsInfoHeader("Cỡ chữ", MusicIcons.TextSize, actions = {
             SettingsFadingReset(config.customTextScale && config.textScale != 1f, { vm.edit { it.copy(textScale = 1f) } }, "Đặt lại cỡ chữ",
                Modifier.testTag("appearance_text_scale_reset"))
        })
        Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
             SettingsSegmentedControl(listOf("Theo hệ thống", "Tùy chỉnh"), if (config.customTextScale) 1 else 0,
                { index -> vm.edit { it.copy(customTextScale = index == 1) } }, tag = "appearance_text_scale_mode")
            SettingsGroup {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(Modifier.fillMaxWidth().animateContentSize(tween(200, easing = CubicBezierEasing(.215f, .61f, .355f, 1f))), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        val density = LocalDensity.current
                        CompositionLocalProvider(LocalDensity provides Density(density.density, value)) {
                            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("Aa", fontSize = 20.sp, lineHeight = 25.sp, fontWeight = FontWeight.SemiBold)
                                 Text("Văn bản trong ứng dụng sẽ hiển thị với cỡ chữ này", fontSize = 15.sp, lineHeight = 20.sp, color = colors.onSurfaceVariantSummary)
                            }
                        }
                        Text("${(value * 100).roundToInt()}%", Modifier.clip(CircleShape).background(colors.surfaceContainerHigh).padding(horizontal = 10.dp, vertical = 4.dp),
                            fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold, color = colors.onSurfaceVariantSummary)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("A", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                        GlassSlider(value, { draft = it }, enabled = config.customTextScale, valueRange = .8f..1.4f, steps = 11, showKeyPoints = true,
                            keyPoints = (0..12).map { .8f + it * .05f }, magnetThreshold = .015f,
                            onValueChangeFinished = { vm.edit { it.copy(textScale = draft) } },
                            modifier = Modifier.weight(1f).testTag("appearance_text_scale_slider").semantics { setProgress { target ->
                                if (config.customTextScale) { draft = target.coerceIn(.8f, 1.4f); vm.edit { it.copy(textScale = draft) }; true } else false
                            } })
                        Text("A", fontSize = 22.sp, color = colors.onSurfaceVariantSummary)
                    }
                }
            }
        }
    }
}
