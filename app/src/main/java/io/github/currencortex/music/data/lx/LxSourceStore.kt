package io.github.currencortex.music.data.lx

import android.content.Context
import android.net.Uri
import io.github.currencortex.music.core.media.AudioQuality
import io.github.currencortex.music.core.security.TokenStore
import io.github.currencortex.music.data.song.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import io.github.currencortex.music.core.network.SourceAvailabilityStatus
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

/** Scripts may contain their own keys. Keep the library encrypted and out of Android backups. */
class LxSourceStore(private val context: Context, private val vault: TokenStore, scope: CoroutineScope,
    private val runtime: LxRuntime = LxRuntime(context), private val http: LxHttpClient = LxHttpClient()) : java.io.Closeable {
    val state = MutableStateFlow<List<LxSourceInfo>>(emptyList())
    val health = MutableStateFlow<Map<String, LxSourceHealth>>(emptyMap())
    val resolveTimes = MutableStateFlow<Map<String, Long>>(emptyMap())
    val ready = CompletableDeferred<Unit>()
    private val lock = Mutex()
    private val probeLock = Mutex()
    private val resolveLock = Mutex()
    private val json = Json { ignoreUnknownKeys = true }
    private val serializer = ListSerializer(LxStoredSource.serializer())
    private var entries: List<LxStoredSource> = emptyList()
    init { scope.launch(Dispatchers.IO) {
        try {
            entries = vault.read()?.let { json.decodeFromString(serializer, it) }.orEmpty()
                .filter { it.script.toByteArray().size <= LX_MAX_SCRIPT_BYTES && lxScriptInfo(it.script).id == it.info.id }.take(12)
                .map { LxStoredSource(it.info.copy(capabilities = lxNeteaseCapabilities(it.info.capabilities)), it.script, it.performance) }
            state.value = entries.map { it.info }
            publishTimes()
            ready.complete(Unit)
        } catch (cancelled: CancellationException) { ready.cancel(cancelled); throw cancelled }
        catch (_: Exception) { ready.completeExceptionally(LxSourceException("Đọc danh sách nguồn nhạc Luoxue thất bại, vui lòng nhập lại")) }
    } }
    fun supported() = runtime.supported()
    suspend fun importFile(uri: Uri): LxSourceInfo = withContext(Dispatchers.IO) {
        require(uri.scheme == "content") { "Vui lòng chọn tập lệnh nguồn nhạc trong trình chọn tệp của hệ thống" }
        val script = context.contentResolver.openInputStream(uri)?.use { lxReadBounded(it, LX_MAX_SCRIPT_BYTES).toString(Charsets.UTF_8) }
            ?: throw LxSourceException("Không thể đọc tệp nguồn nhạc")
        importScript(script)
    }
    suspend fun importUrl(value: String): LxSourceInfo = withContext(Dispatchers.IO) {
        val url = value.trim()
        importScript(http.download(url), url)
    }
    suspend fun importScript(script: String, origin: String = ""): LxSourceInfo = withContext(Dispatchers.IO) {
        ready.await()
        lock.withLock {
            val info = lxScriptInfo(script, origin)
            entries.firstOrNull { it.info.id == info.id }?.let { throw LxSourceException("Nguồn nhạc này đã được nhập, bạn có thể chọn để sử dụng ngay") }
            require(entries.size < 12 && entries.sumOf { it.script.toByteArray().size } + script.toByteArray().size <= 8 * LX_MAX_SCRIPT_BYTES) {
                "Đã đạt giới hạn số lượng hoặc tổng dung lượng nguồn nhạc, vui lòng xóa nguồn không sử dụng trước"
            }
            val capabilities = runtime.inspect(info, script)
            val complete = info.copy(capabilities = capabilities)
            save(entries + LxStoredSource(complete, script))
            complete
        }
    }
    suspend fun remove(id: String) {
        ready.await()
        withContext(Dispatchers.IO) { lock.withLock { save(entries.filterNot { it.info.id == id }) } }
        runtime.close()
        health.update { it - id }
    }
    /** Called by the visible settings page; no timer or background polling. */
    suspend fun checkAvailability(id: String) {
        ready.await()
        if (!probeLock.tryLock()) return
        val previous = health.value[id] ?: LxSourceHealth()
        try {
            if (state.value.none { it.id == id } || previous.fresh(android.os.SystemClock.elapsedRealtime())) return
            health.update { it + (id to previous.copy(status = SourceAvailabilityStatus.CHECKING)) }
            val result = try {
                withTimeout(60_000) {
                    // Fixed NetEase test track. Never changes the selected source, queue or playback.
val test = Song(2135426631, "5:20AM", "Đao Tương", "5:20AM", durationMs = 142000)                    val audio = source(test, AudioQuality.STANDARD, id)
                    val status = http.probeAudio(audio.url)
                    LxSourceHealth(status, android.os.SystemClock.elapsedRealtime(), when (status) {
                        SourceAvailabilityStatus.AVAILABLE -> "Đã phân tích và đọc được dữ liệu âm thanh"
                        SourceAvailabilityStatus.LIMITED -> "Địa chỉ âm thanh trả về phản hồi giới hạn tần suất"
                        SourceAvailabilityStatus.TIMEOUT -> "Địa chỉ âm thanh phản hồi quá thời gian chờ"
                        else -> "Địa chỉ âm thanh trả về không khả dụng hoặc không phải dữ liệu âm thanh"
                    })
                }
            } catch (_: TimeoutCancellationException) {
                currentCoroutineContext().ensureActive()
                LxSourceHealth(SourceAvailabilityStatus.TIMEOUT, android.os.SystemClock.elapsedRealtime(), "Kiểm tra quá thời gian chờ")
            } catch (cancelled: CancellationException) { throw cancelled }
            catch (_: java.net.SocketTimeoutException) {
                LxSourceHealth(SourceAvailabilityStatus.TIMEOUT, android.os.SystemClock.elapsedRealtime(), "Phản hồi mạng quá thời gian chờ")
            } catch (error: LxSourceException) {
                val timedOut = error.message?.contains("quá thời gian chờ") == true
                LxSourceHealth(if (timedOut) SourceAvailabilityStatus.TIMEOUT else SourceAvailabilityStatus.UNAVAILABLE,
                    android.os.SystemClock.elapsedRealtime(), if (timedOut) "Phân tích nguồn nhạc quá thời gian chờ" else "Phân tích nguồn nhạc thất bại")
            } catch (_: Exception) {
                LxSourceHealth(SourceAvailabilityStatus.UNAVAILABLE, android.os.SystemClock.elapsedRealtime(), "Phân tích nguồn nhạc hoặc kết nối mạng thất bại")
            }
            if (result.status != SourceAvailabilityStatus.AVAILABLE) recordPerformance(id, null)
            if (state.value.any { it.id == id }) health.update { it + (id to result) }
        } catch (cancelled: CancellationException) {
            health.update { if (state.value.any { entry -> entry.id == id }) it + (id to previous) else it - id }
            throw cancelled
        } finally { probeLock.unlock() }
    }
    suspend fun source(song: Song, quality: AudioQuality, id: String, smartSwitch: Boolean = false): AudioSource {
        ready.await()
        // Exclude time queued behind another playback/probe from the performance measurement.
        return resolveLock.withLock {
            val candidates = lock.withLock {
                if (smartSwitch) lxOrderedSources(entries, id, System.currentTimeMillis())
                else listOf(entries.firstOrNull { it.info.id == id }
                    ?: throw LxSourceException("Vui lòng nhập và chọn nguồn nhạc Luoxue trong mục Nguồn nhạc và mạng trước")
            }
            lxTrySources(candidates) { source ->
                val started = android.os.SystemClock.elapsedRealtime()
                try {
                    val audio = if (smartSwitch) {
                        try { withTimeout(12_000) { resolveSource(song, quality, source) } }
                        catch (_: TimeoutCancellationException) {
                            currentCoroutineContext().ensureActive()
                            throw LxSourceException("Phân tích nguồn nhạc Luoxue quá thời gian chờ, vui lòng thử lại hoặc đổi nguồn nhạc")
                        }
                    } else resolveSource(song, quality, source)
                    recordPerformance(source.info.id, android.os.SystemClock.elapsedRealtime() - started)
                    audio
                } catch (cancelled: CancellationException) { throw cancelled }
                catch (error: LxSourceException) {
                    recordPerformance(source.info.id, null)
                    throw error
                }
            }
        }
    }
    private suspend fun resolveSource(song: Song, quality: AudioQuality, source: LxStoredSource): AudioSource {
        val (request, url) = lxResolveTrack(song, quality, source.info.capabilities) { data ->
            runtime.resolve(source.info, source.script, data)
        }
        val level = when (request.quality) { "flac24bit" -> "hires"; "flac" -> "lossless"; "320k" -> "exhigh"; else -> "standard" }
        return AudioSource(url, level, 0, 0, if (request.quality.startsWith("flac")) "flac" else "mp3", validForSeconds = 30, source = "lx:${source.info.id}")
    }
    private suspend fun recordPerformance(id: String, elapsedMs: Long?) = withContext(Dispatchers.IO) {
        lock.withLock {
            val source = entries.firstOrNull { it.info.id == id } ?: return@withLock
            val now = System.currentTimeMillis()
            val performance = if (elapsedMs == null) source.performance.copy(failedAtMs = now)
                else LxSourcePerformance(elapsedMs.coerceAtLeast(0), now)
            val updated = entries.map { if (it.info.id == id) LxStoredSource(it.info, it.script, performance) else it }
            // A statistics write failure must not discard a usable playback URL.
            try { vault.write(json.encodeToString(serializer, updated)) }
            catch (cancelled: CancellationException) { throw cancelled }
            catch (_: Exception) { }
            entries = updated
            publishTimes()
        }
    }
    private fun publishTimes() {
        val now = System.currentTimeMillis()
        resolveTimes.value = entries.mapNotNull { source -> source.performance.recentTime(now)?.let { source.info.id to it } }.toMap()
    }
    private fun save(value: List<LxStoredSource>) {
        vault.write(json.encodeToString(serializer, value))
        entries = value; state.value = value.map { it.info }
        publishTimes()
    }
    override fun close() = runtime.close()
}
