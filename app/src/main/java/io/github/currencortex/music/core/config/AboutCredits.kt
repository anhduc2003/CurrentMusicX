// CurrentMusic contributors, displayed with the LeiFetch member cards.
package io.github.currencortex.music.core.config

data class AboutMember(
    val qq: String,
    val name: String,
    val role: String,
    val detail: String? = null,
    val githubUrl: String? = null,
)

data class AboutSection(val title: String, val members: List<AboutMember>)

object AboutCredits {
    val sections = listOf(
        AboutSection("Nhóm phát triển", listOf(
            AboutMember("3140014249", "bileizhen", "Phát triển chính · Thiết kế · Bảo trì",
                detail = "Phát triển chính toàn bộ CurrentMusicX, phụ trách phát triển tổng thể dự án, kiến trúc và thiết kế giao diện.\n" +
                    "Điều phối việc cải tiến tính năng và tích hợp đóng góp, liên tục duy trì trải nghiệm ứng dụng, phát hành phiên bản và phối hợp dự án.",
                githubUrl = "https://github.com/bileizhen"),
            AboutMember("1945826346", "Rcst20", "Phát triển dự án gốc · Tương thích nguồn nhạc và phát nhạc",
                detail = "Phát triển dự án CurrentMusic gốc, đồng thời tham gia tương thích nguồn nhạc và phát nhạc cho CurrentMusicX.\n" +
                    "Bổ sung ưu tiên chọn nguồn Dolby có thể phát khi tự động chọn chất lượng âm thanh cao nhất, kèm phương án dự phòng khi thất bại; sửa tên thiết bị trong báo cáo nghe nhạc trên Nhạc Vân NetEase.\n" +
                    "Sửa lỗi lời bài hát trên linh động đảo ở màn hình chính vẫn trôi sang trái phải khi văn bản chưa bị tràn.\n" +
                    "Bổ sung sơ đồ nguyên lý bằng tiếng Trung và tiếng Anh, mô tả nguồn nhạc, lời bài hát và luồng yêu cầu giữa frontend và backend.",
                githubUrl = "https://github.com/backrooms-yrc"),
            AboutMember("2468872022", "LinYe2804", "Trải nghiệm lời bài hát · Hiệu năng giao diện · Tương thích màn hình ngang",
                detail = "Tối ưu cập nhật lời bài hát và hoạt ảnh chuyển tiếp của trình phát mini, sửa lỗi lời bài hát nhấp nháy.\n" +
                    "Thêm lời bài hát từng chữ theo phong cách Apple Music, hỗ trợ điều chỉnh khoảng cách, tốc độ hoạt ảnh, cường độ phát sáng và làm mờ, cùng tùy chọn hiển thị bản dịch, phiên âm La-tinh và chú âm; tối ưu độ mượt khi hiển thị lời bài hát.\n" +
                    "Tối ưu việc tái cấu trúc giao diện, tuần tự hóa hàng đợi phát, tải danh sách phát và kết quả tìm kiếm, cùng hiệu ứng chuyển tiếp làm mờ trang và cách vẽ kính lỏng, giảm tải cho luồng chính và quá trình kết xuất.\n" +
                    "Cải thiện hiệu ứng chuyển sắc trong suốt của ảnh bìa ở chế độ đắm chìm và chuyển tiếp giữa màn hình ngang, dọc; sửa viền trắng của danh sách phát trên màn hình rộng và hiệu ứng ánh sáng nhịp điệu của trình phát mini ở chế độ ngang, thống nhất cách tương tác với thanh bên.\n" +
                    "Sửa lỗi kết xuất, sắp xếp, lọc và khôi phục sau khi xoay khi mở kệ đĩa CD từ trang tìm kiếm; hoàn thiện bố cục ngang và khả năng tương thích với vùng an toàn.\n" +
                    "Cố định điều khiển chuyển tab ở đầu trang Khám phá để tránh xung đột với thao tác cuộn nội dung.",
                githubUrl = "https://github.com/LinYe2804"),
            AboutMember("442259851", "KelierAndes", "Tương tác lời bài hát · Tối ưu tìm kiếm lời bài hát",
                detail = "Sửa lỗi phần tô sáng không bám theo khi kéo lời bài hát và hiện tượng giật khi bật nảy; cải thiện trải nghiệm trượt liên tục khi kéo nhanh và quay về dòng đang được hát.\n" +
                    "Hoàn thiện việc chuyển đổi giữa duyệt lời bài hát và đồng bộ theo phát nhạc: ẩn bản dịch và phiên âm La-tinh khi duyệt, khôi phục hiển thị khi quay lại lời bài hát hiện tại; có thể kéo lại để tiếp tục điều khiển trong lúc bật nảy.\n" +
                    "Bổ sung hiển thị câu khớp cùng ngữ cảnh trước và sau, tô sáng từ khớp và tự bổ sung ngữ cảnh bị thiếu cho tính năng tìm kiếm lời bài hát; đồng thời tối ưu bố cục kết quả tìm kiếm.",
                githubUrl = "https://github.com/KelierAndes"),
AboutMember("2711058826", "Mười Ba", "Hiển thị lời bài hát · Tương thích màn hình tràn viền",                detail = "Sửa lỗi phần xem trước lời bài hát trên ảnh bìa ở trang phát nhạc bị cắt ở cạnh dưới do chiều cao dòng cố định; điều chỉnh chiều cao dòng và vùng xem trước theo cỡ chữ và tỷ lệ phóng to phông chữ hệ thống.\n" +
                    "Tương thích với điều hướng bằng cử chỉ trên màn hình tràn viền, cho phép nội dung trang kéo dài xuống vùng thanh cử chỉ phía dưới và chừa khoảng cách an toàn của thanh điều hướng cho vùng nhập tin nhắn."
                githubUrl = "https://github.com/WhiteXero"),
        )),
        AboutSection("Nhóm CDN", listOf(
            AboutMember("2536843865", "Hutao_felicity", "CDN · currentmusic.geekertao.com",
                githubUrl = "https://github.com/Geekertao"),
            AboutMember("3140014249", "bileizhen", "CDN · cm.bileizhen.top",
                githubUrl = "https://github.com/bileizhen"),
            AboutMember("1462302892", "Var username:String?=null", "CDN · music.ctcehs.com"),
        )),
    )
}
