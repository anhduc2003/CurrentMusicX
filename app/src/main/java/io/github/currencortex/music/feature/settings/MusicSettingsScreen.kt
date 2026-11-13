package io.github.currencortex.music.feature.settings

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.network.CurrentMusicServer
import io.github.currencortex.music.data.settings.AudioProvider
import io.github.currencortex.music.data.settings.LyricsSource
import io.github.currencortex.music.ui.component.MusicDestinationRow
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.MusicTextAction
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.TextField

/** Sources and connections only; playback and other settings each have one owner. */
@Composable fun MusicSettingsScreen(vm: MusicSettingsViewModel, onNavidrome: () -> Unit = {}, onEmby: () -> Unit = {}) {
    SettingsUiScope {
    val settings by vm.settings.collectAsStateWithLifecycle()
    val state by vm.state.collectAsStateWithLifecycle()
    val provider by vm.audioProvider.collectAsStateWithLifecycle()
    val audioState by vm.audioState.collectAsStateWithLifecycle()
    val automatic by vm.automaticServer.collectAsStateWithLifecycle()
    // Never save a credential in a Bundle.
    var audioKey by remember { mutableStateOf("") }
    var serverDialogOpen by rememberSaveable { mutableStateOf(false) }
    var customServerOpen by rememberSaveable { mutableStateOf(false) }
    SettingsPage("Nguồn nhạc và mạng", "network_settings") {
        item("source") { SettingsSection("Nguồn nhạc", MusicIcons.Headphones) {
            SettingsOptionsRow("Nguồn nhạc", provider.provider.label, AudioProvider.entries.map { entry ->
                SettingsOption("audio_provider_${entry.name.lowercase()}", entry.label, provider.provider == entry,
                    onSelect = { audioKey = ""; vm.audioProvider(entry) })
            }, Modifier.testTag("audio_provider"), enabled = !audioState.busy)
            AnimatedVisibility(provider.provider == AudioProvider.LEIZ,
                enter = fadeIn() + expandVertically(), exit = fadeOut() + shrinkVertically()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SettingsHint(if (provider.keyConfigured) "API Key được mã hóa và lưu trên thiết bị" else "Nhập API Key để sử dụng nguồn LeiZ")
                    TextField(audioKey, { audioKey = it }, label = if (provider.keyConfigured) "Thay thế API Key" else "API Key",
                        singleLine = true, visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth().testTag("audio_key_input"))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        MusicTextAction("Lưu", { vm.audioKey(audioKey); audioKey = "" }, Modifier.testTag("audio_key_save"),
                            enabled = audioKey.isNotBlank() && !audioState.busy)
                        if (provider.keyConfigured) MusicTextAction("Xóa", { vm.clearAudioKey(); audioKey = "" },
                            Modifier.testTag("audio_key_clear"), enabled = !audioState.busy, destructive = true)
                    }
                }
            }
            if (provider.provider != AudioProvider.LX) audioState.message?.let { SettingsHint(it, error = !audioState.busy) }
        } }
        if (provider.provider == AudioProvider.LX) item("lx_sources") { LxSourceSettings(vm) }
        item("lyrics") { SettingsSection("Nguồn lời bài hát", MusicIcons.File) {
            SettingsOptionsRow("Nguồn mặc định", when (settings.lyricsSource) {
                LyricsSource.AUTO -> "Tự động chọn phù hợp nhất"
                LyricsSource.CURRENT_MUSIC -> "CurrentMusic"
                LyricsSource.NETEASE -> "Nhạc Vân NetEase"
                LyricsSource.LOCAL -> "Lời bài hát nhúng trong tệp cục bộ"
            }, LyricsSource.entries.map { source ->
                SettingsOption("lyrics_source_${source.name.lowercase()}", source.label, source == settings.lyricsSource,
                    summary = when (source) {
                        LyricsSource.AUTO -> "Khớp thông tin ≥75% · Ưu tiên tìm lời từng chữ thực tế trên QQ"
                        LyricsSource.CURRENT_MUSIC -> "CurrentMusic · Lời từng chữ thực tế từ LDDC"
                        LyricsSource.NETEASE -> "Điện thoại kết nối trực tiếp để lấy lời từng chữ hoặc từng dòng từ Nhạc Vân NetEase"
                        LyricsSource.LOCAL -> "Đọc lời bài hát từ tệp âm thanh cục bộ khi ngoại tuyến"
                    }, onSelect = { vm.lyricsSource(source) })
            }, Modifier.testTag("lyrics_source"))
        } }
        item("server") { SettingsSection("Dịch vụ CurrentMusic", MusicIcons.Storage) {
            MusicDestinationRow("Tuyến máy chủ", { serverDialogOpen = true }, Modifier.testTag("server_source"),
                summary = when (settings.serverMode) {
                    CurrentMusicServer.AUTO -> "Tự động · ${CurrentMusicServer.presets.firstOrNull { it.url == automatic }?.label.orEmpty()}"
                    CurrentMusicServer.CUSTOM -> "Tùy chỉnh · ${settings.customServer}"
                    else -> settings.serverMode.label
                }, enabled = !state.busy)
            SettingsDivider()
            SettingsPreference(if (state.busy) "Đang kiểm tra…" else "Kiểm tra kết nối", state.message,
                Modifier.testTag("server_test"), enabled = !state.busy, onClick = { vm.test() })
        } }
        item("library") { SettingsSection("Thư viện nhạc tự xây dựng", MusicIcons.List) {
            MusicDestinationRow("Navidrome", onNavidrome, Modifier.testTag("open_navidrome"),
                summary = "Kết nối máy chủ nhạc của bạn · Subsonic")
            SettingsDivider()
            MusicDestinationRow("Emby", onEmby, Modifier.testTag("open_emby"), summary = "Album, bài hát, nghệ sĩ và danh sách phát · Phát âm thanh nguyên bản")
        } }
    }
    if (serverDialogOpen) CurrentMusicServerDialog(vm, onDismiss = { serverDialogOpen = false }, onCustom = {
        serverDialogOpen = false; customServerOpen = true
    })
    if (customServerOpen) CustomMusicServerDialog(settings.customServer, state, onSave = { vm.server(it) },
        onDismiss = { customServerOpen = false })
    }
}
