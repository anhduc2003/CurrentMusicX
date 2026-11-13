package io.github.currencortex.music.core.media

import io.github.currencortex.music.core.netease.NeteaseSessionStore
import io.github.currencortex.music.data.effects.NcaeDecoder
import io.github.currencortex.music.data.effects.NeteaseSoundEffect
import io.github.currencortex.music.data.effects.NeteaseSoundEffectsRepository
import io.github.currencortex.music.data.effects.NeteaseEffectEntitlement
import io.github.currencortex.music.core.network.ApiException
import io.github.currencortex.music.core.network.ErrorKind
import io.github.currencortex.music.data.settings.OriginalSoundEffectsSettingsRepository
import io.github.currencortex.music.data.settings.SavedOriginalSoundEffect
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal data class OriginalEffectsAccount(val ownerKey: String, val revision: Long)

data class OriginalSoundEffectsState(
    val available: Boolean = false,
    val loading: Boolean = false,
    val selectedId: Long? = null,
    val selectedName: String? = null,
    val error: String? = null,
    val requestedId: Long? = null,
    val requestedName: String? = null,
    val restoring: Boolean = false,
    val processing: Boolean = false,
    val unavailableReason: String? = null,
    val unsupported: Map<Long, String> = emptyMap(),
    val permissionPending: Boolean = false,
    val permissionExpiresAt: Long? = null,
)

/** Every restored ID must pass current catalog, account rights, checksum and decoder again. */
class OriginalSoundEffectsController internal constructor(
    accounts: Flow<OriginalEffectsAccount>,
    private val ready: suspend () -> Unit,
    private val readSaved: suspend () -> SavedOriginalSoundEffect?,
    private val writeSaved: suspend (SavedOriginalSoundEffect?) -> Unit,
    private val catalog: suspend () -> List<NeteaseSoundEffect>,
    private val prepare: suspend (NeteaseSoundEffect) -> NeteaseOriginalPreset,
    private val scope: CoroutineScope,
    private val engineAvailable: () -> Boolean,
    private val entitlement: suspend () -> NeteaseEffectEntitlement = { NeteaseEffectEntitlement() },
    private val now: () -> Long = System::currentTimeMillis,
    private val resolveFromServer: suspend (Long) -> NeteaseSoundEffect? = { null },
    private val beforeSelection: suspend () -> Unit,
) : java.io.Closeable {
    constructor(repository: NeteaseSoundEffectsRepository, sessions: NeteaseSessionStore,
        settings: OriginalSoundEffectsSettingsRepository, scope: CoroutineScope, beforeSelection: suspend () -> Unit) : this(
        accounts = sessions.state.map {
            val uid = it.profile?.uid?.takeIf { value -> value > 0 && it.loggedIn }
            OriginalEffectsAccount(uid?.let { value -> "netease:$value" } ?: "anonymous", it.revision)
        }.distinctUntilChanged(),
        ready = { sessions.ready.await(); Unit }, readSaved = settings::snapshot, writeSaved = settings::set,
        catalog = repository::catalog,
        prepare = { effect ->
            withContext(Dispatchers.Default) {
                val decoded = NcaeDecoder.decode(repository.downloadPreset(effect))
                try {
                    when (decoded.type) {
                        1 -> NeteasePresetParameters.fromJson(decoded.bytes.toString(Charsets.UTF_8))
                        2 -> NeteasePresetParameters.fromFir(decoded.bytes)
                        else -> throw IllegalArgumentException("Loại tài nguyên cài đặt trước không được hỗ trợ")
                    }
                } catch (failure: IllegalArgumentException) {
                    throw UnsupportedOperationException(failure.message ?: "Phiên bản tham số không được hỗ trợ", failure)
                }
                NeteaseOriginalPreset(effect.id, effect.name, decoded.type, decoded.bytes)
            }
        }, scope = scope, engineAvailable = NeteaseNativeEngine::available,
        entitlement = repository::originalPresetEntitlement, resolveFromServer = repository::resolve,
        beforeSelection = beforeSelection)

    private val mutableSelection = MutableStateFlow<NeteaseOriginalPreset?>(null)
    val selection: StateFlow<NeteaseOriginalPreset?> = mutableSelection.asStateFlow()
    private val mutableState = MutableStateFlow(OriginalSoundEffectsState(available = engineAvailable(),
        unavailableReason = if (engineAvailable()) null else "Chưa nhập công cụ hiệu ứng âm thanh gốc tương thích của NetEase"))
    val state: StateFlow<OriginalSoundEffectsState> = mutableState.asStateFlow()
    private var generation = 0L
    private var pending: Job? = null
    private var expiry: Job? = null
    private var currentAccount: OriginalEffectsAccount? = null
    private val accountReady = CompletableDeferred<OriginalEffectsAccount>()
    private val persistence = Mutex()
    private var lastSavedGeneration = -1L
    private var closed = false
    private val startupGeneration = generation
    private val accountObserver = scope.launch {
        ready()
        accounts.distinctUntilChanged().collect { account ->
            val previous = currentAccount
            currentAccount = account
            accountReady.complete(account)
            if (previous == null) {
                val token = startupGeneration
                val saved = readSaved()
                if (token != generation || closed) return@collect
                if (saved != null && saved.ownerKey != account.ownerKey) {
                    persist(null, token)
                } else if (saved != null) {
                    startLoad(null, saved.effectId, saved.name, restoring = true)
                }
            } else {
                // Even a login/logout for the same uid invalidates in-flight account material.
                clear()
            }
        }
    }

    private suspend fun persist(value: SavedOriginalSoundEffect?, token: Long) = persistence.withLock {
        // A newer loading request must not cancel a user's previous explicit "off" edit.
        // A newer successfully saved choice, however, must not be removed by that old edit.
        if (!closed && (token == generation || value == null && token >= lastSavedGeneration)) {
            writeSaved(value)
            lastSavedGeneration = token
        }
    }

    fun select(effect: NeteaseSoundEffect) {
        if (closed) return
        val checked = state.value.copy(unsupported = state.value.unsupported - effect.id)
        val reason = originalEffectUnavailableReason(effect, checked)
        if (reason != null) {
            mutableState.value = state.value.copy(error = reason)
            return
        }
        mutableState.value = checked
        startLoad(effect, effect.id, effect.name, restoring = false)
    }

    fun retry() {
        val value = state.value
        val id = value.requestedId ?: return
        if (closed || value.loading) return
        mutableState.value = value.copy(unsupported = value.unsupported - id)
        startLoad(null, id, value.requestedName.orEmpty(), restoring = value.restoring)
    }

    /** Cancel a superseded preparation without removing the currently audible preset. */
    fun cancelPendingSelection() {
        if (closed || !state.value.loading) return
        generation++
        pending?.cancel()
        pending = null
        mutableState.value = state.value.copy(loading = false, requestedId = selection.value?.id,
            requestedName = selection.value?.name, restoring = false, permissionPending = false, error = null)
    }

    private fun startLoad(candidate: NeteaseSoundEffect?, id: Long, name: String, restoring: Boolean) {
        val token = ++generation
        pending?.cancel()
        mutableState.value = state.value.copy(loading = true, error = null, requestedId = id,
            requestedName = name, restoring = restoring, permissionPending = false)
        pending = scope.launch {
            try {
                ready()
                accountReady.await()
                val owner = requireNotNull(currentAccount)
                if (!state.value.available) throw UnsupportedOperationException(state.value.unavailableReason)
                val effect = candidate ?: resolveFromServer(id) ?: catalog().firstOrNull { it.id == id }
                    ?: throw UnsupportedOperationException("Hiệu ứng này đã bị xóa khỏi danh mục NetEase và không thể khôi phục cài đặt trước cũ")
                if (token != generation || owner != currentAccount) return@launch
                if (effect.id != id) throw UnsupportedOperationException("ID hiệu ứng âm thanh do NetEase trả về không khớp với mục đã chọn")
                originalEffectUnavailableReason(effect, state.value)?.let { throw UnsupportedOperationException(it) }
                val permission = if (effect.legacyMemberOriginal) {
                    mutableState.value = state.value.copy(permissionPending = true)
                    val uid = owner.ownerKey.removePrefix("netease:").toLongOrNull()
                        ?: throw OriginalEffectPermissionException("Vui lòng đăng nhập tại “Cá nhân → Tài khoản NetEase” trước khi xác minh quyền hội viên Vinyl")
                    val rights = entitlement()
                    if (token != generation || owner != currentAccount) return@launch
                    val deadline = rights.expiryFor(effect.memberType)
                    if (rights.userId != uid || deadline <= now())
                        throw OriginalEffectPermissionException("Tài khoản hiện tại không có quyền ${if (effect.memberType == 4) "SVIP" else "hội viên Vinyl"} hợp lệ của NetEase, nên hiệu ứng chưa được bật")
                    deadline
                } else null
                mutableState.value = state.value.copy(permissionPending = false)
                val prepared = prepare(effect)
                require(prepared.id == effect.id) { "Tài nguyên hiệu ứng không khớp với mục đã chọn" }
                if (token != generation || owner != currentAccount) return@launch
                if (permission != null && permission <= now())
                    throw OriginalEffectPermissionException("Quyền hội viên Vinyl đã hết hạn, hiệu ứng chưa được bật")
                beforeSelection()
                if (token != generation || owner != currentAccount) return@launch
                if (permission != null && permission <= now())
                    throw OriginalEffectPermissionException("Quyền hội viên Vinyl đã hết hạn, hiệu ứng chưa được bật")
                val previousChoice = selection.value?.let { SavedOriginalSoundEffect(it.id, it.name, owner.ownerKey) }
                persist(SavedOriginalSoundEffect(effect.id, effect.name, owner.ownerKey), token)
                if (token != generation || owner != currentAccount) return@launch
                if (permission != null && permission <= now()) {
                    persist(previousChoice, token)
                    throw OriginalEffectPermissionException("Quyền hội viên Vinyl đã hết hạn, hiệu ứng chưa được bật")
                }
                mutableSelection.value = prepared
                mutableState.value = state.value.copy(loading = false, selectedId = effect.id,
                    selectedName = effect.name, requestedName = effect.name, restoring = false, processing = false, error = null,
                    permissionPending = false, permissionExpiresAt = permission)
                watchExpiry(prepared, permission)
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (failure: OriginalEffectPermissionException) {
                permissionFailed(token, restoring, failure.message.orEmpty())
            } catch (failure: ApiException) {
                if (failure.kind == ErrorKind.NeteaseBindingRequired)
                    permissionFailed(token, restoring, "Đăng nhập NetEase đã hết hiệu lực, vui lòng đăng nhập lại tại “Cá nhân → Tài khoản NetEase”")
                else if (failure.kind == ErrorKind.Forbidden)
                    permissionFailed(token, restoring, "NetEase chưa cấp cho tài khoản hiện tại quyền sử dụng hiệu ứng này")
                else if (token == generation) mutableState.value = state.value.copy(loading = false, permissionPending = false,
                    error = "Không thể xác minh tài nguyên hoặc quyền hiệu ứng của NetEase, hãy kiểm tra mạng rồi thử lại")
            }
            catch (failure: UnsupportedOperationException) {
                if (token == generation) {
                    val reason = failure.message?.take(160) ?: "Phiên bản tham số của hiệu ứng này hiện chưa được hỗ trợ"
                    mutableState.value = state.value.copy(loading = false, permissionPending = false, error = reason,
                        unsupported = if (state.value.available) state.value.unsupported + (id to reason) else state.value.unsupported)
                    // Do not repeatedly restore a preset whose current catalog/format is incompatible.
                    if (restoring && state.value.available) persist(null, token)
                }
            } catch (_: Exception) {
                if (token == generation) mutableState.value = state.value.copy(loading = false, permissionPending = false,
                    error = if (restoring) "Tự động khôi phục chưa hoàn tất, hãy kiểm tra mạng rồi thử lại; hiện vẫn giữ hiệu ứng trên thiết bị"
                        else "Tải hiệu ứng gốc chưa hoàn tất, hãy thử lại; hiệu ứng hiện tại không thay đổi")
            }
        }
    }

    private suspend fun permissionFailed(token: Long, restoring: Boolean, message: String) {
        if (token != generation) return
        mutableState.value = state.value.copy(loading = false, permissionPending = false, error = message)
        if (restoring) persist(null, token)
    }

    private fun watchExpiry(preset: NeteaseOriginalPreset, expiresAt: Long?) {
        expiry?.cancel()
        expiry = expiresAt?.let { deadline -> scope.launch {
            while (selection.value === preset) {
                val remaining = deadline - now()
                if (remaining <= 0) {
                    reportFailure("Quyền hội viên Vinyl của NetEase đã hết hạn; đã tắt hiệu ứng hội viên gốc và khôi phục cài đặt trên thiết bị")
                    return@launch
                }
                delay(minOf(remaining, 60_000L))
            }
        } }
    }

    fun clear() {
        if (closed) return
        val token = clearMemory()
        scope.launch { persist(null, token) }
    }

    private fun clearMemory(): Long {
        val token = ++generation
        pending?.cancel()
        expiry?.cancel()
        expiry = null
        pending = null
        mutableSelection.value = null
        mutableState.value = OriginalSoundEffectsState(available = state.value.available,
            unavailableReason = state.value.unavailableReason, unsupported = state.value.unsupported)
        return token
    }

    /** Only successful PCM processing may acknowledge this state; selection/playing alone is insufficient. */
    fun reportProcessing(presetId: Long) {
        if (closed || selection.value?.id != presetId) return
        if (state.value.permissionExpiresAt?.let { it <= now() } == true)
            reportFailure("Quyền hội viên Vinyl của NetEase đã hết hạn; đã tắt hiệu ứng hội viên gốc và khôi phục cài đặt trên thiết bị")
        else mutableState.value = state.value.copy(processing = true)
    }

    fun refreshAvailability() {
        if (closed) return
        val available = engineAvailable()
        mutableState.value = state.value.copy(available = available,
            unavailableReason = if (available) null else "Chưa nhập công cụ hiệu ứng âm thanh gốc tương thích của NetEase",
            error = if (available && !state.value.available) null else state.value.error)
        if (available && selection.value == null && state.value.requestedId != null) retry()
    }

    fun reportFailure(message: String) {
        if (closed) return
        clear()
        mutableState.value = state.value.copy(error = message)
    }

    override fun close() {
        if (closed) return
        clearMemory()
        closed = true
        accountObserver.cancel()
        // Closing the container is not a user request to forget a successfully saved effect.
    }
}

internal fun originalEffectUnavailableReason(effect: NeteaseSoundEffect, state: OriginalSoundEffectsState): String? = when {
    !effect.legacyOriginalStrategy -> "Hiệu ứng này dùng cơ chế cấp quyền NetEase chưa được tích hợp; hãy sử dụng trong ứng dụng NetEase"
    effect.memberType !in setOf(1, 3, 4) -> "Loại quyền hội viên của hiệu ứng này chưa được tích hợp; hãy sử dụng trong ứng dụng NetEase"
    effect.soundType != 1 -> "Loại hiệu ứng này chưa được tích hợp vào chuỗi phát trên thiết bị"
    !effect.compatibleOriginalResource -> "NetEase chưa cung cấp tài nguyên cài đặt trước tương thích có thể xác minh"
    !state.available -> state.unavailableReason ?: "Động cơ hiệu ứng âm thanh gốc chưa được tải"
    else -> state.unsupported[effect.id]
}

private class OriginalEffectPermissionException(message: String) : Exception(message)
