package io.github.currencortex.music.core.network

enum class SourceAvailabilityStatus(val label: String) {
    UNKNOWN("Chưa kiểm tra"), CHECKING("Đang kiểm tra"), AVAILABLE("Khả dụng"), LIMITED("Bị giới hạn tần suất"), TIMEOUT("Quá thời gian chờ"), UNAVAILABLE("Không khả dụng")
}
