package io.github.currencortex.music.feature.playlistimport

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun PlaylistImportScreen(vm: PlaylistImportViewModel, onBack: () -> Unit,
    onLogin: () -> Unit, onPlaylist: (Long) -> Unit) {
    val state by vm.state.collectAsStateWithLifecycle()
    val account by vm.account.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val permission = if (Build.VERSION.SDK_INT >= 33) Manifest.permission.READ_MEDIA_AUDIO else Manifest.permission.READ_EXTERNAL_STORAGE
    var allowed by remember { mutableStateOf(ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) }
    var permissionError by remember { mutableStateOf<String?>(null) }
    val request = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        allowed = granted
        permissionError = if (granted) null else "Chưa được cấp quyền truy cập âm thanh, bạn có thể bật quyền này trong Cài đặt hệ thống."
        if (granted) vm.scan(context.applicationContext)
    }
    val imagePicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) { uris ->
        vm.readImages(context.applicationContext, uris)
    }
    DisposableEffect(lifecycle, permission) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                val granted = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
                if (allowed && !granted) vm.localPermissionLost()
                allowed = granted
            }
        }
        lifecycle.lifecycle.addObserver(observer)
        onDispose { lifecycle.lifecycle.removeObserver(observer) }
    }
    val colors = MiuixTheme.colorScheme
    val connected = account.loggedIn && !account.stale
    val editable = connected && !state.busy && !state.imagesLoading && state.task == null
    val clipboard = LocalClipboardManager.current
    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val ready = editable && !state.scanning && when (state.mode) {
        PlaylistImportMode.LINKS -> state.links.any(String::isNotBlank)
        PlaylistImportMode.TEXT -> state.text.isNotBlank()
        PlaylistImportMode.IMAGES -> state.images.isNotEmpty() && state.targetId != null && !state.targetsLoading
        PlaylistImportMode.LOCAL -> state.selected.isNotEmpty()
    }
    MusicSubpage("Nhập danh sách phát", "playlist_import", onBack, backTag = "playlist_import_back",
        modifier = Modifier.imePadding()) { topInset ->
        LazyColumn(Modifier.fillMaxSize().testTag("playlist_import_list"),
            contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp)),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
        if (state.task == null) item("actions") { SettingsGroup {
            Row(Modifier.fillMaxWidth().testTag("playlist_import_actions")
                .padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                if (connected) Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text("Nhạc Vân NetEase · ${account.profile?.nickname.orEmpty()}", fontSize = 11.sp,
                        color = colors.onSurfaceVariantSummary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    if (state.mode != PlaylistImportMode.IMAGES) Row(Modifier.fillMaxWidth().heightIn(min = 32.dp),
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Thêm vào mục «Bài hát yêu thích»", Modifier.weight(1f), fontSize = 13.sp,
                            color = colors.onSurfaceVariantSummary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        GlassSwitch(state.importStar, vm::importStar, enabled = editable, modifier = Modifier.testTag("playlist_import_star"))
                    } else Text("Lưu vào danh sách phát đã chọn", fontSize = 13.sp, color = colors.onSurfaceVariantSummary,
                        maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                GlassActionButton(if (!connected) "Đăng nhập Nhạc Vân NetEase để nhập" else if (state.busy) "Đang gửi…" else "Bắt đầu nhập", {
                    focus.clearFocus(); keyboard?.hide(); if (connected) vm.submit() else onLogin()
                }, modifier = (if (connected) Modifier.width(132.dp) else Modifier.fillMaxWidth())
                    .testTag(if (connected) "playlist_import_submit" else "playlist_import_login"),
                    primary = true, enabled = !connected || ready)
            }
        } }
        item("modes") {
            SettingsSegmentedControl(PlaylistImportMode.entries.map { it.label.removeSuffix("Nhập") }, state.mode.ordinal, {
                focus.clearFocus(); keyboard?.hide(); vm.mode(PlaylistImportMode.entries[it])
            }, enabled = editable, tag = "playlist_import_modes", role = Role.Tab,
                itemTag = { "playlist_import_tab_${PlaylistImportMode.entries[it].name}" })
        }
            if (!connected) item {
                Text(if (account.stale) "Phiên đăng nhập Nhạc Vân NetEase đã hết hạn, hãy đăng nhập lại để nhập." else "Đăng nhập Nhạc Vân NetEase để nhập danh sách phát.",
                    fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
            }
            run {
                if (state.task == null) {
                    item { PlaylistImportHero(state.mode) }
                    when (state.mode) {
                        PlaylistImportMode.LINKS -> {
                            item { ImportStep(1, "Chuẩn bị liên kết chia sẻ") }
                            item { PlaylistImportGuide() }
                            item { Spacer(Modifier.height(4.dp)); ImportStep(2, "Thêm liên kết chia sẻ") }
                            itemsIndexed(state.links) { index, link ->
                                ImportLinkField(link, { vm.link(index, it) }, "Liên kết ${index + 1}", editable,
                                    onPaste = { clipboard.getText()?.text?.takeIf(String::isNotBlank)?.let { vm.link(index, it) } },
                                    onRemove = if (state.links.size > 1) ({ vm.removeLink(index) }) else null,
                                    modifier = Modifier.fillMaxWidth(), tag = "playlist_import_link_$index")
                            }
                            if (state.links.size < 10) item {
                                ImportTextAction("＋ Thêm liên kết khác", vm::addLink, editable, Modifier.testTag("playlist_import_add_link"))
                            }
                            item { Text("Tối đa 10 liên kết, hỗ trợ danh sách phát, trang cá nhân và bài viết.", fontSize = 12.sp, color = colors.onSurfaceVariantSummary) }
                        }
                        PlaylistImportMode.TEXT -> item {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                ImportStep(1, "Dán thông tin bài hát")
                                Text("Mỗi dòng một bài hát; định dạng “Tên bài hát - Ca sĩ” giúp nhận diện chính xác hơn.", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                                BasicTextField(state.text, vm::text, enabled = editable,
                                    textStyle = TextStyle(fontSize = 15.sp, color = colors.onBackground), cursorBrush = SolidColor(colors.primary),
                                    modifier = Modifier.fillMaxWidth().heightIn(min = 220.dp).clip(RoundedCornerShape(20.dp))
                                        .background(colors.surfaceContainer).padding(18.dp).testTag("playlist_import_text"),
                                    decorationBox = { inner -> if (state.text.isEmpty()) Text("Tên bài hát - Ca sĩ\nTên bài hát - Ca sĩ", fontSize = 15.sp, color = colors.onSurfaceVariantSummary); inner() })
                                Text("Hỗ trợ nhiều bài hát, Nhạc Vân NetEase sẽ tự động tìm khớp.", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                            }
                        }
                        PlaylistImportMode.IMAGES -> {
                            item {
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    ImportStep(1, "Chọn ảnh danh sách phát")
                                    Text("Chọn ảnh chụp màn hình danh sách phát hoặc danh sách bài hát, tối đa 9 ảnh, mỗi ảnh không quá 8 MB.",
                                        fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
                                    Text("Sau khi nhấn Bắt đầu nhập, ảnh đã chọn sẽ được tải lên Nhạc Vân NetEase để nhận diện, rồi các bài hát sẽ được thêm vào danh sách phát bạn chọn.",
                                        fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
                                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                        Text(if (state.imagesLoading) "Đang tải ảnh…" else "Đã chọn ${state.images.size} / 9 ảnh",
                                            Modifier.weight(1f).testTag("playlist_import_image_count"), fontSize = 14.sp)
                                        MusicTextAction(if (state.images.isEmpty()) "Chọn ảnh" else "Chọn lại ảnh",
                                            { imagePicker.launch(arrayOf("image/*")) },
                                            Modifier.testTag("playlist_import_pick_images"), enabled = editable)
                                    }
                                }
                            }
                            itemsIndexed(state.images) { index, image ->
                                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    Text("${index + 1}. ${image.fileName}", Modifier.weight(1f), fontSize = 14.sp,
                                        maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    MusicTextAction("Xóa", { vm.removeImage(index) },
                                        Modifier.testTag("playlist_import_remove_image_$index"), enabled = editable)
                                }
                            }
                            item {
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                        Box(Modifier.weight(1f)) { ImportStep(2, "Chọn danh sách phát đích") }
                                        MusicTextAction(if (state.targetsLoading) "Đang tải…" else "Làm mới", vm::loadTargets,
                                            Modifier.testTag("playlist_import_reload_targets"), enabled = editable && !state.targetsLoading)
                                    }
                                    Text("Vui lòng chọn một danh sách phát trên Nhạc Vân NetEase mà bạn có thể chỉnh sửa.", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                                    state.targetsError?.let { Text(it, fontSize = 13.sp, color = colors.error) }
                                    if (state.targetsLoaded && state.targets.isEmpty()) Text("Chưa có danh sách phát nào có thể chỉnh sửa. Vui lòng tạo danh sách phát trên Nhạc Vân NetEase trước, rồi quay lại làm mới.",
                                        fontSize = 14.sp, modifier = Modifier.testTag("playlist_import_targets_empty"))
                                    if (state.targets.isNotEmpty()) LazyColumn(Modifier.fillMaxWidth().heightIn(max = 260.dp).selectableGroup()
                                        .testTag("playlist_import_targets")) {
                                        items(state.targets, key = { it.id }) { target ->
                                            val selected = state.targetId == target.id
                                            Row(Modifier.fillMaxWidth().selectable(selected, enabled = editable && !state.targetsLoading,
                                                role = Role.RadioButton, onClick = { vm.target(target.id) })
                                                .testTag("playlist_import_target_${target.id}").padding(vertical = 12.dp),
                                                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                                Text(target.name, Modifier.weight(1f), fontSize = 15.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                                Text(if (selected) "✓" else "○", color = if (selected) colors.primary else colors.onSurfaceVariantSummary)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        PlaylistImportMode.LOCAL -> {
                            item {
                                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                    ImportStep(1, "Chọn bài hát trên thiết bị")
                                    Text("Sau khi quét, hãy chọn bài hát cần tìm khớp. Khi bắt đầu nhập, chỉ thông tin về các bài hát đã chọn được gửi đi, tệp âm thanh sẽ không được tải lên.",
                                        fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
                                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                        MusicTextAction(if (state.scanning) "Đang quét…" else if (state.scanned) "Quét lại" else "Quét nhạc trên thiết bị", {
                                            permissionError = null
                                            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED)
                                                vm.scan(context.applicationContext) else request.launch(permission)
                                        }, Modifier.weight(1f).testTag("playlist_import_scan"), enabled = editable && !state.scanning)
                                        if (state.localSongs.isNotEmpty()) MusicTextAction(if (state.selected.size == state.localSongs.size) "Bỏ chọn tất cả" else "Chọn tất cả",
                                            vm::selectAll, enabled = editable)
                                    }
                                    permissionError?.let { Text(it, fontSize = 13.sp, color = colors.error) }
                                    if (state.scanned) Text("Đã chọn ${state.selected.size} / ${state.localSongs.size} bài", fontSize = 13.sp,
                                        modifier = Modifier.testTag("playlist_import_local_count"))
                                    if (state.scanned && state.localSongs.isEmpty()) Text("Không tìm thấy nhạc trên thiết bị.", fontSize = 14.sp)
                                }
                            }
                            if (state.localSongs.isNotEmpty()) item {
                                LazyColumn(Modifier.fillMaxWidth().heightIn(max = 320.dp).testTag("playlist_import_local_songs")) {
                                    items(state.localSongs, key = { it.localUri }) { song ->
                                        val selected = song.localUri in state.selected
                                        Row(Modifier.fillMaxWidth().selectable(selected, enabled = editable, role = Role.Checkbox, onClick = { vm.select(song) })
                                            .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                            Box(Modifier.size(28.dp).clip(CircleShape).background(if (selected) colors.primary else colors.surfaceContainer), contentAlignment = Alignment.Center) {
                                                Text(if (selected) "✓" else "", color = colors.onPrimary, fontSize = 16.sp)
                                            }
                                            Column(Modifier.weight(1f)) {
                                                Text(song.name, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                                Text(song.artists, fontSize = 12.sp, color = colors.onSurfaceVariantSummary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (state.task != null) item {
                    SettingsGroup { Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(if (state.result?.finished == true) "Kết quả nhập" else "Đang xử lý nhập",
                            fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        state.message?.let { Text(it, fontSize = 14.sp, modifier = Modifier.testTag("playlist_import_status")) }
                        Text("Tác vụ đã được gửi đến Nhạc Vân NetEase; rời khỏi trang sẽ không hủy tác vụ.", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                        if (state.result?.finished != true) MusicDialogButton(if (state.busy) "Đang kiểm tra tiến độ…" else "Làm mới tiến độ", vm::refresh,
                            Modifier.testTag("playlist_import_refresh"), enabled = !state.busy)
                        state.result?.playlistIds.orEmpty().forEachIndexed { index, id ->
                            MusicDestinationRow("Mở danh sách phát đã nhập${if (state.result?.playlistIds?.size == 1) "" else " ${index + 1}"}", { onPlaylist(id) },
                                Modifier.testTag("playlist_import_result_$id"))
                        }
                        if (state.result?.finished == true) MusicDialogButton("Tiếp tục nhập", vm::newImport, enabled = !state.busy)
                    } }
                }
                state.error?.let { error -> item { Text(error, fontSize = 14.sp, color = colors.error, modifier = Modifier.testTag("playlist_import_error")) } }
            }
        }

    }
}

@Composable private fun ImportTextAction(label: String, onClick: () -> Unit, enabled: Boolean, modifier: Modifier = Modifier) {
    GlassActionButton(label, onClick, modifier, enabled = enabled, fillWidth = false)

}

@Composable private fun ImportLinkField(value: String, onValue: (String) -> Unit, label: String, enabled: Boolean,
    onPaste: () -> Unit, onRemove: (() -> Unit)?, modifier: Modifier, tag: String) {
    val colors = MiuixTheme.colorScheme
    Column(modifier.clip(RoundedCornerShape(18.dp)).background(colors.surfaceContainer)
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(label, Modifier.weight(1f), fontSize = 12.sp, fontWeight = FontWeight.Medium, color = colors.onSurfaceVariantSummary)
            ImportTextAction("Dán", onPaste, enabled)
            onRemove?.let { ImportTextAction("Xóa", it, enabled, Modifier.testTag("${tag}_remove")) }
        }
        BasicTextField(value, onValue, enabled = enabled, singleLine = true,
            modifier = Modifier.fillMaxWidth().heightIn(min = 28.dp).testTag(tag),
            textStyle = TextStyle(color = colors.onBackground, fontSize = 15.sp), cursorBrush = SolidColor(colors.primary),
            decorationBox = { inner -> if (value.isEmpty()) Text("Dán liên kết hoặc toàn bộ nội dung chia sẻ", fontSize = 14.sp,
                color = colors.onSurfaceVariantSummary.copy(alpha = .65f), maxLines = 1, overflow = TextOverflow.Ellipsis); inner() })
    }
}
