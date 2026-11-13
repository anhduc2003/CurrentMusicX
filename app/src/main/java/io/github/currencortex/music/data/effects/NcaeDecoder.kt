package io.github.currencortex.music.data.effects

import java.io.ByteArrayOutputStream
import java.util.zip.Inflater

data class NcaeDecoded(val type: Int, val bytes: ByteArray)

/** NCAE container interoperability; does not interpret or change account permissions. */
object NcaeDecoder {
    private const val MAX_BYTES = 2 * 1024 * 1024

    fun decode(container: ByteArray): NcaeDecoded {
        require(container.size in 18..MAX_BYTES) { "Kích thước tệp hiệu ứng âm thanh không được hỗ trợ" }
        require(container.copyOfRange(0, 4).contentEquals(byteArrayOf(78, 67, 65, 69))) { "Định dạng tệp hiệu ứng âm thanh không được hỗ trợ" }
        fun byte(index: Int) = container[index].toInt() and 255
        val length = (0..3).fold(0L) { value, i -> value or (byte(4 + i).toLong() shl (8 * i)) }
        val type = byte(14) or (byte(15) shl 8)
        val keyLength = byte(16)
        require(type == 1 || type == 2) { "Loại tài nguyên hiệu ứng âm thanh này hiện chưa được hỗ trợ" }
        require(keyLength >= 5 && (keyLength + 3) % 4 == 0 && 17L + keyLength + length == container.size.toLong()) {
            "Cấu trúc tệp hiệu ứng âm thanh không hợp lệ"
        }
        val key = (0 until keyLength).filter { it != 4 }.map { byte(17 + it) xor byte(21) }
        val box = IntArray(256) { it }
        var j = 0
        repeat(256) { i ->
            j = (j + box[i] + key[i % key.size]) and 255
            val swap = box[i]; box[i] = box[j]; box[j] = swap
        }
        val compressed = ByteArray(length.toInt()) { i ->
            val index = (i + 1) and 255
            (byte(17 + keyLength + i) xor box[(box[(box[index] + index) and 255] + box[index]) and 255]).toByte()
        }
        val inflater = Inflater(true)
        val output = ByteArrayOutputStream()
        try {
            inflater.setInput(compressed)
            val scratch = ByteArray(8192)
            while (!inflater.finished()) {
                val count = inflater.inflate(scratch)
                require(count > 0 || inflater.finished()) { "Giải mã tệp hiệu ứng âm thanh thất bại" }
                require(output.size() + count <= MAX_BYTES) { "Tài nguyên hiệu ứng âm thanh quá lớn" }
                output.write(scratch, 0, count)
            }
            require(inflater.remaining == 0 && output.size() > 0) { "Nội dung tệp hiệu ứng âm thanh không hợp lệ" }
            return NcaeDecoded(type, output.toByteArray())
        } finally { inflater.end() }
    }
}
