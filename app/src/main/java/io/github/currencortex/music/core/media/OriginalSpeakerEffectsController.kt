package io.github.currencortex.music.core.media

import io.github.currencortex.music.core.netease.NeteaseSessionStore
import io.github.currencortex.music.data.effects.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class OriginalSpeakerState(
    val enabled: Boolean = false,
    val loading: Boolean = false,
    val requestedEnabled: Boolean = false,
    val processing: Boolean = false,
    val available: Boolean = false,
    val scene: OriginalSpeakerScene = OriginalSpeakerScene.NORMAL,
    val modelId: Long? = null,
    val message: String = "Hiệu ứng âm thanh loa ngoài bản gốc của Nhạc Vân NetEase đã tắt",
)

/** Keeps the original negative material ID, live SVIP ownership, and speaker-only routing together. */
class OriginalSpeakerEffectsController internal constructor(
    accounts: Flow<OriginalSmartAccount>,
    private val ready: suspend () -> Unit,
    environment: Flow<OriginalSpeakerEnvironment>,
    engineStatus: Flow<Boolean>,
    private val rights: suspend () -> OriginalSpeakerEntitlement,
    private val catalog: suspend (OriginalSpeakerScene) -> List<OriginalSpeakerMaterial>,
    private val prepare: suspend (OriginalSpeakerMaterial) -> NeteaseOriginalPreset,
    private val scope: CoroutineScope,
    private val beforeEnable: suspend () -> Unit,
    private val now: () -> Long = System::currentTimeMillis,
) : java.io.Closeable {
    constructor(repository: OriginalSpeakerEffectsRepository, sessions: NeteaseSessionStore,
        environment: Flow<OriginalSpeakerEnvironment>, engineStatus: Flow<Boolean>, scope: CoroutineScope,
        beforeEnable: suspend () -> Unit) : this(
        accounts = sessions.state.map { OriginalSmartAccount(it.profile?.uid ?: 0, it.revision, it.loggedIn, it.stale) },
        ready = { sessions.ready.await(); Unit }, environment = environment, engineStatus = engineStatus,
        rights = repository::entitlement, catalog = repository::catalog,
        prepare = { material -> withContext(Dispatchers.Default) {
            val decoded = repository.download(material)
            require(decoded.type == 1) { "Tài nguyên hiệu ứng âm thanh loa ngoài này không phải tham số JSON bản gốc tương thích" }
            // Type-10 original speakers explicitly use modern aev2 when present.
            NeteasePresetParameters.fromJson(decoded.bytes.toString(Charsets.UTF_8), soundType = 9)
            NeteaseOriginalPreset(material.id, material.name.ifBlank { "Hiệu ứng âm thanh loa ngoài" }, decoded.type, decoded.bytes)
        } }, scope = scope, beforeEnable = beforeEnable)

    private data class PlaybackContext(val account: OriginalSmartAccount, val environment: OriginalSpeakerEnvironment, val engine: Boolean)
    private val mutableState = MutableStateFlow(OriginalSpeakerState())
    val state: StateFlow<OriginalSpeakerState> = mutableState.asStateFlow()
    private val mutableSelection = MutableStateFlow<NeteaseOriginalPreset?>(null)
    val selection: StateFlow<NeteaseOriginalPreset?> = mutableSelection.asStateFlow()
    private var context: PlaybackContext? = null
    private var requested = false
    private var scene = OriginalSpeakerScene.NORMAL
    private var ownerId = 0L
    private var generation = 0L
    private var pending: Job? = null
    private var expiry: Job? = null
    private var closed = false
    private val observer = scope.launch {
        ready()
        combine(accounts.distinctUntilChanged(), environment.distinctUntilChanged(), engineStatus.distinctUntilChanged()) {
            account, route, engine -> PlaybackContext(account, route, engine)
        }.distinctUntilChanged().collect { next ->
            val old = context
            context = next
            if (requested && ownerId > 0 && (ownerId != next.account.userId || !next.account.loggedIn || next.account.stale)) {
                disable("Tài khoản Nhạc Vân NetEase đã thay đổi, vui lòng bật lại hiệu ứng âm thanh loa ngoài")
            } else if (old?.account != next.account || old?.engine != next.engine || old?.environment?.speaker != next.environment.speaker ||
                old?.let { originalSpeakerId(scene, it.environment.landscape, it.environment.volume) } !=
                originalSpeakerId(scene, next.environment.landscape, next.environment.volume)) evaluate()
        }
    }

    fun setEnabled(value: Boolean, scene: OriginalSpeakerScene = this.scene) {
        if (closed) return
        if (!value) { disable(); return }
        this.scene = scene
        requested = true
        evaluate()
    }
    fun setScene(value: OriginalSpeakerScene) { if (!closed) { scene = value; if (requested) evaluate() else mutableState.value = state.value.copy(scene = value) } }
    fun refresh() { if (!closed) evaluate() }
    fun disable() = disable("Hiệu ứng âm thanh loa ngoài bản gốc của Nhạc Vân NetEase đã tắt")
    private fun disable(message: String) {
        requested = false; ownerId = 0
        invalidate()
        mutableState.value = OriginalSpeakerState(available = context?.engine == true, scene = scene, message = message)
    }
    private fun invalidate(): Long {
        generation++; pending?.cancel(); expiry?.cancel(); pending = null; expiry = null
        mutableSelection.value = null
        return generation
    }
    private fun evaluate() {
        val token = invalidate()
        val current = context
        val base = OriginalSpeakerState(available = current?.engine == true, requestedEnabled = requested, scene = scene)
        if (!requested) { mutableState.value = base; return }
        if (current == null) { mutableState.value = base.copy(loading = true, message = "Đang đọc trạng thái hiệu ứng âm thanh loa ngoài"); return }
        if (!current.account.loggedIn || current.account.stale || current.account.userId <= 0) {
            mutableState.value = base.copy(message = "Vui lòng đăng nhập tài khoản Nhạc Vân NetEase trước, rồi xác minh quyền sử dụng hiệu ứng âm thanh loa ngoài"); return
        }
        if (!current.engine) { mutableState.value = base.copy(message = "Vui lòng nhập engine hiệu ứng âm thanh bản gốc của Nhạc Vân NetEase trước"); return }
        if (!current.environment.speaker) { mutableState.value = base.copy(message = "Hiệu ứng âm thanh loa ngoài chỉ bật khi phát qua loa điện thoại; hiện đã tạm dừng xử lý"); return }
        val selectedScene = scene
        val id = originalSpeakerId(selectedScene, current.environment.landscape, current.environment.volume)
        mutableState.value = base.copy(loading = true, modelId = id, message = "Đang tải hiệu ứng âm thanh loa ngoài bản gốc của Nhạc Vân NetEase")
        pending = scope.launch {
            try {
                val permission = rights()
                check(permission.userId == current.account.userId && permission.canUse && permission.expiresAt > now()) {
                    "Tài khoản Nhạc Vân NetEase hiện tại không có quyền sử dụng hiệu ứng âm thanh loa ngoài SVIP hợp lệ"
                }
                val material = catalog(selectedScene).firstOrNull { it.id == id }
                    ?: error("Nhạc Vân NetEase không cung cấp tài nguyên âm thanh loa ngoài cho tư thế, âm lượng và ngữ cảnh hiện tại")
                val prepared = prepare(material)
                check(prepared.id == id) { "Tài nguyên hiệu ứng âm thanh loa ngoài không khớp với mục đã chọn" }
                if (closed || token != generation) return@launch
                check(permission.expiresAt > now()) { "Quyền SVIP của Nhạc Vân NetEase đã hết hạn" }
                beforeEnable()
                if (closed || token != generation) return@launch
                check(permission.expiresAt > now()) { "Quyền SVIP của Nhạc Vân NetEase đã hết hạn" }
                ownerId = permission.userId
                mutableSelection.value = prepared
                mutableState.value = base.copy(enabled = true, modelId = id, message = "${selectedScene.label} · Đã bật hiệu ứng âm thanh loa ngoài bản gốc")
                expiry = scope.launch {
                    while (token == generation && permission.expiresAt > now())
                        delay((permission.expiresAt - now()).coerceIn(1, 60_000))
                    if (token == generation) disable("Quyền SVIP của Nhạc Vân NetEase đã hết hạn, hiệu ứng âm thanh loa ngoài đã dừng")
                }
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (failure: Exception) {
                if (token == generation) mutableState.value = base.copy(modelId = id,
                    message = failure.message?.takeIf { it.isNotBlank() && it.length < 180 }
                         ?: "Chưa xác minh xong tài nguyên hoặc quyền sử dụng hiệu ứng âm thanh loa ngoài, vui lòng thử lại")
            }
        }
    }
    fun reportProcessing(id: Long) {
        if (!closed && selection.value?.id == id) mutableState.value = state.value.copy(processing = true)
    }
    fun reportFailure(message: String) {
        if (closed) return
        invalidate()
        mutableState.value = state.value.copy(enabled = false, processing = false, loading = false, message = message)
    }
    override fun close() { if (!closed) { closed = true; invalidate(); observer.cancel() } }
}
