package io.github.currencortex.music.feature.player

import io.github.currencortex.music.ui.component.MusicDialogButton
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.song.NeteaseComment
import io.github.currencortex.music.data.song.CommentSort
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.DecoratedAvatar
import io.github.currencortex.music.ui.component.MusicOverlayDialog
import io.github.currencortex.music.ui.component.MusicPlaceholder
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.drop

@Composable internal fun PlayerCommentsDialog(state: PlayerComments, dismiss: () -> Unit, reload: () -> Unit, more: () -> Unit,
    composer: PlayerCommentComposer = PlayerCommentComposer(), edit: () -> Unit = {}, reply: (NeteaseComment?) -> Unit = {}, send: () -> Unit = {},
    sort: (CommentSort) -> Unit = {}, replies: (NeteaseComment, Boolean) -> Unit = { _, _ -> },
    like: (NeteaseComment) -> Unit = {}, delete: (NeteaseComment) -> Unit = {}) {
    val colors = io.github.currencortex.music.ui.component.musicDialogColors()
    val indication = LocalIndication.current
    val density = LocalDensity.current
    val windowHeight = with(density) { LocalWindowInfo.current.containerSize.height.toDp() }
    val ime = with(density) { WindowInsets.ime.getBottom(this).toDp() }
    val safeTop = with(density) { WindowInsets.statusBars.getTop(this).toDp() }
    val maxHeight = (windowHeight - ime - safeTop - 72.dp).coerceIn(180.dp, 680.dp)
    var hotSelected by rememberSaveable(state.songId) { mutableStateOf(true) }
    val showHot = state.enhanced == false && hotSelected && state.hot.isNotEmpty()
    var deleting by remember(state.songId) { mutableStateOf<NeteaseComment?>(null) }
    val hotScroll = rememberLazyListState()
    val latestScroll = rememberLazyListState()
    LaunchedEffect(state.songId, state.sort) { latestScroll.scrollToItem(0); hotScroll.scrollToItem(0) }
    val focus = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    var focusReply by remember { mutableIntStateOf(0) }
    LaunchedEffect(focusReply) { if (focusReply > 0) { focus.requestFocus(); keyboard?.show() } }
    LaunchedEffect(composer.message) {
        if (composer.message != null) { focusManager.clearFocus(); keyboard?.hide(); hotSelected = false; latestScroll.scrollToItem(0) }
    }
    val onEdit by rememberUpdatedState(edit)
    LaunchedEffect(composer.input) {
        // Observe only for feedback; never round-trip IME text through the StateFlow.
        snapshotFlow { composer.input.text.toString() }.drop(1).collect { onEdit() }
    }
    val close = { focusManager.clearFocus(); keyboard?.hide(); dismiss() }
    MusicOverlayDialog(show = true, onDismissRequest = close, insideMargin = DpSize(20.dp, 16.dp)) {
        CompositionLocalProvider(LocalContentColor provides colors.onSurface, LocalIndication provides indication) {
        Column(Modifier.fillMaxWidth().heightIn(max = maxHeight), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Bình luận Nhạc Vân NetEase", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                state.total?.let { Text(" $it", Modifier.weight(1f), fontSize = 13.sp, color = colors.onSurfaceVariantSummary) }
                    ?: Spacer(Modifier.weight(1f))
                CommentAction(if (state.loading) "Đang làm mới…" else "Làm mới", "refresh_comments", !state.loading && !composer.sending && state.busy.isEmpty(), reload)
                Box(Modifier.size(44.dp).clip(RoundedCornerShape(14.dp)).clickable(onClick = close)
                    .semantics { contentDescription = "Đóng bình luận"; role = Role.Button }.testTag("close_comments"), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Close, null, Modifier.size(20.dp), tint = colors.onSurfaceVariantSummary)
                }
            }
            if (state.enhanced != false) Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                CommentSort.entries.forEach { choice ->
                    CommentTab(choice.label, state.sort == choice, "comments_${choice.value}") {
                        if (!composer.sending && state.busy.isEmpty()) sort(choice)
                    }
                }
            } else if (state.hot.isNotEmpty()) Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                CommentTab("Phổ biến", showHot, "comments_hot") { hotSelected = true }
                CommentTab("Mới nhất", !showHot, "comments_latest") { hotSelected = false }
            }
            val rows = if (showHot) state.hot else state.latest
            LazyColumn(Modifier.fillMaxWidth().weight(1f).testTag("player_comments_list"),
                state = if (showHot) hotScroll else latestScroll, contentPadding = PaddingValues(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)) {
                if (state.loading && rows.isEmpty()) items(3) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        MusicPlaceholder(Modifier.size(36.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            MusicPlaceholder(Modifier.fillMaxWidth(.35f).height(12.dp))
                            MusicPlaceholder(Modifier.fillMaxWidth().height(40.dp))
                        }
                    }
                }
                items(rows, key = { it.id }) { comment ->
                    var expandedReplies by rememberSaveable(state.songId, state.sort, comment.id) { mutableStateOf(false) }
                    val actionsEnabled = !composer.sending && !state.loading && state.busy.isEmpty() && state.replies.values.none { it.loading }
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        CommentContent(comment, enabled = !composer.sending,
                            enhanced = state.enhanced == true, busy = !actionsEnabled || comment.id in state.busy,
                            own = state.meUid != 0L && state.meUid == comment.userId,
                            like = { like(comment) }, delete = { deleting = comment }) { reply(comment); focusReply++ }
                        if (state.enhanced == true && comment.replyCount > 0) {
                            CommentAction(if (expandedReplies) "Thu gọn câu trả lời" else "Xem ${comment.replyCount} câu trả lời", "replies_${comment.id}") {
                                expandedReplies = !expandedReplies
                                if (expandedReplies && state.replies[comment.id] == null) replies(comment, false)
                            }
                            if (expandedReplies) Column(Modifier.padding(start = 24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                val floor = state.replies[comment.id]
                                if (floor == null) CommentAction("Tải câu trả lời", "load_replies_${comment.id}", !state.loading && state.busy.isEmpty()) { replies(comment, false) }
                                floor?.comments?.forEach { child -> key(child.id) {
                                    CommentContent(child, !composer.sending, enhanced = true,
                                        busy = !actionsEnabled || child.id in state.busy,
                                        own = state.meUid != 0L && state.meUid == child.userId,
                                        like = { like(child) }, delete = { deleting = child }) { reply(child); focusReply++ }
                                } }
                                if (floor?.loading == true) Text("Đang tải câu trả lời…", fontSize = 12.sp)
                                floor?.error?.let { Text(it, fontSize = 12.sp)
                                    CommentAction("Thử tải lại câu trả lời", "retry_replies_${comment.id}") { replies(comment, floor.comments.isNotEmpty()) }
                                }
                                if (floor?.more == true && !floor.loading && floor.error == null)
                                    CommentAction("Xem thêm câu trả lời", "more_replies_${comment.id}") { replies(comment, true) }
                            }
                        }
                    }
                }
                if (!state.loading && state.error == null && rows.isEmpty()) item {
                    Column(Modifier.fillMaxWidth().padding(vertical = 28.dp), horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Chưa có bình luận", fontSize = 17.sp, fontWeight = FontWeight.Medium)
                        Text("Hãy chia sẻ cảm nhận và trở thành người nghe đầu tiên", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                    }
                }
                state.error?.let { error -> item {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(error, fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                        CommentAction("Thử lại", "retry_comments", !state.loading, { if (state.retryMore) more() else reload() })
                    }
                } }
                if (!showHot && state.more && state.error == null) item {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CommentAction(if (state.loading) "Đang tải…" else "Tải thêm bình luận", "more_comments", !state.loading, more)
                    }
                }
                if (!showHot && rows.isNotEmpty() && !state.more && !state.loading && state.error == null) item {
                    Text("Đã hiển thị tất cả bình luận", Modifier.fillMaxWidth().padding(vertical = 8.dp), fontSize = 12.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center, color = colors.onSurfaceVariantSummary)
                }
            }
            HorizontalDivider(color = colors.onSurface.copy(alpha = .08f))
            state.actionError?.let { Text(it, fontSize = 12.sp, modifier = Modifier.testTag("comment_action_error")) }
            composer.replyTo?.let { comment ->
                Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(colors.onSurface.copy(alpha = .04f)).padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text("Trả lời ${comment.user.nickname.ifBlank { "Người dùng Nhạc Vân NetEase" }}", fontSize = 12.sp, color = colors.primary)
                        Text(comment.content, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, color = colors.onSurfaceVariantSummary)
                    }
                    CommentAction("Hủy", "cancel_comment_reply", !composer.sending) { reply(null) }
                }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Bottom) {
                TextField(state = composer.input, modifier = Modifier.fillMaxWidth().then(Modifier.weight(1f).focusRequester(focus).testTag("player_comment_draft")),
                    label = if (composer.replyTo == null) "Viết bình luận của bạn" else "Viết câu trả lời của bạn", useLabelAsPlaceholder = true,
                    insideMargin = DpSize(14.dp, 12.dp), cornerRadius = 16.dp, lineLimits = TextFieldLineLimits.MultiLine(maxHeightInLines = 3),
                    textStyle = MiuixTheme.textStyles.main.copy(fontSize = 15.sp), readOnly = composer.sending)
                MusicDialogButton(if (composer.sending) "Đang gửi…" else "Gửi", send, Modifier.testTag("player_comment_send"),
                    enabled = !composer.sending && !state.loading && state.busy.isEmpty() && composer.draft.isNotBlank() && composer.draft.length <= 1000, fillWidth = false)
            }
            val feedback = if (composer.draft.length > 1000) "Bình luận tối đa 1000 ký tự, vui lòng rút ngắn rồi gửi" else composer.error ?: composer.message
            if (feedback != null || composer.draft.isNotEmpty()) Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                Text(feedback ?: "", Modifier.weight(1f).testTag(if (composer.error != null || composer.draft.length > 1000) "player_comment_error" else "player_comment_result")
                    .semantics { liveRegion = LiveRegionMode.Polite }, fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                if (composer.draft.isNotEmpty()) Text("${composer.draft.length}/1000", Modifier.padding(start = 8.dp), fontSize = 11.sp,
                    color = if (composer.draft.length >= 1000) colors.primary else colors.onSurfaceVariantSummary)
            }
        }
        }
    }
    deleting?.let { comment ->
        MusicDialog("Xóa bình luận", { deleting = null }) {
            Text("Bạn có chắc muốn xóa bình luận này không?")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MusicDialogButton("Hủy", { deleting = null }, modifier = Modifier.weight(1f))
                MusicDialogButton("Xóa", { deleting = null; delete(comment) }, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable private fun CommentTab(title: String, selected: Boolean, tag: String, onClick: () -> Unit) {
    val colors = io.github.currencortex.music.ui.component.musicDialogColors()
    Column(Modifier.clip(RoundedCornerShape(4.dp)).clickable(onClick = onClick).semantics { this.selected = selected; role = Role.Tab }
        .testTag(tag).padding(horizontal = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(title, Modifier.padding(top = 4.dp, bottom = 10.dp), fontSize = 15.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) colors.onSurface else colors.onSurfaceVariantSummary)
        Box(Modifier.width(18.dp).height(3.dp).clip(RoundedCornerShape(2.dp)).background(if (selected) colors.primary else androidx.compose.ui.graphics.Color.Transparent))
    }
}

@Composable private fun CommentAction(title: String, tag: String, enabled: Boolean = true, onClick: () -> Unit) {
    Text(title, Modifier.clip(RoundedCornerShape(10.dp)).clickable(enabled = enabled, role = Role.Button, onClick = onClick)
        .testTag(tag).padding(horizontal = 10.dp, vertical = 12.dp), fontSize = 13.sp,
        color = MiuixTheme.colorScheme.primary.copy(alpha = if (enabled) 1f else .45f))
}

@Composable private fun CommentContent(comment: NeteaseComment, enabled: Boolean, enhanced: Boolean = false,
    busy: Boolean = false, own: Boolean = false, like: () -> Unit = {}, delete: () -> Unit = {}, reply: () -> Unit) {
    val colors = io.github.currencortex.music.ui.component.musicDialogColors()
    var expanded by rememberSaveable(comment.id) { mutableStateOf(false) }
    var overflow by remember(comment.content) { mutableStateOf(false) }
    val time = remember(comment.time) {
        if (comment.time > 0) SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(comment.time)) else ""
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        DecoratedAvatar(comment.user.avatarUrl.takeIf { it.isNotBlank() }, null, size = 34.dp)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(comment.user.nickname.ifBlank { "Người dùng Nhạc Vân NetEase" }, Modifier.weight(1f), fontSize = 13.sp, fontWeight = FontWeight.Medium,
                    color = colors.onSurfaceVariantSummary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                if (enhanced) CommentAction("${if (comment.likedByMe) "Đã thích" else "Thích"} ${comment.likedCount}",
                    "like_comment_${comment.id}", !busy, like)
                else if (comment.likedCount > 0) Text("${comment.likedCount} lượt thích", Modifier.padding(start = 8.dp), fontSize = 11.sp, color = colors.onSurfaceVariantSummary)
            }
            SelectionContainer {
                Text(comment.content, fontSize = 15.sp, lineHeight = 23.sp, maxLines = if (expanded) Int.MAX_VALUE else 6,
                    overflow = TextOverflow.Ellipsis, onTextLayout = { if (!expanded) overflow = it.hasVisualOverflow })
            }
            if (overflow || expanded) CommentAction(if (expanded) "Thu gọn" else "Mở rộng", "expand_comment_${comment.id}") { expanded = !expanded }
            comment.beReplied.firstOrNull()?.let { quote ->
                Text("${quote.user?.nickname?.ifBlank { "Người dùng Nhạc Vân NetEase" } ?: "Người dùng Nhạc Vân NetEase"}：${quote.content ?: "Bình luận gốc đã bị xóa"}",
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(colors.onSurface.copy(alpha = .04f)).padding(10.dp),
                    fontSize = 12.sp, lineHeight = 18.sp, maxLines = 3, overflow = TextOverflow.Ellipsis, color = colors.onSurfaceVariantSummary)
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(time, Modifier.weight(1f), fontSize = 11.sp, color = colors.onSurfaceVariantSummary)
                if (enhanced && own) CommentAction("Xóa", "delete_comment_${comment.id}", !busy, delete)
                CommentAction("Trả lời", "reply_comment_${comment.id}", enabled, reply)
            }
        }
    }
}
