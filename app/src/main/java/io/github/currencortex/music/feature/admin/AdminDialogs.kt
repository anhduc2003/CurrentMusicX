package io.github.currencortex.music.feature.admin

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.admin.*
import io.github.currencortex.music.data.profile.userLevelLabel
import io.github.currencortex.music.feature.update.MarkdownText
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun AdminDetail(destination: AdminDestination, state: AdminState, actions: AdminActions,
    open: (AdminDestination) -> Unit, confirm: (AdminConfirmation) -> Unit, onBack: () -> Unit) {
    key(destination) {
        when (destination) {
            is AdminDestination.User -> AdminUserDetail(destination.user, state, open, confirm, onBack)
            is AdminDestination.EditUser -> AdminUserEditor(destination, state, actions, { open(AdminDestination.User(destination.user)) }, onBack)
            is AdminDestination.Announcement -> AdminAnnouncementEditor(destination.item, state, actions, confirm, onBack)
        }
    }
}

@Composable private fun AdminUserDetail(initial: AdminUser, state: AdminState, open: (AdminDestination) -> Unit,
    confirm: (AdminConfirmation) -> Unit, onBack: () -> Unit) {
    val user = state.users?.users?.find { it.id == initial.id } ?: initial
    MusicSubpage("Quản lý người dùng", "admin_user_detail", onBack, modifier = Modifier.background(MiuixTheme.colorScheme.background)) { inset ->
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(start = 16.dp, end = 16.dp, top = inset + 12.dp,
            bottom = LocalMusicBottomInset.current + 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AdminCard(user.name, "@${user.username} · ID ${user.id}") {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { AdminBadge(adminRole(user)); if (user.disabled) AdminBadge("Đã vô hiệu hóa", true) }
                AdminMetric("Danh hiệu", user.title.ifBlank { "Chưa thiết lập" }); AdminMetric("Cấp độ và điểm", "${userLevelLabel(user.level)} · ${adminCount(user.points)} điểm")
                AdminMetric("Thiết bị trực tuyến", "${user.sessions} thiết bị")
            }
            if (user.email.isNotBlank() || user.phone.isNotBlank()) AdminExpandable("Thông tin liên hệ", "Xem email và số điện thoại", "admin_user_contact") {
                if (user.email.isNotBlank()) AdminMetric("Email", user.email)
                if (user.phone.isNotBlank()) AdminMetric("Số điện thoại", user.phone)
            }
            val allowed = AdminUserAction.entries.filter { state.actor?.let { actor -> it.allowed(actor, user) } == true }
            if (allowed.isEmpty()) SettingsHint(if (state.actor?.id == user.id) "Tài khoản hiện tại. Hãy chỉnh sửa thông tin đăng nhập của bạn trong mục Tài khoản và bảo mật." else "Vai trò hiện tại không thể quản lý tài khoản này.")
            listOf("Thông tin và phát triển" to listOf(AdminUserAction.TITLE, AdminUserAction.PROGRESSION),
                "Quyền quản lý" to listOf(AdminUserAction.ADMIN, AdminUserAction.SUPER, AdminUserAction.OWNER),
                "Bảo mật tài khoản" to listOf(AdminUserAction.PASSWORD, AdminUserAction.KICK, AdminUserAction.DISABLE)).forEach { (title, all) ->
                val rows = all.filter { it in allowed }
                if (rows.isNotEmpty()) {
                    SettingsListHeader(title)
                    SettingsGroup { rows.forEachIndexed { index, action ->
                        if (index > 0) SettingsDivider(52.dp)
                        val edit = action in setOf(AdminUserAction.TITLE, AdminUserAction.PROGRESSION, AdminUserAction.PASSWORD)
                        AdminActionRow(userActionLabel(action, user), userActionSummary(action, user),
                            if (action == AdminUserAction.PROGRESSION) MusicIcons.Check else MusicIcons.Account,
                            "admin_action_${action.name.lowercase()}", enabled = !state.loading && !state.busy,
                            dangerous = action in setOf(AdminUserAction.DISABLE, AdminUserAction.KICK)) {
                            if (edit) open(AdminDestination.EditUser(user, action)) else confirm(AdminConfirmation.User(user, action))
                        }
                    } }
                }
            }
        }
    }
}

@Composable private fun AdminUserEditor(destination: AdminDestination.EditUser, state: AdminState, actions: AdminActions,
    onBack: () -> Unit, done: () -> Unit) {
    val target = destination.user; val action = destination.action
    val initial = when (action) { AdminUserAction.TITLE -> target.title; AdminUserAction.PROGRESSION -> target.points.toString(); else -> "" }
    var value by remember { mutableStateOf(initial) }
    var passwordAgain by remember { mutableStateOf("") }
    var level by remember { mutableStateOf(target.level) }
    val titleMax = state.overview?.number("titleMax") ?: 16L
    val validation = when (action) {
        AdminUserAction.PASSWORD -> when {
            value.length !in 6..64 -> "Mật khẩu mới phải có từ 6–64 ký tự"
            value != passwordAgain -> "Hai lần nhập mật khẩu phải giống nhau"
            else -> null
        }
        AdminUserAction.TITLE -> if (value.codePointCount(0, value.length) > titleMax) "Danh hiệu tối đa $titleMax ký tự" else null
        AdminUserAction.PROGRESSION -> state.rules?.let { rules -> when {
            value.toLongOrNull()?.let { it in 0..rules.maxPoints } != true -> "Vui lòng nhập số nguyên trong khoảng 0–${rules.maxPoints}"
            level !in rules.levels.map { it.level } + rules.trial.plusLevel -> "Vui lòng chọn cấp độ"
            else -> null
        } } ?: "Không thể tải quy tắc cấp độ lúc này, vui lòng thử lại"
        else -> null
    }
    AdminEditorFrame(userActionLabel(action, target), "admin_user_editor", state, onBack,
        dirty = value != initial || passwordAgain.isNotEmpty() || level != target.level, valid = validation == null,
        saveLabel = if (action == AdminUserAction.PASSWORD) "Xác nhận đặt lại mật khẩu" else "Lưu thay đổi",
        save = { actions.userAction(target, action, value, level, done) }) {
        AdminCard(target.name, "@${target.username} · ID ${target.id}") { Text(userActionSummary(action, target), fontSize = 13.sp) }
        SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            AdminField(if (action == AdminUserAction.PASSWORD) "Mật khẩu mới" else if (action == AdminUserAction.PROGRESSION) "Tổng điểm" else "Danh hiệu",
                value, { value = it; actions.clearOperationError() }, "admin_action_value", enabled = !state.busy,
                password = action == AdminUserAction.PASSWORD, number = action == AdminUserAction.PROGRESSION)
            if (action == AdminUserAction.TITLE) Text("Tối đa $titleMax ký tự, để trống để xóa danh hiệu", fontSize = 12.sp)
            if (action == AdminUserAction.PASSWORD) AdminField("Nhập lại mật khẩu mới", passwordAgain, { passwordAgain = it; actions.clearOperationError() },
                "admin_password_again", enabled = !state.busy, password = true)
            if (validation != null) SettingsHint(validation, error = value.isNotEmpty())
        } }
        if (action == AdminUserAction.PROGRESSION) {
            if (state.rules == null) AdminNotice(state.rulesError ?: "Không thể tải quy tắc cấp độ", error = true, retry = actions::refresh)
            else {
                SettingsListHeader("Cấp độ")
                SettingsGroup {
                    (state.rules.levels.map { it.level } + state.rules.trial.plusLevel).distinct().forEach { option ->
                        MusicDialogChoiceRow(userLevelLabel(option) ?: "Cấp độ $option", level == option, { if (!state.busy) level = option })
                    }
                }
            }
        }
    }
}

@Composable private fun AdminAnnouncementEditor(item: AdminAnnouncement?, state: AdminState, actions: AdminActions,
    confirm: (AdminConfirmation) -> Unit, onBack: () -> Unit) {
    var title by remember { mutableStateOf(item?.title.orEmpty()) }
    var body by remember { mutableStateOf(item?.body.orEmpty()) }
    var link by remember { mutableStateOf(item?.link.orEmpty()) }
    var pinned by remember { mutableStateOf(item?.pinned ?: false) }
    var preview by remember { mutableStateOf(item != null) }
    val editable = item == null || item.canEdit
    val dirty = title != item?.title.orEmpty() || body != item?.body.orEmpty() || link != item?.link.orEmpty() || pinned != (item?.pinned ?: false)
    val validLink = link.isBlank() || runCatching { java.net.URI(link.trim()).let {
        it.scheme in setOf("http", "https") && !it.host.isNullOrBlank() && it.userInfo == null
    } }.getOrDefault(false)
    AdminEditorFrame(if (item == null) "Đăng thông báo" else "Chi tiết thông báo", "admin_announcement_editor", state, onBack, dirty && editable,
        valid = title.trim().length in 1..60 && validLink && (dirty || item == null),
        saveLabel = if (item == null) "Xác nhận đăng" else "Lưu thay đổi", showSave = editable,
        save = { actions.announcement(item, title, body, link.trim(), pinned, onBack) }) {
        if (editable) SettingsSegmentedControl(listOf("Chỉnh sửa", "Xem trước"), if (preview) 1 else 0, { preview = it == 1 }, tag = "admin_announcement_mode")
        if (preview || !editable) {
            AdminCard(title.ifBlank { "Tiêu đề thông báo" }, item?.let { "${it.author} · ${adminTime(it.createdAt)}" } ?: "Xem trước trước khi đăng") {
                if (pinned) AdminBadge("Ghim")
                if (item?.removed == true) AdminBadge("Đã gỡ", true)
                if (body.isNotBlank()) MarkdownText(body, compact = true, onLinkClick = {}) else Text("Bản xem trước nội dung sẽ hiển thị tại đây", fontSize = 13.sp)
                if (link.isNotBlank()) Text(link, fontSize = 12.sp, color = MiuixTheme.colorScheme.primary)
            }
        } else {
            SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                AdminField("Tiêu đề · ${title.length}/60", title, { title = it; actions.clearOperationError() }, "admin_announcement_title", enabled = !state.busy)
                if (title.length > 60) SettingsHint("Tiêu đề tối đa 60 ký tự", error = true)
                AdminField("Nội dung · hỗ trợ Markdown", body, { body = it; actions.clearOperationError() }, "admin_announcement_body", enabled = !state.busy, multiline = true)
                AdminField("Liên kết chi tiết (không bắt buộc)", link, { link = it; actions.clearOperationError() }, "admin_announcement_link", enabled = !state.busy)
                if (!validLink) SettingsHint("Vui lòng nhập liên kết http / https hợp lệ", error = true)
                SettingsSwitch("Ghim thông báo", pinned, { if (!state.busy) pinned = it })
            } }
        }
        if (item != null && editable) {
            SettingsListHeader("Trạng thái thông báo")
            SettingsGroup {
                if (item.removed) AdminActionRow("Khôi phục thông báo", "Sau khi khôi phục, thông báo sẽ hiển thị lại trên ứng dụng", MusicIcons.Update, "admin_announcement_restore", enabled = !state.busy && !dirty) {
                    confirm(AdminConfirmation.Announcement(item, "restore", "Khôi phục thông báo"))
                } else {
                    AdminActionRow(if (item.pinned) "Bỏ ghim" else "Ghim thông báo", "Điều chỉnh thứ tự hiển thị thông báo", MusicIcons.Sort, "admin_announcement_pin", enabled = !state.busy && !dirty) {
                        confirm(AdminConfirmation.Announcement(item, "pin", if (item.pinned) "Bỏ ghim" else "Ghim thông báo"))
                    }
                    SettingsDivider(52.dp)
                    AdminActionRow("Gỡ thông báo", "Thông báo sẽ không còn hiển thị trên ứng dụng và có thể khôi phục bất cứ lúc nào", MusicIcons.Close, "admin_announcement_remove", enabled = !state.busy && !dirty, dangerous = true) {
                        confirm(AdminConfirmation.Announcement(item, "remove", "Gỡ thông báo"))
                    }
                }
            }
            if (dirty) SettingsHint("Vui lòng lưu thay đổi trước khi điều chỉnh trạng thái thông báo.")
        }
        SettingsHint(if (!editable) "Thông báo này do quản trị viên khác đăng; tài khoản hiện tại chỉ có thể xem." else if (item?.removed == true) "Thông báo này đã bị gỡ; sau khi lưu, bạn vẫn cần khôi phục để hiển thị lại." else "Sau khi lưu, người dùng sẽ thấy thông báo đã cập nhật.")
    }
}

@Composable private fun AdminField(label: String, value: String, change: (String) -> Unit, tag: String,
    enabled: Boolean, password: Boolean = false, number: Boolean = false, multiline: Boolean = false) {
    Text(label, fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
    TextField(value, change, enabled = enabled, singleLine = !multiline,
        modifier = Modifier.fillMaxWidth().then(if (multiline) Modifier.heightIn(min = 180.dp) else Modifier).testTag(tag),
        visualTransformation = if (password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = if (password) KeyboardType.Password else if (number) KeyboardType.Number else KeyboardType.Text))
}

@Composable private fun AdminEditorFrame(title: String, tag: String, state: AdminState, onBack: () -> Unit, dirty: Boolean,
    valid: Boolean, saveLabel: String, showSave: Boolean = true, save: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    var discard by remember { mutableStateOf(false) }
    val focus = LocalFocusManager.current
    fun leave() { if (!state.busy) { focus.clearFocus(); if (dirty) discard = true else onBack() } }
    BackHandler { leave() }
    val keyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    MusicSubpage(title, tag, ::leave, modifier = Modifier.background(MiuixTheme.colorScheme.background)) { inset ->
        Column(Modifier.fillMaxSize().padding(top = inset).imePadding()) {
            Column(Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState()).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp), content = content)
            state.operationError?.let { AdminNotice(it, error = true) }
            if (showSave) GlassActionButton(if (state.busy) "Đang gửi…" else saveLabel, { focus.clearFocus(); save() },
                Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp).testTag("admin_editor_save"),
                primary = true, enabled = valid && !state.busy && !state.loading)
            Spacer(Modifier.height(if (keyboardVisible) 8.dp else LocalMusicBottomInset.current + 12.dp))
        }
    }
    if (discard) SettingsUiScope { MusicDialog("Bỏ các thay đổi chưa lưu?", { discard = false }) {
        Text("Rời đi, các thay đổi lần này sẽ không được lưu.")
        MusicDialogActions {
            MusicDialogButton("Tiếp tục chỉnh sửa", { discard = false }, Modifier.weight(1f))
            MusicDialogButton("Bỏ thay đổi", { discard = false; onBack() }, destructive = true, modifier = Modifier.weight(1f).testTag("admin_discard"))
        }
    } }
}

@Composable internal fun AdminConfirmDialog(confirmation: AdminConfirmation, state: AdminState, actions: AdminActions, close: () -> Unit, done: () -> Unit) {
    val title: String
    val description: String
    val submit: () -> Unit
    when (confirmation) {
        is AdminConfirmation.User -> {
            title = userActionLabel(confirmation.action, confirmation.user)
            description = "${confirmation.user.name}（@${confirmation.user.username}，ID ${confirmation.user.id}）\n${userActionSummary(confirmation.action, confirmation.user)}"
            submit = { actions.userAction(confirmation.user, confirmation.action, "", null, done) }
        }
        is AdminConfirmation.Announcement -> {
            title = confirmation.label; description = "${confirmation.item.title}\n" + if (confirmation.action == "remove") "Thông báo này sẽ không còn hiển thị với người dùng. Bạn có thể khôi phục thông báo trong bảng điều khiển." else "Trạng thái hiển thị của thông báo này sẽ được cập nhật。"
            submit = { actions.announcementAction(confirmation.item, confirmation.action, done) }
        }
        is AdminConfirmation.Kick -> {
            title = "Đăng xuất khỏi tất cả thiết bị"; description = "Xác nhận thu hồi tất cả phiên đăng nhập của @${confirmation.session.username}? Người dùng này sẽ cần đăng nhập lại."
            submit = { actions.kick(confirmation.session, done) }
        }
        AdminConfirmation.Cache -> {
            title = "Xóa bộ nhớ đệm phụ trợ"; description = "Xóa bộ nhớ đệm lời bài hát, bình luận và chi tiết; bộ nhớ đệm dò tìm mức chất lượng âm thanh sẽ được giữ lại."
            submit = { actions.clearCache(done) }
        }
    }
    SettingsUiScope { MusicDialog(title, close) {
        Text(description)
        state.operationError?.let { SettingsHint(it, error = true) }
        MusicDialogActions {
            MusicDialogButton("Hủy", close, Modifier.weight(1f), enabled = !state.busy)
            MusicDialogButton(if (state.busy) "Đang gửi…" else "Xác nhận", submit, Modifier.weight(1f).testTag("admin_confirm_action"),
                enabled = !state.busy && !state.loading, destructive = true)
        }
    } }
}
internal fun userActionLabel(action: AdminUserAction, target: AdminUser): String = when (action) {
     AdminUserAction.DISABLE -> if (target.disabled) "Bỏ cấm" else "Cấm tài khoản"
     AdminUserAction.ADMIN -> if (target.isAdmin) "Hủy quyền quản trị viên" else "Đặt làm quản trị viên"
     AdminUserAction.SUPER -> if (target.isSuper) "Hủy quyền siêu quản trị viên" else "Đặt làm siêu quản trị viên"
     AdminUserAction.OWNER -> if (target.isOwner) "Hủy quyền chủ trang" else "Đặt làm chủ trang"
    else -> action.label
}
private fun userActionSummary(action: AdminUserAction, target: AdminUser) = when (action) {
     AdminUserAction.TITLE -> "Hiển thị bên cạnh danh tính người dùng; để trống để xóa"
     AdminUserAction.PROGRESSION -> "Điều chỉnh điểm tích lũy và cấp hiện tại"
     AdminUserAction.PASSWORD -> "Sau khi đặt lại, người dùng này sẽ cần đăng nhập lại"
     AdminUserAction.KICK -> "Thu hồi tất cả phiên đăng nhập của người dùng này"
     AdminUserAction.DISABLE -> if (target.disabled) "Cho phép người dùng này đăng nhập lại" else "Cấm đăng nhập và thu hồi các phiên hiện có"
     AdminUserAction.OWNER -> if (target.isOwner) "Sau khi hủy quyền chủ trang, người dùng sẽ trở lại làm siêu quản trị viên" else "Cấp quyền chủ trang; thay đổi có hiệu lực ngay lập tức"
     else -> "Điều chỉnh quyền quản trị; thay đổi có hiệu lực ngay lập tức"
}
