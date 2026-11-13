package io.github.currencortex.music.feature.admin

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.admin.*
import io.github.currencortex.music.feature.update.MarkdownText
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

internal fun LazyListScope.adminOverview(state: AdminState) {
    val stats = state.overview ?: return
    item { AdminStats(listOf(
        AdminStat("Người dùng đăng ký", adminCount(stats.number("users")), "Hôm nay +${adminCount(stats.number("usersToday"))} · 7 ngày +${adminCount(stats.number("usersWeek"))}"),
        AdminStat("Thiết bị trực tuyến", adminCount(stats.number("sessions")), "${adminCount(stats.number("onlineUsers"))} người hoạt động trong 1 giờ qua"),
        AdminStat("Lượt phát tích lũy", adminCount(stats.number("plays")), "${adminCount(stats.number("playlists"))} danh sách phát"),
        AdminStat("Tổng lượt thích", adminCount((stats.number("likes") ?: 0) + (stats.number("ncmLikes") ?: 0)), "Lượt thích trong ứng dụng và lượt thích trên Nhạc Vân NetEase"),
        AdminStat("Tài khoản bị vô hiệu hóa", adminCount(stats.number("disabled"))),
        AdminStat("Liên kết Nhạc Vân NetEase", adminCount(stats.number("bindings"))),
        AdminStat("Mục bộ nhớ đệm", adminCount(stats.number("cache")), "Lời bài hát, bình luận và chi tiết"),
        AdminStat("Phiên bản hiện tại", stats.obj("release").text("version").ifBlank { "—" }))) }
    item { AdminTraffic(state) }
    state.traffic?.rows("top")?.takeIf { it.isNotEmpty() }?.let { paths ->
        item { AdminCard("Xếp hạng lưu lượng hôm nay", "Sắp xếp theo byte gửi đi · Top ${paths.size}") {
            AdminRanking(paths.map { it.text("path") to (it.number("bytes") ?: 0) }, bytes = true)
        } }
    }
    item { AdminCard("Tăng trưởng người dùng", "30 ngày qua · Tổng cộng có thêm ${stats.rows("userDaily").sumOf { it.number("count") ?: 0 }} người") {
        AdminChart(stats.rows("userDaily").map { it.number("count") ?: 0 }, "Người dùng mới mỗi ngày", stats.rows("userDaily").map { it.text("day").takeLast(5) }, tag = "admin_chart_growth")
    } }
    item { AdminCard("Mức độ hoạt động phát nhạc", "14 ngày qua · ${adminCount(stats.rows("playDaily").sumOf { it.number("plays") ?: 0 })} lượt · ${String.format(java.util.Locale.ROOT, "%.1f", stats.rows("playDaily").sumOf { it.number("ms") ?: 0 } / 3600000.0)} giờ") {
        AdminChart(stats.rows("playDaily").map { it.number("plays") ?: 0 }, "Lượt phát mỗi ngày", stats.rows("playDaily").map { it.text("day").takeLast(5) }, secondary = stats.rows("playDaily").map { it.number("users") ?: 0 }, tag = "admin_chart_plays")
    } }
    item { AdminCard("Cơ cấu vai trò", "Chủ trang, siêu quản trị viên, quản trị viên và người dùng thông thường") {
        AdminDistribution(listOf("Chủ trang", "Siêu quản trị viên", "Quản trị viên", "Người dùng thông thường"),
            listOf("owner", "super", "admin", "user").map { stats.obj("roleCounts").number(it) ?: 0 })
    } }
    if (stats.obj("sourceCounts").isNotEmpty()) item { AdminCard("Nguồn người dùng", "Phân loại theo lần đăng nhập hoặc đăng ký thành công gần nhất") {
        AdminDistribution(listOf("Web", "Nền tảng X", "Khác"), listOf("web", "x", "other").map { stats.obj("sourceCounts").number(it) ?: 0 }, donut = false)
    } }
    item { AdminCard("Nội dung và tương tác", "So sánh số liệu trong ứng dụng") {
        AdminRanking(listOf("Lượt thích trong ứng dụng" to "likes", "Lượt thích trên Nhạc Vân NetEase" to "ncmLikes", "Danh sách phát tự tạo" to "playlists", "Bài hát trong danh sách phát" to "tracks",
            "Lịch sử phát" to "plays", "Liên kết Nhạc Vân NetEase" to "bindings", "Bài hát trong bộ nhớ đệm" to "songs", "Mục bộ nhớ đệm" to "cache")
            .map { (label, key) -> label to (stats.number(key) ?: 0) })
    } }
}

@Composable private fun AdminTraffic(state: AdminState) {
    val traffic = state.traffic
    var hourly by rememberSaveable { mutableStateOf(false) }
    AdminCard("Lưu lượng gửi đi", "Thống kê lưu lượng máy chủ") {
        if (traffic?.flag("available") == true) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                Column(Modifier.weight(1f)) { Text("Hôm nay", fontSize = 12.sp); Text(adminBytes(traffic.number("today")), fontSize = 24.sp) }
                Column(Modifier.weight(1f)) { Text("Tháng này", fontSize = 12.sp); Text(adminBytes(traffic.number("month")), fontSize = 24.sp) }
            }
            AdminMetric("Tốc độ hiện tại · trung bình 5 phút gần nhất", "${adminBytes(traffic.text("rate5m").toDoubleOrNull()?.toLong())}/s")
            traffic.rows("top").firstOrNull()?.let { AdminMetric("Đường dẫn có lưu lượng cao nhất hôm nay", adminBytes(it.number("bytes"))); Text(it.text("path"), fontSize = 11.sp) }
             SettingsSegmentedControl(listOf("30 ngày gần đây", "48 giờ gần đây"), if (hourly) 1 else 0, { hourly = it == 1 }, tag = "admin_traffic_range")
            val rows = traffic.rows(if (hourly) "hourly" else "daily")
             AdminChart(rows.map { it.number("bytes") ?: 0 }, "Lưu lượng gửi đi", rows.map { row -> row.number("t")?.let { java.text.SimpleDateFormat(if (hourly) "MM/dd HH:mm" else "MM/dd", java.util.Locale.getDefault()).format(java.util.Date(it * 1000)) } ?: row.text("t").takeLast(5) }, bytes = true, line = true, tag = "admin_chart_traffic")
             Text("Cập nhật lúc ${adminTime(traffic.number("generatedAt") ?: 0)}", fontSize = 12.sp,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
         } else Text(if (state.loading && traffic == null) "Đang tải thống kê lưu lượng…" else state.trafficError ?: traffic?.text("error")?.takeIf { it.isNotBlank() } ?: "Máy chủ hiện chưa cung cấp thống kê lưu lượng", fontSize = 13.sp)
    }
}

internal fun LazyListScope.adminClients(state: AdminState) {
    val clients = state.clients ?: return
    val current = clients.obj("current"); val cmx = clients.obj("cmx"); val apk = cmx.obj("apk")
     item { AdminCard("CurrentMusic", "Web và ứng dụng gốc") {
         AdminBadge("v${current.text("version")}"); AdminMetric("Gói cài đặt", adminBytes(current.number("size")))
         AdminMetric("Phát hành lúc", adminTime(current.number("publishedAt") ?: 0)); AdminMetric("Bản sao", if (current.flag("mirrored")) "Đã đồng bộ" else "Chưa đồng bộ")
    } }
     item { AdminCard("CurrentMusicX", "Ứng dụng Android gốc") {
         AdminBadge("v${cmx.text("version")}"); AdminMetric("Gói cài đặt", adminBytes(apk.number("size")))
         AdminMetric("Lượt tải xuống", adminCount(apk.number("downloads"))); AdminMetric("Bản sao", if (apk.flag("mirrored")) "Đã đồng bộ" else "Chưa đồng bộ")
         AdminMetric("Phát hành lúc", adminTime(cmx.number("publishedAt") ?: 0))
    } }
     if (cmx.text("body").isNotBlank()) item { AdminExpandable("Ghi chú cập nhật CurrentMusicX", cmx.text("name"), "admin_cmx_notes") { MarkdownText(cmx.text("body"), compact = true) } }
     if (current.text("changelog").isNotBlank()) item { AdminExpandable("Ghi chú cập nhật CurrentMusic", current.text("version"), "admin_current_notes") { MarkdownText(current.text("changelog"), compact = true) } }
     if (cmx.rows("history").isNotEmpty()) item { AdminCard("Lượt tải xuống các phiên bản", "Tổng cộng trên GitHub · ${cmx.rows("history").size} phiên bản gần đây nhất") {
        val releases = cmx.rows("history").reversed()
         AdminChart(releases.map { it.number("apkDownloads") ?: 0 }, "Lượt tải xuống", releases.map { it.text("version") }, tag = "admin_chart_downloads")
    } }
     item { SettingsListHeader("Các phiên bản trước") }
    items(cmx.rows("history")) { release -> SettingsGroup {
         SettingsPreference("v${release.text("version")}", "${adminCount(release.number("apkDownloads"))} lượt tải xuống · ${adminBytes(release.number("apkSize"))} · ${if (release.flag("mirrored")) "Đã sao chép" else "Chưa sao chép"}")
    } }
}

internal fun LazyListScope.adminSystem(state: AdminState, confirm: (AdminConfirmation) -> Unit) {
    val stats = state.overview ?: return
     item { AdminStats(listOf(AdminStat("Danh sách phát", adminCount(stats.number("playlists"))), AdminStat("Bản nhạc", adminCount(stats.number("tracks"))),
         AdminStat("Liên kết Nhạc Vân NetEase", adminCount(stats.number("bindings"))), AdminStat("Siêu dữ liệu bài hát", adminCount(stats.number("songs"))))) }
     item { SettingsListHeader("Bảo trì") }
    item { SettingsGroup {
         AdminActionRow("Xóa bộ nhớ đệm phụ trợ", "Hiện có ${adminCount(stats.number("cache"))} mục · lời bài hát, bình luận và thông tin chi tiết", MusicIcons.Storage,
            "admin_clear_cache", enabled = !state.loading && !state.busy, dangerous = true) { confirm(AdminConfirmation.Cache) }
    } }
     item { SettingsHint("Sau khi xóa, lần tải đầu tiên có thể chậm hơn; bộ nhớ đệm dò chất lượng âm thanh sẽ được giữ lại.") }
}
