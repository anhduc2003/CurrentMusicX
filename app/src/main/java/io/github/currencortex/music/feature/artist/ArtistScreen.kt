package io.github.currencortex.music.feature.artist

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.github.currencortex.music.core.media.PlaybackMode
import io.github.currencortex.music.core.media.PlayerMode
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.feature.library.LibrarySongActions
import io.github.currencortex.music.feature.library.LibraryViewModel
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.util.collectAsPageState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable fun ArtistScreen(vm: ArtistViewModel, library: LibraryViewModel, onBack: () -> Unit,
    navigate: (String) -> Unit, play: (List<Song>, Int) -> Unit) {
    val state by vm.state.collectAsPageState()
    val playback by vm.container.playerController.state.collectAsPageState()
    val room = LocalRoomSongRequest.current
    var tab by rememberSaveable(vm.id) { mutableStateOf("home") }
    var encyclopedia by rememberSaveable(vm.id) { mutableStateOf(false) }
    var filter by rememberSaveable(vm.id) { mutableStateOf("all") }
    val homeScroll = rememberLazyListState()
    val songsScroll = rememberLazyListState()
    val albumsScroll = rememberLazyListState()
    val mvsScroll = rememberLazyListState()
    val scroll = when (tab) { "songs" -> songsScroll; "albums" -> albumsScroll; "mvs" -> mvsScroll; else -> homeScroll }
    val albums = remember(state.albums, filter) { artistAlbumsForDisplay(state.albums, filter) }
    val hotSongs = state.overview?.songs?.takeIf { it.isNotEmpty() } ?: state.songs.take(50)
    val previewAlbums = state.overview?.albums?.takeIf { it.isNotEmpty() } ?: state.albums.take(6)
    val playingSongs = if (tab == "home") hotSongs else state.songs
    LaunchedEffect(state.songs, hotSongs) { library.refreshStatuses((state.songs + hotSongs).distinctBy { it.id }) }
    LaunchedEffect(filter, state.albumOrder) { albumsScroll.scrollToItem(0) }
    BackHandler(encyclopedia) { encyclopedia = false }
    PreloadMusicCovers(if (tab == "albums") albums.take(10).map { it.cover } else state.songs.take(12).map { it.cover })
    if (encyclopedia) {
        ArtistEncyclopedia(state, { encyclopedia = false }, { vm.loadBiography() }, vm::loadProfile)
        return
    }
    MusicSubpage(state.displayArtist?.name?.ifBlank { "Trang nghệ sĩ" } ?: "Trang nghệ sĩ", "artist_page", onBack, backTag = "artist_back") { topInset ->
        MusicPullToRefresh(state.loading, vm::reload, Modifier.fillMaxSize().padding(top = topInset)) {
            LazyColumn(Modifier.fillMaxSize().testTag("artist_home"), state = scroll,
                contentPadding = musicScrollPadding(PaddingValues(16.dp, 8.dp, 16.dp, 32.dp)),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                if (tab == "home") item("hero") { ArtistHero(state.displayArtist, state.fans) { encyclopedia = true } }
                stickyHeader("tabs") {
                     MusicCategoryTabs(listOf("home" to "Nhạc", "songs" to "Bài hát", "albums" to "Album", "mvs" to "MV"),
                        tab, { tab = it }, "artist_tab", Modifier.background(MiuixTheme.colorScheme.background))
                }
                 state.error?.let { error -> item("main_error") { ArtistSectionError(error, "Thử lại trang nghệ sĩ", vm::reload, state.loading) } }
                if (state.loading && state.artist == null) item("loading") { LoadingSongList(4) }
                if (tab == "home" || tab == "songs") {
                    if (playingSongs.isNotEmpty()) item("play") {
                         ArtistPlayHeader(if (tab == "home") "Phát bài hát nổi bật" else "Phát các bài hát đã tải", playingSongs.size,
                            room == null && playback.mode == PlayerMode.LOCAL) {
                            vm.container.playerController.setMode(PlaybackMode.LIST); play(playingSongs, 0)
                        }
                    }
                     if (room != null) item("room") { Text("Nhấn vào bài hát để chọn phát trong phòng hiện tại") }
                    val visibleSongs = if (tab == "home") hotSongs.take(5) else state.songs
                    itemsIndexed(visibleSongs, key = { _, song -> "song_${song.id}" }) { index, song ->
                        Box(Modifier.testTag("artist_song_${song.id}")) {
                            SongRow(SongRowUi(song), { play(playingSongs, index) }, { vm.container.playerController.add(song, true) },
                                { vm.container.playerController.add(song) }) { LibrarySongActions(library, song, navigate) }
                        }
                    }
                     if (!state.loading && playingSongs.isEmpty() && state.error == null) item("songs_empty") { ArtistEmpty("Chưa có bài hát") }
                    if (tab == "songs") {
                        if (state.songsLoading) item("songs_loading") { LoadingSongList(2) }
                         state.songsError?.let { error -> item("songs_error") { ArtistSectionError(error, "Thử lại danh sách bài hát", vm::moreSongs, state.songsLoading) } }
                        if (state.moreSongs && state.songsError == null) item("songs_more") {
                             MusicTextAction("Tải thêm bài hát", vm::moreSongs, Modifier.fillMaxWidth().testTag("artist_more_songs"), enabled = !state.songsLoading)
                        }
                    } else {
                        if (state.songs.isNotEmpty()) item("all_songs") {
                             MusicTextAction("Tất cả ${maxOf(state.displayArtist?.songTotal ?: 0, state.songs.size)} bài hát ›",
                                { tab = "songs" }, Modifier.fillMaxWidth().testTag("artist_all_songs"))
                        }
                        item("albums_preview") {
                             ArtistSectionTitle("Album", "Xem tất cả") { tab = "albums" }
                            if (state.albumsLoading && previewAlbums.isEmpty()) LoadingTextList(2)
                            else if (previewAlbums.isNotEmpty()) LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                items(previewAlbums.take(6), key = { it.id }) { ArtistAlbumCard(it, Modifier.width(152.dp), navigate) }
                             } else if (state.albumsError == null) ArtistEmpty("Chưa có album")
                             state.albumsError?.let { ArtistSectionError(it, "Thử lại danh sách album", vm::moreAlbums, state.albumsLoading) }
                        }
                        item("mvs_preview") {
                             ArtistSectionTitle("MV", "Xem tất cả") { tab = "mvs" }
                            if (state.mvsLoading && state.mvs.isEmpty()) LoadingTextList(2)
                            else if (state.mvs.isNotEmpty()) LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                items(state.mvs.take(6), key = { it.id }) { ArtistMvCard(it, Modifier.width(256.dp), navigate) }
                             } else if (state.mvsError == null) ArtistEmpty("Chưa có MV")
                             state.mvsError?.let { ArtistSectionError(it, "Thử lại danh sách MV", vm::moreMvs, state.mvsLoading) }
                        }
                        item("encyclopedia") { ArtistBiographyPreview(state) { encyclopedia = true } }
                        item("similar") {
                             ArtistSectionTitle("Nghệ sĩ tương tự")
                            if (state.similarLoading && state.similar.isEmpty()) LoadingTextList(2)
                            else if (state.similar.isNotEmpty()) LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                items(state.similar, key = { it.id }) { artist ->
                                    MusicArtworkCard(artist.cover, artist.name, artist.subtitle,
                                        { navigate("lib/artist/${artist.id}") }, Modifier.width(144.dp).testTag("artist_similar_${artist.id}"))
                                }
                             } else if (state.similarError == null) ArtistEmpty("Chưa có nghệ sĩ tương tự")
                             state.similarError?.let { ArtistSectionError(it, "Thử lại danh sách nghệ sĩ tương tự", vm::loadSimilar, state.similarLoading) }
                        }
                    }
                }
                if (tab == "albums") {
                    item("album_filters") { ArtistAlbumFilters(filter, { filter = it }, state.albumOrder, vm::setAlbumOrder,
                        albums.size, state.albums.size, state.displayArtist?.albumTotal ?: 0, state.moreAlbums) }
                    items(albums, key = { "album_${it.id}" }) { ArtistAlbumRow(it, navigate) }
                    if (state.albumsLoading) item("albums_loading") { LoadingSongList(3) }
                     state.albumsError?.let { error -> item("albums_error") { ArtistSectionError(error, "Thử lại danh sách album", vm::moreAlbums, state.albumsLoading) } }
                    if (!state.albumsLoading && albums.isEmpty() && state.albumsError == null) item("albums_empty") {
                         ArtistEmpty(if (state.moreAlbums) "Chưa có tác phẩm thuộc thể loại này trong số đã tải; bạn có thể tải thêm" else "Chưa có tác phẩm thuộc thể loại này")
                    }
                    if (state.moreAlbums && state.albumsError == null) item("albums_more") {
                         MusicTextAction("Tải thêm album", vm::moreAlbums, Modifier.fillMaxWidth().testTag("artist_more_albums"), enabled = !state.albumsLoading)
                    }
                }
                if (tab == "mvs") {
                    items(state.mvs, key = { "mv_${it.id}" }) { ArtistMvCard(it, Modifier.fillMaxWidth(), navigate) }
                    if (state.mvsLoading) item("mvs_loading") { LoadingTextList(3) }
                     state.mvsError?.let { error -> item("mvs_error") { ArtistSectionError(error, "Thử lại danh sách MV", vm::moreMvs, state.mvsLoading) } }
                     if (!state.mvsLoading && state.mvs.isEmpty() && state.mvsError == null) item("mvs_empty") { ArtistEmpty("Chưa có MV") }
                    if (state.moreMvs && state.mvsError == null) item("mvs_more") {
                         MusicTextAction("Tải thêm MV", vm::moreMvs, Modifier.fillMaxWidth().testTag("artist_more_mvs"), enabled = !state.mvsLoading)
                    }
                }
            }
        }
    }
}
