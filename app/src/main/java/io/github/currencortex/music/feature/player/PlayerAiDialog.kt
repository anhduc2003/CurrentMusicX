package io.github.currencortex.music.feature.player

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.data.profile.PostAttachment
import io.github.currencortex.music.feature.update.MarkdownText
import io.github.currencortex.music.ui.component.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun PlayerAiDialog(controller: PlayerAiController, onDismiss: () -> Unit, onCard: (PostAttachment) -> Unit) {
    val state by controller.state.collectAsState()
    val list = rememberLazyListState()
    LaunchedEffect(state.turns.lastOrNull()?.answer, state.turns.size) {
        if (state.turns.isNotEmpty()) list.animateScrollToItem(state.turns.lastIndex)
    }
    MusicDialog("Hỏi AI", onDismiss) {
        Text(state.song?.name.orEmpty(), fontSize = 16.sp, maxLines = 2)
        Text("Chỉ trả lời trong hộp thoại này, không gửi đến phòng trò chuyện công khai", fontSize = 12.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
        LazyColumn(Modifier.fillMaxWidth().heightIn(min = 80.dp, max = 300.dp).testTag("player_ai_history"), state = list,
            verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(state.turns) { turn -> Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(turn.question, color = MiuixTheme.colorScheme.primary, fontSize = 14.sp)
                if (turn.answer.isNotEmpty()) MarkdownText(turn.answer, compact = true, allowImages = false)
                else if (state.busy && turn == state.turns.last()) Text("Đang suy nghĩ…", fontSize = 13.sp)
                turn.cards.forEach { card -> MusicAttachmentCard(card) { onCard(card) } }
            } }
        }
        if (state.turns.isEmpty()) MusicTextAction("Bối cảnh sáng tác của bài hát này là gì？", { controller.draft("Bối cảnh sáng tác của bài hát này là gì？") },
            Modifier.testTag("player_ai_suggestion"))
        state.error?.let { Text(it, color = MiuixTheme.colorScheme.error, fontSize = 12.sp) }
        TextField(state.draft, controller::draft, label = "Bạn muốn hỏi gì về bài hát này？",
            modifier = Modifier.fillMaxWidth().testTag("player_ai_input"), maxLines = 3)
        MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MusicDialogButton("Đóng", onDismiss, Modifier.weight(1f))
            MusicDialogButton(if (state.busy) "Đang trả lời…" else "Gửi", controller::ask, Modifier.weight(1f).testTag("player_ai_send"),
                enabled = !state.busy && state.draft.isNotBlank())
        }
    }
}
