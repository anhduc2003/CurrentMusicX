package io.github.currencortex.music.feature.library

import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import io.github.currencortex.music.data.library.Playlist
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star

import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.R
import io.github.currencortex.music.core.media.PlaybackMode
import io.github.currencortex.music.core.media.PlayerMode
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.style.StyleArtwork
import io.github.currencortex.music.feature.style.rememberStyleTint
import io.github.currencortex.music.ui.component.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Cover-first library entrance; the existing create dialog and server ownership stay in the host. */
@Composable internal fun PlaylistIndexBody(vm: LibraryViewModel,
    navigate: (String) -> Unit, play: (List<Song>, Int) -> Unit,
    onBack: (() -> Unit)? = null, onCreate: () -> Unit) {
    val home by vm.home.collectAsStateWithLifecycle()
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    val nativeAccount by vm.container.neteaseSessions.state.collectAsStateWithLifecycle()
    var playlistPreview by remember { mutableStateOf<Pair<Playlist, Rect>?>(null) }
    val playlistPlayback = rememberPlaylistPreviewPlayback(vm, play)
    var category by rememberSaveable { mutableStateOf(PlaylistCategory.ALL) }
    val playlists = remember(home.playlists, category, account.account?.id) {
        home.playlists.filter { category.includes(it, account.account?.id ?: 0) }
    }
    MusicSubpage("Danh sách phát của tôi", "playlist_index_page", onBack, actions = {
        if (account.account != null || (nativeAccount.loggedIn && !nativeAccount.stale))
            GlassIconButton(onCreate, "Tạo danh sách phát", Modifier.testTag("create_playlist")) { Icon(Icons.Default.Add, null) }
    }) { topInset ->
    MusicPullToRefresh(home.loading, vm::refresh, Modifier.fillMaxSize(), showIndicator = false) {
        LazyVerticalGrid(GridCells.Adaptive(148.dp), Modifier.fillMaxSize().testTag("playlist_index"),
            contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp)),
            horizontalArrangement = Arrangement.spacedBy(16.dp), verticalArrangement = Arrangement.spacedBy(24.dp)) {
            item(key = "categories", span = { GridItemSpan(maxLineSpan) }) {
                SettingsSegmentedControl(PlaylistCategory.entries.map { it.label }, category.ordinal,
                    { category = PlaylistCategory.entries[it] }, tag = "playlist_category")
            }
            item(key = "count", span = { GridItemSpan(maxLineSpan) }) { SettingsHint("${playlists.size} danh sách phát · ${category.label}") }
            home.errors["Danh sách phát của tôi"]?.let { error -> item(span = { GridItemSpan(maxLineSpan) }) {
                Column { Text(error); TextButton("Thử lại", onClick = vm::refresh) }
            } }
            if ((home.loading || account.loading) && home.playlists.isEmpty()) items(4) {
                LoadingCoverTile()
            }
            if (!home.loading && !account.loading && home.playlists.isEmpty() && home.errors["Danh sách phát của tôi"] == null)
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(Modifier.fillMaxWidth().padding(vertical = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(MusicIcons.List, null, Modifier.size(40.dp), tint = MiuixTheme.colorScheme.primary.copy(alpha = .6f))
                        Text(if (account.account == null) "Đăng nhập để xem danh sách phát của bạn" else "Tạo danh sách phát cho những bản nhạc yêu thích", Modifier.padding(top = 16.dp), fontSize = 17.sp)
                        Text(if (account.account == null) "Liên kết tài khoản Nhạc Vân NetEase để đồng bộ nhạc đã lưu" else "Nhấn + ở góc trên bên phải để bắt đầu lưu", Modifier.padding(top = 8.dp),
                            fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                    }
                }
            if (!home.loading && home.playlists.isNotEmpty() && playlists.isEmpty() && home.errors["Danh sách phát của tôi"] == null)
                item(span = { GridItemSpan(maxLineSpan) }) { SettingsHint("Chưa có danh sách phát ${category.label}") }
            items(playlists, key = { "${it.source}-${it.id}" }) { playlist ->
                var coverBounds by remember { mutableStateOf(Rect.Zero) }
                Column(Modifier.testTag("playlist_card_${playlist.id}").combinedClickable(role = Role.Button,
                    onClick = { navigate("lib/${if (playlist.source == "netease") "ncmplaylist" else "playlist"}/${playlist.id}") },
                    onLongClickLabel = "Xem trước danh sách phát và thao tác",
                    onLongClick = { if (!coverBounds.isEmpty) playlistPreview = playlist to coverBounds })) {
                    MusicCover(playlist.cover.ifBlank { playlist.songs.firstOrNull()?.cover.orEmpty() },
                        Modifier.fillMaxWidth().aspectRatio(1f).onGloballyPositioned { coordinates ->
                            coverBounds = Rect(coordinates.localToWindow(Offset.Zero),
                                coordinates.localToWindow(Offset(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())))
                        }, 480, cornerRadius = 18.dp)
                    Text(playlist.name, Modifier.padding(top = 10.dp), fontSize = 16.sp, fontWeight = FontWeight.Medium,
                        maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Text("${playlist.count.takeIf { it > 0 } ?: playlist.songs.size} bài · ${if (playlist.source in setOf("ncm", "netease")) "Nhạc Vân NetEase" else "CurrentMusic"}",
                        Modifier.padding(top = 4.dp), fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                }
            }
        }
    }
    }
    playlistPreview?.let { (playlist, bounds) ->
        PlaylistArtworkPreview(vm, playlist, bounds, canPlay = playlistPlayback.enabled,
            onPlay = { shuffle -> playlistPlayback.play(playlist, shuffle) },
            onOpen = { navigate("lib/${if (playlist.source == "netease") "ncmplaylist" else "playlist"}/${playlist.id}") },
            onDismiss = { playlistPreview = null })
    }
}

/** Routes whose playlist overlay is actually rendered; empty lists keep the flag but show no overlay. */
object PlaylistOverlayVisibility {
    val visible = mutableStateMapOf<String, Boolean>()
}

internal data class LocalMusicPlaylistActions(val allowed: Boolean, val rescan: () -> Unit,
    val requestPermission: () -> Unit, val openSettings: () -> Unit, val permissionMessage: String? = null,
    val chooseScope: () -> Unit = requestPermission)

@Composable internal fun PlaylistDetailBody(state: LibraryDetailState, vm: LibraryDetailViewModel,
    library: LibraryViewModel, onBack: () -> Unit, navigate: (String) -> Unit,
    play: (List<Song>, Int) -> Unit, onRename: () -> Unit, onDelete: () -> Unit,
    localMusic: LocalMusicPlaylistActions? = null) {
    fun refresh() {
        if (localMusic == null) vm.reload(force = true)
        else if (localMusic.allowed) localMusic.rescan() else localMusic.requestPermission()
    }
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    val statuses by library.statuses.collectAsStateWithLifecycle()
    val likesUseNetease by vm.container.primaryLibrary.likesUseNetease.collectAsStateWithLifecycle()
    val neteaseMain by vm.container.primaryLibrary.usesNetease.collectAsStateWithLifecycle()
    val neteaseLikes by vm.container.neteaseSongActions.likedState.collectAsStateWithLifecycle()
    val navidromeLikes by vm.container.navidrome.likes.collectAsStateWithLifecycle()
    val likeBusy by library.primaryLikeBusy.collectAsStateWithLifecycle()
    val batch by library.batchFavorites.collectAsStateWithLifecycle()
    val musicSettings by vm.container.musicSettings.state.collectAsStateWithLifecycle()
    val binding by vm.container.bindingRepository.state.collectAsStateWithLifecycle()
    val selectionAllowed = localMusic == null && playlistSupportsMultiSelect(vm.route)
    var selecting by remember(vm.route, account.account?.id, binding?.profile?.uid, musicSettings.server, musicSettings.likeMode) { mutableStateOf(false) }
    var selectedKeys by remember(vm.route, account.account?.id, binding?.profile?.uid, musicSettings.server, musicSettings.likeMode) { mutableStateOf(emptySet<String>()) }
    fun exitSelection() { selecting = false; selectedKeys = emptySet() }
    BackHandler(selecting) { if (!batch.running) exitSelection() }
    LaunchedEffect(state.songs) {
        val available = state.songs.filter(::supportsBatchFavorite).map(::favoriteSelectionKey).toSet()
        selectedKeys = selectedKeys.intersect(available)
    }
    val playback = vm.container.playerController.state.collectAsStateWithLifecycle()
    val queue = vm.container.playbackQueue.state.collectAsStateWithLifecycle()
    val playbackMode by remember(playback) { derivedStateOf(structuralEqualityPolicy()) { playback.value.mode } }
    val currentSong by remember(queue) { derivedStateOf(structuralEqualityPolicy()) { queue.value.current } }
    val pausedWithProgress by remember(playback, queue) { derivedStateOf(structuralEqualityPolicy()) {
        !playback.value.showPause && (queue.value.positionMs > 0 || playback.value.positionMs > 0)
    } }
    val busy by library.busy.collectAsStateWithLifecycle()
    val importState by vm.container.playlistLikeImporter.state.collectAsStateWithLifecycle()
    var importOpen by rememberSaveable(vm.route) { mutableStateOf(false) }
    val appearance by vm.container.settings.state.collectAsStateWithLifecycle()
    val room = LocalRoomSongRequest.current
    val editable = vm.editable(state.playlist, account.account?.id ?: 0)
    val removable = vm.removable(state.playlist, account.account?.id ?: 0)
    val subscribable = vm.subscribable(state.playlist)
    val subscribed = state.playlist?.nativeSubscribed == true
    fun subscribe() { library.action("Đã lưu danh sách phát") { vm.subscribe() } }
    val sharedMotion = LocalPlaylistContainerMotion.current
    val cover = remember(state.cover, state.songs, sharedMotion) {
        state.cover.ifBlank { sharedMotion?.cover.orEmpty() }
            .ifBlank { state.songs.firstOrNull { it.cover.isNotBlank() }?.cover.orEmpty() }
    }
    val tint = rememberStyleTint(cover)
    val surface = MiuixTheme.colorScheme.background
    val list = rememberLazyListState()
    var heroHeightPx by remember { mutableIntStateOf(0) }
    val pullMotion = rememberPlaylistPullMotion(state.loading || state.refreshing, ::refresh)
    val scope = rememberCoroutineScope()
    var searching by rememberSaveable(vm.route) { mutableStateOf(false) }
    var query by rememberSaveable(vm.route) { mutableStateOf("") }
    var order by rememberSaveable(vm.route) { mutableIntStateOf(0) }
    var sortOpen by rememberSaveable(vm.route) { mutableStateOf(false) }
    var menuOpen by rememberSaveable(vm.route) { mutableStateOf(false) }
    var infoOpen by rememberSaveable(vm.route) { mutableStateOf(false) }
    // Reserve result scroll range without inserting an editor into the song list.
    val searchClearance = with(LocalDensity.current) { if (searching && query.isNotBlank()) heroHeightPx.toDp() else 0.dp }
    val songs = remember(state.songs, query, order) {
        val filtered = if (query.isBlank()) state.songs else {
            val needle = MusicSearchQuery(query)
            state.songs.filter { needle.matches(it.name, it.artists, it.album) }
        }
        if (order == 1 || order == 2) {
            val collator = java.text.Collator.getInstance(java.util.Locale.SIMPLIFIED_CHINESE).apply {
                strength = java.text.Collator.PRIMARY
            }
            if (order == 1) filtered.sortedWith { left, right -> collator.compare(left.name, right.name) }
            else filtered.sortedWith { left, right -> collator.compare(left.artists, right.artists) }
        } else {
            filtered
        }
    }
    val canPlay = room == null && playbackMode == PlayerMode.LOCAL
    // A shelf is another view of the current list, including its order and filter.
    // Keep the empty-result view reachable so its close action can return to that filter.
    if (appearance.playlistRecordShelf && state.songs.isNotEmpty()) {
        DisposableEffect(vm.route) {
            PlaylistOverlayVisibility.visible[vm.route] = true
            onDispose { PlaylistOverlayVisibility.visible.remove(vm.route) }
        }
        val previewContext = androidx.compose.ui.platform.LocalContext.current
        val previewLifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current
        val preview = remember(vm.container) { RecordShelfPreview(previewContext, vm.container) }
        val previewPageActive = io.github.currencortex.music.ui.component.LocalMusicPageActive.current
        val previewAllowed by rememberUpdatedState(canPlay && previewPageActive)
        DisposableEffect(preview, previewLifecycle) {
            val observer = androidx.lifecycle.LifecycleEventObserver { _, _ ->
                preview.setActive(previewAllowed && previewLifecycle.lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.RESUMED))
            }
            previewLifecycle.lifecycle.addObserver(observer)
            onDispose { previewLifecycle.lifecycle.removeObserver(observer); preview.close() }
        }
        SideEffect {
            preview.setActive(previewAllowed && previewLifecycle.lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.RESUMED))
            preview.setFullLength(appearance.recordShelfFullPreview)
            preview.setMode(appearance.recordShelfPreviewMode)
        }
        LaunchedEffect(songs, likesUseNetease) {
            library.refreshSearchLikes(songs.filter { !it.privateLibrary })
        }
        PlaylistRecordShelf(songs, canPlay, onPlay = { index -> preview.commit { play(songs, index) } },
            collectionKey = vm.route,
            onPreview = preview::select,
            previewMode = appearance.recordShelfPreviewMode,
            fullPreview = appearance.recordShelfFullPreview,
            onFullPreviewChange = { full -> scope.launch { vm.container.settings.edit { it.copy(recordShelfFullPreview = full) } } },
            onPreviewModeChange = { mode -> scope.launch { vm.container.settings.edit { it.copy(recordShelfPreviewMode = mode) } } },
            nextPreviewRequests = preview.nextRequests,
            isSaved = { song -> when {
                song.navidrome != null -> navidromeLikes[song.id] == true
                likesUseNetease -> io.github.currencortex.music.data.song.NeteaseSongActionsRepository.songId(song) in neteaseLikes
                else -> statuses[song.id]?.liked == true
            } },
            onSave = if (localMusic != null) null else { song -> library.togglePrimaryLike(song, undoable = true) }, saveEnabled = !likeBusy,
            onClose = { scope.launch { vm.container.settings.edit { it.copy(playlistRecordShelf = false) } } })
        return
    }
    if (appearance.playlistDepthRibbon && songs.isNotEmpty()) {
        DisposableEffect(vm.route) {
            PlaylistOverlayVisibility.visible[vm.route] = true
            onDispose { PlaylistOverlayVisibility.visible.remove(vm.route) }
        }
        PlaylistDepthRibbon(songs, canPlay, onPlay = { index -> play(songs, index) },
            onPrepare = { index -> val job = vm.preloadForPlayback(songs[index]); { job.cancel() } },
            onClose = { scope.launch { vm.container.settings.edit { it.copy(playlistDepthRibbon = false) } } })
        return
    }
    val songKeys = remember(songs) { playlistSongKeys(songs) }
    val locateRequests = LocalCurrentSongLocateRequests.current
    val pageActive = LocalMusicPageActive.current
    LaunchedEffect(locateRequests, pageActive, songs, currentSong) {
        if (pageActive) locateRequests?.collectLatest {
            val index = playlistCurrentSongIndex(songs, currentSong)
            if (index < 0) {
                vm.container.errorNotices.show("Bài hát hiện tại không có trong danh sách này")
                return@collectLatest
            }
            // Track rows form the tail of the list. Include every optional header.
            val headers = (list.layoutInfo.totalItemsCount - songs.size).coerceAtLeast(0)
            list.locatePlaylistSong(headers + index)
        }
    }
    val resume = if (canPlay && pausedWithProgress) remember(state.songs, currentSong) {
        currentSong?.takeIf { current -> state.songs.any { it.id == current.id && it.musicSource == current.musicSource } }
    } else null
    val albumPage = vm.route.startsWith("lib/album/")
    val collectionKind = if (albumPage) "Album" else "Danh sách phát"
    val title = state.title.ifBlank { sharedMotion?.title.orEmpty() }
        .ifBlank { if (state.loading) "Đang tải$collectionKind" else collectionKind }
    val origin = when {
        albumPage -> state.description.lineSequence().firstOrNull { it.isNotBlank() } ?: "Album"
        localMusic != null -> "Thư viện nhạc cục bộ · ${state.songs.size} bài hát"
        state.playlist?.source == "ncm" -> "Nhạc Vân NetEase · Danh sách phát đồng bộ chỉ đọc"
        state.playlist?.source == "netease" -> "Nhạc Vân NetEase"
        editable -> account.account?.nickname.orEmpty().ifBlank { "Danh sách phát của tôi" }
        vm.route == "lib/likes" -> "CurrentMusic · Nhạc tôi thích"
        vm.route == "lib/daily" -> "Đề xuất hằng ngày cho bạn"
        vm.route == "lib/foryou" -> "Đề xuất theo sở thích âm nhạc của bạn"
        vm.route == "lib/recent" -> "Nhạc đã nghe gần đây"
        else -> "CurrentMusic playlist"
    }
    val heroOrigin = if (state.playlist?.source == "ncm") "Nhạc Vân NetEase" else origin
    val heroDescription = (if (albumPage) state.description.substringAfter('\n', "") else state.description).trim().takeUnless {
        it == "Đến từ Nhạc Vân NetEase" || it == heroOrigin
    }.orEmpty()
    fun start(shuffle: Boolean) {
        if (songs.isEmpty() || !canPlay) return
        vm.container.playerController.setMode(if (shuffle) PlaybackMode.SHUFFLE else PlaybackMode.LIST)
        play(songs, if (shuffle) songs.indices.random() else 0)
    }
    val context = LocalContext.current
    // Opening only transforms the toolbar. Reveal results when filtering starts.
    LaunchedEffect(searching && query.isNotBlank()) {
        if (searching && query.isNotBlank()) list.animateScrollToItem(1)
    }
    fun share() {
        val text = buildString {
            appendLine("《$title》 · ${state.songs.size} bài hát")
            state.songs.take(12).forEach { appendLine("${it.name}${it.artists.takeIf(String::isNotBlank)?.let { artist -> " — $artist" }.orEmpty()}") }
            append("Đến từ CurrentMusic")
        }
        runCatching { context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, text), "Chia sẻ$collectionKind")) }.onFailure { library.container.errorNotices.show("Tạm thời không thể mở mục chia sẻ") }
    }
    PlaylistPageLayout(cover, list, heroHeightPx,
        if (localMusic != null) "local_music_screen" else "library_detail", toolbar = { progress ->
            GlassSearchToolbar(title, ((progress - .35f) / .65f).coerceIn(0f, 1f),
                { if (selecting) { if (!batch.running) exitSelection() } else onBack() },
                searching, { searching = it }, query, { query = it },
                action = GlassToolbarAction(MusicIcons.Image, "Kệ đĩa CD", {
                    scope.launch { vm.container.settings.edit { it.copy(playlistRecordShelf = true, playlistDepthRibbon = false) } }
                }, "playlist_record_shelf_toggle"),
                trailing = GlassToolbarAction(Icons.Default.MoreVert, "Thao tác playlist", { menuOpen = true }, "playlist_more"),
                modifier = Modifier.testTag("playlist_top_bar"), inputTag = "playlist_query", searchTag = "playlist_search_toggle",
                placeholder = "Tìm kiếm$collectionKind")
        }) { topInset ->
        // Track rows start below the artwork even while the hero itself is visible.
        // The hero and toolbar keep their own live sources.
        val trackBackdrop = rememberSolidGlassBackdrop(LocalGlassBackdrop.current, surface)
        Box(Modifier.fillMaxSize().testTag("playlist_refresh")) {
            LazyColumn(Modifier.fillMaxSize().clipToBounds().nestedScroll(pullMotion).testTag("playlist_tracks"),
                state = list, overscrollEffect = null,
                contentPadding = musicScrollPadding(PaddingValues(top = topInset + 8.dp, bottom = 32.dp + searchClearance))) {
                item(key = "hero") {
                    Column(Modifier.fillMaxWidth().onSizeChanged { heroHeightPx = it.height }
                        .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 24.dp).testTag("playlist_hero")) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                            StyleArtwork(cover, Modifier.size(128.dp).then(playlistMotionElement(sharedMotion, 0, destination = true))
                                .clip(RoundedCornerShape(16.dp)).testTag("playlist_cover"), tint,
                                pixels = 420, pending = state.loading && cover.isBlank(), contentDescription = "Ảnh bìa playlist",
                                onArtworkReady = { sharedMotion?.artworkReady = it })
                            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(title, modifier = playlistMotionElement(sharedMotion, 1, destination = true),
                                    fontSize = 22.sp, lineHeight = 28.sp, fontWeight = FontWeight.SemiBold, color = Color.White,
                                    maxLines = 3, overflow = TextOverflow.Ellipsis)
                                Text(heroOrigin, fontSize = 12.sp, color = Color.White.copy(alpha = .72f), maxLines = 2)
                                if (heroDescription.isNotBlank()) Text(heroDescription,
                                    Modifier.clickable { infoOpen = true }, fontSize = 12.sp, color = Color.White.copy(alpha = .72f),
                                    maxLines = 2, overflow = TextOverflow.Ellipsis)
                            }
                        }
                        Row(Modifier.fillMaxWidth().padding(top = 18.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            if (localMusic != null) PlaylistPill("Phạm vi quét", Icons.Default.Search,
                                localMusic.chooseScope, Modifier.weight(1f).testTag("local_music_scope"))
                            else if (subscribable) PlaylistPill(if (subscribed) "Đã lưu" else "Lưu playlist",
                                if (subscribed) Icons.Default.Star else MusicIcons.Star,
                                { if (subscribed) onDelete() else subscribe() },
                                Modifier.weight(1f).testTag("playlist_subscribe"),
                                enabled = !busy && !state.loading && !state.refreshing)
                            else PlaylistPill("Chia sẻ", Icons.Default.Share, ::share, Modifier.weight(1f).testTag("playlist_share"), enabled = state.title.isNotBlank())
                            PlaylistPill("Phát ngẫu nhiên", Icons.Default.PlayArrow, { start(true) }, Modifier.weight(1f).testTag("playlist_shuffle"), enabled = canPlay && songs.isNotEmpty(), emphasized = true)
                            if (localMusic != null) PlaylistPill(if (localMusic.allowed) "Quét nhạc" else "Chọn phạm vi", Icons.Default.Refresh,
                                ::refresh, Modifier.weight(1f).testTag(if (localMusic.allowed) "local_music_refresh" else "local_music_permission"),
                                enabled = !state.loading && !state.refreshing)
                            else if (subscribable) PlaylistPill("Chia sẻ", Icons.Default.Share, ::share,
                                Modifier.weight(1f).testTag("playlist_share"), enabled = state.title.isNotBlank())
                            else PlaylistPill("Thông tin ${collectionKind}", Icons.Default.Info, { infoOpen = true }, Modifier.weight(1f).testTag("playlist_info"))
                        }
                    }
                }
                item(key = "playback") {
                    Column(Modifier.fillMaxWidth().playlistPullSurface(pullMotion).background(surface)
                        .testTag("playlist_playback_panel")) {
                        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                            MusicTransportButton("Phát tất cả", { start(false) }, Modifier.size(48.dp).testTag("play_library_all"),
                                prominent = true, enabled = songs.isNotEmpty() && canPlay)
                            Column(Modifier.weight(1f).clickable(enabled = songs.isNotEmpty() && canPlay, role = Role.Button) { start(false) }
                                .padding(horizontal = 14.dp)) {
                                Text("Phát tất cả", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                                Text(if (query.isBlank()) remember(songs) { playlistDurationLabel(songs) } else "Tìm thấy ${songs.size} bài / tổng cộng ${state.songs.size} bài",
                                    Modifier.padding(top = 3.dp), fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                            }
                            PlaylistIconAction(MusicIcons.Sort, "Sắp xếp bài hát", { sortOpen = true }, Modifier.testTag("playlist_sort"),
                                ink = MiuixTheme.colorScheme.onSurface, background = Color.Transparent)
                        }

                    }
                }
                if (selecting) item(key = "selection") {
                    Column(Modifier.fillMaxWidth().background(surface).padding(16.dp).testTag("playlist_selection"),
                        verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Đã chọn ${selectedKeys.size} bài", fontWeight = FontWeight.Medium)
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            MusicTextAction("Chọn tất cả", { selectedKeys = selectedKeys + songs.filter(::supportsBatchFavorite).map(::favoriteSelectionKey) },
                                Modifier.testTag("playlist_select_all"), enabled = !batch.running)
                            MusicTextAction("Hủy", ::exitSelection, Modifier.testTag("playlist_selection_cancel"), enabled = !batch.running)
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            listOf(true to "Yêu thích", false to "Bỏ yêu thích").forEach { (liked, label) ->
                                GlassActionButton(label, {
                                    library.setSelectedFavorites(state.songs.filter { favoriteSelectionKey(it) in selectedKeys }, liked, ::exitSelection)
                                }, Modifier.weight(1f).testTag(if (liked) "playlist_batch_favorite" else "playlist_batch_unfavorite"),
                                    enabled = selectedKeys.isNotEmpty() && !likeBusy)
                            }
                        }
                        if (batch.running) Text("Đang xử lý ${batch.completed} / ${batch.total} bài hát", fontSize = 13.sp)
                        else batch.error?.let { Text("Đã hoàn tất ${batch.completed} / ${batch.total} bài hát, $it. Có thể thử lại hoặc hủy.", fontSize = 13.sp) }
                    }
                }
                if (resume != null) item(key = "resume") {
                    Row(Modifier.fillMaxWidth().playlistPullSurface(pullMotion).background(surface).clickable(role = Role.Button) { vm.container.playerController.toggle() }
                        .padding(horizontal = 20.dp, vertical = 12.dp).testTag("playlist_resume"), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PlayArrow, null, Modifier.size(16.dp), tint = MiuixTheme.colorScheme.primary)
                        Text("Tiếp tục phát: ${resume.name}", Modifier.padding(start = 10.dp), fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
                if (state.loading && state.songs.isEmpty()) item { Box(Modifier.fillMaxWidth().playlistPullSurface(pullMotion).padding(20.dp)) { LoadingSongList(4) } }
                if (localMusic != null && !localMusic.allowed) item {
                    Column(Modifier.fillMaxWidth().playlistPullSurface(pullMotion).background(surface).padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(localMusic.permissionMessage ?: "Chọn thư mục nhạc để chỉ nhập các bài hát trong thư mục đó và thư mục con; hoặc chọn quét nhạc trên toàn thiết bị.", fontSize = 14.sp)
                        TextButton("Chọn phạm vi quét", onClick = localMusic.requestPermission, modifier = Modifier.testTag("local_music_grant"))
                        if (localMusic.permissionMessage != null) TextButton("Mở cài đặt hệ thống", onClick = localMusic.openSettings,
                            modifier = Modifier.testTag("local_music_open_app_settings"))
                    }
                }
                state.error?.let { error -> item { Column(Modifier.fillMaxWidth().playlistPullSurface(pullMotion).padding(20.dp)) { Text(error); TextButton("Thử lại", onClick = ::refresh) } } }
                if (room != null && localMusic == null) item { Text("Nhấn vào bài hát để yêu cầu phát trong phòng hiện tại", Modifier.playlistPullSurface(pullMotion).padding(horizontal = 20.dp, vertical = 8.dp), fontSize = 13.sp) }
                if (!state.loading && songs.isEmpty() && state.error == null && localMusic?.allowed != false) item {
                    Text(if (query.isNotBlank()) "Không tìm thấy bài hát phù hợp" else if (localMusic != null) "Không tìm thấy tệp nhạc, kéo xuống để quét lại" else "Chưa có bài hát nào ở đây, bạn có thể thêm bài hát từ kết quả tìm kiếm",
                        Modifier.fillMaxWidth().playlistPullSurface(pullMotion).padding(24.dp), fontSize = 14.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f))
                }
                itemsIndexed(songs, key = { index, _ -> songKeys[index] }, contentType = { _, _ -> "track" }) { index, song ->
                    Box(Modifier.fillMaxWidth().animateItem(fadeInSpec = tween(280), placementSpec = tween(300), fadeOutSpec = tween(160))
                        .playlistPullSurface(pullMotion).background(surface).padding(horizontal = 16.dp)) {
                        CompositionLocalProvider(LocalGlassBackdrop provides trackBackdrop) {
                            PlaylistTrack(song, selected = currentSong?.id == song.id && currentSong?.musicSource == song.musicSource,
                                liked = localMusic == null && (if (neteaseMain) song.id in neteaseLikes else statuses[song.id]?.liked == true), play = { play(songs, index) },
                                next = { vm.container.playerController.add(song, true) }, enqueue = { vm.container.playerController.add(song) },
                                onMultiSelect = if (selectionAllowed && supportsBatchFavorite(song)) ({
                                    library.clearBatchFavoriteResult()
                                    selectedKeys = setOf(favoriteSelectionKey(song)); selecting = true
                                    scope.launch { list.animateScrollToItem(1) }
                                }) else null,
                                selectionMode = selecting, selectionChecked = favoriteSelectionKey(song) in selectedKeys,
                                selectionEnabled = !batch.running && supportsBatchFavorite(song), onSelect = {
                                    val key = favoriteSelectionKey(song)
                                    selectedKeys = if (key in selectedKeys) selectedKeys - key else selectedKeys + key
                                }) {
                                LibrarySongActions(library, song, navigate)
                                val dismiss = LocalDismissSongMenu.current
                                if (editable) MusicDestinationRow("Xóa khỏi danh sách phát", {
                                    dismiss(); library.action("Đã xóa khỏi danh sách phát") { vm.remove(song) }
                                }, enabled = !busy, chevron = false)
                            }
                        }
                    }
                }
            }
        }
    }
    SettingsUiScope {
    if (sortOpen) MusicChoiceDialog("Sắp xếp bài hát", onDismiss = { sortOpen = false }) {
        listOf("Thứ tự mặc định", "Tên bài hát A–Z", "Ca sĩ A–Z").forEachIndexed { index, label ->
            MusicDialogChoiceRow(label, order == index, { order = index; sortOpen = false },
                modifier = Modifier.testTag("playlist_order_$index"))
        }
    }
    if (menuOpen) MusicChoiceDialog("Thao tác với ${collectionKind}", onDismiss = { menuOpen = false }) {
        MusicDestinationRow("Kệ đĩa CD", {
            menuOpen = false
            scope.launch { vm.container.settings.edit { it.copy(playlistRecordShelf = true, playlistDepthRibbon = false) } }
        }, modifier = Modifier.testTag("playlist_record_shelf_menu"), chevron = false)
        MusicDestinationRow("Dải ruy băng lập thể", {
            menuOpen = false
            scope.launch { vm.container.settings.edit { it.copy(playlistDepthRibbon = true, playlistRecordShelf = false) } }
        }, modifier = Modifier.testTag("playlist_depth_ribbon_toggle"), chevron = false)
        MusicDestinationRow("Thông tin ${collectionKind}", { menuOpen = false; infoOpen = true }, modifier = Modifier.testTag("playlist_menu_info"), chevron = false)
        MusicDestinationRow(if (localMusic != null) "Quét lại nhạc trên thiết bị" else "Làm mới danh sách phát", { menuOpen = false; refresh() }, chevron = false)
        if (state.playlist?.editable(account.account?.id ?: 0) == true) {
            MusicDestinationRow(if (importState.running) "Xem tiến trình nhập từ Nhạc Vân NetEase" else "Nhập mục tôi thích trên Nhạc Vân NetEase chỉ với một lần chạm", {
                menuOpen = false
                if (!importState.running) vm.container.playlistLikeImporter.start(vm.id, state.title)
                importOpen = true
            }, enabled = !state.loading && state.songs.isNotEmpty(),
                modifier = Modifier.testTag("playlist_import_netease_likes"), chevron = false)
        }
        if (subscribable && !subscribed) {
            MusicDestinationRow("Lưu danh sách phát", { menuOpen = false; subscribe() },
                enabled = !busy && !state.loading && !state.refreshing,
                modifier = Modifier.testTag("playlist_menu_subscribe"), chevron = false)
        }
        if (editable) {
            MusicDestinationRow("Đổi tên", { menuOpen = false; onRename() }, enabled = !busy, modifier = Modifier.testTag("rename_playlist"), chevron = false)
        }
        if (removable) {
            MusicDestinationRow(if (state.playlist?.source == "netease" && !state.playlist.nativeOwned) "Bỏ lưu" else "Xóa danh sách phát",
                { menuOpen = false; onDelete() }, enabled = !busy, modifier = Modifier.testTag("delete_playlist"), chevron = false)
        }
    }
    if (infoOpen) MusicDialog("Thông tin ${collectionKind}", onDismiss = { infoOpen = false }) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Text(origin, fontSize = 13.sp)
        Text(playlistDurationLabel(state.songs), fontSize = 13.sp)
        Text(state.description.ifBlank { "Chưa có giới thiệu danh sách phát" }, Modifier.heightIn(max = 240.dp).verticalScroll(androidx.compose.foundation.rememberScrollState()), fontSize = 14.sp)
        MusicDialogButton("Đóng", onClick = { infoOpen = false })
    }
    if (importOpen) PlaylistLikeImportDialog(importState, onClose = { importOpen = false },
        onStop = vm.container.playlistLikeImporter::stop,
        onRetry = { vm.container.playlistLikeImporter.start(importState.playlistId, importState.name) })
    }
}

internal fun playlistDurationLabel(songs: List<Song>): String {
    val count = "${songs.size} bài"
    if (songs.isEmpty() || songs.any { it.durationMs <= 0 }) return count
    val minutes = songs.sumOf { it.durationMs } / 60_000
    return "$count · " + if (minutes >= 60) "${minutes / 60} giờ ${minutes % 60} phút" else if (minutes > 0) "${minutes} phút" else "Chưa đến 1 phút"
}

@Composable internal fun PlaylistTrack(song: Song, selected: Boolean, liked: Boolean, play: () -> Unit,
    next: () -> Unit, enqueue: () -> Unit, onMultiSelect: (() -> Unit)? = null,
    selectionMode: Boolean = false, selectionChecked: Boolean = false, selectionEnabled: Boolean = true,
    onSelect: () -> Unit = {}, extra: @Composable () -> Unit) {
    val host = LocalSongMenu.current
    var menu by remember(song.id) { mutableStateOf<SongMenu?>(null) }
    val room = LocalRoomSongRequest.current.takeUnless { song.privateLibrary || song.musicSource == io.github.currencortex.music.data.song.MusicSource.LOCAL }
    val pending = room?.pending?.invoke(song.id) == true
    val primary = { if (selectionMode) onSelect() else if (room != null) room.request(song) else play() }
    var coverBounds by remember(song.id, song.musicSource) { mutableStateOf(Rect.Zero) }
    fun openMenu(preview: Boolean) {
        val value = SongMenu(song, primary, next, enqueue, room != null, extra,
            previewAnchor = coverBounds.takeIf { preview && !it.isEmpty }, onMultiSelect = onMultiSelect)
        if (host != null) host(value) else menu = value
    }
    Row(Modifier.fillMaxWidth().testTag("playlist_song_${song.id}").combinedClickable(
        enabled = if (selectionMode) selectionEnabled else !pending,
        role = if (selectionMode) Role.Checkbox else Role.Button, onClick = primary,
        onLongClickLabel = "Xem trước bài hát và thao tác", onLongClick = if (selectionMode) null else ({ openMenu(preview = true) }))
        .semantics { if (selectionMode) toggleableState = ToggleableState(selectionChecked) }
        .padding(vertical = 9.dp), verticalAlignment = Alignment.CenterVertically) {
        if (selectionMode) Box(Modifier.padding(end = 12.dp).size(24.dp).border(1.dp, MiuixTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center) {
            if (selectionChecked) Icon(MusicIcons.Check, "Đã chọn", Modifier.size(18.dp), tint = MiuixTheme.colorScheme.primary)
        }
        MusicCover(song.cover, Modifier.size(48.dp).onGloballyPositioned { coordinates ->
            coverBounds = Rect(coordinates.localToWindow(Offset.Zero),
                coordinates.localToWindow(Offset(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())))
        }, cornerRadius = 10.dp)
        Column(Modifier.weight(1f).padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(song.name, fontSize = 16.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis,
                color = if (selected) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                if (liked) Image(painterResource(R.drawable.player_symbol_favorite_fill1), "Đã thích", Modifier.size(12.dp),
                    colorFilter = ColorFilter.tint(MiuixTheme.colorScheme.primary))
                Text(if (pending) "Đang gửi yêu cầu bài hát…" else listOf(song.artists, song.album).filter(String::isNotBlank).joinToString(" · "),
                    fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
            }
            if (song.mv > 0) Text("MV", fontSize = 10.sp, color = MiuixTheme.colorScheme.primary)
        }
        if (!selectionMode) PlaylistIconAction(Icons.Default.MoreVert, "Thao tác bài hát", {
            openMenu(preview = false)
        }, Modifier.testTag("song_menu_${song.id}"), ink = MiuixTheme.colorScheme.onSurface.copy(alpha = .4f),
            background = Color.Transparent, enabled = !pending)
    }
    menu?.let { SongActionsSheet(it) { menu = null } }
}

@Composable private fun PlaylistPill(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true, emphasized: Boolean = false) {
    GlassActionButton(label, onClick, modifier, primary = emphasized, enabled = enabled)
}

@Composable private fun PlaylistIconAction(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String,
    onClick: () -> Unit, modifier: Modifier = Modifier, ink: Color = Color.White,
    background: Color = Color.White.copy(alpha = .08f), enabled: Boolean = true) {
    GlassIconButton(onClick, label, modifier, enabled = enabled) {
        Icon(icon, null, Modifier.size(22.dp), tint = ink)
    }
}
