package io.github.currencortex.music.core.media

import io.github.currencortex.music.core.netease.NeteaseSessionStore
import io.github.currencortex.music.data.effects.OriginalMoodEntitlement
import io.github.currencortex.music.data.effects.OriginalMoodModelRepository
import io.github.currencortex.music.data.settings.OriginalSmartPreferences
import io.github.currencortex.music.data.settings.OriginalMoodSettingsRepository
import io.github.currencortex.music.data.song.MusicSource
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


data class OriginalMoodState(
    val enabled: Boolean = false,
    val loading: Boolean = false,
    val message: String = "Hiệu ứng âm thanh cảm xúc AI bản gốc của Nhạc Vân NetEase đã tắt",
    val processing: Boolean = false,
    val available: Boolean = false,
    val requestedEnabled: Boolean = false,
    val songId: Long? = null,
    val emotion: String? = null,
    val family: String? = null,
    /** Only original algorithm output belongs here; the UI never synthesizes song measurements. */
    val visualExtra: kotlinx.serialization.json.JsonObject? = null,
)

/** The dedicated 106/116 benefit gates the entire original model and preset pipeline. */
class OriginalMoodEffectsController internal constructor(
    accounts: Flow<OriginalSmartAccount>,
    private val ready: suspend () -> Unit,
    private val readSaved: suspend () -> OriginalSmartPreferences,
    private val writeSaved: suspend (OriginalSmartPreferences) -> Unit,
    private val entitlement: suspend () -> OriginalMoodEntitlement,
    private val prepareModel: suspend () -> java.io.File,
    private val descriptor: suspend (Long) -> kotlinx.serialization.json.JsonObject?,
    songs: Flow<Song?>,
    engineStatus: Flow<Boolean>,
    private val scope: CoroutineScope,
    private val beforeEnable: suspend () -> Unit,
    private val definition: () -> ByteArray?,
    private val now: () -> Long = System::currentTimeMillis,
    private val workDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : java.io.Closeable {
    constructor(repository: OriginalMoodModelRepository, sessions: NeteaseSessionStore,
        settings: OriginalMoodSettingsRepository, songs: Flow<Song?>, engineStatus: Flow<Boolean>,
        scope: CoroutineScope, beforeEnable: suspend () -> Unit, definition: () -> ByteArray?) : this(
        accounts = sessions.state.map { OriginalSmartAccount(it.profile?.uid ?: 0, it.revision, it.loggedIn, it.stale) },
        ready = { sessions.ready.await(); Unit }, readSaved = settings::snapshot, writeSaved = settings::set,
        entitlement = repository::entitlement, prepareModel = repository::prepare, descriptor = repository::descriptor,
        songs = songs, engineStatus = engineStatus, scope = scope, beforeEnable = beforeEnable, definition = definition)

    private data class Target(val id: Long, val source: MusicSource, val video: Boolean)
    private data class Context(val account: OriginalSmartAccount, val target: Target?, val engine: Boolean)
    private val mutableState = MutableStateFlow(OriginalMoodState())
    val state: StateFlow<OriginalMoodState> = mutableState.asStateFlow()
    private val mutableSelection = MutableStateFlow<NeteaseOriginalPreset?>(null)
    val selection: StateFlow<NeteaseOriginalPreset?> = mutableSelection.asStateFlow()
    private var requested = OriginalSmartPreferences()
    private var context: Context? = null
    private var generation = 0L
    private var pending: Job? = null
    private var expiry: Job? = null
    private var permissionExpiresAt = 0L
    private var closed = false
    private val persistence = Mutex()
    private var lastSavedGeneration = -1L
    private val startupGeneration = generation
    private val observer = scope.launch {
        ready()
        val saved = readSaved()
        if (closed) return@launch
        if (generation == startupGeneration) requested = saved
        combine(accounts.distinctUntilChanged(),
            songs.map { it?.let { song -> Target(song.id, song.musicSource, song.video) } }.distinctUntilChanged(),
            engineStatus.distinctUntilChanged()) { account, target, engine -> Context(account, target, engine) }
            .distinctUntilChanged().collect { next ->
                val old = context
                context = next
                if (requested.enabled && requested.ownerId > 0 && requested.ownerId != next.account.userId) {
                    revoke("Tài khoản đã chuyển đổi, vui lòng bật lại hiệu ứng âm thanh cảm xúc AI cho tài khoản Nhạc Vân NetEase hiện tại")
                } else if (old != next) evaluate()
            }
    }

    fun setEnabled(value: Boolean) {
        if (closed) return
        if (!value) { disable(); return }
        // A direct request binds to the current account only after live entitlement verification.
        requested = OriginalSmartPreferences(true, context?.account?.userId?.takeIf { it > 0 } ?: 0)
        evaluate()
    }

    fun disable() {
        if (closed) return
        requested = OriginalSmartPreferences()
        val token = invalidate()
        mutableState.value = OriginalMoodState(available = context?.engine ?: false)
        persistOff(token)
    }

    /** Retries an import or network failure without manufacturing a previous entitlement. */
    fun refresh() { if (!closed) evaluate() }

    private fun invalidate(): Long {
        val token = ++generation
        pending?.cancel(); pending = null
        expiry?.cancel(); expiry = null
        permissionExpiresAt = 0L
        mutableSelection.value = null
        return token
    }

    private fun evaluate() {
        val token = invalidate()
        val current = context
        mutableState.value = OriginalMoodState(available = current?.engine ?: false,
            requestedEnabled = requested.enabled, songId = current?.target?.id,
            loading = requested.enabled && current != null,
            message = if (requested.enabled) "Đang xác minh quyền lợi dành riêng cho AI cảm xúc của Nhạc Vân NetEase" else "Hiệu ứng âm thanh cảm xúc AI bản gốc của Nhạc Vân NetEase đã tắt")
        if (!requested.enabled || closed || current == null) return
        val account = current.account
        if (!account.loggedIn || account.stale || account.userId <= 0) {
            revoke("Vui lòng đăng nhập tài khoản Nhạc Vân NetEase dành riêng cho AI cảm xúc hợp lệ trước")
            return
        }
        if (requested.ownerId != 0L && requested.ownerId != account.userId) {
            revoke("Hiệu ứng âm thanh cảm xúc AI đã lưu không thuộc tài khoản Nhạc Vân NetEase hiện tại")
            return
        }
        pending = scope.launch request@ {
            try {
                val right = retryMoodNetwork { entitlement() }
                if (!isCurrent(token, current)) return@request
                if (right.userId != account.userId) { revoke("Xác minh tài khoản quyền lợi Nhạc Vân NetEase không thành công, vui lòng đăng nhập lại rồi thử lại"); return@request }
                if (!right.canUse || right.expiresAt <= now()) {
                    revoke("Hiệu ứng âm thanh cảm xúc AI bản gốc của Nhạc Vân NetEase yêu cầu quyền lợi AI cảm xúc hợp lệ")
                    return@request
                }
                requested = OriginalSmartPreferences(true, account.userId)
                persist(requested, token)
                if (!isCurrent(token, current)) return@request
                permissionExpiresAt = right.expiresAt
                expiry = scope.launch {
                    while (isCurrent(token, current)) {
                        val remaining = right.expiresAt - now()
                        if (remaining <= 0) { evaluate(); return@launch }
                        // Android delays use uptime, while server membership deadlines use wall time.
                        delay(minOf(remaining, 60_000L))
                    }
                }
                if (!current.engine) {
                    mutableState.value = state.value.copy(loading = false,
                        message = "Đã ghi nhớ ý định bật; sẽ xác minh lại quyền lợi sau khi nhập engine tương thích với bản gốc")
                    return@request
                }
                val raw = withContext(workDispatcher) { definition()?.copyOf() }
                if (!isCurrent(token, current)) return@request
                if (raw == null) {
                    mutableState.value = state.value.copy(loading = false,
                        message = "Chưa nhập tài nguyên hiệu ứng âm thanh cảm xúc AI bản gốc, hiện tiếp tục sử dụng hiệu ứng âm thanh thủ công")
                    return@request
                }
                val target = current.target
                if (target == null || target.id <= 0 || target.source != MusicSource.NETEASE || target.video) {
                    // Validate the default resource and enforce mutual exclusion even before playback.
                    withContext(workDispatcher) { OriginalMoodCodec(raw) }
                    if (!isCurrent(token, current)) return@request
                    beforeEnable()
                    if (!isCurrent(token, current)) return@request
                    if (right.expiresAt <= now()) { revoke("Quyền lợi dành riêng cho AI cảm xúc của Nhạc Vân NetEase đã hết hạn, đã khôi phục hiệu ứng âm thanh thủ công"); return@request }
                    mutableState.value = state.value.copy(enabled = true, loading = false,
                        message = "Đã bật, tự động khớp hiệu ứng âm thanh bản gốc khi phát bài hát Nhạc Vân NetEase")
                    return@request
                }
                val prepared = withContext(workDispatcher) {
                    val algorithm = OriginalMoodCodec(raw)
                    val row = retryMoodNetwork { descriptor(target.id) } ?: kotlinx.serialization.json.buildJsonObject {
                        put("song_id", kotlinx.serialization.json.JsonPrimitive(target.id))
                    }
                    OriginalMoodModel(retryMoodNetwork { prepareModel() }).use { model ->
                        val input = algorithm.prepare(row, model.inputs, model.outputs)
                        val output = if (input.infer) model.infer(input.emotion, input.genre, input.features, input.extra) else null
                        algorithm.finish(input, output)
                    }
                }
                if (!isCurrent(token, current)) return@request
                beforeEnable()
                if (!isCurrent(token, current)) return@request
                if (right.expiresAt <= now()) { revoke("Quyền lợi AI cảm xúc của Nhạc Vân NetEase đã hết hạn, đã khôi phục hiệu ứng âm thanh thủ công"); return@request }
                mutableSelection.value = prepared.preset()
                mutableState.value = state.value.copy(enabled = true, loading = false, processing = false,
                    emotion = prepared.emotion, family = prepared.family, visualExtra = prepared.visualExtra,
                    message = if (prepared.inferred) "Đã khớp mô hình cảm xúc AI bản gốc, đang chờ xử lý âm thanh" else "Đặc trưng không đủ, đã khớp mẫu cảm xúc bản gốc")
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: LinkageError) {
                if (isCurrent(token, current)) reportFailure("Tải engine cảm xúc AI bản gốc thất bại, vui lòng nhập lại gói cài đặt tương thích rồi thử lại")
            }
            catch (_: Exception) {
                if (isCurrent(token, current)) mutableState.value = state.value.copy(enabled = false, loading = false,
                    processing = false, message = "Hiệu ứng âm thanh cảm xúc AI bản gốc hiện không khả dụng, vui lòng kiểm tra mạng hoặc tài nguyên rồi thử lại; hiện đang dùng hiệu ứng âm thanh thủ công")
            }
        }
    }

    private fun isCurrent(token: Long, expected: Context): Boolean =
        !closed && token == generation && context == expected && requested.enabled

    private fun revoke(message: String) {
        requested = OriginalSmartPreferences()
        val token = invalidate()
        mutableState.value = OriginalMoodState(available = context?.engine ?: false,
            songId = context?.target?.id, message = message)
        persistOff(token)
    }

    private fun persistOff(token: Long) {
        scope.launch {
            try { persist(OriginalSmartPreferences(), token) }
            catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) {
                if (!closed && token == generation) mutableState.value = state.value.copy(
                    message = "Hiệu ứng âm thanh cảm xúc AI bản gốc đã tắt nhưng lưu cài đặt thất bại, vui lòng thử lại sau")
            }
        }
    }

    private suspend fun persist(value: OriginalSmartPreferences, token: Long) = persistence.withLock {
        if ((!closed && token == generation) || (!value.enabled && token >= lastSavedGeneration)) {
            writeSaved(value)
            lastSavedGeneration = token
        }
    }

    /** Acknowledged only after the audio processor successfully produces native PCM. */
    fun reportProcessing(presetId: Long) {
        if (closed || presetId != ORIGINAL_MOOD_PRESET_ID || selection.value?.id != presetId || !state.value.enabled) return
        if (permissionExpiresAt <= now()) evaluate()
        else mutableState.value = state.value.copy(processing = true, message = "Hiệu ứng âm thanh cảm xúc AI bản gốc của Nhạc Vân NetEase đang xử lý âm thanh")
    }

    /** A native failure withdraws output but keeps the user's account-bound retry intention. */
    fun reportFailure(message: String) {
        if (closed) return
        invalidate()
        mutableState.value = state.value.copy(enabled = false, loading = false, processing = false,
            requestedEnabled = requested.enabled, emotion = null, family = null, visualExtra = null,
            message = message.take(200).ifBlank { "Xử lý hiệu ứng âm thanh cảm xúc AI bản gốc thất bại, hiện đang dùng hiệu ứng âm thanh thủ công; có thể thử lại" })
    }

    override fun close() {
        if (closed) return
        invalidate(); closed = true; observer.cancel()
        mutableState.value = state.value.copy(enabled = false, loading = false, processing = false)
        // Container disposal is not a user request to erase an account-bound saved intent.
    }

}
