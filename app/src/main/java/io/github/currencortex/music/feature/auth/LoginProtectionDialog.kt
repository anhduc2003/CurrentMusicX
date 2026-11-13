package io.github.currencortex.music.feature.auth

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.network.*
import io.github.currencortex.music.data.auth.*
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.MusicDialogButton
import io.github.currencortex.music.ui.component.SettingsSwitch
import io.github.currencortex.music.ui.component.nativeui.*
import kotlinx.coroutines.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.theme.MiuixTheme

private fun Context.activity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.activity()
    else -> null
}
private fun <T> AppResult<T>.valueOrThrow(): T = when (this) {
    is AppResult.Success -> value
    is AppResult.Failure -> throw ApiException(kind, status, message)
}

/** Closing the form drops its scope, credentials, enrollment and recovery codes. */
@Composable internal fun LoginProtectionDialog(container: AppContainer, questionMode: Boolean, onDismiss: () -> Unit) {
    val repo = remember(container) { AccountSecurityRepository(container.apiClient, container.accountRepository) }
    val owner = remember(repo) { repo.owner() }
    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf<LoginProtectionStatus?>(null) }
    var setup by remember { mutableStateOf<AuthenticatorEnrollment?>(null) }
    var recovery by remember { mutableStateOf<RecoveryCodes?>(null) }
    var password by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var question by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var saved by remember { mutableStateOf(false) }
    var completed by remember { mutableStateOf(false) }
    var remaining by remember { mutableLongStateOf(0L) }
    val window = LocalContext.current.activity()?.window
    DisposableEffect(window) {
        val alreadySecure = window?.attributes?.flags?.and(WindowManager.LayoutParams.FLAG_SECURE) != 0
        window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        onDispose { if (!alreadySecure) window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE) }
    }
    val dismiss by rememberUpdatedState(onDismiss)
    LaunchedEffect(owner) {
        while (repo.isCurrent(owner)) { delay(500) }
        dismiss()
    }
    fun launch(block: suspend () -> Unit) {
        if (busy) return
        busy = true; error = null
        scope.launch {
            try { block() }
            catch (e: CancellationException) { throw e }
            catch (e: Exception) { error = (e as? ApiException)?.userMessage ?: "Thao tác thất bại, vui lòng thử lại" }
            finally { busy = false }
        }
    }
    fun load() = launch { status = repo.status(owner).valueOrThrow() }
    LaunchedEffect(owner) { load() }
    LaunchedEffect(setup) {
        while (setup != null) {
            remaining = setup?.remainingSeconds ?: 0
            if (remaining <= 0) { setup = null; code = ""; error = "Liên kết đã hết hạn, vui lòng bắt đầu lại"; break }
            delay(1000)
        }
    }
    fun close() {
        if (busy) return
        if (recovery != null && !saved) { error = "Mã khôi phục chỉ hiển thị một lần, vui lòng lưu và xác nhận trước"; return }
        onDismiss()
    }
    SettingsUiScope {
        MusicDialog(if (questionMode) "Câu hỏi bảo mật đăng nhập" else "Trình xác thực", ::close) {
            Column(Modifier.fillMaxWidth().heightIn(max = 480.dp).verticalScroll(rememberScrollState())
                .testTag("login_protection_form"), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                val current = status
                val enrollment = setup
                val codes = recovery
                when {
                    completed -> {
                        Text("Đã cập nhật cài đặt bảo mật, các thiết bị khác cần đăng nhập lại.")
                        MusicDialogButton("Hoàn tất", onClick = ::close)
                    }
                    codes != null -> {
                        Text("Đã bật trình xác thực. Mã khôi phục chỉ hiển thị một lần và mỗi mã chỉ dùng được một lần. Vui lòng lưu ở nơi an toàn.")
                        SelectionContainer { Text(codes.codes.joinToString("\n"), Modifier.testTag("security_recovery_codes")) }
                        SettingsSwitch("Tôi đã lưu mã khôi phục an toàn", saved, { saved = it })
                        MusicDialogButton("Hoàn tất", onClick = ::close, enabled = saved)
                    }
                    enrollment != null -> {
                        Text("Quét mã QR hoặc thêm khóa bên dưới theo cách thủ công trong trình xác thực. TOTP · SHA1 · 6 chữ số · 30 giây.")
                        val bitmap = remember(enrollment) {
                            val pixels = QRCodeWriter().encode(enrollment.uri, BarcodeFormat.QR_CODE, 512, 512,
                                mapOf(EncodeHintType.MARGIN to 4, EncodeHintType.CHARACTER_SET to "UTF-8"))
                            Bitmap.createBitmap(IntArray(512 * 512) { index ->
                                if (pixels[index % 512, index / 512]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
                            }, 512, 512, Bitmap.Config.ARGB_8888).asImageBitmap()
                        }
                        Image(bitmap, "Mã QR liên kết trình xác thực", Modifier.fillMaxWidth().height(200.dp))
                        SelectionContainer { Text(enrollment.secret, Modifier.testTag("security_totp_secret")) }
                        Text("Liên kết có hiệu lực ${remaining / 60}:${(remaining % 60).toString().padStart(2, '0')}")
                        TextField(code, { code = it.filter { char -> char in '0'..'9' }.take(6); error = null },
                            label = "Mã xác minh 6 chữ số", singleLine = true, enabled = !busy,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().testTag("security_totp_code"))
                        MusicDialogButton("Xác nhận bật", onClick = {
                            val submitted = code; code = ""
                            launch { recovery = repo.enable(enrollment, submitted).valueOrThrow(); setup = null }
                        }, enabled = !busy && code.length == 6 && remaining > 0, modifier = Modifier.testTag("security_enable_totp"))
                        MusicDialogButton("Liên kết lại", onClick = { setup = null; code = "" }, enabled = !busy)
                    }
                    current == null -> {
                        Text(if (busy) "Đang tải cài đặt bảo mật…" else "Không thể tải cài đặt bảo mật")
                        if (!busy) MusicDialogButton("Thử lại", onClick = ::load)
                    }
                    (questionMode && current.enabled) || (!questionMode && current.securityQuestionEnabled) -> {
                        Text(if (questionMode) "Trình xác thực đã được bật. Vui lòng tắt trình xác thực trước khi thiết lập câu hỏi bảo mật."
                            else "Câu hỏi bảo mật đăng nhập đã được bật. Vui lòng xác minh câu trả lời hiện tại và tắt câu hỏi bảo mật trước khi liên kết trình xác thực.")
                        MusicDialogButton("Quay lại", onClick = ::close)
                    }
                    else -> {
                        Text(if (questionMode) "Bạn chỉ có thể bật một trong hai: câu hỏi bảo mật hoặc trình xác thực. Để đổi câu hỏi, trước tiên hãy xác minh và tắt câu hỏi bảo mật hiện tại."
                            else if (current.enabled) "Trình xác thực đã được bật, còn ${current.recoveryCodesRemaining} mã khôi phục."
                            else "Sau khi xác nhận mật khẩu hiện tại, hãy quét mã QR bằng trình xác thực và nhập mã xác minh để hoàn tất liên kết.")
                        Text("Sau khi bật hoặc tắt, các thiết bị khác cần đăng nhập lại; thiết bị hiện tại vẫn giữ nguyên trạng thái đăng nhập.")
                         SecretField(password, { password = it; error = null }, "Mật khẩu hiện tại", "security_password", !busy)
                        if (questionMode) {
                            if (current.securityQuestionEnabled) Text(current.securityQuestion)
                             else TextField(question, { question = it.take(100); error = null }, label = "Câu hỏi tùy chỉnh (4～100 ký tự)",
                                singleLine = true, enabled = !busy, modifier = Modifier.fillMaxWidth().testTag("security_question"))
                             SecretField(answer, { answer = it.take(128); error = null }, "Câu trả lời bảo mật", "security_answer", !busy)
                            if (!current.securityQuestionEnabled) SecretField(confirm, { confirm = it.take(128); error = null },
                                 "Nhập lại câu trả lời", "security_answer_confirm", !busy)
                             MusicDialogButton(if (current.securityQuestionEnabled) "Xác minh và tắt câu hỏi bảo mật" else "Bật bảo mật đăng nhập", onClick = {
                                val pass = password; val response = answer; password = ""; answer = ""; confirm = ""
                                launch {
                                    if (current.securityQuestionEnabled) repo.disableQuestion(owner, pass, response).valueOrThrow()
                                    else repo.setQuestion(owner, pass, question, response).valueOrThrow()
                                    completed = true
                                }
                            }, enabled = !busy && password.isNotEmpty() && answer.isNotBlank() &&
                                (current.securityQuestionEnabled || (question.trim().length in 4..100 && answer.trim().length in 2..128 && answer == confirm)),
                                modifier = Modifier.testTag("security_question_submit"))
                        } else {
                            if (current.enabled) SecretField(code, { code = it.take(128); error = null },
                                 "Mã xác minh hoặc mã khôi phục", "security_disable_code", !busy)
                             MusicDialogButton(if (current.enabled) "Xác minh và tắt trình xác thực" else "Liên kết trình xác thực", onClick = {
                                val pass = password; val response = code; password = ""; code = ""
                                launch {
                                    if (current.enabled) { repo.disable(owner, pass, response).valueOrThrow(); completed = true }
                                    else { setup = repo.setup(owner, pass).valueOrThrow(); remaining = setup!!.remainingSeconds }
                                }
                            }, enabled = !busy && password.isNotEmpty() && (!current.enabled || code.isNotBlank()),
                                modifier = Modifier.testTag("security_totp_submit"))
                        }
                    }
                }
                error?.let { Text(it, color = MiuixTheme.colorScheme.error, modifier = Modifier.testTag("security_error")) }
                 if (busy) Text("Đang xử lý…")
            }
        }
    }
}

@Composable private fun SecretField(value: String, edit: (String) -> Unit, label: String, tag: String, enabled: Boolean) {
    TextField(value, edit, label = label, singleLine = true, enabled = enabled,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, autoCorrectEnabled = false),
        modifier = Modifier.fillMaxWidth().testTag(tag))
}
