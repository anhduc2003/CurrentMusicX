package io.github.currencortex.music.feature.binding

import android.graphics.Bitmap
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.currencortex.music.data.binding.BindingState
import io.github.currencortex.music.data.binding.NeteaseMembershipTier
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable internal fun BindingAction(label: String, onClick: () -> Unit, modifier: Modifier = Modifier,
    enabled: Boolean = true, icon: ImageVector? = null, destructive: Boolean = false, fillWidth: Boolean = false) {
    GlassActionButton(label, onClick, modifier, enabled = enabled, destructive = destructive,
        fillWidth = fillWidth)

}

@Composable internal fun BindingNotice(text: String, modifier: Modifier = Modifier, busy: Boolean = false) {
    Row(modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp))
        .background(MiuixTheme.colorScheme.primary.copy(alpha = .06f)).padding(14.dp)
        .semantics { liveRegion = LiveRegionMode.Polite },
        horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
        if (busy) CircularProgressIndicator(Modifier.size(18.dp))
        else Icon(Icons.Default.Info, null, Modifier.size(18.dp), tint = MiuixTheme.colorScheme.primary)
        Text(text, Modifier.weight(1f), fontSize = 13.sp, lineHeight = 20.sp,
            color = MiuixTheme.colorScheme.onSurface.copy(alpha = .7f))
    }
}

@Composable internal fun BindingAccountCard(binding: BindingState, busy: Boolean,
    onSync: () -> Unit, onLive: () -> Unit, onRefresh: () -> Unit, onRelogin: () -> Unit, onUnbind: () -> Unit,
    mainLibrary: Boolean = false, membership: BindingMembershipState = BindingMembershipState(), onMembershipRetry: () -> Unit = {}) {
    SettingsGroup(Modifier.fillMaxWidth().testTag("binding_account_card")) {
        Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                val avatar = binding.profile?.avatar.orEmpty()
                 if (avatar.isNotBlank()) AsyncImage(avatar, "Ảnh đại diện NetEase Cloud", Modifier.size(48.dp).clip(CircleShape), contentScale = ContentScale.Crop)
                else Box(Modifier.size(48.dp).clip(CircleShape).background(MiuixTheme.colorScheme.primary.copy(alpha = .08f)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Person, null, Modifier.size(26.dp), tint = MiuixTheme.colorScheme.primary)
                }
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                     Text(binding.profile?.nickname?.takeIf { it.isNotBlank() } ?: "Nhạc Vân NetEase", fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                     Text("Tài khoản Nhạc Vân NetEase", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                }
                 Text(if (binding.stale) "Cần đăng nhập" else "Đã liên kết", Modifier.clip(CircleShape)
                    .background(MiuixTheme.colorScheme.primary.copy(alpha = .08f)).padding(horizontal = 10.dp, vertical = 6.dp),
                    fontSize = 11.sp, color = MiuixTheme.colorScheme.primary)
            }
            if (!binding.stale && membership.ownerId == binding.profile?.uid) {
                val member = membership.value
                when {
                     membership.loading -> Text("Đang tải thông tin hội viên…", fontSize = 12.sp,
                        color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                    member != null -> {
                        val tint = when (member.tier) {
                            NeteaseMembershipTier.SVIP -> Color(0xFF9A7529)
                            NeteaseMembershipTier.VIP -> Color(0xFFC34965)
                            else -> MiuixTheme.colorScheme.onSurface.copy(alpha = .6f)
                        }
                        Text(member.label, Modifier.testTag("binding_membership").clip(RoundedCornerShape(8.dp))
                            .background(tint.copy(alpha = .10f)).padding(horizontal = 10.dp, vertical = 5.dp),
                            fontSize = 12.sp, fontWeight = FontWeight.Medium, color = tint)
                    }
                    membership.error != null -> Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(membership.error, Modifier.weight(1f), fontSize = 12.sp,
                            color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
                         BindingAction("Thử lại", onMembershipRetry, Modifier.testTag("binding_membership_retry"))
                    }
                }
            }
             if (binding.stale) BindingNotice("Phiên đăng nhập NetEase Cloud đã hết hạn, vui lòng đăng nhập lại NetEase Cloud để tiếp tục sử dụng thư viện nhạc.")
             else if (mainLibrary) Text("Đã kết nối trực tiếp với mục Nhạc tôi thích và danh sách phát trên NetEase Cloud", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
            else if (binding.lastSync > 0) {
                 val date = remember(binding.lastSync) { SimpleDateFormat("MM' tháng 'dd' ngày' HH:mm", Locale.getDefault()).format(Date(binding.lastSync * 1000)) }
                 Text("Đã đồng bộ ${binding.lastSyncCount} danh sách phát · $date", fontSize = 12.sp,
                    color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
             } else Text("Đã kết nối, bạn có thể đồng bộ danh sách phát NetEase Cloud.", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
             GlassActionButton(if (mainLibrary) "Làm mới thư viện Nhạc Vân NetEase" else "Đồng bộ danh sách phát NetEase Cloud", onSync,
                Modifier.fillMaxWidth().testTag("sync_binding"), primary = true, enabled = !busy)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                 BindingAction("Kiểm tra trạng thái đăng nhập", onLive, Modifier.weight(1f), !busy, Icons.Default.Check, fillWidth = true)
                 BindingAction("Làm mới trạng thái đăng nhập", onRefresh, Modifier.weight(1f), !busy, Icons.Default.Refresh, fillWidth = true)
            }
            Box(Modifier.fillMaxWidth().height(1.dp).background(MiuixTheme.colorScheme.onSurface.copy(alpha = .05f)))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                 BindingAction("Đăng nhập lại", onRelogin, Modifier.weight(1f).testTag("relogin_binding"), !busy, fillWidth = true)
                 BindingAction("Hủy liên kết", onUnbind, Modifier.weight(1f).testTag("unbind_binding"), !busy, destructive = true, fillWidth = true)
            }
        }
    }
}

@Composable internal fun BindingMethods(qr: Boolean, enabled: Boolean, onSelect: (Boolean) -> Unit) {
     SettingsSegmentedControl(listOf("Đăng nhập bằng mã QR", "Mã xác minh điện thoại"), if (qr) 0 else 1,
        { onSelect(it == 0) }, enabled = enabled, role = Role.Tab,
        tag = "binding_method", itemTag = { if (it == 0) "qr_binding" else "phone_binding_tab" })

}

@Composable internal fun BindingQrContent(bitmap: Bitmap?, state: BindingUiState, busy: Boolean, onRefresh: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
         Text("Dùng Nhạc Vân NetEase để quét", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
         Text("Quét mã QR · Xác nhận cấp quyền · Kết nối thư viện nhạc", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .5f))
        Box(Modifier.size(216.dp).clip(RoundedCornerShape(22.dp)).background(Color.White)
            .border(1.dp, Color.Black.copy(alpha = .05f), RoundedCornerShape(22.dp)).padding(8.dp), contentAlignment = Alignment.Center) {
             if (bitmap != null) Image(bitmap.asImageBitmap(), "Mã QR đăng nhập NetEase Cloud", Modifier.fillMaxSize().testTag("binding_qr"))
             else if (state.qrUrl != null || state.qrMessage.isBlank() || state.qrMessage.contains("Đang tạo")) CircularProgressIndicator(Modifier.size(28.dp))
            else Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
                 Icon(if (state.qrMessage.contains("Đã hết hạn")) Icons.Default.Refresh else Icons.Default.Info, null,
                    Modifier.size(36.dp), tint = Color(0xFF747C89))
                 Text(if (state.qrMessage.contains("Đã hết hạn")) "Mã QR đã hết hạn" else if (state.qrMessage == "Liên kết thành công") "Đã cấp quyền" else "Mã QR hiện không khả dụng",
                    color = Color(0xFF535D6B), fontSize = 13.sp)
            }
        }
         Text(state.qrMessage.ifBlank { "Đang chuẩn bị mã QR…" }, Modifier.testTag("binding_qr_status").semantics { liveRegion = LiveRegionMode.Polite },
            fontSize = 13.sp, lineHeight = 20.sp, color = MiuixTheme.colorScheme.onSurface.copy(alpha = .65f))
        BindingAction("Làm mới mã QR", onRefresh, Modifier.testTag("refresh_binding_qr"), !busy, Icons.Default.Refresh)
        Text("Sau khi cấp quyền, chỉ cần quay lại trang này; việc chuyển ứng dụng sẽ không làm mới mã QR hiện tại.", fontSize = 12.sp, lineHeight = 19.sp,
            color = MiuixTheme.colorScheme.onSurface.copy(alpha = .45f))
    }
}
