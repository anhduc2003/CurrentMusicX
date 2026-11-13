package io.github.currencortex.music.feature.player

import androidx.compose.runtime.*
import io.github.currencortex.music.core.media.PlayerState
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.lyrics.data.PlaybackLyricsState
import io.github.currencortex.music.feature.lyrics.domain.LyricsSynchronizer
import io.github.currencortex.music.feature.lyrics.ui.rememberLyricsPosition

/** Use the same playback clock, identity check and offset convention as the full lyrics view. */
@Composable internal fun currentSettingsLyric(lyrics: PlaybackLyricsState, player: PlayerState,
    song: Song?, globalOffsetMs: Long): String {
    val clock = rememberLyricsPosition(player)
    val timeline = remember(lyrics.document) { LyricsSynchronizer(lyrics.document) }
    val offset = globalOffsetMs - lyrics.songOffsetMs
    val text by remember(lyrics, player.song, song, timeline, clock, offset) { derivedStateOf {
        when {
            player.song == null || song == null -> "Chưa có lời bài hát"
            !lyrics.matches(song) || !lyrics.matches(player.song) || lyrics.loading -> "Đang tải lời bài hát"
            lyrics.document.lines.isEmpty() -> lyrics.error ?: "Chưa có lời bài hát"
            else -> {
                val position = LyricsSynchronizer.lyricPosition(clock.value, offset)
                lyrics.document.lines.getOrNull(timeline.findCurrentLine(position))?.text?.takeIf(String::isNotBlank)
                    ?: timeline.interlude(position)?.label ?: "Âm nhạc vẫn đang tiếp tục"
            }
        }
    } }
    return text
}
