package io.github.currencortex.music.feature.lyrics.desktop

import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.ui.component.MusicDialog
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** A one-time, optional shortcut using the same activation guard as desktop settings. */
@Composable internal fun DesktopLyricsRecommendation(container: AppContainer, eligible: Boolean) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val offered by produceState(false, container) { value = container.desktopLyrics.shouldRecommend() }
    val options by container.desktopLyrics.state.collectAsStateWithLifecycle()
    var dismissed by rememberSaveable { mutableStateOf(false) }
    var pending by rememberSaveable { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val activation = remember(container) { DesktopLyricsActivation(scope, { Settings.canDrawOverlays(context) },
        { container.playerController.connect() }, { enabled -> container.desktopLyrics.edit { it.copy(enabled = enabled) } }) }
    val action by activation.state.collectAsStateWithLifecycle()
    DisposableEffect(activation) { onDispose { activation.close() } }
    LaunchedEffect(options.enabled) { if (options.enabled) dismissed = true }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val requested = pending; pending = false
        if (requested && Settings.canDrawOverlays(context)) activation.request(true)
        else if (requested) error = "Chưa được cấp quyền cửa sổ nổi, bạn có thể bật quyền này sau trong phần cài đặt。"
    }
    fun markSeen() { scope.launch { container.desktopLyrics.markRecommendationSeen() } }
    fun dismiss() { dismissed = true; pending = false; activation.request(false); markSeen() }
    if (eligible && offered && !dismissed && !options.enabled) {
         MusicDialog("Dùng thử Đảo linh động lời bài hát trên màn hình chính", onDismiss = ::dismiss) {
             Text("Nổi ở đầu màn hình, bạn vẫn có thể xem lời bài hát khi rời ứng dụng。", Modifier.testTag("desktop_recommendation"), fontSize = 15.sp)
             Text("Mặc định, ứng dụng hiển thị FPS; nhấn vào Đảo linh động để chuyển đổi giữa lời bài hát và tốc độ khung hình。", fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            (error ?: action.error)?.let { Text(it, fontSize = 13.sp, color = MiuixTheme.colorScheme.error) }
            MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
             MusicDialogButton("Để sau", onClick = ::dismiss, modifier = Modifier.weight(1f).testTag("desktop_recommendation_skip"))
             MusicDialogButton(if (action.connecting) "Đang bật…" else "Bật nhanh", onClick = {
                error = null; markSeen()
                if (Settings.canDrawOverlays(context)) activation.request(true)
                else {
                    pending = true
                    try { permission.launch(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        "package:${context.packageName}".toUri())) }
                     catch (_: RuntimeException) { pending = false; error = "Không thể mở trang cấp quyền, vui lòng cho phép cửa sổ nổi trong phần cài đặt hệ thống。" }
                }
            }, enabled = !action.connecting && !pending, modifier = Modifier.weight(1f).testTag("desktop_recommendation_enable"))
            }
        }
    }
}
