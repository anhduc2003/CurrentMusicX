package io.github.currencortex.music.data.netease.playlistimport

import io.github.currencortex.music.core.netease.*
import io.github.currencortex.music.core.network.*
import io.github.currencortex.music.data.song.Song
import kotlinx.serialization.json.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PlaylistImportTask internal constructor(val id: Long, internal val owner: NeteaseSession)
data class PlaylistImportStatus(val finished: Boolean, val successful: Boolean,
    val playlistIds: List<Long> = emptyList(), val message: String = "")

/** Official import protocol reconstructed from the NetEase app and its linked public H5. */
class NeteasePlaylistImportRepository(private val transport: NeteaseTransport,
    uploadBaseUrl: String = "https://nosup-hz1.127.net/") {
    val accounts get() = transport.sessions.state
    private val imageUpload = PlaylistImportImageUpload(uploadBaseUrl)

    private suspend fun owner(): NeteaseSession = transport.sessions.snapshot().also {
        if (it.credential.profile == null || it.credential.stale || it.credential.cookies["MUSIC_U"].isNullOrBlank())
            throw ApiException(ErrorKind.NeteaseBindingRequired)
    }

    internal suspend fun request(path: String, owner: NeteaseSession, data: JsonObject = buildJsonObject {}): JsonObject {
        transport.sessions.check(owner)
        val raw = transport.request(path, data, session = owner, clientProfile = NeteaseClientProfile.ANDROID).body
        val code = (raw["code"] as? JsonPrimitive)?.intOrNull ?: throw ApiException(ErrorKind.Parse)
        if (code == 200) return raw
        if (code in setOf(301, 401)) transport.sessions.markStale(owner)
        throw ApiException(when (code) {
            301, 401 -> ErrorKind.NeteaseBindingRequired
            403 -> ErrorKind.Forbidden
            406, 429 -> ErrorKind.RateLimited
            else -> ErrorKind.Server
        }, code, listOf("message", "msg").firstNotNullOfOrNull { key ->
            (raw[key] as? JsonPrimitive)?.contentOrNull?.takeIf(String::isNotBlank)?.take(180)
        })
    }

    suspend fun submitLinks(links: List<String>, importStar: Boolean): PlaylistImportTask {
        val clean = links.map(::extractImportLink).distinct()
        require(clean.size in 1..10) { "Vui lòng nhập 1–10 liên kết danh sách phát" }
        val owner = owner()
        val normalized = mutableListOf<String>()
        for (link in clean) {
            val checked = request("/api/playlist/import/url/check", owner, buildJsonObject { put("link", encodeImportUri(link)) })
            require(checked["data"] != null && checked["data"] != JsonNull && checked["data"] != JsonPrimitive(false)) {
                "Nhạc Vân NetEase hiện chưa hỗ trợ liên kết này, vui lòng kiểm tra rồi thử lại"
            }
            val canonical = ((checked["data"] as? JsonObject)?.get("link") as? JsonPrimitive)?.contentOrNull
            normalized += canonical?.takeIf(String::isNotBlank)?.let(::extractImportLink) ?: link
        }
        return submitUrls(owner, normalized.distinct(), importStar)
    }

    suspend fun submitText(text: String, importStar: Boolean): PlaylistImportTask {
        require(text.isNotBlank() && text.length <= 20_000) { "Vui lòng nhập nội dung bài hát, tối đa 20000 ký tự" }
        return submitUrls(owner(), listOf("rpc://playlist/import?text=${text.trim()}"), importStar)
    }

    suspend fun submitLocal(songs: List<Song>, importStar: Boolean): PlaylistImportTask {
        require(songs.size in 1..10_000 && songs.all { it.name.isNotBlank() && !it.video }) { "Vui lòng chọn 1–10000 bài hát hợp lệ" }
        val multiSongs = buildJsonArray { songs.forEach { song -> add(buildJsonObject {
            put("songName", song.name); put("artistName", song.artists); put("albumName", song.album)
        }) } }
        return create(owner(), buildJsonObject { put("multiSongs", multiSongs.toString()); put("importStarPlaylist", importStar) })
    }

    suspend fun targets(): List<PlaylistImportTarget> = targets(owner())

    private suspend fun targets(owner: NeteaseSession): List<PlaylistImportTarget> {
        val uid = requireNotNull(owner.credential.profile).uid
        val raw = request("/api/user/playlist", owner, buildJsonObject { put("uid", uid); put("limit", 1000); put("offset", 0) })
        return (raw["playlist"] as? JsonArray).orEmpty().filterIsInstance<JsonObject>().mapNotNull { row ->
            val creator = (row["creator"] as? JsonObject)?.get("userId") ?: row["userId"]
            val id = (row["id"] as? JsonPrimitive)?.longOrNull?.takeIf { it > 0 }
            if ((creator as? JsonPrimitive)?.longOrNull != uid || id == null) null
            else PlaylistImportTarget(id, (row["name"] as? JsonPrimitive)?.contentOrNull.orEmpty().ifBlank { "Danh sách phát $id" })
        }.distinctBy { it.id }
    }

    suspend fun submitImages(images: List<PlaylistImportImage>, playlistId: Long): PlaylistImportTask {
        require(images.size in 1..9 && images.all { it.bytes.size in 1..(8 * 1024 * 1024) && it.mimeType.startsWith("image/") }) {
            "Vui lòng chọn 1–9 ảnh, mỗi ảnh tối đa 8 MB"
        }
        val owner = owner()
        require(targets(owner).any { it.id == playlistId }) { "Vui lòng chọn danh sách phát đích do tài khoản Nhạc Vân NetEase hiện tại tạo" }
        val ids = mutableListOf<String>()
        for (image in images) {
            val raw = request("/api/nos/token/whalealloc", owner, buildJsonObject {
                put("filename", image.fileName.take(200)); put("fileSize", image.bytes.size)
                put("type", "image"); put("bucket", "yyimgs"); put("bizKey", "64e97e4d"); put("encryptFlag", 1)
            })
            val result = (raw["data"] ?: raw["result"]) as? JsonObject ?: throw ApiException(ErrorKind.Parse)
            val key = (result["objectKey"] as? JsonPrimitive)?.contentOrNull?.takeIf { it.isNotBlank() && it.length <= 1024 }
                ?: throw ApiException(ErrorKind.Parse)
            val token = (result["token"] as? JsonPrimitive)?.contentOrNull?.takeIf(String::isNotBlank)
                ?: throw ApiException(ErrorKind.Parse)
            imageUpload.upload(image, key, token) { transport.sessions.check(owner) }
            ids += "yyimgs/$key"
        }
        return task(request("/api/playlist/import/create", owner, buildJsonObject {
            put("playlistId", playlistId); put("picIds", JsonArray(ids.map(::JsonPrimitive)).toString()); put("rqRefer", "")
        }), owner)
    }

    private suspend fun submitUrls(owner: NeteaseSession, urls: List<String>, importStar: Boolean): PlaylistImportTask {
        val name = "Nhập nhạc " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        return create(owner, buildJsonObject {
            put("playlistName", name); put("taskIdForLog", ""); put("rqRefer", "")
            put("songs", buildJsonArray { urls.forEach { url -> add(buildJsonObject {
                put("name", name); put("type", ""); put("url", encodeImportUri(url))
            }) } }.toString())
            put("importStarPlaylist", importStar)
        })
    }

    private suspend fun create(owner: NeteaseSession, data: JsonObject): PlaylistImportTask {
        val raw = request("/api/playlist/import/name/task/create", owner, data)
        return task(raw, owner)
    }

    private fun task(raw: JsonObject, owner: NeteaseSession): PlaylistImportTask {
        val result = raw["data"] as? JsonObject ?: raw
        val id = (result["taskId"] as? JsonPrimitive)?.longOrNull?.takeIf { it > 0 }
            ?: throw ApiException(ErrorKind.Parse, userMessage = "Nhạc Vân NetEase không trả về mã tác vụ nhập, vui lòng kiểm tra danh sách phát rồi thử lại")
        return PlaylistImportTask(id, owner)
    }

    suspend fun status(task: PlaylistImportTask): PlaylistImportStatus {
        val raw = request("/api/playlist/import/task/status/v2", task.owner, buildJsonObject {
            put("taskIds", buildJsonArray { add(task.id) }.toString())
        })
        val data = raw["data"] as? JsonObject ?: throw ApiException(ErrorKind.Parse)
        val row = (data["tasks"] as? JsonArray)?.filterIsInstance<JsonObject>()
            ?.firstOrNull { (it["id"] as? JsonPrimitive)?.longOrNull == task.id }
            ?: return PlaylistImportStatus(false, false, message = "Đã gửi tác vụ, đang chờ Nhạc Vân NetEase xử lý")
        val state = when ((row["status"] as? JsonPrimitive)?.contentOrNull) {
            "IMPORTING" -> 1; "COMPLETE" -> 2; "FAILED" -> 3; "PLAYLIST_DELETE" -> 4
            "PLAYLIST_FULL_SOME_SUCCESS" -> 5; "PLAYLIST_FULL_NONE_SUCCESS" -> 6; "RECOGNIZE_NO_SONGS" -> 7
            else -> 0
        }
        val finished = state in 2..7
        val successful = state == 2 || state == 5
        val playlist = (row["playlistId"] as? JsonPrimitive)?.longOrNull?.takeIf { it > 0 }
        val message = (row["msg"] as? JsonPrimitive)?.contentOrNull?.takeIf(String::isNotBlank)?.take(300)
            ?: when (state) {
                1 -> "Nhạc Vân NetEase đang tìm và nhập bài hát"
                2 -> "Nhập hoàn tất"
                3 -> "Nhập thất bại, vui lòng kiểm tra nội dung danh sách phát"
                4 -> "Danh sách phát đích đã bị xóa"
                5 -> "Danh sách phát đã đầy, chỉ nhập thành công một phần bài hát"
                6 -> "Danh sách phát đã đầy, không nhập thành công bài hát nào"
                7 -> "Không nhận diện được bài hát nào có thể nhập"
                else -> "Nhạc Vân NetEase vẫn đang xử lý tác vụ"
            }
        return PlaylistImportStatus(finished, successful, listOfNotNull(playlist), message)
    }
}

internal fun extractImportLink(input: String): String {
    val link = Regex("https?://[^\\s<>\"']+", RegexOption.IGNORE_CASE).find(input)?.value
        ?.trimEnd('，', '。', '；', '、', '！', '？', '」', '』', '】', '）')
        ?: throw IllegalArgumentException("Vui lòng dán nội dung chia sẻ có liên kết http hoặc https")
    require(link.length <= 4096 && runCatching { java.net.URI(link).host?.isNotBlank() == true }.getOrDefault(false)) { "Liên kết danh sách phát không hợp lệ" }
    return link
}

/** JavaScript encodeURI, including percent signs and UTF-8 text in the text-import RPC URL. */
internal fun encodeImportUri(value: String): String = buildString {
    val safe = ";,/?:@&=+$-_.!~*'()#"
    value.toByteArray(Charsets.UTF_8).forEach { byte ->
        val c = byte.toInt() and 255
        if (c in 65..90 || c in 97..122 || c in 48..57 || c.toChar() in safe) append(c.toChar())
        else append('%').append("0123456789ABCDEF"[c shr 4]).append("0123456789ABCDEF"[c and 15])
    }
}
