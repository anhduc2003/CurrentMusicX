// Bottom dialog layout adapted from 123PanX / XBlocker / MIUIX, GPL-3.0-only.
package io.github.currencortex.music.feature.update

import io.github.currencortex.music.ui.component.MusicDialogButton
import io.github.currencortex.music.ui.util.showMusicMessage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.update.*
import io.github.currencortex.music.BuildConfig
import io.github.currencortex.music.ui.util.openExternalLink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.*
import io.github.currencortex.music.ui.component.MusicOverlayDialog as OverlayDialog
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun UpdatePage(service: UpdateService, transfer: UpdateTransfer, cover: String = "",
    origin: UpdatePageOrigin? = null, onPresentedChanged: (Boolean) -> Unit = {},
    versionPreview: String = BuildConfig.UPDATE_ANIMATION_PREVIEW_VERSION,
    sourceCheck: UpdateSourceCheck = remember { UpdateSourceProbe() }) {
    val visible by service.dialogVisible.collectAsStateWithLifecycle()
    val prompt by service.prompt.collectAsStateWithLifecycle()
    val state by service.state.collectAsStateWithLifecycle()
    val download by transfer.state.collectAsStateWithLifecycle()
    val selectedRelease by transfer.selectedRelease.collectAsStateWithLifecycle()
    val owner = LocalLifecycleOwner.current
    DisposableEffect(owner, transfer) {
        val observer = LifecycleEventObserver { _, event -> if (event == Lifecycle.Event.ON_RESUME) transfer.onResume() }
        owner.lifecycle.addObserver(observer)
        onDispose { owner.lifecycle.removeObserver(observer) }
    }
    val release = (state as? UpdateState.Available)?.release
    LaunchedEffect(release) {
        if (release != null && (download.download is UpdateDownloadState.Idle || selectedRelease?.sameAsset(release) == true)) transfer.selectRelease(release)
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    // Restored background work intentionally stores compact metadata, without release notes.
    LaunchedEffect(visible, selectedRelease?.version) {
        if (visible && selectedRelease?.notes?.isBlank() == true && state == UpdateState.Idle)
            service.check()
    }
    val shownState = if (selectedRelease != null && download.download !is UpdateDownloadState.Idle)
        UpdateState.Available(selectedRelease!!) else state
    val installedNotes by service.installedNotes.collectAsStateWithLifecycle()
    LaunchedEffect(visible, shownState is UpdateState.Available) {
        if (visible && shownState !is UpdateState.Available) service.loadInstalledNotes()
    }
    val settings by service.settings.state.collectAsStateWithLifecycle()
    UpdatePageHost(visible, shownState, onDismiss = service::dismissDialog,
        onRetry = { scope.launch { service.present(transfer) } },
        onIgnore = { scope.launch { service.ignoreCurrentRelease() } },
        onOpenRelease = { openExternalLink(context, it) }, transferState = download,
        onDownload = { source ->
            if (BuildConfig.UPDATE_DOWNLOAD_EXPERIENCE) {
                if (transfer.state.value.download is UpdateDownloadState.Ready) transfer.cancel()
                transfer.download(source)
            } else scope.launch { service.downloadLatest(transfer, source) }
        }, onInstall = transfer::install, onCancel = transfer::cancel,
        onChangeSource = transfer::changeSource, downloadExperience = BuildConfig.UPDATE_DOWNLOAD_EXPERIENCE,
        settings = settings, onAutoCheck = { scope.launch { service.settings.setAutoCheck(it) } },
        onChannel = { scope.launch { service.settings.setChannel(it); service.present(transfer) } }, cover = cover,
        origin = origin, onPresentedChanged = onPresentedChanged, versionPreview = versionPreview, sourceCheck = sourceCheck,
        onSourceSelected = { source -> scope.launch {
            try { service.settings.setDownloadSource(source) }
            catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { context.showMusicMessage("Không thể lưu nguồn tải xuống, vui lòng thử lại") }
        } },
        installedNotesState = installedNotes, onRetryInstalledNotes = { scope.launch { service.loadInstalledNotes() } })
    if (!visible) prompt?.let { available ->
        UpdateAvailablePrompt(available, onIgnore = { scope.launch { service.ignoreCurrentRelease() } },
            onView = service::viewPrompt, onDismiss = service::dismissPrompt)
    }
}

@Composable
fun UpdateDialogContent(state: UpdateState, onDismiss: () -> Unit, onRetry: () -> Unit,
                        onIgnore: () -> Unit, onOpenRelease: (String) -> Unit, preview: Boolean = false,
                        transferState: UpdateTransferState = UpdateTransferState(),
                        onDownload: ((UpdateSource) -> Unit)? = null, onInstall: () -> Unit = {},
                        onCancel: () -> Unit = {}, onChangeSource: ((UpdateSource) -> Unit)? = null,
                        downloadExperience: Boolean = false) {
    val release = (state as? UpdateState.Available)?.release
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var source by remember(release?.version) { mutableStateOf(if (release?.isCi == true) UpdateSource.GITHUB else UpdateSource.default) }
    var showSources by remember { mutableStateOf(false) }
    var previewProgress by remember { mutableFloatStateOf(-1f) }
    val previewDownloading = previewProgress in 0f..<1f
    val downloading = transferState.download is UpdateDownloadState.Downloading || previewDownloading
    val ready = transferState.download is UpdateDownloadState.Ready || previewProgress >= 1f
    LaunchedEffect(transferState.activeSource) { transferState.activeSource?.let { source = it } }
    val animatedProgress by animateFloatAsState(targetValue = when (val value = transferState.download) {
        is UpdateDownloadState.Downloading -> (value.received.toDouble() / value.total.coerceAtLeast(1)).toFloat().coerceIn(0f, 1f)
        is UpdateDownloadState.Ready -> 1f
        else -> previewProgress.coerceIn(0f, 1f)
    }, animationSpec = tween(180), label = "update_progress")
    OverlayDialog(show = true, insideMargin = DpSize(20.dp, 18.dp),
        onDismissRequest = if (transferState.installing) null else onDismiss) {
        Column(Modifier.fillMaxWidth().heightIn(max =
            (LocalConfiguration.current.screenHeightDp * .82f).dp.coerceAtMost(680.dp))) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(if (release != null && downloadExperience) "Tải bản trải nghiệm" else if (release != null) "Đã có phiên bản mới" else "Kiểm tra cập nhật", fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            if (release != null) Text("v${release.version}", fontSize = 14.sp,
                fontWeight = FontWeight.Medium, color = MiuixTheme.colorScheme.primary,
                maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        if (release != null) Text("${if (release.isCi) "Bản CI" else "Bản chính thức"} · Gói cài đặt ${formatSize(release.size)}",
            fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
            modifier = Modifier.padding(top = 4.dp))
        Spacer(Modifier.height(14.dp))
        if (downloadExperience) Text("Tiến trình tải xuống sẽ ưu tiên hiển thị trên Đảo động; sau khi hoàn tất, bạn có thể nhấn để xem chi tiết cập nhật.", fontSize = 13.sp,
            color = MiuixTheme.colorScheme.primary, modifier = Modifier.padding(bottom = 10.dp))
        val message = when (state) {
            UpdateState.Idle, UpdateState.Checking -> "Đang kiểm tra cập nhật…"
            UpdateState.UpToDate -> "Bạn đang dùng phiên bản mới nhất"
            is UpdateState.Failed -> state.reason
            is UpdateState.Available -> state.release.notes.ifBlank { "Phiên bản mới đã được phát hành." }
        }
        val notesHeight = (LocalConfiguration.current.screenHeightDp - 240).coerceIn(88, 440).dp
        val notesModifier = Modifier.fillMaxWidth().heightIn(max = notesHeight)
            .weight(1f, fill = false).verticalScroll(rememberScrollState()).testTag("update_message")
        if (release != null) MarkdownText(stripVersionHeadings(release.version, message), modifier = notesModifier, compact = true)
        else Text(message, fontSize = 15.sp, modifier = notesModifier)
        if (release != null) {
            if (preview) Text("Bản xem trước thử nghiệm", fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
            Spacer(Modifier.height(12.dp))
            HorizontalDivider()
            Row(Modifier.fillMaxWidth().clickable(
                enabled = (!downloading || onChangeSource != null) && !ready && !transferState.installing,
                role = Role.Button, onClick = { showSources = true })
                .padding(vertical = 14.dp).testTag("update_source"), verticalAlignment = Alignment.CenterVertically) {
                Text("Nguồn tải xuống · ${source.compactLabel()}", modifier = Modifier.weight(1f), fontSize = 15.sp)
                Text("Chọn ›", color = MiuixTheme.colorScheme.onSurfaceVariantSummary, fontSize = 13.sp)
            }
            when {
                previewDownloading -> {
                    Text("${formatReceived((release.size * previewProgress).toLong())} / ${formatSize(release.size)}", fontSize = 13.sp)
                    LinearProgressIndicator(progress = animatedProgress, modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
                }
                 previewProgress >= 1f -> Text("Đã tải xong bản thử nghiệm, không tải gói cài đặt thực tế")
                else -> when (val download = transferState.download) {
                    is UpdateDownloadState.Downloading -> {
                        transferState.activeSource?.let { active ->
                            Text(if (transferState.attempt > 1) "Đã chuyển sang ${active.label}（${transferState.attempt}/${transferState.totalSources}）"
                                else "Đang dùng ${active.label}", fontSize = 12.sp, modifier = Modifier.testTag("update_active_source"))
                        }
                        Text("${formatReceived(download.received)} / ${formatSize(download.total)} · ${(animatedProgress * 100).toInt()}%", fontSize = 13.sp)
                        LinearProgressIndicator(progress = animatedProgress,
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp))
                        Text("Tiếp tục tải xuống sau khi đóng hộp thoại; xem tiến trình trong Cài đặt hoặc thông báo", fontSize = 12.sp,
                            color = MiuixTheme.colorScheme.onSurfaceVariantSummary, modifier = Modifier.padding(top = 6.dp))
                    }
                    is UpdateDownloadState.Ready -> Text("Đã tải xong, gói cài đặt đã được xác minh", fontSize = 13.sp,
                        color = MiuixTheme.colorScheme.primary)
                    is UpdateDownloadState.Failed -> Text(download.message, fontSize = 13.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
                    UpdateDownloadState.Idle -> Text("Tự động thử nguồn khác nếu tải xuống thất bại", fontSize = 12.sp,
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                }
            }
            transferState.message?.let { Text(it, fontSize = 13.sp, maxLines = 3, overflow = TextOverflow.Ellipsis) }
        }
        Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MusicDialogButton(if (downloading) "Tải xuống nền" else "Đóng", onClick = onDismiss,
                enabled = !transferState.installing, minHeight = 48.dp, cornerRadius = 16.dp,
                insideMargin = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                textStyle = MiuixTheme.textStyles.body1.copy(fontSize = 15.sp),
                modifier = Modifier.weight(1f).testTag("update_close"))
            if (release != null) {
                val verifiedAsset = release.apkUrl != null && release.size in 1..UpdateDownloader.MAX_APK_BYTES &&
                    Regex("[a-f0-9]{64}").matches(release.sha256)
                val label = when {
                    transferState.installing -> "Đang yêu cầu cài đặt…"
                    ready -> "Yêu cầu cài đặt"
                    !verifiedAsset && !preview -> "Xem bản phát hành"
                    transferState.download is UpdateDownloadState.Failed -> "Thử tải xuống lại"
                    downloading -> "Hủy tải xuống"
                    else -> "Tải bản cập nhật"
                }
                Button(onClick = {
                    if (preview) {
                         if (ready) context.showMusicMessage("Bản xem trước thử nghiệm sẽ không cài đặt ứng dụng")
                        else scope.launch {
                            previewProgress = 0f
                            repeat(10) { delay(120); previewProgress = (it + 1) / 10f }
                        }
                    } else if (downloading) onCancel()
                    else if (ready) onInstall()
                    else if (verifiedAsset && onDownload != null) onDownload(source)
                    else onOpenRelease(release.pageUrl)
                }, enabled = !previewDownloading && !transferState.installing,
                    minHeight = 48.dp, cornerRadius = 16.dp, insideMargin = PaddingValues(horizontal = 12.dp, vertical = 10.dp),
                    colors = ButtonDefaults.buttonColorsPrimary(), modifier = Modifier.weight(1f).testTag("update_download")) {
                    Text(label, fontSize = 15.sp, fontWeight = FontWeight.Medium)
                }
            } else if (state is UpdateState.Failed) Button(onClick = onRetry,
                colors = ButtonDefaults.buttonColorsPrimary(), modifier = Modifier.weight(1f)) { Text("Thử lại") }
        }
        if (release != null && !downloading && !ready && !transferState.installing) Box(
            Modifier.fillMaxWidth().heightIn(min = 40.dp).clickable(role = Role.Button, onClick = onIgnore)
                .testTag("update_ignore"), contentAlignment = Alignment.Center) {
             Text("Bỏ qua phiên bản này", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        }
        }
    }
    if (showSources && release != null) UpdateSourceDialog(release, source,
        channel = if (release.isCi) UpdateChannel.CI else UpdateChannel.STABLE,
        downloading = downloading, onDismiss = { showSources = false }, onSelect = { next ->
            if (next != source) { source = next; if (downloading) onChangeSource?.invoke(next) }
            showSources = false
        })
}

private fun UpdateSource.compactLabel() = when (this) {
     UpdateSource.CURRENTMUSIC -> "Mirror chuyên dụng"
     UpdateSource.GITHUB -> "GitHub chính thức"
     UpdateSource.TBAP -> "Mirror TBAP"
     UpdateSource.GH_PROXY -> "Mirror GH-Proxy"
     UpdateSource.GEEKERTAO -> "Mirror Geekertao"
     UpdateSource.DPIK -> "Mirror DPIK"
}

private fun formatReceived(value: Long) = if (value <= 0) "0 MB" else formatSize(value)

 private fun formatSize(value: Long): String = if (value <= 0) "Chưa có thông tin kích thước" else when {
    value >= 1024 * 1024 -> String.format(java.util.Locale.ROOT, "%.1f MB", value / (1024.0 * 1024))
    value >= 1024 -> String.format(java.util.Locale.ROOT, "%.1f KB", value / 1024.0)
    else -> "$value B"
}
