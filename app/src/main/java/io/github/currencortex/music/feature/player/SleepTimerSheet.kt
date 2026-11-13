package io.github.currencortex.music.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.media.SleepTimer
import io.github.currencortex.music.ui.component.MusicDialog
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.NumberPicker
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.util.Locale

private val SLEEP_PRESETS = listOf(10, 20, 30, 45, 60, 90)
private const val DEFAULT_SLEEP_MINUTES = 30

/** Sleep timer sheet: NetEase's structure (countdown card, preset chips, custom time) in Miuix ink. */
@Composable internal fun SleepTimerSheet(timer: SleepTimer, onDismiss: () -> Unit) {
    val state by timer.state.collectAsStateWithLifecycle()
    var custom by rememberSaveable { mutableStateOf(false) }
    if (custom) {
        SleepCustomTimeDialog(onDismiss = { custom = false },
            onConfirm = { minutes -> custom = false; timer.start(minutes, state.extendToSongEnd) })
    } else MusicDialog("Tắt theo hẹn giờ", onDismiss) {
        val colors = io.github.currencortex.music.ui.component.musicDialogColors()
        Column(Modifier.testTag("sleep_timer_sheet").heightIn(max = 460.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(Modifier.fillMaxWidth().background(colors.onSurface.copy(alpha = .05f), RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 14.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        when {
                            state.waitingForSongEnd -> "Tạm dừng sau khi phát xong bài này"
                            state.enabled -> formatRemaining(state.remainingMs)
                            else -> "Chọn thời gian"
                        },
                        Modifier.weight(1f).testTag("sleep_timer_countdown"),
                        fontSize = if (state.waitingForSongEnd) 17.sp else 24.sp,
                        fontWeight = FontWeight.Bold, color = colors.onSurface)
                    Switch(state.enabled, { checked ->
                        if (checked) timer.start(state.minutes.coerceAtLeast(1).takeIf { state.minutes > 0 } ?: DEFAULT_SLEEP_MINUTES)
                        else timer.stop()
                    }, Modifier.testTag("sleep_timer_switch"))
                }
                Spacer(Modifier.height(14.dp))
                // True circles: the row's width is split evenly, the height follows it, and the
                // label is pinned to one line so "90" can never wrap inside the disc.
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    SLEEP_PRESETS.forEach { minutes ->
                        val active = state.enabled && state.minutes == minutes
                        Box(Modifier.weight(1f).aspectRatio(1f)
                            .background(if (active) colors.primary else colors.onSurface.copy(alpha = .07f), CircleShape)
                            .clickable(role = Role.RadioButton) { timer.start(minutes, state.extendToSongEnd) }
                            .testTag("sleep_preset_$minutes"), contentAlignment = Alignment.Center) {
                            Text("$minutes", fontSize = 15.sp, maxLines = 1, softWrap = false,
                                fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal,
                                color = if (active) colors.onPrimary else colors.onSurface)
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))
                Box(Modifier.fillMaxWidth().height(1.dp).background(colors.dividerLine))
                 Text("Tùy chỉnh", Modifier.fillMaxWidth().clickable(role = Role.Button) { custom = true }
                    .testTag("sleep_timer_custom").padding(vertical = 14.dp),
                    textAlign = TextAlign.Center, fontSize = 14.sp, color = colors.primary)
            }
            Row(Modifier.fillMaxWidth().background(colors.onSurface.copy(alpha = .05f), RoundedCornerShape(20.dp))
                .clickable(role = Role.Switch) { timer.setExtendToSongEnd(!state.extendToSongEnd) }
                .padding(horizontal = 16.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f).padding(end = 12.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text("Tự động kéo dài đến khi bài hát phát xong", fontSize = 15.sp, color = colors.onSurface)
                    Text("Nếu vẫn đang phát khi hết giờ, sẽ đợi bài hát hiện tại phát xong rồi tạm dừng", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                }
                Switch(state.extendToSongEnd, { timer.setExtendToSongEnd(it) }, Modifier.testTag("sleep_extend_switch"))
            }
            Text("Khi hết giờ, phát sẽ tạm dừng nhưng hàng đợi phát sẽ không bị xóa. Bộ hẹn giờ vẫn tiếp tục chạy khi đóng hộp thoại hoặc chuyển ứng dụng xuống nền.",
                Modifier.padding(horizontal = 4.dp).testTag("sleep_timer_note"),
                fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
        }
    }
}

/** Hours + minutes wheel, matching NetEase's custom sheet but with the app's primary action. */
@Composable private fun SleepCustomTimeDialog(onDismiss: () -> Unit, onConfirm: (Int) -> Unit) {
    val colors = io.github.currencortex.music.ui.component.musicDialogColors()
    var hours by rememberSaveable { mutableStateOf(0) }
    var minutes by rememberSaveable { mutableStateOf(0) }
    MusicDialog("Tắt theo hẹn giờ tùy chỉnh", onDismiss) {
        Column(Modifier.fillMaxWidth().background(colors.onSurface.copy(alpha = .05f), RoundedCornerShape(20.dp))
            .padding(vertical = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                NumberPicker(hours, { hours = it }, Modifier.weight(1f).testTag("sleep_custom_hours"),
                    range = 0..23, label = { "%02d".format(it) }, visibleItemCount = 3)
                Text("Giờ", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
                Box(Modifier.width(1.dp).height(28.dp).background(colors.dividerLine))
                NumberPicker(minutes, { minutes = it }, Modifier.weight(1f).testTag("sleep_custom_minutes"),
                    range = 0..59, label = { "%02d".format(it) }, visibleItemCount = 3)
                Text("Phút", fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
            }
        }
        io.github.currencortex.music.ui.component.MusicDialogActions {
            io.github.currencortex.music.ui.component.MusicDialogButton("Hủy", onDismiss,
                Modifier.weight(1f).testTag("sleep_custom_cancel"))
            io.github.currencortex.music.ui.component.MusicDialogButton("Xác nhận", { onConfirm(hours * 60 + minutes) },
                Modifier.weight(1f).testTag("sleep_custom_confirm"), enabled = hours > 0 || minutes > 0)
        }
    }
}

/** One-line summary for the player's "Tắt theo hẹn giờ" menu row. */internal fun sleepSummary(state: SleepTimer.State): String = when {
    state.waitingForSongEnd -> "Tạm dừng sau khi phát xong bài này"
    state.enabled -> "Còn ${formatRemaining(state.remainingMs)}"
    else -> "Tắt"
}

private fun formatRemaining(ms: Long): String {    val total = ((ms.coerceAtLeast(0L) + 999) / 1000)
    val hours = total / 3600
    val minutes = total % 3600 / 60
    val seconds = total % 60
    return if (hours > 0) String.format(Locale.ROOT, "%d:%02d:%02d", hours, minutes, seconds)
    else String.format(Locale.ROOT, "%02d:%02d", minutes, seconds)
}
