package io.github.currencortex.music.core.media

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal object NeteaseUsbDevices {
    private val changed = MutableStateFlow(0)
    val revision = changed.asStateFlow()
    fun refresh() { changed.value += 1 }
    fun devices(context: Context) = context.getSystemService(UsbManager::class.java).deviceList.values
        .filter { device -> (0 until device.interfaceCount).any {
            val face = device.getInterface(it)
            face.interfaceClass == UsbConstants.USB_CLASS_AUDIO && face.interfaceSubclass == 2 &&
                (0 until face.endpointCount).any { endpoint -> face.getEndpoint(endpoint).let {
                    usableUsbIsoOutput(it.direction, it.type, it.maxPacketSize, it.attributes)
                } }
        } }.sortedBy { it.deviceId }
    fun key(device: UsbDevice) = "${device.vendorId}:${device.productId}:${device.deviceName}"
    fun label(device: UsbDevice) = device.productName ?: "USB Audio ${device.vendorId.toString(16)}:${device.productId.toString(16)}"
    /** Reads descriptors only, without claiming an interface or requesting permission. */
    fun descriptors(context: Context, device: UsbDevice): UsbAudioDescriptorReport {
        val manager = context.getSystemService(UsbManager::class.java)
        if (!manager.hasPermission(device)) return UsbAudioDescriptorReport(emptyList(), "Chưa được cấp quyền đọc descriptor USB")
        val connection = manager.openDevice(device)
            ?: return UsbAudioDescriptorReport(emptyList(), "Hệ thống không thể mở kết nối descriptor USB")
        return try { UsbAudioDescriptorParser.parse(connection.rawDescriptors) }
        catch (_: Exception) { UsbAudioDescriptorReport(emptyList(), "Hệ thống không cung cấp đầy đủ descriptor USB Audio") }
        finally { connection.close() }
    }
    fun select(devices: List<UsbDevice>, selected: String): UsbDevice {
        if (selected.isNotEmpty()) return devices.singleOrNull { key(it) == selected }
            ?: error("[USB_MISSING] Thiết bị USB Host đã chọn không được kết nối, vui lòng chọn lại")
        check(devices.isNotEmpty()) { "[USB_MISSING] Không có thiết bị đầu ra USB Audio nào được kết nối" }
        check(devices.size == 1) { "[USB_AMBIGUOUS] Có nhiều thiết bị âm thanh USB được kết nối, vui lòng chọn DAC mục tiêu" }
        return devices.single()
    }
    fun requestPermission(context: Context, device: UsbDevice) {
        val intent = Intent(context, NeteaseUsbPermissionReceiver::class.java).setAction("${context.packageName}.USB_AUDIO_PERMISSION")
        val pending = PendingIntent.getBroadcast(context, device.deviceId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or if (android.os.Build.VERSION.SDK_INT >= 31) PendingIntent.FLAG_MUTABLE else 0)
        context.getSystemService(UsbManager::class.java).requestPermission(device, pending)
    }
}

/** Explicit, non-exported PendingIntent recipient; no automatic playback on authorization. */
class NeteaseUsbPermissionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "${context.packageName}.USB_AUDIO_PERMISSION") return
        val manager = context.getSystemService(UsbManager::class.java)
        @Suppress("DEPRECATION") val device = intent.getParcelableExtra<UsbDevice>(UsbManager.EXTRA_DEVICE)
        val authorized = device != null && manager.deviceList.values.any { it.deviceId == device.deviceId } && manager.hasPermission(device)
        CurrentMusicAudioDiagnostics.record("usbHost.permissionGranted=$authorized")
        NeteaseUsbDevices.refresh()
    }
}
