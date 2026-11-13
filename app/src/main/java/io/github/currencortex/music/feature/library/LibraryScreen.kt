package io.github.currencortex.music.feature.library

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import top.yukonga.miuix.kmp.theme.MiuixTheme
import io.github.currencortex.music.ui.util.collectAsPageState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import io.github.currencortex.music.data.library.*
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun catalogRoute(album: Boolean, query: String = "") = "lib/browse/${if (album) "album" else "artist"}/${URLEncoder.encode(query, StandardCharsets.UTF_8.name())}"

@Composable fun LibraryLinks(navigate: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(Modifier.fillMaxWidth().height(IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
listOf(Triple("Yêu thích", "lib/likes", "open_likes"), Triple("Danh sách phát", "lib/playlists", "open_playlists"),Triple("Đã phát gần đây", "lib/recent", "open_recent")).forEach { (title, route, tag) ->                Card(Modifier.weight(1f).fillMaxHeight().testTag(tag), onClick = { navigate(route) }, showIndication = true) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
Text("Mở thư viện nhạc", fontSize = 11.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f))                    }
                }
            }
        }
        Card(Modifier.fillMaxWidth().testTag("open_local_music"), onClick = { navigate("lib/local") }, showIndication = true) {
            Column(Modifier.padding(14.dp)) {
Text("Nhạc cục bộ", fontSize = 15.sp, fontWeight = FontWeight.Medium)
Text("Quét nhạc trong điện thoại", fontSize = 11.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .55f))            }
        }
    }
}

@Composable fun LibrarySongActions(vm: LibraryViewModel, song: Song, navigate: (String) -> Unit) {
    val dismiss = LocalDismissSongMenu.current
    librarySongMenuItems(vm, song, navigate).forEach { item ->
        MusicDestinationRow(item.label, { dismiss(); item.onClick() }, enabled = item.enabled,
            modifier = Modifier.testTag(item.id), chevron = item.id !in setOf("song_like_${song.id}", "song_playlist_${song.id}"))
    }
}

/** Shared actions for the song sheet and artwork's nested glass menu. */
@Composable internal fun librarySongMenuItems(vm: LibraryViewModel, song: Song, navigate: (String) -> Unit): List<GlassMenuItem> {
    if (song.musicSource == io.github.currencortex.music.data.song.MusicSource.LOCAL) return emptyList()
    if (song.musicSource == io.github.currencortex.music.data.song.MusicSource.EMBY) {
return listOf(GlassMenuItem("song_like_${song.id}", "Thích / Bỏ thích", onClick = { vm.togglePrimaryLike(song) }),GlassMenuItem("song_emby", "Mở thư viện nhạc Emby", onClick = { navigate("emby") }))    }
    val statuses by vm.statuses.collectAsStateWithLifecycle()
    val status = statuses[song.id] ?: SongStatus()
    LaunchedEffect(song.id) { vm.refreshStatuses(listOf(song)) }
    return buildList {
add(GlassMenuItem("song_like_${song.id}", "Thích / Bỏ thích", enabled = !status.pending && !song.video,            onClick = { vm.togglePrimaryLike(song) }))
add(GlassMenuItem("song_playlist_${song.id}", "Thêm vào danh sách phát", onClick = { vm.choosePlaylist(song) }))        if (song.musicSource == io.github.currencortex.music.data.song.MusicSource.NAVIDROME) {
add(GlassMenuItem("song_navidrome", "Mở thư viện nhạc Navidrome", onClick = { navigate("navidrome") }))            return@buildList
        }
        if (song.artistIds.isNotEmpty()) song.artistIds.forEachIndexed { index, id ->
add(GlassMenuItem("song_artist_$id", "Ca sĩ · ${song.artists.split(" / ").getOrNull(index).orEmpty()}",
                onClick = { navigate("lib/artist/$id") }))
} else if (song.artists.isNotBlank()) add(GlassMenuItem("song_artist", "Xem ca sĩ", onClick = { navigate(catalogRoute(false, song.artists)) }))
if (song.album.isNotBlank()) add(GlassMenuItem("song_album", "Xem album", onClick = { navigate(catalogRoute(true, song.album)) }))
if (song.mv > 0) add(GlassMenuItem("song_mv_${song.id}", "Phát MV", onClick = { navigate("lib/mv/${song.mv}") }))    }
}

@Composable fun LibraryDialogs(vm: LibraryViewModel) {
    val likes by vm.likes.state.collectAsStateWithLifecycle()
    if (likes.song != null) SongLikeSheet(likes, vm.likes::toggle, vm.likes::dismiss)
    val song by vm.selectedSong.collectAsStateWithLifecycle()
    val lists by vm.availablePlaylists.collectAsStateWithLifecycle()
    val playlistError by vm.playlistError.collectAsStateWithLifecycle()
    val busy by vm.busy.collectAsStateWithLifecycle()
if (song != null) MusicDialog("Lưu vào danh sách phát", onDismiss = { vm.selectedSong.value = null }) {if (busy && lists.isEmpty()) Text("Đang tải danh sách phát…")if (!busy && lists.isEmpty() && playlistError == null) Text("Không có danh sách phát nào có thể chỉnh sửa, vui lòng tạo danh sách phát trước")playlistError?.let { error -> Text(error); MusicDialogButton("Thử lại", onClick = { vm.choosePlaylist(song!!) }, enabled = !busy) }        LazyColumn(Modifier.heightIn(max = 360.dp)) {
            items(lists, key = { "${it.source}-${it.id}" }) { playlist ->
                MusicDestinationRow(playlist.name, { vm.addToPlaylist(playlist) },
                    Modifier.testTag("collect_playlist_${playlist.source}_${playlist.id}"),
summary = when (playlist.source) { "netease" -> "Nhạc đám mây NetEase"; "navidrome" -> "Navidrome"; else -> "CurrentMusic" }, enabled = !busy, chevron = false)            }
        }
    }
}

@Composable fun PlaylistIndexScreen(vm: LibraryViewModel, navigate: (String) -> Unit, play: (List<Song>, Int) -> Unit, onBack: (() -> Unit)? = null) {
    val busy by vm.busy.collectAsStateWithLifecycle()
    val neteaseMain by vm.container.primaryLibrary.usesNetease.collectAsStateWithLifecycle()
    var create by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    PlaylistIndexBody(vm, navigate, play, onBack, onCreate = { create = true })
    SettingsUiScope {
if (create) MusicDialog("Tạo danh sách phát mới", onDismiss = { if (!busy) create = false }) {TextField(name, { name = it }, label = "Tên danh sách phát", modifier = Modifier.fillMaxWidth().then(Modifier.testTag("playlist_name")))if (!neteaseMain || vm.container.nativeNetease == null) TextField(description, { description = it }, label = "Giới thiệu", modifier = Modifier.fillMaxWidth())MusicDialogButton("Tạo", onClick = { vm.create(name, description) { create = false; name = ""; description = "" } },            enabled = name.isNotBlank() && !busy, modifier = Modifier.testTag("confirm_create_playlist"))
    }
    }
}

@Composable fun PlaylistCard(playlist: Playlist, open: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = open).padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        MusicCover(playlist.cover, Modifier.size(64.dp))
        Column(Modifier.padding(start = 12.dp).weight(1f)) {
            Text(playlist.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
Text("${playlist.count} bài" + if (playlist.source == "ncm") " · NetEase Cloud Music · Chỉ đọc" else "")        }
    }
}

@Composable fun LibraryDetailScreen(vm: LibraryDetailViewModel, library: LibraryViewModel, onBack: () -> Unit,
    navigate: (String) -> Unit, play: (List<Song>, Int) -> Unit, playMv: (MvDto) -> Unit, deleted: () -> Unit,
    onDialogActive: (Boolean) -> Unit = {}) {
    val state by vm.state.collectAsPageState()
    val account by vm.container.accountRepository.state.collectAsStateWithLifecycle()
    val busy by library.busy.collectAsStateWithLifecycle()
    val playbackMode by vm.playbackMode.collectAsStateWithLifecycle()
    val roomRequest = LocalRoomSongRequest.current
    var rename by rememberSaveable { mutableStateOf(false) }
    var delete by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    val editable = vm.editable(state.playlist, account.account?.id ?: 0)
    LaunchedEffect(state.songs) { library.refreshStatuses(state.songs) }
    LaunchedEffect(state.refreshError) { state.refreshError?.let { library.container.errorNotices.show("Làm mới thất bại, đã giữ lại danh sách cũ: $it") } }
    // NavDisplay resumes the destination after the transition. Visible rows request their own art.
    val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateAsState()
    if (LocalMusicPageActive.current && LocalPageContainerSettled.current && lifecycleState.isAtLeast(Lifecycle.State.RESUMED)) {
        PreloadMusicCovers(remember(state.songs) { state.songs.take(12).map { it.cover } })
    }
    if (vm.route.startsWith("lib/mv/")) {
        io.github.currencortex.music.feature.mv.MvDetailScreen(state, vm.container, onBack, playMv,
            onArtist = { navigate("lib/artist/$it") }, onRefresh = { vm.reload(force = true) }, onDialogActive = onDialogActive)
    } else if (vm.route.split('/').getOrNull(1) in setOf("playlist", "ncmplaylist", "album", "likes", "daily", "foryou", "recent")) {
        PlaylistDetailBody(state, vm, library, onBack, navigate, play,
            onRename = { name = state.title; rename = true }, onDelete = { delete = true })
    } else {
    MusicPullToRefresh(state.loading || state.refreshing, { vm.reload(force = true) }, Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()) {
        LazyColumn(Modifier.fillMaxSize().testTag("library_detail"), contentPadding = musicScrollPadding(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { TextButton("Quay lại", onClick = onBack); Text(state.title, fontSize = 28.sp) }
            if (state.loading && state.songs.isEmpty() && state.mv == null) item { LoadingSongList(4) }
            if (state.cover.isNotBlank()) item { MusicCover(state.cover, Modifier.size(180.dp), 500) }
            if (state.description.isNotBlank()) item { Text(state.description) }
            if (state.playlist?.source == "ncm") item { Text("Nhạc Vân NetEase · Danh sách phát đồng bộ chỉ đọc") }
            state.error?.let { error -> item { Text(error); TextButton("Thử lại", onClick = { vm.reload() }) } }
            state.mv?.let { mv -> item { TextButton("Phát MV", onClick = { playMv(mv) }, modifier = Modifier.testTag("play_mv")) } }
            if (state.songs.isNotEmpty()) item {
                LazyRow {
                    item { TextButton("Phát tất cả", enabled = roomRequest == null && playbackMode == io.github.currencortex.music.core.media.PlayerMode.LOCAL,
                        onClick = { vm.container.playerController.setMode(io.github.currencortex.music.core.media.PlaybackMode.LIST); play(state.songs, 0) }, modifier = Modifier.testTag("play_library_all")) }
                    item { MusicTextAction("Phát ngẫu nhiên", enabled = roomRequest == null && playbackMode == io.github.currencortex.music.core.media.PlayerMode.LOCAL,
                        onClick = { vm.container.playerController.setMode(io.github.currencortex.music.core.media.PlaybackMode.SHUFFLE); play(state.songs, state.songs.indices.random()) }) }
                }
            }
            if (roomRequest != null) item { Text("Nhấn vào bài hát để chọn phát trong phòng hiện tại", fontSize = 13.sp) }
            if (editable) item { Row {
                TextButton("Đổi tên", onClick = { name = state.title; rename = true }, modifier = Modifier.testTag("rename_playlist"))
                TextButton("Xóa danh sách phát", onClick = { delete = true }, modifier = Modifier.testTag("delete_playlist"))
            } }
            if (!state.loading && state.songs.isEmpty() && state.mv == null && state.error == null) item { Text("Chưa có bài hát nào ở đây. Bạn có thể thêm bài hát vào danh sách phát từ kết quả tìm kiếm") }
            if (state.albums.isNotEmpty()) item { Text("Album", fontSize = 22.sp) }
            if (state.albums.isNotEmpty()) item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    items(state.albums, key = Album::id) { album ->
                        Column(Modifier.width(160.dp).testTag("catalog_entry_${album.id}").clickable { navigate("lib/album/${album.id}") }) {
                            MusicCover(album.cover, Modifier.size(160.dp), 400)
                            Text(album.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                    }
                }
            }
            if (state.moreAlbums) item { TextButton("Thêm album", onClick = vm::moreAlbums, enabled = !state.loading) }
            itemsIndexed(state.songs, key = { index, song -> "${song.id}-$index" }) { index, song ->
                SongRow(SongRowUi(song), { play(state.songs, index) }, { vm.container.playerController.add(song, true) }, { vm.container.playerController.add(song) }) {
                    LibrarySongActions(library, song, navigate)
                    val dismiss = LocalDismissSongMenu.current
                    if (editable) MusicDestinationRow("Xóa khỏi danh sách phát", onClick = { dismiss(); library.action("Đã xóa khỏi danh sách phát") { vm.remove(song) } }, enabled = !busy, chevron = false)
                }
            }
            if (state.more) item { TextButton("Tải thêm bài hát", onClick = { vm.reload(true) }, enabled = !state.loading) }
        }
    }
    }
    SettingsUiScope {
    if (rename) MusicDialog("Đổi tên danh sách phát", onDismiss = { if (!busy) rename = false }) {
        TextField(name, { name = it }, label = "Tên danh sách phát", modifier = Modifier.fillMaxWidth().then(Modifier.testTag("rename_input")))
        MusicDialogButton("Lưu", onClick = { library.action("Đã cập nhật tên") { vm.rename(name); rename = false } }, enabled = name.isNotBlank() && !busy)
    }
    val unsubscribe = state.playlist?.let { it.source == "netease" && !it.nativeOwned } == true
    if (delete) MusicDialog(if (unsubscribe) "Hủy lưu danh sách phát?" else "Xóa danh sách phát?", onDismiss = { if (!busy) delete = false }) {
        Text(if (unsubscribe) "Danh sách phát sẽ bị xóa khỏi mục đã lưu trên Nhạc Vân NetEase của bạn." else "Không thể khôi phục sau khi xóa.")
        MusicDialogButton(if (unsubscribe) "Xác nhận hủy lưu" else "Xác nhận xóa", onClick = {
            library.action(if (unsubscribe) "Đã hủy lưu danh sách phát" else "Đã xóa danh sách phát") { vm.delete(); delete = false; deleted() }
        }, enabled = !busy)
    }
    }
}

@Composable fun CatalogScreen(vm: CatalogViewModel, onBack: () -> Unit, navigate: (String) -> Unit) {
    val state by vm.state.collectAsPageState()
    val keyboard = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
    val focus = androidx.compose.ui.platform.LocalFocusManager.current
    fun submit() { keyboard?.hide(); focus.clearFocus(); vm.search() }
    MusicSubpage(if (state.albums) "Album" else "Nghệ sĩ", "catalog_page", onBack) { topInset ->
    LazyColumn(Modifier.fillMaxSize().testTag("catalog_screen"),
        contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp)), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            MusicSearchBar(state.query, ::submit, onInput = vm::input, placeholder = "Tìm kiếm ${if (state.albums) "album" else "nghệ sĩ"}",
                inputTag = "catalog_query", submitTag = "catalog_submit")
        }
        if (state.loading) item { LoadingSongList(3) }
        state.error?.let { error -> item { Text(error); MusicTextAction("Thử lại", onClick = { vm.search() }) } }
         if (state.submitted.isNotBlank() && !state.loading && state.entries.isEmpty() && state.error == null) item { Text("Không tìm thấy kết quả") }
        itemsIndexed(state.entries, key = { index, entry -> "${entry.id}-$index" }) { _, entry -> CatalogCard(entry) { navigate("lib/${if (state.albums) "album" else "artist"}/${entry.id}") } }
         if (state.more) item { MusicTextAction("Tải thêm", onClick = { vm.search(true) }, enabled = !state.loading) }
    }
    }
}

@Composable private fun CatalogCard(entry: CatalogEntry, open: () -> Unit) {
    SettingsGroup {
        SettingsPreference(entry.name, entry.subtitle, Modifier.testTag("catalog_entry_${entry.id}"), onClick = open,
            leading = { MusicCover(entry.cover, Modifier.size(56.dp), cornerRadius = 12.dp) },
            trailing = { Text("›", fontSize = 22.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary) })
    }
}
