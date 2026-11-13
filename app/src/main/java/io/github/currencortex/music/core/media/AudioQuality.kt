package io.github.currencortex.music.core.media

enum class AudioQuality(val value: String, val label: String) {
    AUTO("auto", "Tự động (cao nhất)"), JYMASTER("jymaster", "Master siêu nét"),
    SKY("sky", "Âm thanh vòm đắm chìm"), VIVID("vivid", "Âm thanh toàn cảnh chất lượng cao"), DOLBY("dolby", "Âm thanh toàn cảnh Dolby"),
    JYEFFECT("jyeffect", "Âm thanh chất lượng cao"), HIRES("hires", "Hi-Res"), LOSSLESS("lossless", "Không mất dữ liệu"),
    EXHIGH("exhigh", "Cực cao"), HIGHER("higher", "Cao hơn"), STANDARD("standard", "Tiêu chuẩn");
    companion object { fun from(value: String) = entries.firstOrNull { it.value == value } ?: AUTO }
}
