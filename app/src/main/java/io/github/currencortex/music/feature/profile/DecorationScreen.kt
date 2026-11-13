package io.github.currencortex.music.feature.profile

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.profile.*
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.util.collectAsPageState
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun DecorationScreen(vm: DecorationViewModel, onBack: () -> Unit = {}) {
    val state by vm.state.collectAsPageState()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val message by vm.message.collectAsStateWithLifecycle()
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    val user = ProfileUser(id = account.account?.id ?: 0L, avatar = account.account?.avatar.orEmpty())
    DecorationPage(state, busy, message, onBack, vm::reload, vm::query, vm::preview, vm::set) { id, size ->
        UserAvatar(user, vm.container, size, id, reserveOverlay = false)
    }
}

private fun Decoration.label() = name.ifBlank { id }.removePrefix("key-").ifBlank { "Không đeo" }

@Composable internal fun DecorationPage(state: DecorationState, busy: Boolean, message: String?, onBack: () -> Unit,
    reload: () -> Unit, query: (String) -> Unit, preview: (Decoration?) -> Unit, apply: (String) -> Unit,
    avatar: @Composable (String, Dp) -> Unit) {
    val colors = MiuixTheme.colorScheme
    val hint = colors.onSurfaceVariantSummary
    val catalog = state.catalog
    val selectedId = state.preview?.id ?: catalog?.current.orEmpty()
    val chosen = state.preview ?: catalog?.decorations?.firstOrNull { it.id == selectedId }
    val changed = catalog != null && selectedId != catalog.current
    val name = if (selectedId.isEmpty()) "Không đeo phụ kiện" else chosen?.label() ?: selectedId.removePrefix("key-")
    val focus = LocalFocusManager.current
    val grid = rememberLazyGridState()
    LaunchedEffect(state.query) { grid.scrollToItem(0) }
    MusicSubpage("Phụ kiện ảnh đại diện", "decoration_screen", onBack, backTag = "decoration_back", actions = {
        MusicTextAction(if (state.loading) "Đang cập nhật" else "Làm mới", reload, enabled = !busy && !state.loading)
    }) { topInset ->
    BoxWithConstraints(Modifier.fillMaxSize().padding(top = topInset)) {
        val compact = maxHeight < 480.dp
        Column(Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(bottom = LocalMusicBottomInset.current)) {
            if (catalog != null && !compact) {
                SettingsGroup(Modifier.fillMaxWidth().testTag("decoration_preview")) {
                    Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Box(Modifier.size(64.dp), Alignment.Center) {
                            Crossfade(selectedId, label = "decoration_preview") { id ->
                                Box(Modifier.fillMaxSize(), Alignment.Center) { avatar(id, 26.dp) }
                            }
                        }
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(name, Modifier.testTag("decoration_preview_name"), fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(if (changed) "Đang thử" else "Trang trí hiện tại", Modifier.weight(1f), fontSize = 11.sp,
                                    color = colors.primary, maxLines = 1)
                                if (!catalog.unlocked) Text("${catalog.listenMs.coerceAtLeast(0) / 60000} / ${catalog.minListenMs.coerceAtLeast(0) / 60000} phút để mở khóa",
                                    fontSize = 11.sp, color = hint)
                            }
                            if (!catalog.unlocked) {
                                val progress = if (catalog.minListenMs > 0) (catalog.listenMs.toFloat() / catalog.minListenMs).coerceIn(0f, 1f) else 0f
                                Box(Modifier.fillMaxWidth().height(3.dp).clip(CircleShape).background(colors.onSurface.copy(alpha = .06f))) {
                                    if (progress > 0f) Box(Modifier.fillMaxWidth(progress).fillMaxHeight().background(colors.primary))
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
            if (catalog != null) {
                MusicSearchBar(state.query, { focus.clearFocus() }, onInput = query, placeholder = "Tìm tên hoặc ID phụ kiện",
                    inputTag = "decoration_query", modifier = Modifier.testTag("decoration_search"))
                Row(Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(if (state.query.isBlank()) "Tất cả phụ kiện" else "Kết quả tìm kiếm", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    val count = catalog.decorations.count { it.name.contains(state.query.trim(), true) || it.id.contains(state.query.trim(), true) }
                    Text("$count món", fontSize = 12.sp, color = hint)
                }
            }
            when {
                catalog == null -> Column(Modifier.weight(1f).fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                    Text(if (state.loading) "Đang tải phụ kiện…" else state.error ?: "Không có phụ kiện khả dụng", fontSize = 14.sp, color = hint)
                    if (!state.loading) MusicTextAction("Tải lại", reload)
                }
                else -> {
                    val matches = catalog.decorations.filter { it.name.contains(state.query.trim(), true) || it.id.contains(state.query.trim(), true) }
                    state.error?.let { Text("Cập nhật thất bại, hãy làm mới để thử lại", fontSize = 12.sp, color = hint, modifier = Modifier.padding(bottom = 8.dp)) }
                    if (matches.isEmpty() && state.query.isNotBlank()) Column(Modifier.weight(1f).fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                        Text("Không tìm thấy phụ kiện phù hợp", fontSize = 15.sp)
                        MusicTextAction("Xóa tìm kiếm", { focus.clearFocus(); query("") })
                    } else LazyVerticalGrid(GridCells.Adaptive(92.dp), Modifier.weight(1f).testTag("decoration_grid"), state = grid,
                        contentPadding = PaddingValues(bottom = 12.dp), horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        if (state.query.isBlank()) item(key = "none") {
                            DecorationTile(Decoration("", "Không đeo"), selectedId.isEmpty(), catalog.current.isEmpty(), busy,
                                { focus.clearFocus(); preview(Decoration("", "Không đeo")) }, avatar)
                        }
                        items(matches, key = { "frame:${it.id}" }) { item ->
                            DecorationTile(item, selectedId == item.id, catalog.current == item.id, busy,
                                { focus.clearFocus(); preview(item) }, avatar)
                        }
                        if (matches.isEmpty()) item(span = { GridItemSpan(maxLineSpan) }) {
                            Text("Chưa có phụ kiện nào được mở bán", Modifier.padding(vertical = 20.dp), fontSize = 13.sp, color = hint)
                        }
                    }
                    Column(Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        message?.let { Text(it, fontSize = 12.sp, color = colors.primary,
                            modifier = Modifier.testTag("decoration_status")) }
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            MusicTextAction("Khôi phục", { preview(null) }, enabled = changed && !busy)
                            GlassActionButton(if (busy) "Đang lưu…" else if (!changed) "Trang trí hiện tại" else if (selectedId.isEmpty()) "Hủy đeo"
                                else if (!catalog.unlocked) "Nghe nhạc để mở khóa" else "Đeo phụ kiện này", { apply(selectedId) },
                                Modifier.weight(1f).testTag("wear_decoration"), primary = true,
                                enabled = !busy && changed && (selectedId.isEmpty() || catalog.unlocked))
                        }
                    }
                }
            }
        }
    }
    }
}

@Composable private fun DecorationTile(item: Decoration, selected: Boolean, worn: Boolean, busy: Boolean,
    onClick: () -> Unit, avatar: @Composable (String, Dp) -> Unit) {
    val colors = MiuixTheme.colorScheme
    val shape = RoundedCornerShape(16.dp)
    Column(Modifier.fillMaxWidth().clip(shape).background(if (selected) colors.primary.copy(alpha = .08f) else colors.surfaceContainer)
        .border(if (selected) 1.5.dp else 0.dp, if (selected) colors.primary.copy(alpha = .65f) else androidx.compose.ui.graphics.Color.Transparent, shape)
        .clickable(enabled = !busy, role = Role.RadioButton, onClick = onClick).semantics { this.selected = selected }
        .testTag("decoration_${item.id.ifEmpty { "none" }}").padding(horizontal = 6.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.fillMaxWidth().height(78.dp), Alignment.Center) {
            avatar(item.id, 30.dp)
            SettingsSelectionCheck(selected, Modifier.align(Alignment.TopEnd).size(18.dp))
        }
        Text(item.label(), fontSize = 12.sp, fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1, overflow = TextOverflow.Ellipsis)
        Text(if (worn) (if (item.id.isEmpty()) "Hiện tại" else "Đang đeo") else if (selected) "Đang thử" else "", fontSize = 10.sp, lineHeight = 14.sp,
            color = if (selected) colors.primary else colors.onSurfaceVariantSummary)
    }
}
