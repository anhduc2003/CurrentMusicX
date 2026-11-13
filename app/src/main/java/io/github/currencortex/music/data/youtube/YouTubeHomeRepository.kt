package io.github.currencortex.music.data.youtube

import io.github.currencortex.music.data.library.Daily
import io.github.currencortex.music.data.song.MusicSource
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class YouTubeHomeRepository(private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build(),
    private val cookies: suspend () -> Map<String, String> = { emptyMap() }) {

    suspend fun daily(): Daily = withContext(Dispatchers.IO) {
        try {
            val root = browse() ?: return@withContext Daily(emptyList(), emptyList(), emptyList())
            val shelves = shelves(root)
            val daily = shelves.firstOrNull { it.songs.isNotEmpty() }?.songs.orEmpty()
            val forYou = shelves.drop(1).flatMap { it.songs }.distinctBy { it.id }.ifEmpty { shelves.flatMap { it.songs } }
            Daily(daily, forYou.distinctBy { it.id }, daily.take(20).map { it.artists })
        } catch (_: Exception) {
            Daily(emptyList(), emptyList(), emptyList())
        }
    }

    private suspend fun browse(): JsonObject? {
        val jar = cookies()
        val body = buildJsonObject {
            put("browseId", "FEmusic_home")
            putJsonObject("context") {
                putJsonObject("client") {
                    put("clientName", "WEB_REMIX")
                    put("clientVersion", "1.20240403.01.00")
                    put("hl", "en")
                    put("gl", "US")
                }
            }
        }
        val builder = Request.Builder().url("https://music.youtube.com/youtubei/v1/browse")
            .header("User-Agent", USER_AGENT)
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Origin", "https://music.youtube.com")
        if (jar.isNotEmpty()) builder.header("Cookie", jar.entries.joinToString("; ") { "${it.key}=${it.value}" })
        client.newCall(builder.post(body.toString().toRequestBody(JSON)).build()).execute().use { response ->
            if (!response.isSuccessful) return null
            val payload = response.body?.string()?.takeIf { it.isNotBlank() } ?: return null
            return runCatching { io.github.currencortex.music.core.network.ApiJson.parseToJsonElement(payload).jsonObject }.getOrNull()
        }
    }

    private fun shelves(root: JsonObject): List<Shelf> {
        val out = mutableListOf<Shelf>()
        fun visit(node: JsonElement) {
            when (node) {
                is JsonObject -> {
                    (node["musicShelfRenderer"] as? JsonObject)?.let { parseShelf(it, out) }
                    node.values.forEach { visit(it) }
                }
                is JsonArray -> node.forEach { visit(it) }
                else -> Unit
            }
        }
        visit(root)
        return out
    }

    private fun parseShelf(shelf: JsonObject, out: MutableList<Shelf>) {
        val title = shelf["title"]?.textOf().orEmpty()
        val contents = (shelf["contents"] as? JsonArray).orEmpty()
        val songs = contents.mapNotNull { element ->
            val renderer = (element as? JsonObject)?.get("musicResponsiveListItemRenderer") as? JsonObject ?: return@mapNotNull null
            song(renderer)
        }
        if (songs.isNotEmpty()) out += Shelf(title, songs)
    }

    private fun song(renderer: JsonObject): Song? {
        val videoId = renderer.videoId() ?: return null
        val columns = (renderer["flexColumns"] as? JsonArray).orEmpty()
        val title = columns.getOrNull(0)?.runs()?.filterNot { it.isBlank() || it in SEPARATORS }?.joinToString(" ")?.trim().orEmpty()
        if (title.isBlank()) return null
        val meta = columns.getOrNull(1)?.runs().orEmpty()
        val artists = meta.filterNot { it.isBlank() || it in SEPARATORS || it in KINDS }.joinToString(" ").trim()
        val duration = (columns + (renderer["fixedColumns"] as? JsonArray).orEmpty())
            .firstNotNullOfOrNull { element -> (element as? JsonObject)?.runs()?.firstNotNullOfOrNull { timestamp(it) } } ?: 0L
        val cover = renderer.cover()
        return Song(videoId.toSyntheticId(), title, artists, cover = cover, durationMs = duration,
            musicSource = MusicSource.NETEASE)
    }

    private fun JsonObject.videoId(): String? {
        fun from(node: JsonElement?): String? = when (node) {
            is JsonObject -> (node["videoId"] as? JsonPrimitive)?.contentOrNull?.takeIf { it.length == 11 }
                ?: node.values.firstNotNullOfOrNull { from(it) }
            is JsonArray -> node.firstNotNullOfOrNull { from(it) }
            else -> null
        }
        return from(this)
    }

    private fun JsonObject.cover(): String {
        val thumbnails = ((this["thumbnail"] as? JsonObject)?.get("musicThumbnailRenderer") as? JsonObject)
            ?.get("thumbnail")?.let { it as? JsonObject }?.get("thumbnails") as? JsonArray
            ?: (this["thumbnail"] as? JsonObject)?.get("thumbnails") as? JsonArray
        val url = thumbnails?.lastOrNull()?.let { (it as? JsonObject)?.get("url")?.let { url -> (url as? JsonPrimitive)?.contentOrNull } }.orEmpty()
        return url.replace(Regex("w\\d+-h\\d+"), "w544-h544")
    }

    private fun JsonElement.runs(): List<String> {
        val renderer = (this as? JsonObject)?.get("musicResponsiveListItemFlexColumnRenderer") as? JsonObject ?: return emptyList()
        return ((renderer["text"] as? JsonObject)?.get("runs") as? JsonArray).orEmpty()
            .mapNotNull { (it as? JsonPrimitive)?.contentOrNull }
    }

    private fun timestamp(value: String): Long? {
        val text = value.trim()
        if (!text.matches(Regex("\\d{1,2}:\\d{2}(:\\d{2})?"))) return null
        val parts = text.split(':').map { it.toLongOrNull() ?: 0L }
        return when (parts.size) {
            2 -> (parts[0] * 60 + parts[1]) * 1000
            3 -> (parts[0] * 3600 + parts[1] * 60 + parts[2]) * 1000
            else -> null
        }
    }

    private fun String.toSyntheticId(): Long {
        val id = (fold(0L) { acc, c -> acc * 31 + c.code } and Long.MAX_VALUE) or (1L shl 60)
        return if (id == 0L) 1L else id
    }

    private fun JsonElement.textOf(): String = when (this) {
        is JsonPrimitive -> contentOrNull.orEmpty()
        is JsonObject -> (get("simpleText") as? JsonPrimitive)?.contentOrNull
            ?: ((get("runs") as? JsonArray)?.firstOrNull() as? JsonObject)?.get("text")?.textOf()
            ?: (get("text") as? JsonElement)?.textOf().orEmpty()
        is JsonArray -> firstOrNull()?.textOf().orEmpty()
        else -> ""
    }

    private data class Shelf(val title: String, val songs: List<Song>)

    private companion object {
        const val USER_AGENT = "Mozilla/5.0 (Linux; Android 14; Pixel 8) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Mobile Safari/537.36"
        val JSON = "application/json; charset=utf-8".toMediaType()
        val SEPARATORS = setOf("•", "&", "|", "·")
        val KINDS = setOf("Song", "Video", "Artist", "Album", "Playlist", "Episode", "Podcast", "Profile")
    }
}