package io.github.currencortex.music.feature.room

import io.github.currencortex.music.ui.component.nativeui.*

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.core.media.ListenTogetherPair
import io.github.currencortex.music.data.netease.listen.ListenTogetherFateReport
import io.github.currencortex.music.ui.component.ListeningPairArtwork
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.MusicTextAction
import io.github.currencortex.music.ui.component.musicScrollPadding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Native relationship statistics are read-only here. Deletion only requests the caller's confirmation. */
@Composable internal fun ListenTogetherFateContent(
    report: ListenTogetherFateReport?,
    loading: Boolean,
    permissionRequired: Boolean,
    onBack: () -> Unit,
    onRetry: () -> Unit,
    onHistory: (Long) -> Unit,
    onShare: (ListenTogetherFateReport) -> Unit,
    onDeleteRequest: () -> Unit,
    onOfficial: (ListenTogetherFateReport) -> Unit,
    busy: Boolean = false,
    sharing: Boolean = false,
    error: Boolean = false,
    onInvite: ((ListenTogetherFateReport) -> Unit)? = null,
    activeRoom: Boolean = false,
) {
    val colors = MiuixTheme.colorScheme
    MusicSubpage("Báo cáo duyên phận", "listen_fate_screen", onBack, backTag = "listen_fate_back") { topInset ->
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 16.dp).testTag("listen_fate_list"),
            contentPadding = musicScrollPadding(PaddingValues(top = topInset + 8.dp, bottom = 24.dp)),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (loading) item {
                 if (report == null) ListenTogetherSkeleton("Đang tải báo cáo duyên phận…", "listen_fate_loading")
                 else ListenLoadingLabel("Đang cập nhật báo cáo duyên phận…", Modifier.testTag("listen_fate_loading"))
            }
            if (permissionRequired) item {
                 FateStatus("Cần quyền truy cập thành viên Nhạc Vân NetEase", "listen_fate_permission", "Vui lòng thử lại bằng tài khoản có quyền truy cập báo cáo.", onRetry)
            } else if (error) item {
                // The detailed failure is delivered by the application's top notice channel.
                 FateStatus("Hiện không thể tải báo cáo", "listen_fate_error", "Vui lòng thử lại sau.", onRetry)
            }
            if (report != null && !permissionRequired) {
                item { SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_fate_summary")) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            val people = listOfNotNull(report.self, report.peer)
                            ListeningPairArtwork(ListenTogetherPair(people.map {
                                io.github.currencortex.music.core.media.ListenTogetherParticipant(it.userId, it.nickname, it.avatarUrl)
                            }), Modifier.size(88.dp, 55.dp))
                            Column(Modifier.weight(1f).padding(start = 16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                 Text(people.joinToString(" · ") { it.nickname.ifBlank { "Người dùng Nhạc Vân NetEase" } }.ifBlank { "Hai bạn đã cùng nghe" },
                                    fontSize = 17.sp, fontWeight = FontWeight.SemiBold, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                 FateMuted("Khoảng thời gian âm nhạc của hai bạn")
                            }
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                             FateMuted("Thời gian đồng hành bên nhau")
                            Text(fateDuration(report.totalDurationMs), Modifier.testTag("listen_fate_total_duration"),
                                fontSize = 30.sp, fontWeight = FontWeight.SemiBold, color = colors.primary)
                        }
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                             FateMetric("Đã cùng nghe", report.totalSongCount?.let { "$it bài hát" } ?: "—",
                                Modifier.weight(1f).testTag("listen_fate_total_songs"))
                             FateMetric("Cùng thả tim", report.commonRedSongCount?.let { "$it bài hát" } ?: "—",
                                Modifier.weight(1f).testTag("listen_fate_common_red"))
                        }
                         onInvite?.let { invite -> MusicTextAction(if (activeRoom) "Đang cùng nghe" else "Mời lại", { invite(report) },
                            Modifier.testTag("listen_fate_invite"), enabled = report.canInvite && !report.peer.masked && !activeRoom && !busy) }
                        report.failMessage.takeIf(String::isNotBlank)?.let { FateMuted(it) }
                        if (listOf(report.totalDurationMs, report.totalSongCount, report.commonRedSongCount).any { it == null }) {
                             FateMuted("— nghĩa là Nhạc Vân NetEase không cung cấp dữ liệu này.")
                        }
                    }
                } }
                item { SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_fate_first")) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                         Text("Từ lần đầu tiên cùng nghe", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                             FateMetric("Lần đầu cùng nghe", fateDate(report.firstListenTimeMs, "yyyy.MM.dd HH:mm"), Modifier.weight(1f))
                             FateMetric("Số ngày quen nhau", report.daysSinceFirstListen?.let { "$it ngày" } ?: "—", Modifier.weight(1f))
                        }
                    }
                } }
                if (report.latestLateNightTimeMs != null || report.mostPlayedSongName.isNotBlank()) item {
                    SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_fate_memories")) {
                        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                             Text("Khoảnh khắc âm nhạc đáng nhớ", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                            if (report.latestLateNightTimeMs != null) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                     FateMuted("Ngày hai bạn nghe cùng nhau muộn nhất")
                                    Text(fateDate(report.latestLateNightTimeMs, "yyyy.MM.dd  HH:mm"), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                                }
                            }
                            if (report.mostPlayedSongName.isNotBlank()) {
                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                     FateMuted("Bài hát hai bạn nghe cùng nhau nhiều nhất")
                                    Text(report.mostPlayedSongName, fontSize = 18.sp, fontWeight = FontWeight.Medium, maxLines = 3, overflow = TextOverflow.Ellipsis)
                                }
                            }
                        }
                    }
                }
                item { SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_fate_longest")) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                         Text("Lần đồng hành lâu nhất", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Text(fateDuration(report.longestDurationMs), fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = colors.primary)
                    }
                } }
                item { SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_fate_actions")) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                         GlassActionButton(if (sharing) "Đang tạo báo cáo…" else "Chia sẻ báo cáo duyên phận", { onShare(report) },
                            Modifier.fillMaxWidth().testTag("listen_fate_share"), primary = true, enabled = !busy && !sharing && !loading)
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                             MusicTextAction("Lịch sử nghe cùng", { onHistory(report.partnerUid) }, Modifier.weight(1f).testTag("listen_fate_history"),
                                enabled = report.partnerUid > 0 && !busy)
                             MusicTextAction("Báo cáo Nhạc Vân NetEase", { onOfficial(report) }, Modifier.weight(1f).testTag("listen_fate_official"), enabled = !busy)
                        }
                         MusicTextAction("Xóa báo cáo duyên phận", onDeleteRequest, Modifier.testTag("listen_fate_delete"),
                            enabled = !busy && !sharing && !loading && report.partnerUid > 0, destructive = true)
                         if (report.openActivity) FateMuted("Xóa báo cáo sẽ thu hồi huy hiệu và tiến độ liên quan đến tính năng nghe cùng.")
                    }
                } }
            } else if (!loading && !permissionRequired && !error) item {
                FateStatus("Chưa có báo cáo duyên phận", "listen_fate_empty", "Nhạc Vân NetEase hiện chưa cung cấp dữ liệu duyên phận của hai bạn.")
            }
        }
    }
}

@Composable private fun FateMetric(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        FateMuted(label)
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable private fun FateMuted(text: String) {
    Text(text, fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
}

@Composable private fun FateStatus(title: String, tag: String, message: String? = null, retry: (() -> Unit)? = null) {
    SettingsGroup(Modifier.fillMaxWidth().testTag(tag)) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            message?.let { FateMuted(it) }
            retry?.let { MusicTextAction("Thử lại", it, Modifier.testTag("listen_fate_retry")) }
        }
    }
}

private fun fateDuration(ms: Long?): String {
    if (ms == null) return "—"
    val minutes = ms / 60_000
    return when { ms > 0 && minutes == 0L -> "Dưới 1 phút"; minutes < 60 -> "$minutes phút"; else -> "${minutes / 60} giờ ${minutes % 60} phút" }
}
private fun fateDate(ms: Long?, pattern: String): String = ms?.takeIf { it > 0 }
    ?.let { SimpleDateFormat(pattern, Locale.CHINA).format(Date(it)) } ?: "—"
