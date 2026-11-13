package io.github.currencortex.music.feature.room

import io.github.currencortex.music.ui.component.nativeui.*

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.core.media.ListenTogetherPair
import io.github.currencortex.music.data.netease.listen.*
import io.github.currencortex.music.ui.component.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Read-only archive pages. Network requests, invitations and sharing belong to their explicit callbacks. */
@Composable internal fun ListenTogetherHistoryContent(
    state: ListenTogetherArchiveState,
    onBack: () -> Unit,
    onHistory: (Long?) -> Unit,
    onRanking: () -> Unit,
    onReport: (String) -> Unit,
    onMore: () -> Unit,
    onRetry: () -> Unit,
    onInvite: (ListenTogetherParticipant) -> Unit,
    onShare: (ListenTogetherRecordReport) -> Unit,
    onCollect: () -> Unit,
    onOfficial: (ListenTogetherRecordReport) -> Unit,
    activeRoom: Boolean = false,
    onFate: ((ListenTogetherParticipant, Int) -> Unit)? = null,
    sharing: Boolean = false,
) {
    val colors = MiuixTheme.colorScheme
    val title = when (state.page) {
        ListenArchivePage.RANKING -> "Xếp hạng nghe cùng"
        ListenArchivePage.REPORT -> "Nghe cùng TA"
        else -> "Lịch sử nghe cùng"
    }
    MusicSubpage(title, "listen_archive_screen", onBack, backTag = "listen_archive_back",
        actions = {
            if (state.page != ListenArchivePage.HISTORY || state.peerFilter != null)
                MusicTextAction("Tất cả bản ghi", { onHistory(null) }, Modifier.testTag("listen_archive_all"))
        }) { topInset ->
        LazyColumn(Modifier.fillMaxSize().padding(horizontal = 16.dp).testTag("listen_archive_list"),
            contentPadding = musicScrollPadding(PaddingValues(top = topInset + 8.dp, bottom = 24.dp)),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            val hasContent = when (state.page) {
                ListenArchivePage.HISTORY -> state.history != null
                ListenArchivePage.RANKING -> state.ranking != null
                ListenArchivePage.REPORT -> state.report != null
                null -> false
            }
            if (state.loading) item(key = "loading") {
                val label = when (state.page) {
                    ListenArchivePage.RANKING -> "Đang tải xếp hạng nghe cùng…"
                    ListenArchivePage.REPORT -> "Đang tạo báo cáo nghe cùng lần này…"
                    else -> "Đang tải lịch sử nghe cùng…"
                }
                if (hasContent) ListenLoadingLabel(label, Modifier.testTag("listen_archive_loading"))
                else ListenTogetherSkeleton(label, "listen_archive_loading")
            }
            state.error?.let { error -> item {
                ArchiveMessage(if (state.permissionRequired) "Cần quyền thành viên Nhạc Vân NetEase" else "Tạm thời không thể tải", error,
                    if (state.permissionRequired) "Nhạc Vân NetEase giới hạn quyền truy cập dữ liệu này. Hãy thử lại khi tài khoản của bạn có quyền." else null,
                    "listen_archive_error", onRetry)
            } }
            if (!state.loading || hasContent) when (state.page) {
                ListenArchivePage.HISTORY -> {
                    val page = state.history
                    if (state.peerFilter == null) {
                        item { Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                            Text("Người thường nghe cùng TA nhất", Modifier.weight(1f), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                            MusicTextAction("Xếp hạng", onRanking, Modifier.testTag("listen_archive_ranking"))
                        } }
                        page?.mostListen?.let { most -> item {
                            SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_archive_most")) {
                                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        val self = state.self?.let { current -> current.copy(avatarUrl = most.selfAvatarUrl.ifBlank { current.avatarUrl }) }
                                        ArchivePair(listOfNotNull(self, most.user), Modifier.size(88.dp, 55.dp))
                                        Column(Modifier.weight(1f).padding(start = 16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                            Text("Bạn và ${most.user.displayName()}", fontSize = 17.sp, fontWeight = FontWeight.SemiBold,
                                                maxLines = 2, overflow = TextOverflow.Ellipsis)
                                            Text(if (most.online) "Bạn bè đang trực tuyến" else "Người đồng hành nghe cùng", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
                                        }
                                    }
                                    ArchiveStats("Đồng hành cùng nhau", durationLabel(most.totalDurationMs), "Đã nghe cùng", countLabel(most.totalSongCount))
                                    if (most.totalDurationMs == null || most.totalSongCount == null) ArchiveMuted("Chỉ thành viên mới xem được một phần dữ liệu tích lũy")
                                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                        MusicTextAction("Xem lịch sử của hai bạn", { onHistory(most.user.userId) }, Modifier.weight(1f).testTag("listen_archive_most_history"))
                                        if (onFate != null) MusicTextAction("Báo cáo duyên phận", { onFate(most.user, most.ltType) },
                                            Modifier.weight(1f).testTag("listen_archive_most_fate"))
                                    }
                                }
                            }
                        } }
                    }
                    val records = page?.records.orEmpty().filter { state.peerFilter == null || it.user.userId == state.peerFilter }
                    if (activeRoom) item { ArchiveMuted("Đang nghe cùng. Bạn có thể mời bạn bè lại sau khi phiên hiện tại kết thúc.") }
                    item { Text(if (state.peerFilter == null) "Lịch sử nghe cùng" else "Lịch sử nghe cùng TA",
                        Modifier.padding(top = 4.dp, bottom = 4.dp), fontSize = 16.sp, fontWeight = FontWeight.SemiBold) }
                    if (!state.loading && state.error == null && records.isEmpty()) item {
                        ArchiveMessage("Chưa có lịch sử nghe cùng", "Sau khi kết thúc nghe cùng, bạn có thể xem bản ghi được Nhạc Vân NetEase lưu tại đây.", tag = "listen_archive_empty")
                    }
                    // Keep server ordering and group only consecutive dates; do not reorder sessions locally.
                    records.forEachIndexed { index, record ->
val date = record.date.ifBlank { dateLabel(record.startTimeMs, "d 'tháng' M") }val previous = records.getOrNull(index - 1)?.let { it.date.ifBlank { dateLabel(it.startTimeMs, "M tháng d ngày") } }                        if (index == 0 || date != previous) item { ArchiveMuted(date.ifBlank { "Chưa có ngày" }) }
                        item(key = "record:${record.id}:$index") { Box(Modifier.animateItem()) { HistoryRecordCard(record, activeRoom, { onReport(record.roomId) }, { onInvite(record.user) }) } }
                    }
                    if (page?.hasMore == true) item {
                        if (state.loadingMore) ListenLoadingLabel("Đang tải thêm bản ghi…", Modifier.fillMaxWidth().padding(12.dp).testTag("listen_archive_more_loading"))
                        MusicTextAction("Tải thêm", onMore,
                            Modifier.fillMaxWidth().testTag("listen_archive_more"), enabled = !state.loadingMore && !state.loading)
                    }
                }
                ListenArchivePage.RANKING -> {
                    item { ArchiveMuted("Hiển thị theo dữ liệu đồng hành tích lũy do Nhạc Vân NetEase cung cấp") }
                    val rankings = state.ranking?.rankings.orEmpty()
                    if (!state.loading && state.error == null && rankings.isEmpty()) item {
                        ArchiveMessage("Chưa có bảng xếp hạng nghe cùng", "Hiện chưa có dữ liệu xếp hạng để hiển thị.", tag = "listen_archive_empty")
                    }
                    items(rankings) { entry ->
                        SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_archive_rank_${entry.user.userId}")) {
                            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(entry.rank.takeIf { it > 0 }?.toString() ?: "—", Modifier.width(28.dp), fontSize = 22.sp,
                                        fontWeight = FontWeight.SemiBold, color = if (entry.rank == 1) colors.primary else colors.onSurfaceVariantSummary)
                                    ArchiveAvatar(entry.user, entry.online)
                                    Text(entry.user.displayName(), Modifier.weight(1f).padding(start = 12.dp), fontSize = 16.sp,
                                        maxLines = 1, overflow = TextOverflow.Ellipsis)
                                }
                                ArchiveRule()
                                ArchiveStats("Đồng hành cùng nhau", durationLabel(entry.totalDurationMs), "Đã nghe cùng nhau", countLabel(entry.totalSongCount))
                                if (entry.totalDurationMs == null || entry.totalSongCount == null) ArchiveMuted("Chỉ hội viên mới xem được một số dữ liệu tích lũy")
                                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                    MusicTextAction("Xem lịch sử", { onHistory(entry.user.userId) },
                                        Modifier.weight(1f).testTag("listen_archive_rank_history_${entry.user.userId}"))
                                    if (onFate != null) MusicTextAction("Báo cáo duyên phận", { onFate(entry.user, entry.ltType) },
                                        Modifier.weight(1f).testTag("listen_archive_rank_fate_${entry.user.userId}"))
                                }
                            }
                        }
                    }
                }
                ListenArchivePage.REPORT -> {
                    val raw = state.report
                    if (raw != null) {
                        val report = resolveReportPeople(raw, state)
                        val peer = report.peer
                        item { SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_archive_partner")) {
                            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                    if (peer != null) ArchiveAvatar(peer, report.online)
                                    Column(Modifier.weight(1f).padding(start = if (peer != null) 12.dp else 0.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                        Text(peer?.displayName() ?: "Nghe cùng lần này", fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
                                            maxLines = 2, overflow = TextOverflow.Ellipsis)
                                        ArchiveMuted(if (report.online) "Bạn bè đang trực tuyến" else "Đã kết thúc nghe cùng")
                                    }
                                    MusicTextAction(if (activeRoom) "Đang nghe cùng nhau" else "Mời lại", { peer?.let(onInvite) },
                                        Modifier.testTag("listen_archive_report_invite"), enabled = peer != null && report.canInvite && !activeRoom)
                                }
                                if (activeRoom) ArchiveMuted("Có thể mời bạn bè lần nữa sau khi kết thúc phiên hiện tại.")
                                if (peer != null) onFate?.let { open ->
                                    ArchiveRule()
                                    Row(Modifier.fillMaxWidth().clickable(role = Role.Button) { open(peer, report.ltType) }
                                        .heightIn(min = 48.dp).padding(vertical = 10.dp).testTag("listen_archive_report_fate"),
                                        verticalAlignment = Alignment.CenterVertically) {
                                        Text("Xem báo cáo duyên phận của bạn và người ấy", Modifier.weight(1f), fontSize = 13.sp,
                                            fontWeight = FontWeight.Medium, color = colors.primary)
                                        Icon(MusicIcons.Forward, null, Modifier.size(16.dp), tint = colors.onSurfaceVariantSummary)
                                    }
                                }
                                report.failMessage.takeIf(String::isNotBlank)?.let { ArchiveMuted(it) }
                            }
                        } }
                        item { SettingsGroup(Modifier.fillMaxWidth().animateItem().testTag("listen_archive_report")) {
                            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(18.dp)) {
                                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                    Text("Khoảnh khắc đồng hành lần này", Modifier.weight(1f), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                                    report.endTimeMs?.takeIf { it > 0 }?.let { ArchiveMuted(dateLabel(it, "yyyy.MM.dd")) }
                                }
                                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                    ArchivePair(listOfNotNull(report.self, report.peer), Modifier.size(128.dp, 80.dp))
                                }
                                ArchiveStats("Lần này đã nghe cùng", countLabel(report.songCount), "Thời gian đồng hành lần này", durationLabel(report.durationMs), emphasized = true)
                                ArchiveRule()
                                ArchiveStats("Tổng số bài đã nghe cùng", countLabel(report.totalSongCount), "Tổng thời gian đồng hành", durationLabel(report.totalDurationMs))
                                if (listOf(report.durationMs, report.songCount, report.totalDurationMs, report.totalSongCount).any { it == null }) {
                                    ArchiveMuted("— nghĩa là Nhạc Vân NetEase không cung cấp dữ liệu này; chỉ hội viên mới xem được một số dữ liệu tích lũy.")
                                }
                                if (report.showCollectButton) GlassActionButton(
                                    if (state.collecting) "Đang đọc bài hát…" else "Thêm các bài hát lần này vào danh sách phát", onCollect,
                                    Modifier.fillMaxWidth().testTag("listen_archive_collect"), enabled = !state.collecting)
                                state.collectionError?.let { Text(it, Modifier.testTag("listen_archive_collection_error"), fontSize = 12.sp, color = colors.error) }
                                GlassActionButton(if (sharing) "Đang tạo báo cáo…" else "Chia sẻ báo cáo nghe cùng", { onShare(report) },
                                    Modifier.fillMaxWidth().testTag("listen_archive_share"), primary = true, enabled = !sharing && !state.loading)
                                MusicTextAction("Xem báo cáo trên Nhạc Vân NetEase", { onOfficial(report) }, Modifier.testTag("listen_archive_official"))
                            }
                        } }
                    } else if (!state.loading && state.error == null) item {
                        ArchiveMessage("Chưa có báo cáo lần này", "Nhạc Vân NetEase hiện chưa cung cấp dữ liệu nghe cùng lần này.", tag = "listen_archive_empty")
                    }
                }
                else -> Unit
            }
        }
    }
}

@Composable private fun HistoryRecordCard(record: ListenTogetherHistoryRecord, activeRoom: Boolean, onReport: () -> Unit, onInvite: () -> Unit) {
    SettingsGroup(Modifier.fillMaxWidth().testTag("listen_archive_record_${record.id}")) {
        SettingsPreference(record.user.displayName(),
            listOf(dateLabel(record.startTimeMs, "HH:mm"), durationLabel(record.durationMs), countLabel(record.songCount),
                record.failMessage).filter(String::isNotBlank).joinToString(" · "),
            Modifier.testTag("listen_archive_open_${record.id}"), enabled = record.roomId.isNotBlank(), onClick = onReport,
            leading = { ArchiveAvatar(record.user, record.online) }, trailing = {
                MusicTextAction("Mời", onInvite, Modifier.testTag("listen_archive_invite_${record.id}"),
                    enabled = record.canInvite && !activeRoom)
            })
    }
}

@Composable private fun ArchiveAvatar(user: ListenTogetherParticipant, online: Boolean) {
    Box(Modifier.size(44.dp)) {
        DecoratedAvatar(user.avatarUrl.takeIf(String::isNotBlank), null, size = 44.dp)
        if (online) Box(Modifier.align(Alignment.BottomEnd).size(10.dp).background(MiuixTheme.colorScheme.primary, CircleShape))
    }
}

@Composable private fun ArchivePair(users: List<ListenTogetherParticipant>, modifier: Modifier) {
    ListeningPairArtwork(ListenTogetherPair(users.map { io.github.currencortex.music.core.media.ListenTogetherParticipant(it.userId, it.displayName(), it.avatarUrl) }), modifier)
}

@Composable private fun ArchiveStats(firstLabel: String, first: String, secondLabel: String, second: String, emphasized: Boolean = false) {
    val density = LocalDensity.current
    val measurer = rememberTextMeasurer()
    BoxWithConstraints(Modifier.fillMaxWidth()) {
        val baseSize = if (emphasized) 20.sp else 16.sp
        val style = TextStyle(fontSize = baseSize, fontWeight = FontWeight.SemiBold)
        val widest = maxOf(measurer.measure(first, style).size.width, measurer.measure(second, style).size.width).coerceAtLeast(1)
        val columnWidth = with(density) { ((maxWidth - 16.dp) / 2).toPx() }
        val fittedSize = baseSize * (columnWidth / widest).coerceIn(0f, 1f)
        // Keep both values at the same size; use full-width rows before text becomes too small.
        val stacked = fittedSize.value < if (emphasized) 16f else 14f
        val valueSize = if (stacked) baseSize else fittedSize
        val cells: @Composable (Modifier) -> Unit = { modifier ->
            listOf(firstLabel to first, secondLabel to second).forEach { (label, value) ->
                Column(modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    ArchiveMuted(label)
                    Text(value, fontSize = valueSize, fontWeight = FontWeight.SemiBold,
                        color = if (emphasized) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface)
                }
            }
        }
        if (stacked) Column(verticalArrangement = Arrangement.spacedBy(14.dp)) { cells(Modifier.fillMaxWidth()) }
        else Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) { cells(Modifier.weight(1f)) }
    }
}

@Composable private fun ArchiveMuted(text: String) {
    Text(text, fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
}

@Composable private fun ArchiveRule() { Box(Modifier.fillMaxWidth().height(1.dp).background(MiuixTheme.colorScheme.onSurface.copy(alpha = .07f))) }

@Composable private fun ArchiveMessage(title: String, message: String? = null, detail: String? = null, tag: String, onRetry: (() -> Unit)? = null) {
    SettingsGroup(Modifier.fillMaxWidth().testTag(tag)) {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            message?.let { ArchiveMuted(it) }
            detail?.let { ArchiveMuted(it) }
            onRetry?.let { MusicTextAction("Thử lại", it, Modifier.testTag("listen_archive_retry")) }
        }
    }
}

private fun ListenTogetherParticipant.displayName() = nickname.ifBlank { "Người dùng Nhạc Vân NetEase" }
private fun countLabel(count: Long?) = count?.let { "$it bài hát" } ?: "—"
private fun durationLabel(ms: Long?): String {
    if (ms == null) return "—"
    val minutes = ms.coerceAtLeast(0) / 60_000
    return when { ms > 0 && minutes == 0L -> "Dưới 1 phút"; minutes < 60 -> "$minutes phút"; else -> "${minutes / 60} giờ ${minutes % 60} phút" }
}
private fun dateLabel(ms: Long, pattern: String) = if (ms > 0) SimpleDateFormat(pattern, Locale.CHINA).format(Date(ms)) else ""

/** Fill only truly missing profile fields by matching ID; preserve anonymous names returned by NetEase. */
private fun resolveReportPeople(report: ListenTogetherRecordReport, state: ListenTogetherArchiveState): ListenTogetherRecordReport {
    val members = state.sourceRoom?.participants.orEmpty()
    fun resolve(raw: ListenTogetherParticipant?, fallback: ListenTogetherParticipant?): ListenTogetherParticipant? {
        if (raw == null) return fallback
        if (raw.masked) return raw
        val match = members.firstOrNull { it.userId == raw.userId } ?: fallback?.takeIf { it.userId == raw.userId }
        return raw.copy(nickname = raw.nickname.ifBlank { match?.nickname.orEmpty() }, avatarUrl = raw.avatarUrl.ifBlank { match?.avatarUrl.orEmpty() })
    }
    val self = resolve(report.self, state.self)
    return report.copy(self = self, peer = resolve(report.peer, members.firstOrNull { it.userId != self?.userId }))
}
