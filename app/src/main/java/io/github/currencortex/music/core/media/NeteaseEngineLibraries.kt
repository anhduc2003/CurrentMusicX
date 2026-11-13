package io.github.currencortex.music.core.media

import android.content.Context
import android.net.Uri
import android.os.Build
import io.github.currencortex.music.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest
import java.util.zip.ZipFile

/** Bundled, version-bound native libraries, with explicit import retained as a fallback. */
internal object NeteaseEngineLibraries {
    private const val ENGINE_HASH = "3f8e2eec659080cb8e90bbfc672d327a180e4e0b6c051818e38625c5f01fcbd1"
    private const val RUNTIME_HASH = "3e6d529147ffd66bd31fab85c8902b526abd1c06e43554ce64b97519e255e4b3"
    private const val SMART_HASH = "b4b927b0d494e9e726a7eac3ed0a81777eea3c0543a203879c6bf3898ec20e78"
    private const val SMART_ENTRY = "assets/audioeffect/preinstall/smartae/-110#6#81f3b6dfa9b6052500be01120678bbeb"
    private val moodLibraries = linkedMapOf(
        "libMNN.so" to "be7ad29454cfeecfb30399e32c3f600f963e69c7dd914f0001021fc78435249d",
        "libMNN_Express.so" to "e095c40a22d3dbb83894c74b3735db857e5137079109cab25797864921d4de57",
        "libCore_MNN_JNI.so" to "fec5363885343072c9aaf395a8560b217def8600404fa2ab9c940509215e0bef")
    private const val MAX_APK = 400L * 1024 * 1024
    private var context: Context? = null
    @Volatile private var loaded = false
    @Volatile private var moodLoaded = false
    private var attempted = false

    @Synchronized fun configure(value: Context) { context = value.applicationContext }
    private fun digest(bytes: ByteArray) = MessageDigest.getInstance("SHA-256").digest(bytes)
        .joinToString("") { "%02x".format(it.toInt() and 255) }
    private fun directory(context: Context) = context.getDir("netease-original-9530", Context.MODE_PRIVATE)

    @Synchronized fun moodAvailable(): Boolean {
        val owner = context ?: return false
        if (BuildConfig.NETEASE_ORIGINAL_EFFECTS) return "arm64-v8a" in Build.SUPPORTED_ABIS.orEmpty() &&
            moodLibraries.keys.all { File(owner.applicationInfo.nativeLibraryDir, it).isFile }
        return moodLibraries.all { (name, hash) -> verified(File(directory(owner), name), hash) }
    }

    @Synchronized fun moodDefinition(): ByteArray? = runCatching {
        val owner = context ?: return null
        owner.assets.open("netease-original/mood-codec.js").use { bounded(it, 1_048_576) }
    }.getOrNull()

    @Synchronized fun loadMoodLibraries() {
        if (moodLoaded) return
        check(available()) { "Vui lòng nhập engine gốc tương thích của Nhạc Vân NetEase trước" }
        val owner = checkNotNull(context)
        check(moodAvailable()) { "Vui lòng nhập lại gói cài đặt Nhạc Vân NetEase để bổ sung engine mô hình cảm xúc gốc" }
        moodLibraries.keys.forEach {
            if (BuildConfig.NETEASE_ORIGINAL_EFFECTS) System.loadLibrary(it.removePrefix("lib").removeSuffix(".so"))
            else System.load(File(directory(owner), it).absolutePath)
        }
        moodLoaded = true
    }

    @Synchronized fun smartDefinition(): ByteArray? = runCatching {
        val owner = context ?: return null
        val file = File(directory(owner), "smartae.ncae")
        val bytes = if (verified(file, SMART_HASH)) file.readBytes() else {
            if (!BuildConfig.NETEASE_ORIGINAL_EFFECTS) return null
            owner.assets.open("original-effects/smartae.ncae").use { bounded(it, 256 * 1024) }
        }
        require(digest(bytes) == SMART_HASH)
        io.github.currencortex.music.data.effects.NcaeDecoder.decode(bytes).also { require(it.type == 1) }.bytes
    }.getOrNull()

    @Synchronized fun available(): Boolean {
        if (loaded) return true
        if (attempted || "arm64-v8a" !in Build.SUPPORTED_ABIS.orEmpty()) return false
        attempted = true
        try {
            if (BuildConfig.NETEASE_ORIGINAL_EFFECTS) {
                System.loadLibrary("c++_shared")
                System.loadLibrary("neaudioeffects")
                System.loadLibrary("currentmusic_netease_bridge")
            } else {
                val owner = context ?: return false.also { attempted = false }
                val folder = directory(owner)
                val engine = File(folder, "libneaudioeffects.so")
                val runtime = File(folder, "libc++_shared.so")
                if (!verified(engine, ENGINE_HASH) || !verified(runtime, RUNTIME_HASH)) return false
                val helper = owner.assets.open("audiofx/arm64-v8a/libcurrentmusic_netease_bridge.so").use { it.readBytes() }
                val bridge = File(folder, "bridge-${digest(helper)}.so")
                if (!bridge.isFile) writeReadOnly(bridge, helper)
                check(verified(bridge, digest(helper)))
                System.load(runtime.absolutePath)
                System.load(engine.absolutePath)
                System.load(bridge.absolutePath)
            }
            loaded = true
        } catch (_: LinkageError) { loaded = false }
        catch (_: Exception) { loaded = false }
        return loaded
    }

    private fun verified(file: File, expected: String) = file.isFile && file.length() in 1..(16L * 1024 * 1024) &&
        digest(file.readBytes()) == expected

    private fun writeReadOnly(file: File, bytes: ByteArray) {
        val temporary = File(file.parentFile, "${file.name}.importing")
        try {
            temporary.setWritable(true, true)
            temporary.writeBytes(bytes)
            check(temporary.setReadOnly()) { "Không thể bảo vệ tệp thư viện hiệu ứng âm thanh" }
            if (!temporary.renameTo(file)) throw IOException("Không thể lưu engine hiệu ứng âm thanh")
        } finally { temporary.delete() }
    }

    suspend fun installFromApk(context: Context, uri: Uri) = withContext(Dispatchers.IO) {
        require("arm64-v8a" in Build.SUPPORTED_ABIS.orEmpty()) { "Thiết bị hiện tại chưa hỗ trợ engine gốc này" }
        val copy = File.createTempFile("netease-engine-", ".apk", context.cacheDir)
        try {
            val stream = context.contentResolver.openInputStream(uri) ?: throw IOException("Không thể đọc gói cài đặt")
            stream.use { input -> copy.outputStream().use { output ->
                val buffer = ByteArray(64 * 1024); var total = 0L
                while (true) {
                    val count = input.read(buffer); if (count < 0) break
                    total += count; require(total <= MAX_APK) { "Gói cài đặt vượt quá giới hạn kích thước nhập" }
                    output.write(buffer, 0, count)
                }
            } }
            installFile(context, copy)
        } finally { copy.delete() }
    }

    suspend fun installFromInstalled(context: Context) = withContext(Dispatchers.IO) {
        val source = try { context.packageManager.getApplicationInfo("com.netease.cloudmusic", 0).sourceDir }
        catch (_: Exception) { throw IllegalArgumentException("Vui lòng cài đặt Nhạc Vân NetEase tương thích trước hoặc chọn gói cài đặt để nhập") }
        installFile(context, File(source))
    }

    private fun readLibrary(archive: ZipFile, name: String): ByteArray {
        val entry = archive.getEntry("lib/arm64-v8a/$name") ?: throw IllegalArgumentException("Gói cài đặt không chứa engine hiệu ứng âm thanh arm64 tương thích")
        require(entry.size in 1..(16L * 1024 * 1024)) { "Kích thước tệp engine hiệu ứng âm thanh không hợp lệ" }
        return archive.getInputStream(entry).use { bounded(it, 16 * 1024 * 1024) }
    }
    private fun bounded(input: InputStream, maximum: Int): ByteArray {
        val output = java.io.ByteArrayOutputStream(); val scratch = ByteArray(8192)
        while (true) {
            val count = input.read(scratch); if (count < 0) break
            require(output.size().toLong() + count <= maximum) { "Tệp hiệu ứng âm thanh quá lớn" }
            output.write(scratch, 0, count)
        }
        return output.toByteArray()
    }
    @Synchronized internal fun installFile(context: Context, apk: File) {
        configure(context)
        var mood: Map<String, ByteArray> = emptyMap()
        val imported = try {
            ZipFile(apk).use { archive ->
                mood = moodLibraries.mapValues { (name, _) -> readLibrary(archive, name) }
                val smart = archive.getEntry(SMART_ENTRY)?.let { entry ->
                    archive.getInputStream(entry).use { bounded(it, 256 * 1024) }
                } ?: throw IllegalArgumentException("Gói cài đặt thiếu tài nguyên hiệu ứng âm thanh thông minh gốc tương thích")
                Triple(readLibrary(archive, "libneaudioeffects.so"), readLibrary(archive, "libc++_shared.so"), smart)
            }
        } catch (failure: IllegalArgumentException) { throw failure }
        catch (_: Exception) { throw IOException("Không thể đọc gói cài đặt Nhạc Vân NetEase, vui lòng chọn tệp APK đầy đủ") }
        val (engine, runtime, smart) = imported
        require(digest(engine) == ENGINE_HASH && digest(runtime) == RUNTIME_HASH && digest(smart) == SMART_HASH) {
            "Hiện chỉ tương thích với engine arm64 của Nhạc Vân NetEase 9.5.30; phiên bản gói cài đặt này chưa được hỗ trợ"
        }
        require(moodLibraries.all { (name, hash) -> digest(checkNotNull(mood[name])) == hash }) {
            "Engine mô hình cảm xúc gốc của gói cài đặt này chưa được hỗ trợ"
        }
        val folder = directory(context)
        writeReadOnly(File(folder, "smartae.ncae"), smart)
        if (!moodLoaded) mood.forEach { (name, bytes) -> writeReadOnly(File(folder, name), bytes) }
        // Do not replace mapped libraries while an audio thread owns an engine instance.
        if (!loaded) {
            writeReadOnly(File(folder, "libc++_shared.so"), runtime)
            writeReadOnly(File(folder, "libneaudioeffects.so"), engine)
        }
        attempted = false
        check(available()) { "Hệ thống hiện tại không thể tải engine gốc, hiệu ứng âm thanh trên thiết bị đã được giữ lại" }
    }
}
