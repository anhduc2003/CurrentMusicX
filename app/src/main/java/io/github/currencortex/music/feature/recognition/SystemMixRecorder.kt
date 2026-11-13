package io.github.currencortex.music.feature.recognition

import android.annotation.SuppressLint
import android.media.audiofx.Visualizer
import android.os.SystemClock
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.ceil

/** Public system-mix waveform capture. Never changes routing or another player's capture policy. */
class SystemMixRecorder : RecognitionRecorder {
    override suspend fun record(stop: AtomicBoolean, progress: (Int, Float) -> Unit): ShortArray =
        recordStreaming(stop, progress) { }

    @SuppressLint("MissingPermission") // The activity obtains RECORD_AUDIO; MODIFY_AUDIO_SETTINGS is declared.
    override suspend fun recordStreaming(stop: AtomicBoolean, progress: (Int, Float) -> Unit,
        onWindow: (ShortArray) -> Unit): ShortArray = withContext(Dispatchers.IO) {
        val deadline = SystemClock.elapsedRealtime() + MAX_WALL_MILLIS
        var visualizer: Visualizer? = null
        var enabledByUs = false
        var waveform: ByteArray? = null
        var accumulator: SystemMixPcmAccumulator? = null
        try {
            val source = Visualizer(0)
            visualizer = source
            val alreadyEnabled = source.enabled
            val captureSize = if (alreadyEnabled) {
                source.captureSize.also { check(it > 0) }
            } else {
                val range = Visualizer.getCaptureSizeRange()
                check(range.size >= 2)
                Integer.highestOneBit(minOf(1024, range[1])).also {
                    check(it >= range[0] && it > 0)
                    check(source.setCaptureSize(it) == Visualizer.SUCCESS)
                    check(source.setScalingMode(Visualizer.SCALING_MODE_NORMALIZED) == Visualizer.SUCCESS)
                }
            }
            val rateHz = source.samplingRate / 1000 // Android reports millihertz.
            check(rateHz in 8000..384_000)
            val samples = SystemMixPcmAccumulator(rateHz)
            val windows = RecognitionWindows()
            accumulator = samples
            val frame = ByteArray(captureSize)
            waveform = frame
            if (!alreadyEnabled) {
                enabledByUs = source.setEnabled(true) == Visualizer.SUCCESS
                check(enabledByUs)
            }
            val pollingMillis = ceil(captureSize * 1000.0 / rateHz / 6).toLong().coerceIn(1, 20)
            val remaining = deadline - SystemClock.elapsedRealtime()
            if (remaining > 0) withTimeoutOrNull(remaining) {
                while (!stop.get() && !samples.full) {
                    currentCoroutineContext().ensureActive()
                    check(source.getWaveForm(frame) == Visualizer.SUCCESS)
                    if (samples.appendSnapshot(frame) > 0) {
                        progress(samples.size / 8000, samples.lastLevel)
                        windows.emitAvailable(samples.size, samples::copyPcm, onWindow)
                    }
                    if (!stop.get() && !samples.full) delay(pollingMillis)
                }
            }
            check(samples.hasSignal) { NO_SIGNAL }
            check(samples.size >= 16_000) { TOO_SHORT }
            samples.copyPcm()
        } catch (cancelled: CancellationException) {
            throw cancelled
        } catch (failure: Exception) {
            if (failure is IllegalStateException && (failure.message == NO_SIGNAL || failure.message == TOO_SHORT)) throw failure
            throw IllegalStateException(UNSUPPORTED, failure)
        } finally {
            if (enabledByUs) runCatching { visualizer?.setEnabled(false) }
            runCatching { visualizer?.release() }
            waveform?.fill(0)
            accumulator?.clear()
        }
    }

    companion object {
        private const val MAX_WALL_MILLIS = 17_000L
        private const val UNSUPPORTED = "Thiết bị hiện tại không thể đọc bản phối âm thanh hệ thống, vui lòng chuyển sang nhận diện bài hát bằng quyền ghi âm hệ thống"
        private const val NO_SIGNAL = "Không đọc được nhạc đang phát trên hệ thống, vui lòng phát bài hát trước; bạn cũng có thể chuyển sang nhận diện bài hát bằng quyền ghi âm hệ thống"
        private const val TOO_SHORT = "Đoạn âm thanh phối trộn hệ thống chưa đủ 2 giây, vui lòng tiếp tục phát nhạc hoặc chuyển sang nhận diện bài hát bằng quyền ghi âm hệ thống"
    }
}
