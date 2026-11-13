package io.github.currencortex.music.data.song

import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.core.network.ApiJson
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.json.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.security.MessageDigest
import java.text.Normalizer
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class YouTubeAudioClient(client: OkHttpClient = OkHttpClient(),
    private val cookies: suspend () -> Map<String, String> = { emptyMap() }) {
    private val http = client.newBuilder()
        .connectTimeout(8, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).callTimeout(25, TimeUnit.SECONDS)
        .followRedirects(false).followSslRedirects(false).build()

    suspend fun source(song: Song, quality: AudioQuality, music: Boolean): AudioSource {
        val query = listOf(song.name, song.artists).filter(String::isNotBlank).joinToString(" ").trim().take(200)
        if (query.isBlank()) throw IOException("Thông tin bài hát không đủ để tìm trên YouTube")
        var match = search(query, music).bestMatch(song)
        val nameQuery = song.name.trim().take(200)
        if (match == null && nameQuery.isNotBlank() && nameQuery != query) match = search(nameQuery, music).bestMatch(song)
        if (match == null) match = search(query, !music).bestMatch(song)
        val chosen = match ?: throw IOException("Không tìm thấy bài hát phù hợp trên YouTube")
        val selected = chooseFormat(audioFormats(chosen.videoId), quality)
            ?: throw IOException("YouTube không cung cấp luồng âm thanh cho bài hát này")
        return AudioSource(selected.url, if (selected.bitrate >= 128_000L) "higher" else "standard",
            selected.sampleRate, selected.channels, format = if (selected.mime.contains("webm")) "webm" else "m4a",
            validForSeconds = 6L * 60 * 60,
            source = if (music) "youtube-music" else "youtube", requestedLevel = quality.value,
            bitrate = selected.bitrate, bits = 16, size = selected.size, durationMs = selected.durationMs)
    }

    private suspend fun search(query: String, music: Boolean): List<Candidate> {
        val client = if (music) MUSIC_SEARCH else YOUTUBE_SEARCH
        val body = buildJsonObject {
            put("context", client.context())
            put("query", query)
        }
        val root = post(if (music) MUSIC_SEARCH_URL else YOUTUBE_SEARCH_URL, body, client)
        val candidates = mutableListOf<Candidate>()
        fun visit(node: JsonElement) {
            when (node) {
                is JsonObject -> {
                    (node["musicResponsiveListItemRenderer"] as? JsonObject)?.let { parseMusicItem(it, candidates) }
                    (node["videoRenderer"] as? JsonObject)?.let { parseVideoRenderer(it, candidates) }
                    node.values.forEach { visit(it) }
                }
                is JsonArray -> node.forEach { visit(it) }
                else -> Unit
            }
        }
        visit(root)
        return candidates.distinctBy(Candidate::videoId)
    }

    private fun parseMusicItem(item: JsonObject, out: MutableList<Candidate>) {
        val videoId = item.videoId() ?: return
        val columns = (item["flexColumns"] as? JsonArray).orEmpty()
        val title = columns.getOrNull(0)?.columnText().orEmpty()
        if (title.isBlank()) return
        val meta = columns.getOrNull(1)?.columnRuns().orEmpty()
        val kind = meta.firstOrNull().orEmpty()
        val artists = meta.filterNot { it == kind || it in SEPARATORS }.joinToString(" ").trim()
        out += Candidate(videoId, title, artists, (columns + (item["fixedColumns"] as? JsonArray).orEmpty()).timestamp(), kind)
    }

    private fun parseVideoRenderer(item: JsonObject, out: MutableList<Candidate>) {
        val videoId = (item["videoId"] as? JsonPrimitive)?.contentOrNull?.takeIf { it.length == 11 } ?: return
        val title = item["title"]?.textOf().orEmpty()
        if (title.isBlank()) return
        val artists = (item["ownerText"] ?: item["longBylineText"])?.textOf().orEmpty()
        val duration = timestampOf((item["lengthText"] as? JsonObject)?.get("simpleText")?.let { (it as? JsonPrimitive)?.contentOrNull })
        out += Candidate(videoId, title, artists, duration, "")
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

    private fun JsonElement.columnRuns(): List<String> {
        val renderer = (this as? JsonObject)?.get("musicResponsiveListItemFlexColumnRenderer") as? JsonObject ?: return emptyList()
        return renderer.runs()
    }

    private fun JsonElement.columnText(): String =
        columnRuns().filterNot { it.isBlank() || it in SEPARATORS }.joinToString(" ").trim()

    private fun JsonObject.runs(): List<String> =
        ((this["text"] as? JsonObject)?.get("runs") as? JsonArray).orEmpty().mapNotNull { (it as? JsonPrimitive)?.contentOrNull }

    private fun List<JsonElement>?.timestamp(): Long = this?.flatMap { element ->
        (element as? JsonObject)?.runs().orEmpty()
    }?.firstNotNullOfOrNull { timestampOf(it) } ?: 0L

    private fun timestampOf(value: String?): Long {
        val text = value?.trim().orEmpty()
        if (!text.matches(Regex("\\d{1,2}:\\d{2}(:\\d{2})?"))) return 0L
        val parts = text.split(':').map { it.toLongOrNull() ?: 0L }
        return when (parts.size) {
            2 -> (parts[0] * 60 + parts[1]) * 1000
            3 -> (parts[0] * 3600 + parts[1] * 60 + parts[2]) * 1000
            else -> 0L
        }
    }

    private fun JsonElement.textOf(): String = when (this) {
        is JsonPrimitive -> contentOrNull.orEmpty()
        is JsonObject -> (get("simpleText") as? JsonPrimitive)?.contentOrNull
            ?: ((get("runs") as? JsonArray)?.firstOrNull() as? JsonObject)?.get("text")?.textOf()
            ?: (get("text") as? JsonElement)?.textOf().orEmpty()
        is JsonArray -> firstOrNull()?.textOf().orEmpty()
        else -> ""
    }

    private fun List<Candidate>.bestMatch(song: Song): Candidate? {
        val target = tokenize(song.name)
        val artistTokens = tokenize(song.artists)
        if (target.isEmpty()) return null
        var best: Candidate? = null
        var bestScore = Double.NEGATIVE_INFINITY
        for (candidate in this) {
            val titleTokens = tokenize(candidate.title.replace(Regex("\\s*[([][^)\\]]*[)\\]]"), " "))
            if (titleTokens.isEmpty()) continue
            val shared = titleTokens.count { it in target }
            val jaccard = shared.toDouble() / (target.size + titleTokens.size - shared)
            val containment = shared.toDouble() / minOf(target.size, titleTokens.size)
            val titleScore = maxOf(jaccard, containment * 0.9) - (titleTokens.size - shared) * 0.06 +
                (if (normalize(candidate.title) == normalize(song.name)) 0.12 else 0.0)
            if (titleScore < 0.5) continue
            val candidateArtistTokens = tokenize(candidate.artists) + titleTokens
            val artistScore = if (artistTokens.isEmpty()) 0.5
                else artistTokens.count { it in candidateArtistTokens }.toDouble() / artistTokens.size
            val durationScore = when {
                song.durationMs <= 0 || candidate.durationMs <= 0 -> 0.5
                else -> {
                    val diff = kotlin.math.abs(song.durationMs - candidate.durationMs)
                    when { diff <= 5_000 -> 1.0; diff <= 20_000 -> 0.7; diff <= 60_000 -> 0.3; else -> 0.0 }
                }
            }
            var penalty = if (LOW_QUALITY_REGEX.containsMatchIn(candidate.title.lowercase(Locale.ROOT))) 0.4 else 0.0
            if (candidate.title.contains(" x ", ignoreCase = true)) penalty += 0.08
            if (candidate.kind == "Song") penalty -= 0.05
            if (candidate.kind in WEAK_KINDS) penalty += 0.08
            val score = titleScore * 0.65 + artistScore * 0.2 + durationScore * 0.15 - penalty
            if (score > bestScore) { bestScore = score; best = candidate }
        }
        return best?.takeIf { bestScore >= 0.55 }
    }

    private suspend fun audioFormats(videoId: String): List<AudioFormat> {
        var last: IOException? = null
        for (client in PLAYERS) {
            val body = buildJsonObject {
                put("context", client.context())
                put("videoId", videoId)
                put("contentCheckOk", true)
                put("racyCheckOk", true)
            }
            val root = try { post(PLAYER_URL, body, client) }
            catch (cancellation: kotlinx.coroutines.CancellationException) { throw cancellation }
            catch (failure: IOException) { last = failure; continue }
            val status = root["playabilityStatus"] as? JsonObject
            if ((status?.get("status") as? JsonPrimitive)?.contentOrNull != "OK") {
                val reason = (status?.get("reason") as? JsonPrimitive)?.contentOrNull?.takeIf(String::isNotBlank)
                last = IOException(reason ?: "YouTube không cho phép phát video này")
                continue
            }
            val formats = ((root["streamingData"] as? JsonObject)?.get("adaptiveFormats") as? JsonArray)
                .orEmpty().mapNotNull { (it as? JsonObject)?.audioFormat() }
            if (formats.isNotEmpty()) return formats
            last = IOException("YouTube không cung cấp luồng âm thanh cho video này")
        }
        throw last ?: IOException("Không thể phân tích luồng YouTube")
    }

    private fun JsonObject.audioFormat(): AudioFormat? {
        val mime = (this["mimeType"] as? JsonPrimitive)?.contentOrNull?.takeIf { it.startsWith("audio/") } ?: return null
        val url = (this["url"] as? JsonPrimitive)?.contentOrNull?.takeIf { it.startsWith("https://") } ?: return null
        val bitrate = (this["bitrate"] as? JsonPrimitive)?.longOrNull ?: 0L
        if (bitrate <= 0L) return null
        return AudioFormat(url, mime, bitrate,
            (this["audioSampleRate"] as? JsonPrimitive)?.intOrNull ?: 0,
            (this["audioChannels"] as? JsonPrimitive)?.intOrNull ?: 0,
            (this["contentLength"] as? JsonPrimitive)?.longOrNull ?: 0L,
            (this["approxDurationMs"] as? JsonPrimitive)?.longOrNull ?: 0L)
    }

    private fun chooseFormat(formats: List<AudioFormat>, quality: AudioQuality): AudioFormat? {
        val usable = formats.filter { it.bitrate > 0L }.ifEmpty { formats }
        val mp4 = usable.filter { it.mime.contains("mp4") }.ifEmpty { usable }
        val ordered = if (quality == AudioQuality.STANDARD) mp4.sortedBy(AudioFormat::bitrate)
            else mp4.sortedByDescending(AudioFormat::bitrate)
        return ordered.firstOrNull()
    }

    private suspend fun post(url: String, body: JsonObject, client: InnertubeClient): JsonObject {
        val jar = cookies()
        val origin = if (url.startsWith(MUSIC_SEARCH_URL)) "https://music.youtube.com" else "https://www.youtube.com"
        return suspendCancellableCoroutine { continuation ->
            val builder = Request.Builder().url(url)
                .header("User-Agent", client.userAgent)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Origin", origin)
            if (jar.isNotEmpty()) {
                builder.header("Cookie", jar.entries.joinToString("; ") { "${it.key}=${it.value}" })
                jar["VISITOR_INFO1_LIVE"]?.takeIf { it.isNotBlank() }
                    ?.let { builder.header("X-Goog-Visitor-Id", it) }
                val sapisid = jar["SAPISID"] ?: jar["__Secure-1PAPISID"] ?: jar["__Secure-3PAPISID"]
                if (!sapisid.isNullOrBlank()) {
                    val timestamp = System.currentTimeMillis() / 1000
                    val hash = MessageDigest.getInstance("SHA-1")
                        .digest("$timestamp $sapisid $origin".toByteArray()).joinToString("") { "%02x".format(it) }
                    builder.header("Authorization", "SAPISIDHASH $timestamp $hash")
                }
            }
            val call = http.newCall(builder.post(body.toString().toRequestBody(JSON)).build())
            continuation.invokeOnCancellation { call.cancel() }
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, error: IOException) {
                    if (!continuation.isActive) return
                    continuation.resumeWithException(if (error is java.io.InterruptedIOException)
                        IOException("Yêu cầu YouTube đã hết thời gian chờ, vui lòng kiểm tra mạng rồi thử lại", error)
                    else IOException("Yêu cầu YouTube thất bại, vui lòng kiểm tra mạng rồi thử lại", error))
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        val payload = response.use {
                            if (!it.isSuccessful) throw IOException("YouTube trả về HTTP ${it.code}")
                            it.body ?: throw IOException("YouTube trả về phản hồi trống")
                        }
                        val source = payload.source()
                        source.request(MAX_BYTES + 1)
                        if (source.buffer.size > MAX_BYTES) throw IOException("Phản hồi YouTube quá lớn")
                        val json = ApiJson.parseToJsonElement(source.readUtf8()).jsonObject
                        if (continuation.isActive) continuation.resume(json)
                    } catch (failure: Exception) {
                        if (continuation.isActive) continuation.resumeWithException(if (failure is IOException) failure
                        else IOException("Định dạng phản hồi YouTube không hợp lệ", failure))
                    }
                }
            })
        }
    }

    private fun tokenize(value: String): Set<String> = normalize(value).split(' ').filter { it.length > 1 }.toSet()

    private fun normalize(value: String): String = Normalizer.normalize(value.lowercase(Locale.ROOT), Normalizer.Form.NFD)
        .replace(Regex("\\p{Mn}+"), "").replace('đ', 'd').replace(Regex("[^a-z0-9\\s]"), " ")
        .replace(Regex("\\s+"), " ").trim()

    private data class Candidate(val videoId: String, val title: String, val artists: String, val durationMs: Long, val kind: String)
    private data class AudioFormat(val url: String, val mime: String, val bitrate: Long, val sampleRate: Int,
        val channels: Int, val size: Long, val durationMs: Long)
    private data class InnertubeClient(val name: String, val version: String, val userAgent: String,
        val deviceModel: String = "", val osName: String = "", val osVersion: String = "", val androidSdkVersion: Int = 0) {
        fun context(): JsonObject = buildJsonObject {
            putJsonObject("client") {
                put("clientName", name)
                put("clientVersion", version)
                put("hl", "en")
                put("gl", "US")
                if (deviceModel.isNotEmpty()) put("deviceModel", deviceModel)
                if (osName.isNotEmpty()) put("osName", osName)
                if (osVersion.isNotEmpty()) put("osVersion", osVersion)
                if (androidSdkVersion > 0) put("androidSdkVersion", androidSdkVersion)
            }
        }
    }

    private companion object {
        const val MAX_BYTES = 12L * 1024 * 1024
        val JSON = "application/json; charset=utf-8".toMediaType()
        const val PLAYER_URL = "https://www.youtube.com/youtubei/v1/player"
        const val MUSIC_SEARCH_URL = "https://music.youtube.com/youtubei/v1/search"
        const val YOUTUBE_SEARCH_URL = "https://www.youtube.com/youtubei/v1/search"
        val SEPARATORS = setOf("•", "&", "|", "·")
        val LOW_QUALITY_REGEX = Regex("karaoke|instrumental|\\bcover\\b|nightcore|sped up|slowed|reverb|8d audio|bass boost|reaction|\\blive\\b|lyrics|remix|episode|podcast")
        val WEAK_KINDS = setOf("Video", "Episode", "Artist", "Profile", "Podcast", "Album", "Playlist")
        val MUSIC_SEARCH = InnertubeClient("WEB_REMIX", "1.20240403.01.00",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
        val YOUTUBE_SEARCH = InnertubeClient("WEB", "2.20240726.00.00",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
        val PLAYERS = listOf(
            InnertubeClient("ANDROID_VR", "1.60.19",
                "com.google.android.apps.youtube.vr.oculus/1.60.19 (Linux; U; Android 12; eureka-user Build/SQ3A.220605.009.A1) gzip",
                "Oculus Quest 3", "Android", "12", 32),
            InnertubeClient("IOS", "19.29.1",
                "com.google.ios.youtube/19.29.1 (iPhone16,2; U; CPU iOS 17_5_1 like Mac OS X)", "iPhone16,2"),
            InnertubeClient("ANDROID", "19.29.37",
                "com.google.android.youtube/19.29.37 (Linux; U; Android 11) gzip", "", "Android", "11", 30),
            InnertubeClient("WEB", "2.20240726.00.00",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"),
            InnertubeClient("MWEB", "2.20240726.00.00",
                "Mozilla/5.0 (iPhone; CPU iPhone OS 17_5 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.5 Mobile/15E148 Safari/604.1"))
    }
}