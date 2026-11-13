package io.github.currencortex.music.core.media.engine

import io.github.currencortex.music.data.settings.AudioOutputMode

data class AudioDeviceCapabilities(val rates: Set<Int> = emptySet(), val encodings: Set<Int> = emptySet(),
    val channels: Set<Int> = emptySet(), val usb: Boolean = false)
data class AudioFormatDecision(val accepted: Boolean, val reason: String? = null, val requiresRuntimeVerification: Boolean = true)
/** Descriptor/capability lists are hints; successful stream creation and observed format remain required. */
object AudioFormatNegotiator {
    fun negotiate(format: AudioPcmFormat, mode: AudioOutputMode, device: AudioDeviceCapabilities): AudioFormatDecision {
        if ((format.sampleRate ?: 0) <= 0 || (format.channels ?: 0) <= 0 || format.bitDepth == null)
            return AudioFormatDecision(false, "Bộ giải mã không báo cáo định dạng PCM hợp lệ")
        if (mode == AudioOutputMode.BIT_PERFECT && !device.usb) return AudioFormatDecision(false, "Không tìm thấy thiết bị đầu ra USB")
        if (mode == AudioOutputMode.DIRECT && format.encoding != 2) return AudioFormatDecision(false, "Backend Direct hiện chỉ hỗ trợ PCM 16 bit")
        if (device.rates.isNotEmpty() && format.sampleRate !in device.rates) return AudioFormatDecision(false, "Thiết bị không khai báo tần số lấy mẫu này")
        if (device.encodings.isNotEmpty() && format.encoding !in device.encodings) return AudioFormatDecision(false, "Thiết bị không khai báo độ sâu bit PCM này")
        if (device.channels.isNotEmpty() && format.channels !in device.channels) return AudioFormatDecision(false, "Thiết bị không khai báo số kênh này")
        return AudioFormatDecision(true)
    }
}
