package io.github.currencortex.music.feature.recognition

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.os.Build
import android.media.projection.MediaProjectionManager
import android.media.projection.MediaProjectionConfig
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.BackHandler
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable fun MusicRecognitionScreen(onBack: () -> Unit, play: (List<Song>, Int) -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val lifecycle = LocalLifecycleOwner.current
    val controller = remember {
        val encoder = NeteaseFingerprint(context)
        val aacEncoder = RecognitionAacEncoder()
        val repository = NeteaseRecognitionRepository()
        RecognitionController(scope, MicrophoneRecorder(), encoder::encode, repository::recognize,
            recognizeInSession = repository::recognize, recognizeLyrics = { samples, session ->
                val audio = aacEncoder.encode(samples)
                try { repository.recognizeLyrics(audio, session) } finally { audio.fill(0) }
            })
    }
    val localState by controller.state.collectAsStateWithLifecycle()
    val desktopState by RecognitionDesktopSession.state.collectAsStateWithLifecycle()
    val desktopAttached by RecognitionDesktopSession.attached.collectAsStateWithLifecycle()
    val serviceRunning by RecognitionDesktopSession.running.collectAsStateWithLifecycle()
    val state = if (serviceRunning || (localState.phase == RecognitionPhase.IDLE && desktopState.phase != RecognitionPhase.IDLE)) desktopState else localState
    val historyStore = remember { RecognitionHistory(context) }
    var history by remember { mutableStateOf(historyStore.read()) }
    var showingHistory by remember { mutableStateOf(false) }
    var permissionMessage by remember { mutableStateOf<String?>(null) }
    var pendingRecord by remember { mutableStateOf(false) }
    var pendingDesktop by remember { mutableStateOf(false) }
    var desktopStarting by remember { mutableStateOf(false) }
    var source by rememberSaveable { mutableStateOf(if (Build.VERSION.SDK_INT >= 29) RecognitionAudioSource.SYSTEM else RecognitionAudioSource.MICROPHONE) }
    var pendingProjection by remember { mutableStateOf<Intent?>(null) }
    var useProjection by rememberSaveable { mutableStateOf(false) }
    fun launchService(consent: Intent?, desktop: Boolean, systemMix: Boolean = false) {
        desktopStarting = desktop
        scope.launch {
            try {
                // Finish the previous source before starting a new foreground-service type.
                RecognitionDesktopService.stop(context)
                check(withTimeoutOrNull(2_000) { RecognitionDesktopSession.running.first { !it } } != null)
                if (!lifecycle.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) return@launch
                controller.reset()
                RecognitionDesktopSession.clearResult()
                if (consent != null) RecognitionDesktopService.startSystem(context, consent, desktop)
                else if (systemMix) RecognitionDesktopService.startSystemMix(context, desktop)
                else RecognitionDesktopService.start(context)
                if (desktop) {
                    val ready = withTimeoutOrNull(4_000) { RecognitionDesktopSession.attached.first { it } } == true
                    if (ready && lifecycle.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))
                        context.startActivity(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                    else if (!ready) permissionMessage = "Không thể khởi chạy nhận diện nhạc trên màn hình chính, hãy kiểm tra quyền rồi thử lại"
                }
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) { permissionMessage = "Hiện chưa thể bắt đầu nhận diện nhạc, hãy cấp quyền lại rồi thử lại" }
            finally { desktopStarting = false }
        }
    }
    fun startSystem() {
        if (!lifecycle.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) return
        val consent = pendingProjection ?: return
        pendingProjection = null
        pendingRecord = false
        val desktop = pendingDesktop
        pendingDesktop = false
        launchService(consent, desktop)
    }
    val projectionConsent = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            pendingProjection = result.data
            startSystem()
        } else {
            pendingDesktop = false
            permissionMessage = "Đã hủy quyền truy cập âm thanh hệ thống, bạn có thể bắt đầu lại hoặc chuyển sang micrô"
        }
    }
    fun startChosen() {
        pendingRecord = false
        permissionMessage = null
        if (source == RecognitionAudioSource.SYSTEM) {
            if (!useProjection) {
                val desktop = pendingDesktop
                pendingDesktop = false
                launchService(null, desktop, systemMix = true)
                return
            }
            RecognitionDesktopService.stop(context)
            val manager = context.getSystemService(MediaProjectionManager::class.java)
            val consent = if (Build.VERSION.SDK_INT >= 34) manager.createScreenCaptureIntent(MediaProjectionConfig.createConfigForDefaultDisplay())
                else manager.createScreenCaptureIntent()
            projectionConsent.launch(consent)
            return
        }
        if (pendingDesktop) {
            pendingDesktop = false
            if (!lifecycle.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) return
            launchService(null, desktop = true)
        } else {
            RecognitionDesktopService.stop(context)
            RecognitionDesktopSession.clearResult()
            controller.start()
        }
    }
    BackHandler(showingHistory) { showingHistory = false }
    val permission = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        pendingRecord = granted && lifecycle.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
        if (granted && lifecycle.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            startChosen()
        } else if (!granted) permissionMessage = "Cần quyền ghi âm để nhận diện nhạc, bạn có thể bật quyền trong phần cài đặt hệ thống"
    }
    DisposableEffect(controller, lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                controller.cancel()
                if (RecognitionDesktopSession.running.value && !RecognitionDesktopSession.attached.value) RecognitionDesktopService.stop(context)
            }
            if (event == Lifecycle.Event.ON_RESUME && pendingRecord) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) startChosen()
            }
            if (event == Lifecycle.Event.ON_RESUME) history = historyStore.read()
            if (event == Lifecycle.Event.ON_RESUME && pendingProjection != null) startSystem()
        }
        lifecycle.lifecycle.addObserver(observer)
        onDispose {
            lifecycle.lifecycle.removeObserver(observer); pendingRecord = false; pendingProjection = null; controller.cancel()
            if (RecognitionDesktopSession.running.value && !RecognitionDesktopSession.attached.value) RecognitionDesktopService.stop(context)
        }
    }
    LaunchedEffect(state.phase, state.songs) {
        if (localState.phase == RecognitionPhase.MATCHED && localState.songs.isNotEmpty())
            history = withContext(Dispatchers.IO) { historyStore.add(localState.songs.first()) }
    }
    fun begin(desktop: Boolean = false) {
        if (desktopStarting || state.busy) return
        permissionMessage = null
        pendingDesktop = desktop
        if (desktop && !Settings.canDrawOverlays(context)) {
            pendingDesktop = false
            permissionMessage = "Bật quyền cửa sổ nổi, sau đó chạm vào Đảo động trên màn hình chính để nhận diện nhạc"
            context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}")))
            return
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) startChosen()
        else permission.launch(Manifest.permission.RECORD_AUDIO)
    }
    val colors = MiuixTheme.colorScheme
    val bottom = LocalMusicBottomInset.current
    MusicSubpage(if (showingHistory) "Lịch sử nhận diện" else "Nhận diện bài hát", "music_recognition",
        onBack = { if (showingHistory) showingHistory = false else { controller.cancel(); onBack() } },
        backTag = "recognition_back", toolbarTag = "recognition_header", actions = {
            if (showingHistory) GlassActionButton("Xóa tất cả", { historyStore.clear(); history = emptyList() },
                Modifier.testTag("recognition_clear_history"), fillWidth = false, minHeight = 36.dp)
            else GlassIconButton({ controller.cancel(); showingHistory = true }, "Lịch sử nhận diện", Modifier.testTag("recognition_history")) {
                Icon(MusicIcons.Update, null)
            }
        }) { topInset ->
        if (showingHistory) {
            LazyColumn(Modifier.fillMaxSize().testTag("recognition_history_list"),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = topInset + 8.dp, bottom = bottom + 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                if (history.isEmpty()) item { Text("Chưa có lịch sử nhận diện. Nghe thấy bài nhạc hay thì hãy thử nhé", Modifier.padding(vertical = 30.dp), color = colors.onSurfaceVariantSummary, fontSize = 14.sp) }
                itemsIndexed(history, key = { _, item -> item.song.id }) { index, entry ->
                    RecognitionSongCard(entry.song, DateTimeFormatter.ofPattern("MM-dd HH:mm").withZone(ZoneId.systemDefault()).format(Instant.ofEpochMilli(entry.recognizedAt)),
                        "recognition_history_song_$index") { play(history.map { it.song }, index) }
                }
            }
        } else {
            MusicRecognitionContent(state, permissionMessage, bottom,
                onAction = { when (state.phase) {
                    RecognitionPhase.RECORDING -> if (serviceRunning) RecognitionDesktopService.finish(context) else controller.finishRecording()
                    RecognitionPhase.RECOGNIZING -> if (serviceRunning) RecognitionDesktopService.stop(context) else controller.cancel()
                    else -> begin()
                } }, onSettings = {
                    context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:${context.packageName}")))
                }, onPlay = play, onDesktop = { begin(desktop = true) }, desktopStarting = desktopStarting,
                source = source, onSource = { if (!state.busy) source = it }, useProjection = useProjection,
                onProjectionMode = { if (!state.busy) useProjection = !useProjection }, topInset = topInset)
        }
    }
}

@Composable internal fun MusicRecognitionContent(state: RecognitionState, permissionMessage: String?, bottom: androidx.compose.ui.unit.Dp,
    onAction: () -> Unit, onSettings: () -> Unit, onPlay: (List<Song>, Int) -> Unit,
    onDesktop: (() -> Unit)? = null, desktopStarting: Boolean = false,
    source: RecognitionAudioSource = RecognitionAudioSource.MICROPHONE, onSource: ((RecognitionAudioSource) -> Unit)? = null,
    useProjection: Boolean = false, onProjectionMode: (() -> Unit)? = null, topInset: androidx.compose.ui.unit.Dp = 0.dp) {
    val colors = MiuixTheme.colorScheme
    BoxWithConstraints(Modifier.fillMaxSize()) {
        // Center the listening group in the usable area above the floating player, rather than
        // anchoring a fixed-height stack to the top of a full-screen scene.
        val usableHeight = (maxHeight - topInset - bottom - 24.dp).coerceAtLeast(0.dp)
        val orbSize = minOf(maxWidth - 44.dp,
            if (state.songs.isEmpty()) (usableHeight * .48f).coerceIn(190.dp, 300.dp)
            else (usableHeight * .34f).coerceIn(160.dp, 220.dp))
        val topSpace = if (state.songs.isEmpty() && permissionMessage == null)
            ((usableHeight - orbSize - 238.dp) / 2).coerceIn(12.dp, 64.dp) else 12.dp
        LazyColumn(Modifier.fillMaxSize().padding(bottom = bottom + 12.dp).testTag("recognition_content"), horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(start = 22.dp, end = 22.dp, top = topInset + topSpace, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)) {
            item {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Để mỗi giai điệu hay đều có tên", fontSize = 25.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center)
                    Text(if (source == RecognitionAudioSource.SYSTEM) "Nhận diện nhạc đang phát trên điện thoại" else "Đưa lại gần nguồn âm thanh, nghe một đoạn ngắn là được", fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
                    if (onSource != null) Row(Modifier.clip(RoundedCornerShape(20.dp)).background(colors.surfaceContainer).padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        (if (Build.VERSION.SDK_INT >= 29) RecognitionAudioSource.entries else listOf(RecognitionAudioSource.MICROPHONE)).forEach { option ->
                            Text(if (option == RecognitionAudioSource.SYSTEM) "Âm thanh hệ thống" else "Micrô",
                                Modifier.clip(RoundedCornerShape(16.dp)).background(if (source == option) colors.primary.copy(alpha = .12f) else androidx.compose.ui.graphics.Color.Transparent)
                                    .clickable(enabled = !state.busy, role = Role.Button) { onSource(option) }
                                    .padding(horizontal = 16.dp, vertical = 8.dp).testTag("recognition_source_${option.name}"),
                                color = if (source == option) colors.primary else colors.onSurfaceVariantSummary, fontSize = 13.sp)
                        }
                    }
                    if (source == RecognitionAudioSource.SYSTEM && onProjectionMode != null) Text(
                        if (useProjection) "Cách cấp quyền ghi âm · Chuyển sang trộn âm thanh hệ thống" else "Không dùng được trộn âm thanh hệ thống? Chuyển sang cách cấp quyền ghi âm",
                        Modifier.clickable(enabled = !state.busy, role = Role.Button, onClick = onProjectionMode)
                            .padding(horizontal = 10.dp, vertical = 6.dp).testTag("recognition_capture_mode"),
                        fontSize = 12.sp, color = colors.primary)
                }
            }
            item {
                RecognitionOrb(state, orbSize, onAction)
            }
            item {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(when (state.phase) {
                        RecognitionPhase.RECORDING -> "Đang nghe · ${state.seconds} giây"
                        RecognitionPhase.RECOGNIZING -> "Đang nhận diện nhạc…"
                        RecognitionPhase.MATCHED -> "Đã tìm thấy bài hát này"
                        RecognitionPhase.NO_MATCH -> "Thử nghe lại lần nữa"
                        RecognitionPhase.ERROR -> "Lần này chưa thể hoàn tất nhận diện"
                        else -> "Chạm để bắt đầu nhận diện bài hát"
                    }, Modifier.testTag("recognition_status"), fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text(when (state.phase) {
                        RecognitionPhase.RECORDING -> if (state.round == 0) "Vừa nghe vừa nhận diện, nhanh nhất 2 giây, lâu nhất 15 giây" else "Đang khớp lần thứ ${state.round} · Chạm để dừng nghe"
                        RecognitionPhase.RECOGNIZING -> (if (state.step == RecognitionStep.MATCH) "Đang khớp bài hát" else "Đang phân tích âm thanh") + " · Chạm để hủy"
                        RecognitionPhase.MATCHED -> "Chạm vào bài hát để phát hoặc tiếp tục nhận diện"
                        else -> state.message ?: "Hỗ trợ nhận diện bản nhạc gốc của bài hát đang phát"
                    }, fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                    permissionMessage?.let { Text(it, Modifier.testTag("recognition_permission_message"), color = colors.error, fontSize = 13.sp)
                        Text("Đi đến cài đặt", Modifier.clickable(role = Role.Button, onClick = onSettings).padding(10.dp), color = colors.primary, fontSize = 14.sp) }
                }
            }
            itemsIndexed(state.songs, key = { _, song -> song.id }) { index, song ->
                RecognitionSongCard(song, if (index == 0) "Kết quả nhận diện của Nhạc Vân NetEase" else "Có thể bạn đang nghe", "recognition_result_$index") { onPlay(state.songs, index) }
            }
            item {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                if (onDesktop != null) Text(if (desktopStarting) "Đang khởi chạy nhận diện nhạc trên máy tính…" else "Nhận diện nhạc bằng Đảo động trên máy tính  ↗",
                    Modifier.clip(RoundedCornerShape(20.dp)).background(colors.primary.copy(alpha = .08f))
                        .clickable(enabled = !state.busy && !desktopStarting, role = Role.Button, onClick = onDesktop)
                        .padding(horizontal = 18.dp, vertical = 12.dp).testTag("recognition_desktop"),
                    color = colors.primary.copy(alpha = if (state.busy) .45f else 1f), fontSize = 14.sp)
                Text((if (source == RecognitionAudioSource.SYSTEM) {
                    if (useProjection) "Cần cấp quyền ghi/phản chiếu màn hình của hệ thống; chỉ thu âm thanh, không ghi hình\nMột số ứng dụng không cho phép thu âm thanh, bạn có thể chuyển sang dùng micrô\n"
                    else "Đọc nhạc đang phát trên hệ thống, không sử dụng micrô\nNếu thiết bị không hỗ trợ, bạn có thể chuyển sang phương thức cấp quyền ghi âm\n"
                } else "Sử dụng micrô khi nhận diện\n") +
                    "Gửi dấu vân tay âm thanh đến Nhạc Vân NetEase; nếu không tìm thấy kết quả, sẽ gửi 10 giây âm thanh đầu tiên để hỗ trợ nhận diện lời bài hát\nThiết bị này chỉ lưu lịch sử bài hát; chế độ thông thường sẽ dừng khi rời trang, chế độ máy tính sẽ dừng khi đóng hoặc khóa màn hình",
                    Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 10.dp).testTag("recognition_disclosure"), fontSize = 11.sp,
                    lineHeight = 18.sp, color = colors.onSurfaceVariantSummary.copy(alpha = .75f), textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable private fun RecognitionOrb(state: RecognitionState, diameter: androidx.compose.ui.unit.Dp, onAction: () -> Unit) {
    val colors = MiuixTheme.colorScheme
    val active = state.busy
    val pulse = if (active) {
        val transition = rememberInfiniteTransition(label = "recognition")
        transition.animateFloat(0f, 1f, infiniteRepeatable(tween(1800, easing = LinearEasing)), label = "ripple").value
    } else 0f
    Box(Modifier.fillMaxWidth().height(diameter), contentAlignment = Alignment.Center) {
        Canvas(Modifier.size(diameter)) {
            val radius = size.minDimension / 2
            drawCircle(colors.primary.copy(alpha = .035f), radius)
            drawCircle(colors.primary.copy(alpha = .055f), radius * .79f)
            drawCircle(colors.primary.copy(alpha = .09f), radius * (.57f + state.level * .05f))
            if (active) drawCircle(colors.primary.copy(alpha = (1 - pulse) * .12f), radius * (.54f + pulse * .46f))
        }
        Box(Modifier.size(diameter * .486f).clip(CircleShape).background(colors.primary)
            .clickable(role = Role.Button, onClick = onAction).testTag("recognition_action"), contentAlignment = Alignment.Center) {
            Icon(if (state.phase == RecognitionPhase.RECORDING) MusicIcons.Stop else if (state.phase == RecognitionPhase.RECOGNIZING) MusicIcons.Close else MusicIcons.Microphone,
                when (state.phase) { RecognitionPhase.RECORDING -> "Dừng ghi âm và nhận diện"; RecognitionPhase.RECOGNIZING -> "Hủy nhận diện"; else -> "Bắt đầu nhận diện bài hát" },
                Modifier.size(diameter * .2f), tint = colors.onPrimary)
        }
    }
}

@Composable private fun RecognitionSongCard(song: Song, subtitle: String, tag: String, onPlay: () -> Unit) {
    val colors = MiuixTheme.colorScheme
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(22.dp)).background(colors.surfaceContainer)
        .clickable(role = Role.Button, onClick = onPlay).testTag(tag).padding(14.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        MusicCover(song.cover, Modifier.size(62.dp), cornerRadius = 14.dp)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(subtitle, fontSize = 11.sp, color = colors.primary)
            Text(song.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(song.artists, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, color = colors.onSurfaceVariantSummary)
        }
        Icon(MusicIcons.Play, "Phát", Modifier.size(24.dp), tint = colors.onSurface)
    }
}

@Composable private fun RecognitionIconButton(description: String, icon: androidx.compose.ui.graphics.vector.ImageVector, tag: String, onClick: () -> Unit) {
    Box(Modifier.size(42.dp).clip(CircleShape).background(MiuixTheme.colorScheme.surfaceContainer)
        .clickable(role = Role.Button, onClick = onClick).testTag(tag), contentAlignment = Alignment.Center) {
        Icon(icon, description, Modifier.size(22.dp))
    }
}
