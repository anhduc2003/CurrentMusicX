package io.github.currencortex.music.feature.binding

import io.github.currencortex.music.ui.component.MusicDialogButton
import io.github.currencortex.music.ui.component.musicScrollPadding
import android.graphics.Bitmap
import android.os.SystemClock
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import io.github.currencortex.music.ui.component.MusicPlaceholder
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.github.currencortex.music.ui.util.collectAsPageState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import io.github.currencortex.music.ui.component.MusicDialog
import kotlinx.coroutines.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun qrPixels(url: String, size: Int = 512): IntArray {
    val matrix = QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, size, size,
        mapOf(EncodeHintType.MARGIN to 4, EncodeHintType.CHARACTER_SET to "UTF-8"))
    return IntArray(size * size) { index -> if (matrix[index % size, index / size]) android.graphics.Color.BLACK else android.graphics.Color.WHITE }
}
@Composable fun BindingScreen(vm: BindingViewModel, onBack: () -> Unit, onCurrentMusicLogin: () -> Unit = {}) {
    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    fun finishInput() { focus.clearFocus(); keyboard?.hide() }
    val state by vm.state.collectAsPageState()
    val membership by vm.membership.collectAsPageState()
    val medals by vm.medals.collectAsPageState()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val projectSync by vm.container.bindingRepository.projectBindingSync.collectAsStateWithLifecycle()
    val musicSettings by vm.container.musicSettings.state.collectAsStateWithLifecycle()
    val message by vm.message.collectAsStateWithLifecycle()
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    val settings by vm.container.musicSettings.state.collectAsStateWithLifecycle()
    val tasksEnabled by vm.container.neteaseTasksEnabled.collectAsStateWithLifecycle()
    val taskScope = rememberCoroutineScope()
    val sessionRevision by vm.container.accountRepository.sessionRevision.collectAsStateWithLifecycle()
    val canLoginNetease = account.account != null && vm.canLoginNetease
    var qr by rememberSaveable(account.account?.id, settings.server, sessionRevision) { mutableStateOf(true) }
    var generation by remember { mutableIntStateOf(0) }
    var phone by rememberSaveable(account.account?.id, settings.server, sessionRevision) { mutableStateOf("") }
    var country by rememberSaveable(account.account?.id, settings.server, sessionRevision) { mutableStateOf("86") }
    var captcha by remember(account.account?.id, settings.server, sessionRevision) { mutableStateOf("") }
    var unbind by remember { mutableStateOf(false) }
    var reauthenticate by rememberSaveable(account.account?.id, settings.server, sessionRevision) { mutableStateOf(false) }
    val list = rememberLazyListState()
    var revealLogin by remember { mutableStateOf(false) }
    LaunchedEffect(revealLogin) {
        if (revealLogin) {
            withFrameNanos { }; withFrameNanos { }
            list.animateScrollToItem((list.layoutInfo.totalItemsCount - 1).coerceAtLeast(0))
            revealLogin = false
        }
    }
    val loginVisible = reauthenticate || state.binding?.let { !it.bound || it.stale } == true ||
        (state.binding == null && !state.loading && state.error != null)
    val owner = LocalLifecycleOwner.current
    LaunchedEffect(vm, owner, qr, loginVisible, generation, account.account?.id, settings.server, sessionRevision) {
        if (qr && loginVisible && canLoginNetease) owner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.qrSession(); awaitCancellation()
        } else vm.clearQr()
    }
    LaunchedEffect(state.phoneBoundRevision) { if (state.phoneBoundRevision > 0) { captcha = ""; reauthenticate = false } }
    var now by remember { mutableLongStateOf(SystemClock.elapsedRealtime()) }
    LaunchedEffect(state.codeUntil) {
        now = SystemClock.elapsedRealtime()
        while (now < state.codeUntil) { delay(1000); now = SystemClock.elapsedRealtime() }
    }
    val bitmap by produceState<Bitmap?>(null, state.qrUrl) {
        val url = state.qrUrl
        value = if (url == null) null else withContext(Dispatchers.Default) {
            Bitmap.createBitmap(qrPixels(url), 512, 512, Bitmap.Config.ARGB_8888)
        }
    }
    MusicSubpage("Tài khoản Nhạc Vân NetEase", "binding_page", onBack, backTag = "binding_back") { topInset ->
    LazyColumn(Modifier.fillMaxSize().testTag("binding_screen"), state = list,
        contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        if (!canLoginNetease) item {
            SettingsGroup(Modifier.fillMaxWidth().testTag("binding_currentmusic_required")) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("Đăng nhập CurrentMusic trước", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text("Vui lòng đăng ký và đăng nhập tài khoản CurrentMusic trước, sau đó đăng nhập Nhạc Vân NetEase. Nếu đã có tài khoản, bạn có thể đăng nhập trực tiếp.",
                        fontSize = 14.sp, lineHeight = 22.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                    GlassActionButton("Đăng ký / Đăng nhập CurrentMusic", onCurrentMusicLogin, primary = true,
                        modifier = Modifier.fillMaxWidth().testTag("binding_currentmusic_login"))
                }
            }
        }
        if (state.loading && state.binding == null) item {
            SettingsGroup(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    MusicPlaceholder(Modifier.fillMaxWidth(.65f).height(24.dp))
                    MusicPlaceholder(Modifier.fillMaxWidth(.9f).height(14.dp))
                    Text("Đang kiểm tra liên kết…", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                }
            }
        }
        state.error?.let { error -> item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                BindingNotice(error)
                BindingAction("Thử lại", vm::reload, enabled = !busy)
            }
        } }
        if (canLoginNetease) item {
            SettingsGroup(Modifier.testTag("binding_project_sync")) {
                SettingsPreference("Đồng bộ với CurrentMusic", "Trạng thái đăng nhập Nhạc Vân NetEase sẽ được đồng bộ qua HTTPS đến máy chủ hiện tại để dùng trên web và ứng dụng của cùng tài khoản.")
                if (vm.container.apiClient.hasLocalNeteaseLogin) {
                    SettingsDivider()
                    SettingsPreference(when {
                        projectSync.syncing -> "Đang đồng bộ liên kết…"
                        projectSync.synced -> "Đã đồng bộ liên kết"
                        else -> "Liên kết chưa được đồng bộ"
                    }, projectSync.error ?: if (!projectSync.synced && !projectSync.syncing) "Thông tin đăng nhập trên thiết bị này đã được giữ lại. Bạn có thể thử đồng bộ lại thủ công." else null,
                        modifier = Modifier.testTag("binding_project_sync_status"), trailing = {
                            if (projectSync.synced) SettingsSelectionCheck(true)
                            else GlassActionButton("Đồng bộ liên kết", vm::syncProjectBinding,
                                Modifier.testTag("binding_project_sync_retry"),
                                enabled = !busy && !projectSync.syncing, fillWidth = false)
                        })
                }
            }
        }
        state.binding?.let { binding ->
            if (binding.bound) item {
                BindingAccountCard(binding, busy, { if (musicSettings.neteaseMainLibrary || vm.container.apiClient.hasLocalNeteaseLogin) vm.reloadMusic() else vm.sync() }, vm::live, vm::refresh,
                    { finishInput(); reauthenticate = true; revealLogin = true }, { unbind = true }, musicSettings.neteaseMainLibrary || vm.container.apiClient.hasLocalNeteaseLogin,
                    membership, vm::reloadMembership)
                if (!binding.stale && medals.ownerId == binding.profile?.uid) {
                    Spacer(Modifier.height(16.dp))
                    BindingMedalsCard(medals, vm::reloadMedals)
                }
            } else if (canLoginNetease) item {
                Column(Modifier.padding(vertical = 4.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Kết nối thư viện nhạc của bạn", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                    Text("Kết nối mục Bài hát yêu thích và danh sách phát trên Nhạc Vân NetEase để tiếp tục nghe trong CurrentMusic.", fontSize = 13.sp, lineHeight = 21.sp,
                        color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f))
                }
            }
        }
        if (vm.container.nativeNetease != null) item {
            SettingsGroup(Modifier.fillMaxWidth()) {
                io.github.currencortex.music.ui.component.SettingsSwitch("Tác vụ Nhạc Vân NetEase", tasksEnabled == true, { enabled ->
                    taskScope.launch {
                        val result = io.github.currencortex.music.core.network.appResult { vm.container.neteaseTaskPreferences.enable(enabled) }
                        if (result is io.github.currencortex.music.core.network.AppResult.Failure) vm.container.errorNotices.show(result.message)
                    }
                }, Modifier.testTag("netease_tasks_enabled"), summary = "Sau khi bật, lối tắt tác vụ sẽ hiển thị bên dưới tài khoản Nhạc Vân NetEase trong mục “Của tôi”", enabled = tasksEnabled != null)
            }
            Spacer(Modifier.height(12.dp))
            Text(if (vm.container.apiClient.hasLocalNeteaseLogin)
                "Đang dùng tài khoản Nhạc Vân NetEase đăng nhập trên thiết bị này. Thông tin đăng nhập được mã hóa và lưu trên thiết bị. Tài sản trên đám mây của CurrentMusic được đồng bộ riêng."
                else if (state.binding?.bound == true)
                    "Đã dùng lại liên kết Nhạc Vân NetEase trên đám mây của tài khoản CurrentMusic hiện tại, không cần quét mã lần nữa. Đăng nhập trên thiết bị này có thể dùng để kết nối trực tiếp và sử dụng hiệu ứng âm thanh gốc."
                else "Sau khi đăng ký và đăng nhập CurrentMusic, bạn có thể đăng nhập Nhạc Vân NetEase tại đây. Thông tin đăng nhập Nhạc Vân NetEase được mã hóa và lưu trên thiết bị này; API gốc vẫn kết nối trực tiếp với Nhạc Vân NetEase.",
                fontSize = 12.sp, lineHeight = 19.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f),
                modifier = Modifier.testTag("netease_direct_notice"))
        }
        item {
            SettingsGroup(Modifier.fillMaxWidth()) {
                io.github.currencortex.music.ui.component.SettingsSwitch("Dùng Nhạc Vân NetEase làm thư viện nhạc chính", musicSettings.neteaseMainLibrary,
                    vm::mainLibrary, Modifier.testTag("netease_main_library"),
                    summary = "Sau khi liên kết, dùng trực tiếp mục Bài hát yêu thích trên Nhạc Vân NetEase; chạm biểu tượng trái tim để đồng bộ bài hát yêu thích, nhấn giữ để chọn danh sách phát khác", enabled = !busy)
            }
        }
        if (busy) item { BindingNotice("Đang xử lý, vui lòng chờ…", busy = true) }
        message?.let { value -> item { BindingNotice(value, Modifier.testTag("binding_result")) } }
        if (loginVisible && canLoginNetease) {
            if (state.binding?.bound == true) item {
                Text("Đăng nhập lại Nhạc Vân NetEase", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
            item {
                SettingsGroup(Modifier.fillMaxWidth().testTag("binding_login_card")) {
                    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        BindingMethods(qr, !busy) { method -> finishInput(); qr = method }
                        if (qr) BindingQrContent(bitmap, state, busy) { vm.clearQr(); generation++ }
                        else Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            Text("Đăng nhập bằng mã xác minh qua điện thoại", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                TextField(country, { country = it.filter(Char::isDigit).take(4) }, singleLine = true, label = "Mã vùng",
                                    modifier = Modifier.width(72.dp).testTag("binding_country"),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next))
                                TextField(phone, { phone = it.filter(Char::isDigit).take(15) }, singleLine = true, label = "Số điện thoại",
                                    modifier = Modifier.weight(1f).testTag("binding_phone"),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next))
                            }
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                                TextField(captcha, { captcha = it.filter(Char::isDigit).take(8) }, singleLine = true, label = "Mã xác minh",
                                    modifier = Modifier.weight(1f).testTag("binding_code"),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(onDone = { finishInput() }))
                                GlassActionButton(if (now < state.codeUntil) "Gửi lại sau ${(state.codeUntil - now + 999) / 1000}s" else "Lấy mã xác minh",
                                    { vm.code(phone, country) },
                                    enabled = phone.length >= 5 && country.isNotBlank() && !busy && now >= state.codeUntil,
                                    modifier = Modifier.width(116.dp).testTag("send_binding_code"))
                            }
                            if (state.codeFailed) {
                                Text("Nếu đã nhận được tin nhắn, bạn có thể điền trực tiếp; nếu chưa nhận được, hãy đợi hết thời gian đếm ngược rồi thử gửi bằng phương thức dự phòng.", fontSize = 12.sp, lineHeight = 19.sp,
                                    color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
                                BindingAction("Gửi mã xác minh bằng phương thức dự phòng", { vm.code(phone, country, alternate = true) },
                                    Modifier.testTag("send_binding_code_alternate"),
                                    phone.length >= 5 && country.isNotBlank() && !busy && now >= state.codeUntil)
                            }
                            GlassActionButton("Xác nhận liên kết", { finishInput(); vm.phone(phone, captcha, country) }, primary = true,
                                enabled = phone.length >= 5 && captcha.isNotBlank() && country.isNotBlank() && !busy,
                                modifier = Modifier.fillMaxWidth().testTag("confirm_phone_binding"))
                            Text("Mã xác minh được gửi bởi Nhạc Vân NetEase. Sau khi liên kết, hãy kết nối thư viện nhạc theo phương thức đã chọn.", fontSize = 12.sp, lineHeight = 19.sp,
                                color = MiuixTheme.colorScheme.onSurface.copy(alpha = .45f))
                        }
                    }
                }
            }
        }
    }
    }
    SettingsUiScope {
    if (unbind) MusicDialog("Hủy liên kết Nhạc Vân NetEase?", { if (!busy) unbind = false }) {
        Text(if (vm.container.apiClient.hasLocalNeteaseLogin) "Thao tác này sẽ đăng xuất tài khoản Nhạc Vân NetEase trên thiết bị; liên kết đám mây và danh sách phát sẽ được giữ lại."
            else "Thao tác này sẽ hủy liên kết Nhạc Vân NetEase trên đám mây của tài khoản CurrentMusic hiện tại; danh sách phát đã nhập sẽ được giữ lại dưới dạng ảnh chụp." )
        MusicDialogButton("Xác nhận hủy liên kết", onClick = { unbind = false; vm.unbind() }, enabled = !busy, modifier = Modifier.testTag("confirm_unbind"))
        BindingAction("Hủy", { unbind = false }, enabled = !busy)
    }
}
}
