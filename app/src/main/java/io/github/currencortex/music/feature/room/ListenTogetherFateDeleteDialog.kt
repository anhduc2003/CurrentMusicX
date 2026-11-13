package io.github.currencortex.music.feature.room

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.netease.listen.ListenTogetherFateReport
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.MusicTextAction
import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import io.github.currencortex.music.ui.component.nativeui.SettingsToggle
import top.yukonga.miuix.kmp.basic.Text

@Composable internal fun ListenTogetherFateDeleteDialog(report: ListenTogetherFateReport,
    onDismiss: () -> Unit, onConfirm: (Boolean) -> Unit) {
    var deleteRecords by remember(report) { mutableStateOf(false) }
    MusicDialog("Xóa báo cáo duyên phận?", onDismiss) {
        Text("Báo cáo duyên phận và dữ liệu xếp hạng với ${report.peer.nickname.ifBlank { "người bạn nghe nhạc này" }} sẽ bị xóa. Sau khi xóa, bạn không thể khôi phục.", fontSize = 14.sp)
        if (report.openActivity) Text("Huy hiệu Nghe cùng và tiến độ liên quan cũng sẽ bị thu hồi.", fontSize = 14.sp)
        SettingsToggle("Đồng thời xóa lịch sử Nghe cùng", deleteRecords, { deleteRecords = it },
            Modifier.testTag("listen_fate_delete_records"), summary = "Mặc định giữ lại lịch sử với họ"
        MusicDialogActions {
            MusicDialogButton("Hủy", onDismiss, Modifier.weight(1f).testTag("listen_fate_delete_cancel"))
            MusicDialogButton("Xác nhận xóa", { onConfirm(deleteRecords) }, Modifier.weight(1f).testTag("listen_fate_delete_confirm"), destructive = true)
        }
    }
}
