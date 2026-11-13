package io.github.currencortex.music.feature.room

import io.github.currencortex.music.ui.component.MusicChoiceDialog

import io.github.currencortex.music.ui.component.MusicDialogButton

import io.github.currencortex.music.ui.component.musicScrollPadding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.res.painterResource
import io.github.currencortex.music.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.testTag
import android.content.ClipData
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.ui.util.collectAsPageState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.room.*
import io.github.currencortex.music.core.network.RequestSession
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.MusicCover
import io.github.currencortex.music.ui.component.MusicTextAction
import io.github.currencortex.music.ui.component.MusicCategoryTabs
import io.github.currencortex.music.ui.component.MusicSearchBar
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.component.MusicDestinationRow
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.json.*
import top.yukonga.miuix.kmp.basic.*

@Composable fun RoomScreen(vm: RoomViewModel, onBack: () -> Unit, onSearch: () -> Unit, onPlayer: () -> Unit,
    onDialogActive: (Boolean) -> Unit = {}, onBinding: (() -> Unit)? = null) {
    val native by vm.container.listenTogether.state.collectAsStateWithLifecycle()
    var nativePage by rememberSaveable { mutableStateOf(native.active || native.endedRoom != null) }
    var nativeDialog by remember { mutableStateOf(false) }
    var roomDialog by remember { mutableStateOf(false) }
    LaunchedEffect(nativePage, nativeDialog, roomDialog) { onDialogActive(if (nativePage) nativeDialog else roomDialog) }
    DisposableEffect(Unit) { onDispose { onDialogActive(false) } }
    LaunchedEffect(native.active, native.endedRoom?.roomId) { if (native.active || native.endedRoom != null) nativePage = true }
    SettingsUiScope {
    ListenTogetherPageTransition(nativePage, { if (it) "netease" else "rooms" }, { if (it) 1 else 0 }, Modifier.fillMaxSize()) { showNative ->
        if (showNative) NeteaseListenTogetherScreen(vm.container, onBack = { nativePage = false }, onSearch = onSearch,
            onPlayer = onPlayer, onBinding = onBinding, onDialogActive = { nativeDialog = it })
        else CurrentMusicRoomContent(vm, onBack, onSearch, onPlayer, { roomDialog = it }, onNative = { nativePage = true })
    }
    }
}

@Composable private fun CurrentMusicRoomContent(vm: RoomViewModel, onBack: () -> Unit, onSearch: () -> Unit,
    onPlayer: () -> Unit, onDialogActive: (Boolean) -> Unit, onNative: () -> Unit) {
    val native by vm.container.listenTogether.state.collectAsStateWithLifecycle()
    val browser by vm.browser.collectAsPageState()
    val live by vm.session.state.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val passwordRoom by vm.passwordRoom.collectAsStateWithLifecycle()
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    var query by rememberSaveable { mutableStateOf("") }
    var create by rememberSaveable { mutableStateOf(false) }
    var exit by remember { mutableStateOf(false) }
    var close by remember { mutableStateOf(false) }
    var deleteRoom by remember(account.account?.id) { mutableStateOf<Pair<RoomCard, RequestSession>?>(null) }
    var memberDialog by remember { mutableStateOf(false) }
    val dialogActive = create || exit || close || memberDialog || passwordRoom != null || deleteRoom != null
    LaunchedEffect(dialogActive) { onDialogActive(dialogActive) }
    DisposableEffect(Unit) { onDispose { onDialogActive(false) } }
    val clipboard = LocalClipboard.current; val scope = rememberCoroutineScope()
    LaunchedEffect(live.detail?.room?.id) { if (live.detail != null) create = false }
    MusicSubpage("Cùng nghe", "room_page", onBack) { topInset ->
    Column(Modifier.fillMaxSize().testTag("room_screen")) {
        val detail = live.detail
        if (detail == null) {
            // Keep the viewport full-width so elastic controls can paint into the side gutters.
            LazyColumn(Modifier.fillMaxSize(),
                contentPadding = musicScrollPadding(PaddingValues(start = 16.dp, end = 16.dp,
                    top = topInset + 8.dp, bottom = 24.dp)),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    SettingsSection("Cùng bạn bè", io.github.currencortex.music.ui.component.MusicIcons.Headphones) {
                        MusicDestinationRow("Nhạc Vân NetEase cùng nghe hai người", onNative, Modifier.testTag("open_netease_listen_together"),
                            summary = if (native.active) "Đang cùng nghe · Xem phiên" else "Nghe nhạc đồng bộ với bạn bè trên Nhạc Vân NetEase")
                    }
                }
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        SettingsInfoHeader("Phòng CurrentMusic", io.github.currencortex.music.ui.component.MusicIcons.Room,
                            horizontalInset = 0.dp)
                        MusicCategoryTabs(listOf("public" to "Phòng công khai", "mine" to "Phòng của tôi"), browser.scope.query,
                            { vm.load(query, roomScope = if (it == "mine") RoomScope.MINE else RoomScope.PUBLIC) }, "room_scope")
                    }
                }
                item {
                    MusicSearchBar(query, { vm.search(query.trim()) }, onInput = { query = it },
                        placeholder = "Mã phòng gồm sáu chữ số hoặc tên phòng", inputTag = "room_query")
                }
                item {
                    FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        GlassActionButton("Tìm", { vm.search(query.trim()) }, enabled = !busy, fillWidth = false)
                        GlassActionButton("Tạo phòng", { create = true }, primary = true,
                            enabled = account.account != null && !busy, fillWidth = false)
                         GlassActionButton(if (browser.loading) "Đang làm mới" else "Làm mới", { vm.load() },
                            enabled = !browser.loading && !busy, fillWidth = false)
                    }
                    if (account.account == null) SettingsHint("Đăng nhập tài khoản CurrentMusic để tạo phòng.")
                }
                if (busy) item { ListenLoadingLabel("Đang xử lý phòng…") }
                if (browser.loading && browser.rooms.isEmpty()) item { ListenTogetherSkeleton("Đang tải phòng…", "room_browser_loading", rows = 2) }
                browser.error?.let { error -> item {
                    SettingsGroup { SettingsHint(error, error = true); MusicTextAction("Thử lại", { vm.load() }, Modifier.padding(16.dp)) }
                } }
                live.error?.let { error -> item { SettingsHint(error, error = true) } }
                items(browser.rooms, key = { it.id }) { room ->
                    SettingsGroup(Modifier.animateItem()) {
                        SettingsPreference(room.name,
                            "#${room.code} · " + if (room.archived) {
                                if (room.archiveReason == "idle") "Tự động lưu trữ khi không hoạt động" else "Đã lưu trữ"
                            } else "${room.online} người đang trực tuyến",
                            trailing = { MusicTextAction(if (room.archived) "Khôi phục và vào" else "Tham gia",
                                { if (browser.scope == RoomScope.MINE) vm.enterOwned(room) else vm.find(room.code) }, enabled = !busy) })
                        SettingsDivider()
                        SettingsHint(listOf(room.ownerName.takeIf { it.isNotBlank() },
                            if (room.freeMode) "Yêu cầu bài hát tự do" else "Chế độ thông thường").filterNotNull().joinToString(" · "))
                        if (browser.scope == RoomScope.MINE) MusicTextAction("Xóa vĩnh viễn", { deleteRoom = room to vm.container.roomRepository.session() },
                            Modifier.padding(horizontal = 16.dp, vertical = 8.dp), enabled = !busy)
                    }
                }
                if (browser.more) item { GlassActionButton("Tải thêm", { vm.load(more = true) }, enabled = !browser.loading) }
                if (!browser.loading && browser.rooms.isEmpty() && browser.error == null) item {
                    SettingsGroup { SettingsPreference(if (browser.scope == RoomScope.MINE) "Bạn chưa có phòng nào" else "Chưa có phòng công khai",
                        if (browser.scope == RoomScope.MINE) "Các phòng bạn tạo và phòng đã lưu trữ sẽ hiển thị ở đây." else "Tạo phòng hoặc dùng mã phòng sáu chữ số của bạn bè để tham gia.") }
                }
            }
        } else {
            Column(Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp, top = topInset + 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)) {
            val role = detail.role(account.account?.id ?: 0)
            SettingsGroup {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(detail.room.name, fontSize = 24.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text("#${detail.room.code} · ${detail.members.size} người · ${when(role) { RoomRole.OWNER -> "Chủ phòng"; RoomRole.ADMIN -> "Quản trị viên"; else -> "Thành viên" }}",
                fontSize = 14.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            Text(if (live.connected) "Đã kết nối · Đang đồng bộ phát nhạc" else "Đang kết nối phòng, tự động khôi phục đồng bộ…", fontSize = 13.sp, color = MiuixTheme.colorScheme.primary)
            Row { MusicTextAction("Sao chép mã phòng", onClick = { scope.launch { clipboard.setClipEntry(ClipEntry(ClipData.newPlainText("Mã phòng", detail.room.code))) } })
                MusicTextAction("Rời phòng", onClick = { exit = true }, enabled = !busy) }
            } }
            live.error?.let { Row(verticalAlignment = Alignment.CenterVertically) {
                Text(it, Modifier.weight(1f), fontSize = 13.sp)
                MusicTextAction("Đã hiểu", onClick = vm.session::dismissError)
            } }
            SettingsGroup(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(14.dp)) {
                    val track = detail.timeline?.trackMeta
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MusicCover(track?.pic.orEmpty(), Modifier.size(56.dp), cornerRadius = 16.dp)
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(track?.name ?: "Đang chờ chọn bài", fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(track?.artists?.takeIf { it.isNotBlank() } ?: if (detail.room.freeMode || !detail.room.needApproval) "Tất cả thành viên có thể chọn bài trực tiếp" else "Bài hát sẽ được thêm vào hàng đợi sau khi được duyệt",
                                fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        MusicTextAction("Chọn bài", onClick = onSearch)
                        MusicTextAction("Trình phát", onClick = onPlayer)
                        MusicTextAction(if (live.refreshing) "Đang làm mới" else "Làm mới", onClick = vm.session::refreshWithFeedback, enabled = !live.refreshing)
                    }
                    if (role?.controls == true) {
                        val controlsBusy = live.pendingActions.any { it in setOf("prev", "next", "play", "pause", "seek") }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            RoomTransport("Bài trước", R.drawable.player_symbol_skip_previous_fill1, vm.session::previous, !controlsBusy)
                            RoomTransport(if(detail.timeline?.playing == true) "Tạm dừng" else "Phát", if (detail.timeline?.playing == true) R.drawable.player_symbol_pause_fill1 else R.drawable.player_symbol_play_arrow_fill1,
                                { vm.session.play(detail.timeline?.playing != true) }, !controlsBusy, primary = true)
                            RoomTransport("Bài tiếp theo", R.drawable.player_symbol_skip_next_fill1, vm.session::next, !controlsBusy)
                        }
                        if (controlsBusy) Text("Đang đồng bộ thao tác…", fontSize = 12.sp, color = MiuixTheme.colorScheme.primary)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            BoxWithConstraints(Modifier.weight(1f)) {
                if (maxWidth >= 700.dp) Row(Modifier.fillMaxSize().testTag("room_two_panes"), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    RoomQueue(detail, role, vm, Modifier.weight(1f)); RoomMembers(detail, role, vm, Modifier.weight(1f), { close = true }, { memberDialog = it })
                } else {
                    var members by rememberSaveable { mutableStateOf(false) }
                    Column {
                        MusicCategoryTabs(listOf("queue" to "Hàng đợi chọn bài ${detail.queue.size}", "members" to "Thành viên và cài đặt ${detail.members.size}"),
                            if (members) "members" else "queue", { members = it == "members" }, "room_tab")
                        if (members) RoomMembers(detail, role, vm, Modifier.weight(1f), { close = true }, { memberDialog = it })
                        else RoomQueue(detail, role, vm, Modifier.weight(1f)) }
                }
            }
        }
        }
    }
    }
    if (create) RoomForm("Tạo phòng", { if (!busy) create = false }, saving = busy, error = browser.error) { name, pw, public, free, approval, locked ->
        vm.create(name, pw.orEmpty(), public, free, approval, locked) }
    deleteRoom?.let { (room, expected) -> MusicDialog("Xóa phòng vĩnh viễn", onDismiss = { deleteRoom = null }) {
        Text("Phòng «${room.name}» (#${room.code}) cùng thành viên, hàng đợi chọn bài, sự kiện và danh sách cấm sẽ bị xóa vĩnh viễn. Thao tác này không thể hoàn tác.")
        MusicDialogButton("Xóa vĩnh viễn", onClick = { deleteRoom = null; vm.deleteOwned(room, expected) }, enabled = !busy,
            modifier = Modifier.testTag("confirm_delete_room"))
    } }
    passwordRoom?.let { room ->
        var password by remember(room.id) { mutableStateOf("") }
        MusicDialog("Tham gia ${room.name}", onDismiss = { vm.passwordRoom.value = null }) {
            TextField(password, { password = it }, label = "Mật khẩu phòng", visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            browser.error?.let { Text(it, fontSize = 13.sp) }
            MusicDialogButton(if (busy) "Đang tham gia…" else "Tham gia", onClick = { vm.join(room, password) }, enabled = !busy)
        }
    }
    if (exit) MusicDialog("Rời phòng nghe cùng nhau", onDismiss = { exit = false }) {
        Text("Sau khi rời phòng, hàng đợi cục bộ sẽ được khôi phục và giữ ở trạng thái tạm dừng.")
        MusicDialogButton("Rời phòng", onClick = { exit = false; vm.leave() }, modifier = Modifier.testTag("confirm_leave_room"))
    }
    if (close) MusicDialog("Lưu trữ phòng", onDismiss = { close = false }) {
        Text("Phát sẽ dừng lại và tất cả thành viên sẽ rời khỏi phòng. Mã phòng, hàng đợi và tiến trình sẽ được giữ lại; bạn có thể khôi phục từ “Phòng của tôi”.")
        MusicDialogButton("Lưu trữ phòng", onClick = { close = false; vm.session.action("close", permission = { it == RoomRole.OWNER }) })
    }
}
@Composable private fun RoomTransport(label: String, icon: Int, onClick: () -> Unit, enabled: Boolean, primary: Boolean = false) {
    Column(Modifier.width(72.dp), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)) {
        GlassIconButton(onClick, label, enabled = enabled, size = if (primary) 48.dp else 44.dp) {
            Icon(painterResource(icon), null, Modifier.size(28.dp),
                tint = (if (primary) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface).copy(alpha = if (enabled) 1f else .35f))
        }
        Text(label, fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
    }
}
@Composable private fun RoomQueue(detail: RoomDetail, role: RoomRole?, vm: RoomViewModel, modifier: Modifier) {
    val live by vm.session.state.collectAsStateWithLifecycle()
    val waiting = detail.queue.filter { it.status == "approved" }
    LazyColumn(modifier, contentPadding = musicScrollPadding(PaddingValues(0.dp)), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(detail.queue, key = { it.id }) { item -> SettingsGroup(Modifier.fillMaxWidth().animateItem()) { Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            val pending = "queue:${item.id}" in live.pendingActions
            Text(item.name, fontWeight = FontWeight.Medium, maxLines = 2, overflow = TextOverflow.Ellipsis)
            val subtitle = listOf(item.artists, item.requester).filter { it.isNotBlank() }.joinToString(" · ")
            if (subtitle.isNotBlank()) Text(subtitle, fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            Text(when(item.status) { "pending" -> "Chờ duyệt"; "playing" -> "Đang phát"; "rejected" -> "Đã từ chối"; else -> "Đã thêm vào hàng đợi" })
            if (pending) Text("Đang gửi…", fontSize = 12.sp, color = MiuixTheme.colorScheme.primary)
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (item.status == "pending" && role?.controls == true) {
                    MusicTextAction("Duyệt", onClick = { vm.session.queueAction(item, "approve") }, enabled = !pending)
                    MusicTextAction("Từ chối", onClick = { vm.session.queueAction(item, "reject") }, enabled = !pending)
                }
                if (RoomPermissions.remove(role, item)) MusicTextAction("Xóa", onClick = { vm.session.queueAction(item, "remove") }, enabled = !pending)
                if (item.status == "approved" && role?.controls == true) {
                    val index = waiting.indexOfFirst { it.id == item.id }
                    val reordering = live.pendingActions.any { it.startsWith("queue:") }
                    if (index > 0) MusicTextAction("Di chuyển lên", { vm.session.reorder(item, index - 1) }, enabled = !reordering)
                    if (index >= 0 && index < waiting.lastIndex) MusicTextAction("Di chuyển xuống", { vm.session.reorder(item, index + 1) }, enabled = !reordering)
                }
            }
        } } }
        if (detail.queue.isEmpty()) item {
            SettingsGroup { SettingsPreference("Chưa có yêu cầu bài hát", "Vào mục tìm kiếm, chọn “Thêm vào hàng đợi” để gửi yêu cầu bài hát.") }
        }
    }
}
@Composable private fun RoomMembers(detail: RoomDetail, role: RoomRole?, vm: RoomViewModel, modifier: Modifier, onClose: () -> Unit,
    onDialogActive: (Boolean) -> Unit) {
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    var target by remember { mutableStateOf<RoomMember?>(null) }
    var settings by remember { mutableStateOf(false) }
    var showBans by remember(detail.room.id) { mutableStateOf(false) }
    val bans by vm.bans.collectAsStateWithLifecycle()
    LaunchedEffect(role) { if (role != RoomRole.OWNER) { settings = false; showBans = false } }
    LaunchedEffect(target != null || settings || showBans) { onDialogActive(target != null || settings || showBans) }
    DisposableEffect(Unit) { onDispose { onDialogActive(false) } }
    LazyColumn(modifier, contentPadding = musicScrollPadding(PaddingValues(0.dp)), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        if (role == RoomRole.OWNER) item {
            SettingsGroup {
                MusicDestinationRow("Cài đặt phòng", { settings = true }, summary = "Tên, mật khẩu, chế độ hiển thị và quyền yêu cầu bài hát")
                SettingsDivider()
                SettingsToggle("Khóa tham gia", detail.room.joinLocked, { locked ->
                    vm.session.action("settings", buildJsonObject { put("joinLocked", locked) }, { it == RoomRole.OWNER }) },
                    summary = "Sau khi bật, phòng sẽ không nhận thêm thành viên mới")
                SettingsDivider()
                MusicDestinationRow("Danh sách chặn", { showBans = true; vm.loadBans() }, summary = "Xem và gỡ lệnh cấm tham gia")
                SettingsDivider()
                SettingsPreference("Lưu trữ phòng", "Giữ lại mã phòng, hàng đợi và tiến trình để khôi phục sau", onClick = onClose,
                    trailing = { Icon(io.github.currencortex.music.ui.component.MusicIcons.Close, null, tint = MiuixTheme.colorScheme.error) })
            }
        }
        item { SettingsSectionHeader("Thành viên · ${detail.members.size}") }
        items(detail.members, key = { it.userId }) { member ->
            SettingsGroup(Modifier.animateItem()) {
                SettingsPreference(member.nickname, when (RoomRole.parse(member.role)) {
                    RoomRole.OWNER -> "Chủ phòng"; RoomRole.ADMIN -> "Quản trị viên"; else -> "Thành viên"
                }, leading = { Icon(io.github.currencortex.music.ui.component.MusicIcons.Account, null) }, trailing = {
                    if (member.userId != account.account?.id && RoomPermissions.kick(role, RoomRole.parse(member.role)))
                        MusicTextAction("Quản lý", { target = member })
                })
            }
        }
    }
    target?.let { member -> MusicChoiceDialog("Quản lý ${member.nickname}", onDismiss = { target = null }) {
        if (role == RoomRole.OWNER) {
            MusicTextAction(if (RoomRole.parse(member.role) == RoomRole.ADMIN) "Hủy quyền quản trị viên" else "Đặt làm quản trị viên", onClick = {
                vm.session.action("admins", buildJsonObject { put("userId", member.userId); put("on", RoomRole.parse(member.role) != RoomRole.ADMIN) }, { it == RoomRole.OWNER }); target = null })
            var transfer by remember { mutableStateOf(false) }
            if (!transfer) MusicTextAction("Chuyển quyền chủ phòng", onClick = { transfer = true })
            else MusicTextAction("Xác nhận chuyển quyền chủ phòng", onClick = { vm.session.action("transfer", buildJsonObject { put("userId", member.userId) }, { it == RoomRole.OWNER }); target = null })
        }
        MusicTextAction("Mời khỏi phòng", onClick = { vm.session.action("kick", buildJsonObject { put("userId", member.userId); put("ban", false) }, { RoomPermissions.kick(it, RoomRole.parse(member.role)) }); target = null })
        MusicTextAction("Mời khỏi phòng và cấm tham gia", onClick = { vm.session.action("kick", buildJsonObject { put("userId", member.userId); put("ban", true) }, { RoomPermissions.kick(it, RoomRole.parse(member.role)) }); target = null })
    } }
    if (showBans) MusicDialog("Danh sách bị cấm", onDismiss = { showBans = false }) {
        Column(Modifier.heightIn(max = 360.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (bans.loading) Text("Đang tải…")
            bans.error?.let { Text(it); MusicTextAction("Thử lại", vm::loadBans) }
            if (!bans.loading && bans.error == null && bans.bans.isEmpty()) Text("Chưa có thành viên nào bị cấm")
            bans.bans.forEach { ban ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(ban.nickname.ifBlank { "Người dùng ${ban.userId}" })
                        if (ban.reason.isNotBlank()) Text(ban.reason, fontSize = 12.sp)
                    }
                    MusicTextAction("Gỡ cấm", { vm.unban(ban.userId) }, enabled = !bans.loading)
                }
            }
        }
    }
    if (settings) RoomForm("Cài đặt phòng", { settings = false }, detail.room) { name, password, public, free, approval, locked ->
        vm.session.action("settings", buildJsonObject { put("name", name); put("isPublic", public); put("freeMode", free); put("needApproval", approval); put("joinLocked", locked)
            if (password != null) put("password", password) }, { it == RoomRole.OWNER }); settings = false
    }
}
@Composable private fun RoomForm(title: String, onDismiss: () -> Unit, initial: RoomInfo? = null,
    saving: Boolean = false, error: String? = null,
    onSave: (String, String?, Boolean, Boolean, Boolean, Boolean) -> Unit) {
    var name by remember { mutableStateOf(initial?.name ?: "") }; var password by remember { mutableStateOf("") }
    var public by remember { mutableStateOf(initial?.isPublic ?: true) }; var free by remember { mutableStateOf(initial?.freeMode ?: false) }
    var approval by remember { mutableStateOf(initial?.needApproval ?: true) }
    var locked by remember { mutableStateOf(initial?.joinLocked ?: false) }
    var changePassword by remember { mutableStateOf(initial == null) }
    MusicDialog(title, onDismiss = onDismiss) {
        Column(Modifier.heightIn(max = 360.dp).verticalScroll(rememberScrollState()).testTag("room_form"),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            TextField(name, { if (it.length <= 60) name = it }, label = "Tên phòng", modifier = Modifier.fillMaxWidth())
            if (initial != null) SettingsToggle("Đổi mật khẩu", changePassword, { changePassword = it }, summary = "Để trống rồi lưu để gỡ mật khẩu")
            if (changePassword) TextField(password, { if (it.length <= 128) password = it }, label = "Mật khẩu phòng", visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            SettingsGroup {
                SettingsToggle("Phòng công khai", public, { public = it }, summary = "Hiển thị trong sảnh phòng")
                SettingsDivider()
                SettingsToggle("Tự do yêu cầu bài hát", free, { free = it }, summary = "Khi bật, yêu cầu bài hát của thành viên thường sẽ được thêm trực tiếp vào hàng đợi")
                SettingsDivider()
                SettingsToggle("Yêu cầu bài hát cần phê duyệt", approval, { approval = it }, summary = if (free) "Tạm thời không phê duyệt khi bật chế độ tự do yêu cầu bài hát" else "Khi tắt, yêu cầu bài hát của thành viên thường sẽ được thêm trực tiếp vào hàng đợi")
                SettingsDivider()
                SettingsToggle("Khóa tham gia", locked, { locked = it }, summary = "Khi bật, phòng sẽ không nhận thêm thành viên mới")
            }
            error?.let { Text(it, fontSize = 13.sp) }
            MusicDialogButton(if (saving) "Đang tạo…" else "Lưu", enabled = name.trim().length in 1..60 && !saving,
                onClick = { onSave(name.trim(), if (changePassword) password else null, public, free, approval, locked) })
        }
    }
}
