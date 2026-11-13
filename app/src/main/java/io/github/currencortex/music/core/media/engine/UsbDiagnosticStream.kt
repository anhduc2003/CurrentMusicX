package io.github.currencortex.music.core.media.engine

import androidx.media3.exoplayer.audio.AudioOutput
import io.github.currencortex.music.core.media.BitPerfectStream
import io.github.currencortex.music.core.media.NeteaseUsbStream
import java.nio.ByteBuffer

/** Authorized USB FD and descriptor declarations do not establish the DAC's active clock/format. */
internal data class UsbOutputSnapshot(val deviceLabel: String, val requestedWireFormat: AudioPcmFormat,
    val declaredCapabilities: String)

internal fun usbDiagnostics(previous: AudioDiagnosticsSnapshot, snapshot: UsbOutputSnapshot) = previous.copy(
backend = "USB Host / Nhạc Vân NetEase bộ máy nguyên bản", device = snapshot.deviceLabel,    // UsbDevice.deviceId and AudioDeviceInfo.id are different namespaces.
    deviceId = null, output = AudioPcmFormat(), softwareBufferFrames = null,
    deviceBufferFrames = null, capacityFrames = null, underruns = null, latencyMs = null,
    latencyEvidence = "Trình điều khiển không cung cấp dấu thời gian DAC hoặc độ trễ đầu ra có thể xác minh được",
    usbCapabilities = snapshot.declaredCapabilities,
formatEvidence = "Động cơ yêu cầu ${snapshot.requestedWireFormat.describe()}；định dạng/đồng hồ DAC thực tế chưa được xác minh",    bitPerfect = BitPerfectEvidence.UNVERIFIED,
)

/** Samples metadata on the writer thread; never adds a second PCM buffer, DSP or audio owner. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal class UsbDiagnosticStream(private val delegate: NeteaseUsbStream,
    private val diagnostics: AudioDiagnostics, private val primary: () -> Boolean) : BitPerfectStream {
    private var published = false
    private fun publish() {
        if (published || !primary()) return
        val value = delegate.snapshot()
        diagnostics.update { usbDiagnostics(it, value) }
        published = true
    }
    override val output: AudioOutput = object : AudioOutput by delegate.output {
        override fun write(buffer: ByteBuffer, encodedAccessUnitCount: Int, presentationTimeUs: Long): Boolean {
            val result = delegate.output.write(buffer, encodedAccessUnitCount, presentationTimeUs)
            runCatching { publish() } // Observers must never turn successful delivery into output failure.
            return result
        }
        override fun play() { delegate.output.play(); runCatching { publish() } }
    }
    override fun verify() { delegate.verify(); runCatching { publish() } }
    override fun close() = delegate.close()
}
