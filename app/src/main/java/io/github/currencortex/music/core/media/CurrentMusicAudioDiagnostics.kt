package io.github.currencortex.music.core.media

import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.os.Build
import android.util.Log
import androidx.media3.common.Format
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Technical audio fields only: no media URI, track title, USB address, PCM samples or account data. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
internal object CurrentMusicAudioDiagnostics {
    const val TAG = "CurrentMusicAudio"
    private val lines = ArrayDeque<String>()
    private var size = 0
    private val mutable = MutableStateFlow("")
    val text = mutable.asStateFlow()
    private fun header() = "CurrentMusicAudio · Android ${Build.VERSION.RELEASE} / API ${Build.VERSION.SDK_INT}"
    @Synchronized fun latestAttempt(): String = AudioDiagnosticExcerpt.latest(header(), lines.toList())
    @Synchronized fun fullSnapshot(): String = (listOf(header()) + lines).joinToString("\n")
    @Synchronized fun record(message: String) {
        val safe = message.replace(Regex("[\\r\\n]"), " ")
        val line = "${android.os.SystemClock.elapsedRealtime()} $safe"
        Log.i(TAG, safe)
        lines.addLast(line); size += line.length
        while (size > 262_144 && lines.size > 1) size -= lines.removeFirst().length
        mutable.value = fullSnapshot()
    }
    fun format(stage: String, format: Format) = record("$stage rate=${format.sampleRate} encoding=${format.pcmEncoding} " +
        "(${bitPerfectEncodingLabel(format.pcmEncoding)}) channels=${format.channelCount} mask=0x${format.channelMask.toString(16)} " +
        "mime=${format.sampleMimeType?.take(80)}")

    fun device(device: AudioDeviceInfo) = BitPerfectDevice(device.id, device.outputKey(), device.productName.toString(),
        device.type, device.sampleRates.toList(), device.encodings.toList(), device.channelMasks.toList(), device.channelIndexMasks.toList())

    /** Read-only capability snapshot, also callable before any playback has started. */
    fun capabilities(manager: AudioManager, device: AudioDeviceInfo): String {
        record("device ${device(device).describe()}")
        if (Build.VERSION.SDK_INT < 34) return "Android API ${Build.VERSION.SDK_INT} không cung cấp Bit-perfect MixerAttributes"
        return try {
            val all = manager.getSupportedMixerAttributes(device)
            record("supportedMixerAttributes.count=${all.size} deviceId=${device.id}")
            val formats = all.mapIndexed { index, mixer ->
                val f = mixer.format
                val snapshot = BitPerfectMixer(index, BitPerfectPcm(f.sampleRate, f.encoding, f.channelCount, f.channelMask, f.channelIndexMask), mixer.mixerBehavior)
                record("supportedMixer[$index]=${snapshot.describe()}")
                snapshot.describe()
            }
            "${device.productName} · ${all.count { it.mixerBehavior == 1 }} cấu hình Bit-perfect\n" +
                if (formats.isEmpty()) "Android không báo cáo MixerAttributes có thể cấu hình; chưa rõ khả năng phần cứng DAC" else formats.joinToString("\n")
        } catch (e: Exception) {
            record("failure=MIXER_QUERY exception=${e.javaClass.simpleName} deviceId=${device.id}")
            "Truy vấn MixerAttributes của Android thất bại（${e.javaClass.simpleName}）；chưa rõ khả năng phần cứng DAC"
        }
    }
}
