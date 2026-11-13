package io.github.currencortex.music.feature.settings

/** Original 9.5.30 JSK 967 keyword table; labels and videos are from the original RN constant.ts. */
internal data class MoodEmotionPresentation(val label: String, val name: String, val keyword: String) {
    val video get() = "emotion_video_map_${label}_dark_video.mp4"
    val poster get() = "emotion_${label}_dark_poster.png"
}

internal object MoodEffectsPresentation {
    val emotions = listOf(
        MoodEmotionPresentation("default", "Mặc định", "Mỗi tâm trạng đều có tiếng vọng"),
        MoodEmotionPresentation("ecstatic", "Cuồng nhiệt", "Cuồng nhiệt là nhịp điệu disco"),
        MoodEmotionPresentation("passionate", "Nhiệt huyết", "Nhiệt huyết là sóng âm cuộn trào"),
        MoodEmotionPresentation("energetic", "Tràn đầy năng lượng", "Sức sống là ánh sáng hướng lên"),
        MoodEmotionPresentation("joyful", "Vui tươi", "Niềm vui là nhịp trống rộn ràng"),
        MoodEmotionPresentation("sweet", "Ngọt ngào", "Sự ngọt ngào lơ lửng trong sắc hồng"),
        MoodEmotionPresentation("confident", "Tự tin", "Tự tin tỏa sáng"),
        MoodEmotionPresentation("romantic", "Lãng mạn", "Lãng mạn là vì sao đang nở rộ"),
        MoodEmotionPresentation("relaxed", "Thư thái", "Thư thái khiến thời gian tan chảy"),
        MoodEmotionPresentation("healing", "Chữa lành", "Chữa lành là một sắc xanh"),
        MoodEmotionPresentation("calm", "Bình yên", "Bình yên là khi gió ngừng thổi"),
        MoodEmotionPresentation("nostalgic", "Nhung nhớ", "Nỗi nhớ giấu trong lời ca"),
        MoodEmotionPresentation("lonely", "Cô đơn", "Cô đơn là bóng hình mãi lang thang"),
        MoodEmotionPresentation("sad", "Buồn bã", "Nỗi buồn là những giọt nước mắt pixel"),
        MoodEmotionPresentation("bitter", "Đau khổ", "Tình sâu đậm là một tách cà phê"),
        MoodEmotionPresentation("depressed", "U sầu", "U sầu là căn phòng xanh thẫm"),
    )
    fun emotion(label: String?) = emotions.firstOrNull { it.label == label } ?: emotions.first()

    // The original RN page's generic fallback. It is not presented as a measured per-song result.
    const val introduction = "Điều chỉnh âm thanh thông minh theo cảm xúc của bài hát, thấu hiểu sắc thái tình cảm đằng sau mỗi ca khúc để âm nhạc không chỉ đơn thuần là phát nhạc, mà còn là sự cộng hưởng cảm xúc"
    const val fallbackDescription = "Điều chỉnh âm thanh thông minh theo cảm xúc của bài hát, khiến cảm xúc trong mỗi ca khúc thêm mãnh liệt, khuếch đại rung động và sự đồng điệu"
    val explanation = listOf(
"1. Mô tả chức năng" to "Phân tích các đặc trưng âm thanh như giai điệu, nhịp điệu của bài hát bằng thuật toán AI để tự động nhận diện cảm xúc chủ đạo của bài hát hiện tại, đồng thời áp dụng theo thời gian thực các thông số hiệu ứng âm thanh riêng được tinh chỉnh chính xác theo cảm xúc, tăng cường bầu không khí cảm xúc và cảm giác đắm chìm của bài hát. Không cần điều chỉnh thủ công, bạn vẫn có thể tận hưởng trải nghiệm nghe phù hợp sâu sắc với cảm xúc của bài hát""2.Phạm vi bao phủ bài hát" to "Hệ thống liên tục phân tích chuyên sâu cảm xúc của nhiều bài hát hơn. Những bài hát đã được phân tích sẽ có thể sử dụng tính năng điều chỉnh âm thanh chính xác theo cảm xúc; các bài hát còn lại sẽ tự động áp dụng phương án tối ưu hóa cảm xúc chung. Phạm vi hỗ trợ đang được tiếp tục mở rộng""3. Hiệu ứng âm thanh theo cảm xúc" to "① Trình điều chỉnh âm thanh theo cảm xúc áp dụng chiến lược điều chỉnh riêng cho từng loại cảm xúc: khuếch đại niềm vui, lắng đọng nỗi buồn, sưởi ấm và chữa lành, giúp cảm xúc trong mỗi bài hát được thể hiện rõ nét hơn\n② Hiệu ứng âm thanh theo cảm xúc có thể kết hợp với quyền lợi chất lượng âm thanh: khi đồng thời sử dụng âm thanh không gian (âm thanh vòm đắm chìm, âm thanh toàn cảnh chất lượng cao) và Trình điều chỉnh âm thanh theo cảm xúc, để đảm bảo trải nghiệm nghe nhạc tốt, hiệu quả điều chỉnh theo cảm xúc sẽ bị giảm bớt""4.Giới hạn bản quyền" to "Một số bài hát đặc biệt bị bên giữ bản quyền hạn chế (chẳng hạn như album kỹ thuật số trả phí) sẽ không thể sử dụng tính năng điều chỉnh hiệu ứng âm thanh""5. Quyền riêng tư dữ liệu" to "Lịch sử sử dụng hiệu ứng âm thanh hoặc dữ liệu phân tích cảm xúc bài hát của người dùng chỉ được dùng để tối ưu hóa hiệu ứng âm thanh trên thiết bị, không được tải lên hoặc liên kết với thông tin nhận dạng cá nhân"    )
}
