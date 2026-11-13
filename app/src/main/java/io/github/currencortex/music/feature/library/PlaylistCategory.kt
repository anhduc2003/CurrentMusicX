package io.github.currencortex.music.feature.library

import io.github.currencortex.music.data.library.Playlist

internal enum class PlaylistCategory(val label: String) {
    ALL("Tất cả"), CREATED("Tự tạo"), COLLECTED("Đã sưu tầm");

    fun includes(playlist: Playlist, accountId: Long): Boolean = when (this) {
        ALL -> true
        CREATED -> if (playlist.source == "netease") playlist.nativeOwned else playlist.editable(accountId)
        // Imported snapshots do not carry the original creator, so keep them in All.
        COLLECTED -> playlist.source == "netease" && !playlist.nativeOwned
    }
}
