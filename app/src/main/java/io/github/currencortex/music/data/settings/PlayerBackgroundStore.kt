package io.github.currencortex.music.data.settings

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import java.io.InputStream
import java.util.UUID

/** Own the selected image so later provider revocation/removal cannot break the background. */
class PlayerBackgroundStore(context: Context, private val settings: SettingsRepository, suffix: String = "") {
    private val resolver = context.applicationContext.contentResolver
    private val directory = File(context.filesDir, "player-background$suffix")
    private val mutex = Mutex()
    fun file(name: String): File? = name.takeIf { it.matches(FILE_NAME) }
        ?.let { File(directory, it) }?.takeIf(File::isFile)

    suspend fun import(uri: Uri) = replace { resolver.openInputStream(uri) ?: error("Không thể đọc ảnh, vui lòng chọn lại") }

    internal suspend fun replace(open: () -> InputStream) = mutex.withLock {
        withContext(Dispatchers.IO) {
            check(directory.isDirectory || directory.mkdirs()) { "Không thể lưu ảnh nền" }
            val name = "${UUID.randomUUID()}.image"
            val pending = File(directory, "$name.part")
            val destination = File(directory, name)
            var committed = false
            try {
                open().use { input -> pending.outputStream().use { output ->
                    val buffer = ByteArray(8192)
                    var total = 0L
                    while (true) {
                        currentCoroutineContext().ensureActive()
                        val size = input.read(buffer)
                        if (size < 0) break
                        total += size
                        require(total <= MAX_BYTES) { "Ảnh quá lớn, vui lòng chọn ảnh có dung lượng dưới 20 MB" }
                        output.write(buffer, 0, size)
                    }
                } }
                val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                BitmapFactory.decodeFile(pending.absolutePath, bounds)
                require(bounds.outWidth in 1..32768 && bounds.outHeight in 1..32768 &&
                    bounds.outWidth.toLong() * bounds.outHeight <= 200_000_000) { "Không thể nhận dạng ảnh này, vui lòng chọn lại" }
                check(pending.renameTo(destination)) { "Không thể lưu ảnh nền" }
                currentCoroutineContext().ensureActive()
                // Once the setting is committed, cancellation must not remove its backing file.
                withContext(NonCancellable) {
                    try {
                        val previous = settings.snapshot().playerBackground
                        settings.edit { it.copy(playerBackground = name) }
                        committed = true
                        file(previous)?.delete()
                    } catch (failure: Throwable) {
                        destination.delete()
                        throw failure
                    }
                }
            } finally {
                pending.delete()
                // A cancelled dispatcher return before the commit leaves no orphan file.
                if (!committed) destination.delete()
            }
        }
    }

    suspend fun clear() = mutex.withLock {
        withContext(Dispatchers.IO + NonCancellable) {
            val previous = settings.snapshot().playerBackground
            settings.edit { it.copy(playerBackground = "") }
            file(previous)?.delete()
        }
    }

    companion object {
        private val FILE_NAME = Regex("[0-9a-f-]{36}\\.image")
        private const val MAX_BYTES = 20L * 1024 * 1024
    }
}
