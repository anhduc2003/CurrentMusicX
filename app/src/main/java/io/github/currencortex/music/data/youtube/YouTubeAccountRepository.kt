package io.github.currencortex.music.data.youtube

import io.github.currencortex.music.core.network.ApiException
import io.github.currencortex.music.core.network.ApiJson
import io.github.currencortex.music.core.network.AppResult
import io.github.currencortex.music.core.network.ErrorKind
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class YouTubeAccountRepository(private val sessions: YouTubeSessionStore,
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).build()) {

    suspend fun login(cookies: Map<String, String>): AppResult<YouTubeAccountState> = withContext(Dispatchers.IO) {
        try {
            val jar = cookies.mapValues { it.value.trim() }.filterValues { it.isNotBlank() }
            if (jar.none { it.key in AUTH_COOKIES }) return@withContext AppResult.Failure(ErrorKind.Unauthorized)
            val expected = sessions.snapshot()
            val profile = profile(jar) ?: YouTubeProfile()
            sessions.login(jar, profile, expected)
            AppResult.Success(sessions.state.value)
        } catch (e: CancellationException) { throw e }
        catch (e: ApiException) { AppResult.Failure(e.kind) }
        catch (_: Exception) { AppResult.Failure(ErrorKind.Network) }
    }

    suspend fun logout(): AppResult<YouTubeAccountState> = withContext(Dispatchers.IO) {
        try {
            sessions.logout(sessions.snapshot())
            AppResult.Success(sessions.state.value)
        } catch (e: CancellationException) { throw e }
        catch (e: ApiException) { AppResult.Failure(e.kind) }
        catch (_: Exception) { AppResult.Failure(ErrorKind.Network) }
    }

    suspend fun refresh(): AppResult<YouTubeAccountState> = withContext(Dispatchers.IO) {
        try {
            val expected = sessions.snapshot()
            val jar = expected.credential.cookies
            if (jar.isEmpty()) return@withContext AppResult.Success(sessions.state.value)
            val profile = profile(jar) ?: return@withContext AppResult.Failure(ErrorKind.Unauthorized)
            sessions.updateProfile(profile, expected)
            AppResult.Success(sessions.state.value)
        } catch (e: CancellationException) { throw e }
        catch (e: ApiException) { AppResult.Failure(e.kind) }
        catch (_: Exception) { AppResult.Failure(ErrorKind.Network) }
    }

    suspend fun cookies(): Map<String, String> = sessions.snapshot().credential.cookies

    private fun profile(jar: Map<String, String>): YouTubeProfile? {
        val body = buildJsonObject {
            putJsonObject("context") {
                putJsonObject("client") {
                    put("clientName", "WEB")
                    put("clientVersion", CLIENT_VERSION)
                    put("hl", "en")
                    put("gl", "US")
                }
            }
        }
        val root = post(ACCOUNTS_URL, body, jar) ?: return null
        return profileOf(root)
    }

    private fun post(url: String, body: JsonObject, jar: Map<String, String>): JsonObject? {
        val request = Request.Builder().url(url)
            .header("User-Agent", USER_AGENT)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Origin", "https://www.youtube.com")
            .header("Cookie", jar.entries.joinToString("; ") { "${it.key}=${it.value}" })
            .post(body.toString().toRequestBody(JSON))
            .build()
        return client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            val payload = response.body?.string()?.takeIf { it.isNotBlank() } ?: return null
            runCatching { ApiJson.parseToJsonElement(payload).jsonObject }.getOrNull()
        }
    }

    private fun profileOf(root: JsonObject): YouTubeProfile? {
        var found: JsonObject? = null
        fun visit(node: JsonElement) {
            if (found != null) return
            when (node) {
                is JsonObject -> {
                    val item = node["activeAccountHeaderRenderer"] as? JsonObject
                        ?: node["accountItemSectionRenderer"] as? JsonObject
                    if (item != null) { found = item; return }
                    node.values.forEach { visit(it) }
                }
                is JsonArray -> node.forEach { visit(it) }
                else -> Unit
            }
        }
        visit(root)
        val item = found ?: return null
        val name = item["accountName"]?.textOf().orEmpty()
        val email = item["email"]?.textOf().orEmpty()
        val avatar = ((item["accountPhoto"] as? JsonObject)?.get("thumbnails") as? JsonArray)
            ?.firstOrNull()?.let { (it as? JsonObject)?.get("url")?.let { url -> (url as? JsonPrimitive)?.contentOrNull } }.orEmpty()
        if (name.isBlank() && email.isBlank()) return null
        return YouTubeProfile(email.ifBlank { name }, name.ifBlank { email }, email, avatar)
    }

    private fun JsonElement.textOf(): String = when (this) {
        is JsonPrimitive -> contentOrNull.orEmpty()
        is JsonObject -> (get("simpleText") as? JsonPrimitive)?.contentOrNull
            ?: ((get("runs") as? JsonArray)?.firstOrNull() as? JsonObject)?.get("text")?.textOf()
            ?: (get("text") as? JsonElement)?.textOf().orEmpty()
        is JsonArray -> firstOrNull()?.textOf().orEmpty()
        else -> ""
    }

    private companion object {
        const val CLIENT_VERSION = "2.20240726.00.00"
        const val USER_AGENT = "Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36"
        const val ACCOUNTS_URL = "https://www.youtube.com/youtubei/v1/account/accounts_list"
        val JSON = "application/json; charset=utf-8".toMediaType()
        val AUTH_COOKIES = setOf("SID", "__Secure-1PSID", "__Secure-3PSID", "SAPISID",
            "__Secure-1PAPISID", "__Secure-3PAPISID")
    }
}