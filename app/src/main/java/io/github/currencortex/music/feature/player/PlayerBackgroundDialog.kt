package io.github.currencortex.music.feature.player

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun PlayerBackgroundDialog(container: AppContainer, onDismiss: () -> Unit) {
    val config by container.settings.state.collectAsStateWithLifecycle()
    val queue by container.playbackQueue.state.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    var busy by remember { mutableStateOf(false) }
    // Miuix retains drag callbacks. Keep the state object stable across persisted updates.
    var blur by remember { mutableFloatStateOf(config.playerBackgroundBlur) }
    LaunchedEffect(config.playerBackgroundBlur) { blur = config.playerBackgroundBlur }
    val file = remember(config.playerBackground) { container.playerBackgrounds.file(config.playerBackground) }
    val playback by container.playerController.state.collectAsStateWithLifecycle()
    val backgroundSpeed = rememberPlayerFluidSpeed(container.dockAudioLevel, playback.playing,
        file == null && config.playerDynamicBackground, queue.current, playback.playbackSpeed)
    val canAdjustBlur = !busy && (file != null || !config.playerDynamicBackground)
    fun operation(action: suspend () -> Unit) {
        if (busy) return
        busy = true
        scope.launch {
            try { action() }
            catch (cancelled: CancellationException) { throw cancelled }
            catch (failure: Exception) { container.errorNotices.show(
                if (failure is IllegalArgumentException || failure is IllegalStateException)
                     failure.message ?: "Lưu ảnh nền thất bại, vui lòng thử lại" else "Không thể đọc ảnh nền, vui lòng chọn lại") }
            finally { busy = false }
        }
    }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) operation { container.playerBackgrounds.import(uri) }
    }
    fun saveBlur() {
        val value = blur
        scope.launch { container.settings.edit { it.copy(playerBackgroundBlur = value) } }
    }
     MusicDialog("Nền phát nhạc", { if (!busy) onDismiss() }) {
        Column(Modifier.fillMaxWidth().heightIn(max = 480.dp).verticalScroll(rememberScrollState())
            .testTag("player_background_settings"), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            PlayerBackdrop(queue.current?.cover.orEmpty(), Modifier.fillMaxWidth().height(140.dp)
                .clip(RoundedCornerShape(18.dp)).testTag("player_background_preview"), file, blur,
                dynamicBackground = config.playerDynamicBackground,
                motionSpeed = { backgroundSpeed.value })
             Text(if (busy) "Đang lưu ảnh…" else if (file != null) "Nền tùy chỉnh · Ảnh chỉ được lưu trên thiết bị này"
                 else if (config.playerBackground.isNotBlank()) "Ảnh không khả dụng, vui lòng chọn lại"
                 else if (config.playerDynamicBackground) "Nền chuyển động · Thay đổi theo nhịp điệu trực tiếp khi phát nhạc"
                 else "Ảnh bìa làm mờ · Nhạc và lời bài hát dùng chung nền dịu nhẹ", fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            MusicDialogActions(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                 listOf(false to "Ảnh bìa làm mờ", true to "Nền chuyển động").forEach { (dynamic, label) ->
                    val chosen = file == null && config.playerDynamicBackground == dynamic
                    MusicDialogButton(if (chosen) "✓ $label" else label, enabled = !busy && file == null,
                        colors = if (chosen) ButtonDefaults.textButtonColors(color = MiuixTheme.colorScheme.primary,
                            textColor = MiuixTheme.colorScheme.onPrimary) else ButtonDefaults.textButtonColors(),
                        modifier = Modifier.weight(1f).testTag(if (dynamic) "player_dynamic_background" else "player_frosted_background")
                            .semantics { selected = chosen },
                        onClick = { scope.launch { container.settings.edit { it.copy(playerDynamicBackground = dynamic) } } })
                }
            }
             if (file != null) Text("Sau khi khôi phục nền mặc định, bạn có thể chọn hiệu ứng ảnh bìa", fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            MusicDialogActions(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                 MusicDialogButton(if (file == null) "Chọn ảnh" else "Đổi ảnh",
                    colors = ButtonDefaults.textButtonColors(color = MiuixTheme.colorScheme.primary, textColor = MiuixTheme.colorScheme.onPrimary),
                    enabled = !busy, modifier = Modifier.weight(1f).testTag("player_background_choose"),
                    onClick = { picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) })
                 MusicDialogButton("Khôi phục mặc định", enabled = config.playerBackground.isNotBlank() && !busy,
                    modifier = Modifier.weight(1f).testTag("player_background_reset"),
                    onClick = { operation { container.playerBackgrounds.clear() } })
            }
             Text("Độ mờ ${blur.roundToInt()}%", fontSize = 15.sp)
            Slider(value = blur, onValueChange = { blur = it }, onValueChangeFinished = ::saveBlur,
                valueRange = 0f..100f, enabled = canAdjustBlur,
                modifier = Modifier.fillMaxWidth().testTag("player_background_blur").semantics {
                    if (!canAdjustBlur) disabled()
                    setProgress { target ->
                        if (!canAdjustBlur || !target.isFinite()) false else {
                            blur = target.coerceIn(0f, 100f); saveBlur(); true
                        }
                    }
                })
             MusicDialogButton("Xong", enabled = !busy, modifier = Modifier.fillMaxWidth(), onClick = {
                val value = blur
                operation { container.settings.edit { it.copy(playerBackgroundBlur = value) }; onDismiss() }
            })
        }
    }
}
