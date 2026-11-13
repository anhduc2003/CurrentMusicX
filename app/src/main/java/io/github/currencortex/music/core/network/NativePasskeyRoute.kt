package io.github.currencortex.music.core.network

import io.github.currencortex.music.core.config.ServerDefaults
import okhttp3.HttpUrl.Companion.toHttpUrl

/** Passkey ceremonies must not follow automatic CDN selection. */
object NativePasskeyRoute {
    fun server(server: String): String {
        val normalized = ServerUrl.normalize(server)
        val pinned = if (CurrentMusicServer.builtIn(normalized)) ServerDefaults.URL else normalized
        require(pinned.toHttpUrl().isHttps) { "Khóa truy cập cần máy chủ HTTPS" }
        return pinned
    }
    fun rpId(base: String) = server(base).toHttpUrl().host
}
