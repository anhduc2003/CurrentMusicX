package io.github.currencortex.music.core.network

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

object ServerUrl {
    fun normalize(value: String): String {
        val url = value.trim().toHttpUrlOrNull() ?: throw IllegalArgumentException("Vui lòng nhập địa chỉ máy chủ HTTP/HTTPS hợp lệ")
        require(url.username.isEmpty() && url.password.isEmpty() && url.query == null && url.fragment == null) {
            "Địa chỉ máy chủ không được chứa thông tin xác thực, truy vấn hoặc fragment"
        }
        return url.newBuilder().encodedPath(url.encodedPath.trimEnd('/') + "/").build().toString()
    }
    fun endpoint(base: String, path: String, query: Map<String, String>): HttpUrl {
        require(!path.contains("..") && !path.contains("://"))
        val builder = normalize(base).toHttpUrlOrNull()!!.newBuilder()
        builder.addEncodedPathSegments(path.trimStart('/'))
        query.forEach { (key, value) -> builder.addQueryParameter(key, value) }
        return builder.build()
    }
}
