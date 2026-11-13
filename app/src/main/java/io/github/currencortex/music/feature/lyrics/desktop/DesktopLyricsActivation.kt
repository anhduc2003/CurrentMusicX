package io.github.currencortex.music.feature.lyrics.desktop

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DesktopActivationState(val connecting: Boolean = false, val error: String? = null)

/** A late connection must never override a newer close or revoked permission. */
internal class DesktopLyricsActivation(private val scope: CoroutineScope,
    private val permitted: () -> Boolean, private val connect: suspend () -> Unit,
    private val saveEnabled: suspend (Boolean) -> Unit) {
    private var revision = 0
    private var operation: Job? = null
    private val mutable = MutableStateFlow(DesktopActivationState())
    val state = mutable.asStateFlow()
    fun request(enabled: Boolean) {
        val request = ++revision
        operation?.cancel()
        mutable.value = DesktopActivationState(connecting = enabled)
        operation = scope.launch {
            try {
                if (enabled) {
                     if (!permitted()) throw IllegalStateException("Vui lòng cấp quyền cửa sổ nổi trước")
                    connect()
                    ensureActive()
                     if (!permitted()) throw IllegalStateException("Quyền cửa sổ nổi đã bị tắt, vui lòng bật lại")
                }
                if (request == revision) saveEnabled(enabled)
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (failure: Exception) {
                if (request == revision) {
                    saveEnabled(false)
                    mutable.value = DesktopActivationState(error = if (failure is IllegalStateException)
                         failure.message else "Không thể bật lời bài hát trên màn hình nền, vui lòng thử lại")
                }
            } finally { if (request == revision) mutable.value = mutable.value.copy(connecting = false) }
        }
    }
    fun close() { ++revision; operation?.cancel() }
}
