package io.github.currencortex.music.feature.navidrome

import io.github.currencortex.music.ui.component.nativeui.*

import io.github.currencortex.music.ui.component.nativeui.SettingsGroup as Card
import io.github.currencortex.music.ui.component.nativeui.SettingsUiScope
import io.github.currencortex.music.ui.component.nativeui.GlassActionButton

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun NavidromeAccountPage(vm: NavidromeViewModel, onConnected: () -> Unit, onDisconnect: () -> Unit,
    topInset:androidx.compose.ui.unit.Dp=0.dp) {
    SettingsUiScope {
    val account by vm.account.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val error by vm.actionError.collectAsStateWithLifecycle()
    var edit by rememberSaveable(account.revision) { mutableStateOf(!account.configured) }
    var server by rememberSaveable(account.server) { mutableStateOf(account.server) }
    var username by rememberSaveable(account.username) { mutableStateOf(account.username) }
    // Never persist the password into saved state, URLs, test diagnostics or logs.
    var password by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }
    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val canConnect = !busy && server.isNotBlank() && username.isNotBlank() && password.isNotEmpty()
    fun connect() { if (canConnect) { focus.clearFocus(); keyboard?.hide(); vm.connect(server, username, password) { password = ""; visible = false; onConnected() } } }
    LazyColumn(Modifier.fillMaxSize().imePadding(), contentPadding = musicScrollPadding(PaddingValues(16.dp,topInset+8.dp,16.dp,32.dp)), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item { SettingsHint("Tương thích với Navidrome / Subsonic, phát trực tiếp bài hát ở chất lượng âm thanh gốc, thông tin tài khoản được mã hóa và lưu trên thiết bị.") }
        if (account.configured) {
            item { SettingsSection("Tài khoản hiện tại", MusicIcons.Device) {
                SettingsPreference(account.username, account.server)
                if (account.version.isNotBlank()) SettingsHint("Navidrome ${account.version}")
                SettingsDivider()
                SettingsSwitch("Dùng làm thư viện nhạc trên trang chủ", account.home, vm::home, summary = "Duyệt trực tiếp thư viện nhạc tự tạo trên trang chủ; tắt để khôi phục trang chủ trước đây", enabled = !busy)
                SettingsDivider()
                MusicDestinationRow(if (edit) "Hủy đổi tài khoản" else "Đổi máy chủ hoặc tài khoản", {
                    server = account.server; username = account.username
                    edit = !edit; password = ""; visible = false; vm.clearError(); focus.clearFocus(); keyboard?.hide()
                }, enabled = !busy, modifier = Modifier.testTag("navidrome_edit_account"), chevron = false)
            } }
        }
        if (edit) item { SettingsSection("Kết nối máy chủ", MusicIcons.Storage) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            TextField(server, { server = it; vm.clearError() }, label = "Địa chỉ máy chủ", singleLine = true, enabled = !busy,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri, imeAction = ImeAction.Next), modifier = Modifier.testTag("navidrome_server"))
            TextField(username, { username = it; vm.clearError() }, label = "Tên người dùng", singleLine = true, enabled = !busy,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next), modifier = Modifier.testTag("navidrome_username"))
            TextField(password, { password = it; vm.clearError() }, label = "Mật khẩu", singleLine = true, enabled = !busy,
                visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { connect() }), modifier = Modifier.testTag("navidrome_password"))
            MusicTextAction(if (visible) "Ẩn mật khẩu" else "Hiện mật khẩu", { visible = !visible }, enabled = !busy)
            error?.let { Text(it, color = MiuixTheme.colorScheme.error, fontSize = 13.sp, modifier = Modifier.testTag("navidrome_account_error")) }
        } } }
        // Keep the primary action in its own lazy item. A form taller than the IME viewport
        // otherwise brings the whole card into view and can place its action under the header.
        if (edit) item("connect") {
            GlassActionButton(if (busy) "Đang kết nối…" else if (account.configured) "Xác nhận đổi" else "Kết nối máy chủ", ::connect,
                primary=true,enabled = canConnect, modifier = Modifier.fillMaxWidth().testTag("navidrome_connect"))
        }
        if (!edit) error?.let { item { Text(it, color = MiuixTheme.colorScheme.error, fontSize = 13.sp) } }
         if (account.configured) item { Card { SettingsPreference("Ngắt kết nối", "Chỉ đăng xuất khỏi máy chủ này, không ảnh hưởng đến các tài khoản nhạc khác",
            Modifier.testTag("navidrome_disconnect"), enabled = !busy, onClick = onDisconnect) } }
    }
    }
}
