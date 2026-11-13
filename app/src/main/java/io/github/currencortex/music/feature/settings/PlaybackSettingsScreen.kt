package io.github.currencortex.music.feature.settings

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.selectableGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.settings.LikeMode
import io.github.currencortex.music.data.settings.ListeningSource
import io.github.currencortex.music.data.settings.MusicTransitionMode
import io.github.currencortex.music.ui.component.MusicDestinationRow
import io.github.currencortex.music.ui.component.LyricsStyleSelector
import io.github.currencortex.music.ui.component.AppleMusicLyricsControls
import io.github.currencortex.music.ui.component.LyricsSizeSlider
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.data.settings.LyricsStyle
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.SettingsSwitch
import io.github.currencortex.music.ui.component.nativeui.*
import kotlin.math.roundToInt

@Composable fun PlaybackSettingsScreen(vm: MusicSettingsViewModel) {
    val settings by vm.settings.collectAsStateWithLifecycle()
    var qualityOpen by rememberSaveable { mutableStateOf(false) }
    var appleLyricsOpen by rememberSaveable { mutableStateOf(false) }
    var seconds by remember(settings.crossfadeSeconds) { mutableFloatStateOf(settings.crossfadeSeconds.toFloat()) }
val modes = mapOf(MusicTransitionMode.OFF to "Tắt", MusicTransitionMode.SMART to "Chuyển tiếp thông minh", MusicTransitionMode.CROSSFADE to "Chuyển tiếp chéo")    SettingsPage("Phát nhạc", "playback_settings") {
        item("quality") { SettingsSection("Chất lượng âm thanh", MusicIcons.Headphones) {
            MusicDestinationRow("Chất lượng âm thanh mặc định", { qualityOpen = true }, Modifier.testTag("default_quality"), summary = settings.quality.label)
        } }
        item("listening") { SettingsSection("Lịch sử nghe nhạc", MusicIcons.Headphones) {
            Column(Modifier.testTag("listening_source_setting").semantics { selectableGroup() }) {
                ListeningSource.entries.forEachIndexed { index, source ->
                    if(index > 0) SettingsDivider()
                    SettingsChoiceRow(source.label, settings.listeningSource == source,
                        { vm.listeningSource(source) }, Modifier.testTag("listening_source_${source.name.lowercase()}"),
                        summary = if(source == ListeningSource.LOCAL) "Thống kê lịch sử phát nhạc trên thiết bị này trong CurrentMusicX"
                            else "Xem lịch sử nghe nhạc trên đám mây của tài khoản NetEase Cloud đã liên kết")
                }
            }
        } }
        item("lyrics") { SettingsSection("Kiểu lời bài hát", MusicIcons.File) {
            LyricsStyleSelector(settings.lyricsDisplay.style, vm::lyricsStyle)
            if (settings.lyricsDisplay.style == LyricsStyle.APPLE_MUSIC) {
                SettingsDivider()
                MusicDestinationRow("Tùy chỉnh Apple Music", { appleLyricsOpen = true },
                    Modifier.testTag("open_apple_lyrics_settings"), summary = "Cỡ chữ, khoảng cách, tốc độ hoạt ảnh và hiệu ứng ánh sáng")
            }
        } }
        item("transition") { SettingsSection("Chuyển tiếp bài hát", MusicIcons.Update) {
            Column(Modifier.testTag("playback_transition").semantics { selectableGroup() }) {
                modes.entries.forEachIndexed { index, (mode, title) ->
                    if (index > 0) SettingsDivider()
                    SettingsChoiceRow(title, settings.transitionMode == mode, { vm.transition(mode) },
                        Modifier.testTag(when (mode) {
                            MusicTransitionMode.OFF -> "transition_off"
                            MusicTransitionMode.SMART -> "smart_transition"
                            MusicTransitionMode.CROSSFADE -> "crossfade"
                        }))
                }
            }
            AnimatedVisibility(settings.transitionMode == MusicTransitionMode.CROSSFADE,
                enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
                SettingsPreference("Thời lượng chuyển tiếp", "${seconds.roundToInt()} giây", footer = {
                    GlassSlider(seconds, { seconds = it.roundToInt().toFloat() }, valueRange = 1f..12f,
                        onValueChangeFinished = { vm.crossfadeSeconds(seconds.roundToInt()) },
                        modifier = Modifier.fillMaxWidth().testTag("crossfade_duration").semantics {
                            setProgress { value ->
                                if (!value.isFinite()) false else {
                                    seconds = value.roundToInt().coerceIn(1, 12).toFloat()
                                    vm.crossfadeSeconds(seconds.roundToInt()); true
                                }
                            }
                        })
                })
            }
        } }
        item("background") { SettingsSection("Hành vi phát nhạc", MusicIcons.Play) {
            SettingsSwitch("Cho phép phát đồng thời với ứng dụng khác", settings.allowConcurrentPlayback, vm::concurrentPlayback,
                summary = "Cho phép âm thanh từ nhạc và ứng dụng khác phát đồng thời", modifier = Modifier.testTag("allow_concurrent_playback"))
            SettingsDivider()
            SettingsSwitch("Khôi phục hàng đợi phát", settings.restoreQueue, vm::restore,
                summary = "Quay lại vị trí phát lần trước khi mở lại ứng dụng", modifier = Modifier.testTag("restore_queue"))
        } }
        item("preload") { SettingsSection("Tải trước", MusicIcons.Download) {
            SettingsSwitch("Tải trước bài tiếp theo", settings.preloadAudio, vm::preload, modifier = Modifier.testTag("preload_audio"))
            SettingsDivider()
            SettingsSwitch("Cho phép tải trước qua mạng tính phí", settings.preloadMetered, vm::preloadMetered,
                summary = "Bao gồm mạng di động, sẽ tiêu tốn thêm dữ liệu", enabled = settings.preloadAudio, modifier = Modifier.testTag("preload_metered"))
        } }
        item("likes") { SettingsSection("Yêu thích và thích", MusicIcons.Heart) {
            SettingsOptionsRow("Vị trí biểu tượng trái tim mặc định", settings.likeMode.label, LikeMode.entries.map { mode ->
                SettingsOption("like_mode_${mode.name}", mode.label, mode == settings.likeMode,
                    summary = when (mode) {
                        LikeMode.AUTO -> "Đồng bộ nếu đã liên kết NetEase Cloud, nếu không thì dùng CurrentMusic"
LikeMode.NETEASE -> "Dùng CurrentMusic khi chưa liên kết với Nhạc Vân NetEase"
LikeMode.CURRENT_MUSIC -> "Chỉ lưu trên ứng dụng này, không ảnh hưởng đến tài khoản Nhạc Vân NetEase"
                    },
                    onSelect = { vm.likeMode(mode) })
            }, Modifier.testTag("like_mode"))
        } }
    }
    if (qualityOpen) io.github.currencortex.music.feature.player.AudioQualitySheet(settings.quality, vm::quality,
         { qualityOpen = false }, title = "Chất lượng phát mặc định")
    if (appleLyricsOpen) MusicDialog("Lời bài hát Apple Music", { appleLyricsOpen = false }) {
        Column(Modifier.fillMaxWidth().heightIn(max = 420.dp).verticalScroll(rememberScrollState())
            .testTag("apple_lyrics_settings")) {
            LyricsSizeSlider(settings.lyricsFontSize, vm::lyricsFontSize)
            AppleMusicLyricsControls(settings.lyricsDisplay, vm::lyricsDisplay)
        }
    }
}
