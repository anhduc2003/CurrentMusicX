package io.github.currencortex.music.core.media

import android.content.Context
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.data.effects.OriginalSpeakerScene
import io.github.currencortex.music.data.effects.originalSpeakerId
import io.github.currencortex.music.data.song.Song
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class EffectsAuditionState(val active: Boolean = false, val loading: Boolean = false, val playing: Boolean = false,
    val processed: Boolean = false, val processing: Boolean = false, val scene: OriginalSpeakerScene? = null, val error: String? = null)

/** Temporary, bounded A/B playback; never rewrites the queue or the saved effect selection. */
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class EffectsAuditionController internal constructor(private val context: Context, private val container: AppContainer,
    private val scope: CoroutineScope) : java.io.Closeable {
    private val mutableState = MutableStateFlow(EffectsAuditionState())
    val state = mutableState.asStateFlow()
    @Volatile private var preset: NeteaseOriginalPreset? = null
    private var player: ExoPlayer? = null
    private var job: Job? = null
    private var timer: Job? = null
    private var generation = 0L
    private data class ResumeToken(val song: Song, val timeline: Long, val command: Long)
    private var suspended: ResumeToken? = null
    private var pausingMain = false
    private var requestRevision: Long? = null
    private val accounts = scope.launch {
        container.neteaseSessions.state.collect { account ->
            if (requestRevision?.let { it != account.revision } == true) stop(resume = false)
        }
    }

    private val mainPlayback = scope.launch {
        container.playerController.state.collect { main ->
            val saved = suspended
            if (state.value.active && !pausingMain && (main.showPause || main.mode != PlayerMode.LOCAL ||
                saved?.let { it.song != container.playerController.queue.state.value.current ||
                    it.timeline != main.timelineRevision || it.command != container.playerController.commandRevision } == true)) stop(resume = false)
        }
    }
    private val route = scope.launch {
        OriginalSpeakerEnvironmentObserver(context).state.collect { environment ->
            if (!environment.speaker && state.value.scene != null && state.value.active) {
                stop(resume = false)
                mutableState.value = EffectsAuditionState(error = "Đã rời khỏi loa điện thoại, phát thử qua loa ngoài đã dừng")
            }
        }
    }

    fun speaker(scene: OriginalSpeakerScene) = start(processed = true, scene = scene) {
        check(OriginalSpeakerEnvironmentObserver(context).snapshot().speaker) { "Vui lòng sử dụng loa điện thoại để nghe thử hiệu ứng loa ngoài" }
        check(NeteaseNativeEngine.available()) { "Vui lòng nhập engine hiệu ứng gốc tương thích trước" }
        val owner = container.neteaseSessions.snapshot()
        val permission = container.originalSpeakerRepository.entitlement()
        check(permission.userId == owner.credential.profile?.uid && permission.canUse && permission.expiresAt > System.currentTimeMillis()) {
            "Nghe thử hiệu ứng loa ngoài yêu cầu tài khoản hiện tại có quyền lợi SVIP còn hiệu lực"
        }
        val env = OriginalSpeakerEnvironmentObserver(context).snapshot()
        val id = originalSpeakerId(scene, env.landscape, env.volume)
        val material = container.originalSpeakerRepository.catalog(scene).firstOrNull { it.id == id }
            ?: error("Nhạc Vân NetEase không cung cấp hiệu ứng loa ngoài cho tình huống này")
        val decoded = container.originalSpeakerRepository.download(material)
        check(decoded.type == 1)
        NeteasePresetParameters.fromJson(decoded.bytes.toString(Charsets.UTF_8), soundType = 9)
        container.neteaseSessions.check(owner)
        Audition(SPEAKER_SAMPLES.getValue(scene), NeteaseOriginalPreset(id, material.name, 1, decoded.bytes),
            deadline = permission.expiresAt)
    }

    fun master(song: Song?, processed: Boolean, selected: Set<String>) = start(processed, null) {
        val current = requireNotNull(song) { "Vui lòng chọn một bài hát Nhạc Vân NetEase trước" }
        check(current.musicSource == io.github.currencortex.music.data.song.MusicSource.NETEASE && !current.video) { "Vui lòng chọn bài hát Nhạc Vân NetEase để nghe thử" }
        val owner = container.neteaseSessions.snapshot()
        var deadline = Long.MAX_VALUE
        val effect = if (processed) {
            check(NeteaseNativeEngine.available()) { "Vui lòng nhập engine hiệu ứng gốc tương thích trước" }
            val permission = container.originalAiMasterRepository.entitlement()
            check(permission.userId == owner.credential.profile?.uid && permission.canUse && permission.expiresAt > System.currentTimeMillis()) {
                "Nghe thử AI Master Tuning yêu cầu tài khoản hiện tại có quyền lợi SVIP còn hiệu lực"
            }
            deadline = permission.expiresAt
            val material = container.originalAiMasterRepository.prepare(current.id)
            withContext(Dispatchers.Default) {
                OriginalAiMasterCodec(context.assets.open("netease-ai-master-codec.js").use { it.readBytes() })
                    .compose(material.base, material.diy, material.baseKey, material.song, selected).preset()
            }
        } else null
        val audio = container.audioSources.resolve(container.audioRequest(current, container.musicSettings.state.value.quality))
        container.neteaseSessions.check(owner)
        Audition(audio.source.url, effect, audio.key, container.playerController.state.value.positionMs, deadline)
    }

    private data class Audition(val url: String, val preset: NeteaseOriginalPreset?, val cacheKey: String? = null,
        val position: Long = 0, val deadline: Long = Long.MAX_VALUE)

    private fun start(processed: Boolean, scene: OriginalSpeakerScene?, prepare: suspend () -> Audition) {
        if (container.audioOutput.state.value.mode.bypassProcessing) {
            mutableState.value = EffectsAuditionState(error = "Đầu ra hiện tại đã bỏ qua hiệu ứng, vui lòng chuyển chế độ trong mục đầu ra âm thanh trước")
            return
        }
        if (container.playerController.state.value.mode != PlayerMode.LOCAL) {
            mutableState.value = EffectsAuditionState(error = "Vui lòng thoát tính năng nghe cùng trước khi nghe thử hiệu ứng")
            return
        }
        stopInternal(resume = false, forgetSuspension = false)
        val token = generation
        requestRevision = container.neteaseSessions.state.value.revision
        mutableState.value = EffectsAuditionState(active = true, loading = true, processed = processed, scene = scene)
        val main = container.playerController
        if (suspended == null && main.state.value.showPause) {
            val current = main.queue.state.value.current
            if (current != null) {
                pausingMain = true
                main.pause()
                suspended = ResumeToken(current, main.state.value.timelineRevision, main.commandRevision)
                pausingMain = false
            }
        }
        job = scope.launch {
            try {
                val prepared = prepare()
                ensureActive()
                if (token != generation) return@launch
                check(prepared.deadline > System.currentTimeMillis()) { "Quyền lợi hiệu ứng Nhạc Vân NetEase đã hết hạn" }
                preset = prepared.preset
                val samplePlayer = ExoPlayer.Builder(context, DockRenderersFactory(context, DockAudioLevel(),
                    originalPreset = { preset }, originalProcessing = { _ -> scope.launch {
                        if (token == generation && !state.value.processing) mutableState.value = state.value.copy(processing = true)
                    } }, originalFailure = { _, _ -> scope.launch {
                        if (token == generation) fail("Xử lý nghe thử hiệu ứng gốc thất bại, vui lòng nhập lại engine tương thích")
                    } }, outputSampleRate = { container.audioOutput.state.value.sampleRate },
                    outputSettings = { container.audioOutput.state.value },
                    outputStatus = { container.audioOutput.status.value = it }))
                    .setMediaSourceFactory(DefaultMediaSourceFactory(container.audioCache.dataSourceFactory)).build()
                player = samplePlayer
                samplePlayer.setPreferredAudioDevice(context.getSystemService(android.media.AudioManager::class.java)
                    .preferredOutput(container.audioOutput.state.value.deviceKey))
                samplePlayer.setAudioAttributes(androidx.media3.common.AudioAttributes.Builder().setUsage(C.USAGE_MEDIA)
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC).build(), true)
                samplePlayer.setHandleAudioBecomingNoisy(true)
                samplePlayer.addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        if (token == generation) mutableState.value = state.value.copy(playing = isPlaying)
                    }
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        if (token != generation) return
                        if (playbackState == Player.STATE_READY) mutableState.value = state.value.copy(loading = false)
                        if (playbackState == Player.STATE_ENDED) stop()
                    }
                    override fun onPlayerError(error: PlaybackException) {
                        if (token == generation) fail("Tạm thời không thể phát đoạn nghe thử, vui lòng thử lại sau")
                    }
                })
                samplePlayer.setMediaItem(MediaItem.Builder().setUri(prepared.url).setCustomCacheKey(prepared.cacheKey).build(), prepared.position)
                samplePlayer.prepare(); samplePlayer.playWhenReady = true
                timer = scope.launch {
                    delay(minOf(30_000L, (prepared.deadline - System.currentTimeMillis()).coerceAtLeast(1)))
                    if (token == generation) stop()
                }
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (failure: Exception) { if (token == generation) fail(failure.message?.take(160) ?: "Nghe thử hiệu ứng thất bại") }
        }
    }

    private fun fail(message: String) { stop(); mutableState.value = EffectsAuditionState(error = message) }
    fun stop(resume: Boolean = true) = stopInternal(resume, forgetSuspension = true)
    private fun stopInternal(resume: Boolean, forgetSuspension: Boolean) {
        generation++; job?.cancel(); job = null; timer?.cancel(); timer = null
        requestRevision = null
        player?.release(); player = null; preset = null
        mutableState.value = EffectsAuditionState()
        if (forgetSuspension) {
            val saved = suspended
            suspended = null
            val main = container.playerController
            if (resume && saved != null && main.state.value.mode == PlayerMode.LOCAL &&
                main.queue.state.value.current == saved.song && !main.state.value.showPause &&
                main.state.value.timelineRevision == saved.timeline && main.commandRevision == saved.command) main.toggle()
        }
    }
    override fun close() { stop(); accounts.cancel(); mainPlayback.cancel(); route.cancel() }

    companion object {
        /** Original outside detail's explicitly published audition excerpts. */
        private val SPEAKER_SAMPLES = mapOf(
            OriginalSpeakerScene.NORMAL to "https://m7.music.126.net/20360106214649/afddfa097fc61c1def3e68a31bcaa8a2/ymusic/54c31ce3cdeef5b575008f951954ba87.flac",
            OriginalSpeakerScene.SHOWER to "https://m7.music.126.net/20360106214836/64cc50051fc36e8e4c9e9bd619625c39/ymusic/0358af4657f023f6ab1fa9901c90e9ef.flac",
            OriginalSpeakerScene.SLEEP to "https://m8.music.126.net/20360106214844/38e6af387e3f26152213de9581ad6191/ymusic/7a902eea337ea07e119d03e170b64eae.flac")
    }
}
