package io.github.currencortex.music.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.storage.StorageUsage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StorageState(val usage: StorageUsage = StorageUsage(), val busy: Boolean = false, val message: String? = null)

class StorageViewModel(private val container: AppContainer) : ViewModel() {
    val state = MutableStateFlow(StorageState())
    val audioBytes = container.audioCache.bytes
    val audioTracks = container.audioCache.entries

    fun refresh() = viewModelScope.launch {
        val usage = container.storage.measure()
        state.update { it.copy(usage = usage) }
    }

    fun clearData() = action("Đã xóa bộ nhớ đệm dữ liệu") { container.storage.clearDataCache() }

    fun clearAudio() = action("Đã xóa bộ nhớ đệm nhạc") { container.storage.clearAudioCache() }

    private fun action(done: String, block: suspend () -> Unit) = viewModelScope.launch {
        if (state.value.busy) return@launch
        state.update { it.copy(busy = true, message = null) }
        val failure = runCatching { block() }.exceptionOrNull()
        val usage = runCatching { container.storage.measure() }.getOrNull()
        state.update {
            it.copy(busy = false, message = failure?.message ?: done, usage = usage ?: it.usage)
        }
    }
}
