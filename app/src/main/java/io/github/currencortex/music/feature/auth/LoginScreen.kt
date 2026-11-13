package io.github.currencortex.music.feature.auth

import io.github.currencortex.music.R
import io.github.currencortex.music.ui.component.musicScrollPadding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import android.os.SystemClock
import kotlinx.coroutines.delay
import io.github.currencortex.music.data.auth.RegistrationInput

@Composable fun LoginScreen(vm: AuthViewModel, addingAccount: Boolean = false, onDone: () -> Unit = {},
    showTitle: Boolean = true, contentTopInset: androidx.compose.ui.unit.Dp = 0.dp, onBack: (() -> Unit)? = null) {
    val state by vm.state.collectAsStateWithLifecycle()
    val account by vm.account.collectAsStateWithLifecycle()
    val challenge by vm.loginChallenge.collectAsStateWithLifecycle()
    val context = androidx.compose.ui.platform.LocalContext.current
    DisposableEffect(vm) { onDispose { vm.dismissLogin() } }
    var username by rememberSaveable { mutableStateOf("") }
    // Password is intentionally not saved in SavedState or a persistent store.
    var password by remember { mutableStateOf("") }
    var register by rememberSaveable { mutableStateOf(false) }
    var emailLogin by rememberSaveable { mutableStateOf(false) }
    var phone by rememberSaveable { mutableStateOf(false) }
    var nickname by rememberSaveable { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var revealPassword by remember { mutableStateOf(false) }
    var recovering by remember { mutableStateOf(false) }
    val resetDeadline by vm.resetCodeUntil.collectAsStateWithLifecycle()
    LaunchedEffect(state.message) {
        if (state.message == "Đã đặt lại mật khẩu, hãy đăng nhập bằng mật khẩu mới") { recovering = false; password = ""; revealPassword = false }
    }
    val deadline by vm.codeUntil.collectAsStateWithLifecycle()
    val emailDeadline by vm.loginEmailCodeUntil.collectAsStateWithLifecycle()
    var now by remember { mutableLongStateOf(SystemClock.elapsedRealtime()) }
    LaunchedEffect(deadline, emailDeadline) {
        now = SystemClock.elapsedRealtime()
        while (now < maxOf(deadline, emailDeadline)) { delay(1000); now = SystemClock.elapsedRealtime() }
    }
    LaunchedEffect(state.message) { if (addingAccount && state.message in listOf("Đăng nhập thành công", "Đăng ký thành công")) onDone() }
    // Entering the page starts a clean attempt: a stale error must not greet the user.
    LaunchedEffect(Unit) { vm.clearMessage() }
    LaunchedEffect(state.message, state.failed, account.error) {
        if (challenge == null) (if (state.failed) state.message else account.error)?.let(vm.errorNotices::show)
    }
    // Editing any field clears the previous outcome, so the message never describes stale input.
    fun edit(block: () -> Unit) { block(); if (state.message != null) vm.clearMessage() }
    val colors = MiuixTheme.colorScheme
    val secondary = colors.onSurfaceVariantSummary
    val form: @Composable (androidx.compose.ui.unit.Dp) -> Unit = { topInset ->
    Column(Modifier.fillMaxSize().imePadding().verticalScroll(rememberScrollState())
        .padding(musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp))).testTag("login_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        if (challenge != null) TwoFactorLoginCard(requireNotNull(challenge), state.loading,
            state.message.takeIf { state.failed }, vm::clearMessage, vm::verifyLogin, vm::dismissLogin)
        else {
        account.account?.takeIf { !addingAccount }?.let {
            Text(it.nickname, fontSize = 24.sp)
            GlassActionButton("Đăng xuất", onClick = { vm.logout() }, enabled = !state.loading)
        } ?: run {
            val canLogin = !state.loading && if (emailLogin) contact.isNotBlank() && code.isNotBlank()
                else username.isNotBlank() && password.isNotBlank()
            val canRegister = username.isNotBlank() && password.length >= 6 && contact.isNotBlank() &&
                code.isNotBlank() && !state.loading
            fun submit() {
                if (emailLogin) vm.loginWithEmail(contact, code) else vm.login(username, password)
                password = ""; code = ""
            }
            SettingsGroup(Modifier.fillMaxWidth().testTag(if (register) "register_card" else "login_card")) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // The mode switch lives in the header so it stays visible without scrolling.
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(if (register) "Đăng ký CurrentMusic" else "Đăng nhập CurrentMusic", Modifier.weight(1f),
                            fontSize = 20.sp, fontWeight = FontWeight.Medium)
                        GlassActionButton(if (register) "Quay lại đăng nhập" else "Đăng ký", {
                            register = !register; password = ""; code = ""; contact = ""; revealPassword = false
                        }, Modifier.testTag("login_switch_mode"), enabled = !state.loading, fillWidth = false)
                    }
                    if (!register) SettingsSegmentedControl(listOf("Đăng nhập bằng mật khẩu", "Mã xác minh email"), if (emailLogin) 1 else 0, {
                        edit { emailLogin = it == 1; password = ""; code = ""; revealPassword = false }
                    }, enabled = !state.loading, tag = "login_method")
                    if (!register && emailLogin) {
                        Text("Đăng nhập bằng email đã liên kết.", fontSize = 13.sp, color = secondary)
                        TextField(contact, { edit { contact = it } }, label = "Email", singleLine = true,
                            enabled = !state.loading,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                            modifier = Modifier.fillMaxWidth().testTag("login_email"))
                        TextField(code, { edit { code = it.filter { char -> char in '0'..'9' }.take(6) } },
                            label = "Mã xác minh email", singleLine = true, enabled = !state.loading,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(onDone = { if (canLogin) submit() }),
                            modifier = Modifier.fillMaxWidth().testTag("login_email_code"))
                        GlassActionButton(if (emailDeadline > now) "Gửi lại sau ${(emailDeadline - now + 999) / 1000}s" else "Lấy mã đăng nhập",
                            { vm.loginEmailCode(contact) }, enabled = contact.isNotBlank() && !state.loading && now >= emailDeadline,
                            modifier = Modifier.testTag("login_email_send_code"))
                    } else {
                    Text(if (register) "Sau khi đăng ký, bạn có thể đồng bộ mục Yêu thích và danh sách phát." else "Có thể dùng tên người dùng hoặc số điện thoại.", fontSize = 13.sp, color = secondary)
                    // One label per field, keyboard hints and a reveal toggle replace the bare rows.
                    TextField(username, { edit { username = it } }, singleLine = true, label = "Tên người dùng",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next, autoCorrectEnabled = false),
                        modifier = Modifier.fillMaxWidth().testTag("login_username"))
                    TextField(password, { edit { password = it } }, singleLine = true, label = "Mật khẩu",
                        visualTransformation = if (revealPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = if (register) ImeAction.Next else ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { if (canLogin) submit() }),
                        trailingIcon = {
                            Box(Modifier.size(48.dp).clickable(role = Role.Button,
                                    interactionSource = remember { MutableInteractionSource() }, indication = null) { revealPassword = !revealPassword }
                                    .testTag("login_password_toggle"),
                                contentAlignment = Alignment.Center) {
                                Image(painterResource(if (revealPassword) R.drawable.auth_password_hidden else R.drawable.auth_password_visible),
                                    contentDescription = if (revealPassword) "Ẩn mật khẩu" else "Hiện mật khẩu",
                                    modifier = Modifier.size(20.dp), colorFilter = ColorFilter.tint(secondary))
                            }
                        },
                        modifier = Modifier.fillMaxWidth().testTag("login_password"))
                    }
                    if (!register) GlassActionButton("Quên mật khẩu?", onClick = { vm.clearMessage(); recovering = true }, fillWidth = false,
                        enabled = !state.loading, modifier = Modifier.testTag("forgot_password"))
                    // Registration needs a 6-character password; say so instead of only greying the button.
                    if (register && password.isNotEmpty() && password.length < 6)
                        Text("Mật khẩu phải có ít nhất 6 ký tự (hiện có ${password.length} ký tự)", fontSize = 12.sp,
                            color = colors.error, modifier = Modifier.testTag("register_password_hint"))
                    if (register) {
                        TextField(nickname, { edit { nickname = it } }, singleLine = true, label = "Biệt danh (không bắt buộc)",
                            modifier = Modifier.fillMaxWidth().testTag("register_nickname"))
                        SettingsSegmentedControl(listOf("Xác minh email", "Xác minh điện thoại"), if (phone) 1 else 0, {
                            phone = it == 1; contact = ""; code = ""
                        }, enabled = !state.loading, tag = "register_verification")
                        TextField(contact, { edit { contact = it } }, singleLine = true,
                            label = if (phone) "Số điện thoại" else "Email",
                            keyboardOptions = KeyboardOptions(keyboardType = if (phone) KeyboardType.Phone else KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth().testTag("register_contact"))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            TextField(code, { edit { code = it } }, singleLine = true, label = "Mã xác minh",
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(onDone = { if (canRegister) {
                                    vm.register(RegistrationInput(username, password, nickname, contact, code, phone)); password = ""; code = ""
                                } }),
                                modifier = Modifier.weight(1f).testTag("register_code"))
                            GlassActionButton(if (deadline > now) "${(deadline - now + 999) / 1000}s" else "Lấy mã xác minh",
                                onClick = { vm.code(contact, phone) },
                                enabled = contact.isNotBlank() && !state.loading && now >= deadline,
                                modifier = Modifier.testTag("register_send_code"), fillWidth = false)
                        }
                    }
                    Spacer(Modifier.height(2.dp))
                    GlassActionButton(if (state.loading) (if (register) "Đang đăng ký…" else "Đang đăng nhập…") else (if (register) "Đăng ký" else "Đăng nhập"), {
                        if (register) { vm.register(RegistrationInput(username, password, nickname, contact, code, phone)); password = ""; code = "" }
                        else submit()
                    }, enabled = if (register) canRegister else canLogin, primary = true,
                        modifier = Modifier.fillMaxWidth().testTag("login_submit"))
                    if (!register) GlassActionButton("Đăng nhập bằng khóa truy cập", {
                        password = ""; code = ""; vm.loginWithPasskey(context)
                    }, enabled = !state.loading, modifier = Modifier.fillMaxWidth().testTag("login_passkey"))
                }
            }
        }
        }
        if (account.loading) Text("Đang xác minh phiên…", fontSize = 13.sp, color = secondary)
        state.message?.takeIf { !state.failed }?.let {
            Text(it, fontSize = 13.sp, color = secondary, modifier = Modifier.testTag("login_message"))
        }
        if (account.offline) {
            Text("Đang ngoại tuyến, thông tin phiên đăng nhập đã được giữ lại", fontSize = 13.sp, color = secondary)
            GlassActionButton("Kết nối lại", onClick = { vm.retry() })
        }
    }
    }
    SettingsUiScope {
        if (showTitle) MusicSubpage("Tài khoản CurrentMusic", "login_page", onBack, content = form)
        else form(contentTopInset + 16.dp)
    }
    if (recovering) PasswordDialog(true, state.loading, resetDeadline,
        onDismiss = { recovering = false; vm.clearMessage() }, onSendCode = vm::passwordCode,
        onSubmit = { email, code, newPassword, _ -> vm.resetPassword(email, code, newPassword) })
}
