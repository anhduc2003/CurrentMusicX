package io.github.currencortex.music.feature.room

import io.github.currencortex.music.ui.component.nativeui.*

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import io.github.currencortex.music.core.netease.listen.ListenTogetherOperation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.media.ListenTogetherPair
import io.github.currencortex.music.core.media.ListenTogetherParticipant
import io.github.currencortex.music.core.media.PlayerState
import io.github.currencortex.music.core.netease.NeteaseAccountState
import io.github.currencortex.music.core.netease.listen.NeteaseListenTogetherState
import io.github.currencortex.music.data.netease.listen.ListenTogetherInvitation
import io.github.currencortex.music.feature.player.NeteaseSongsCollectionDialog
import io.github.currencortex.music.ui.util.showMusicError
import io.github.currencortex.music.ui.component.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.ensureActive
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

private data class ListenPageSnapshot(val archive: ListenTogetherArchiveState, val fate: ListenTogetherFateState) {
    val key: String get() = when {
        fate.partnerUid != null -> "fate:${fate.partnerUid}"
        archive.page != null -> "${archive.page}:${archive.peerFilter}:${archive.reportRoomId}"
        else -> "session"
    }
    val depth: Int get() = when {
        fate.partnerUid != null -> 4
        archive.page == ListenArchivePage.REPORT -> 3
        archive.page == ListenArchivePage.RANKING || archive.peerFilter != null -> 2
        archive.page != null -> 1
        else -> 0
    }
}

/** Actions are explicit, so opening this page cannot send an invitation to another person. */
internal data class NeteaseListenTogetherActions(
    val create: () -> Unit, val join: (String) -> Unit, val resume: () -> Unit,
    val refresh: () -> Unit, val leave: () -> Unit, val dismissError: () -> Unit,
    val copyInvitation: suspend (String) -> Unit, val pasteInvitation: suspend () -> String?)

@Composable internal fun NeteaseListenTogetherScreen(container: AppContainer, onBack: () -> Unit,
    onSearch: () -> Unit, onPlayer: () -> Unit, onBinding: (() -> Unit)? = null,
    onDialogActive: (Boolean) -> Unit = {}, initialInvitation: String? = null) {
    val controller = container.listenTogether
    val state by controller.state.collectAsStateWithLifecycle()
    val account by container.neteaseSessions.state.collectAsStateWithLifecycle()
    val playback by container.playerController.state.collectAsStateWithLifecycle()
    val cmRoom by container.roomSession.state.collectAsStateWithLifecycle()
    val clipboard = LocalClipboard.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val archiveController = remember(container, account.revision) {
        ListenTogetherHistoryController(container.listenTogetherHistory, scope, container.musicRepository::details)
    }
    val archive by archiveController.state.collectAsStateWithLifecycle()
    val fateController = remember(container, account.revision) {
        ListenTogetherFateController(container.listenTogetherFate, scope) { container.errorNotices.show(it) }
    }
    val fate by fateController.state.collectAsStateWithLifecycle()
    var deleteFate by remember(account.revision) { mutableStateOf<io.github.currencortex.music.data.netease.listen.ListenTogetherFateReport?>(null) }
    var invitePeer by remember(account.revision) { mutableStateOf<io.github.currencortex.music.data.netease.listen.ListenTogetherParticipant?>(null) }
    var shareJob by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }
    var fateShareJob by remember { mutableStateOf<kotlinx.coroutines.Job?>(null) }
    var fateSharing by remember { mutableStateOf(false) }
    var reportSharing by remember { mutableStateOf(false) }
    var mainDialogActive by remember { mutableStateOf(false) }
    val page = ListenPageSnapshot(archive, fate)
    val pageActive = LocalMusicPageActive.current
    BackHandler(enabled = pageActive) {
        when {
            fate.partnerUid != null -> if (!fate.deleting) fateController.close()
            archive.page != null -> archiveController.back()
            else -> onBack()
        }
    }
    val collectionShown = !archive.collectionSongs.isNullOrEmpty()
    LaunchedEffect(page.key, mainDialogActive, deleteFate, invitePeer, collectionShown) {
        onDialogActive(deleteFate != null || invitePeer != null || collectionShown || (page.key == "session" && mainDialogActive))
    }
    DisposableEffect(Unit) { onDispose { onDialogActive(false) } }
    DisposableEffect(archiveController) { onDispose { archiveController.close(); shareJob?.cancel() } }
    DisposableEffect(fateController) { onDispose { fateController.close(); fateShareJob?.cancel() } }
    LaunchedEffect(fate.partnerUid) { if (fate.partnerUid == null) { fateShareJob?.cancel(); fateSharing = false; deleteFate = null } }
    LaunchedEffect(fate.deleted) { if (fate.deleted) { fateController.close(); archiveController.history() } }
    LaunchedEffect(archive.page, archive.reportRoomId, fate.partnerUid) { shareJob?.cancel() }
    LaunchedEffect(state.endedRoom?.roomId, state.endedOwnerUid, account.revision) {
        state.endedRoom?.takeIf { state.endedOwnerUid == account.profile?.uid }?.let { ended ->
            // Leaving another room to accept a link should keep the invitation form visible.
            if (initialInvitation == null || ListenTogetherInvitation.parse(initialInvitation)?.roomId == ended.roomId)
                archiveController.report(ended.roomId, ended)
            controller.dismissEndedRoom()
        }
    }
    LaunchedEffect(controller, account.revision) {
        if (account.loggedIn && !account.stale && (account.profile?.uid ?: 0) > 0) controller.refresh()
    }
    key(account.revision) {
        ListenTogetherPageTransition(page, { it.key }, { it.depth }, Modifier.fillMaxSize().testTag("listen_page_transition")) { snapshot ->
            val archive = snapshot.archive
            val fate = snapshot.fate
            val visible = pageActive && snapshot.key == page.key
            if (fate.partnerUid != null) {
                ListenTogetherFateContent(fate.report, fate.loading, fate.permissionRequired,
                    onBack = { if (!fate.deleting) fateController.close() }, onRetry = fateController::retry,
                    onHistory = { uid -> fateController.close(); archiveController.history(uid) },
                    onShare = { report -> if (fateShareJob?.isActive != true) {
                        fateSharing = true
                        fateShareJob = scope.launch {
                            try {
                                val expected = fateController.shareSession(report)
                                container.listenTogetherFate.checkSession(expected)
                                val file = ListenTogetherFateSharing.create(context, report)
                                try {
                                    container.listenTogetherFate.checkSession(expected)
                                    if (fateController.state.value.report === report) {
                                        context.startActivity(Intent.createChooser(ListenTogetherFateSharing.intent(context, report, file), "Chia sẻ báo cáo duyên phận"))
                                    } else file.delete()
                                } catch (failure: Exception) { file.delete(); throw failure }
                            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
                            catch (_: Exception) { context.showMusicError("Tạm thời không thể chia sẻ báo cáo duyên phận, vui lòng thử lại") }
                            finally { fateSharing = false }
                        }
                    } },
                    onDeleteRequest = { deleteFate = fate.report },
                    onOfficial = { report -> try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(report.officialUrl()))) }
                        catch (_: Exception) { context.showMusicError("Không thể mở báo cáo duyên phận Nhạc Vân NetEase") } },
                    onInvite = { report -> if (report.canInvite && !report.peer.masked && !state.active) {
                        fateController.close(); invitePeer = report.peer
                    } }, activeRoom = state.active,
                    busy = fate.deleting, sharing = fateSharing, error = fate.error != null)
                deleteFate?.takeIf { visible }?.let { report -> ListenTogetherFateDeleteDialog(report,
                    onDismiss = { deleteFate = null }, onConfirm = { records ->
                        deleteFate = null
                        fateController.deleteConfirmed(report, records)
                    }) }
            } else if (archive.page != null) {
                val collectionShown = visible && !archive.collectionSongs.isNullOrEmpty()
                val back = { archiveController.back() }
                ListenTogetherHistoryContent(archive, onBack = back, onHistory = archiveController::history,
                    onRanking = archiveController::ranking, onReport = { roomId ->
                        val knownPeer = archive.history?.records?.firstOrNull { it.roomId == roomId }?.user
                        val knownRoom = io.github.currencortex.music.data.netease.listen.ListenTogetherRoom(roomId,
                            archive.self?.userId ?: 0, listOfNotNull(archive.self, knownPeer))
                        archiveController.report(roomId, knownRoom)
                    },
                    onMore = archiveController::more, onRetry = archiveController::retry,
                    onInvite = { if (!state.active) invitePeer = it },
                    onShare = { report -> if (shareJob?.isActive != true) {
                        reportSharing = true
                        shareJob = scope.launch {
                            try {
                                val file = ListenTogetherReportSharing.create(context, report)
                                try {
                                    kotlinx.coroutines.currentCoroutineContext().ensureActive()
                                    if (archiveController.state.value.report === report && fateController.state.value.partnerUid == null)
                                        context.startActivity(Intent.createChooser(ListenTogetherReportSharing.intent(context, report, file), "Chia sẻ báo cáo nghe cùng nhau"))
                                    else file.delete()
                                } catch (failure: Exception) { file.delete(); throw failure }
                            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
                            catch (_: Exception) { context.showMusicError("Tạm thời không thể chia sẻ báo cáo, vui lòng thử lại") }
                            finally { reportSharing = false }
                        }
                    } },
                    onCollect = archiveController::collectSongs,
                    onOfficial = { report -> try { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(report.officialUrl()))) }
                        catch (_: Exception) { context.showMusicError("Không thể mở báo cáo chính thức") } },
                    activeRoom = state.active, sharing = reportSharing, onFate = { peer, scene -> fateController.open(peer.userId, scene) })
                NeteaseSongsCollectionDialog(archive.collectionSongs.orEmpty(), collectionShown,
                    archiveController::dismissCollection,
                    loadPlaylists = { container.neteaseLibrary.playlists(fresh = true).filter { it.nativeOwned } },
                    collect = { id, songs -> container.neteaseLibrary.addAll(id, songs); Unit })
                invitePeer?.takeIf { visible }?.let { peer -> MusicDialog("Nghe cùng nhau lần nữa", onDismiss = { invitePeer = null }) {
                    Text("Sau khi tạo phiên nghe đôi mới, bạn có thể sao chép liên kết mời và gửi cho ${peer.nickname.ifBlank { "bạn bè" }}.")
                    MusicDialogActions {
                        MusicDialogButton("Hủy", { invitePeer = null }, Modifier.weight(1f))
                        MusicDialogButton("Tạo phiên nghe cùng nhau", { invitePeer = null; archiveController.close(); controller.create() },
                            Modifier.weight(1f), enabled = !state.active && !state.busy)
                    }
                } }
            } else {
                NeteaseListenTogetherContent(state, account, playback,
                    actions = NeteaseListenTogetherActions(controller::create, controller::join, controller::resume,
                        controller::refresh, controller::leave, controller::dismissError,
                        copyInvitation = { url -> clipboard.setClipEntry(ClipData.newPlainText("Lời mời nghe cùng nhau Nhạc Vân NetEase", url))) },
                        pasteInvitation = { clipboard.getClipEntry()?.clipData?.let { clip ->
                            if (clip.itemCount > 0) clip.getItemAt(0).coerceToText(context)?.toString() else null
                        } }),
                    onBack = onBack, onSearch = onSearch, onPlayer = onPlayer, onBinding = onBinding,
                    currentMusicRoomActive = cmRoom.detail != null, onDialogActive = { mainDialogActive = it },
                    onHistory = { archiveController.history() }, initialInvitation = initialInvitation)
            }
        }
    }
}

@Composable internal fun NeteaseListenTogetherContent(state: NeteaseListenTogetherState,
    account: NeteaseAccountState, playback: PlayerState, actions: NeteaseListenTogetherActions,
    onBack: () -> Unit, onSearch: () -> Unit, onPlayer: () -> Unit,
    onBinding: (() -> Unit)? = null, currentMusicRoomActive: Boolean = false,
    onDialogActive: (Boolean) -> Unit = {}, onHistory: (() -> Unit)? = null, initialInvitation: String? = null) {
    val colors = MiuixTheme.colorScheme
    val ready = account.loggedIn && !account.stale && (account.profile?.uid ?: 0) > 0
    val canStart = ready && !state.busy && !currentMusicRoomActive
    val showAccountCard = !ready
    val accountSummary = when { account.stale -> "Đăng nhập Nhạc Vân NetEase trên thiết bị này đã hết hạn"; ready -> "Đã đăng nhập · ${account.profile?.nickname?.ifBlank { "người dùng Nhạc Vân NetEase" }}"; else -> "Chưa đăng nhập tài khoản Nhạc Vân NetEase trên thiết bị này" }
    var invite by rememberSaveable(account.revision, initialInvitation) { mutableStateOf(initialInvitation.orEmpty()) }
    val requestedRoomId = remember(initialInvitation) { initialInvitation?.let(ListenTogetherInvitation::parse)?.roomId }
    var inputError by remember(account.revision) { mutableStateOf<String?>(null) }
    var clipboardMessage by remember { mutableStateOf<String?>(null) }
    var leaveConfirmation by remember { mutableStateOf(false) }
    var copying by remember(state.room?.roomId) { mutableStateOf(false) }
    var pasting by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val focus = LocalFocusManager.current
    LaunchedEffect(leaveConfirmation) { onDialogActive(leaveConfirmation) }
    DisposableEffect(Unit) { onDispose { onDialogActive(false) } }
    LaunchedEffect(state.room?.roomId, account.revision) { clipboardMessage = null }
    LaunchedEffect(state.busy) { if (state.busy) focus.clearFocus() }
    LaunchedEffect(state.active, state.room?.roomId) {
        if (state.active) {
            if (requestedRoomId == null || requestedRoomId == state.room?.roomId) invite = ""
            inputError = null
        } else leaveConfirmation = false
    }
    fun phase(value: NeteaseListenTogetherState) = when {
        value.active -> "active:${value.room?.roomId}"
        ready && !currentMusicRoomActive && value.busy && value.availableRoom == null &&
            value.operation in setOf(ListenTogetherOperation.REFRESH, ListenTogetherOperation.RESTORE) -> "checking"
        else -> "idle"
    }
    MusicSubpage("Nghe cùng nhau", "netease_listen_screen", onBack, backTag = "netease_listen_back",
        actions = {
            if (ready && onHistory != null) GlassIconButton(onHistory, "Lịch sử nghe cùng nhau", Modifier.testTag("netease_listen_history")) {
                Icon(MusicIcons.Update, null)
            }
        }) { topInset ->
        ListenTogetherPageTransition(state, ::phase, { if (it.active) 1 else 0 }, Modifier.fillMaxSize().imePadding().padding(horizontal = 16.dp)) { state ->
        if (phase(state) == "checking") {
            LazyColumn(Modifier.fillMaxSize(), contentPadding = musicScrollPadding(PaddingValues(top = topInset + 8.dp, bottom = 24.dp))) {
                item { ListenTogetherSkeleton(if (state.operation == ListenTogetherOperation.RESTORE) "Đang khôi phục phiên nghe cùng nhau…" else "Đang kiểm tra phiên hiện có…",
                    "netease_listen_loading", rows = 2) }
            }
        } else LazyColumn(Modifier.fillMaxSize().testTag("netease_listen_content"),
            contentPadding = musicScrollPadding(PaddingValues(top = topInset + 8.dp, bottom = 24.dp)),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (!showAccountCard) item {
                SettingsGroup {
                    SettingsPreference("Nhạc Vân NetEase", accountSummary, Modifier.testTag("netease_listen_account"),
                        leading = { Icon(MusicIcons.Headphones, null, tint = colors.primary) })
                }
            }
            if (showAccountCard) item { SettingsGroup(Modifier.fillMaxWidth()) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Tài khoản Nhạc Vân NetEase", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(accountSummary, Modifier.testTag("netease_listen_account"), fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                if (!ready) {
                    Text("Sau khi đăng nhập, bạn có thể đồng bộ nghe cùng một bài hát với bạn bè trên Nhạc Vân NetEase.", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                    if (onBinding != null) MusicTextAction(if (account.stale) "Đăng nhập lại Nhạc Vân NetEase" else "Đăng nhập Nhạc Vân NetEase", onBinding,
                        Modifier.testTag("netease_listen_login"))
                }
            } } }
            if (currentMusicRoomActive) item { SettingsGroup(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Đang sử dụng phòng CurrentMusic", fontWeight = FontWeight.SemiBold)
                Text("Vui lòng rời khỏi phòng hiện tại trước khi bật tính năng nghe đôi Nhạc Vân NetEase.", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                MusicTextAction("Quay lại phòng", onBack)
            } } }
            state.error?.let { error -> item { SettingsGroup(Modifier.fillMaxWidth().testTag("netease_listen_error")) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(error, fontSize = 14.sp, color = colors.error)
                    Row { MusicTextAction("Thử đồng bộ lại", actions.refresh, Modifier.testTag("netease_listen_retry"), enabled = ready && !state.busy)
                        MusicTextAction("Đã hiểu", actions.dismissError) }
                }
            } } }
            if (state.active) {
                if (requestedRoomId != null && requestedRoomId != state.room?.roomId && invite.isNotBlank()) item {
                    SettingsGroup(Modifier.fillMaxWidth().testTag("netease_listen_pending_invitation")) {
                        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                             Text("Đã nhận được lời mời nghe cùng nhau", fontWeight = FontWeight.SemiBold)
                             Text("Vui lòng thoát phiên hiện tại trước, rồi xác nhận tham gia phiên được mời. Liên kết mời đã được giữ lại.",
                                fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                        }
                    }
                }
                item { SettingsGroup(Modifier.fillMaxWidth().testTag("netease_listen_session")) { Column(Modifier.padding(20.dp)) {
                    val participants = state.room?.participants.orEmpty()
                    val pair = ListenTogetherPair(participants.map { ListenTogetherParticipant(it.userId, it.nickname, it.avatarUrl) })
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        ListeningPairArtwork(pair, Modifier.size(84.dp, 52.dp))
                        Column(Modifier.weight(1f).padding(start = 16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                             Text(if (participants.size >= 2) "Đang nghe cùng nhau" else "Đang chờ bạn bè tham gia", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                             Text(participants.joinToString(" · ") { it.nickname.ifBlank { "Người dùng NetEase Cloud" } }.ifBlank { account.profile?.nickname.orEmpty() },
                                fontSize = 13.sp, color = colors.onSurfaceVariantSummary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            AnimatedContent(when {
                                 state.operation == ListenTogetherOperation.LEAVE -> "Đang thoát khỏi phiên nghe cùng nhau…"
                                 state.busy -> "Đang đồng bộ…"
                                 state.connected -> "Đã kết nối · Đang đồng bộ phát nhạc"
                                 else -> "Đang khôi phục đồng bộ…"
                            }, transitionSpec = { fadeIn(tween(180)) togetherWith fadeOut(tween(120)) }, label = "listen-connection") { message ->
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    if (state.busy || !state.connected) ListenProgress(size = 12.dp)
                                    else Box(Modifier.size(5.dp).background(colors.primary, CircleShape))
                                    Text(message, Modifier.testTag("netease_listen_connection"), fontSize = 11.sp, color = colors.primary)
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    val invitation = state.invitationUrl
                    GlassActionButton(
                         if (copying) "Đang sao chép…" else if (clipboardMessage == "Đã sao chép liên kết mời") "Đã sao chép liên kết mời" else "Sao chép liên kết mời",
                        onClick = { if (invitation != null && !copying) scope.launch {
                        copying = true
                         clipboardMessage = try { actions.copyInvitation(invitation); "Đã sao chép liên kết mời" }
                        catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
                         catch (_: Exception) { "Hiện chưa thể sao chép, vui lòng thử lại" }
                        finally { copying = false }
                    } },
                        enabled = !invitation.isNullOrBlank() && !copying && !state.busy, primary = true,
                        modifier = Modifier.fillMaxWidth().testTag("netease_listen_copy"))
                     Text(clipboardMessage ?: "Bạn bè có thể mở liên kết bằng Nhạc Vân NetEase để tham gia.", Modifier.fillMaxWidth().padding(top = 8.dp),
                        fontSize = 11.sp, color = if (clipboardMessage == null) colors.onSurfaceVariantSummary else colors.primary,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                    Spacer(Modifier.height(16.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                         GlassActionButton(if (state.operation == ListenTogetherOperation.REFRESH) "Đang đồng bộ…" else "Làm mới đồng bộ",
                            actions.refresh, Modifier.weight(1f).testTag("netease_listen_refresh"), enabled = ready && !state.busy)
                         GlassActionButton("Thoát phiên nghe cùng nhau", { leaveConfirmation = true }, Modifier.weight(1f).testTag("netease_listen_leave"), enabled = !state.busy, destructive = true)
                    }
                } } }
                item { SettingsGroup(Modifier.fillMaxWidth().testTag("netease_listen_now_playing")) { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        AnimatedContent(playback.song, contentKey = { it?.id }, label = "listen-now-playing",
                            transitionSpec = { fadeIn(tween(200)) togetherWith fadeOut(tween(120)) }) { song ->
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        MusicCover(song?.cover.orEmpty(), Modifier.size(56.dp), cornerRadius = 12.dp)
                        Column(Modifier.weight(1f).padding(start = 12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                 Text("Đang phát", Modifier.weight(1f), fontSize = 11.sp, color = colors.onSurfaceVariantSummary)
                                 Text("${state.queueCount} bài", fontSize = 11.sp, color = colors.onSurfaceVariantSummary)
                            }
                             Text(song?.name ?: "Đang chờ chọn bài hát", maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 17.sp, fontWeight = FontWeight.Medium)
                            song?.artists?.takeIf(String::isNotBlank)?.let { Text(it, fontSize = 12.sp, color = colors.onSurfaceVariantSummary,
                                maxLines = 1, overflow = TextOverflow.Ellipsis) }
                        } }
                        }
                    }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                         GlassActionButton("Chọn bài hát", onSearch, Modifier.weight(1f).testTag("netease_listen_search"), enabled = !state.busy)
                         GlassActionButton("Mở trình phát", onPlayer, Modifier.weight(1f).testTag("netease_listen_player"), enabled = playback.song != null)
                    }
                } } }
            } else {
                if (state.availableRoom != null) item { SettingsGroup(Modifier.fillMaxWidth()) { Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                     Text("Phát hiện phiên nghe cùng nhau hiện có", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                     Text("Sau khi khôi phục, tiếp tục đồng bộ phát nhạc trên thiết bị này.", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                     GlassActionButton(if (state.operation == ListenTogetherOperation.RESTORE) "Đang khôi phục…" else "Khôi phục phiên nghe cùng nhau",
                        actions.resume, Modifier.fillMaxWidth().testTag("netease_listen_resume"), enabled = canStart)
                } } }
                item { SettingsGroup(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                     Text("Nghe cùng bạn bè một bài hát", fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
                     Text("Tạo phiên hai người, sao chép liên kết mời để đồng bộ phát, tạm dừng và chuyển bài cùng bạn bè.", fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
                    GlassActionButton(if (state.operation == ListenTogetherOperation.CREATE) "Đang tạo…" else "Tạo cùng nghe",
                        actions.create, Modifier.fillMaxWidth().testTag("netease_listen_create"), primary = true, enabled = canStart)
                } } }
                item { SettingsGroup(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Tham gia cùng bạn bè", fontSize = 19.sp, fontWeight = FontWeight.SemiBold)
                    TextField(invite, { invite = it; inputError = null; clipboardMessage = null }, label = "Liên kết mời cùng nghe Nhạc Vân NetEase",
                        enabled = !state.busy, modifier = Modifier.fillMaxWidth().testTag("netease_listen_invite"))
                    inputError?.let { Text(it, Modifier.testTag("netease_listen_invite_error"), fontSize = 12.sp, color = colors.error) }
                    clipboardMessage?.let { Text(it, fontSize = 12.sp, color = colors.onSurfaceVariantSummary) }
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        MusicTextAction(if (pasting) "Đang dán…" else "Dán lời mời", {
                            scope.launch {
                                pasting = true
                                try {
                                    val value = actions.pasteInvitation()
                                    if (value.isNullOrBlank()) clipboardMessage = "Không có liên kết mời trong bộ nhớ tạm"
                                    else { invite = value; inputError = null; clipboardMessage = null }
                                } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
                                catch (_: Exception) { clipboardMessage = "Hiện không thể đọc bộ nhớ tạm, vui lòng dán thủ công" }
                                finally { pasting = false }
                            }
                        }, Modifier.testTag("netease_listen_paste"), enabled = !state.busy && !pasting)
                        MusicTextAction(if (state.operation == ListenTogetherOperation.JOIN) "Đang tham gia…" else "Tham gia cùng nghe", {
                            if (!ListenTogetherInvitation.isSupported(invite)) inputError = "Vui lòng dán liên kết mời cùng nghe Nhạc Vân NetEase hợp lệ"
                            else { focus.clearFocus(); actions.join(invite.trim()) }
                        }, Modifier.testTag("netease_listen_join"), enabled = canStart && invite.isNotBlank())
                    }
                    if (state.operation == ListenTogetherOperation.JOIN) ListenLoadingLabel("Đang kết nối với phiên của bạn bè…", Modifier.testTag("netease_listen_join_loading"))
                    Text("Vui lòng dùng liên kết mời của Nhạc Vân NetEase; mã phòng CurrentMusic gồm sáu chữ số thì quay lại trang phòng để tham gia.", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                } } }
                if (ready && onHistory != null) item {
                    SettingsGroup { MusicDestinationRow("Lịch sử cùng nghe", onHistory, summary = "Lịch sử, xếp hạng và báo cáo nhân duyên") }
                }
                item { MusicTextAction(if (state.busy) "Đang kiểm tra…" else "Kiểm tra phiên hiện có", actions.refresh,
                    Modifier.testTag("netease_listen_check"), enabled = ready && !state.busy) }
            }
        }
        }
    }
    if (leaveConfirmation && LocalMusicPageActive.current) MusicDialog("Thoát phiên cùng nghe", onDismiss = { leaveConfirmation = false }) {
        Text("Thoát sẽ kết thúc phiên cùng nghe hiện tại và khôi phục hàng đợi phát trên thiết bị này.")
        MusicDialogActions {
            MusicDialogButton("Hủy", { leaveConfirmation = false }, Modifier.weight(1f).testTag("netease_listen_cancel_leave"))
            MusicDialogButton("Xác nhận thoát", { leaveConfirmation = false; actions.leave() },
                Modifier.weight(1f).testTag("netease_listen_confirm_leave"), destructive = true, enabled = !state.busy)
        }
    }
}
