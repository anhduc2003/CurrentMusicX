package io.github.currencortex.music.feature.settings

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.core.media.NeteaseUsbDevices
import io.github.currencortex.music.data.settings.AudioOutputSettings
import io.github.currencortex.music.ui.component.MusicDestinationRow
import io.github.currencortex.music.ui.component.MusicDialogChoiceRow
import io.github.currencortex.music.ui.component.nativeui.SettingsGroup as Card
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun NeteaseUsbOutputSettings(settings: AudioOutputSettings, select: (String) -> Unit) {
    val context = LocalContext.current
    val revision by NeteaseUsbDevices.revision.collectAsStateWithLifecycle()
    val devices = remember(revision) { NeteaseUsbDevices.devices(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(context, lifecycle) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) { NeteaseUsbDevices.refresh() }
        }
        val filter = IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED).apply { addAction(UsbManager.ACTION_USB_DEVICE_DETACHED) }
        ContextCompat.registerReceiver(context, receiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED)
        val observer = LifecycleEventObserver { _, event -> if (event == Lifecycle.Event.ON_RESUME) NeteaseUsbDevices.refresh() }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer); context.unregisterReceiver(receiver) }
    }
    val manager = remember(context) { context.getSystemService(UsbManager::class.java) }
    var message by remember { mutableStateOf<String?>(null) }
    val hint = MiuixTheme.colorScheme.onSurfaceVariantSummary
    Card { Column(Modifier.padding(vertical = 8.dp).testTag("netease_usb_settings")) {
        Text("Đầu ra độc quyền USB Host", Modifier.padding(horizontal = 20.dp, vertical = 10.dp), fontSize = 18.sp)
        Text("Tích hợp trình điều khiển USB arm64 của Nhạc Vân NetEase 9.6.15, truy cập trực tiếp thiết bị đã được cấp quyền. Hỗ trợ PCM số nguyên 16/24/32-bit, đơn âm hoặc âm thanh nổi, ở tần số 8–192 kHz; 24-bit packed được mở rộng thành 32-bit. Âm lượng đồng bộ với âm lượng đa phương tiện của hệ thống, có thể điều chỉnh bằng phím âm lượng của điện thoại; ứng dụng sẽ giảm âm lượng PCM, trình điều khiển gốc vẫn có xử lý tăng âm lượng dần, chế độ này không được đánh dấu là Bit-perfect.",
            Modifier.padding(horizontal = 20.dp, vertical = 8.dp), fontSize = 13.sp, color = hint)
        if (devices.isEmpty()) Text("Chưa kết nối thiết bị USB Audio; sau khi kết nối, hãy cấp quyền tại đây.", Modifier.padding(20.dp), fontSize = 14.sp)
        else devices.forEach { device ->
            val selected = settings.usbDeviceKey == NeteaseUsbDevices.key(device) || (settings.usbDeviceKey.isEmpty() && devices.size == 1)
            MusicDialogChoiceRow(NeteaseUsbDevices.label(device), selected, {
                select(NeteaseUsbDevices.key(device))
                if (!manager.hasPermission(device)) try { NeteaseUsbDevices.requestPermission(context, device) }
                catch (_: Exception) { message = "Yêu cầu cấp quyền USB thất bại, vui lòng kết nối lại thiết bị" }
            }, summary = if (manager.hasPermission(device)) "Đã được cấp quyền truy cập USB" else "Nhấn để chọn và cấp quyền truy cập USB")
            if (selected && !manager.hasPermission(device)) MusicDestinationRow("Cấp quyền truy cập USB DAC", {
                try { NeteaseUsbDevices.requestPermission(context, device) }
                catch (_: Exception) { message = "Yêu cầu cấp quyền USB thất bại, vui lòng kết nối lại thiết bị" }
            }, Modifier.testTag("request_usb_audio_permission"), summary = "Sau khi hệ thống cấp quyền thành công, hãy bắt đầu phát thủ công")
        }
        message?.let { Text(it, Modifier.padding(20.dp), fontSize = 13.sp, color = hint) }
    } }
}
