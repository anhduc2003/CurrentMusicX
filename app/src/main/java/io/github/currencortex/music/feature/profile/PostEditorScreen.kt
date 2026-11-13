package io.github.currencortex.music.feature.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.profile.PostAttachment
import io.github.currencortex.music.data.song.NeteaseSongActionsRepository
import io.github.currencortex.music.feature.update.MarkdownText
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun PostEditorScreen(vm: PostsViewModel, state: PostsState) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue(state.draft, TextRange(state.draft.length))) }
    var preview by rememberSaveable { mutableStateOf(false) }
    val requester = remember { FocusRequester() }
    val songFlow = remember(vm) { vm.container.playerController.state.map { it.song }.distinctUntilChanged() }
    val song by songFlow.collectAsStateWithLifecycle(initialValue = null)
    val current = song?.let { s -> NeteaseSongActionsRepository.songId(s)?.let { PostAttachment("song", it, s.name, s.artists, s.cover) } }
    val colors = MiuixTheme.colorScheme
    val appDensity = LocalDensity.current
    fun close() { if (!state.busy) { if (state.picker) vm.picker(false) else vm.editor(false) } }
    Dialog(::close, properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false,
        dismissOnBackPress = !state.busy, dismissOnClickOutside = false)) {
        CompositionLocalProvider(LocalGlassBackdrop provides null, LocalMusicBottomInset provides 0.dp, LocalDensity provides appDensity) {
            val focus = LocalFocusManager.current
             MusicSubpage(if (state.picker) "Thêm nhạc" else "Đăng bài", "post_editor", onBack = ::close,
                backTag = "post_editor_close", modifier = Modifier.background(colors.background).imePadding(), actions = {
                     if (state.picker) GlassActionButton("Hoàn tất", { vm.picker(false) }, Modifier.testTag("post_picker_done"),
                        primary = true, fillWidth = false)
                     else GlassActionButton(if (state.busy) "Đang đăng" else "Đăng", { focus.clearFocus(); vm.publish() },
                        Modifier.testTag("post_publish"), primary = true, fillWidth = false,
                        enabled = !state.busy && state.draft.isNotBlank())
                }) { topInset ->
                Column(Modifier.fillMaxSize().padding(top = topInset)) {
                    if (state.picker) {
                        PostAttachmentPicker(vm, state, current, Modifier.weight(1f))
                    } else {
                         SettingsSegmentedControl(listOf("Chỉnh sửa", "Xem trước"), if (preview) 1 else 0, {
                            focus.clearFocus(); preview = it == 1
                        }, Modifier.padding(horizontal = 16.dp, vertical = 8.dp), tag = "post_editor_mode", enabled = !state.busy)
                        LazyColumn(Modifier.weight(1f).fillMaxWidth().testTag("post_editor_content"),
                            contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            item {
                                SettingsGroup {
                                    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                                        verticalAlignment = Alignment.CenterVertically) {
                                         Text(if (preview) "Xem trước nội dung" else "Viết điều bạn đang nghĩ", Modifier.weight(1f),
                                            fontSize = 15.sp, fontWeight = FontWeight.Medium)
                                         Text("${text.text.length} ký tự", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                                    }
                                    Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 16.dp).heightIn(min = 176.dp)) {
                                        if (preview) {
                                             if (text.text.isBlank()) Text("Chưa có nội dung", color = colors.onSurfaceVariantSummary)
                                            else MarkdownText(text.text, Modifier.testTag("post_preview"), compact = true)
                                        } else BasicTextField(text, { text = it; vm.draft(it.text) },
                                            Modifier.fillMaxWidth().heightIn(min = 176.dp).focusRequester(requester)
                                                 .semantics { contentDescription = "Nội dung bài đăng" }.testTag("post_draft"), enabled = !state.busy,
                                            textStyle = TextStyle(fontSize = 16.sp, lineHeight = 25.sp, color = colors.onSurface),
                                            cursorBrush = SolidColor(colors.primary), decorationBox = { input ->
                                                 Box { if (text.text.isEmpty()) Text("Gần đây bạn đang nghe gì?", fontSize = 16.sp,
                                                    lineHeight = 25.sp, color = colors.onSurfaceVariantSummary); input() }
                                            })
                                    }
                                    if (!preview) {
                                        SettingsDivider()
                                        Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(8.dp),
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                            PostMarkdownTool.entries.forEach { tool ->
                                                GlassIconButton({ text = applyPostMarkdown(text, tool); vm.draft(text.text); requester.requestFocus() },
                                                    tool.label, Modifier.testTag("post_tool_${tool.name.lowercase()}"), enabled = !state.busy, surface = false) {
                                                    Text(tool.glyph, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = colors.onSurface)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            item {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Row(Modifier.fillMaxWidth().padding(horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                                         Text("Chia sẻ nhạc", Modifier.weight(1f), fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                                        Text("${state.attachments.size}/5", Modifier.testTag("post_attachment_count"),
                                            fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                                    }
                                    SettingsGroup {
                                        state.attachments.forEach { card ->
                                            PostAttachmentTile(card, Modifier.padding(horizontal = 12.dp).padding(top = 12.dp)) {
                                                 GlassIconButton({ vm.removeAttachment(card) }, "Xóa ${card.name}",
                                                    Modifier.testTag("post_remove_${card.type}_${card.id}"), enabled = !state.busy, surface = false) {
                                                    Icon(MusicIcons.Close, null, Modifier.size(18.dp))
                                                }
                                            }
                                        }
                                         SettingsPreference("Thêm nhạc", if (state.attachments.isEmpty()) "Bài hát, album, nghệ sĩ hoặc danh sách phát" else "Có thể thêm ${5 - state.attachments.size} mục nữa",
                                            Modifier.testTag("post_add_attachment"), enabled = !state.busy && state.attachments.size < 5,
                                            onClick = { focus.clearFocus(); vm.picker(true) },
                                            leading = { Icon(MusicIcons.Add, null, Modifier.size(22.dp), tint = colors.primary) },
                                            trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp), tint = colors.onSurfaceVariantSummary) })
                                        if (state.attachments.isEmpty() && current != null) {
                                            SettingsDivider(50.dp)
                                             SettingsPreference("Đính kèm bài hát hiện tại", current.name, Modifier.testTag("post_attach_current"),
                                                enabled = !state.busy, onClick = { vm.attach(current) },
                                                leading = { MusicCover(current.pic, Modifier.size(32.dp).clip(RoundedCornerShape(8.dp))) })
                                        }
                                    }
                                     SettingsHint("Đóng lại để giữ bản nháp")
                                }
                            }
                        }
                        state.message?.let { SettingsHint(it, Modifier.testTag("post_editor_message"), error = true) }
                    }
                }
            }
        }
    }
}

@Composable private fun PostAttachmentPicker(vm: PostsViewModel, state: PostsState, current: PostAttachment?, modifier: Modifier) {
    val focus = LocalFocusManager.current
    val types = listOf("song", "album", "artist", "playlist")
    Column(modifier.fillMaxWidth()) {
         SettingsSegmentedControl(listOf("Bài hát", "Album", "Nghệ sĩ", "Danh sách phát"), types.indexOf(state.attachmentType),
            { vm.attachmentType(types[it]) }, Modifier.padding(horizontal = 16.dp, vertical = 8.dp), tag = "post_attachment_type")
        MusicSearchBar(state.attachmentQuery, { focus.clearFocus(); vm.searchAttachments() },
            Modifier.fillMaxWidth().padding(16.dp), onInput = vm::attachmentQuery,
             placeholder = "Tìm kiếm${postAttachmentLabel(state.attachmentType)}", inputTag = "post_attachment_query",
            submitTag = "post_attachment_search", onCancel = { vm.attachmentQuery("") })
         Text("Đã thêm ${state.attachments.size}/5 · Nhấn vào mục để thêm hoặc xóa", Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
            fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        LazyColumn(Modifier.weight(1f).testTag("post_attachment_results"), contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
             if (!state.attachmentSearched && current != null) item { Text("Đang phát", fontSize = 13.sp) }
            if (!state.attachmentSearched && current != null) item { AttachmentChoice(current, state, vm) }
             if (!state.attachmentSearched) item { Text("Nhập từ khóa để tìm nội dung nhạc muốn chia sẻ.", fontSize = 13.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary) }
            items(state.attachmentResults, key = { "${it.type}:${it.id}" }) { AttachmentChoice(it, state, vm) }
            if (state.attachmentLoading) item { LoadingTextList(3, leading = true) }
            if (state.attachmentSearched && !state.attachmentLoading && state.attachmentError == null && state.attachmentResults.isEmpty()) item {
                 Text("Không tìm thấy nội dung phù hợp, hãy thử từ khóa khác.", Modifier.padding(vertical = 20.dp), fontSize = 14.sp)
            }
            state.attachmentError?.let { error -> item {
                Text(error, fontSize = 13.sp)
                 MusicTextAction("Thử lại", vm::retryAttachments, Modifier.testTag("post_attachment_retry"))
            } }
            if (state.attachmentMore && !state.attachmentLoading && state.attachmentError == null) item {
                 MusicTextAction("Tải thêm", { vm.searchAttachments(true) }, Modifier.testTag("post_attachment_more"))
            }
        }
        state.message?.let { Text(it, Modifier.padding(horizontal = 20.dp), fontSize = 13.sp, color = MiuixTheme.colorScheme.primary) }
    }
}

@Composable private fun AttachmentChoice(card: PostAttachment, state: PostsState, vm: PostsViewModel) {
    val selected = state.attachments.any { it.type == card.type && it.id == card.id }
    val enabled = selected || state.attachments.size < 5
    PostAttachmentTile(card, Modifier.testTag("post_pick_${card.type}_${card.id}").semantics { this.selected = selected },
        onClick = { if (selected) vm.removeAttachment(card) else vm.attach(card) }, enabled = enabled) {
        Icon(if (selected) MusicIcons.Check else MusicIcons.Add,
             if (selected) "Đã thêm" else if (enabled) "Thêm" else "Tối đa 5 mục", Modifier.size(22.dp),
            tint = if (selected) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface.copy(alpha = if (enabled) .6f else .25f))
    }
}
