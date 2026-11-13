package io.github.currencortex.music.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.network.*
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun CurrentMusicServerDialog(vm: MusicSettingsViewModel, onDismiss: () -> Unit, onCustom: () -> Unit) {
    val settings by vm.settings.collectAsStateWithLifecycle()
    val state by vm.state.collectAsStateWithLifecycle()
    val health by vm.serverHealth.collectAsStateWithLifecycle()
    val automatic by vm.automaticServer.collectAsStateWithLifecycle()
    val checking by vm.serverProbeBusy.collectAsStateWithLifecycle()
    LaunchedEffect(vm) { vm.refreshServers() }
    MusicChoiceDialog("Máy chủ CurrentMusic", onDismiss, footer = {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MusicDialogButton(if (checking) "Đang kiểm tra" else "Kiểm tra lại", { vm.refreshServers() },
                Modifier.weight(1f).testTag("server_probe_refresh"), enabled = !checking)
            MusicDialogButton("Đóng", onDismiss, Modifier.weight(1f))
        }
    }) {
        Text("Tự động chọn tuyến khả dụng nhanh hơn khi khởi động. Độ trễ là thời gian phản hồi của giao diện thông báo, thứ tự danh sách được cố định.",
            Modifier.padding(horizontal = 24.dp, vertical = 12.dp), fontSize = 12.sp,
            color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        CurrentMusicServer.entries.forEach { candidate ->
            val address = when (candidate) {
                CurrentMusicServer.AUTO -> automatic
                CurrentMusicServer.CUSTOM -> settings.customServer
                else -> requireNotNull(candidate.url)
            }
            val label = when (candidate) {
                CurrentMusicServer.MAIN -> "music.20110208.xyz"
                CurrentMusicServer.CDN -> "cm.bileizhen.top"
                CurrentMusicServer.CDN_GEEKERTAO -> "currentmusic.geekertao.com"
                CurrentMusicServer.CDN_CTCEHS -> "music.ctcehs.com"
                else -> candidate.label
            }
            MusicDialogChoiceRow(label, settings.serverMode == candidate, {
                if (candidate == CurrentMusicServer.CUSTOM) onCustom()
                else { vm.selectServer(candidate); onDismiss() }
            }, Modifier.testTag("server_source_${candidate.name}"), enabled = !state.busy,
                showDivider = candidate != CurrentMusicServer.CUSTOM,
                summary = when (candidate) {
                    CurrentMusicServer.AUTO -> "Ưu tiên: ${CurrentMusicServer.presets.first { it.url == automatic }.label}"
                    CurrentMusicServer.CUSTOM -> settings.customServer.ifBlank { "Nhập địa chỉ máy chủ của bạn" }
                    CurrentMusicServer.MAIN -> "Máy chủ chính"
                    CurrentMusicServer.CDN, CurrentMusicServer.CDN_GEEKERTAO, CurrentMusicServer.CDN_CTCEHS -> "CDN"
                }, leadingContent = {
                    ServerHealthBadge(candidate, health[address])
                })
        }
    }
}

@Composable private fun ServerHealthBadge(source: CurrentMusicServer, health: ServerHealth?) {
    val label = when (health?.status) {
        ServerStatus.CHECKING -> "Đang kiểm tra"
        ServerStatus.AVAILABLE -> "Khả dụng"
        ServerStatus.TIMEOUT -> "Quá thời gian chờ"
        ServerStatus.UNAVAILABLE -> "Không khả dụng"
        null -> "Chưa kiểm tra"
    }
    val dark = MiuixTheme.colorScheme.onBackground.luminance() > .5f
    val color = when (health?.status) {
        ServerStatus.AVAILABLE -> if (dark) Color(0xFF73D4A4) else Color(0xFF24865B)
        ServerStatus.TIMEOUT -> if (dark) Color(0xFFE8BA69) else Color(0xFFAD731B)
        ServerStatus.UNAVAILABLE -> if (dark) Color(0xFFF28F99) else Color(0xFFCD5058)
        else -> MiuixTheme.colorScheme.onSurfaceVariantSummary
    }
    Column(Modifier.width(72.dp).background(color.copy(alpha = .12f), RoundedCornerShape(10.dp))
        .padding(horizontal = 4.dp, vertical = 5.dp).testTag("server_source_badge_${source.name}")
        .semantics { contentDescription = "${source.label}: $label, ${health?.latencyMs?.let { "${it} mili giây" }.orEmpty()}" },
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, color = color, fontSize = 11.sp, lineHeight = 14.sp)
        Text(health?.latencyMs?.let { "$it ms" } ?: "— ms", color = color, fontSize = 10.sp, lineHeight = 13.sp)
    }
}

@Composable internal fun CustomMusicServerDialog(saved: String, state: NetworkSettingsState,
    onSave: (String) -> Unit, onDismiss: () -> Unit) {
    var address by rememberSaveable { mutableStateOf(saved) }
    var error by remember { mutableStateOf<String?>(null) }
    MusicChoiceDialog("Máy chủ tùy chỉnh", onDismiss, footer = {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MusicDialogButton("Hủy", onDismiss, Modifier.weight(1f))
            MusicDialogButton("Lưu", {
                val normalized = try { ServerUrl.normalize(address) } catch (failure: IllegalArgumentException) {
                    error = failure.message; return@MusicDialogButton
                }
                onSave(normalized); onDismiss()
            }, Modifier.weight(1f).testTag("save_server"), enabled = !state.busy && address.isNotBlank())
        }
    }) {
        Column(Modifier.padding(horizontal = 24.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            TextField(address, { address = it; error = null }, singleLine = true, label = "Địa chỉ máy chủ",
                modifier = Modifier.fillMaxWidth().testTag("server_input"))
            Text("Nhập đầy đủ địa chỉ API, bao gồm các đường dẫn như /cm/. Cần đăng nhập lại sau khi chuyển sang máy chủ khác.",
                fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            error?.let { Text(it, fontSize = 12.sp, color = MiuixTheme.colorScheme.error) }
        }
    }
}
