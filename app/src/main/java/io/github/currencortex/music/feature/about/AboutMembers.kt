// Ported from LeiFetch AboutScreen, GPL-3.0; same avatar rows, member dialogs and AnimatedList motion.
package io.github.currencortex.music.feature.about

import io.github.currencortex.music.ui.component.MusicDialogButton
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import io.github.currencortex.music.core.config.AboutMember
import io.github.currencortex.music.ui.util.openExternalLink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import io.github.currencortex.music.ui.component.MusicOverlayDialog as OverlayDialog
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme

internal data class MemberFocus(val member: AboutMember, val group: String)

/** Bộ nhớ đệm ảnh đại diện QQ; mỗi lần mở trang giới thiệu chỉ tải tối đa vài ảnh đại diện, không cần ghi xuống đĩa.
 *  Mức độ khả dụng của từng endpoint khác nhau tùy mạng (thực tế thử nghiệm, headimg_dl trả về 400 trên một số mạng), nên lần lượt thử phương án dự phòng.
 *  Khóa bộ nhớ đệm kèm kích thước: danh sách dùng 100, cửa sổ bật lên chi tiết dùng 640. */
private object QqAvatarCache {
    private val cache = object : LruCache<String, Bitmap>(24) {
        override fun sizeOf(key: String, value: Bitmap) = 1
    }
    private val endpoints = listOf(
        "https://q1.qlogo.cn/g?b=qq&nk=%s&s=%d",
        "https://thirdqq.qlogo.cn/g?b=qq&nk=%s&s=%d",
        "https://q1.qlogo.cn/headimg_dl?dstuin=%s&spec=%d",
    )
    suspend fun load(qq: String, spec: Int = 100): Bitmap? {
        val key = "$qq@$spec"
        cache.get(key)?.let { return it }
        return withContext(Dispatchers.IO) {
            endpoints.firstNotNullOfOrNull { pattern ->
                try {
                    val connection = URL(pattern.format(qq, spec)).openConnection() as HttpURLConnection
                    connection.connectTimeout = 5000
                    connection.readTimeout = 5000
                    connection.setRequestProperty("User-Agent",
                        "CurrentMusic avatar/1.0")
                    try {
                        BitmapFactory.decodeStream(connection.inputStream)?.also { cache.put(key, it) }
                    } finally {
                        connection.disconnect()
                    }
                } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
                catch (_: Exception) { null }
            }
        }
    }
}

@Composable
internal fun QqAvatar(qq: String, size: Dp = 44.dp, spec: Int = 100, modifier: Modifier = Modifier) {
    val bitmap by produceState<Bitmap?>(null, qq, spec) { value = QqAvatarCache.load(qq, spec) }
    Box(modifier.size(size).clip(CircleShape)
        .background(colorScheme.onSurface.copy(alpha = 0.06f)), contentAlignment = Alignment.Center) {
        val loaded = bitmap
        if (loaded != null) Image(loaded.asImageBitmap(), contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
        else Text(qq.takeLast(2), fontSize = (size.value * 0.27f).sp, color = colorScheme.onSurfaceVariantSummary)
    }
}

@Composable
internal fun MemberRow(member: AboutMember, onClick: () -> Unit) {
    BasicComponent(
        title = member.name,
        summary = member.role,
        startAction = { QqAvatar(member.qq, modifier = Modifier.padding(end = 6.dp)) },
        endActions = {
            // Dùng cùng mũi tên với dòng liên kết bên dưới để cho biết dòng này có thể nhấn mở.
            Image(
                imageVector = MiuixIcons.Basic.ArrowRight,
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorScheme.onSurfaceVariantActions),
                modifier = Modifier.size(width = 10.dp, height = 16.dp).align(Alignment.CenterVertically),
            )
        },
        onClick = onClick,
    )
}

/** Hiệu ứng chuyển động cho mục của ReactBits AnimatedList: khi hơn một nửa mục đi vào màn hình, mục sẽ mờ dần từ scale 0.7 / trong suốt hoàn toàn
 *  thành 1; khi rời khỏi khung nhìn thì đặt lại, cuộn trở lại sẽ phát lại (tương ứng với amount 0.5 và once: false của useInView).
 *  Độ trễ của mỗi mục luôn cố định ở 100ms; các mục tự nhiên lệch nhịp khi lần lượt vượt qua một nửa khung nhìn, thay vì cộng dồn độ trễ theo thứ tự.
 *
 *  Không thể dùng onGloballyPositioned để xác định khả năng hiển thị: khi cuộn, LazyColumn chỉ sắp xếp lại các mục đã đo,
 *  không bố cục lại nên callback vị trí không được gọi lại, khiến các mục ngoài màn hình mãi ở trạng thái trong suốt. Vì vậy callback vị trí chỉ dùng để ghi nhận
 *  độ lệch của mục trong phần tử danh sách chủ và chiều cao của mục (đều cố định khi cuộn); tỷ lệ hiển thị được tính theo thời gian thực từ thông tin cuộn. */
@Composable
internal fun AnimatedListItem(
    listState: LazyListState,
    hostKey: String,
    content: @Composable () -> Unit,
) {
    var offsetInHost by remember { mutableStateOf(0f) }
    var heightPx by remember { mutableStateOf(0f) }

    val inView by remember(listState, hostKey) {
        derivedStateOf {
            val layout = listState.layoutInfo
            val host = layout.visibleItemsInfo.firstOrNull { it.key == hostKey }
            if (host == null || heightPx <= 0f) {
                false
            } else {
                val top = host.offset + offsetInHost
                val visible = (top + heightPx).coerceAtMost(layout.viewportEndOffset.toFloat()) -
                        top.coerceAtLeast(layout.viewportStartOffset.toFloat())
                visible >= heightPx * 0.5f
            }
        }
    }

    Box(
        Modifier.onGloballyPositioned { coordinates ->
            offsetInHost = coordinates.positionInParent().y
            heightPx = coordinates.size.height.toFloat()
        }
    ) {
        val progress by animateFloatAsState(
            targetValue = if (inView) 1f else 0f,
            animationSpec = tween(
                durationMillis = 200,
                delayMillis = 100,
                easing = FastOutSlowInEasing,
            ),
            label = "animatedListItem",
        )
        Box(
            Modifier.graphicsLayer {
                alpha = progress
                val scale = 0.7f + 0.3f * progress
                scaleX = scale
                scaleY = scale
            }
        ) { content() }
    }
}

/** Cửa sổ bật lên thông tin thành viên: ảnh đại diện, biệt danh, nhóm và vai trò; thành viên có detail sẽ có thêm phần mô tả đóng góp.
 *  Truyền riêng show và focus: sau khi đóng, focus vẫn giữ lựa chọn gần nhất để tiếp tục hiển thị nội dung trong lúc chạy hoạt ảnh thoát. */
@Composable
internal fun MemberDetailDialog(show: Boolean, focus: MemberFocus?, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val contentHeight = (androidx.compose.ui.platform.LocalConfiguration.current.screenHeightDp.dp - 160.dp).coerceAtLeast(180.dp)
    OverlayDialog(show = show, title = "Thông tin thành viên", onDismissRequest = onDismiss) {
        Column(Modifier.fillMaxWidth().heightIn(max = contentHeight).testTag("member_dialog_content")) {
            focus?.let { current ->
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    QqAvatar(current.member.qq, size = 76.dp, spec = 640)
                    Text(current.member.name, fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 12.dp))
                    Text(current.group, fontSize = 13.sp, color = colorScheme.onSurfaceVariantSummary,
                        modifier = Modifier.padding(top = 4.dp))
                    HorizontalDivider(Modifier.padding(vertical = 16.dp),
                        color = colorScheme.onSurface.copy(alpha = 0.08f))
                    MemberInfoRow("Vai trò", current.member.role)
                }
                current.member.detail?.let { detail ->
                    // Chi tiết có thể gồm nhiều dòng (mô tả đóng góp của tác giả được chia thành từng mục); giới hạn chiều cao rồi cho phép cuộn để không đẩy nút đóng bên dưới ra ngoài.
                    Text(detail, fontSize = 13.sp, lineHeight = 21.sp,
                        color = colorScheme.onSurface,
                        modifier = Modifier.weight(1f, fill = false).fillMaxWidth().heightIn(max = 300.dp)
                            .verticalScroll(rememberScrollState())
                            .padding(top = 12.dp))
                }
                current.member.githubUrl?.let { url ->
                    MusicDialogButton("GitHub", onClick = { openExternalLink(context, url) },
                        modifier = Modifier.fillMaxWidth().padding(top = 12.dp).testTag("member_github"))
                }
                MusicDialogButton("Đóng", onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth().padding(top = 18.dp))
            }
        }
    }
}

@Composable
private fun MemberInfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(label, fontSize = 13.sp, color = colorScheme.onSurfaceVariantSummary,
            modifier = Modifier.width(48.dp))
        Text(value, fontSize = 13.sp, modifier = Modifier.weight(1f))
    }
}
