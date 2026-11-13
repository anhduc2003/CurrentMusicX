package io.github.currencortex.music.feature.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import io.github.currencortex.music.data.profile.*
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun LevelsScreen(vm: LevelsViewModel, onBack: () -> Unit) {
    val state by vm.state.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(vm, lifecycle) { lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) { vm.poll() } }
    LevelsPage(state, onBack, vm::refresh, vm::startTrial, vm::answer, vm::submit, vm::closeTrial)
}

@Composable internal fun LevelsPage(state: LevelsState, onBack: () -> Unit, refresh: () -> Unit,
    startTrial: () -> Unit, answer: (Int, Int) -> Unit, submit: () -> Unit, closeTrial: () -> Unit) {
    val trial = state.trial
    val list = rememberLazyListState()
    LaunchedEffect(trial, state.signedIn) { list.scrollToItem(0) }
    BackHandler(trial != null) { closeTrial() }
    MusicSubpage(if (trial == null) "Cấp độ và phát triển" else "Thử thách cấp độ", "levels_screen",
        onBack = { if (trial != null) closeTrial() else onBack() }, actions = {
            if (trial == null && state.signedIn) MusicTextAction(if (state.loading) "Đang cập nhật" else "Làm mới", refresh,
                enabled = !state.loading && !state.busy)
        }) { topInset ->
        LazyColumn(Modifier.fillMaxSize().testTag("levels_list"), state = list, contentPadding = PaddingValues(
            start = 16.dp, end = 16.dp, top = topInset + 12.dp, bottom = LocalMusicBottomInset.current + 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (!state.signedIn) item { SettingsHint("Đăng nhập CurrentMusic để xem cấp độ và tiến độ phát triển") }
            if (state.loading && state.progress == null) item { SettingsHint("Đang lấy thông tin cấp độ…") }
            state.error?.let { error -> item { SettingsGroup {
                SettingsHint(error, error = true)
                if (trial == null) MusicTextAction("Thử lại", refresh, Modifier.padding(horizontal = 16.dp),
                    enabled = !state.loading && !state.busy)
            } } }
            if (trial != null) {
                item { SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Đã hoàn thành ${state.answers.count { it >= 0 }} / ${trial.questions.size} câu", fontWeight = FontWeight.SemiBold)
                    state.rules?.trial?.let { Text("Tối đa ${it.maxScore} điểm, đạt ${it.passScore} điểm là đỗ. Nộp bài xong có thể xem kết quả.", fontSize = 13.sp) }
                } } }
                itemsIndexed(trial.questions) { index, question -> SettingsGroup {
                    Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("${index + 1}. ${question.prompt}", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        question.options.forEachIndexed { option, text ->
                            val selected = state.answers.getOrNull(index) == option
                            Row(Modifier.fillMaxWidth().heightIn(min = 48.dp)
                                .selectable(selected, enabled = !state.busy, role = Role.RadioButton, onClick = { answer(index, option) })
                                .testTag("trial_answer_${index}_$option").padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(if (selected) "●" else "○", color = MiuixTheme.colorScheme.primary)
                                Text(text, Modifier.weight(1f), fontSize = 14.sp)
                            }
                        }
                    }
                } }
                item { MusicTextAction(if (state.busy) "Đang nộp…" else "Nộp câu trả lời", submit,
                    Modifier.fillMaxWidth().testTag("submit_level_trial"), enabled = state.canSubmit) }
            } else {
                val progress = state.progress
                val rules = state.rules
                if (progress != null) item { SettingsGroup {
                    Column(Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(userLevelLabel(progress.level).orEmpty(), Modifier.testTag("current_user_level"),
                            fontSize = 28.sp, fontWeight = FontWeight.SemiBold, color = MiuixTheme.colorScheme.primary)
                        Text("Tổng cộng ${progress.points} điểm", Modifier.testTag("current_user_points"), fontSize = 17.sp)
                        rules?.let {
                            val next = it.next(progress)
                            Text(if (progress.level == it.trial.plusLevel) "Đã vượt qua thử thách cấp độ"
                                else if (next == null) "Đã đạt cấp độ thông thường cao nhất, có thể tham gia thử thách cấp độ"
                                else "Còn ${(next.points - progress.points).coerceAtLeast(0)} điểm nữa để đạt ${userLevelLabel(next.level)}",
                                fontSize = 13.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                        }
                    }
                } }
                state.result?.let { result -> item { SettingsGroup {
                    SettingsHint("Điểm thử thách ${result.score} · ${if (result.passed) "Đã vượt qua" else "Chưa vượt qua, có thể thử lại"}",
                        modifier = Modifier.testTag("level_trial_result"))
                } } }
                if (rules != null && progress != null) {
                    item { SettingsListHeader("Phát triển hôm nay") }
                    item { SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Tối đa ${rules.dailyMaximum} điểm mỗi ngày", fontWeight = FontWeight.Medium)
                        Text("Ngày mới bắt đầu lúc 0 giờ ${if (rules.timeZone == "Asia/Shanghai") "giờ Bắc Kinh" else rules.timeZone}; hạn mức được dùng chung trên các thiết bị của cùng một tài khoản.", fontSize = 12.sp)
                        if (rules.pointSources.any { it.id == "listen" })
                            Text("Nghe hợp lệ từ ${rules.listenSeconds} giây trở lên để được tính điểm; mỗi bài hát chỉ được tính một lần mỗi ngày. Điểm sẽ cập nhật sau khi đồng bộ.", fontSize = 12.sp)
                    } } }
                    items(rules.pointSources, key = { "points-${it.id}" }) { source -> SettingsGroup {
                        SettingsPreference(pointSourceLabel(source.id),
                            "${source.points} điểm mỗi lần · Hôm nay ${progress.today[source.id] ?: 0} / ${source.dailyLimit} lần")
                    } }
                    item { SettingsListHeader("Quy tắc cấp độ") }
                    item { SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        rules.levels.sortedBy { it.level }.forEach { Text("${userLevelLabel(it.level)} · ${it.points} điểm", fontSize = 14.sp) }
                    } } }
                    item { SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Thử thách ${userLevelLabel(rules.trial.plusLevel)}", fontWeight = FontWeight.Medium)
                        Text("Đạt ${userLevelLabel(rules.trial.level)} để tham gia; gồm ${rules.trial.questionCount} câu, đạt ${rules.trial.passScore} điểm là đỗ.", fontSize = 13.sp)
                        MusicTextAction(if (state.busy) "Đang tải câu hỏi…" else "Bắt đầu thử thách", startTrial,
                            Modifier.testTag("start_level_trial"), enabled = state.canStartTrial)
                    } } }
                }
            }
        }
    }
}

private fun pointSourceLabel(id: String) = when (id) {
    "visit" -> "Truy cập hằng ngày"
    "listen" -> "Nghe nhạc"
    "post" -> "Đăng bài viết"
    "comment" -> "Bình luận bài viết của người khác"
    else -> id
}
