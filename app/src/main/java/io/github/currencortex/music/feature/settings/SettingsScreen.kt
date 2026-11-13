package io.github.currencortex.music.feature.settings

import io.github.currencortex.music.ui.component.rootMotionItems
import io.github.currencortex.music.ui.component.musicScrollPadding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.PageContainerOrigin
import io.github.currencortex.music.ui.component.LocalPageContainerSource
import io.github.currencortex.music.ui.component.PageNavigationPreference
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.draw.drawWithContent
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.feature.update.UpdatePageOrigin
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun SettingsScreen(onAppearance: () -> Unit, onNetwork: () -> Unit,
                   onStorage: () -> Unit, onLogs: () -> Unit, onAbout: () -> Unit, onUpdates: (UpdatePageOrigin?) -> Unit,
                   onDesktopLyrics: () -> Unit = {},
                   onAudioOutput: () -> Unit = {},
                   onPlayback: () -> Unit = {}, onEffects: () -> Unit = {},
                   updateTransfer: io.github.currencortex.music.core.update.UpdateTransferState = io.github.currencortex.music.core.update.UpdateTransferState(),
                   onNavigateFrom: ((PageContainerOrigin, () -> Unit) -> Unit)? = null,
                   onBack: (() -> Unit)? = null, onSupport: () -> Unit = {}) {
    val context = androidx.compose.ui.platform.LocalContext.current
    CompositionLocalProvider(LocalPageContainerSource provides (onNavigateFrom ?: LocalPageContainerSource.current)) {
    SettingsUiScope {
    SettingsScrollLayout(Modifier.fillMaxSize(), topBar = {
        if (onBack == null) SettingsRootTopBar("Cài đặt", Modifier.testTag("settings_root_toolbar"))
        else SettingsTopBar("Cài đặt", onBack, tag = "settings_root_toolbar")
    }) { topInset ->
    LazyColumn(Modifier.fillMaxSize().testTag("settings_screen"),
        contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 24.dp, 16.dp, 20.dp))) {
        rootMotionItems {
        item {
            var origin by remember { mutableStateOf<LayoutCoordinates?>(null) }
            val sourceLayer = rememberGraphicsLayer()
            val color = MiuixTheme.colorScheme.surfaceContainer
            SettingsGroup(Modifier.fillMaxWidth().onGloballyPositioned { origin = it }.drawWithContent {
                sourceLayer.record { this@drawWithContent.drawContent() }; drawLayer(sourceLayer)
            }) {
                val progress = updateTransfer.download
                val updateTitle = when (progress) {
                    is io.github.currencortex.music.core.update.UpdateDownloadState.Downloading -> "Đang tải bản cập nhật · ${(progress.received.toDouble() / progress.total.coerceAtLeast(1) * 100).toInt()}%"
                    is io.github.currencortex.music.core.update.UpdateDownloadState.Ready -> "Đã tải bản cập nhật"
                    is io.github.currencortex.music.core.update.UpdateDownloadState.Failed -> "Tải bản cập nhật thất bại"
                    else -> "Kiểm tra cập nhật"
                }
                val summary = if (progress is io.github.currencortex.music.core.update.UpdateDownloadState.Idle)
                    "Xem phiên bản và ghi chú cập nhật" else "Xem tiến trình tải xuống, chuyển nguồn hoặc cài đặt"
                SettingsPreference(title = updateTitle, summary = summary,
                    leading = { SettingIcon(MusicIcons.Download) }, trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp)) }, onClick = {
                        onUpdates(origin?.takeIf { it.isAttached }?.let { UpdatePageOrigin(it, color, updateTitle, summary, sourceLayer = sourceLayer) })
                    }, modifier = Modifier.testTag("open_updates"))
            }
        }
        item {
            SettingsListSection("Cá nhân hóa") {
                SettingsPagePreference("Giao diện", "Chủ đề, màu sắc và hiệu ứng giao diện", MusicIcons.Image,"open_appearance",onAppearance)
                SettingsDivider(52.dp)
                SettingsPagePreference("Lời bài hát nổi trên màn hình nền", "Xem trước trực tiếp, kiểu phụ đề và điều khiển cửa sổ", MusicIcons.BottomBar,"open_desktop_lyrics",onDesktopLyrics)
            }
        }
        item {
            SettingsListSection("Âm nhạc") {
                SettingsPagePreference("Phát lại", "Chất lượng âm thanh, chuyển tiếp bài hát, tải trước và hành vi phát", MusicIcons.Play,"open_playback",onPlayback)
                SettingsDivider(52.dp)
                SettingsPagePreference("Nguồn nhạc và mạng", "Nguồn nhạc, nguồn lời bài hát và máy chủ", MusicIcons.Storage,"open_network",onNetwork)
                SettingsDivider(52.dp)
                SettingsPagePreference("Hiệu ứng âm thanh", "Điều chỉnh âm thanh thông minh, hiệu ứng âm thanh và bộ chỉnh âm", MusicIcons.Headphones,"open_equalizer",onEffects)
                SettingsDivider(52.dp)
                SettingsPagePreference("Đầu ra âm thanh", "Tần số lấy mẫu đầu ra, thiết bị và thông tin khả năng", MusicIcons.Device,"open_audio_output",onAudioOutput)
            }
        }
        item {
            SettingsListSection("Ứng dụng") {
                SettingsPreference("Mở liên kết mặc định", "Cho phép mở liên kết Nhạc Vân NetEase và trang web CurrentMusic trong hệ thống", Modifier.testTag("open_app_links"),
                    leading = { SettingIcon(MusicIcons.Info) },
                    trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp)) },
                    onClick = { io.github.currencortex.music.ui.util.openAppLinkSettings(context) })
                SettingsDivider(52.dp)
                SettingsPagePreference("Dung lượng lưu trữ", "Dung lượng bộ nhớ đệm và nội dung tải xuống", MusicIcons.Storage,"open_storage",onStorage)
                SettingsDivider(52.dp)
                SettingsPreference("Xuất nhật ký", "Lưu hoặc chia sẻ tệp chẩn đoán đã ẩn thông tin nhạy cảm", Modifier.testTag("open_logs"),
                    leading = { SettingIcon(MusicIcons.File) }, onClick = onLogs,
                    trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp)) })
                SettingsDivider(52.dp)
                SettingsPagePreference("Ủng hộ nhà phát triển", "Tự nguyện tài trợ cho việc phát triển và bảo trì dự án", MusicIcons.Heart,"open_support",onSupport)
                SettingsDivider(52.dp)
                SettingsPagePreference("Giới thiệu", "Phiên bản, giấy phép mã nguồn mở và quyền riêng tư", MusicIcons.Info,"open_about",onAbout)
            }
        }
    }
    }
    }
    }
    }
}

@Composable private fun SettingsPagePreference(title:String,summary:String,icon:ImageVector,tag:String,onClick:()->Unit) {
    PageNavigationPreference(title,summary,Modifier.testTag(tag),icon,onClick)
}
