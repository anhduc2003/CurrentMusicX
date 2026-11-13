package io.github.currencortex.music.feature.player

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.media.PlaybackMode
import io.github.currencortex.music.core.media.PlayerMode
import io.github.currencortex.music.data.song.NeteaseSongActionsRepository

@Composable internal fun PlayerSongActionsBar(vm: PlayerViewModel, onComments: () -> Unit, onQueue: () -> Unit,
    onSleep: () -> Unit, onLike: (() -> Unit)?, onEffects: (() -> Unit)? = null, onOptions: (() -> Unit)? = null) {
    val queue by vm.queue.collectAsStateWithLifecycle()
    val actions by vm.actions.collectAsStateWithLifecycle()
    val likePreview by vm.likePreview.collectAsStateWithLifecycle()
    val statuses by vm.libraryStatuses.collectAsStateWithLifecycle()
    val neteaseMain by vm.usesNeteaseLibrary.collectAsStateWithLifecycle()
    val sleep by vm.sleepTimer.state.collectAsStateWithLifecycle()
    val id = NeteaseSongActionsRepository.songId(queue.current)
    val navidrome = queue.current?.privateLibrary == true
    val current = actions.takeIf { it.songId == if (navidrome) queue.current?.id else id }
    PlayerFunctionBar(actionCount = 5, transportAligned = onOptions != null) {
        // Match transport: fixed 48dp end buttons, three equally weighted middle slots.
        val middleSlot = if (onOptions != null) Modifier.weight(1f) else Modifier
        val liked = likePreview?.takeIf { it.song.id == queue.current?.id && it.song.musicSource == queue.current?.musicSource &&
            it.song.privateIdentity == queue.current?.privateIdentity }?.liked
            ?: if (neteaseMain || navidrome) current?.liked == true else statuses[queue.current?.id]?.liked == true
        PlayerIconButton(PlayerIcon.LIKE, if (liked) "Bỏ thích" else "Thích", vm::togglePrimaryLike,
            Modifier.testTag("player_like"), selected = liked, count = current?.likeCount,
            showCount = true, enabled = queue.current?.video == false && queue.current?.musicSource != io.github.currencortex.music.data.song.MusicSource.LOCAL &&
                (queue.current?.musicSource != io.github.currencortex.music.data.song.MusicSource.EMBY || current?.liked != null), onLongClick = onLike)
        Box(middleSlot, contentAlignment = Alignment.Center) {
            PlayerIconButton(PlayerIcon.COMMENT, "Khu vực bình luận", onComments, Modifier.testTag("player_comments"),
                count = current?.commentCount, showCount = true, enabled = id != null)
        }
        // Lit while a timer is running, so an armed timer is visible without opening the sheet.
        Box(middleSlot, contentAlignment = Alignment.Center) {
            PlayerIconButton(PlayerIcon.ALARM, "Tắt theo giờ${if (sleep.enabled) "，đang đếm giờ" else ""}", onSleep,
                Modifier.testTag("player_sleep_timer"), selected = sleep.enabled)
        }
        if (onOptions != null) {
            Box(middleSlot, contentAlignment = Alignment.Center) {
                PlayerIconButton(PlayerIcon.EFFECTS, "Hiệu ứng âm thanh", onEffects ?: {}, Modifier.testTag("player_effects"), enabled = onEffects != null)
            }
            PlayerIconButton(PlayerIcon.MORE, "Cài đặt phát nhạc và lời bài hát", onOptions, Modifier.testTag("lyrics_options"))
        } else {
            PlayerPlaybackModeButton(vm)
            PlayerQueueButton(vm, onQueue)
        }
    }
}

@Composable internal fun PlayerPlaybackModeButton(vm: PlayerViewModel) {
    val queue by vm.queue.collectAsStateWithLifecycle()
    val player by vm.state.collectAsStateWithLifecycle()
    val together by vm.together.state.collectAsStateWithLifecycle()
    val shared by vm.together.queue.collectAsStateWithLifecycle()
    val mode = if (together.active) shared.mode else queue.mode
    PlayerIconButton(when (mode) {
        PlaybackMode.ONE -> PlayerIcon.REPEAT_ONE
        PlaybackMode.SHUFFLE -> PlayerIcon.SHUFFLE
        PlaybackMode.HEART -> PlayerIcon.HEART_MODE
        PlaybackMode.LIST -> PlayerIcon.REPEAT
    }, mode.label, vm::cyclePlaybackMode, Modifier.testTag("player_cycle_mode"),
        selected = mode == PlaybackMode.HEART, enabled = if (together.active) !together.busy else player.mode == PlayerMode.LOCAL,
        onLongClick = if (!together.active && player.mode == PlayerMode.LOCAL &&
            NeteaseSongActionsRepository.songId(queue.current) != null) {
            { vm.setPlaybackMode(PlaybackMode.HEART) }
        } else null, onLongClickLabel = "Bật chế độ rung động trái tim")
}

@Composable internal fun PlayerQueueButton(vm: PlayerViewModel, onQueue: () -> Unit) {
    val queue by vm.queue.collectAsStateWithLifecycle()
    val together by vm.together.state.collectAsStateWithLifecycle()
    val shared by vm.together.queue.collectAsStateWithLifecycle()
    PlayerIconButton(PlayerIcon.QUEUE, if (together.active) "Danh sách nghe cùng nhau, ${shared.songs.size} bài" else "Danh sách phát, ${queue.songs.size} bài", onQueue, Modifier.testTag("open_player_queue"))
}
