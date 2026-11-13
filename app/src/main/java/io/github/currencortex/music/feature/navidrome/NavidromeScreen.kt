package io.github.currencortex.music.feature.navidrome

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.media.PlayerMode
import io.github.currencortex.music.data.navidrome.NavidromeEntry
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.library.*
import io.github.currencortex.music.feature.style.*
import io.github.currencortex.music.ui.component.*
import kotlinx.coroutines.flow.distinctUntilChanged
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun NavidromeScreen(vm: NavidromeViewModel, library: LibraryViewModel, navigate: (String) -> Unit,
    play: (List<Song>, Int) -> Unit, root: Boolean = false, onBack: (() -> Unit)? = null) {
    val account by vm.account.collectAsStateWithLifecycle()
    val state by vm.state.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
    val query by vm.query.collectAsStateWithLifecycle()
    val message by vm.message.collectAsStateWithLifecycle()
    val error by vm.actionError.collectAsStateWithLifecycle()
    val queue by vm.container.playbackQueue.state.collectAsStateWithLifecycle()
    val playback by vm.container.playerController.state.collectAsStateWithLifecycle()
    val likes by vm.repository.likes.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val focus = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    var configuration by rememberSaveable { mutableStateOf(false) }
    var filterOpen by rememberSaveable(state.page.key) { mutableStateOf(false) }
    var filter by rememberSaveable(state.page.key) { mutableStateOf("") }
    var dialog by remember { mutableStateOf("") }
    var target by remember { mutableStateOf(NavidromePage()) }
    var removal by remember { mutableStateOf<NavidromeRemoval?>(null) }
    var name by remember { mutableStateOf("") }
    val settings = configuration || !account.configured
    val songDetail = state.page.kind in setOf("album", "playlist", "likes")
    val searchBar: @Composable (Modifier) -> Unit = { modifier ->
        MusicSearchBar(if (songDetail) filter else query, {
            focus.clearFocus(); keyboard?.hide()
            if (!songDetail) vm.search(query)
        }, modifier, onInput = { if (songDetail) filter = it else vm.query.value = it },
            placeholder = if (songDetail) "Tìm bài hát trong danh sách này" else "Tìm bài hát, album, nghệ sĩ",
            inputTag = if (songDetail) "navidrome_filter_input" else "navidrome_query", submitTag = "navidrome_search")
    }
    fun closeDialog() { if (!busy) { dialog = ""; vm.clearError() } }
    fun action(kind: String) { target = state.page; name = if (kind == "rename") target.title else ""; vm.clearError(); dialog = kind }
    fun back() { focus.clearFocus(); keyboard?.hide(); when {
        configuration -> { configuration = false; vm.clearError() }
        filterOpen -> { filterOpen = false; filter = "" }
        vm.nested -> vm.back()
        else -> onBack?.invoke()
    } }
    BackHandler(configuration || filterOpen || vm.nested) { back() }
    LaunchedEffect(message) { message?.let { vm.container.errorNotices.showMessage(it); vm.message.value = null } }
    val shownSongs = remember(state.songs, filter) {
        if (filter.isBlank()) state.songs else state.songs.filter { song ->
            listOf(song.name, song.artists, song.album).any { it.contains(filter.trim(), ignoreCase = true) }
        }
    }
    val browser: @Composable (androidx.compose.ui.unit.Dp) -> Unit = { topInset ->
    BoxWithConstraints(Modifier.fillMaxSize().padding(top = topInset), contentAlignment = Alignment.TopCenter) {
        val columns = if (maxWidth >= 760.dp) 4 else if (maxWidth >= 600.dp) 3 else 2
        val compact = maxWidth > maxHeight && maxHeight < 500.dp
        val showSearch = !settings && (!songDetail || filterOpen)
        Column(Modifier.widthIn(max = if(settings)760.dp else 840.dp).fillMaxSize()) {
            if (root) Row(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp).heightIn(min = 56.dp), verticalAlignment = Alignment.CenterVertically) {
                if (configuration || vm.nested) NavIcon(Icons.AutoMirrored.Filled.ArrowBack, "Quay lại thư viện", ::back, "navidrome_back")
                Column((if (compact && showSearch) Modifier.widthIn(max = 200.dp) else Modifier.weight(1f)).padding(horizontal = 8.dp)) {
                    Text(if (settings) "Tài khoản Navidrome" else if (vm.nested) when (state.page.kind) {
                        "album" -> "Album"; "artist" -> "Nghệ sĩ"; "playlist" -> "Danh sách phát"; else -> state.page.title
                    } else "Thư viện của tôi", fontSize = if (compact) 20.sp else 24.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    if (!settings && !vm.nested && !compact) NavHint("${account.username} · Navidrome")
                }
                if (compact && showSearch) searchBar(Modifier.weight(1f).padding(horizontal = 12.dp))
                if (!settings && songDetail) NavIcon(Icons.Default.Search, "Tìm trong danh sách hiện tại", { filterOpen = !filterOpen; if (!filterOpen) filter = "" }, "navidrome_filter")
                if (!settings) {
                    NavIcon(Icons.Default.Settings, "Tài khoản thư viện", { configuration = true; vm.clearError() }, "navidrome_account")
                    NavIcon(Icons.Default.MoreVert, "Thao tác thư viện", { dialog = "menu"; vm.clearError() }, "navidrome_menu")
                }
            }
            if (settings) {
                NavidromeAccountPage(vm, onConnected = { configuration = false }, onDisconnect = { action("disconnect") })
            } else {
                if (showSearch && (!compact || !root)) searchBar(Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                if (!vm.nested) MusicCategoryTabs(listOf("albums" to "Album", "artists" to "Nghệ sĩ", "playlists" to "Danh sách phát", "likes" to "Yêu thích"),
                    state.page.kind, { focus.clearFocus(); keyboard?.hide(); vm.tab(it) }, "navidrome_tab", Modifier.padding(horizontal = 16.dp))
                val positionKey = "${if (root) "home" else "route"}:${account.revision}:${state.page.key}"
                val grid = remember(positionKey) { vm.position(positionKey).let { LazyGridState(it.index, it.offset) } }
                DisposableEffect(grid, positionKey) { onDispose { vm.savePosition(positionKey, grid.firstVisibleItemIndex, grid.firstVisibleItemScrollOffset) } }
                val latest by rememberUpdatedState(state)
                LaunchedEffect(grid, positionKey) {
                    snapshotFlow { val value = latest; val info = grid.layoutInfo
                        value.more && !value.loading && value.error == null && info.totalItemsCount > 0 &&
                            (info.visibleItemsInfo.lastOrNull()?.index ?: -1) >= info.totalItemsCount - 3
                    }.distinctUntilChanged().collect { if (it) vm.refresh(true) }
                }
                val motion = rememberPlaylistPullMotion(state.loading) { vm.refresh() }
                val entriesAsRows = state.page.kind in setOf("artists", "playlists")
                val songKeys = remember(shownSongs) { playlistSongKeys(shownSongs) }
                val canPlay = playback.mode == PlayerMode.LOCAL
                Box(Modifier.weight(1f).clipToBounds().nestedScroll(motion)) {
                    LazyVerticalGrid(GridCells.Fixed(columns), Modifier.fillMaxSize().playlistPullSurface(motion).testTag("navidrome_grid"), state = grid,
                        contentPadding = musicScrollPadding(PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp)),
                        horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(if (entriesAsRows) 4.dp else 12.dp)) {
                        if (vm.nested && state.page.kind != "search") item("hero", span = { GridItemSpan(maxLineSpan) }) { NavDetailHero(state) }
                        if (state.page.kind == "playlists") item("create", span = { GridItemSpan(maxLineSpan) }) {
                            MusicSectionHeader("${state.entries.size} danh sách phát", "Tạo danh sách phát", { action("create") })
                        }
                        if (state.error != null || error != null && dialog.isEmpty()) item("error", span = { GridItemSpan(maxLineSpan) }) {
                            Column { Text(state.error ?: error.orEmpty(), color = MiuixTheme.colorScheme.error, fontSize = 13.sp)
                                MusicTextAction(if (state.error != null) "Thử lại" else "Đóng", { if (state.error != null) vm.refresh() else vm.clearError() }) }
                        }
                        if (shownSongs.isNotEmpty()) item("play", span = { GridItemSpan(maxLineSpan) }) {
                            Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                                MusicTransportButton("Phát tất cả", { play(shownSongs, 0) }, Modifier.testTag("navidrome_play_all"), prominent = true, enabled = canPlay)
                                Column(Modifier.weight(1f).padding(start = 14.dp)) { Text("Phát tất cả", fontSize = 20.sp, fontWeight = FontWeight.Medium); NavHint(playlistDurationLabel(shownSongs)) }
                                MusicTextAction("Phát ngẫu nhiên", { play(shownSongs.shuffled(), 0) }, enabled = canPlay)
                            }
                        }
                        itemsIndexed(shownSongs, key = { index, _ -> "song-${songKeys[index]}" }, span = { _, _ -> GridItemSpan(maxLineSpan) }) { index, song ->
                            Box(Modifier.animateItem(fadeInSpec = tween(220), placementSpec = tween(260), fadeOutSpec = tween(150))) {
                                PlaylistTrack(song, queue.current?.id == song.id, likes[song.id] == true,
                                    { play(shownSongs, index) }, { vm.enqueue(song, true) }, { vm.enqueue(song) }) {
                                    LibrarySongActions(library, song, navigate)
                                    if (state.page.kind == "playlist" && state.owned) {
                                        val dismiss = LocalDismissSongMenu.current
                                        MusicDestinationRow("Xóa khỏi danh sách phát", { dismiss()
                                            // Map the visible occurrence back to the complete, unfiltered playlist.
                                            val occurrence = shownSongs.take(index).count { it.id == song.id }
                                            val rawIndex = state.songs.indices.filter { state.songs[it].id == song.id }.getOrNull(occurrence)
                                            if (rawIndex != null) { removal = vm.removal(song, rawIndex); action("remove") }
                                        }, enabled = !busy, chevron = false)
                                    }
                                }
                            }
                        }
                        if (state.artists.isNotEmpty()) item("artists-heading", span = { GridItemSpan(maxLineSpan) }) { MusicSectionHeader("Nghệ sĩ") }
                        items(state.artists, key = { "search-artist-${it.id}" }, span = { GridItemSpan(maxLineSpan) }) { NavEntryRow(it) { vm.open("artist", it) } }
                        if (state.albums.isNotEmpty()) item("albums-heading", span = { GridItemSpan(maxLineSpan) }) { MusicSectionHeader("Album") }
                        items(state.entries, key = { "entry-${it.id}" }, span = { GridItemSpan(if (entriesAsRows) maxLineSpan else 1) }) { entry ->
                            val open = { vm.open(when (state.page.kind) { "artists" -> "artist"; "playlists" -> "playlist"; else -> "album" }, entry) }
                            Box(Modifier.animateItem(fadeInSpec = tween(220), placementSpec = tween(260))) {
                                if (entriesAsRows) NavEntryRow(entry, open) else NavAlbumCard(entry, open)
                            }
                        }
                        items(state.albums, key = { "search-album-${it.id}" }) { entry -> NavAlbumCard(entry) { vm.open("album", entry) } }
                        if (!state.loaded && state.loading) {
                            if (state.page.kind in setOf("albums", "artist")) items(4, key = { "placeholder-$it" }) {
                                LoadingCoverTile(cornerRadius = 14.dp, titleGap = 7.dp)
                            } else item("loading", span = { GridItemSpan(maxLineSpan) }) { LoadingSongList(5) }
                        }
                        if (!state.loading && state.error == null && state.entries.isEmpty() && shownSongs.isEmpty() && state.artists.isEmpty() && state.albums.isEmpty()) item("empty", span = { GridItemSpan(maxLineSpan) }) {
                            Column(Modifier.fillMaxWidth().padding(vertical = 40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(when { filter.isNotBlank() -> "Không có bài hát phù hợp"; state.page.kind == "search" -> "Không tìm thấy nhạc phù hợp"; state.page.kind == "likes" -> "Bạn chưa thích bài hát nào"; state.page.kind == "playlists" -> "Tạo danh sách phát đầu tiên"; else -> "Chưa có nhạc ở đây" }, fontSize = 18.sp)
                                NavHint(when (state.page.kind) { "likes" -> "Các bài hát được yêu thích từ menu bài hát sẽ đồng bộ với máy chủ"; "playlist" -> "Thêm nhạc yêu thích từ menu bài hát"; else -> "Thử danh mục khác hoặc kéo xuống để tải lại" })
                                if (filter.isNotBlank()) MusicTextAction("Xóa tìm kiếm", { filter = "" })
                                else if (state.page.kind == "playlists") MusicTextAction("Tạo danh sách phát", { action("create") })
                            }
                        }
                        if (state.more) item("more", span = { GridItemSpan(maxLineSpan) }) {
                            TextButton(if (state.loading) "Đang tải thêm…" else "Tải thêm", { vm.refresh(true) }, enabled = !state.loading, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
    }
    if (root) browser(0.dp)
    else MusicSubpage(if (settings) "Tài khoản Navidrome" else if (vm.nested) state.page.title else "Thư viện của tôi",
        "navidrome_screen", ::back, backTag = "navidrome_back", actions = {
            if (!settings) {
                if (songDetail) NavIcon(Icons.Default.Search, "Tìm trong danh sách hiện tại", {
                    filterOpen = !filterOpen; if (!filterOpen) filter = ""
                }, "navidrome_filter")
                NavIcon(Icons.Default.Settings, "Tài khoản thư viện nhạc", { configuration = true; vm.clearError() }, "navidrome_account")
                NavIcon(Icons.Default.MoreVert, "Thao tác thư viện nhạc", { dialog = "menu"; vm.clearError() }, "navidrome_menu")
            }
        }) { topInset ->
        if (settings) NavidromeAccountPage(vm, onConnected = { configuration = false },
            onDisconnect = { action("disconnect") }, topInset = topInset)
        else browser(topInset)
    }
    SettingsUiScope {
    if (dialog == "menu") MusicChoiceDialog("Thao tác thư viện nhạc", { dialog = "" }) {
        MusicDestinationRow("Làm mới thư viện nhạc", { dialog = ""; vm.refresh() }, enabled = !state.loading, chevron = false)
        if (state.page.kind == "playlists") MusicDestinationRow("Tạo danh sách phát", { action("create") }, enabled = !busy, chevron = false)
        if (state.page.kind == "playlist" && state.owned) {
            MusicDestinationRow("Đổi tên danh sách phát", { action("rename") }, enabled = !busy, chevron = false)
            MusicDestinationRow("Xóa danh sách phát", { action("delete") }, enabled = !busy, chevron = false)
        }
    } else if (dialog.isNotEmpty()) MusicDialog(when (dialog) { "disconnect" -> "Ngắt kết nối"; "delete" -> "Xóa danh sách phát"; "remove" -> "Xóa khỏi danh sách phát"; "rename" -> "Đổi tên danh sách phát"; else -> "Tạo danh sách phát" }, ::closeDialog) {
        if (dialog in setOf("create", "rename")) TextField(name, { name = it }, label = "Tên danh sách phát", singleLine = true, enabled = !busy, modifier = Modifier.fillMaxWidth().then(Modifier.testTag("navidrome_playlist_name")))
        else Text(when (dialog) { "disconnect" -> "Ngắt kết nối tài khoản máy chủ này, các dịch vụ nhạc khác và hàng đợi phát sẽ được giữ nguyên."; "remove" -> "Xóa「${removal?.song?.name.orEmpty()}」khỏi「${removal?.playlist?.title.orEmpty()}」?"; else -> "Xóa「${target.title}」? Các tệp nhạc trên máy chủ sẽ được giữ nguyên." })
        error?.let { Text(it, color = MiuixTheme.colorScheme.error, fontSize = 13.sp) }
        MusicDialogActions(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MusicDialogButton("Hủy", ::closeDialog, enabled = !busy, modifier = Modifier.weight(1f))
            MusicDialogButton(if (busy) "Đang xử lý…" else "Xác nhận", {
                val done: (Boolean) -> Unit = { if (it) dialog = "" }
                when (dialog) { "disconnect" -> vm.disconnect { if (it) { configuration = false; dialog = "" } }; "create" -> vm.create(name, done); "rename" -> vm.rename(target, name, done); "delete" -> vm.delete(target, done); "remove" -> removal?.let { vm.remove(it, done) } }
            }, enabled = !busy && (dialog !in setOf("create", "rename") || name.isNotBlank()), modifier = Modifier.weight(1f).testTag("navidrome_confirm"))
        }
    }
    }
}

@Composable private fun NavIcon(icon: ImageVector, description: String, click: () -> Unit, tag: String) {
    GlassIconButton(click, description, Modifier.testTag(tag)) { Icon(icon, null, Modifier.size(22.dp)) }
}
@Composable internal fun NavHint(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier, fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f), maxLines = 2, overflow = TextOverflow.Ellipsis)
}
@Composable private fun NavEntryRow(entry: NavidromeEntry, open: () -> Unit) {
    Row(Modifier.elasticRootComponent().fillMaxWidth().clip(RoundedCornerShape(14.dp)).clickable(role = Role.Button, onClick = open).testTag("navidrome_entry_${entry.id}").padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        if (entry.cover.isNotBlank()) MusicCover(entry.cover, Modifier.size(52.dp), cornerRadius = 12.dp)
        else Box(Modifier.size(52.dp).background(MiuixTheme.colorScheme.surfaceContainer, RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) { Text(entry.name.take(1), fontSize = 22.sp, color = MiuixTheme.colorScheme.primary) }
        Column(Modifier.weight(1f).padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(entry.name, fontSize = 16.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
             val summary = listOf(entry.subtitle, if (entry.count > 0) "${entry.count} bài hát" else "", if (entry.owned) "Danh sách phát của tôi" else "").filter(String::isNotBlank).joinToString(" · ")
            if (summary.isNotEmpty()) NavHint(summary)
        }
        Text("›", fontSize = 22.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .35f))
    }
}
@Composable private fun NavAlbumCard(entry: NavidromeEntry, open: () -> Unit) {
    Column(Modifier.elasticRootComponent().fillMaxWidth().clip(RoundedCornerShape(14.dp)).clickable(role = Role.Button, onClick = open).testTag("navidrome_entry_${entry.id}"), verticalArrangement = Arrangement.spacedBy(7.dp)) {
        MusicCover(entry.cover, Modifier.fillMaxWidth().aspectRatio(1f), pixels = 480, cornerRadius = 14.dp)
        Text(entry.name, Modifier.heightIn(min = 40.dp), maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 15.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium)
        NavHint(entry.subtitle, Modifier.padding(bottom = 8.dp))
    }
}
@Composable private fun NavDetailHero(state: NavidromeUi) {
    val cover = state.page.cover.ifBlank { state.songs.firstOrNull()?.cover ?: state.entries.firstOrNull()?.cover.orEmpty() }
    val tint = rememberStyleTint(cover)
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(24.dp)).background(styleSurface(tint.value)).padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
         StyleArtwork(cover, Modifier.size(104.dp).clip(RoundedCornerShape(16.dp)), tint, contentDescription = "Ảnh bìa ${state.page.title}")
        Column(Modifier.weight(1f).padding(start = 18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(state.page.title, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, maxLines = 3, overflow = TextOverflow.Ellipsis)
            val summary = state.page.subtitle.ifBlank { state.songs.firstOrNull()?.artists.orEmpty() }
            if (summary.isNotBlank()) NavHint(summary)
             if (state.loaded) NavHint(if (state.page.kind == "artist") "${state.entries.size} album" else playlistDurationLabel(state.songs))
        }
    }
}
