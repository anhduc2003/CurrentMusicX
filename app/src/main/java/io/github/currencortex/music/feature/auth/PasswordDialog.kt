package io.github.currencortex.music.feature.auth

import android.os.SystemClock
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.MusicDialogButton
import kotlinx.coroutines.delay
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** One transient form for public recovery and authenticated password changes. No secrets in saved state. */
@Composable internal fun PasswordDialog(recovery: Boolean, busy: Boolean, codeUntil: Long,
    onDismiss: () -> Unit, onSendCode: (String) -> Unit,
    onSubmit: (email: String, credential: String, password: String, usingCode: Boolean) -> Unit) {
    var usingCode by remember { mutableStateOf(recovery) }
    var email by remember { mutableStateOf("") }
    var credential by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var now by remember { mutableLongStateOf(SystemClock.elapsedRealtime()) }
    LaunchedEffect(codeUntil) {
        now = SystemClock.elapsedRealtime()
        while (now < codeUntil) { delay(1000); now = SystemClock.elapsedRealtime() }
    }
    SettingsUiScope {
    MusicDialog(if (recovery) "Khôi phục mật khẩu" else "Đổi mật khẩu", { if (!busy) onDismiss() }) {
        Column(Modifier.fillMaxWidth().heightIn(max = 480.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (!recovery) SettingsSegmentedControl(listOf("Xác minh mật khẩu hiện tại", "Xác minh qua email"), if (usingCode) 1 else 0, {
            usingCode = it == 1; credential = ""
        }, enabled = !busy, tag = "password_verification",
            itemTag = { if (it == 1) "password_email_mode" else "password_old_mode" })
        if (recovery) TextField(email, { email = it }, label = "Email đã đăng ký", singleLine = true,
            enabled = !busy, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().testTag("password_email"))
        if (usingCode) {
            Text(if (recovery) "Mã xác minh có hiệu lực trong 5 phút. Sau khi đặt lại, bạn cần đăng nhập lại và các thiết bị khác sẽ đăng xuất."
                else "Mã xác minh đã được gửi đến email liên kết với tài khoản hiện tại. Sau khi đổi, thiết bị này vẫn đăng nhập nhưng các thiết bị khác sẽ đăng xuất.",
                fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
TextField(credential, { credential = it }, label = "Xác minh qua email mã", singleLine = true, enabled = !busy,                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier.weight(1f).testTag("password_code"))
                GlassActionButton(if (now < codeUntil) "${(codeUntil - now + 999) / 1000}s" else "Lấy mã xác minh",
                    onClick = { onSendCode(email.trim()) },
                    enabled = !busy && now >= codeUntil && (!recovery || email.isNotBlank()),
                    modifier = Modifier.testTag("password_send_code"), fillWidth = false)
            }
        } else TextField(credential, { credential = it }, label = "Mật khẩu hiện tại", singleLine = true, enabled = !busy,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().testTag("password_old"))
        TextField(password, { password = it }, label = "Mật khẩu mới (6–64 ký tự)", singleLine = true, enabled = !busy,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().testTag("password_new"))
        TextField(confirm, { confirm = it }, label = "Nhập lại mật khẩu mới", singleLine = true, enabled = !busy,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth().testTag("password_confirm"))
        if (confirm.isNotEmpty() && confirm != password) Text("Hai lần nhập mật khẩu không khớp", fontSize = 12.sp,
            color = MiuixTheme.colorScheme.error, modifier = Modifier.testTag("password_mismatch"))
        MusicDialogButton(if (busy) "Đang xử lý…" else if (recovery) "Đặt lại mật khẩu" else "Cập nhật mật khẩu",
            onClick = { onSubmit(email.trim(), credential, password, usingCode) },
            enabled = !busy && (!recovery || email.isNotBlank()) && credential.isNotBlank() &&
                password.length in 6..64 && password == confirm,
            modifier = Modifier.testTag("password_submit"))
        }
    }
}
}
