package io.github.currencortex.music.feature.youtube

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.network.AppResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class YouTubeAccountUiState(val busy: Boolean = false, val message: String? = null, val failed: Boolean = false)

class YouTubeAccountViewModel(private val container: AppContainer) : ViewModel() {
    val account = container.youtubeSessions.state
    val state = MutableStateFlow(YouTubeAccountUiState())

    fun login(cookies: Map<String, String>) {
        if (state.value.busy) return
        val jar = cookies.filterValues { it.isNotBlank() }
        if (jar.isEmpty()) {
            state.value = YouTubeAccountUiState(message = "Cookie không hợp lệ", failed = true)
            return
        }
        state.value = YouTubeAccountUiState(busy = true)
        viewModelScope.launch {
            try {
                when (val result = container.youtubeAccount.login(jar)) {
                    is AppResult.Success -> {
                        state.value = YouTubeAccountUiState(message = "Đã đăng nhập " +
                            result.value.profile?.name?.ifBlank { result.value.profile?.email.orEmpty() }.orEmpty())
                        container.audioSources.invalidate()
                    }
                    is AppResult.Failure -> state.value = YouTubeAccountUiState(message = result.message, failed = true)
                }
            } finally { state.value = state.value.copy(busy = false) }
        }
    }

    fun importCookies(raw: String) {
        val jar = parseCookies(raw)
        if (jar.isEmpty()) {
            state.value = YouTubeAccountUiState(message = "Cookie không hợp lệ", failed = true)
            return
        }
        login(jar)
    }

    fun logout() {
        if (state.value.busy) return
        state.value = YouTubeAccountUiState(busy = true)
        viewModelScope.launch {
            try {
                when (val result = container.youtubeAccount.logout()) {
                    is AppResult.Success -> {
                        state.value = YouTubeAccountUiState(message = "Đã đăng xuất YouTube")
                        container.audioSources.invalidate()
                    }
                    is AppResult.Failure -> state.value = YouTubeAccountUiState(message = result.message, failed = true)
                }
            } finally { state.value = state.value.copy(busy = false) }
        }
    }

    companion object {
        fun parseCookies(raw: String): Map<String, String> = raw.split(';').mapNotNull { part ->
            val trimmed = part.trim()
            val index = trimmed.indexOf('=')
            if (index <= 0) null else trimmed.substring(0, index).trim() to trimmed.substring(index + 1).trim()
        }.toMap().filterValues { it.isNotBlank() }
    }
}