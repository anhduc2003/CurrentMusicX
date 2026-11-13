package io.github.currencortex.music.data.netease.listen

import io.github.currencortex.music.core.netease.NeteaseSession

/** Session boundary also supports silent, deterministic playback synchronization fixtures. */
internal interface ListenTogetherProtocol {
    suspend fun captureSession(): NeteaseSession
    fun checkSession(expected: NeteaseSession)
    suspend fun create(expected: NeteaseSession, refer: String = "", inviteUid: Long? = null): ListenTogetherRoom
    suspend fun accept(expected: NeteaseSession, invitation: ListenTogetherInvitation, refer: String = ""): ListenTogetherRoom
    suspend fun status(expected: NeteaseSession): ListenTogetherStatus
    suspend fun requestSync(expected: NeteaseSession, roomId: String)
    suspend fun restoreDevice(expected: NeteaseSession, roomId: String) {}
    suspend fun resolveInvitation(text: String): ListenTogetherInvitation =
        ListenTogetherInvitation.parse(text) ?: error("Vui lòng dán liên kết lời mời Nghe cùng hợp lệ của Nhạc Vân NetEase")
    suspend fun heartbeat(expected: NeteaseSession, roomId: String, songId: Long, playing: Boolean, positionMs: Long, version: List<ListenTogetherVersion>): Boolean
    suspend fun playlist(expected: NeteaseSession, roomId: String, local: ListenTogetherQueue = ListenTogetherQueue()): ListenTogetherSnapshot
    suspend fun reportPlayback(expected: NeteaseSession, roomId: String, playback: ListenTogetherPlayback)
    suspend fun reportQueue(expected: NeteaseSession, roomId: String, queue: ListenTogetherQueue)
    suspend fun setRecommendation(expected: NeteaseSession, roomId: String, enabled: Boolean): ListenTogetherRecommendation =
        error("Dịch vụ hiện tại chưa hỗ trợ đề xuất Nghe cùng theo cảm xúc")
    suspend fun leave(expected: NeteaseSession, roomId: String)
}
