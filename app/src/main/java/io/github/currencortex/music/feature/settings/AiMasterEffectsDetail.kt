package io.github.currencortex.music.feature.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.LocalMusicBottomInset
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.media.OriginalAiMasterState
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.LocalMusicPageActive
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text

private val MasterGold = Color(0xFFE4CC9F)
private data class MasterDiyOption(val key: String, val title: String, val subtitle: String, val icon: String)
private val masterDiyOptions = listOf(
    MasterDiyOption("en_bass", "Tăng cường âm trầm", "Trầm sâu, mạnh mẽ, tạo cảm giác đắm chìm", "bass"),
    MasterDiyOption("en_treble", "Tăng cường âm cao", "Trong trẻo, không chói tai, tăng độ xuyên thấu", "treble"),
    MasterDiyOption("en_vocal", "Tăng cường giọng hát", "Giọng hát rõ nét, tăng cường chi tiết", "vocal"),
    MasterDiyOption("en_vibe", "Tăng không khí", "Làm mềm ranh giới, khơi gợi cảm xúc", "vibe"),
    MasterDiyOption("en_live", "Tăng cảm giác trực tiếp", "Định vị âm thanh ba chiều, như đang ở trên sân khấu", "live"),
)

/** Native reconstruction of the original black/gold master page and its transactional DIY sheet. */
@OptIn(ExperimentalLayoutApi::class)
@Composable internal fun AiMasterEffectsDetail(
    state: OriginalAiMasterState,
    song: Song?,
    playing: Boolean,
    onBack: () -> Unit,
    onToggle: () -> Unit,
    onPlayback: () -> Unit,
    onSaveDiy: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
    onPreview: (Boolean) -> Unit = {},
    previewing: Boolean = false,
    previewProcessed: Boolean = false,
    onPreviewStop: () -> Unit = {},
) {
    var information by rememberSaveable { mutableStateOf(false) }
    var preferences by rememberSaveable { mutableStateOf(false) }
    val lifecycle by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsStateWithLifecycle()
    val animated = LocalMusicPageActive.current && lifecycle.isAtLeast(Lifecycle.State.RESUMED) && !information && !preferences
    val using = state.enabled || state.requestedEnabled
    BackHandler(enabled = LocalMusicPageActive.current) { when { preferences -> preferences = false; information -> information = false; else -> onBack() } }
    BoxWithConstraints(modifier.fillMaxSize().background(Brush.verticalGradient(
        0f to Color(0xFF2D2000), .535f to Color(0xFF0E0900), 1f to Color(0xFF0E0900)))
        .testTag("sound_ai_master_detail")) {
        val pageWidth = maxWidth.coerceAtMost(600.dp)
        Column(Modifier.align(Alignment.TopCenter).widthIn(max = 600.dp).fillMaxSize()
            .statusBarsPadding().navigationBarsPadding().padding(bottom = LocalMusicBottomInset.current)) {
            Row(Modifier.fillMaxWidth().height(52.dp).padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                MasterDetailIcon("Quay lại hiệu ứng âm thanh", "sound_ai_master_back", onBack) {
                    Icon(Icons.Default.ArrowBack, null, tint = Color.White, modifier = Modifier.size(21.dp))
                }
                Spacer(Modifier.weight(1f))
MasterDetailIcon("Giới thiệu Bậc thầy chỉnh âm AI", "sound_ai_master_info", { information = true }) {                    Icon(Icons.Default.Info, null, tint = Color.White.copy(alpha = .6f), modifier = Modifier.size(21.dp))
                }
            }
            Column(Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState())
                .testTag("sound_ai_master_detail_scroll"), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(Modifier.fillMaxWidth().height(pageWidth * .44f), contentAlignment = Alignment.TopCenter) {
                    // Original streams carry a left alpha plane and right colour plane. Local frames are
                    // composited once during packaging, preserving one transparent native animated sphere.
                    EffectsArtwork(NativeEffectsAssets.local(if (animated) "ai_master_detail_ball.webp" else "ai_master_detail_ball.png"),
                        Modifier.size(pageWidth * .667f).offset(y = -pageWidth * .08f), scale = ContentScale.Fit,
                        animated = animated)
                }
                EffectsArtwork(NativeEffectsAssets.local("ai_master_detail_title.png"), Modifier.width(159.dp).height(26.dp),
"AI chuyên gia chỉnh âm master", ContentScale.Fit)                Row(Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 20.dp, start = 8.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text("Mô hình AI phân tích nội dung bài hát, tùy chỉnh hiệu ứng âm thanh riêng", color = Color.White.copy(alpha = .60f), fontSize = 11.sp)
                    Text("Tùy chọn ›", Modifier.padding(start = 4.dp).clip(CircleShape)
                        .background(Color.White.copy(alpha = .12f)).clickable(role = Role.Button) { preferences = true }
                        .padding(horizontal = 7.dp, vertical = 5.dp).testTag("sound_ai_master_diy_open"),
                        color = Color.White.copy(alpha = .85f), fontSize = 10.sp)
                }
                Column(Modifier.padding(horizontal = 16.dp).fillMaxWidth().heightIn(min = pageWidth * 1.47f)
                    .clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha = .06f))
                    .border(1.dp, Color.White.copy(alpha = .06f), RoundedCornerShape(20.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.fillMaxWidth().height(174.dp), contentAlignment = Alignment.BottomCenter) {
                        EffectsArtwork(NativeEffectsAssets.local(if (animated) "ai_master_detail_waves.webp" else "ai_master_detail_waves.png"),
                            Modifier.fillMaxWidth().height(174.dp).graphicsLayer { alpha = .30f }, animated = animated)
                        Box(Modifier.padding(bottom = 12.dp).size(100.dp).clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFF5C513A)).clickable(enabled = song != null, role = Role.Button,
                                onClick = if (previewing) onPreviewStop else onPlayback).testTag("sound_ai_master_song_playback")) {
                            if (song != null && song.cover.isNotBlank()) EffectsArtwork(song.cover, Modifier.fillMaxSize(), "Ảnh bìa bài hát hiện tại")
                            if (song != null && (using || previewing)) Icon(
                                if ((using && playing) || previewing) MusicIcons.Pause else Icons.Default.PlayArrow,
                                null, tint = Color.White, modifier = Modifier.align(Alignment.BottomEnd).padding(5.dp).size(20.dp))
                        }
                    }
                    Text(song?.name?.let { "《$it》" } ?: "Hiện không phát bài hát nào", Modifier.padding(horizontal = 16.dp),
                        color = Color.White, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Spacer(Modifier.height(25.dp))
                    Text("Từ khóa điều chỉnh âm thanh của chuyên gia", color = Color.White.copy(alpha = .60f), fontSize = 11.sp)
                    Text(state.baseName?.takeIf { it.isNotBlank() } ?: if (state.loading) "Đang khớp" else "",
                        Modifier.fillMaxWidth().heightIn(min = 68.dp).padding(top = 6.dp).testTag("sound_ai_master_keyword"),
                        color = MasterGold, fontSize = 43.sp, lineHeight = 54.sp, fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center)
                    Text(state.longText?.takeIf { it.isNotBlank() } ?: state.description?.takeIf { it.isNotBlank() }
                        ?: if (song == null) "Phát bài hát trên Nhạc Vân NetEase để xem phương án điều chỉnh âm thanh riêng cho bài hát hiện tại" else state.message,
                        Modifier.fillMaxWidth().padding(horizontal = 30.dp, vertical = 10.dp).testTag("sound_ai_master_description"),
                        color = Color.White.copy(alpha = .50f), fontSize = 12.sp, lineHeight = 18.sp, textAlign = TextAlign.Center)
                    if (state.usedDiy.isNotEmpty() && (using || previewProcessed && previewing)) {
                        Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.Center) {
                            Text("● Đang áp dụng nhãn DIY", color = MasterGold.copy(alpha = .85f), fontSize = 10.sp)
                        }
                        FlowRow(Modifier.padding(horizontal = 12.dp, vertical = 9.dp), horizontalArrangement = Arrangement.spacedBy(5.dp,
                            Alignment.CenterHorizontally), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                            masterDiyOptions.filter { it.key in state.usedDiy }.forEach {
                                Text(it.title, Modifier.clip(CircleShape).background(Color.White.copy(alpha = .06f))
                                    .padding(horizontal = 10.dp, vertical = 5.dp), color = Color.White.copy(alpha = .8f), fontSize = 10.sp)
                            }
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    if (!using && song != null) Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        MasterPreviewAction("Phát âm thanh gốc", previewing && !previewProcessed, Modifier.weight(1f),
                            "sound_ai_master_preview_original") { if (previewing && !previewProcessed) onPreviewStop() else onPreview(false) }
                        MasterPreviewAction("Nghe thử hiệu ứng", previewing && previewProcessed, Modifier.weight(1f),
                            "sound_ai_master_preview_processed") { if (previewing && previewProcessed) onPreviewStop() else onPreview(true) }
                    }
                    Spacer(Modifier.height(110.dp))
                }
                Spacer(Modifier.height(24.dp))
            }
        }
        Box(Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(bottom = LocalMusicBottomInset.current).background(Brush.verticalGradient(
            listOf(Color.Transparent, Color(0xF20E0900)))).navigationBarsPadding().padding(top = 28.dp, bottom = 22.dp),
            contentAlignment = Alignment.Center) {
            Row(Modifier.width((pageWidth * .64f).coerceAtMost(320.dp)).height(49.dp).clip(CircleShape)
                .background(if (using) Color.Black.copy(alpha = .3f) else MasterGold)
                .border(if (using) 1.dp else 0.dp, if (using) Color.White.copy(alpha = .25f) else Color.Transparent, CircleShape)
                .clickable(role = Role.Button, onClick = onToggle).testTag("sound_ai_master_toggle"),
                horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(if (using) "Hủy sử dụng" else "Dùng ngay", color = if (using) Color.White else Color(0xFF211A0C),
                    fontSize = 15.sp, fontWeight = FontWeight.Medium)
                if (!using) { Spacer(Modifier.width(5.dp)); EffectsMembership(4) }
            }
        }
    }
    if (preferences) MasterPreferencesSheet(state.selectedDiy, state.diySaving,
        onDismiss = { preferences = false }, onSave = { onSaveDiy(it); preferences = false })
    if (information) MasterInformationSheet { information = false }
}

@Composable private fun MasterPreviewAction(label: String, active: Boolean, modifier: Modifier, tag: String, onClick: () -> Unit) {
    Row(modifier.height(38.dp).clip(CircleShape).background(Color.White.copy(alpha = .06f))
        .clickable(role = Role.Button, onClick = onClick).testTag(tag),
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Icon(if (active) MusicIcons.Pause else Icons.Default.PlayArrow, null, tint = Color.White, modifier = Modifier.size(17.dp))
        Text(label, color = Color.White, fontSize = 12.sp)
    }
}

@Composable private fun MasterDetailIcon(label: String, tag: String, onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(Modifier.size(44.dp).clip(CircleShape).clickable(role = Role.Button, onClickLabel = label, onClick = onClick)
        .semantics { contentDescription = label }.testTag(tag), contentAlignment = Alignment.Center) { content() }
}

@Composable private fun MasterPreferencesSheet(selected: Set<String>, saving: Boolean,
    onDismiss: () -> Unit, onSave: (Set<String>) -> Unit) {
    var draft by remember { mutableStateOf(selected) }
    LaunchedEffect(selected) { draft = selected }
    io.github.currencortex.music.ui.component.MusicDialog("Tùy chọn DIY", onDismiss, footer = {
        io.github.currencortex.music.ui.component.MusicDialogActions {
            io.github.currencortex.music.ui.component.MusicDialogButton("Hủy", onDismiss,
                Modifier.weight(1f).testTag("sound_ai_master_diy_close").semantics { contentDescription = "Đóng tùy chọn" })
            io.github.currencortex.music.ui.component.MusicDialogButton(if (saving) "Đang lưu" else "Lưu tùy chọn", { onSave(draft) },
                Modifier.weight(1f).testTag("sound_ai_master_diy_save"), enabled = !saving)
        }
    }) {
        val colors = top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
        Column(Modifier.heightIn(max = 420.dp).verticalScroll(rememberScrollState())) {
Text("Sau khi chọn tùy chọn, Chuyên gia tinh chỉnh âm thanh AI sẽ dựa trên trải nghiệm nghe nhạc tốt nhất và kết hợp với sở thích của bạn để tùy chỉnh hiệu ứng âm thanh phù hợp nhất! (PS: Nhãn tùy chọn sẽ có hiệu lực tùy theo bài hát)",            Modifier.padding(bottom = 18.dp), color = colors.onSurfaceVariantSummary, fontSize = 11.sp, lineHeight = 16.sp)
        for (row in 0..2) {
            Row(Modifier.fillMaxWidth().padding(bottom = 9.dp), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                for (column in 0..1) {
                    val option = masterDiyOptions.getOrNull(row * 2 + column)
                    val chosen = option != null && option.key in draft
                    Box(Modifier.weight(1f).height(110.dp).clip(RoundedCornerShape(8.dp))
                        .background(if (chosen) colors.primary.copy(alpha = .10f) else colors.onBackground.copy(alpha = .06f))
                        .border(if (chosen) 1.dp else 0.dp, if (chosen) colors.primary else Color.Transparent, RoundedCornerShape(8.dp))
                        .then(if (option != null) Modifier.toggleable(value = chosen, role = Role.Checkbox) { checked ->
                            draft = if (checked) draft + option.key else draft - option.key
                        }.testTag("sound_ai_master_diy_${option.icon}") else Modifier),
                        contentAlignment = if (option == null) Alignment.Center else Alignment.TopStart) {
                        if (option == null) Text("Vui lòng chờ", color = colors.onSurfaceVariantSummary, fontSize = 14.sp)
                        else Column(Modifier.padding(11.dp)) {
                            EffectsArtwork(NativeEffectsAssets.local("ai_master_detail_diy_${option.icon}${if (chosen) "_selected" else ""}.png"),
                                Modifier.size(28.dp), scale = ContentScale.Fit)
                            Text(option.title, Modifier.padding(top = 8.dp), color = colors.onBackground, fontSize = 14.sp)
                            Text(option.subtitle, Modifier.padding(top = 5.dp), color = colors.onSurfaceVariantSummary, fontSize = 10.sp,
                                maxLines = 1, overflow = TextOverflow.Ellipsis)
                        }
                        if (chosen) Text("✓", Modifier.align(Alignment.BottomEnd).padding(9.dp), color = colors.primary, fontSize = 17.sp)
                    }
                }
            }
        }
        }
    }
}

@Composable private fun MasterInformationSheet(onDismiss: () -> Unit) {
    io.github.currencortex.music.ui.component.MusicDialog("Giới thiệu về bậc thầy chỉnh âm AI", onDismiss, footer = {
        io.github.currencortex.music.ui.component.MusicDialogButton("Đã hiểu", onDismiss,
            Modifier.testTag("sound_ai_master_info_close").semantics { contentDescription = "Đóng phần giới thiệu" })
    }) {
        Column(Modifier.heightIn(max = 420.dp).verticalScroll(rememberScrollState())) {
        listOf(
            "1. Mô tả chức năng" to "Phân tích động các đặc trưng của bài hát bằng engine AI học sâu như nhịp điệu, nhạc cụ và tần số giọng hát; tự động tạo thông số hiệu ứng âm thanh phù hợp để mang lại trải nghiệm nghe tốt nhất.",
            "2. Phạm vi bài hát" to "Hệ thống ưu tiên phân tích chuyên sâu nội dung các bài hát phổ biến; những nội dung khác sẽ được xếp hàng để tạo thông số tùy chỉnh. Trong thời gian chờ, hệ thống tự động áp dụng phương án tối ưu hóa hiệu ứng âm thanh chung.",
            "3. Tùy chọn DIY" to "Các tùy chọn được áp dụng theo bài hát hiện tại; không phải bài hát nào cũng hỗ trợ mọi tùy chọn. Khi hiển thị nhãn DIY đang được áp dụng, một phần hoặc toàn bộ tùy chọn đã có hiệu lực. Tùy chọn DIY không có hiệu lực khi sử dụng âm thanh không gian.",
            "4. Giới hạn bản quyền" to "Một số bài hát đặc biệt bị chủ sở hữu bản quyền hạn chế, chẳng hạn như album kỹ thuật số trả phí, nên không thể điều chỉnh hiệu ứng âm thanh.",
            "5. Cài đặt tùy chọn" to "Tùy chọn được lưu trong cài đặt cục bộ của tài khoản NetEase hiện tại và được dùng để tính toán lại thông số hiệu ứng âm thanh cho bài hát hiện tại.",
      ).forEach { (title, description) ->
            Text(title, color = top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme.onBackground, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(description, Modifier.padding(top = 7.dp, bottom = 18.dp), color = top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme.onSurfaceVariantSummary, fontSize = 12.sp, lineHeight = 19.sp)
        }
        }
    }
}
