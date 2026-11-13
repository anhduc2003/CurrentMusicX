package io.github.currencortex.music.feature.recognition

import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong

enum class RecognitionPhase { IDLE, RECORDING, RECOGNIZING, MATCHED, NO_MATCH, ERROR }
enum class RecognitionAudioSource { SYSTEM, MICROPHONE }
enum class RecognitionStep { CAPTURE, ANALYZE, MATCH }
data class RecognitionState(val phase: RecognitionPhase = RecognitionPhase.IDLE, val seconds: Int = 0,
    val level: Float = 0f, val songs: List<Song> = emptyList(), val message: String? = null,
    val source: RecognitionAudioSource = RecognitionAudioSource.MICROPHONE,
    val step: RecognitionStep = RecognitionStep.CAPTURE, val round: Int = 0) {
    val busy get() = phase == RecognitionPhase.RECORDING || phase == RecognitionPhase.RECOGNIZING
}

fun interface RecognitionRecorder {
    suspend fun record(stop: AtomicBoolean, progress: (Int, Float) -> Unit): ShortArray

    /** Each window transfers ownership of an independent cumulative 8 kHz PCM copy. */
    suspend fun recordStreaming(stop: AtomicBoolean, progress: (Int, Float) -> Unit,
        onWindow: (ShortArray) -> Unit): ShortArray = record(stop, progress)
}

/** Continuous capture, fingerprint matching and the optional lyric query share one attempt. */
class RecognitionController(private val scope: CoroutineScope, private val recorder: RecognitionRecorder,
    private val fingerprint: suspend (ShortArray) -> String,
    private val recognize: suspend (String, Double) -> List<Song>,
    private val source: RecognitionAudioSource = RecognitionAudioSource.MICROPHONE,
    private val recognizeInSession: suspend (String, Double, String, Int) -> List<Song> = { fp, seconds, _, _ -> recognize(fp, seconds) },
    private val recognizeLyrics: (suspend (ShortArray, String) -> List<Song>)? = null) {
    private val mutable = MutableStateFlow(RecognitionState())
    val state = mutable.asStateFlow()
    private var work: Job? = null
    private var stop = AtomicBoolean(false)
    private val epoch = AtomicLong(0)

    fun start() {
        if (state.value.busy) return
        work?.cancel()
        val attempt = epoch.incrementAndGet()
        val session = UUID.randomUUID().toString()
        stop = AtomicBoolean(false)
        val requestStop = stop
        mutable.value = RecognitionState(RecognitionPhase.RECORDING, source = source)
        fun publish(transform: (RecognitionState) -> RecognitionState) {
            mutable.update { if (epoch.get() == attempt) transform(it) else it }
        }
        work = scope.launch {
            try {
                withTimeout(42_000) { supervisorScope {
                    // Slow HTTP must never block recording or accumulate obsolete audio copies.
                    val windows = Channel<ShortArray>(Channel.CONFLATED, onUndeliveredElement = { it.fill(0) })
                    val lyricWindows = Channel<ShortArray>(1, onUndeliveredElement = { it.fill(0) })
                    val results = Channel<BranchResult>(2)
                    var latestCaptureSize = -1
                    var lastAfpOffered = -1
                    var lyricsOffered = false
                    fun offer(samples: ShortArray, final: Boolean = false) {
                        if (samples.size < latestCaptureSize) { samples.fill(0); return }
                        latestCaptureSize = samples.size
                        if (recognizeLyrics != null && !lyricsOffered && samples.size in 80_000..120_000) {
                            lyricsOffered = true
                            val lyricPcm = if (samples.size == 80_000) samples else samples.copyOf(80_000)
                            if (lyricWindows.trySend(lyricPcm).isFailure) lyricPcm.fill(0)
                            if (samples.size == 80_000) return
                        }
                        // The ten-second milestone belongs to the auxiliary lyric query, not AFP.
                        // Preserve a manually finished ten-second AFP clip when no lyric matcher
                        // exists, while never adding an AFP request for the streaming milestone.
                        if (samples.size == 80_000 && (recognizeLyrics != null || !final)) { samples.fill(0); return }
                        if (samples.size <= lastAfpOffered) { samples.fill(0); return }
                        lastAfpOffered = samples.size
                        if (windows.trySend(samples).isFailure) samples.fill(0)
                    }
                    fun closeWindows(cause: Throwable? = null) {
                        windows.close(cause)
                        lyricWindows.close(cause)
                    }
                    fun validate(samples: ShortArray) {
                        check(samples.size >= 16_000) { "Đoạn âm thanh quá ngắn, vui lòng nghe ít nhất 2 giây rồi nhận dạng" }
                        check(samples.size <= 120_000) { "Đoạn âm thanh nhận dạng quá dài, vui lòng ghi âm lại" }
                        check(samples.any { it != 0.toShort() }) {
                            if (source == RecognitionAudioSource.SYSTEM) "Không thu được âm thanh hệ thống, vui lòng phát nhạc hoặc đổi cách cấp quyền ghi âm"
                            else "Không thu được âm thanh từ micrô, vui lòng kiểm tra công tắc quyền riêng tư của micrô, ứng dụng đang sử dụng micrô hoặc thử đặt micrô gần nguồn âm hơn"
                        }
                    }
                    val capture = launch {
                        try {
                            val final = withTimeout(18_000) {
                                recorder.recordStreaming(requestStop, { seconds, level ->
                                    publish { it.copy(seconds = seconds, level = level) }
                                }, { offer(it) })
                            }
                            offer(final, final = true)
                            publish { it.copy(phase = RecognitionPhase.RECOGNIZING, level = 0f) }
                            closeWindows()
                        } catch (timeout: TimeoutCancellationException) {
                            closeWindows(IllegalStateException("Hết thời gian chờ thu âm thanh, vui lòng đổi cách thu", timeout))
                        } catch (failure: Throwable) {
                            closeWindows(failure)
                        }
                    }
                    val fingerprintWorker = launch {
                        var result = BranchResult()
                        var round = 0
                        try {
                            for (samples in windows) {
                                var analyzing = true
                                try {
                                    validate(samples)
                                    publish { it.copy(step = RecognitionStep.ANALYZE) }
                                    val encoded = withTimeout(10_000) { fingerprint(samples) }
                                    analyzing = false
                                    round++
                                    publish { it.copy(step = RecognitionStep.MATCH, round = round) }
                                    val songs = withTimeout(15_000) {
                                        recognizeInSession(encoded, samples.size / 8000.0, session, round)
                                    }
                                    ensureActive()
                                    result = BranchResult(songs = songs)
                                    if (songs.isNotEmpty()) break
                                } catch (timeout: TimeoutCancellationException) {
                                    currentCoroutineContext().ensureActive()
                                    result = BranchResult(failure = IllegalStateException(if (analyzing)
                                        "Hết thời gian chờ xử lý âm thanh, vui lòng thử lại" else "Hết thời gian chờ nhận dạng với Nhạc Vân NetEase, vui lòng kiểm tra mạng rồi thử lại", timeout))
                                } catch (cancelled: CancellationException) { throw cancelled }
                                catch (failure: Exception) { result = BranchResult(failure = failure) }
                                finally { samples.fill(0) }
                            }
                        } catch (cancelled: CancellationException) { throw cancelled }
                        catch (failure: Exception) { result = BranchResult(failure = failure) }
                        results.send(result)
                    }
                    val lyricWorker = recognizeLyrics?.let { matchLyrics -> launch {
                        var result = BranchResult()
                        try {
                            val samples = lyricWindows.receive()
                            try {
                                validate(samples)
                                publish { it.copy(step = RecognitionStep.MATCH) }
                                val songs = withTimeout(20_000) { matchLyrics(samples, session) }
                                ensureActive()
                                result = BranchResult(songs = songs)
                            } catch (timeout: TimeoutCancellationException) {
                                currentCoroutineContext().ensureActive()
                                result = BranchResult(failure = IllegalStateException("Hết thời gian chờ ghép lời bài hát hỗ trợ, vui lòng kiểm tra mạng rồi thử lại", timeout))
                            } finally { samples.fill(0) }
                        } catch (cancelled: CancellationException) { throw cancelled }
                        catch (closed: kotlinx.coroutines.channels.ClosedReceiveChannelException) {
                            // Capture can end before ten seconds; there is no auxiliary query then.
                        } catch (failure: Exception) { result = BranchResult(failure = failure) }
                        results.send(result)
                    } }
                    val jobs = listOfNotNull(capture, fingerprintWorker, lyricWorker)
                    suspend fun stopWorkers() {
                        requestStop.set(true)
                        windows.cancel()
                        lyricWindows.cancel()
                        jobs.forEach { it.cancel() }
                        withContext(NonCancellable) { jobs.joinAll() }
                    }
                    try {
                        val completed = mutableListOf<BranchResult>()
                        repeat(if (lyricWorker == null) 1 else 2) {
                            val result = results.receive()
                            if (result.songs.isNotEmpty()) {
                                stopWorkers()
                                publish { it.copy(phase = RecognitionPhase.MATCHED, songs = result.songs, message = null, level = 0f) }
                                return@supervisorScope
                            }
                            completed += result
                        }
                        completed.firstNotNullOfOrNull { it.failure }?.let { throw it }
                        publish { it.copy(phase = RecognitionPhase.NO_MATCH, level = 0f,
                            message = if (source == RecognitionAudioSource.SYSTEM) "Chưa nhận dạng được, vui lòng phát bản nhạc gốc hoặc đổi cách thu âm"
                            else "Chưa nhận dạng được, hãy thử lại khi đặt gần nguồn âm hơn") }
                    } finally {
                        stopWorkers()
                        results.cancel()
                    }
                } }
            } catch (timeout: TimeoutCancellationException) {
                currentCoroutineContext().ensureActive()
                publish { it.copy(phase = RecognitionPhase.ERROR, level = 0f, message = "Hết thời gian chờ nhận dạng bài hát, vui lòng kiểm tra mạng hoặc đổi cách thu âm rồi thử lại") }
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (failure: Exception) {
                publish { it.copy(phase = RecognitionPhase.ERROR, level = 0f,
                    message = if (failure is SecurityException) "Quyền ghi âm đã bị tắt, vui lòng cấp quyền lại"
                    else failure.message?.take(180) ?: "Nhận dạng bài hát thất bại, vui lòng thử lại") }
            }
        }
    }

    /** Flush the current recording into the same recognition session. */
    fun finishRecording() { stop.set(true) }
    fun cancel() {
        epoch.incrementAndGet(); stop.set(true); work?.cancel(); work = null
        if (state.value.busy) mutable.value = RecognitionState()
    }
    fun reset() { cancel(); mutable.value = RecognitionState() }

    private data class BranchResult(val songs: List<Song> = emptyList(), val failure: Exception? = null)
}
