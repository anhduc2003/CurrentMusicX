package io.github.currencortex.music.feature.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.BuildConfig
import io.github.currencortex.music.R
import io.github.currencortex.music.core.config.*
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.PageNavigationPreference
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.util.openExternalLink
import io.github.currencortex.music.ui.util.showMusicMessage
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun AboutScreen(onBack: () -> Unit, onOpenDocument: (LegalDocument) -> Unit, enableBlur: Boolean,
    onOpenMember: (AboutMember, String) -> Unit, onSupport: () -> Unit = {}) {
    val context = LocalContext.current
    val state = remember { AboutUiState() }
    AboutScreenMiuix(state, AboutScreenActions(onBack) { link ->
        when {
            link == "currentmusic:version" -> {
                val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                clipboard.setPrimaryClip(android.content.ClipData.newPlainText("version",
                    "${AppMetadata.APP_NAME} ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})\n${BuildConfig.APPLICATION_ID}"))
                context.showMusicMessage("Đã sao chép thông tin phiên bản")
            }
            link.startsWith("currentmusic:") -> onOpenDocument(LegalDocument.valueOf(link.substringAfter(':')))
            else -> openExternalLink(context, link)
        }
    }, enableBlur, onOpenMember, onSupport)
}

@Immutable
data class AboutLink(val fullText: String, val url: String)

@Immutable
class AboutUiState {
    val title: String = "Giới thiệu"
    val appName: String = AppMetadata.APP_NAME
    val versionName: String = "v${BuildConfig.VERSION_NAME}"
    val links = listOf(
        AboutLink("GitHub", "https://github.com/bileizhen/CurrentMusicX"),
        AboutLink("Nhóm QQ", "https://qm.qq.com/q/LZx3ulz3oG"),
        AboutLink("Giấy phép mã nguồn mở", "currentmusic:LICENSE"),
        AboutLink("Tuyên bố của bên thứ ba", "currentmusic:NOTICES"),
        AboutLink("Quyền riêng tư", "currentmusic:PRIVACY"),
    ) +
        listOfNotNull(
            AppMetadata.WEBSITE_URL.takeIf { it.isNotBlank() }?.let { AboutLink("Trang web", it) },
            AppMetadata.PRIVACY_URL.takeIf { it.isNotBlank() }?.let { AboutLink("Chính sách quyền riêng tư trực tuyến", it) },
        )
}

@Immutable
data class AboutScreenActions(val onBack: () -> Unit, val onOpenLink: (String) -> Unit)

@Composable
fun AboutScreenMiuix(state: AboutUiState, actions: AboutScreenActions, enableBlur: Boolean,
    onOpenMember: (AboutMember, String) -> Unit, onSupport: () -> Unit = {}) {
    val colors = MiuixTheme.colorScheme
    SettingsPage(state.title, "about_screen", actions.onBack) {
        item("brand") {
            Column(Modifier.fillMaxWidth().padding(vertical = 28.dp), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Image(painterResource(R.drawable.ic_launcher_foreground), "Biểu tượng ứng dụng",
                    Modifier.size(88.dp).testTag("about_logo"), colorFilter = ColorFilter.tint(colors.onBackground))
                Text(state.appName, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Text(state.versionName, Modifier.testTag("about_version").clickable { actions.onOpenLink("currentmusic:version") }
                    .padding(horizontal = 16.dp, vertical = 8.dp), fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
            }
        }
        item("project") { SettingsSection("Dự án", MusicIcons.Info) {
            PageNavigationPreference("Ủng hộ nhà phát triển", "Tự nguyện tài trợ cho việc phát triển và bảo trì dự án",
                Modifier.testTag("about_open_support"), MusicIcons.Heart, onSupport)
            SettingsDivider()
            state.links.filterNot { it.fullText == "Chính sách quyền riêng tư trực tuyến" }.distinctBy { it.url }.forEachIndexed { index, link ->
                if (index > 0) SettingsDivider()
                PageNavigationPreference(link.fullText, onClick = { actions.onOpenLink(link.url) })
            }
        } }
        AboutCredits.sections.forEach { section ->
            item("credits_${section.title}") { SettingsSection(section.title, if (section.title == "Nhóm CDN") MusicIcons.Storage else MusicIcons.Heart) {
                section.members.forEachIndexed { index, member ->
                    if (index > 0) SettingsDivider(68.dp)
                    SettingsPreference(member.name, member.role, onClick = { onOpenMember(member, section.title) },
                        leading = { QqAvatar(member.qq) },
                        trailing = { top.yukonga.miuix.kmp.basic.Icon(MusicIcons.Forward, null, Modifier.size(16.dp)) })
                }
            } }
        }
    }
}
