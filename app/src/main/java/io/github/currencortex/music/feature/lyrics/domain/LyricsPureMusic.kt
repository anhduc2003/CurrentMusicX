// Adapted from Folia f96c65b27f7ca351e8db781301cedfa0a52252e2,
// src/utils/lyrics/pureMusic.ts (AGPL-3.0); original contributors retain copyright.
package io.github.currencortex.music.feature.lyrics.domain

import io.github.currencortex.music.feature.lyrics.model.LyricsDocument

/** An explicit whole-line notice is evidence; missing lyrics or a passing mention are not. */
internal fun LyricsDocument.isExplicitPureMusic(): Boolean {
    if (metadata.values["pureMusic"] == listOf("true")) return true
val notice = Regex("^(?:[này/bản này](?:đầu)?(?:bài hát|bản nhạc|tác phẩm|nhạc phẩm|bản nhạc)?[là/đúng]?)?(?:(?:không có|không)(?:lời|lời bài hát)có)?nhạc không lờitrân trọng?mời[bạn]?thưởng thức$"）)val credit = Regex("^(Lời bài hát|Sáng tác|Phối khí|Sản xuất|Thu âm|Hòa âm|Xử lý master|Guitar|Bass|Bè|OP|SP)\\s*[:：]", RegexOption.IGNORE_CASE)    var found = false
    for (line in lines) {
        val text = line.text.trim()
        if (text.isEmpty()) continue
        val normalized = FoliaLyricsMatchPolicy.normalize(text).replace(" ", "")
        if (notice.matches(normalized)) { found = true; continue }
        if (normalized.isEmpty() || credit.containsMatchIn(text) || text.contains(Regex("[:：]|\\s[-–—]\\s"))) continue
        return false
    }
    return found
}
