package io.github.currencortex.music.feature.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import io.github.currencortex.music.ui.util.collectAsPageState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.data.profile.*
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.auth.AuthViewModel
import io.github.currencortex.music.feature.auth.LoginScreen
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlinx.coroutines.launch

@Composable fun UserAvatar(user: ProfileUser, container: AppContainer, size: androidx.compose.ui.unit.Dp = 64.dp,
    decoration: String = user.decoration, reserveOverlay: Boolean = true, flightState: AvatarFlightState? = null) {
    val preferences by container.musicSettings.state.collectAsStateWithLifecycle()
    val scales by container.profileRepository.scales.collectAsStateWithLifecycle()
    val versions by container.profileRepository.avatarVersions.collectAsStateWithLifecycle()
    val repository = container.profileRepository
    val avatarUrl = repository.avatarUrl(preferences.server, user.avatar, versions)
    val decorationUrl = repository.decorationUrl(preferences.server, decoration)
    val scale = scales[decoration] ?: 1.0
    val density = LocalDensity.current
    val placement = LocalRootTabPlacement.current
    var coordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
    val originProvider by rememberUpdatedState<() -> AvatarFlightOrigin?>({
        coordinates?.takeIf { it.isAttached }?.boundsInRoot()?.takeIf { it.top >= 0f }?.let {
            val radius = with(density) { size.toPx() / 2f }
            val bounds = Rect(it.center - Offset(radius, radius), it.center + Offset(radius, radius))
            if (placement?.visible(bounds) == false) null else AvatarFlightOrigin(avatarUrl, bounds, decorationUrl, scale)
        }
    })
    DisposableEffect(flightState) {
        val provider = { originProvider() }
        flightState?.profileOrigin = provider
        onDispose { if (flightState != null && flightState.profileOrigin === provider) flightState.profileOrigin = null }
    }
    DecoratedAvatar(avatarUrl, decorationUrl, modifier = if (flightState != null)
        Modifier.onGloballyPositioned { coordinates = it } else Modifier,
        scale = scale, size = size, reserveOverlay = reserveOverlay, onImageError = {
            // Report the failure category only; image URLs can contain signed credentials.
            container.logger.warn("Image", "User image failed: ${it.javaClass.simpleName}", null)
        })
}

@Composable fun MeScreen(vm: ProfileViewModel, auth: AuthViewModel, navigate: (String) -> Unit, onSettings: () -> Unit,
    play: (List<Song>, Int) -> Unit) {
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
// Thanh điều hướng dưới đã có mục “Cài đặt”; mục thứ hai phía trên biểu mẫu đăng nhập là dư thừa.
    if (account.account == null && vm.container.nativeNetease != null) NativeMeScreen(vm.container, navigate, onSettings)
    else if (account.account == null) ProfilePageLayout(navigate, onSettings) { topInset ->
        LoginScreen(auth, showTitle = false, contentTopInset = topInset)
    } else ProfileScreen(vm, navigate, play, onSettings = onSettings)
}

@Composable private fun NativeMeScreen(container: AppContainer, navigate: (String) -> Unit, onSettings: () -> Unit) {
    val tasksEnabled by container.neteaseTasksEnabled.collectAsStateWithLifecycle()
    val navidromeAccount by container.navidrome.state.collectAsStateWithLifecycle()
    val embyAccount by container.emby.state.collectAsStateWithLifecycle()
    val account by container.neteaseSessions.state.collectAsStateWithLifecycle()
    val bottomInset = LocalMusicBottomInset.current
    val avatarFlight = LocalAvatarFlight.current
    val placement = LocalRootTabPlacement.current
    val density = LocalDensity.current
    val listState = rememberLazyListState()
    var avatarPreview by rememberSaveable(account.profile?.uid) { mutableStateOf(false) }
    LaunchedEffect(avatarFlight?.request) { if (avatarFlight?.active == true && !avatarFlight.returning) listState.scrollToItem(0) }
    LaunchedEffect(listState, avatarFlight) {
        snapshotFlow { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0 }
            .collect { if (!it) avatarFlight?.profileLanding = null }
    }
    ProfilePageLayout(navigate, onSettings) { topInset ->
    LazyColumn(Modifier.fillMaxSize().testTag("profile_screen"), state = listState,
        contentPadding = PaddingValues(start = 16.dp, top = topInset + 24.dp, end = 16.dp, bottom = 32.dp + bottomInset),
        verticalArrangement = Arrangement.spacedBy(0.dp)) {
        rootMotionItems {
        account.profile?.let { native -> item("identity") { SettingsGroup {
            Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(Modifier.testTag("my_profile_avatar").clickable(role = Role.Button) { avatarPreview = true }
                     .semantics { contentDescription = "Xem ảnh đại diện" }.onGloballyPositioned {
                    val center = (placement?.landingBounds(it) ?: it.boundsInRoot()).center
                    val radius = with(density) { 32.dp.toPx() }
                    val landing = Rect(center - Offset(radius, radius), center + Offset(radius, radius))
                    if (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) avatarFlight?.profileLanding = landing
                    if (avatarFlight?.returning != true) avatarFlight?.destination = landing
                }.graphicsLayer { alpha = if (avatarFlight?.active == true) 0f else 1f }) {
                    UserAvatar(ProfileUser(id = native.uid, nickname = native.nickname, avatar = native.avatar), container,
                        reserveOverlay = false, flightState = avatarFlight)
                }
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(native.nickname, fontSize = 23.sp, fontWeight = FontWeight.SemiBold,
                        maxLines = 2, overflow = TextOverflow.Ellipsis)
                     Text(if (account.stale) "Đăng nhập Nhạc Vân NetEase đã hết hạn" else "Nhạc Vân NetEase", fontSize = 13.sp,
                        color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f))
                }
            }
        } } }
        if (account.profile == null) item("identity") { SettingsGroup {
             ProfileDestination("Đăng nhập CurrentMusic", "Đăng nhập để đồng bộ hồ sơ và nhạc yêu thích", MusicIcons.Account,
                "open_currentmusic_login") { navigate("user/login") }
        } }
        item("library") { ProfileLibrarySection(navigate) }
        item("services") { ProfileServicesSection(navigate, tasksEnabled == true, native = true, bound = account.loggedIn,
            navidromeConfigured = navidromeAccount.configured, embyConfigured = embyAccount.configured) }
        if (account.profile != null) item("account") { ProfileAccountSection(navigate, native = true) }
    }
    }
    }
    if (avatarPreview) account.profile?.let { native ->
        AvatarPreviewDialog(ProfileUser(id = native.uid, nickname = native.nickname, avatar = native.avatar),
            container, onDismiss = { avatarPreview = false })
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable fun ProfileScreen(vm: ProfileViewModel, navigate: (String) -> Unit, play: (List<Song>, Int) -> Unit,
    onBack: (() -> Unit)? = null, onSettings: (() -> Unit)? = null) {
    val state = vm.state.collectAsPageState().value
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    val tasksEnabled by vm.container.neteaseTasksEnabled.collectAsStateWithLifecycle()
    val navidromeAccount by vm.container.navidrome.state.collectAsStateWithLifecycle()
    val embyAccount by vm.container.emby.state.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
    var avatarPreview by rememberSaveable(vm.container.accountRepository.server, account.account?.id) { mutableStateOf(false) }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) { avatarPreview = false; vm.avatar.value = uri; vm.dialog.value = "avatar" }
    }
    val user = state.profile?.user ?: account.account?.takeIf { onBack == null }?.let {
        ProfileUser(id = it.id, username = it.username, nickname = it.nickname, avatar = it.avatar,
            isAdmin = it.isAdmin, isSuper = it.isSuper, title = it.title, level = it.level, points = it.points, isOwner = it.isOwner)
    }
    val own = user != null && user.id == account.account?.id
    val bottomInset = LocalMusicBottomInset.current
    val listState = rememberLazyListState()
    val avatarFlight = LocalAvatarFlight.current.takeIf { onBack == null }
    val placement = LocalRootTabPlacement.current
    val density = LocalDensity.current
    val owner = LocalLifecycleOwner.current
    LaunchedEffect(vm, owner) { owner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) { vm.pollStats() } }
    LaunchedEffect(avatarFlight?.request) {
        if (avatarFlight?.active == true && !avatarFlight.returning) listState.scrollToItem(0)
    }
    LaunchedEffect(listState, avatarFlight) {
        snapshotFlow { listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0 }
            .collect { if (!it) avatarFlight?.profileLanding = null }
    }
    ProfilePageLayout(navigate, onSettings, public = onBack != null, loading = state.loading, reload = vm::reload, onBack = onBack) { topInset ->
    MusicPullToRefresh(state.loading, vm::reload, Modifier.fillMaxSize(), enabled = onBack != null) {
    LazyColumn(Modifier.fillMaxSize().testTag("profile_screen"), state = listState, contentPadding = PaddingValues(start = 16.dp, top = topInset + 24.dp, end = 16.dp, bottom = 32.dp + bottomInset),
        verticalArrangement = Arrangement.spacedBy(0.dp)) {
        rootMotionItems {
        if ((state.loading || account.loading) && user == null) item { LoadingProfileCard() }
        state.error?.let { message -> item("error") { SettingsGroup {
            SettingsHint(message, error = true)
             MusicTextAction("Thử lại", vm::reload, Modifier.padding(start = 16.dp, bottom = 12.dp))
        } } }
        if (user != null) {
            item("identity") { SettingsGroup(Modifier.testTag("profile_identity")) {
                Row(Modifier.fillMaxWidth().padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(Modifier.testTag("my_profile_avatar").clickable(role = Role.Button) { avatarPreview = true }
                         .semantics { contentDescription = "Xem ảnh đại diện" }.onGloballyPositioned {
                        val center = (placement?.landingBounds(it) ?: it.boundsInRoot()).center
                        val radius = with(density) { 32.dp.toPx() }
                        val landing = Rect(center - Offset(radius, radius), center + Offset(radius, radius))
                        if (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) avatarFlight?.profileLanding = landing
                        if (avatarFlight?.returning != true && (avatarFlight?.active != true || (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0)))
                            avatarFlight?.destination = landing
                    }.graphicsLayer { alpha = if (avatarFlight?.active == true) 0f else 1f }) {
                        UserAvatar(user, vm.container, 64.dp, reserveOverlay = false, flightState = avatarFlight)
                    }
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(user.nickname.ifBlank { user.username }, fontSize = 22.sp, lineHeight = 28.sp,
                            fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        FlowRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            UserRoleBadge(user.role)
                            UserLevelBadge(user.level)
                            UserTitleBadge(user.title)
                        }
                        UserIdentityDetails("", user.ipRegion)
                    }
                }
                 Text(if (state.loading && state.profile == null) "Đang tải hồ sơ…" else user.bio.ifBlank { "Chưa có phần giới thiệu" },
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 12.dp).then(if (own) Modifier.testTag("edit_profile")
                                .clickable(enabled = !busy && state.profile != null, role = Role.Button) { vm.dialog.value = "edit" }
                                 .semantics { contentDescription = "Chỉnh sửa hồ sơ" } else Modifier),
                            fontSize = 13.sp, lineHeight = 19.sp, maxLines = 2, overflow = TextOverflow.Ellipsis,
                            color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                state.profile?.stat?.let { ProfileStatsBlock(it, if (own) ({ navigate("user/listening") }) else null) }
            } }
            if (own) {
                item("library") { ProfileLibrarySection(navigate) }
                item("services") { ProfileServicesSection(navigate, tasksEnabled == true,
                    navidromeConfigured = navidromeAccount.configured, embyConfigured = embyAccount.configured) }
                item("account") { ProfileAccountSection(navigate, administrator = !account.loading && !account.offline &&
                    account.account?.let { it.isAdmin || it.isSuper || it.isOwner } == true) }
            }
             state.profile?.current?.let { song -> item { SettingsListHeader("Đang nghe")
                SongRow(SongRowUi(song.toDomain()), { play(listOf(song.toDomain()), 0) },
                    { vm.container.playerController.add(song.toDomain(), true) }, { vm.container.playerController.add(song.toDomain()) })
            } }
            val recent = state.profile?.recent.orEmpty().map { it.toDomain() }
             if (recent.isNotEmpty()) item { SettingsListHeader("Nghe gần đây") }
            itemsIndexed(recent, key = { _, song -> "recent-${song.id}" }) { index, song ->
                SongRow(SongRowUi(song), { play(recent, index) }, { vm.container.playerController.add(song, true) }, { vm.container.playerController.add(song) })
            }
            val lists = state.profile?.playlists.orEmpty()
             if (lists.isNotEmpty()) item { SettingsListHeader(if (own) "Danh sách phát của tôi" else "Danh sách phát công khai") }
            items(lists, key = { "playlist-${it.source}-${it.id}" }) { list ->
                SettingsGroup(Modifier.padding(bottom = 12.dp)) {
                    SettingsUiScope {
                         SettingsPreference(list.name, "${list.count} bài" + if (list.source in setOf("ncm", "netease")) " · Nhạc Vân NetEase" else "",
                            Modifier.testTag("profile_playlist_${list.id}"),
                            onClick = { navigate("lib/${if (list.source == "netease") "ncmplaylist" else "playlist"}/${list.id}") },
                            leading = { MusicCover(list.cover, Modifier.size(48.dp)) },
                            trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp)) })
                    }
                }
            }
        }
        state.sectionErrors.forEach { item { SettingsHint(it, error = true) } }
    }
    }
    }
    }
    if (avatarPreview && user != null) AvatarPreviewDialog(user, vm.container,
        onDismiss = { avatarPreview = false },
        onChange = if (own && !busy) ({ picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }) else null,
        onDecorations = if (own) ({ avatarPreview = false; navigate("user/decorations") }) else null)
}

/** Accounts and security are one page: switching accounts and changing credentials are the same job. */
@Composable fun AccountSecurityScreen(vm: ProfileViewModel, auth: AuthViewModel, add: () -> Unit, onBack: (() -> Unit)? = null) {
    val container = vm.container
    val accounts by container.accountRepository.savedAccounts.collectAsStateWithLifecycle()
    val account by container.accountRepository.state.collectAsStateWithLifecycle()
    val state by auth.state.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val colors = MiuixTheme.colorScheme
    var removing by remember { mutableStateOf<io.github.currencortex.music.data.auth.SavedAccount?>(null) }
    var protection by remember { mutableStateOf<String?>(null) }
     MusicSubpage("Tài khoản và bảo mật", "security_page", onBack, actions = {
         MusicTextAction("Thêm tài khoản", add, Modifier.testTag("add_account"), enabled = !state.loading)
    }) { topInset ->
    LazyColumn(Modifier.fillMaxSize().testTag("security_screen"),
        contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
         item { SettingsListHeader("Tài khoản đã lưu") }
        item { SettingsGroup {
            accounts.forEachIndexed { index, saved ->
                if (index > 0) SettingsDivider(68.dp)
                val active = saved.id == account.account?.id && saved.server == container.accountRepository.server
                SettingsPreference(saved.nickname.ifBlank { saved.username }, saved.server,
                    leading = { UserAvatar(ProfileUser(id = saved.id, avatar = saved.avatar), container, 40.dp, reserveOverlay = false) },
                    trailing = {
                        if (active) SettingsSelectionCheck(true, Modifier.testTag("current_account_${saved.id}"))
                    }, footer = if (active) null else ({
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                             MusicTextAction("Chuyển đổi", { auth.switch(saved.key) },
                                Modifier.testTag("switch_account_${saved.id}"), enabled = !state.loading)
                             MusicTextAction("Xóa", { removing = saved },
                                Modifier.testTag("remove_account_${saved.id}"), enabled = !state.loading, destructive = true)
                        }
                    }))
            }
             if (accounts.isEmpty()) SettingsHint("Thiết bị này chưa lưu tài khoản nào")
        } }
         item { SettingsListHeader("Bảo mật") }
        item { SettingsGroup {
             MusicDestinationRow("Câu hỏi bảo mật đăng nhập", { protection = "question" }, Modifier.testTag("open_security_question"),
                 "Thiết lập hoặc tắt câu hỏi bảo mật đăng nhập", enabled = !busy && account.account != null)
            SettingsDivider()
             MusicDestinationRow("Ứng dụng xác thực", { protection = "totp" }, Modifier.testTag("open_authenticator"),
                 "Liên kết, tắt và xem số mã khôi phục còn lại", enabled = !busy && account.account != null)
            SettingsDivider()
             MusicDestinationRow("Đổi mật khẩu", { vm.dialog.value = "password" }, Modifier.testTag("open_password"),
                "Mật khẩu đăng nhập phải có ít nhất 6 ký tự", enabled = !busy)
            SettingsDivider()
            MusicDestinationRow("Đăng xuất", { vm.dialog.value = "logout" }, Modifier.testTag("open_logout"),
                "Chỉ xóa thông tin đăng nhập trên thiết bị này, các tài khoản đã lưu khác vẫn được giữ lại", enabled = !busy)
        } }
        state.message?.let { item { SettingsHint(it) } }
        if (state.loading) item { SettingsHint("Đang xác minh tài khoản…") }
    }
    }
    SettingsUiScope {
    protection?.let { kind -> io.github.currencortex.music.feature.auth.LoginProtectionDialog(container,
        questionMode = kind == "question", onDismiss = { protection = null }) }
    removing?.let { saved -> MusicDialog("Xóa tài khoản đã lưu?", { removing = null }) {
        Text(saved.nickname.ifBlank { saved.username }); Text("Chỉ xóa thông tin đăng nhập trên thiết bị này, tài khoản vẫn không bị ảnh hưởng.")
        MusicDialogButton("Xác nhận xóa", onClick = { removing = null; auth.removeSaved(saved.key) },
            modifier = Modifier.testTag("confirm_remove_account"))
    } }
    }
}

@Composable fun ProfileDialogs(vm: ProfileViewModel, auth: AuthViewModel) {
    val dialog by vm.dialog.collectAsStateWithLifecycle()
    val state by vm.state.collectAsPageState()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val avatar by vm.avatar.collectAsStateWithLifecycle()
    val context = LocalContext.current
    fun close() { if (!busy) { vm.dialog.value = null; vm.avatar.value = null } }
    SettingsUiScope {
    when (dialog) {
        "edit" -> state.profile?.user?.let { user ->
            var nickname by rememberSaveable(user.id) { mutableStateOf(user.nickname) }
            var bio by rememberSaveable(user.id) { mutableStateOf(user.bio) }
            var visible by rememberSaveable(user.id) { mutableStateOf(user.visible) }
            MusicDialog("Chỉnh sửa hồ sơ", ::close) {
                Text("Biệt danh"); TextField(nickname, { nickname = it }, singleLine = true, modifier = Modifier.fillMaxWidth().then(Modifier.testTag("profile_nickname")))
                Text("Giới thiệu"); TextField(bio, { bio = it }, modifier = Modifier.fillMaxWidth().then(Modifier.testTag("profile_bio")))
                visible?.let { current -> SettingsSwitch("Công khai hồ sơ trên Quảng trường người dùng", current, { visible = it }) }
                MusicDialogButton("Lưu", onClick = { vm.update(nickname, bio, visible) }, enabled = nickname.isNotBlank() && !busy,
                    modifier = Modifier.testTag("save_profile"))
            }
        }
        "avatar" -> avatar?.let { uri -> AvatarCropDialog(uri, busy, ::close) { crop ->
            vm.upload(context.contentResolver, crop)
        } }
        "password" -> {
            val deadline by vm.passwordCodeUntil.collectAsStateWithLifecycle()
            io.github.currencortex.music.feature.auth.PasswordDialog(false, busy, deadline, ::close,
                onSendCode = { vm.passwordCode() }, onSubmit = { _, credential, new, usingCode ->
                    if (usingCode) vm.passwordWithEmailCode(credential, new) else vm.password(credential, new)
                })
        }
        "logout" -> MusicDialog("Đăng xuất khỏi tài khoản hiện tại?", ::close) {
            Text("Thông tin đăng nhập của tài khoản hiện tại được lưu trên thiết bị này sẽ bị xóa; các tài khoản đã lưu khác vẫn được giữ lại.")
            MusicDialogButton("Xác nhận đăng xuất", onClick = { vm.container.playerController.pause(); vm.dialog.value = null; auth.logout() })
        }
    }
}
}
