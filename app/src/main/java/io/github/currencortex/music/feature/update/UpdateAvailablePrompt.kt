package io.github.currencortex.music.feature.update

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.core.update.UpdatePromptState
import io.github.currencortex.music.ui.component.MusicDialogButton
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Automatic discovery asks first; only View opens the full update page. */
@Composable internal fun UpdateAvailablePrompt(prompt: UpdatePromptState,
    onIgnore: () -> Unit, onView: () -> Unit, onDismiss: () -> Unit) {
    UpdateOptionsDialog("Phát hiện phiên bản mới", onDismiss = { if (!prompt.saving) onDismiss() }, actions = {
        val colors = MiuixTheme.colorScheme
        MusicDialogButton(if (prompt.saving) "Đang bỏ qua…" else "Bỏ qua cập nhật", onIgnore,
            Modifier.weight(1f).testTag("update_prompt_ignore"), enabled = !prompt.saving, minHeight = 48.dp,
            colors = ButtonDefaults.textButtonColors(color = colors.onBackground.copy(alpha = .07f),
                textColor = colors.onSurfaceVariantSummary))
        MusicDialogButton("Xem", onView, Modifier.weight(1f).testTag("update_prompt_view"),
            enabled = !prompt.saving, minHeight = 48.dp,
            colors = ButtonDefaults.textButtonColors(color = colors.primary, textColor = colors.onPrimary))
    }) {
        Column(Modifier.fillMaxWidth().testTag("update_available_prompt"), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("CurrentMusic ${prompt.release.version} đã phát hành", fontSize = 17.sp)
            Text("Xem nội dung cập nhật và chọn có tải xuống hay không.", fontSize = 14.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            prompt.error?.let { Text(it, fontSize = 13.sp, color = MiuixTheme.colorScheme.error) }
        }
    }
}
