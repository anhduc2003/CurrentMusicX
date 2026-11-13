package io.github.currencortex.music.core.media.engine

import io.github.currencortex.music.data.settings.AudioOutputMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/** Null means unavailable. Requested values are never promoted to measured values. */
data class AudioPcmFormat(val sampleRate: Int? = null, val encoding: Int? = null, val channels: Int? = null) {
    val bitDepth: Int? get() = when (encoding) { 3 -> 8; 2 -> 16; 21 -> 24; 22, 4 -> 32; else -> null }
    fun describe(): String = "${sampleRate?.let { "$it Hz" } ?: "Không rõ tần số lấy mẫu"} · ${bitDepth?.let { "$it bit" } ?: "Không rõ độ sâu bit"}" +
        (if (encoding == 4) " float" else "") + " · ${channels?.let { "$it ch" } ?: "Không rõ số kênh"}"
}
enum class BitPerfectEvidence { INACTIVE, REQUESTED, ANDROID_CONFIGURATION_VERIFIED, UNVERIFIED, FALLBACK }
data class AudioDiagnosticsSnapshot(
    val requestedMode: AudioOutputMode = AudioOutputMode.AUDIO_TRACK,
    val backend: String? = null,
    val source: AudioPcmFormat = AudioPcmFormat(),
    val decoded: AudioPcmFormat = AudioPcmFormat(),
    val requestedRate: Int? = null,
    val output: AudioPcmFormat = AudioPcmFormat(),
    val formatEvidence: String = "Chưa tạo đầu ra âm thanh",
    val device: String? = null,
    val deviceId: Int? = null,
    val usbCapabilities: String? = null,
    val softwareBufferFrames: Long? = null,
    val deviceBufferFrames: Int? = null,
    val capacityFrames: Int? = null,
    val underruns: Int? = null,
    val latencyMs: Double? = null,
    val latencyEvidence: String = "Hệ thống không cung cấp",
    val requestedEffects: List<String> = emptyList(),
    val appliedEffects: List<String> = emptyList(),
    val processingBypass: String? = null,
    val bitPerfect: BitPerfectEvidence = BitPerfectEvidence.INACTIVE,
    val fallbackReason: String? = null,
    val playing: Boolean = false,
)
class AudioDiagnostics {
    private val mutable = MutableStateFlow(AudioDiagnosticsSnapshot())
    val state = mutable.asStateFlow()
    fun update(change: (AudioDiagnosticsSnapshot) -> AudioDiagnosticsSnapshot) = mutable.update(change)
    fun source(value: AudioPcmFormat) = update { it.copy(source = value) }
    fun decoded(value: AudioPcmFormat) = update { it.copy(decoded = value) }
    fun resetTrack() = update { it.copy(source = AudioPcmFormat(), decoded = AudioPcmFormat(), output = AudioPcmFormat(),
        backend = null, formatEvidence = "Đang chờ giải mã và đầu ra", appliedEffects = emptyList(), latencyMs = null,
        device = null, deviceId = null, usbCapabilities = null, softwareBufferFrames = null, deviceBufferFrames = null, capacityFrames = null,
        underruns = null, playing = false) }
    fun text(): String = state.value.let { s -> buildString {
        appendLine("Chế độ yêu cầu: ${s.requestedMode.label}"); appendLine("Backend thực tế: ${s.backend ?: "Hệ thống không cung cấp"}")
        appendLine("Khai báo nguồn: ${s.source.describe()}"); appendLine("PCM đã giải mã: ${s.decoded.describe()}")
        appendLine("Tần số lấy mẫu yêu cầu: ${s.requestedRate ?: "Theo nguồn âm thanh"}"); appendLine("Đầu ra: ${s.output.describe()}")
        appendLine("Bằng chứng định dạng: ${s.formatEvidence}"); appendLine("Thiết bị: ${s.device ?: "Hệ thống không cung cấp"}")
        appendLine("Khả năng được khai báo của USB DAC: ${s.usbCapabilities ?: "Hệ thống không cung cấp"}")
        appendLine("Số khung bộ đệm phần mềm: ${s.softwareBufferFrames ?: "Hệ thống không cung cấp"}")
        appendLine("Số khung bộ đệm thiết bị: ${s.deviceBufferFrames ?: "Hệ thống không cung cấp"}")
        appendLine("Sức chứa bộ đệm (khung): ${s.capacityFrames ?: "Hệ thống không cung cấp"}"); appendLine("Lần thiếu dữ liệu: ${s.underruns ?: "Hệ thống không cung cấp"}")
        appendLine("Độ trễ: ${s.latencyMs?.let { "%.2f ms".format(java.util.Locale.ROOT, it) } ?: "Không thể xác minh"} · ${s.latencyEvidence}")
        appendLine("DSP được yêu cầu: ${s.requestedEffects.joinToString().ifBlank { "Không có" }}")
        appendLine("DSP đã quan sát: ${s.appliedEffects.joinToString().ifBlank { "Không quan sát thấy xử lý" }}")
        appendLine("Chính sách xử lý: ${s.processingBypass ?: "Chuỗi xử lý thông thường"}"); appendLine("Trạng thái bit-perfect: ${s.bitPerfect}")
        appendLine("Độ trong suốt bit-perfect của DAC cuối: Chưa xác minh"); appendLine("Codec Bluetooth hiện tại: Hệ thống không cung cấp")
        s.fallbackReason?.let { appendLine("Lý do chuyển về phương án dự phòng: $it") }
    } }
}
