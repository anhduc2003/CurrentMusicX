package io.github.currencortex.music.core.media

/** Android PCM encodings remain distinct: packed 24 (21), integer 32 (22), float (4). */
internal data class BitPerfectPcm(val rate: Int, val encoding: Int, val channels: Int,
    val positionMask: Int, val indexMask: Int = 0) {
    fun describe() = "$rate Hz / ${bitPerfectEncodingLabel(encoding)}($encoding) / $channels ch / position=0x${positionMask.toString(16)} index=0x${indexMask.toString(16)}"

    fun accepts(actual: BitPerfectPcm): Boolean {
        if (rate != actual.rate || encoding != actual.encoding || channels != actual.channels || channels <= 0) return false
        if (positionMask == actual.positionMask && indexMask == actual.indexMask) return true
        // Only canonical mono/stereo layouts have an unambiguous identity interleave here.
        // Do not guess multichannel ordering or accept non-contiguous physical USB channels.
        val canonicalPosition = when (channels) { 1 -> 4; 2 -> 12; else -> return false }
        val canonicalIndex = (1 shl channels) - 1
        fun canonical(f: BitPerfectPcm) =
            (f.positionMask == canonicalPosition || (f.positionMask == 0 && f.indexMask == canonicalIndex)) &&
                (f.indexMask == 0 || f.indexMask == canonicalIndex)
        return canonical(this) && canonical(actual)
    }
}

internal fun bitPerfectEncodingLabel(encoding: Int) = when (encoding) {
    3 -> "PCM 8-bit"; 2 -> "PCM 16-bit"; 21 -> "PCM 24-bit packed (3 bytes)"
    22 -> "PCM 32-bit integer (4 bytes)"; 4 -> "PCM float 32-bit"; else -> "unknown PCM"
}

internal data class BitPerfectMixer(val token: Int, val format: BitPerfectPcm, val behavior: Int) {
    fun sameConfiguration(other: BitPerfectMixer?) = other != null && behavior == other.behavior && format.accepts(other.format)
    fun describe() = "${format.describe()} behavior=$behavior${if (behavior == 1) " (BIT_PERFECT)" else " (not BIT_PERFECT)"}"
}
internal data class BitPerfectDevice(val id: Int, val key: String, val label: String, val type: Int,
    val rates: List<Int>, val encodings: List<Int>, val positionMasks: List<Int>, val indexMasks: List<Int>) {
    fun describe() = "id=$id name=${label.replace(Regex("[\\r\\n\\t]"), " ").take(120)} type=$type " +
        "deviceRates=$rates deviceEncodings=$encodings devicePositionMasks=$positionMasks deviceIndexMasks=$indexMasks; hardware capability not established"
}
internal enum class BitPerfectFailureCode {
    ANDROID_VERSION, USB_MISSING, USB_AMBIGUOUS, USAGE, MIXER_QUERY, NO_BIT_PERFECT_MIXER,
    MIXER_RATE, MIXER_ENCODING, MIXER_CHANNELS, REQUEST_REJECTED, PREFERENCE_NOT_RETAINED,
    TRACK_INITIALIZATION, TRACK_FORMAT, DEVICE_PREFERENCE, ROUTE_MISMATCH, ROUTE_UNCONFIRMED,
    DISCONNECTED, MIXER_REVOKED, PLATFORM_ERROR, WRITE_FAILED,
}
internal class BitPerfectFailure(val code: BitPerfectFailureCode, detail: String, cause: Throwable? = null) :
    IllegalStateException("[${code.name}] $detail", cause)

/** Capability and manager boundary, injectable without Android or a physical DAC. */
internal interface BitPerfectPlatform {
    val sdk: Int
    val usage: Int
    fun devices(): List<BitPerfectDevice>
    fun mixers(device: BitPerfectDevice): List<BitPerfectMixer>
    fun setPreferred(device: BitPerfectDevice, mixer: BitPerfectMixer): Boolean
    fun getPreferred(device: BitPerfectDevice): BitPerfectMixer?
    fun clearPreferred(device: BitPerfectDevice): Boolean
    fun createTrack(mixer: BitPerfectMixer): BitPerfectTrack
}
internal interface BitPerfectTrack {
    val initialized: Boolean
    val format: BitPerfectPcm
    val playing: Boolean
    val routedDeviceId: Int?
    fun prefer(device: BitPerfectDevice): Boolean
    fun pauseAndFlush()
    fun release()
}

/** One requested HAL configuration, one track, and fail-closed cleanup. No conversion or mixed fallback. */
internal class BitPerfectSession private constructor(private val platform: BitPerfectPlatform,
    val device: BitPerfectDevice, val mixer: BitPerfectMixer, val track: BitPerfectTrack,
    private val nowMs: () -> Long, private val log: (String) -> Unit, private val report: (String) -> Unit,
    private val confirmedOutput: (BitPerfectPcm) -> Unit = {}) : AutoCloseable {
    private var closed = false
    private var failure: BitPerfectFailure? = null
    private var confirmed = false
    private var waitingSince: Long? = null
    private var lastPolicyCheck = Long.MIN_VALUE

    @Synchronized fun invalidate(code: BitPerfectFailureCode, detail: String) {
        if (closed || failure != null) return
        failure = BitPerfectFailure(code, detail)
        log("failure=${failure!!.message}")
        report("Bit-perfect đã dừng: ${failure!!.message}")
        close()
    }

    @Synchronized fun verify(forcePolicyCheck: Boolean = false) {
        failure?.let { throw it }
        check(!closed) { "Bit-perfect output closed" }
        try {
            val route = track.routedDeviceId
            if (route != null && route != device.id) {
                invalidate(BitPerfectFailureCode.ROUTE_MISMATCH, "id định tuyến âm thanh thực tế=$route không khớp với id USB đã chọn=${device.id}")
                throw failure!!
            }
            val now = nowMs()
            if (forcePolicyCheck || lastPolicyCheck == Long.MIN_VALUE || now - lastPolicyCheck >= 500) {
                lastPolicyCheck = now
                if (platform.devices().none { it.id == device.id }) {
                    invalidate(BitPerfectFailureCode.DISCONNECTED, "Thiết bị đầu ra USB đã bị ngắt kết nối")
                    throw failure!!
                }
                val preferred = platform.getPreferred(device)
                if (!mixer.sameConfiguration(preferred)) {
                    log("preferred=${preferred?.describe() ?: "none"}")
                    invalidate(BitPerfectFailureCode.MIXER_REVOKED, "Hệ thống đã thu hồi hoặc thay đổi cấu hình đầu ra bit-perfect")
                    throw failure!!
                }
            }
            if (!track.playing) { confirmed = false; waitingSince = null; return }
            if (route == null) {
                val since = waitingSince ?: now.also { waitingSince = it; log("playState=PLAYING routedDevice=unknown; awaiting route") }
                if (now - since >= 2000) {
                    invalidate(BitPerfectFailureCode.ROUTE_UNCONFIRMED, "Đã bắt đầu phát nhưng Android không xác nhận được định tuyến âm thanh USB thực tế")
                    throw failure!!
                }
                return
            }
            waitingSince = null
            if (!confirmed) {
                // Re-read the track, not just the requested decoder or mixer format.
                if (!mixer.format.accepts(track.format)) {
                    log("actualTrack=${track.format.describe()}")
                    invalidate(BitPerfectFailureCode.TRACK_FORMAT, "Định dạng thực tế của AudioTrack không khớp với cấu hình bit-perfect được yêu cầu")
                    throw failure!!
                }
                confirmed = true
                confirmedOutput(track.format)
                log("finalState=CONFIRMED_ANDROID_API routedDevice=$route actualTrack=${track.format.describe()}; physical DAC bit transparency not measured")
                report("Bit-perfect · Android đã xác nhận định tuyến USB và cấu hình bit-perfect · ${mixer.format.describe()}")
            }
        } catch (e: BitPerfectFailure) { throw e }
        catch (e: Exception) {
            invalidate(BitPerfectFailureCode.PLATFORM_ERROR, "Xác minh giao diện âm thanh thất bại（${e.javaClass.simpleName}）")
            throw failure ?: e
        }
    }

    @Synchronized override fun close() {
        if (closed) return
        closed = true
        cleanup(platform, device, mixer, track, log)
        log("resources=released finalState=${if (failure == null) "CLOSED" else "FAILED"}")
    }

    companion object {
        fun open(platform: BitPerfectPlatform, decoded: BitPerfectPcm, deviceKey: String,
            nowMs: () -> Long, diagnosticLog: (String) -> Unit, diagnosticReport: (String) -> Unit,
            diagnosticOutput: (BitPerfectPcm) -> Unit = {}): BitPerfectSession {
            // These are observers, not platform operations. Their failure must neither
            // invalidate a verified route nor skip release/clearing mixer ownership.
            val log = safeObserver(diagnosticLog)
            val report = safeObserver(diagnosticReport)
            val confirmedOutput = safeObserver(diagnosticOutput)
            log("androidSdk=${platform.sdk} usage=${platform.usage} decodedPcm=${decoded.describe()}")
            var device: BitPerfectDevice? = null
            var requested: BitPerfectMixer? = null
            var accepted = false
            var track: BitPerfectTrack? = null
            var session: BitPerfectSession? = null
            try {
                if (platform.sdk < 34) throw BitPerfectFailure(BitPerfectFailureCode.ANDROID_VERSION, "Android thấp hơn API 34, không cung cấp giao diện đầu ra bit-perfect này")
                if (platform.usage != 1) throw BitPerfectFailure(BitPerfectFailureCode.USAGE, "Đầu ra bit-perfect yêu cầu USAGE_MEDIA")
                val devices = platform.devices().filter { it.type == 11 || it.type == 22 }
                devices.forEach { log("device ${it.describe()}") }
                if (deviceKey.isEmpty() && devices.size > 1) throw BitPerfectFailure(BitPerfectFailureCode.USB_AMBIGUOUS, "Có nhiều đầu ra USB, hãy chọn DAC mục tiêu")
                device = if (deviceKey.isEmpty()) devices.singleOrNull() else devices.firstOrNull { it.key == deviceKey }
                val selected = device ?: throw BitPerfectFailure(BitPerfectFailureCode.USB_MISSING, "Không tìm thấy DAC USB đã chọn; chưa tạo đầu ra âm thanh")
                val mixers = try { platform.mixers(selected) } catch (e: Exception) {
                    throw BitPerfectFailure(BitPerfectFailureCode.MIXER_QUERY, "Android truy vấn USB MixerAttributes thất bại（${e.javaClass.simpleName}）；chưa rõ khả năng phần cứng của DAC", e)
                }
                log("supportedMixerAttributes.count=${mixers.size}")
                mixers.forEachIndexed { i, value -> log("supportedMixer[$i]=${value.describe()}") }
                val bitPerfect = mixers.filter { it.behavior == 1 }
                requested = bitPerfect.firstOrNull { decoded.accepts(it.format) }
                val mixer = requested ?: throw missingConfiguration(decoded, bitPerfect)
                log("requestedMixer=${mixer.describe()}")
                accepted = platform.setPreferred(selected, mixer)
                log("setPreferredMixerAttributes=$accepted")
                if (!accepted) throw BitPerfectFailure(BitPerfectFailureCode.REQUEST_REJECTED, "Android đã từ chối rõ ràng việc thiết lập cấu hình đầu ra Bit-perfect")
                val retained = platform.getPreferred(selected)
                log("getPreferredMixerAttributes=${retained?.describe() ?: "none"}")
                if (!mixer.sameConfiguration(retained)) throw BitPerfectFailure(BitPerfectFailureCode.PREFERENCE_NOT_RETAINED, "Yêu cầu trả về thành công nhưng Android không giữ lại cấu hình bit-perfect tương ứng")
                track = platform.createTrack(mixer)
                log("audioTrack.initialized=${track.initialized} actualFormat=${track.format.describe()}")
                if (!track.initialized) throw BitPerfectFailure(BitPerfectFailureCode.TRACK_INITIALIZATION, "Khởi tạo AudioTrack thất bại")
                if (!mixer.format.accepts(track.format)) throw BitPerfectFailure(BitPerfectFailureCode.TRACK_FORMAT, "Định dạng PCM thực tế của AudioTrack không khớp với MixerAttributes được yêu cầu")
                val preferred = track.prefer(selected)
                log("audioTrack.setPreferredDevice=$preferred targetId=${selected.id} initialRoute=${track.routedDeviceId ?: "unknown"}")
                if (!preferred) throw BitPerfectFailure(BitPerfectFailureCode.DEVICE_PREFERENCE, "AudioTrack từ chối thiết bị đầu ra USB đã chọn")
                val result = BitPerfectSession(platform, selected, mixer, track, nowMs, log, report, confirmedOutput)
                session = result
                result.verify(forcePolicyCheck = true)
                report("Bit-perfect · Android đã chấp nhận cấu hình bit-perfect, đang chờ định tuyến phát USB thực tế")
                return result
            } catch (e: Exception) {
                if (session != null) session.close() else cleanup(platform, device, if (accepted) requested else null, track, log)
                val failure = e as? BitPerfectFailure ?: BitPerfectFailure(BitPerfectFailureCode.PLATFORM_ERROR,
                    "Lỗi giao diện âm thanh Android（${e.javaClass.simpleName}）；chưa tạo đầu ra bit-perfect khả dụng", e)
                log("finalState=FAILED failure=${failure.message}")
                throw failure
            }
        }

        private fun <T> safeObserver(observer: (T) -> Unit): (T) -> Unit = { value ->
            runCatching { observer(value) }
            Unit
        }

        private fun missingConfiguration(decoded: BitPerfectPcm, supported: List<BitPerfectMixer>): BitPerfectFailure {
            val code = when {
                supported.isEmpty() -> BitPerfectFailureCode.NO_BIT_PERFECT_MIXER
                supported.none { it.format.rate == decoded.rate } -> BitPerfectFailureCode.MIXER_RATE
                supported.none { it.format.rate == decoded.rate && it.format.encoding == decoded.encoding } -> BitPerfectFailureCode.MIXER_ENCODING
                else -> BitPerfectFailureCode.MIXER_CHANNELS
            }
            return BitPerfectFailure(code, "Hệ thống âm thanh Android hiện tại không báo cáo hỗ trợ đầu ra bit-perfect cho định dạng PCM đã giải mã này: ${decoded.describe()}。Chưa rõ khả năng phần cứng của DAC; xem cấu hình được hỗ trợ trong chẩn đoán âm thanh")
        }

        private fun cleanup(platform: BitPerfectPlatform, device: BitPerfectDevice?, mixer: BitPerfectMixer?,
            track: BitPerfectTrack?, log: (String) -> Unit) {
            // Each cleanup step is independent: a release exception must not leak the mixer request.
            runCatching { track?.pauseAndFlush() }.onFailure { log("cleanup.pause=${it.javaClass.simpleName}") }
            runCatching { track?.release() }.onFailure { log("cleanup.release=${it.javaClass.simpleName}") }
            if (device != null && mixer != null) runCatching {
                val current = platform.getPreferred(device)
                if (current == null || mixer.sameConfiguration(current)) log("clearPreferredMixerAttributes=${platform.clearPreferred(device)}")
                else log("cleanup.preferenceChanged; leaving newer request untouched")
            }.onFailure { log("cleanup.clear=${it.javaClass.simpleName}; mixer cleanup unconfirmed") }
        }
    }
}
