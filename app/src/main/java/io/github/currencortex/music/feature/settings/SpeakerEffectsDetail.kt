package io.github.currencortex.music.feature.settings

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.core.media.OriginalSpeakerState
import io.github.currencortex.music.data.effects.OriginalSpeakerScene
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.LocalMusicPageActive
import io.github.currencortex.music.ui.component.LocalMusicBottomInset
import io.github.currencortex.music.ui.component.musicScrollPadding
import io.github.currencortex.music.ui.component.MusicDialog
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Text

/** Native presentation of the original outside detail. Browsing a scene never applies its DSP. */
@Composable internal fun SpeakerEffectsDetail(
    state: OriginalSpeakerState,
    song: Song?,
    onBack: () -> Unit,
    onEnable: (OriginalSpeakerScene) -> Unit,
    onDisable: () -> Unit,
    previewing: Boolean = false,
    previewScene: OriginalSpeakerScene? = null,
    onPreviewStart: (OriginalSpeakerScene) -> Unit = {},
    onPreviewStop: () -> Unit = {},
) {
    var browsing by rememberSaveable { mutableStateOf(state.scene.name) }
    val scene = OriginalSpeakerScene.entries.firstOrNull { it.name == browsing } ?: OriginalSpeakerScene.NORMAL
    val art = remember(scene) { speakerDetailArt(scene) }
    val scroll = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var information by rememberSaveable { mutableStateOf(false) }
    val active = LocalMusicPageActive.current
    val currentPreviewing by rememberUpdatedState(previewing)
    val stopPreview by rememberUpdatedState(onPreviewStop)
    DisposableEffect(Unit) { onDispose { if (currentPreviewing) stopPreview() } }
    fun leave() { if (previewing) onPreviewStop(); onBack() }
    BackHandler(enabled = active && !information) { leave() }
    val currentChoice = state.requestedEnabled && state.scene == scene
    val currentReady = state.enabled && state.scene == scene
    val previewHere = previewing && previewScene == scene

    BoxWithConstraints(Modifier.fillMaxSize().testTag("sound_speaker_detail"), contentAlignment = Alignment.TopCenter) {
        val width = maxWidth.coerceAtMost(680.dp)
        val heroHeight = (width * .9067f).coerceIn(240.dp, 390.dp)
        Box(Modifier.widthIn(max = 680.dp).fillMaxSize().background(art.base)) {
            Crossfade(art, animationSpec = tween(600), label = "speaker-environment-backdrop") { backdrop ->
                Box(Modifier.fillMaxSize()) {
                    EffectsArtwork(NativeEffectsAssets.local(backdrop.file("background.png")), Modifier.matchParentSize())
                    Box(Modifier.fillMaxWidth().aspectRatio(750f / 1624f).graphicsLayer {
                        translationY = if (scroll.firstVisibleItemIndex > 0) -size.height else -scroll.firstVisibleItemScrollOffset.toFloat()
                    }) {
                        EffectsVideo(backdrop.file(if (currentReady) "active.mp4" else "ambient.mp4"),
                            backdrop.file("background.png"), active && !information && scroll.firstVisibleItemIndex == 0,
                            Modifier.matchParentSize())
                    }
                }
            }
            LazyColumn(Modifier.fillMaxSize().statusBarsPadding().testTag("sound_speaker_detail_scroll"), state = scroll,
                contentPadding = musicScrollPadding(PaddingValues(bottom = 140.dp))) {
                item(key = "speaker-hero") { Spacer(Modifier.height(heroHeight)) }
                item(key = "speaker-heading") {
                    Column(Modifier.fillMaxWidth().padding(horizontal = 28.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            Text(art.title + if (currentReady) " · Đang tăng cường" else "", color = Color.White,
                                fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.testTag("sound_speaker_detail_title"))
                            EffectsMembership(4)
                        }
                        Text(art.description, Modifier.padding(top = 10.dp), color = Color.White.copy(alpha = .65f),
                            fontSize = 13.sp, lineHeight = 18.sp, textAlign = TextAlign.Center)
                    }
                }
                item(key = "speaker-scenes") {
                    Row(Modifier.padding(horizontal = 28.dp, vertical = 24.dp).fillMaxWidth().height(64.dp)
                        .clip(RoundedCornerShape(32.dp)).background(Color.White.copy(alpha = .13f)).padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        OriginalSpeakerScene.entries.forEach { candidate ->
                            val candidateArt = speakerDetailArt(candidate)
                            val selected = candidate == scene
                            Column(Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(28.dp))
                                .background(if (selected) Color.White.copy(alpha = .20f) else Color.Transparent)
                                .clickable(role = Role.Tab) {
                                    if (candidate != scene) {
                                        if (previewing) onPreviewStop()
                                        browsing = candidate.name
                                        scope.launch { scroll.animateScrollToItem(0) }
                                    }
                                }.semantics { this.selected = selected }
                                .testTag("sound_speaker_detail_scene_${candidate.wireName}"),
                                horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                EffectsArtwork(NativeEffectsAssets.local(candidateArt.file("icon.png")), Modifier.size(27.dp), scale = ContentScale.Fit)
                                Text(if (state.enabled && state.scene == candidate) "Đang sử dụng" else candidateArt.shortTitle,
                                    color = Color.White.copy(alpha = if (selected) 1f else .62f), fontSize = 10.sp)
                            }
                        }
                    }
                }
                item(key = "speaker-tips-$browsing") {
                    EffectsArtwork(NativeEffectsAssets.local(art.file("tips.png")),
                        Modifier.padding(horizontal = 16.dp).fillMaxWidth().aspectRatio(1029f / art.tipsHeight)
                            .testTag("sound_speaker_detail_tips"), art.accessibleTips, ContentScale.Fit)
                }
            }
            Row(Modifier.fillMaxWidth().statusBarsPadding().height(56.dp).background(
                Brush.verticalGradient(listOf(art.base.copy(alpha = .70f), art.base.copy(alpha = 0f)))),
                verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(52.dp).clickable(role = Role.Button) { leave() }
                    .semantics { contentDescription = "Quay lại hiệu ứng âm thanh" }.testTag("sound_speaker_detail_back"), contentAlignment = Alignment.Center) {
                    Canvas(Modifier.size(20.dp)) {
                        drawLine(Color.White, Offset(size.width * .65f, size.height * .15f),
                            Offset(size.width * .30f, size.height * .5f), 1.8.dp.toPx())
                        drawLine(Color.White, Offset(size.width * .30f, size.height * .5f),
                            Offset(size.width * .65f, size.height * .85f), 1.8.dp.toPx())
                    }
                }
                Text("Hiệu ứng loa ngoài", Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center,
                    fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Box(Modifier.size(52.dp).clickable(role = Role.Button) { information = true }
                    .semantics { contentDescription = "Thông tin hiệu ứng loa ngoài" }.testTag("sound_speaker_detail_info"), contentAlignment = Alignment.Center) {
                    Box(Modifier.size(25.dp).background(Color.White.copy(alpha = .12f), CircleShape), contentAlignment = Alignment.Center) {
                        Text("i", color = Color.White.copy(alpha = .8f), fontSize = 17.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
            Column(Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(bottom = LocalMusicBottomInset.current).background(Brush.verticalGradient(
                listOf(art.base.copy(alpha = 0f), art.base.copy(alpha = .95f), art.base)))
                .navigationBarsPadding().padding(start = 40.dp, end = 40.dp, top = 28.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (currentChoice && !currentReady) Text(state.message, Modifier.padding(bottom = 10.dp)
                    .testTag("sound_speaker_detail_status"), color = Color.White.copy(alpha = .75f),
                    fontSize = 11.sp, maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
                Box(Modifier.fillMaxWidth().height(48.dp).clip(RoundedCornerShape(24.dp))
                    .background(if (currentChoice) Color.White.copy(alpha = .13f) else Color(0xFFE7CC97))
                    .clickable(role = Role.Button) {
                        if (previewing) onPreviewStop()
                        if (currentChoice) onDisable() else onEnable(scene)
                    }.testTag("sound_speaker_detail_apply"), contentAlignment = Alignment.Center) {
                    Text(if (currentChoice) (if (state.loading) "Hủy tải" else "Ngừng sử dụng")
                        else if (state.requestedEnabled) "Chuyển sang dùng môi trường này" else "Dùng ngay",
                        color = if (currentChoice) Color.White else Color(0xFF2C2519), fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
                Text(if (previewHere) "Kết thúc nghe thử" else "Nghe thử hiệu ứng ›", Modifier.padding(top = 12.dp)
                    .heightIn(min = 28.dp).clickable(role = Role.Button) {
                        if (previewHere) onPreviewStop() else onPreviewStart(scene)
                    }.testTag("sound_speaker_detail_preview"), color = Color.White.copy(alpha = .65f), fontSize = 13.sp)
                if (previewHere && song != null) Text(song.name, color = Color.White.copy(alpha = .5f), fontSize = 10.sp,
                    maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
    if (information) MusicDialog("Thông tin hiệu ứng loa ngoài", onDismiss = { information = false }) {
        Column(Modifier.heightIn(max = 420.dp).testTag("sound_speaker_detail_information")) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                item { SpeakerInformationSection("Giới thiệu hiệu ứng", "Tối ưu cho đặc tính âm thanh của loa điện thoại, cung cấp ba chế độ môi trường: phổ thông, phòng tắm và trước khi ngủ. Xem trước môi trường sẽ không làm thay đổi hiệu ứng đang dùng; chỉ khi nhấn nút bên dưới, hiệu ứng mới được chuyển chính thức.") }
                item { SpeakerInformationSection("Chế độ phổ thông", "Phù hợp với hầu hết không gian. Tự chọn chất liệu hiệu ứng gốc theo chiều dọc/ngang của màn hình và âm lượng hệ thống, giúp âm thanh đầy đặn, trong trẻo hơn.") }
                item { SpeakerInformationSection("Không gian phòng tắm", "Tạo tiếng vang tự nhiên và điều chỉnh dải trung cao, giúp âm thanh rõ hơn trong phòng tắm và những không gian tương tự.") }
                item { SpeakerInformationSection("Đồng hành trước giờ ngủ", "Giảm âm trầm sâu và các dải tần gây kích thích mạnh, tạo cảm giác nghe yên tĩnh, thư thái.") }
                item { SpeakerInformationSection("Phạm vi sử dụng", "Cần có quyền lợi SVIP còn hiệu lực trên tài khoản Nhạc Vân NetEase hiện tại và bộ máy hiệu ứng âm thanh gốc. Chỉ xử lý âm thanh khi phát qua loa điện thoại; quá trình xử lý sẽ tạm dừng khi kết nối tai nghe. Sau khi kết thúc nghe thử, lựa chọn trước đó sẽ được khôi phục.") }
            }
        }
    }
}

@Composable private fun SpeakerInformationSection(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text(body, fontSize = 13.sp, lineHeight = 20.sp)
    }
}

private data class SpeakerDetailArt(val key: String, val shortTitle: String, val title: String,
    val description: String, val base: Color, val tipsHeight: Float, val accessibleTips: String) {
    fun file(kind: String) = "speaker-detail-$key-$kind"
}

private fun speakerDetailArt(scene: OriginalSpeakerScene): SpeakerDetailArt = when (scene) {
    OriginalSpeakerScene.NORMAL -> SpeakerDetailArt("normal", "Phổ thông", "Chế độ phổ thông", "Tự điều chỉnh theo chiều dọc/ngang, giọng hát rõ ràng, âm thanh đầy đặn",
        Color(0xFF4F8FD6), 1650f, "Chế độ phổ thông · Mẹo sử dụng hiệu ứng âm thanh. Tự điều chỉnh theo chiều dọc/ngang, phù hợp với tư thế cầm điện thoại; âm thanh đầy đặn, trong trẻo hơn.")
    OriginalSpeakerScene.SHOWER -> SpeakerDetailArt("shower", "Phòng tắm", "Không gian phòng tắm", "Thêm tiếng vang, không ngại tiếng nước át, thoải mái tận hưởng lúc tắm",
        Color(0xFF302FA8), 2241f, "Không gian phòng tắm · Mẹo sử dụng hiệu ứng âm thanh. Tiếng vang tự nhiên, làm nổi dải trung cao để giọng hát rõ hơn.")
    OriginalSpeakerScene.SLEEP -> SpeakerDetailArt("sleep", "Trước giờ ngủ", "Đồng hành trước giờ ngủ", "Giảm âm trầm sâu và dải tần gây kích thích mạnh, cùng bạn yên tâm đi vào giấc ngủ",
        Color(0xFF08548D), 2379f, "Đồng hành trước giờ ngủ · Mẹo sử dụng hiệu ứng âm thanh. Thư thái nghe bên gối; tinh chỉnh âm thanh khoa học, thấu hiểu giấc ngủ.")
}
