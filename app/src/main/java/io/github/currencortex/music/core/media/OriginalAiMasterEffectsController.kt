package io.github.currencortex.music.core.media

import io.github.currencortex.music.core.netease.NeteaseSessionStore
import io.github.currencortex.music.data.effects.OriginalMoodEntitlement
import io.github.currencortex.music.data.effects.OriginalAiMasterRepository
import io.github.currencortex.music.data.settings.OriginalSmartPreferences
import io.github.currencortex.music.data.settings.OriginalAiMasterSettingsRepository
import io.github.currencortex.music.data.song.MusicSource
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


data class OriginalAiMasterState(
    val enabled: Boolean = false,
    val loading: Boolean = false,
    val message: String = "AI Master điều chỉnh âm thanh bản gốc Nhạc Vân NetEase đã tắt",
    val processing: Boolean = false,
    val available: Boolean = false,
    val requestedEnabled: Boolean = false,
    val songId: Long? = null,
    val description: String? = null,
    val baseName: String? = null,
    val longText: String? = null,
    val selectedDiy: Set<String> = emptySet(),
    val usedDiy: Set<String> = emptySet(),
    val diySaving: Boolean = false,
)

/** Original type-9 SVIP gate, account-bound cancellation and true native-PCM acknowledgement. */
class OriginalAiMasterEffectsController internal constructor(
    accounts: Flow<OriginalSmartAccount>,
    private val ready: suspend () -> Unit,
    private val readSaved: suspend () -> OriginalSmartPreferences,
    private val writeSaved: suspend (OriginalSmartPreferences) -> Unit,
    private val entitlement: suspend () -> OriginalMoodEntitlement,
    private val prepare: suspend (Long) -> io.github.currencortex.music.data.effects.OriginalAiMasterMaterial,
    songs: Flow<Song?>,
    engineStatus: Flow<Boolean>,
    private val scope: CoroutineScope,
    private val beforeEnable: suspend () -> Unit,
    private val definition: () -> ByteArray?,
    private val now: () -> Long = System::currentTimeMillis,
    private val workDispatcher: CoroutineDispatcher = Dispatchers.Default,
    supportStereo: Flow<Boolean> = flowOf(false),
    private val readDiy: suspend (Long) -> Set<String> = { emptySet() },
    private val writeDiy: suspend (Long, Set<String>) -> Unit = { _, _ -> },
) : java.io.Closeable {
    constructor(repository: OriginalAiMasterRepository, sessions: NeteaseSessionStore,
        settings: OriginalAiMasterSettingsRepository, songs: Flow<Song?>, engineStatus: Flow<Boolean>,
        scope: CoroutineScope, beforeEnable: suspend () -> Unit, definition: () -> ByteArray?,
        supportStereo: Flow<Boolean> = flowOf(false)) : this(
        accounts = sessions.state.map { OriginalSmartAccount(it.profile?.uid ?: 0, it.revision, it.loggedIn, it.stale) },
        ready = { sessions.ready.await(); Unit }, readSaved = settings::snapshot, writeSaved = settings::set,
        entitlement = repository::entitlement, prepare = repository::prepare,
        songs = songs, engineStatus = engineStatus, scope = scope, beforeEnable = beforeEnable,
        definition = definition, supportStereo = supportStereo,
        readDiy = settings::diyFor, writeDiy = settings::setDiy)

    private data class Target(val id: Long, val source: MusicSource, val video: Boolean)
    private data class Context(val account: OriginalSmartAccount, val target: Target?, val engine: Boolean, val stereo: Boolean)
    private val mutableState = MutableStateFlow(OriginalAiMasterState())
    val state: StateFlow<OriginalAiMasterState> = mutableState.asStateFlow()
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
    private var selectedDiy: Set<String> = emptySet()
    private var diyOwner = 0L
    private var diyGeneration = 0L
    private var diySaving = false
    private val diyPersistence = Mutex()
    private var lastMaterial: Pair<Context, io.github.currencortex.music.data.effects.OriginalAiMasterMaterial>? = null
    private val startupGeneration = generation
    private val observer = scope.launch {
        ready()
        val saved = readSaved()
        if (closed) return@launch
        if (generation == startupGeneration) requested = saved
        combine(accounts.distinctUntilChanged(),
            songs.map { it?.let { song -> Target(song.id, song.musicSource, song.video) } }.distinctUntilChanged(),
            engineStatus.distinctUntilChanged(), supportStereo.distinctUntilChanged()) { account, target, engine, stereo ->
            Context(account, target, engine, stereo) }
            .distinctUntilChanged().collectLatest { next ->
                val old = context
                context = next
                if (requested.enabled && requested.ownerId > 0 && requested.ownerId != next.account.userId) {
                    revoke("Tài khoản đã được chuyển đổi, vui lòng bật lại hiệu ứng AI Master điều chỉnh âm thanh cho tài khoản Nhạc Vân NetEase hiện tại")
                }
                if (diyOwner != next.account.userId) {
                    invalidate()
                    val diyToken = ++diyGeneration
                    selectedDiy = emptySet(); diySaving = false; lastMaterial = null
                    mutableState.value = OriginalAiMasterState(available = next.engine, songId = next.target?.id)
                    val uid = next.account.userId
                    val stored = if (uid > 0) diyPersistence.withLock { readDiy(uid) } else emptySet()
                    if (closed || context != next || diyToken != diyGeneration) return@collectLatest
                    diyOwner = uid
                    selectedDiy = stored.filterTo(linkedSetOf()) { it in OriginalAiMasterCodec.DIY_KEYS }
                }
                if (old != next) evaluate()
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
        mutableState.value = OriginalAiMasterState(available = context?.engine ?: false,
            selectedDiy = selectedDiy, diySaving = diySaving)
        persistOff(token)
    }

    /** Save the actual account's five preferences and recompute with the original closing/merge rules. */
    fun setDiy(selected: Set<String>) {
        require(OriginalAiMasterCodec.DIY_KEYS.containsAll(selected))
        if (closed) return
        val account = context?.account ?: return
        if (!account.loggedIn || account.stale || account.userId <= 0) return
        val uid = account.userId
        val snapshot = selected.toSet()
        val token = ++diyGeneration
        diyOwner = uid; selectedDiy = snapshot; diySaving = true
        evaluate()
        scope.launch {
            try {
                diyPersistence.withLock {
                    if (closed || token != diyGeneration || context?.account?.userId != uid) return@withLock
                    writeDiy(uid, snapshot)
                }
                if (closed || token != diyGeneration || context?.account?.userId != uid) return@launch
                diySaving = false
                mutableState.value = state.value.copy(diySaving = false)
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) {
                if (!closed && token == diyGeneration && context?.account?.userId == uid) {
                    diySaving = false
                    mutableState.value = state.value.copy(diySaving = false,
                        message = "Đã áp dụng tùy chỉnh DIY nhưng không lưu được, vui lòng thử lại")
                }
            }
        }
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
        mutableState.value = OriginalAiMasterState(available = current?.engine ?: false,
            requestedEnabled = requested.enabled, songId = current?.target?.id,
            selectedDiy = selectedDiy, diySaving = diySaving,
            loading = requested.enabled && current != null,
            message = if (requested.enabled) "Đang xác minh quyền lợi SVIP của Nhạc Vân NetEase" else "AI Master điều chỉnh âm thanh bản gốc Nhạc Vân NetEase đã tắt")
        if (!requested.enabled || closed || current == null) return
        val account = current.account
        if (!account.loggedIn || account.stale || account.userId <= 0) {
            revoke("Vui lòng đăng nhập tài khoản SVIP AI Master điều chỉnh âm thanh Nhạc Vân NetEase hợp lệ trước")
            return
        }
        if (requested.ownerId != 0L && requested.ownerId != account.userId) {
            revoke("Hiệu ứng AI Master điều chỉnh âm thanh đã lưu không thuộc tài khoản Nhạc Vân NetEase hiện tại")
            return
        }
        pending = scope.launch request@ {
            try {
                val right = entitlement()
                if (!isCurrent(token, current)) return@request
                if (right.userId != account.userId) { revoke("Xác minh tài khoản quyền lợi Nhạc Vân NetEase không thành công, vui lòng đăng nhập lại rồi thử lại"); return@request }
                if (!right.canUse || right.expiresAt <= now()) {
                    revoke("AI Master điều chỉnh âm thanh bản gốc Nhạc Vân NetEase cần quyền lợi SVIP hợp lệ")
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
                        message = "Đã ghi nhớ ý định bật; quyền lợi sẽ được xác minh lại sau khi nhập công cụ tương thích với engine bản gốc")
                    return@request
                }
                val raw = withContext(workDispatcher) { definition()?.copyOf() }
                if (!isCurrent(token, current)) return@request
                if (raw == null) {
                    mutableState.value = state.value.copy(loading = false,
                        message = "Chưa nhập tài nguyên AI Master điều chỉnh âm thanh bản gốc, hiện tiếp tục dùng hiệu ứng âm thanh thủ công")
                    return@request
                }
                val target = current.target
                if (target == null || target.id <= 0 || target.source != MusicSource.NETEASE || target.video) {
                    // Validate the default resource and enforce mutual exclusion even before playback.
                    withContext(workDispatcher) { OriginalAiMasterCodec(raw) }
                    if (!isCurrent(token, current)) return@request
                    beforeEnable()
                    if (!isCurrent(token, current)) return@request
                    if (right.expiresAt <= now()) { revoke("Quyền lợi SVIP Nhạc Vân NetEase đã hết hạn, đã khôi phục hiệu ứng âm thanh thủ công"); return@request }
                    mutableState.value = state.value.copy(enabled = true, loading = false,
                        message = "Đã bật, tự động khớp hiệu ứng âm thanh bản gốc khi phát bài hát Nhạc Vân NetEase")
                    return@request
                }
                val material = lastMaterial?.takeIf { it.first == current }?.second ?: prepare(target.id)
                if (!isCurrent(token, current)) return@request
                lastMaterial = current to material
                val prepared = withContext(workDispatcher) {
                    OriginalAiMasterCodec(raw).compose(material.base, material.diy, material.baseKey, material.song,
                        selected = selectedDiy, supportStereo = current.stereo)
                }
                if (!isCurrent(token, current)) return@request
                beforeEnable()
                if (!isCurrent(token, current)) return@request
                if (right.expiresAt <= now()) { revoke("Quyền lợi AI Master điều chỉnh âm thanh Nhạc Vân NetEase đã hết hạn, đã khôi phục hiệu ứng âm thanh thủ công"); return@request }
                mutableSelection.value = prepared.preset()
                mutableState.value = state.value.copy(enabled = true, loading = false, processing = false,
                    description = material.description, baseName = material.baseName, longText = material.longText,
                    selectedDiy = selectedDiy, usedDiy = prepared.usedDiy.toSet(), diySaving = diySaving,
                    message = if (prepared.songSpecific) "Đã khớp AI Master điều chỉnh âm thanh bản gốc, đang chờ xử lý âm thanh" else "Đã khớp mẫu cơ bản điều chỉnh âm thanh bản gốc, đang chờ xử lý âm thanh")
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: LinkageError) {
                if (isCurrent(token, current)) reportFailure("Không tải được engine AI Master điều chỉnh âm thanh bản gốc, vui lòng nhập lại gói cài đặt tương thích rồi thử lại")
            }
            catch (_: Exception) {
                if (isCurrent(token, current)) mutableState.value = state.value.copy(enabled = false, loading = false,
                    processing = false, message = "AI Master điều chỉnh âm thanh bản gốc hiện không khả dụng, vui lòng kiểm tra mạng hoặc tài nguyên rồi thử lại; hiện đang dùng hiệu ứng âm thanh thủ công")
            }
        }
    }

    private fun isCurrent(token: Long, expected: Context): Boolean =
        !closed && token == generation && context == expected && requested.enabled

    private fun revoke(message: String) {
        requested = OriginalSmartPreferences()
        val token = invalidate()
        mutableState.value = OriginalAiMasterState(available = context?.engine ?: false,
            songId = context?.target?.id, selectedDiy = selectedDiy, diySaving = diySaving, message = message)
        persistOff(token)
    }

    private fun persistOff(token: Long) {
        scope.launch {
            try { persist(OriginalSmartPreferences(), token) }
            catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) {
                if (!closed && token == generation) mutableState.value = state.value.copy(
                    message = "AI Master điều chỉnh âm thanh bản gốc đã tắt nhưng không lưu được cài đặt, vui lòng thử lại sau")
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
        if (closed || presetId != ORIGINAL_AI_MASTER_PRESET_ID || selection.value?.id != presetId || !state.value.enabled) return
        if (permissionExpiresAt <= now()) evaluate()
        else mutableState.value = state.value.copy(processing = true, message = "AI Master điều chỉnh âm thanh bản gốc Nhạc Vân NetEase đang xử lý âm thanh")
    }

    /** A native failure withdraws output but keeps the user's account-bound retry intention. */
    fun reportFailure(message: String) {
        if (closed) return
        invalidate()
        mutableState.value = state.value.copy(enabled = false, loading = false, processing = false,
            requestedEnabled = requested.enabled, description = null, baseName = null, longText = null,
            usedDiy = emptySet(),
            message = message.take(200).ifBlank { "Xử lý bằng AI Master điều chỉnh âm thanh bản gốc thất bại, hiện đang dùng hiệu ứng âm thanh thủ công; có thể thử lại" })
    }

    override fun close() {
        if (closed) return
        invalidate(); closed = true; diyGeneration++; observer.cancel()
        mutableState.value = state.value.copy(enabled = false, loading = false, processing = false)
        // Container disposal is not a user request to erase an account-bound saved intent.
    }

}
