package io.github.currencortex.music.feature.support

import android.content.Intent
import android.os.SystemClock
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import io.github.currencortex.music.ui.component.MusicDialogButton
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.component.MusicOverlayDialog
import io.github.currencortex.music.ui.component.nativeui.*
import io.github.currencortex.music.ui.util.showMusicError
import io.github.currencortex.music.ui.util.showMusicMessage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun SupportScreen(onBack: () -> Unit, afdianUrl: String = SupportConfig.afdianUrl) {
    val context = LocalContext.current
    val appIcon = remember(context) {
        context.packageManager.getApplicationIcon(context.applicationInfo).toBitmap(192, 192).asImageBitmap()
    }
    val scope = rememberCoroutineScope()
    val gate = remember { SupportClickGate(SystemClock::elapsedRealtime) }
    var preview by rememberSaveable { mutableStateOf<SupportCode?>(null) }
    var previewOpen by rememberSaveable { mutableStateOf(false) }
    var pendingSave by rememberSaveable { mutableStateOf<SupportCode?>(null) }
    var busy by remember { mutableStateOf(false) }
    val save = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("image/png")) { uri ->
        val code = pendingSave
        pendingSave = null
        if (uri != null && code != null) scope.launch {
            busy = true
            try {
                SupportFiles.save(context, code, uri)
                context.showMusicMessage("Đã lưu ảnh")
            } catch (cancelled: CancellationException) { throw cancelled }
              catch (_: Exception) { context.showMusicError("Không thể lưu ảnh, vui lòng thử lại") }
            finally { busy = false }
        }
    }
    SettingsPage("Hỗ trợ nhà phát triển", "support_screen", onBack) {
        item("brand") {
            SettingsGroup {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Image(appIcon, "Biểu tượng CurrentMusic", Modifier.size(64.dp))
                    Text("CurrentMusic", fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
                    Text("CurrentMusic được duy trì và phát triển liên tục bởi một nhà phát triển độc lập. Nếu bạn thích ứng dụng nghe nhạc này, bạn có thể tự nguyện ủng hộ dự án để hỗ trợ việc phát triển và bảo trì.",
                        fontSize = 15.sp, lineHeight = 23.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                    Text("Nhà phát triển · bileizhen", fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                }
            }
        }
        item("afdian") {
            SettingsSection("Afdian", MusicIcons.Heart) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Ủng hộ việc phát triển liên tục của CurrentMusic thông qua Afdian.", fontSize = 15.sp, lineHeight = 22.sp)
                    val available = supportHttpsUrl(afdianUrl) != null
                    GlassActionButton(if (available) "Đến Afdian" else "Chưa mở", {
                        if (gate.acquire()) when (openSupportLink(context, afdianUrl)) {
                            SupportLinkResult.INVALID -> context.showMusicError("Liên kết không hợp lệ")
                            SupportLinkResult.NO_APP -> context.showMusicError("Không tìm thấy ứng dụng nào có thể mở liên kết")
                            SupportLinkResult.OPENED -> Unit
                        }
                    }, Modifier.testTag("support_afdian"), primary = true, enabled = available)
                }
            }
        }
        item("codes") {
            SettingsSection("Ủng hộ nhanh", MusicIcons.Image) {
                SupportCode.entries.forEachIndexed { index, code ->
                    if (index > 0) SettingsDivider()
                    SettingsPreference(code.title, code.summary, Modifier.testTag("support_${code.name}"),
                        onClick = { preview = code; previewOpen = true },
                        trailing = { Icon(MusicIcons.Forward, null, Modifier.size(16.dp)) })
                }
            }
        }
        item("privacy") {
            SettingsHint("Mọi khoản ủng hộ đều hoàn toàn tự nguyện và không ảnh hưởng đến việc sử dụng CurrentMusic. Ủng hộ không mở khóa thêm tính năng phần mềm hay quyền lợi thành viên.")
            SettingsHint("Không cần đăng nhập. Thanh toán do nền tảng tương ứng xử lý. CurrentMusic không thu thập thông tin xác thực thanh toán, không đọc lịch sử thanh toán và cũng không xác định giao dịch đã hoàn tất hay chưa.")
        }
    }
    val code = preview
    val dialogMaxHeight = with(LocalDensity.current) { LocalWindowInfo.current.containerSize.height.toDp() * .65f }
    MusicOverlayDialog(show = previewOpen, title = code?.title, onDismissRequest = { previewOpen = false },
        // Opaque panel: the image cannot become part of a refracting glass surface.
        backgroundColor = MiuixTheme.colorScheme.surfaceContainer) {
        if (code != null) Column(Modifier.fillMaxWidth()
            .heightIn(max = dialogMaxHeight)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            SupportQrImage(code)
            Text("Vui lòng dùng ${code.scanner} để quét", fontSize = 15.sp)
            Text("Bạn có thể lưu ảnh trước trên cùng một điện thoại, sau đó chọn ảnh trong album của tính năng quét mã của ${code.scanner}.",
                fontSize = 13.sp, lineHeight = 19.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
            MusicDialogButton("Lưu ảnh", {
                if (!busy && pendingSave == null) {
                    pendingSave = code
                    try { save.launch(code.fileName) }
                    catch (_: Exception) { pendingSave = null; context.showMusicError("Không tìm thấy ứng dụng nào có thể lưu ảnh") }
                }
            }, Modifier.fillMaxWidth().testTag("support_save"), enabled = !busy && pendingSave == null)
            MusicDialogButton("Chia sẻ ảnh", {
                if (!busy) {
                    busy = true
                    scope.launch {
                        try { context.startActivity(Intent.createChooser(SupportFiles.shareIntent(context, code), code.title)) }
                        catch (cancelled: CancellationException) { throw cancelled }
                        catch (_: Exception) { context.showMusicError("Không thể chia sẻ ảnh, vui lòng thử lại") }
                        finally { busy = false }
                    }
                }
            }, Modifier.fillMaxWidth().testTag("support_share"), enabled = !busy)
            MusicDialogButton("Đóng", { previewOpen = false }, Modifier.fillMaxWidth().testTag("support_close"))
        }
    }
}

@Composable
internal fun SupportQrImage(code: SupportCode) {
    val context = LocalContext.current
    val request = remember(context, code) { ImageRequest.Builder(context).data(code.image).crossfade(false).build() }
    // One square, unmodified local image. No elastic press, glass capture, tint or image animation.
    AsyncImage(request, code.title, Modifier.widthIn(max = 320.dp).fillMaxWidth().aspectRatio(1f)
        .background(Color.White).padding(12.dp).testTag("support_qr_${code.name}"), contentScale = ContentScale.Fit)
}
