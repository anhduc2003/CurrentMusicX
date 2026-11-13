package io.github.currencortex.music.feature.settings

import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.component.nativeui.SettingsGroup as Card

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import io.github.currencortex.music.data.effects.NeteaseSoundEffect
import io.github.currencortex.music.data.effects.NativeEffectTile
import io.github.currencortex.music.data.settings.EqualizerPreset
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.abs
import kotlinx.serialization.json.*

/** Original presets and local EQ share one visible selection, with independent verified apply paths. */
@Composable internal fun SoundEffectsMainScreen(vm: SoundEffectsViewModel, onBack: () -> Unit, onEqualizer: () -> Unit, onDetails: (SoundEffectKind) -> Unit,
    onAdaptive: () -> Unit = {}) {
    val adaptive by vm.adaptive.collectAsStateWithLifecycle()
    val options by vm.preferences.collectAsStateWithLifecycle()
    val tuning by vm.tuning.collectAsStateWithLifecycle()
    val manual by vm.manual.collectAsStateWithLifecycle()
    val runtime by vm.runtime.collectAsStateWithLifecycle()
    val playback by vm.presentation.collectAsStateWithLifecycle()
    val queueSong by vm.queueSong.collectAsStateWithLifecycle()
    val currentSong = playback.song ?: queueSong
    val catalog by vm.catalog.collectAsStateWithLifecycle()
    val page by vm.page.collectAsStateWithLifecycle()
    val more by vm.more.collectAsStateWithLifecycle()
    val tileLoading by vm.tileLoading.collectAsStateWithLifecycle()
    val actionError by vm.actionError.collectAsStateWithLifecycle()
    val lifecycle by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsStateWithLifecycle()
    val animationsActive = LocalMusicPageActive.current && LocalPageContainerSettled.current && lifecycle.isAtLeast(Lifecycle.State.RESUMED)
    val original by vm.original.collectAsStateWithLifecycle()
    val originalSmart by vm.originalSmart.collectAsStateWithLifecycle()
    val originalMood by vm.originalMood.collectAsStateWithLifecycle()
    val originalMaster by vm.originalAiMaster.collectAsStateWithLifecycle()
    val originalSpeaker by vm.originalSpeaker.collectAsStateWithLifecycle()
    val engineImport by vm.originalImport.collectAsStateWithLifecycle()
    val colors = MiuixTheme.colorScheme
    val dark = colors.background.luminance() < .5f
    val background = colors.background
    val listState = rememberLazyListState()
    val catalogListState = rememberLazyListState()
    val context = LocalContext.current
    val selectNeteaseApk = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) vm.importOriginal(uri)
    }
    var selectedEffect by remember { mutableStateOf<NeteaseSoundEffect?>(null) }
    var launchError by remember { mutableStateOf<String?>(null) }
    var expandedCatalog by rememberSaveable { mutableStateOf(false) }
    var showEngine by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(expandedCatalog) { if (expandedCatalog) vm.refreshMore() }
    var tileDetails by remember { mutableStateOf<NativeEffectTile?>(null) }
    BackHandler(enabled = LocalMusicPageActive.current && expandedCatalog) { expandedCatalog = false }
    val canApply = runtime.supported
    val status = when {
        !runtime.supported -> "Thiết bị này hiện chưa hỗ trợ bộ cân bằng âm thanh tích hợp"
        runtime.error != null -> runtime.error!!
        options.smartEnabled -> tuning.message
        else -> "Tự động điều chỉnh bộ cân bằng âm thanh tích hợp dựa trên đặc điểm âm thanh của bài hát"
    }
    val masterChosen = originalMaster.requestedEnabled
    val speakerChosen = originalSpeaker.requestedEnabled
    val moodChosen = originalMood.requestedEnabled
    val smartChosen = originalSmart.requestedEnabled
    val moodFailed = !moodChosen && originalMood.message != io.github.currencortex.music.core.media.OriginalMoodState().message
    val smartFailed = !smartChosen && originalSmart.message != io.github.currencortex.music.core.media.OriginalSmartState().message
    val singleChosen = original.requestedId != null || original.selectedId != null
    val anyEnabled = masterChosen || speakerChosen || moodChosen || smartChosen || singleChosen || options.smartEnabled || manual.enabled
    val activeStatus = when {
        !anyEnabled -> "Giữ lại các điều chỉnh của bạn, có thể bật bất cứ lúc nào vào lần sau"
        masterChosen -> originalMaster.message
        speakerChosen -> originalSpeaker.message
        moodChosen -> if (originalMood.enabled && !originalMood.loading && !playback.playing)
            "Đã bật, tự động khớp sau khi phát bài hát trên Nhạc Vân NetEase" else originalMood.message
        smartChosen -> if (originalSmart.enabled && !originalSmart.loading && !playback.playing)
            "Đã bật, tự động khớp sau khi phát bài hát trên Nhạc Vân NetEase" else originalSmart.message
        singleChosen -> when {
            original.loading -> "Đang tải, có thể hủy bất cứ lúc nào"
            original.error != null -> original.error!!
            !playback.playing -> "Đã sẵn sàng, có hiệu lực sau khi phát"
            original.processing -> "Đang xử lý âm thanh"
            else -> "Đang chờ xử lý âm thanh"
        }
        options.smartEnabled -> status
        runtime.error != null -> runtime.error!!
        !runtime.supported -> "Thiết bị này hiện chưa hỗ trợ bộ cân bằng âm thanh tích hợp"
        !playback.playing -> "Đã sẵn sàng, có hiệu lực sau khi phát"
        runtime.active -> "Đang sử dụng bộ cân bằng âm thanh tích hợp"
        else -> "Đang chờ kết nối với trình phát"
    }
    SettingsUiScope {
    Box(Modifier.fillMaxSize().background(background), contentAlignment = Alignment.TopCenter) {
    io.github.currencortex.music.ui.component.nativeui.SettingsScrollLayout(
        Modifier.widthIn(max = 680.dp).fillMaxSize().testTag("native_sound_effects_page"),background=background,topBar={
        SettingsTopBar(if (expandedCatalog) "Tuyển chọn hiệu ứng âm thanh chính thức" else "Hiệu ứng âm thanh",
            onBack = { if (expandedCatalog) expandedCatalog = false else onBack() }, backTag = "sound_effects_back")
        }) {topInset->
        LazyColumn(Modifier.fillMaxSize().testTag("sound_effects_screen"), state = if (expandedCatalog) catalogListState else listState,
            contentPadding = musicScrollPadding(PaddingValues(top=topInset,bottom = 20.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (!expandedCatalog) {
            item {
                Card(Modifier.padding(horizontal = 16.dp)) {
                    SettingsPreference("Hệ thống xử lý âm thanh", if (adaptive.enabled) "Đã bật · EQ động / âm trầm ảo / điều khiển động cơ" else "EQ động · âm trầm ảo · điều khiển động cơ",
                        Modifier.testTag("open_adaptive_audio"), onClick = onAdaptive)
                }
            }
            item { SettingsInfoHeader("Hiệu ứng âm thanh bậc thầy", MusicIcons.Headphones) }
            item(key = "masters") {
                val visible by remember { derivedStateOf { listState.layoutInfo.visibleItemsInfo.any { it.key == "masters" } } }
                NativeMasterPager(dark, moodChosen, originalMood.loading,
                    if (moodChosen) ((originalMood.visualExtra?.get("copywriting") as? JsonObject)
                        ?.get("description") as? JsonPrimitive)?.contentOrNull ?: activeStatus else "Điều chỉnh âm thanh thông minh theo cảm xúc của bài hát, thấu hiểu sắc thái cảm xúc ẩn sau mỗi ca khúc",
                    currentSong?.name, visible && animationsActive && selectedEffect == null && tileDetails == null && !showEngine,
                    true,
                    onMood = { if (moodChosen) vm.turnOff() else vm.originalMoodEnabled(true) },
                    masterChosen = masterChosen, masterReady = originalMaster.enabled && !originalMaster.loading, masterLoading = originalMaster.loading, masterStatus = if (masterChosen) originalMaster.description ?: originalMaster.message
                        else "AI phân tích nội dung bài hát và tùy chỉnh hiệu ứng âm thanh riêng cho từng bài",
                    onMaster = { if (masterChosen) vm.turnOff() else vm.originalAiMasterEnabled(true) },
                    speakerChosen = speakerChosen, speakerReady = originalSpeaker.enabled && !originalSpeaker.loading, speakerLoading = originalSpeaker.loading, speakerStatus = if (speakerChosen) originalSpeaker.message
                        else "Bật trải nghiệm đắm chìm qua loa ngoài, tùy chỉnh linh hoạt cho nhiều tình huống",
                    onSpeaker = { if (speakerChosen) vm.turnOff() else vm.originalSpeakerEnabled(true) },
                    moodEmotion = originalMood.emotion, songCover = currentSong?.cover, playing = playback.playing,
                    speakerScene = originalSpeaker.scene, onDetails = onDetails)
            }
            // Normal progress/success already lives in the master card and island.
            // Inserting a retry row on every successful use used to jump the list.
            if (!originalMaster.loading && !originalMaster.enabled &&
                originalMaster.message != io.github.currencortex.music.core.media.OriginalAiMasterState().message) item {
                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(originalMaster.message, Modifier.weight(1f).testTag("sound_master_status"), fontSize = 12.sp,
                        color = colors.onSurfaceVariantSummary)
                    if (!originalMaster.loading) MusicTextAction("Thử lại", { vm.originalAiMasterEnabled(true) })
                    if (!originalMaster.available) MusicTextAction("Nhập công cụ", { showEngine = true })
                }
            }
            if (!originalSpeaker.loading && !originalSpeaker.enabled &&
                originalSpeaker.message != io.github.currencortex.music.core.media.OriginalSpeakerState().message) item {
                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(originalSpeaker.message, Modifier.weight(1f).testTag("sound_speaker_status"), fontSize = 12.sp,
                        color = colors.onSurfaceVariantSummary)
                    if (!originalSpeaker.loading) MusicTextAction("Thử lại", { vm.originalSpeakerEnabled(true) })
                    if (!originalSpeaker.available) MusicTextAction("Nhập công cụ", { showEngine = true })
                }
            }
            if (moodFailed || moodChosen && !originalMood.loading && !originalMood.enabled) item {
                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(originalMood.message, Modifier.weight(1f).testTag("sound_original_mood_status"), fontSize = 12.sp,
                        color = colors.onSurfaceVariantSummary)
                    MusicTextAction("Thử lại", vm::retryOriginalMood, Modifier.testTag("sound_original_mood_retry"))
                    if (!originalMood.available) MusicTextAction("Nhập engine", { showEngine = true })
                }
            }
            item {
                Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 12.dp).height(28.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                     Text("Hiệu ứng âm thanh tuyển chọn chính thức", Modifier.weight(1f), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                     Text(if (expandedCatalog) "Thu gọn" else "Thêm ›",
                        Modifier.testTag("sound_catalog_expand").clip(RoundedCornerShape(8.dp))
                            .clickable(role = Role.Button) { expandedCatalog = !expandedCatalog }
                            .padding(start = 12.dp, top = 4.dp, bottom = 4.dp),
                        fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                }
            }
            if (smartFailed) item {
                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(originalSmart.message, Modifier.weight(1f), fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                     MusicTextAction("Thử lại", vm::retryOriginalSmart, Modifier.testTag("sound_original_smart_retry"))
                     if (!originalSmart.available) MusicTextAction("Nhập engine", { showEngine = true })
                }
            }
            item(key = "smart") {
                val banner = page.banner.firstOrNull()
                val smartBanner = banner == null || banner.smart
                val bannerChosen = if (smartBanner) smartChosen else tileLoading == banner?.id ||
                    (original.requestedId ?: original.selectedId)?.let { it == banner?.id } == true
                val bannerLoading = if (smartBanner) originalSmart.loading else tileLoading == banner?.id || original.loading && bannerChosen
                val visible by remember { derivedStateOf { listState.layoutInfo.visibleItemsInfo.any { it.key == "smart" } } }
                NativeSmartBanner(banner, dark, bannerChosen, bannerLoading,
                    if (bannerChosen) activeStatus else if (smartBanner && smartFailed) originalSmart.message else null,
                    visible && animationsActive,
                    true,
                    onApply = {
                        if (bannerChosen) vm.turnOff()
                        else if (banner != null) vm.useTile(banner)
                        else vm.originalSmartEnabled(true)
                    })
            }
            }
            if (page.error != null && !expandedCatalog) item {
                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(page.error!!, Modifier.weight(1f), fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                     MusicTextAction("Thử lại", vm::refreshPage, Modifier.testTag("sound_page_retry"))
                }
            }
            if (actionError != null) item {
                Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(actionError!!, Modifier.weight(1f).testTag("sound_tile_error"), fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                     MusicTextAction("Đóng", { vm.actionError.value = null })
                }
            }
             if (!expandedCatalog && catalog.loading) item { Text("Đang tải hiệu ứng âm thanh Nhạc Vân NetEase…", Modifier.padding(horizontal = 16.dp)
                .testTag("sound_catalog_loading"), fontSize = 13.sp, color = colors.onSurfaceVariantSummary) }
            if (!expandedCatalog && catalog.error != null) item { Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(catalog.error!!, Modifier.weight(1f), fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                 MusicTextAction("Thử lại", vm::refreshCatalog, Modifier.testTag("sound_catalog_retry"))
            } }
            if (!expandedCatalog && !catalog.loading && catalog.error == null && catalog.items.isEmpty()) item {
                 Text("Nhạc Vân NetEase chưa có hiệu ứng âm thanh khả dụng", Modifier.padding(horizontal = 16.dp), fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
            }
            if (!expandedCatalog) item {
                BoxWithConstraints(Modifier.fillMaxWidth().testTag("sound_official_effects")) {
                    val cardWidth = ((maxWidth - 48.dp) / 3f).coerceIn(104.dp, 196.dp)
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(if (page.featured.isEmpty()) catalog.items else page.featured.map { tile ->
                            catalog.items.firstOrNull { it.id == tile.id } ?: NeteaseSoundEffect(tile.id, tile.name, tile.description)
                        }, key = { it.id }) { effect ->
                            val chosen = tileLoading == effect.id || (original.requestedId ?: original.selectedId) == effect.id
                            OfficialSoundCard(effect, Modifier.width(cardWidth), chosen,
                                when {
                                     tileLoading == effect.id || original.loading && chosen -> "Đang tải…"
                                     original.error != null && chosen -> "Tải thất bại"
                                     chosen -> if (original.processing && playback.playing) "Đang sử dụng" else "Đã chọn"
                                    effect.legacyMemberOriginal -> if (effect.memberType == 4) "SVIP" else "VIP"
                                     else -> "Miễn phí"
                                }, enabled = true,
                                tile = page.featured.firstOrNull { it.id == effect.id }, dark = dark,
                                onDetails = {
                                    launchError = null
                                    if (catalog.items.none { it.id == effect.id }) tileDetails = page.featured.firstOrNull { it.id == effect.id }
                                    else selectedEffect = effect
                                }, onApply = {
                                    when {
                                        chosen -> vm.turnOff()
                                        catalog.items.none { it.id == effect.id } -> page.featured.firstOrNull { it.id == effect.id }?.let(vm::useTile)
                                        else -> vm.useOriginal(effect)
                                    }
                                })
                        }
                    }
                }
            } else {
                 if (more.loading) item { Text("Đang tải hiệu ứng âm thanh tuyển chọn chính thức…", Modifier.padding(16.dp), fontSize = 13.sp,
                    color = colors.onSurfaceVariantSummary) }
                if (more.error != null) item { Row(Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(more.error!!, Modifier.weight(1f), fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                     MusicTextAction("Thử lại", vm::refreshMore, Modifier.testTag("sound_more_retry"))
                } }
                if (!more.loading && more.error == null && more.featured.isEmpty()) item {
                     Text("Chưa có hiệu ứng âm thanh tuyển chọn chính thức", Modifier.padding(16.dp), fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                }
                items(more.featured, key = { "more_${it.id}" }) { tile ->
                    val chosen = tileLoading == tile.id || (if (tile.smart) smartChosen else (original.requestedId ?: original.selectedId) == tile.id)
                    NativeMoreEffectRow(tile, chosen, tileLoading == tile.id || original.loading && chosen,
                        true,
                        onDetails = { tileDetails = tile }, onApply = { if (chosen) vm.turnOff() else vm.useTile(tile) })
                }
            }
            if (!expandedCatalog) {
            if (original.error != null) item {
                Card(Modifier.padding(horizontal = 16.dp)) { Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                     Text("Hiệu ứng âm thanh gốc · ${original.requestedName ?: original.selectedName.orEmpty()}",
                        Modifier.testTag("sound_original_selected"), fontSize = 17.sp)
                    if (original.loading) {
                        Text(when {
                         original.permissionPending -> "Đang xác minh quyền lợi hội viên Black Vinyl với Nhạc Vân NetEase…"
                         original.restoring -> "Đang kiểm tra lại và khôi phục ${original.requestedName.orEmpty()}…"
                         else -> "Đang tải xuống và xác minh ${original.requestedName.orEmpty()}…"
                        }, Modifier.testTag("sound_original_loading"), fontSize = 13.sp)
                    }
                    original.error?.let { Text(it, Modifier.testTag("sound_original_error"), color = colors.onSurfaceVariantSummary, fontSize = 13.sp) }
                    if (original.selectedId != null) {
                        Text(when {
                            original.requestedId != null && original.requestedId != original.selectedId ->
                                 "Hiện vẫn đang sử dụng ${original.selectedName.orEmpty()}, sẽ chuyển đổi khi hiệu ứng âm thanh mới sẵn sàng"
                             !playback.playing -> "Cài đặt sẵn đã sẵn sàng, hãy bắt đầu phát trên thiết bị để xử lý"
                             original.processing -> "Đang xử lý âm thanh bằng engine gốc"
                            else -> "Đang chờ engine gốc xử lý âm thanh…"
                        }, Modifier.testTag("sound_original_processing"), color = colors.onSurfaceVariantSummary, fontSize = 13.sp)
                        Text("Đã giữ lại thông số bộ chỉnh âm, bạn có thể chuyển lại bất cứ lúc nào", color = colors.onSurfaceVariantSummary, fontSize = 12.sp)
                    }
                    if (original.error != null && original.available && original.requestedId != null &&
                        original.requestedId !in original.unsupported && !original.loading)
                         MusicTextAction("Thử tải lại", vm::retryOriginal, Modifier.testTag("sound_original_retry"))
                    if (original.selectedId != null || original.requestedId != null)
                         MusicTextAction("Tắt hiệu ứng âm thanh", vm::turnOff, Modifier.testTag("sound_original_clear"))
                } }
            }

            if (options.smartEnabled) item {
                Card(Modifier.padding(horizontal = 16.dp)) { Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                     Text(playback.song?.name ?: "Đang chờ phát", fontSize = 17.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    val analysis = tuning.analysis
                    when {
                         tuning.loading -> Text("Đang đọc đặc trưng âm thanh của bài hát…", color = colors.onSurfaceVariantSummary, fontSize = 13.sp)
                        analysis != null -> {
                            val metadata = buildList {
                                analysis.bpm?.let { add("${it.roundToInt()} BPM") }
                                analysis.loudnessLufs?.let { add(String.format(Locale.ROOT, "%.1f LUFS", it)) }
                            }.joinToString(" · ")
                            if (metadata.isNotBlank()) Text(metadata, Modifier.testTag("sound_song_analysis"), fontSize = 13.sp, color = colors.primary)
                            if (analysis.emotions.isNotEmpty()) Text(analysis.emotions.take(4).joinToString(" · "),
                                maxLines = 2, color = colors.onSurfaceVariantSummary, fontSize = 13.sp)
                        }
                        else -> Text(tuning.message, color = colors.onSurfaceVariantSummary, fontSize = 13.sp)
                    }
                    tuning.gains?.let { gains ->
                         Text(if (canApply && runtime.active && tuning.active) "Đã áp dụng chỉnh âm cho bài hát hiện tại" else "Có hiệu lực sau khi bắt đầu phát nhạc trên thiết bị này",
                            Modifier.testTag("sound_tuning_applied"), fontSize = 13.sp, color = colors.primary)
                        TuningCurve(gains)
                    }
                } }
            }

            }
        }
    }
    }
    tileDetails?.let { tile -> MusicDialog(tile.name, onDismiss = { tileDetails = null }) {
        Text(tile.description, fontSize = 14.sp)
         MusicTextAction("Sử dụng", { vm.useTile(tile); tileDetails = null })
         MusicTextAction("Quay lại trang hiệu ứng âm thanh", { tileDetails = null })
    } }
     if (showEngine) MusicDialog("Engine hiệu ứng âm thanh gốc", onDismiss = { showEngine = false }) {
         Text(if (original.available && originalMood.available) "Engine đã sẵn sàng, hỗ trợ hiệu ứng âm thanh gốc và AI điều chỉnh theo cảm xúc, không cần nhập thêm."
             else "Nhập engine tương thích từ Nhạc Vân NetEase trên thiết bị này hoặc từ gói cài đặt. Khi sử dụng hiệu ứng dành cho hội viên, hệ thống sẽ xác minh quyền lợi của tài khoản hiện tại.",
            fontSize = 14.sp, color = colors.onSurfaceVariantSummary)
        engineImport.message?.let { Text(it, Modifier.testTag("sound_engine_import_status"), fontSize = 13.sp,
            color = if (engineImport.failed) colors.onSurfaceVariantSummary else colors.primary) }
        if (!original.available || !originalMood.available) {
             MusicTextAction("Nhập từ Nhạc Vân NetEase đã cài đặt", vm::importInstalledOriginal,
                Modifier.testTag("sound_import_installed"), enabled = !engineImport.loading)
             MusicTextAction("Chọn gói cài đặt Nhạc Vân NetEase", {
                selectNeteaseApk.launch(arrayOf("application/vnd.android.package-archive", "application/octet-stream"))
            }, Modifier.testTag("sound_import_apk"), enabled = !engineImport.loading)
        }
         MusicTextAction("Hoàn tất", { showEngine = false })
    }
    selectedEffect?.let { effect ->
        val unavailable = vm.originalUnavailableReason(effect)
        MusicDialog(effect.name, onDismiss = { selectedEffect = null }) {
            Text(effect.description, fontSize = 15.sp)
            Text(unavailable ?: if (effect.legacyMemberOriginal)
                 "Hiệu ứng này yêu cầu tài khoản Nhạc Vân NetEase ${if (effect.memberType == 4) "SVIP" else "hội viên vinyl"} còn hiệu lực. Trước khi sử dụng, quyền lợi của tài khoản hiện tại sẽ được xác minh với Nhạc Vân NetEase và hiệu ứng sẽ tự động tắt khi hết hạn; bạn có thể đăng nhập tại “Của tôi → Tài khoản Nhạc Vân NetEase”. Lựa chọn sẽ được lưu và xác minh lại sau khi khởi động lại."
                 else "Sử dụng preset gốc do Nhạc Vân NetEase công khai cùng engine hiệu ứng âm thanh nguyên bản. Lựa chọn sẽ được lưu, tải xuống và xác minh lại sau khi khởi động lại; khi bật, tạm thời thay thế tính năng chỉnh âm thông minh và bộ chỉnh âm trên thiết bị này.",
                color = colors.onSurfaceVariantSummary, fontSize = 13.sp)
             MusicTextAction(if (unavailable != null) "Kiểm tra lại và sử dụng" else if (effect.legacyMemberOriginal) "Xác minh quyền lợi và sử dụng" else if (original.selectedId == effect.id) "Tải lại hiệu ứng âm thanh gốc" else "Sử dụng hiệu ứng âm thanh gốc", {
                vm.useOriginal(effect)
                selectedEffect = null
            }, Modifier.testTag("sound_use_original"))
            launchError?.let { Text(it, color = colors.onSurfaceVariantSummary, fontSize = 13.sp) }
             MusicTextAction("Đi đến hiệu ứng âm thanh Nhạc Vân NetEase", {
                try {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("orpheus://effect")).setPackage("com.netease.cloudmusic"))
                    launchError = null
                 } catch (_: ActivityNotFoundException) { launchError = "Vui lòng cài đặt ứng dụng Nhạc Vân NetEase trước" }
                 catch (_: SecurityException) { launchError = "Hiện không thể mở Nhạc Vân NetEase" }
            }, Modifier.testTag("sound_open_netease"))
             MusicTextAction("Quay lại trang hiệu ứng âm thanh", { selectedEffect = null }, Modifier.testTag("sound_official_close"))
        }
    }
    }
}

@Composable private fun OfficialSoundCard(effect: NeteaseSoundEffect, modifier: Modifier, chosen: Boolean,
    status: String, enabled: Boolean, tile: NativeEffectTile? = null, dark: Boolean = false,
    onDetails: () -> Unit, onApply: () -> Unit) {
    val background = if (dark) Color(0xFF1C2028) else Color(0xFFE4EAF2)
    Box(modifier.clip(RoundedCornerShape(12.dp)).background(background)
        .border(if (chosen) 1.5.dp else 0.dp, if (chosen) MiuixTheme.colorScheme.primary else Color.Transparent, RoundedCornerShape(12.dp))) {
        if (tile != null && tile.image(dark).isNotBlank()) EffectsArtwork(tile.image(dark), Modifier.matchParentSize())
        else if (effect.artwork.isNotBlank()) EffectsArtwork(effect.artwork, Modifier.matchParentSize())
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.fillMaxWidth().height(62.dp)) {
                EffectsMembership(tile?.membership ?: effect.memberType, Modifier.align(Alignment.TopEnd).padding(8.dp))
                if (tile == null && effect.artwork.isBlank()) Icon(MusicIcons.Headphones, null,
                    Modifier.align(Alignment.Center).size(28.dp), tint = MiuixTheme.colorScheme.onSurface.copy(alpha = .4f))
            }
            Column(Modifier.fillMaxWidth().testTag("sound_official_${effect.id}").semantics { selected = chosen }
                .clickable(role = Role.Button, onClick = onDetails).padding(horizontal = 6.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(effect.name, fontSize = 14.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(tile?.description ?: effect.description, Modifier.padding(top = 2.dp),
                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary, fontSize = 10.sp,
                    maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
             SoundAction(if (chosen) (if (status == "Đang tải…") "Đang tải…" else "Hủy") else "Sử dụng", onApply,
                Modifier.padding(top = 10.dp, bottom = 12.dp).width(60.dp).testTag("sound_apply_${effect.id}"), enabled)
        }
    }
}

@Composable private fun EffectButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier,
    selected: Boolean = false, enabled: Boolean = true) {
    val colors = MiuixTheme.colorScheme
    Box(modifier.heightIn(min = 44.dp).clip(RoundedCornerShape(14.dp))
        .background(if (selected) colors.primary.copy(alpha = if (enabled) 1f else .35f)
            else colors.onSurface.copy(alpha = if (enabled) .06f else .025f))
        .clickable(enabled = enabled, role = Role.Button, onClick = onClick).padding(horizontal = 16.dp, vertical = 11.dp),
        contentAlignment = Alignment.Center) {
        Text(label, color = if (selected) colors.onPrimary else colors.onSurface, fontSize = 13.sp)
    }
}

@Composable private fun SoundAction(label: String, onClick: () -> Unit, modifier: Modifier, enabled: Boolean) {
    NativeEffectsAction(label, onClick, modifier, enabled)
}

@Composable private fun TuningCurve(gains: List<Int>) {
    val accent = MiuixTheme.colorScheme.primary
    val grid = MiuixTheme.colorScheme.onSurfaceVariantSummary.copy(alpha = .18f)
    Canvas(Modifier.fillMaxWidth().height(60.dp).testTag("sound_tuning_curve")) {
        drawLine(grid, Offset(0f, size.height / 2), Offset(size.width, size.height / 2), 1.dp.toPx())
        val path = Path()
        gains.forEachIndexed { index, value ->
            val x = 5.dp.toPx() + (size.width - 10.dp.toPx()) * index / 4f
            val y = size.height / 2 - value.coerceIn(-300, 300) / 300f * size.height * .4f
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
            drawCircle(accent, 2.5.dp.toPx(), Offset(x, y))
        }
        drawPath(path, accent, style = Stroke(2.dp.toPx()))
    }
}
