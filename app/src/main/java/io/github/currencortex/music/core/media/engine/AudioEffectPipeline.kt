package io.github.currencortex.music.core.media.engine

import io.github.currencortex.music.data.settings.AudioOutputMode

data class AudioEffectPlan(val bypass: Boolean, val requested: List<String>, val reason: String?)
object AudioEffectPipeline {
    fun plan(mode: AudioOutputMode, effects: List<String>): AudioEffectPlan = if (mode.bypassProcessing)
        AudioEffectPlan(true, effects, "${mode.label} bỏ qua DSP, tái lấy mẫu, thay đổi tốc độ và hiệu ứng tăng/giảm âm lượng dần")
    else AudioEffectPlan(false, effects, null)
}
