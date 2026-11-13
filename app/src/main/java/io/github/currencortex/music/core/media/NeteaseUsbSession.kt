package io.github.currencortex.music.core.media

import java.nio.ByteBuffer
import java.nio.ByteOrder

/** Format limits are tied to the inspected 9.6.15 arm64 ABI, not DAC hardware limits. */
internal data class NeteaseUsbPcm(val rate: Int, val channels: Int, val encoding: Int, val precision: Int = 0) {
    val frameBytes get() = channels * when (encoding) { 2 -> 2; 21 -> 3; else -> 4 }
    val validBits get() = if (precision != 0) precision else when (encoding) { 2 -> 16; 21 -> 24; else -> 32 }
    val wireFormat get() = if (encoding == 21) copy(encoding = 22, precision = 24) else this
    fun validate() {
        check(rate in 8000..192000 && channels in 1..2) { "[USB_PCM] Bộ điều hợp trình điều khiển Nhạc Vân NetEase hỗ trợ đơn âm hoặc âm thanh nổi 8–192 kHz" }
        check(encoding == 2 || encoding == 21 || encoding == 22) {
            "[USB_PCM] Đầu ra USB của Nhạc Vân NetEase chỉ nhận PCM số nguyên 16/24/32-bit; định dạng dấu phẩy động sẽ không được coi là số nguyên"
        }
        check(precision == 0 || (encoding == 22 && precision == 24)) { "[USB_PCM] Số bit hợp lệ của vùng chứa không hợp lệ" }
    }
}

internal interface NeteaseUsbDriver {
    fun open(fd: Int, pcm: NeteaseUsbPcm): Long
    fun write(handle: Long, buffer: ByteBuffer): Int
    fun play(handle: Long, playing: Boolean)
    fun position(handle: Long): Long
    fun verify(handle: Long)
    fun close(handle: Long)
}

/** Injectable lifecycle; only an authorized connection's FD may enter the native engine. */
internal class NeteaseUsbSession private constructor(
    private val driver: NeteaseUsbDriver,
    private val connection: AutoCloseable,
    private val connected: () -> Boolean,
    private val handle: Long,
    val pcm: NeteaseUsbPcm,
) : AutoCloseable {
    private var closed = false
    private val scratch by lazy { ByteBuffer.allocateDirect(8192).order(ByteOrder.LITTLE_ENDIAN) }
    @Synchronized fun verify() {
        check(!closed) { "[USB_CLOSED] Đầu ra USB đã được giải phóng" }
        try {
            check(connected()) { "[USB_DISCONNECTED] USB DAC đã ngắt kết nối hoặc quyền đã bị thu hồi" }
            driver.verify(handle)
        } catch (e: Throwable) { runCatching { close() }.exceptionOrNull()?.let(e::addSuppressed); throw e }
    }
    @Synchronized fun write(buffer: ByteBuffer, volume: Float = 1f): Int = guarded {
        check(buffer.isDirect && buffer.remaining() % pcm.frameBytes == 0) { "[USB_BUFFER] Bộ đệm PCM không được căn chỉnh theo khung" }
        val before = buffer.position()
        val gain = usbVolumeGain(volume).toDouble()
        val wire = if (pcm.encoding != 21 && gain == 1.0) buffer else scratch.apply {
            clear()
            val count = minOf(buffer.remaining() / pcm.frameBytes, capacity() / pcm.wireFormat.frameBytes) * pcm.channels
            val sampleBytes = pcm.frameBytes / pcm.channels
            repeat(count) { sampleIndex ->
                val offset = before + sampleIndex * sampleBytes
                // Decode signed little-endian PCM independently of ByteBuffer.order().
                var sample = buffer.get(offset + sampleBytes - 1).toInt()
                for (part in sampleBytes - 2 downTo 0)
                    sample = (sample shl 8) or (buffer.get(offset + part).toInt() and 255)
                // Packed 24 stays left-aligned in the 32-bit USB container. Double
                // preserves all 32 integer bits at unity; never mutate the input,
                // since backpressure can retry the same samples at a new volume.
                if (pcm.encoding == 21) sample = sample shl 8
                val scaled = (sample.toDouble() * gain).toInt()
                if (pcm.encoding == 2) putShort(scaled.toShort()) else putInt(scaled)
            }
            flip()
        }
        val wirePosition = wire.position()
        val bytes = driver.write(handle, wire)
        check(bytes in 0..wire.remaining() && bytes % pcm.wireFormat.frameBytes == 0 && wire.position() == wirePosition && buffer.position() == before) {
            "[USB_WRITE] Trình điều khiển trả về số byte đã nhận không hợp lệ"
        }
        val consumed = bytes / pcm.wireFormat.frameBytes * pcm.frameBytes
        buffer.position(before + consumed)
        consumed
    }
    @Synchronized fun play(playing: Boolean) = guarded { driver.play(handle, playing) }
    @Synchronized fun position() = guarded { driver.position(handle).coerceAtLeast(0) }
    private inline fun <T> guarded(action: () -> T): T {
        verify()
        try { return action() } catch (e: Throwable) { runCatching { close() }.exceptionOrNull()?.let(e::addSuppressed); throw e }
    }
    @Synchronized override fun close() {
        if (closed) return
        closed = true
        try { driver.close(handle) } finally { connection.close() }
    }
    companion object {
        fun open(pcm: NeteaseUsbPcm, permitted: Boolean, fd: Int, connection: AutoCloseable,
            connected: () -> Boolean, driver: NeteaseUsbDriver): NeteaseUsbSession {
            var handle = 0L
            try {
                pcm.validate()
                check(permitted) { "[USB_PERMISSION] Chưa được cấp quyền truy cập USB DAC đã chọn" }
                check(connected()) { "[USB_DISCONNECTED] USB DAC đã chọn đã ngắt kết nối" }
                check(fd >= 0) { "[USB_OPEN] Không thể mở thiết bị USB" }
                handle = driver.open(fd, pcm.wireFormat)
                check(handle != 0L) { "[USB_OPEN] Khởi tạo đầu ra USB gốc thất bại" }
                return NeteaseUsbSession(driver, connection, connected, handle, pcm)
            } catch (e: Throwable) {
                runCatching { if (handle != 0L) driver.close(handle) }.exceptionOrNull()?.let(e::addSuppressed)
                runCatching { connection.close() }.exceptionOrNull()?.let(e::addSuppressed)
                throw e
            }
        }
    }
}
