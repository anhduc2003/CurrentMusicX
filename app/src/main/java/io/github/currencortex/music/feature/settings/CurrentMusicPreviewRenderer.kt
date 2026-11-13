package io.github.currencortex.music.feature.settings

import android.graphics.Paint as AndroidPaint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.TextUtils
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import io.github.currencortex.music.ui.component.MusicIcons
import io.github.currencortex.music.ui.theme.LocalCustomTypeface
import top.yukonga.miuix.kmp.theme.Colors

@Composable internal fun rememberCurrentMusicPreviewRenderer(): CurrentMusicPreviewRenderer {
    val font = LocalCustomTypeface.current
    val fontScale = LocalDensity.current.fontScale
    val vectors = linkedMapOf("home" to Icons.Default.Home, "discover" to Icons.Default.Star,
        "me" to Icons.Default.Person, "avatar" to Icons.Default.Person, "settings" to Icons.Default.Settings,
        "play" to MusicIcons.Play, "heart" to MusicIcons.Heart, "list" to MusicIcons.List, "mic" to MusicIcons.Microphone,
        "headphones" to MusicIcons.Headphones, "download" to MusicIcons.Download,
        "appearance" to MusicIcons.Image, "lyrics" to MusicIcons.BottomBar, "device" to MusicIcons.Device,
        "recent" to MusicIcons.Update, "storage" to MusicIcons.Storage, "file" to MusicIcons.File, "info" to MusicIcons.Info)
    val painters = vectors.mapValues { rememberVectorPainter(it.value) }
    return remember(font, fontScale, painters) { CurrentMusicPreviewRenderer(font, fontScale, painters) }
}

/** Shared, theme-aware CurrentMusic scenes. Layout mirrors MusicHomeScreen,
 * DiscoverScreen, ProfileScreen, SettingsScreen and UnifiedMusicDock. Artwork
 * is local demonstration art; previewing never starts repositories or playback. */
internal class CurrentMusicPreviewRenderer(font: Typeface?, private val fontScale: Float,
    private val icons: Map<String, Painter>) {
    private val regular = font ?: Typeface.create("sans-serif", Typeface.NORMAL)
    private val bold = Typeface.create(regular, Typeface.BOLD)
    private val textPaint = TextPaint(AndroidPaint.ANTI_ALIAS_FLAG)

    fun draw(scope: DrawScope, colors: Colors, floating: Boolean, glass: Boolean, selected: Int,
        previous: Int? = null, progress: Float = 1f, extent: Size = scope.size,
        paper: Color = colors.background, card: Color = colors.surfaceContainer,
        primary: Color = colors.primary, fade: Boolean = false) = with(scope) {
        val factor = extent.width / Width
        val height = extent.height / factor
        withTransform({ scale(factor, factor, Offset.Zero) }) {
            val scene = Scene(this, colors, card, primary)
            drawRect(paper, size = Size(Width, height))
            fun page(tab: Int, offset: Float) = withTransform({ translate(offset, 0f) }) {
                clipRect(0f, 0f, Width, height - DockBottom) {
                    drawRect(paper, size = Size(Width, height))
                    when (tab) {
                        0 -> scene.home()
                        1 -> scene.discover()
                        2 -> scene.profile()
                        else -> scene.settings()
                    }
                }
            }
            if (previous == null) page(selected, 0f) else if (fade) {
                drawContext.canvas.saveLayer(Rect(0f, 0f, Width, height), Paint().apply { alpha = 1 - progress })
                page(previous, 0f); drawContext.canvas.restore()
                drawContext.canvas.saveLayer(Rect(0f, 0f, Width, height), Paint().apply { alpha = progress })
                page(selected, 0f); drawContext.canvas.restore()
            } else {
                val direction = if (previous > selected) -1 else 1
                page(previous, -direction * Width * progress)
                page(selected, direction * Width * (1 - progress))
            }
            scene.dock(height, selected, floating, glass)
            drawRoundRect(colors.onSurface.copy(alpha = .3f), Offset(142f, height - 8f), Size(76f, 3f), CornerRadius(2f))
        }
    }

    private inner class Scene(val scope: DrawScope, val c: Colors, val card: Color, val accent: Color) {
        private val ink = c.onSurface
        private val muted = c.onSurfaceVariantSummary
        private val nav = listOf("home", "discover", "me", "settings")
        private val labels = listOf("Trang chủ", "Khám phá", "Cá nhân", "Cài đặt")
        private fun text(value: String, x: Float, y: Float, size: Float, color: Color = ink,
            strong: Boolean = false, align: AndroidPaint.Align = AndroidPaint.Align.LEFT, maxWidth: Float = 330f) {
            textPaint.typeface = if (strong) bold else regular
            textPaint.textSize = size * fontScale
            textPaint.color = color.toArgb(); textPaint.textAlign = align
            val fitted = TextUtils.ellipsize(value, textPaint, maxWidth, TextUtils.TruncateAt.END).toString()
            scope.drawContext.canvas.nativeCanvas.drawText(fitted, x, y, textPaint)
        }
        private fun panel(x: Float, y: Float, width: Float, height: Float, color: Color = card, radius: Float = 24f) = with(scope) {
            drawRoundRect(color, Offset(x, y), Size(width, height), CornerRadius(radius))
        }
        private fun icon(name: String, x: Float, y: Float, side: Float = 24f, color: Color = accent) = with(scope) {
            withTransform({ translate(x, y) }) { with(icons.getValue(name)) { draw(Size(side, side), colorFilter = ColorFilter.tint(color)) } }
        }
        private fun play(x: Float, y: Float, side: Float, color: Color = ink) = with(scope) {
            val path = Path().apply { moveTo(x, y); lineTo(x + side * .8f, y + side / 2); lineTo(x, y + side); close() }
            drawPath(path, color)
        }
        private fun avatar(x: Float, y: Float, radius: Float) = with(scope) {
            drawCircle(accent.copy(alpha = .18f), radius, Offset(x, y))
            icon("avatar", x - radius * .68f, y - radius * .68f, radius * 1.36f)
        }
        private fun art(x: Float, y: Float, side: Float, index: Int, radius: Float = 18f) = with(scope) {
            val palette = ArtColors[index % ArtColors.size]
            val clip = Path().apply { addRoundRect(RoundRect(Rect(x, y, x + side, y + side), CornerRadius(radius))) }
            clipPath(clip) {
                drawRect(Brush.linearGradient(palette, Offset(x, y), Offset(x + side, y + side)), Offset(x, y), Size(side, side))
                drawCircle(Color.White.copy(alpha = .12f), side * .64f, Offset(x + side * .85f, y + side * .15f))
                val mountain = Path().apply {
                    moveTo(x, y + side * .79f); lineTo(x + side * .43f, y + side * .34f)
                    lineTo(x + side, y + side * .9f); lineTo(x + side, y + side); lineTo(x, y + side); close()
                }
                drawPath(mountain, Color.Black.copy(alpha = .2f))
                drawCircle(Color.White.copy(alpha = .7f), side * .075f, Offset(x + side * .28f, y + side * .26f))
                text("MUSIC", x + side * .13f, y + side * .84f, side * .13f, Color.White, true, maxWidth = side * .8f)
            }
        }
        fun home() = with(scope) {
            text("Current Music", 16f, 46f, 24f, strong = true, maxWidth = 265f)
            avatar(316f, 36f, 21f)
            panel(16f, 80f, 328f, 48f)
            drawCircle(muted, 6f, Offset(34f, 102f), style = Stroke(1.5f))
            drawLine(muted, Offset(38f, 107f), Offset(43f, 112f), 1.5f)
            text("Tìm bài hát, ca sĩ, playlist", 52f, 109f, 13f, muted)
            withTransform({ translate(0f, -12f) }) {
            text("Đề xuất hằng ngày", 20f, 176f, 20f, strong = true)
            text("Tất cả ›", 338f, 174f, 12f, accent, align = AndroidPaint.Align.RIGHT)
            art(35f, 220f, 122f, 1); art(203f, 220f, 122f, 2)
            drawRoundRect(Color.Black.copy(alpha = .12f), Offset(105f, 207f), Size(150f, 150f), CornerRadius(20f))
            art(105f, 200f, 150f, 0, 20f)
            text("Tuyển chọn hôm nay", 180f, 381f, 18f, strong = true, align = AndroidPaint.Align.CENTER)
            text("CurrentMusic · Đề xuất hằng ngày", 180f, 402f, 12f, muted, align = AndroidPaint.Align.CENTER)
            panel(20f, 424f, 320f, 64f)
            text("Phiêu du riêng tư", 36f, 450f, 16f)
            text("Theo sở thích của bạn, nghe liền mạch những bản nhạc hay", 36f, 470f, 12f, muted, maxWidth = 235f)
            drawCircle(accent, 19f, Offset(305f, 456f)); play(300f, 448f, 16f, c.onPrimary)
            panel(20f, 502f, 320f, 76f)
            val shortcutIcons = listOf("heart", "list", "mic", "headphones")
            listOf("Yêu thích", "Playlist", "Nhận diện bài hát", "Nghe cùng nhau").forEachIndexed { index, title ->
                val center = 60f + index * 80
                icon(shortcutIcons[index], center - 11.5f, 516f, 23f)
                text(title, center, 560f, 12f, align = AndroidPaint.Align.CENTER, maxWidth = 76f)
            }
            text("Có thể bạn cũng thích", 32f, 613f, 17f, muted, true)
            text("Thêm ›", 338f, 611f, 12f, accent, align = AndroidPaint.Align.RIGHT)
            clipRect(20f, 626f, 340f, 810f) {
                repeat(3) { i ->
                    val x = 20f + i * 152
                    art(x, 630f, 136f, i + 1)
                    text(listOf("Tuyển chọn cho bạn", "Khoảnh khắc thư thái", "Giai điệu nhẹ nhàng")[i], x, 788f, 14f)
                }
            }
            }
        }
        fun discover() = with(scope) {
            text("Khám phá", 16f, 46f, 24f, strong = true)
            panel(16f, 72f, 328f, 44f, radius = 22f)
            panel(20f, 76f, 80f, 36f, accent, 18f)
            listOf("Nhạc", "Người dùng", "Bài đăng", "Chat").forEachIndexed { i, title ->
                text(title, 60f + i * 80, 101f, 15f, if (i == 0) c.onPrimary else muted,
                    strong = i == 0, align = AndroidPaint.Align.CENTER, maxWidth = 74f)
            }
            panel(16f, 152f, 328f, 124f, radius = 20f)
            row("Đề xuất hằng ngày", "Playlist dành riêng cho hôm nay", 152f, "play")
            row("Có thể bạn cũng thích", "Tiếp tục khám phá theo giai điệu yêu thích", 214f, "heart")
            text("Thể loại nhạc", 32f, 315f, 17f, muted, true)
            listOf("Pop", "Rock", "Nhạc điện tử", "Dân ca", "Jazz", "Cổ điển").forEachIndexed { i, title ->
                val x = 20f + i % 2 * 166; val y = 336f + i / 2 * 122
                panel(x, y, 154f, 108f)
                art(x + 10, y + 10, 64f, i, 14f)
                text(title, x + 10, y + 96, 15f, strong = true, maxWidth = 130f)
            }
        }
        fun profile() = with(scope) {
            text("Cá nhân", 16f, 46f, 24f, strong = true)
            icon("file", 264f, 24f, 24f, ink)
            icon("settings", 310f, 24f, 24f, ink)
            withTransform({ translate(0f, 8f) }) {
            panel(16f, 88f, 328f, 240f, radius = 20f)
            avatar(64f, 136f, 32f)
            text("CurrentMusic", 112f, 128f, 21f, strong = true, maxWidth = 212f)
            text("Để âm nhạc yêu thích luôn đồng hành", 112f, 153f, 13f, muted, maxWidth = 212f)
            drawLine(ink.copy(alpha = .08f), Offset(32f, 184f), Offset(328f, 184f))
            listOf("Đã thích", "Yêu thích", "Playlist").forEachIndexed { i, title ->
                text(listOf("24", "18", "8")[i], 32f + i * 104, 217f, 21f, strong = true)
                text(title, 32f + i * 104, 241f, 12f, muted, maxWidth = 92f)
            }
            drawLine(ink.copy(alpha = .08f), Offset(32f, 260f), Offset(328f, 260f))
            text("Số ngày nghe nhạc", 32f, 282f, 12f, muted)
            text("12 ngày", 32f, 308f, 15f)
            text("Thời lượng nghe nhạc", 188f, 282f, 12f, muted)
            text("3 giờ 30 phút", 188f, 308f, 15f, maxWidth = 140f)
            text("Nhạc của tôi", 32f, 368f, 17f, muted, true)
            panel(16f, 388f, 328f, 248f, radius = 20f)
            listOf(Triple("Bài hát yêu thích", "Bài hát đã lưu", "heart"), Triple("Danh sách phát", "Danh sách phát đã tạo và lưu", "list"),
                Triple("Phát gần đây", "Nghe lại các bài hát đã nghe", "recent"), Triple("Nhạc trên thiết bị", "Bài hát trên điện thoại", "device")).forEachIndexed { i, item ->
                row(item.first, item.second, 388f + i * 62, item.third)
            }
            text("Dịch vụ âm nhạc", 32f, 676f, 17f, muted, true)
            panel(16f, 696f, 328f, 186f, radius = 20f)
            listOf("Tài khoản Nhạc Vân NetEase" to "Liên kết tài khoản, đồng bộ danh sách phát", "Nhập danh sách phát" to "Liên kết, văn bản, hình ảnh và bài hát trên thiết bị",
                "Thư viện Navidrome" to "Máy chủ nhạc của riêng bạn").forEachIndexed { i, item -> row(item.first, item.second, 696f + i * 62) }
            }
        }
        fun settings() {
            text("Cài đặt", 16f, 46f, 24f, strong = true)
            panel(16f, 96f, 328f, 62f, radius = 20f)
            row("Kiểm tra cập nhật", "Xem phiên bản và ghi chú cập nhật", 96f, "download")
            text("Cá nhân hóa", 32f, 198f, 17f, muted, true)
            panel(16f, 217f, 328f, 124f, radius = 20f)
            row("Giao diện", "Chủ đề, màu sắc và hiệu ứng giao diện", 217f, "appearance")
            row("Lời bài hát nổi trên màn hình", "Xem trước trực tiếp, kiểu phụ đề và điều khiển cửa sổ", 279f, "lyrics")
            text("Âm nhạc", 32f, 381f, 17f, muted, true)
            panel(16f, 400f, 328f, 248f, radius = 20f)
            row("Phát nhạc", "Chất lượng âm thanh, chuyển bài và tải trước", 400f, "headphones")
            row("Nguồn nhạc và mạng", "Nguồn nhạc, nguồn lời bài hát và máy chủ", 462f, "storage")
            row("Hiệu ứng âm thanh", "Điều chỉnh âm thanh thông minh, hiệu ứng và bộ cân bằng", 524f, "headphones")
            row("Đầu ra âm thanh", "Tần số lấy mẫu đầu ra, thiết bị và thông tin khả năng", 586f, "device")
        }
        private fun row(title: String, summary: String, y: Float, glyph: String? = null) {
            val x = if (glyph == null) 36f else 72f
            glyph?.let { icon(it, 30f, y + 17f, 24f, ink) }
            text(title, x, y + 26f, 17f, maxWidth = 302f - x)
            text(summary, x, y + 47f, 12f, muted, maxWidth = 302f - x)
            text("›", 324f, y + 36f, 23f, muted, align = AndroidPaint.Align.RIGHT)
        }
        fun dock(height: Float, selected: Int, floating: Boolean, glass: Boolean) = with(scope) {
            val left = if (floating) DockMargin else 0f
            val right = Width - left
            val bottom = height - DockBottom
            val top = bottom - DockHeight
            val radius = if (floating) 32f else 0f
            panel(left, top + 3f, right - left, DockHeight, Color.Black.copy(alpha = .1f), radius)
            panel(left, top, right - left, DockHeight, card, radius)
            if (glass) {
                val shape = Path().apply { addRoundRect(RoundRect(Rect(left, top, right, bottom), CornerRadius(radius))) }
                clipPath(shape) {
                    drawRect(Brush.radialGradient(listOf(Color(0xFF83CBE7).copy(alpha = .38f), Color.Transparent),
                        Offset(right - 30, bottom + 20), 230f), Offset(left, top), Size(right - left, DockHeight))
                }
                drawRoundRect(Brush.linearGradient(listOf(Color.White.copy(alpha = .55f), Color.White.copy(alpha = .07f),
                    Color.White.copy(alpha = .25f)), Offset(left, top), Offset(right, bottom)), Offset(left, top),
                    Size(right - left, DockHeight), CornerRadius(radius), style = Stroke(1f))
            }
            val cover = Path().apply { addOval(Rect(left + 10, top + 10, left + 52, top + 52)) }
            clipPath(cover) { art(left + 10, top + 10, 42f, 0, 21f) }
            text("Tuyển chọn hôm nay", left + 64, top + 28, 14f, maxWidth = 122f)
            text("CurrentMusic", left + 64, top + 45, 11f, muted, maxWidth = 122f)
            drawCircle(ink.copy(alpha = .3f), 12f, Offset(right - 72, top + 31), style = Stroke(1.5f))
            play(right - 75, top + 24, 14f)
            icon("list", right - 43, top + 19, 24f, ink)
            val cell = (right - left) / 4
            nav.forEachIndexed { i, glyph ->
                val center = left + cell * (i + .5f)
                val navTop = top + DockHeight - NavHeight
                if (i == selected) panel(center - cell * .46f, navTop + 4, cell * .92f, 54f, ink.copy(alpha = .06f), 26f)
                val color = if (i == selected) accent else ink
                icon(glyph, center - 12, navTop + 11, 24f, color)
                text(labels[i], center, navTop + 49, 11f, color, align = AndroidPaint.Align.CENTER, maxWidth = cell - 4)
            }
        }
    }
    companion object {
        const val Width = 360f
        const val DockMargin = 26f
        const val DockBottom = 24f
        const val DockHeight = 122f
        const val NavHeight = 64f
        private val ArtColors = listOf(listOf(Color(0xFF719DB8), Color(0xFF192E51)),
            listOf(Color(0xFFD4AF88), Color(0xFF755065)), listOf(Color(0xFF899A85), Color(0xFF273E48)))
    }
}
