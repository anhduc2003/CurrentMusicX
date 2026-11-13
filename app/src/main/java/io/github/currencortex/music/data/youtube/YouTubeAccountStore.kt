package io.github.currencortex.music.data.youtube

import io.github.currencortex.music.core.network.ApiException
import io.github.currencortex.music.core.network.ApiJson
import io.github.currencortex.music.core.network.ErrorKind
import io.github.currencortex.music.core.security.TokenStore
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

@Serializable data class YouTubeProfile(val id: String = "", val name: String = "", val email: String = "",
    val avatar: String = "")
@Serializable data class YouTubeCredential(val cookies: Map<String, String> = emptyMap(),
    val profile: YouTubeProfile? = null, val deviceId: String = "", val stale: Boolean = false)
data class YouTubeSession(val credential: YouTubeCredential, val revision: Long)
data class YouTubeAccountState(val profile: YouTubeProfile? = null, val loggedIn: Boolean = false,
    val stale: Boolean = false, val revision: Long = 0)

class YouTubeSessionStore(private val vault: TokenStore, scope: CoroutineScope) {
    private val mutex = Mutex()
    @Volatile private var current = YouTubeCredential()
    val state = MutableStateFlow(YouTubeAccountState())
    val ready = CompletableDeferred<Unit>()
    init { scope.launch(Dispatchers.IO) {
        mutex.withLock {
            var restored = false
            current = try {
                (vault.read()?.let { ApiJson.decodeFromString(YouTubeCredential.serializer(), it) }
                    ?: YouTubeCredential()).also { restored = true }
            } catch (_: Exception) {
                YouTubeCredential()
            }
            if (restored) {
                try { vault.write(ApiJson.encodeToString(YouTubeCredential.serializer(), current)) }
                catch (_: Exception) { /* Keep the restored session usable in memory. */ }
            }
            publish()
            ready.complete(Unit)
        }
    }.invokeOnCompletion { error -> if (error != null) ready.completeExceptionally(error) } }

    private fun publish() {
        state.value = YouTubeAccountState(current.profile, current.cookies.any { it.value.isNotBlank() },
            current.stale, state.value.revision)
    }
    suspend fun snapshot(): YouTubeSession { ready.await(); return mutex.withLock { YouTubeSession(current, state.value.revision) } }
    fun check(expected: YouTubeSession) {
        if (state.value.revision != expected.revision) throw ApiException(ErrorKind.Unauthorized)
    }
    suspend fun login(cookies: Map<String, String>, profile: YouTubeProfile, expected: YouTubeSession) {
        require(cookies.any { it.value.isNotBlank() })
        ready.await()
        withContext(Dispatchers.IO) { mutex.withLock {
            check(expected)
            val record = YouTubeCredential(cookies, profile, current.deviceId, false)
            vault.write(ApiJson.encodeToString(YouTubeCredential.serializer(), record))
            current = record
            state.value = state.value.copy(revision = state.value.revision + 1)
            publish()
        } }
    }
    suspend fun updateProfile(profile: YouTubeProfile, expected: YouTubeSession) {
        ready.await()
        withContext(Dispatchers.IO) { mutex.withLock {
            check(expected)
            if (current.profile == profile) return@withLock
            val record = current.copy(profile = profile)
            vault.write(ApiJson.encodeToString(YouTubeCredential.serializer(), record))
            current = record
            publish()
        } }
    }
    suspend fun logout(expected: YouTubeSession) {
        ready.await()
        withContext(Dispatchers.IO) { mutex.withLock {
            check(expected)
            val record = YouTubeCredential(emptyMap(), null, current.deviceId, false)
            vault.write(ApiJson.encodeToString(YouTubeCredential.serializer(), record))
            current = record
            state.value = state.value.copy(revision = state.value.revision + 1)
            publish()
        } }
    }
}