package io.github.currencortex.music.data.lx

import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.data.song.*
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import java.security.MessageDigest

internal const val LX_MAX_SCRIPT_BYTES = 1024 * 1024
internal const val LX_MAX_RESPONSE_BYTES = 2 * 1024 * 1024
internal val lxQualities = listOf("128k", "320k", "flac", "flac24bit")
internal val lxPlatforms = linkedMapOf("wy" to "Nhạc Vân NetEase")

/** Also applied to saved declarations from versions which exposed other platforms. */
internal fun lxNeteaseCapabilities(sources: List<LxCapability>): List<LxCapability> {
    val qualities = lxQualities.filter { quality -> sources.any { it.platform == "wy" && quality in it.qualities } }
    return if (qualities.isEmpty()) emptyList() else listOf(LxCapability("wy", qualities))
}

@Serializable data class LxCapability(val platform: String, val qualities: List<String>)
@Serializable data class LxSourceInfo(val id: String, val name: String, val description: String = "",
    val version: String = "", val author: String = "", val homepage: String = "",
    val origin: String = "", val capabilities: List<LxCapability> = emptyList()) {
    val summary get() = (if (lxNeteaseCapabilities(capabilities).isEmpty()) "Chưa khai báo API Nhạc Vân NetEase" else "Nhạc Vân NetEase") +
        version.takeIf(String::isNotBlank)?.let { " · $it" }.orEmpty()
}
@Serializable internal class LxStoredSource(val info: LxSourceInfo, val script: String,
    val performance: LxSourcePerformance = LxSourcePerformance()) {
    override fun toString() = "LxStoredSource(${info.id}, script=[private])"
}
class LxSourceException(message: String, val qualityUnavailable: Boolean = false) : java.io.IOException(message)

internal fun lxScriptInfo(script: String, origin: String = ""): LxSourceInfo {
    require(script.toByteArray(Charsets.UTF_8).size in 1..LX_MAX_SCRIPT_BYTES) { "Tập lệnh nguồn nhạc trống hoặc vượt quá 1 MB" }
    val header = Regex("^\\s*/\\*[\\s\\S]*?\\*/").find(script.removePrefix("\uFEFF"))?.value
        ?: throw IllegalArgumentException("Không phải nguồn nhạc Luoxue hợp lệ: thiếu chú thích thông tin tập lệnh")
    val fields = Regex("(?m)^\\s*\\*?\\s*@(name|description|version|author|homepage)\\s+([^\\r\\n]+)")
        .findAll(header).associate { it.groupValues[1] to it.groupValues[2].trim() }
    val name = fields["name"].orEmpty()
    require(name.isNotBlank() && name.length <= 256) { "Tập lệnh nguồn nhạc thiếu tên hợp lệ" }
    require(fields.values.all { it.length <= 8192 }) { "Thông tin tập lệnh nguồn nhạc quá dài" }
    val id = MessageDigest.getInstance("SHA-256").digest(script.toByteArray()).joinToString("") { "%02x".format(it) }
    return LxSourceInfo(id, name, fields["description"].orEmpty(), fields["version"].orEmpty(),
        fields["author"].orEmpty(), fields["homepage"].orEmpty(), origin)
}

internal fun lxCapabilities(value: JsonObject): List<LxCapability> {
    val sources = value["sources"] as? JsonObject ?: throw LxSourceException("Nguồn nhạc chưa khai báo nền tảng được hỗ trợ")
    return lxPlatforms.keys.mapNotNull { key ->
        val source = sources[key] as? JsonObject ?: return@mapNotNull null
        val actions = (source["actions"] as? JsonArray).orEmpty().mapNotNull { (it as? JsonPrimitive)?.contentOrNull }
        if (source["type"]?.jsonPrimitive?.contentOrNull != "music" || "musicUrl" !in actions) return@mapNotNull null
        val declared = (source["qualitys"] as? JsonArray).orEmpty().mapNotNull { (it as? JsonPrimitive)?.contentOrNull }
        val qualities = lxQualities.filter { it in declared }
        qualities.takeIf { it.isNotEmpty() }?.let { LxCapability(key, it) }
    }.also { if (it.isEmpty()) throw LxSourceException("Nguồn nhạc này chưa khai báo API phát nhạc Nhạc Vân NetEase; hiện chỉ hỗ trợ Nhạc Vân NetEase") }
}

internal data class LxTrackRequest(val platform: String, val quality: String, val data: JsonObject)
internal fun lxTrackRequest(song: Song, quality: AudioQuality, sources: List<LxCapability>): LxTrackRequest {
    val wy = song.externalIds.neteaseId?.takeIf { it.toLongOrNull()?.let { id -> id > 0 } == true }
        ?: song.id.takeIf { song.musicSource == MusicSource.NETEASE && it > 0 }?.toString()
    val id = wy ?: throw LxSourceException("Nguồn nhạc Luoxue hiện chỉ hỗ trợ bài hát có ID Nhạc Vân NetEase hợp lệ")
    val platform = "wy"
    val supported = lxNeteaseCapabilities(sources).firstOrNull()?.qualities
        ?: throw LxSourceException("Nguồn nhạc này chưa khai báo API phát nhạc Nhạc Vân NetEase, vui lòng chuyển nguồn nhạc")
    val desired = when (quality) { AudioQuality.STANDARD, AudioQuality.HIGHER -> 0; AudioQuality.EXHIGH -> 1; AudioQuality.LOSSLESS -> 2; AudioQuality.AUTO -> lxQualities.lastIndex; else -> 3 }
    val selected = lxQualities.take(desired + 1).lastOrNull { it in supported }
        ?: lxQualities.first { it in supported }
    return LxTrackRequest(platform, selected, buildJsonObject {
        put("source", platform); put("action", "musicUrl")
        put("info", buildJsonObject {
            put("type", selected)
            put("musicInfo", buildJsonObject {
                put("source", platform); put("songmid", id); put("name", song.name)
                put("singer", song.artists); put("albumName", song.album); put("img", song.cover)
                put("interval", "%02d:%02d".format(java.util.Locale.ROOT, song.durationMs / 60000, song.durationMs / 1000 % 60))
                put("_types", buildJsonObject { supported.forEach { put(it, buildJsonObject { put("type", it) }) } })
                put("types", JsonArray(supported.map { buildJsonObject { put("type", it) } }))
                put("meta", buildJsonObject { put("songId", id); put("albumName", song.album) })
            })
        })
    })
}

/** Only an explicit per-track rejection permits AUTO to try the next declared quality. */
internal suspend fun lxResolveTrack(song: Song, quality: AudioQuality, sources: List<LxCapability>,
    resolve: suspend (JsonObject) -> String): Pair<LxTrackRequest, String> {
    val first = lxTrackRequest(song, quality, sources)
    val requests = if (quality == AudioQuality.AUTO) {
        val supported = lxNeteaseCapabilities(sources).single().qualities
        listOf(first) + supported.asReversed().drop(1).map { level ->
            val fallback = when (level) {
                "flac" -> AudioQuality.LOSSLESS
                "320k" -> AudioQuality.EXHIGH
                else -> AudioQuality.STANDARD
            }
            lxTrackRequest(song, fallback, sources)
        }
    } else listOf(first)
    for ((index, request) in requests.withIndex()) {
        kotlinx.coroutines.currentCoroutineContext().ensureActive()
        try { return request to resolve(request.data) }
        catch (error: LxSourceException) {
            if (!error.qualityUnavailable || index == requests.lastIndex) throw error
        }
    }
    error("No declared LX quality")
}
