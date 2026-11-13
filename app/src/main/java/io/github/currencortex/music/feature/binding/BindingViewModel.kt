package io.github.currencortex.music.feature.binding

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.network.*
import io.github.currencortex.music.data.binding.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class BindingUiState(val loading: Boolean = false, val binding: BindingState? = null, val error: String? = null,
    val qrUrl: String? = null, val qrMessage: String = "", val codeUntil: Long = 0,
    val codeFailed: Boolean = false, val phoneBoundRevision: Int = 0)
data class BindingMembershipState(val ownerId: Long = 0, val loading: Boolean = false,
    val value: NeteaseMembership? = null, val error: String? = null)
data class BindingMedalsState(val ownerId: Long = 0, val loading: Boolean = false,
    val value: NeteaseMedals? = null, val error: String? = null)
class BindingViewModel(val container: AppContainer) : ViewModel() {
    val state = MutableStateFlow(BindingUiState())
    val busy = MutableStateFlow(false)
    val message = MutableStateFlow<String?>(null)
    val membership = MutableStateFlow(BindingMembershipState())
    val medals = MutableStateFlow(BindingMedalsState())
    private var medalsTask: Job? = null
    private var medalsGeneration = 0L
    private var medalsOwner: io.github.currencortex.music.core.netease.NeteaseAccountState? = null
    private var membershipTask: Job? = null
    private var membershipGeneration = 0L
    private var membershipOwner: io.github.currencortex.music.core.netease.NeteaseAccountState? = null
    private var task: Job? = null
    private var actionTask: Job? = null
    private var qrKey: String? = null
    private var qrOwner: RequestSession? = null
    private var qrGeneration = 0L
    private var qrTerminal = false
    private fun session() = RequestSession(container.accountRepository.server, container.accountRepository.token)
    val canLoginNetease get() = container.accountRepository.state.value.account != null && !container.accountRepository.token.isNullOrBlank()
    private fun requireCurrentMusicAccount() {
        if (!canLoginNetease) throw ApiException(ErrorKind.CurrentMusicAccountRequired)
    }
    init {
        viewModelScope.launch {
            container.neteaseSessions.ready.await()
            container.neteaseSessions.state.map { it.revision }.distinctUntilChanged().collect {
                reloadMembership(); reloadMedals()
            }
        }
        viewModelScope.launch {
            container.sessionRestored.await()
            combine(container.accountRepository.sessionRevision,
                container.musicSettings.state.map { it.server }.distinctUntilChanged()) { revision, server -> revision to server }
                .collect {
                    actionTask?.cancel(); busy.value = false; message.value = null
                    clearQr(); state.value = BindingUiState(); reload()
                }
        }
    }
    fun reload() {
        reloadMembership()
        reloadMedals()
        task?.cancel(); task = viewModelScope.launch {
            state.update { it.copy(loading = true, error = null) }
            when (val r = appResult { container.bindingRepository.status() }) {
                is AppResult.Success -> state.update { it.copy(loading = false, binding = r.value) }
                is AppResult.Failure -> state.update { it.copy(loading = false, error = r.kind.message) }
            }
        }
    }
    fun reloadMembership() {
        val owner = container.neteaseSessions.state.value
        val uid = owner.profile?.uid ?: 0
        if (container.nativeNetease != null && owner.loggedIn && !owner.stale &&
            membershipOwner == owner && membership.value.loading) return
        val token = ++membershipGeneration
        membershipTask?.cancel()
        membershipOwner = owner
        membership.value = BindingMembershipState()
        if (container.nativeNetease == null || !owner.loggedIn || owner.stale || uid <= 0) return
        membership.value = BindingMembershipState(uid, loading = true)
        membershipTask = viewModelScope.launch {
            val result = appResult { container.bindingRepository.membership() }
            if (token != membershipGeneration || container.neteaseSessions.state.value != owner) return@launch
            membership.value = when (result) {
                is AppResult.Success -> if (result.value.userId == uid) BindingMembershipState(uid, value = result.value)
                    else BindingMembershipState(uid, error = "Thông tin hội viên hiện chưa khả dụng")
                is AppResult.Failure -> BindingMembershipState(uid, error = "Thông tin hội viên hiện chưa khả dụng")
            }
        }
    }
    fun reloadMedals() {
        val owner = container.neteaseSessions.state.value
        val uid = owner.profile?.uid ?: 0
        if (container.nativeNetease != null && owner.loggedIn && !owner.stale &&
            medalsOwner == owner && medals.value.loading) return
        val generation = ++medalsGeneration
        medalsTask?.cancel()
        medalsOwner = owner
        medals.value = BindingMedalsState()
        if (container.nativeNetease == null || !owner.loggedIn || owner.stale || uid <= 0) return
        medals.value = BindingMedalsState(uid, loading = true)
        medalsTask = viewModelScope.launch {
            val result = appResult { container.bindingRepository.medals() }
            if (generation != medalsGeneration || container.neteaseSessions.state.value != owner) return@launch
            medals.value = when (result) {
                is AppResult.Success -> if (result.value.userId == uid) BindingMedalsState(uid, value = result.value)
                    else BindingMedalsState(uid, error = "Hiện không thể tải huy hiệu")
                is AppResult.Failure -> BindingMedalsState(uid, error = "Hiện không thể tải huy hiệu")
            }
        }
    }
    private fun failureMessage(failure: AppResult.Failure, stage: String): String {
        val status = if (failure.status > 0) "（HTTP ${failure.status}）" else ""
        if (stage == "Gửi mã xác minh dự phòng" && failure.kind in listOf(ErrorKind.Forbidden, ErrorKind.NotFound))
            return "Máy chủ hiện tại chưa hỗ trợ API gửi mã xác minh dự phòng, vui lòng đăng nhập bằng cách quét mã QR."
        if (stage.endsWith("Gửi mã xác minh") && failure.kind in listOf(ErrorKind.Server, ErrorKind.Timeout, ErrorKind.Network))
            return "Chưa xác nhận được kết quả gửi mã xác minh NetEase$status. Nếu đã nhận được tin nhắn, bạn có thể nhập mã ngay; nếu chưa nhận được, hãy thử lại sau hoặc đăng nhập bằng cách quét mã QR."
        if (failure.kind == ErrorKind.Server) return "${stage} NetEase tạm thời thất bại$status, vui lòng thử lại sau" +
            (if (stage == "Đăng nhập bằng mã xác minh điện thoại") " hoặc đăng nhập bằng cách quét mã QR." else ".")
        return "NetEase $stage$status：${failure.kind.message}"
    }
    private fun action(stage: String = "Thao tác tài khoản", block: suspend (RequestSession) -> String) {
        if (busy.value) return
        busy.value = true
        message.value = null
        val expected = session()
        actionTask = viewModelScope.launch {
            try { when (val r = appResult { block(expected) }) {
                is AppResult.Success -> if (expected == session()) { message.value = r.value; reload() }
                is AppResult.Failure -> if (expected == session()) { message.value = failureMessage(r, stage); reload() }
            } } finally { if (expected == session()) busy.value = false }
        }
    }
    fun live() = action {
        val status = container.bindingRepository.live()
        if (status.ok) "Trạng thái đăng nhập NetEase bình thường" else if (!status.bound) "Chưa liên kết NetEase" else "Trạng thái đăng nhập NetEase đã hết hiệu lực, vui lòng đăng nhập lại"
    }
    fun refresh() = action { expected -> container.bindingRepository.refresh(expected); "Đã làm mới trạng thái đăng nhập NetEase" }
    fun syncProjectBinding() { viewModelScope.launch { container.bindingRepository.syncProjectBinding() } }
    fun sync(expected: RequestSession = session()) = action { container.bindingRepository.sync(expected).message() }
    fun mainLibrary(value: Boolean) = viewModelScope.launch { container.musicSettings.setNeteaseMainLibrary(value) }
    fun reloadMusic() = action {
        container.bindingRepository.status()
        container.neteaseLibrary.invalidate()
        container.libraryRepository.invalidate()
        "Đã cập nhật thư viện nhạc"
    }
    private suspend fun afterBinding(expected: RequestSession): String {
        container.importNeteaseAvatarAfterLogin(expected)
        if (container.musicSettings.snapshot().neteaseMainLibrary || container.apiClient.hasLocalNeteaseLogin) {
            val status = appResult { container.bindingRepository.status() }
            container.neteaseLibrary.invalidate()
            container.libraryRepository.invalidate()
            return if (status is AppResult.Success) {
                if (container.apiClient.hasLocalNeteaseLogin) "Liên kết thành công, đã sẵn sàng kết nối trực tiếp với NetEase" else "Liên kết thành công, đang sử dụng thư viện nhạc NetEase"
            }
                else "Liên kết thành công; hiện không thể đọc thư viện nhạc, vui lòng làm mới và thử lại"
        }
        return when (val result = appResult { container.bindingRepository.sync(expected) }) {
            is AppResult.Success -> "Liên kết thành công; ${result.value.message()}"
            is AppResult.Failure -> "Liên kết thành công; ${failureMessage(result, "Đồng bộ danh sách phát")}, bạn có thể đồng bộ lại"
        }
    }
    fun unbind() = action { expected ->
        val local = container.apiClient.hasLocalNeteaseLogin
        container.bindingRepository.unbind(expected); clearQr()
        if (local) "Đã đăng xuất tài khoản NetEase trên thiết bị này, liên kết đám mây vẫn được giữ lại" else "Đã hủy liên kết NetEase, danh sách phát đã nhập vẫn được giữ dưới dạng ảnh chụp" }
    fun code(phone: String, country: String, alternate: Boolean = false) {
        if (SystemClock.elapsedRealtime() < state.value.codeUntil) return
        action(if (alternate) "Gửi mã xác minh dự phòng" else "Gửi mã xác minh") { expected ->
            requireCurrentMusicAccount()
            // A gateway timeout does not prove that the SMS was not delivered.
            state.update { it.copy(codeUntil = SystemClock.elapsedRealtime() + 60_000, codeFailed = false) }
            when (val result = appResult { container.bindingRepository.sendCode(phone, country, alternate, expected) }) {
                is AppResult.Success -> "Đã gửi mã xác minh, vui lòng kiểm tra tin nhắn"
                is AppResult.Failure -> {
                    if (expected == session()) state.update { it.copy(codeFailed = true) }
                    throw ApiException(result.kind, result.status)
                }
            }
        }
    }
    fun phone(phone: String, captcha: String, country: String) = action("Đăng nhập bằng mã xác minh điện thoại") { expected ->
        requireCurrentMusicAccount()
        container.bindingRepository.bindPhone(phone, captcha, country, expected)
        if (expected != session()) throw ApiException(ErrorKind.Unauthorized)
        state.update { it.copy(phoneBoundRevision = it.phoneBoundRevision + 1) }
        afterBinding(expected)
    }
    suspend fun qrSession() {
        if (!canLoginNetease) { clearQr(); return }
        val expected = session()
        if (qrOwner != expected) clearQr()
        qrOwner = expected
        if (qrTerminal) return
        val generation = qrGeneration
        fun valid() = expected == session() && generation == qrGeneration
        if (qrKey == null) {
            state.update { it.copy(qrUrl = null, qrMessage = "Đang tạo mã QR…") }
            when (val key = appResult { container.bindingRepository.qrKey(expected) }) {
                is AppResult.Failure -> {
                    if (valid()) state.update { it.copy(qrMessage = failureMessage(key, "Tạo mã QR")) }
                    return
                }
                is AppResult.Success -> {
                    if (!valid()) return
                    qrKey = key.value
                     state.update { it.copy(qrUrl = container.bindingRepository.qrUrl(key.value), qrMessage = "Đang chờ quét…") }
                }
            }
        }
        val key = qrKey ?: return
        var retryDelay = 2000L
        // Resume checks the existing key immediately, including confirmation made in the NCM app.
        while (currentCoroutineContext().isActive && valid()) {
            when (val status = appResult { container.bindingRepository.qrStatus(key, expected) }) {
                is AppResult.Failure -> {
                    if (!valid()) return
                    if (status.kind in listOf(ErrorKind.Unauthorized, ErrorKind.Forbidden, ErrorKind.NotFound)) {
                        qrTerminal = true
                         state.update { it.copy(qrUrl = null, qrMessage = failureMessage(status, "Tra cứu trạng thái quét mã")) }
                        return
                    }
                     state.update { it.copy(qrMessage = failureMessage(status, "Tra cứu trạng thái quét mã") + " Đang thử lại, mã QR vẫn giữ nguyên.") }
                    retryDelay = (retryDelay * 2).coerceAtMost(15000L)
                }
                is AppResult.Success -> {
                    if (!valid()) return
                    retryDelay = 2000L
                    when (status.value.code) {
                        800 -> {
                            qrTerminal = true
                             state.update { it.copy(qrMessage = "Mã QR đã hết hạn, vui lòng làm mới", qrUrl = null) }
                            return
                        }
                         802 -> state.update { it.copy(qrMessage = "Đã quét, hãy xác nhận ủy quyền trong Nhạc Vân NetEase…") }
                        803 -> {
                            qrTerminal = true
                             state.update { it.copy(qrUrl = null, qrMessage = "Liên kết thành công") }
                             action("Cập nhật thư viện nhạc") { afterBinding(expected) }
                            return
                        }
                         801 -> state.update { it.copy(qrMessage = "Đang chờ quét…") }
                         else -> state.update { it.copy(qrMessage = "Hiện không thể truy vấn trạng thái quét mã, đang thử lại, mã QR vẫn giữ nguyên.") }
                    }
                }
            }
            delay(retryDelay)
        }
    }
    fun clearQr() {
        qrGeneration++; qrKey = null; qrOwner = null; qrTerminal = false
        state.update { it.copy(qrUrl = null, qrMessage = "") }
    }
    override fun onCleared() { task?.cancel() }
}
