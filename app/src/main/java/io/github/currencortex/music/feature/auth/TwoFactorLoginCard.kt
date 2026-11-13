package io.github.currencortex.music.feature.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.auth.LoginChallenge
import io.github.currencortex.music.ui.component.nativeui.*
import kotlinx.coroutines.delay
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun TwoFactorLoginCard(challenge: LoginChallenge, loading: Boolean, error: String?,
    onEdit: () -> Unit, onVerify: (String) -> Unit, onCancel: () -> Unit) {
    var recovery by remember(challenge) { mutableStateOf(false) }
    var code by remember(challenge) { mutableStateOf("") }
    var remaining by remember(challenge) { mutableLongStateOf(challenge.remainingSeconds) }
    val focus = remember { FocusRequester() }
    val question = challenge.securityQuestion
    val canSubmit = !loading && remaining > 0 && if (recovery || question != null) code.isNotBlank() else code.length == 6
    fun submit() { if (canSubmit) { onVerify(code); code = "" } }
    BackHandler { onCancel() }
    LaunchedEffect(challenge) {
        withFrameNanos { }; focus.requestFocus()
        while (true) {
            remaining = challenge.remainingSeconds
            if (remaining <= 0) break
            delay(1000)
        }
    }
    SettingsGroup(Modifier.fillMaxWidth().testTag("login_two_factor")) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(if (question != null) "Câu hỏi bảo mật" else "Xác minh hai bước", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text("Vui lòng hoàn tất bước xác minh thứ hai để tiếp tục đăng nhập.", fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            if (question == null) SettingsSegmentedControl(listOf("Ứng dụng xác thực", "Mã khôi phục"), if (recovery) 1 else 0, {
                recovery = it == 1; code = ""; onEdit()
            }, enabled = !loading, tag = "login_two_factor_method")
             Text(question ?: if (recovery) "Nhập mã khôi phục dùng một lần đã lưu khi bật tính năng xác minh hai bước." else "Mở ứng dụng xác thực và nhập mã xác minh gồm 6 chữ số hiện tại.",
                fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            TextField(code, { value ->
                code = if (recovery || question != null) value.take(128) else value.filter { it in '0'..'9' }.take(6)
                onEdit()
            }, singleLine = true, enabled = !loading && remaining > 0,
                 label = if (question != null) "Câu trả lời bảo mật" else if (recovery) "Mã khôi phục" else "Mã xác minh gồm 6 chữ số",
                visualTransformation = if (recovery || question != null) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(keyboardType = if (question != null) KeyboardType.Password else if (recovery) KeyboardType.Ascii else KeyboardType.Number,
                    imeAction = ImeAction.Done, autoCorrectEnabled = false),
                keyboardActions = KeyboardActions(onDone = { submit() }),
                modifier = Modifier.fillMaxWidth().focusRequester(focus).testTag("login_two_factor_code"))
            error?.let { Text(it, fontSize = 13.sp, color = MiuixTheme.colorScheme.error,
                modifier = Modifier.testTag("login_two_factor_error")) }
             Text(if (remaining > 0) "Thời hạn xác minh ${remaining / 60}:${(remaining % 60).toString().padStart(2, '0')}"
                 else "Xác minh đã hết hạn, vui lòng đăng nhập lại", fontSize = 12.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary, modifier = Modifier.testTag("login_two_factor_expiry"))
             GlassActionButton(if (loading) "Đang xác minh…" else "Xác minh và đăng nhập", { submit() },
                enabled = canSubmit, primary = true, modifier = Modifier.testTag("login_two_factor_submit"))
             GlassActionButton("Đăng nhập lại", onCancel, modifier = Modifier.testTag("login_two_factor_cancel"))
        }
    }
}
