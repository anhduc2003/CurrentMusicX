package io.github.currencortex.music.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.admin.*
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val adminSorts = listOf("reg" to "Đăng ký mới nhất", "reg_asc" to "Đăng ký sớm nhất", "name" to "Biệt danh", "active" to "Hoạt động gần đây",
    "sessions" to "Thiết bị trực tuyến", "likes" to "Lượt thích", "plays" to "Lượt phát", "role" to "Vai trò")

@Composable internal fun AdminSearch(state: AdminState, actions: AdminActions) {
    var query by rememberSaveable { mutableStateOf(state.query) }
    LaunchedEffect(state.query) { query = state.query }
    var sorting by rememberSaveable { mutableStateOf(false) }
    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    fun search(value: String = query, sort: String = state.sort) {
        if (state.busy) return
        focus.clearFocus(); keyboard?.hide(); actions.search(value, sort)
    }
    Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        MusicSearchBar(query, { search() }, Modifier.fillMaxWidth(), onInput = {
            query = it
            if (it.isEmpty() && state.query.isNotEmpty()) search("")
        }, placeholder = "Tên người dùng, biệt danh, email hoặc số điện thoại", inputTag = "admin_user_query", submitTag = "admin_search",
            clearTag = "admin_search_clear", onCancel = { query = ""; focus.clearFocus(); keyboard?.hide() })
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(if (state.loading) "Đang tìm…" else "${state.users?.total ?: 0} người dùng", Modifier.weight(1f), fontSize = 12.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            Row(Modifier.heightIn(min = 48.dp).clickable(enabled = !state.busy, role = Role.Button) {
                focus.clearFocus(); keyboard?.hide(); sorting = true
            }.testTag("admin_sort").padding(start = 12.dp, end = 4.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(adminSorts.firstOrNull { it.first == state.sort }?.second ?: "Sắp xếp", fontSize = 13.sp,
                    color = MiuixTheme.colorScheme.primary)
                Icon(MusicIcons.Sort, null, Modifier.size(16.dp), tint = MiuixTheme.colorScheme.primary)
            }
        }
    }
    if (sorting) MusicDialog("Sắp xếp người dùng", { sorting = false }) {
        Column(Modifier.heightIn(max = 380.dp).verticalScroll(rememberScrollState())) {
            adminSorts.forEach { (value, label) -> MusicDialogChoiceRow(label, state.sort == value, {
                sorting = false; search(query, value)
            }) }
        }
    }
}

internal fun LazyListScope.adminUsers(state: AdminState, actions: AdminActions, open: (AdminDestination) -> Unit) {
    val result = state.users ?: return
    if (result.users.isEmpty()) item { AdminEmpty("Không tìm thấy người dùng phù hợp", "Hãy thử từ khóa khác hoặc xem tất cả tài khoản", "Xóa tìm kiếm") { actions.search("", state.sort) } }
    items(result.users, key = { "user-${it.id}" }) { user -> SettingsGroup {
        Row(Modifier.fillMaxWidth().clickable(role = Role.Button, enabled = !state.loading && !state.busy) { open(AdminDestination.User(user)) }
            .testTag("admin_manage_${user.id}").padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(Modifier.size(44.dp).clip(CircleShape).background(MiuixTheme.colorScheme.primary.copy(alpha = .12f)), contentAlignment = Alignment.Center) {
                Text(user.name.take(1).uppercase(), fontSize = 20.sp, fontWeight = FontWeight.Medium, color = MiuixTheme.colorScheme.primary)
            }
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(user.name, fontSize = 16.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("@${user.username}", fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (user.isAdmin || user.isSuper || user.isOwner) AdminBadge(adminRole(user))
                    if (user.disabled) AdminBadge("Đã vô hiệu hóa", warning = true)
                    if (user.sessions > 0) Text("${user.sessions} thiết bị trực tuyến", fontSize = 11.sp, color = MiuixTheme.colorScheme.primary)
                }
            }
            Icon(MusicIcons.Forward, null, Modifier.size(16.dp), tint = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        }
    } }
    if (result.total > 0 || state.offset > 0) item { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        MusicTextAction("Trang trước", { actions.page(-1) }, Modifier.testTag("admin_previous_page"), enabled = !state.loading && !state.busy && state.offset > 0)
        Text("${state.offset / AdminRepository.PAGE_SIZE + 1} / ${((result.total + AdminRepository.PAGE_SIZE - 1) / AdminRepository.PAGE_SIZE).coerceAtLeast(1)}",
            fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        MusicTextAction("Trang sau", { actions.page(1) }, Modifier.testTag("admin_next_page"),
            enabled = !state.loading && !state.busy && state.offset + AdminRepository.PAGE_SIZE < result.total)
    } }
}

internal fun LazyListScope.adminAnnouncements(state: AdminState, open: (AdminDestination) -> Unit) {
    val rows = state.announcements ?: return
    if (rows.isEmpty()) item { AdminEmpty("Chưa có thông báo", "Sau khi đăng, người dùng sẽ thấy thông báo trong ứng dụng.") }
    items(rows, key = { "announcement-${it.id}" }) { item -> SettingsGroup {
        Column(Modifier.fillMaxWidth().clickable(role = Role.Button, enabled = !state.busy) { open(AdminDestination.Announcement(item)) }
            .testTag("admin_announcement_${item.id}").padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                AdminBadge(if (item.removed) "Đã gỡ" else if (item.pinned) "Đã ghim" else "Đang có hiệu lực", warning = item.removed)
                if (!item.canEdit) AdminBadge("Chỉ đọc")
            }
            Text(item.title, fontSize = 17.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(item.body, fontSize = 13.sp, maxLines = 2, overflow = TextOverflow.Ellipsis, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("${item.author} · ${adminTime(item.createdAt)}", Modifier.weight(1f), fontSize = 11.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                Icon(MusicIcons.Forward, null, Modifier.size(16.dp))
            }
        }
    } }
}

internal fun LazyListScope.adminDevices(state: AdminState, confirm: (AdminConfirmation) -> Unit) {
    val sessions = state.sessions ?: return
    val groups = sessions.groupBy { it.user_id }.values.toList()
    item { Text("${groups.size} người dùng · ${sessions.size} thiết bị" + if (sessions.size >= 100) "（Hiển thị tối đa 100 thiết bị）" else "",
        Modifier.padding(horizontal = 2.dp, vertical = 4.dp), fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary) }
    if (groups.isEmpty()) item { AdminEmpty("Chưa có thiết bị trực tuyến", "Các phiên mới sẽ hiển thị sau khi làm mới.") }
    items(groups, key = { "devices-${it.first().user_id}" }) { rows ->
        val first = rows.first()
        AdminExpandable(first.nickname.ifBlank { first.username }, "@${first.username} · ${rows.size} thiết bị", "admin_devices_${first.user_id}") {
            rows.forEachIndexed { index, session ->
                if (index > 0) SettingsDivider(0.dp)
                Text(session.device, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Text("${session.platform} · Hoạt động gần đây ${adminTime(session.last_seen)}", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            }
            if (first.user_id == state.actor?.id) SettingsHint("Tài khoản hiện tại, hãy quản lý đăng nhập của bạn trong mục Tài khoản và bảo mật.")
            else MusicTextAction("Đăng xuất khỏi tất cả thiết bị", { confirm(AdminConfirmation.Kick(first)) }, enabled = !state.loading && !state.busy, destructive = true)
        }
    }
}
internal fun adminRole(user: AdminUser) = if (user.isOwner) "Chủ trang" else if (user.isSuper) "Quản trị viên cấp cao" else if (user.isAdmin) "Quản trị viên" else "Người dùng thường"
