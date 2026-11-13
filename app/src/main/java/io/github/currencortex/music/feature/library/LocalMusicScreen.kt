package io.github.currencortex.music.feature.library

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import io.github.currencortex.music.data.song.MusicSource
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

internal fun localSongId(uri: String): Long {
    var hash = -3750763034362895579L
    uri.toByteArray(Charsets.UTF_8).forEach { hash = (hash xor (it.toLong() and 255)) * 1099511628211L }
    return -1L - (hash and Long.MAX_VALUE)
}

internal suspend fun scanLocalMusic(context: Context): List<Song> {
    val collection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    val columns = mutableListOf(MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.DISPLAY_NAME)
    if (Build.VERSION.SDK_INT >= 29) columns.add(MediaStore.MediaColumns.VOLUME_NAME)
    return context.contentResolver.query(collection, columns.toTypedArray(), "${MediaStore.Audio.Media.IS_MUSIC} != 0", null,
        "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC")?.use { cursor ->
        val id = cursor.getColumnIndexOrThrow(columns[0]); val title = cursor.getColumnIndexOrThrow(columns[1])
        val artist = cursor.getColumnIndexOrThrow(columns[2]); val album = cursor.getColumnIndexOrThrow(columns[3])
        val duration = cursor.getColumnIndexOrThrow(columns[4]); val albumId = cursor.getColumnIndexOrThrow(columns[5])
        val display = cursor.getColumnIndexOrThrow(columns[6]); val items = mutableListOf<Song>()
        while (cursor.moveToNext()) {
            currentCoroutineContext().ensureActive()
            val rawId = cursor.getLong(id)
            if (rawId < 0 || rawId == Long.MAX_VALUE) continue
            fun text(column: Int) = cursor.getString(column).orEmpty().takeUnless { it == "<unknown>" }.orEmpty()
            val name = text(title).ifBlank { text(display).substringBeforeLast('.') }.ifBlank { "Bài hát không xác định" }
            val volume = if (Build.VERSION.SDK_INT >= 29) cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.VOLUME_NAME)).orEmpty() else ""
            val base = if (volume.isNotBlank()) MediaStore.Audio.Media.getContentUri(volume) else collection
            val uri = ContentUris.withAppendedId(base, rawId).toString()
            val artId = cursor.getLong(albumId)
            val cover = if (artId > 0) "content://media/${volume.ifBlank { "external" }}/audio/albumart/$artId" else ""
            items += Song(id = localSongId(uri), name = name, artists = text(artist), album = text(album),
                cover = cover, durationMs = cursor.getLong(duration).coerceAtLeast(0), musicSource = MusicSource.LOCAL, localUri = uri)
        }
        items
    } ?: error("Hệ thống không trả về danh sách nhạc")
}

/** Scanning scope is independent of playback: changing it never replaces the playing queue. */
@Composable fun LocalMusicScreen(vm: LibraryDetailViewModel, library: LibraryViewModel,
    onBack: () -> Unit, navigate: (String) -> Unit, play: (List<Song>, Int) -> Unit) {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current
    val prefs = remember(context) { context.getSharedPreferences("local_music_scan", Context.MODE_PRIVATE) }
    var folder by remember { mutableStateOf(prefs.getString("folder", null)) }
    var wholeDevice by remember { mutableStateOf(prefs.getBoolean("whole_device", false)) }
    var scopeOpen by androidx.compose.runtime.saveable.rememberSaveable { mutableStateOf(false) }
    var revision by remember { mutableIntStateOf(0) }
    var refreshRequested by remember { mutableStateOf(false) }
    val permission = if (Build.VERSION.SDK_INT >= 33) Manifest.permission.READ_MEDIA_AUDIO else Manifest.permission.READ_EXTERNAL_STORAGE
    var allowed by remember { mutableStateOf(ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) }
    fun hasFolderPermission() = folder?.let { selected ->
        context.contentResolver.persistedUriPermissions.any { it.uri.toString() == selected && it.isReadPermission }
    } ?: false
    var folderAllowed by remember { mutableStateOf(hasFolderPermission()) }
    var songs by remember { mutableStateOf<List<Song>>(emptyList()) }
    var scanning by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val canScan = folder != null || wholeDevice && allowed
    fun chooseScope(uri: String?) {
        prefs.edit().putString("folder", uri).putBoolean("whole_device", uri == null).apply()
        folder = uri; wholeDevice = uri == null; folderAllowed = hasFolderPermission()
        songs = emptyList(); error = null; refreshRequested = true; revision++
    }
    val folderPicker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        if (uri != null) {
            try {
                context.contentResolver.takePersistableUriPermission(uri, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
                chooseScope(uri.toString())
            } catch (_: SecurityException) { error = "Không thể giữ quyền đọc thư mục, vui lòng chọn lại" }
        }
    }
    val request = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        allowed = granted
        error = if (granted) null else "Chưa được cấp quyền âm thanh, bạn vẫn có thể chọn thư mục để quét."
    }
    DisposableEffect(lifecycle, permission) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                allowed = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
                folderAllowed = hasFolderPermission()
            }
        }
        lifecycle.lifecycle.addObserver(observer)
        onDispose { lifecycle.lifecycle.removeObserver(observer) }
    }
    LaunchedEffect(folder, wholeDevice, canScan, folderAllowed, revision) {
        if (!canScan) { songs = emptyList(); scanning = false; return@LaunchedEffect }
        if (folder != null && !folderAllowed) {
            songs = emptyList(); scanning = false
            error = "Quyền truy cập thư mục đã hết hiệu lực, vui lòng chọn lại thư mục"
            return@LaunchedEffect
        }
        scanning = true; error = null
        try {
            val selectedFolder = folder
            val result = library.container.localMusic.load(
                scope = selectedFolder?.let { "folder:$it" } ?: "device",
                refresh = refreshRequested,
            ) {
                selectedFolder?.let { scanLocalFolder(context.applicationContext, android.net.Uri.parse(it)) }
                    ?: scanLocalMusic(context.applicationContext)
            }
            songs = result.songs
            refreshRequested = false
            if (!result.cacheSaved) error = "Đã quét xong nhưng không lưu được danh sách; lần sau có thể cần quét lại"
        } catch (e: CancellationException) { throw e }
        catch (_: SecurityException) {
            songs = emptyList()
            error = if (folder != null) "Quyền truy cập thư mục đã hết hiệu lực, vui lòng chọn lại thư mục" else "Quyền đọc nhạc đã bị tắt"
        } catch (_: Exception) { error = "Quét thất bại, vui lòng kiểm tra vị trí đã chọn rồi thử lại" }
        finally { scanning = false }
    }
    val description = folder?.let {
        val path = runCatching { android.provider.DocumentsContract.getTreeDocumentId(android.net.Uri.parse(it)) }.getOrDefault("")
        "Thư mục đã chọn：$path · Bao gồm thư mục con"
    } ?: if (wholeDevice) "Nhạc trên toàn thiết bị · Vuốt xuống để quét lại" else "Trước tiên hãy chọn thư mục hoặc quét toàn bộ thiết bị"
    PlaylistDetailBody(
        LibraryDetailState(title = "Nhạc trên thiết bị", description = description,
            songs = songs, loading = scanning && songs.isEmpty(), refreshing = scanning && songs.isNotEmpty(),
            error = error.takeIf { canScan }, loaded = canScan),
        vm, library, onBack, navigate, play, onRename = {}, onDelete = {},
        localMusic = LocalMusicPlaylistActions(canScan, {
            if (!scanning) { refreshRequested = true; revision++ }
        }, { scopeOpen = true }, {
            context.startActivity(android.content.Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                android.net.Uri.parse("package:${context.packageName}")))
        }, error, { scopeOpen = true }))
    if (scopeOpen) io.github.currencortex.music.ui.component.MusicDialog("Phạm vi quét", onDismiss = { scopeOpen = false }) {
        io.github.currencortex.music.ui.component.MusicDestinationRow("Chọn thư mục", {
            scopeOpen = false
            try { folderPicker.launch(folder?.let(android.net.Uri::parse)) }
            catch (_: android.content.ActivityNotFoundException) { error = "Hệ thống không có trình chọn thư mục khả dụng" }
        }, chevron = false, modifier = androidx.compose.ui.Modifier.testTag("local_music_choose_folder"))
         io.github.currencortex.music.ui.component.MusicDestinationRow("Quét nhạc trên toàn thiết bị", {
            scopeOpen = false; chooseScope(null)
            if (!allowed) request.launch(permission)
        }, chevron = false, modifier = androidx.compose.ui.Modifier.testTag("local_music_scan_device"))
         top.yukonga.miuix.kmp.basic.Text("Chế độ thư mục chỉ quét thư mục đã chọn và các thư mục con, không thay đổi hàng đợi phát hiện tại.")
    }
}
