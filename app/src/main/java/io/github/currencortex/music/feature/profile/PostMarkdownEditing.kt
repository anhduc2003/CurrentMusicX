package io.github.currencortex.music.feature.profile

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue

internal enum class PostMarkdownTool(val label: String, val glyph: String) {
    HEADING("Tiêu đề", "H"), BOLD("In đậm", "B"), ITALIC("In nghiêng", "I"), CODE("Mã", "</>"),
    QUOTE("Trích dẫn", "❞"), BULLET("Danh sách không thứ tự", "• ≡"), NUMBERED("Danh sách có thứ tự", "1."), LINK("Liên kết", "↗"), RULE("Đường phân cách", "—")
}

/** Operate on the selection, not the end of the draft; a toolbar edit ends IME composition. */
internal fun applyPostMarkdown(value: TextFieldValue, tool: PostMarkdownTool): TextFieldValue {
    val text = value.text
    val start = value.selection.min.coerceIn(0, text.length)
    val end = value.selection.max.coerceIn(start, text.length)
    fun wrap(prefix: String, suffix: String, placeholder: String): TextFieldValue {
        val body = text.substring(start, end).ifEmpty { placeholder }
        return TextFieldValue(text.replaceRange(start, end, prefix + body + suffix),
            TextRange(start + prefix.length, start + prefix.length + body.length))
    }
    return when (tool) {
        PostMarkdownTool.BOLD -> wrap("**", "**", "Văn bản in đậm")
        PostMarkdownTool.ITALIC -> wrap("*", "*", "Văn bản in nghiêng")
        PostMarkdownTool.CODE -> if ('\n' in text.substring(start, end)) wrap("```\n", "\n```", "Mã") else wrap("`", "`", "Mã")
        PostMarkdownTool.LINK -> {
            val label = text.substring(start, end).ifEmpty { "Văn bản liên kết" }
            val urlStart = start + label.length + 3
            TextFieldValue(text.replaceRange(start, end, "[$label](https://)"), TextRange(urlStart, urlStart + 8))
        }
        PostMarkdownTool.RULE -> {
            val insertion = (if (start > 0) "\n\n" else "") + "---\n\n"
            TextFieldValue(text.replaceRange(start, end, insertion), TextRange(start + insertion.length))
        }
        else -> {
            val lineStart = text.lastIndexOf('\n', start - 1).let { if (start == 0) 0 else it + 1 }
            val last = if (end > start && text[end - 1] == '\n') end - 1 else end
            val lineEnd = text.indexOf('\n', last).let { if (it < 0) text.length else it }
            val edited = text.substring(lineStart, lineEnd).split('\n').mapIndexed { index, line ->
                val prefix = when (tool) {
                    PostMarkdownTool.HEADING -> "# "
                    PostMarkdownTool.QUOTE -> "> "
                    PostMarkdownTool.BULLET -> "- "
                    else -> "${index + 1}. "
                }
                prefix + line
            }.joinToString("\n")
            TextFieldValue(text.replaceRange(lineStart, lineEnd, edited), TextRange(lineStart, lineStart + edited.length))
        }
    }
}
