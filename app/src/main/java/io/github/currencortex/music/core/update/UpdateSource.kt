package io.github.currencortex.music.core.update

import io.github.currencortex.music.core.config.AppMetadata
import java.net.URI
import okhttp3.HttpUrl.Companion.toHttpUrl

enum class UpdateSource(val label: String, val prefix: String) {
    CURRENTMUSIC("Mirror riêng cho CurrentMusic", ""),
    GITHUB("Trang chính thức của GitHub", ""),
    TBAP("github.tbap.top", "https://github.tbap.top/"),
    GH_PROXY("gh-proxy.com", "https://gh-proxy.com/"),
    GEEKERTAO("ghfile.geekertao.top", "https://ghfile.geekertao.top/"),
    DPIK("github.dpik.top", "https://github.dpik.top/");

    /** Try the requested source once, then the other mirrors, with the origin as the final fallback. */
    fun fallbacks(): List<UpdateSource> = when (this) {
        CURRENTMUSIC -> listOf(CURRENTMUSIC, GITHUB) + available.filter { it != CURRENTMUSIC && it != GITHUB }
        GITHUB -> listOf(GITHUB) + available.filter { it != GITHUB }
        else -> listOf(this) + available.filter { it != this && it != GITHUB } + GITHUB
    }

    fun url(release: AppRelease, proxy: UpdateProxy = UpdateProxy.current): String {
        val official = requireNotNull(release.apkUrl) { "Bản phát hành này không có APK" }
        require(official.startsWith("${AppMetadata.RELEASES_PROJECT_URL}/releases/download/")) { "Nguồn cập nhật không khớp" }
        if (this == CURRENTMUSIC) {
            require(!release.isCi) { "Mirror riêng cho CurrentMusic chỉ cung cấp bản phát hành chính thức" }
            return proxy.assetUrl(official.toHttpUrl())
        }
        if (prefix.isNotEmpty()) require(prefix.startsWith("https://")) { "Nguồn tải xuống phải sử dụng HTTPS" }
        val result = prefix + official
        val uri = URI(result)
        require(uri.scheme == "https" && uri.rawUserInfo == null) { "Nguồn tải xuống phải sử dụng HTTPS" }
        return result
    }

    companion object {
        val available: List<UpdateSource> get() = entries.filter { it != CURRENTMUSIC || UpdateProxy.current.configured }
        val default: UpdateSource get() = if (UpdateProxy.current.configured) CURRENTMUSIC else GITHUB
        fun forChannel(channel: UpdateChannel) = available.filter { channel != UpdateChannel.CI || it != CURRENTMUSIC }
    }
}
