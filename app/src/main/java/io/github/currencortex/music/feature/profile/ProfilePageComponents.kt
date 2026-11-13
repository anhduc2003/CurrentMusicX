package io.github.currencortex.music.feature.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.profile.ProfileStats
import io.github.currencortex.music.data.profile.listeningDuration
import io.github.currencortex.music.ui.component.rememberPageNavigationSource
import io.github.currencortex.music.ui.component.LocalRootTabSwipe
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun ProfilePageTitle(navigate: (String) -> Unit, onSettings: (() -> Unit)?,
    public: Boolean = false, loading: Boolean = false, reload: () -> Unit = {}) {
    val actions: @Composable RowScope.() -> Unit = {
        if (public) GlassIconButton(reload, "Làm mới", enabled = !loading) { Icon(MusicIcons.Update, null) }
        else GlassIconButton({ navigate("user/announcements") }, "Thông báo", Modifier.testTag("me_announcements")) {
            Icon(MusicIcons.File, null)
        }
     onSettings?.let { action -> GlassIconButton(action, "Cài đặt", Modifier.testTag("me_settings")) {
            Icon(Icons.Outlined.Settings, null)
        } }
    }
    SettingsUiScope {
        if (public) Row(Modifier.fillMaxWidth().height(SettingsToolbarHeight).padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
             Text("Trang cá nhân", Modifier.weight(1f), fontSize = 24.sp, lineHeight = 30.sp,
                fontWeight = FontWeight.SemiBold, color = MiuixTheme.colorScheme.onSurface)
            actions()
        }
         else SettingsRootTopBar("Của tôi", Modifier.testTag("me_root_toolbar"), actions = actions)
    }
}

@Composable internal fun ProfilePageLayout(navigate: (String) -> Unit, onSettings: (() -> Unit)?,
    public: Boolean = false, loading: Boolean = false, reload: () -> Unit = {}, onBack: (() -> Unit)? = null, content: @Composable (Dp) -> Unit) {
     if (public) MusicSubpage("Trang cá nhân", "public_profile", onBack, actions = {
         GlassIconButton(reload, "Làm mới", enabled = !loading) { Icon(MusicIcons.Update, null) }
    }, content = content)
    else SettingsScrollLayout(Modifier.fillMaxSize(), topBar = {
        ProfilePageTitle(navigate, onSettings, loading = loading, reload = reload)
    }, content = content)
}

@Composable internal fun ProfileDestination(title: String, summary: String?, icon: ImageVector,
    tag: String, onClick: () -> Unit) {
    val source = rememberPageNavigationSource(title, summary.orEmpty(), icon)
    SettingsUiScope {
        SettingsPreference(title, summary, Modifier.testTag(tag).then(source.modifier), onClick = { source.open(onClick) },
            leading = { Icon(icon, null, Modifier.size(24.dp)) },
            trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp), tint = MiuixTheme.colorScheme.onSurfaceVariantSummary) })
    }
}

@Composable internal fun ProfileLibrarySection(navigate: (String) -> Unit) {
     SettingsListSection("Nhạc của tôi") {
         ProfileDestination("Yêu thích", "Bài hát đã lưu", MusicIcons.Heart, "open_likes") { navigate("lib/likes") }
        SettingsDivider(52.dp)
         ProfileDestination("Danh sách phát", "Danh sách phát đã tạo và đã lưu", MusicIcons.List, "open_playlists") { navigate("lib/playlists") }
        SettingsDivider(52.dp)
         ProfileDestination("Nghe gần đây", "Xem lại các bài hát đã nghe", MusicIcons.Update, "open_recent") { navigate("lib/recent") }
        SettingsDivider(52.dp)
         ProfileDestination("Thời lượng nghe nhạc", "Lịch sử nghe nhạc trên thiết bị và Nhạc Vân NetEase", MusicIcons.Headphones, "open_listening") { navigate("user/listening") }
        SettingsDivider(52.dp)
         ProfileDestination("Nhạc trên thiết bị", "Bài hát trên điện thoại", MusicIcons.Device, "open_local_music") { navigate("lib/local") }
    }
}

/** Native and CurrentMusic accounts share destinations and the same list geometry. */
@Composable internal fun ProfileServicesSection(navigate: (String) -> Unit, tasks: Boolean,
    native: Boolean = false, bound: Boolean = true,
    navidromeConfigured: Boolean = false, embyConfigured: Boolean = false) {
     SettingsListSection("Dịch vụ âm nhạc") {
         ProfileDestination(if (native && !bound) "Đăng nhập Nhạc Vân NetEase" else "Tài khoản Nhạc Vân NetEase",
             if (native && !bound) "Trước tiên hãy đăng ký và đăng nhập CurrentMusic" else "Liên kết tài khoản, đồng bộ danh sách phát", MusicIcons.Account, "open_binding") { navigate("user/binding") }
        if (tasks) {
            SettingsDivider(52.dp)
             ProfileDestination("Nhiệm vụ Nhạc Vân NetEase", "Điểm danh, Vân Bối và tăng cấp hội viên", MusicIcons.Check, "open_netease_tasks") { navigate("user/netease-tasks") }
        }
        SettingsDivider(52.dp)
         ProfileDestination("Nhập danh sách phát", "Liên kết, văn bản, hình ảnh và bài hát trên thiết bị", MusicIcons.Transfer, "profile_playlist_import") { navigate("user/playlist-import") }
        if (navidromeConfigured) {
            SettingsDivider(52.dp)
             ProfileDestination("Thư viện Navidrome", "Máy chủ nhạc của riêng bạn", MusicIcons.Storage, "profile_navidrome") { navigate("navidrome") }
        }
        if (embyConfigured) {
            SettingsDivider(52.dp)
             ProfileDestination("Thư viện Emby", "Máy chủ nhạc của riêng bạn", MusicIcons.Storage, "profile_emby") { navigate("emby") }
        }
    }
}

@Composable internal fun ProfileAccountSection(navigate: (String) -> Unit, native: Boolean = false, administrator: Boolean = false) {
     SettingsListSection("Tài khoản") {
         if (native) ProfileDestination("Tài khoản CurrentMusic", "Đăng ký và đăng nhập, trang trí ảnh đại diện và nghe cùng nhau", MusicIcons.Account,
            "open_currentmusic_login") { navigate("user/login") }
        else {
             ProfileDestination("Cấp độ và phát triển", "Xem điểm, tiến độ hằng ngày và thử thách cấp độ", MusicIcons.Check, "open_levels") { navigate("user/levels") }
            SettingsDivider(52.dp)
             ProfileDestination("Phụ kiện ảnh đại diện", "Trang trí ảnh đại diện của bạn", MusicIcons.Image, "open_decorations") { navigate("user/decorations") }
            SettingsDivider(52.dp)
            ProfileDestination("Tài khoản và bảo mật", "Chuyển đổi tài khoản, đổi mật khẩu và đăng xuất", MusicIcons.Account, "open_security") { navigate("user/security") }
            if (administrator) {
                SettingsDivider(52.dp)
                ProfileDestination("Bảng điều khiển quản trị viên", "Quản lý người dùng, thông báo, thiết bị và hệ thống", MusicIcons.Account, "open_admin") { navigate("user/admin") }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable internal fun ProfileStatsBlock(stats: ProfileStats, onListening: (() -> Unit)? = null) {
    val muted = MiuixTheme.colorScheme.onSurfaceVariantSummary
    Column(Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 16.dp).testTag("profile_stats"),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("Lượt thích" to "${stats.likes}", "Bộ sưu tập" to "${stats.favs}", "Danh sách phát" to "${stats.playlists}").forEach { (label, value) ->
                Column(Modifier.weight(1f).semantics(mergeDescendants = true) {},
                    horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(value, fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold)
                    Text(label, fontSize = 12.sp, lineHeight = 16.sp, color = muted)
                }
            }
        }
        BoxWithConstraints(Modifier.fillMaxWidth()) {
        val columns = if (maxWidth >= 280.dp * LocalDensity.current.fontScale + 10.dp) 2 else 1
        FlowRow(Modifier.fillMaxWidth(), maxItemsInEachRow = columns,
            horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val tile = Modifier.weight(1f).background(MiuixTheme.colorScheme.onSurface.copy(alpha = .035f), RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp)
            Column(tile, verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text("Số ngày nghe nhạc", fontSize = 12.sp, lineHeight = 16.sp, color = muted)
                Text("${stats.playDays} ngày", fontSize = 15.sp, lineHeight = 20.sp)
            }
            Column(tile.then(onListening?.let { Modifier.clickable(role = Role.Button, onClick = it).testTag("profile_listening_duration") }
                ?: Modifier), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text("Thời lượng nghe nhạc", fontSize = 12.sp, lineHeight = 16.sp, color = muted)
                Text(listeningDuration(stats.listenMs), fontSize = 15.sp, lineHeight = 20.sp)
            }
        }
        }
    }
}
