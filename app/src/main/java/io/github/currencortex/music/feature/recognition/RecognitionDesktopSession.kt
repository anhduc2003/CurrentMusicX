package io.github.currencortex.music.feature.recognition

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Process-local handoff; no audio, fingerprint or pending recording survives process death. */
object RecognitionDesktopSession {
    private val mutableState = MutableStateFlow(RecognitionState())
    private val mutableAttached = MutableStateFlow(false)
    private val mutableRunning = MutableStateFlow(false)
    val state = mutableState.asStateFlow()
    val attached = mutableAttached.asStateFlow()
    val running = mutableRunning.asStateFlow()
    internal fun publish(state: RecognitionState) { mutableState.value = state }
    internal fun attach(desktop: Boolean = true) { mutableRunning.value = true; mutableAttached.value = desktop }
    internal fun detach() { mutableAttached.value = false; mutableRunning.value = false }
    fun clearResult() { if (!running.value) mutableState.value = RecognitionState() }
}

internal fun recognitionIslandTitle(state: RecognitionState): String = when (state.phase) {
    RecognitionPhase.RECORDING -> "${if (state.source == RecognitionAudioSource.SYSTEM) "Âm thanh hệ thống" else "Đang lắng nghe"} · ${state.seconds} giây"
    RecognitionPhase.RECOGNIZING -> "Đang nhận diện bài hát…"
    RecognitionPhase.MATCHED -> state.songs.firstOrNull()?.name ?: "Đã tìm thấy bài hát này"
    RecognitionPhase.NO_MATCH -> "Chưa nhận diện được bài hát"
    RecognitionPhase.ERROR -> "Không thể hoàn tất nhận diện bài hát"
    RecognitionPhase.IDLE -> "Nghe nhạc nhận diện bài hát"
}

internal fun recognitionIslandSubtitle(state: RecognitionState): String = when (state.phase) {
    RecognitionPhase.RECORDING -> if (state.round == 0) "${if (state.source == RecognitionAudioSource.SYSTEM) "Phát nhạc" else "Đưa lại gần nguồn âm thanh"} · tối đa 15 giây" else "Vừa nghe vừa nhận diện · lần khớp thứ ${state.round}"
    RecognitionPhase.RECOGNIZING -> if (state.step == RecognitionStep.MATCH) "Đang tìm bài hát" else "Đang phân tích âm thanh"
    RecognitionPhase.MATCHED -> state.songs.firstOrNull()?.artists.orEmpty().ifBlank { "Nhấn để xem kết quả nhận diện" }
    else -> state.message ?: "Nhấn để quay lại nghe nhạc nhận diện bài hát"
}
