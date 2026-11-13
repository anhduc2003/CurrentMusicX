package io.github.currencortex.music.feature.roaming

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.ui.component.*
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** The dialog previews choices; the embedded listening page applies them on tap. */
@Composable internal fun PersonalRoamingDialog(container: AppContainer, onDismiss: () -> Unit, onBinding: () -> Unit) {
    PersonalRoamingContent(container, onDismiss, onBinding)
}

@Composable internal fun PersonalRoamingPage(container: AppContainer, modifier: Modifier = Modifier,
    onPlay: () -> Unit, onBinding: () -> Unit, onDialogActive: (Boolean) -> Unit,
    header: @Composable () -> Unit) {
    PersonalRoamingContent(container, onPlay, onBinding, embedded = true, modifier = modifier,
        onDialogActive = onDialogActive, header = header)
}

@Composable private fun PersonalRoamingContent(container: AppContainer, onDismiss: () -> Unit,
    onBinding: () -> Unit, embedded: Boolean = false, modifier: Modifier = Modifier,
    onDialogActive: (Boolean) -> Unit = {}, header: @Composable () -> Unit = {}) {
    val station = container.personalRoaming
    val state by station.state.collectAsStateWithLifecycle()
    val account by container.neteaseSessions.state.collectAsStateWithLifecycle()
    var draftKey by rememberSaveable { mutableStateOf<String?>(null) }
    var submitted by remember { mutableStateOf<String?>(null) }
    var settings by remember { mutableStateOf(false) }
    val choice = if (embedded) state.choice else state.catalog.choices.find { it.key == draftKey } ?: state.choice
    val loggedIn = account.loggedIn && !account.stale
    val changed = !state.active || choice.key != state.choice.key
    val selectChoice: (io.github.currencortex.music.data.roaming.RoamingChoice) -> Unit = { selected ->
        if (!embedded) draftKey = selected.key
        else if (state.ready && !state.loading) {
            when {
                !loggedIn -> { onDismiss(); onBinding() }
                state.active && selected.key == state.choice.key && state.error == null -> onDismiss()
                else -> { submitted = selected.key; station.start(selected) }
            }
        }
    }
    val colors = if (embedded) MiuixTheme.colorScheme else musicDialogColors()
    val bodyHeight = (LocalConfiguration.current.screenHeightDp * .48f).dp
    LaunchedEffect(Unit) { station.loadCatalog() }
    LaunchedEffect(account.revision) { draftKey = null; submitted = null }
    LaunchedEffect(state.active, state.choice.key, state.loading, state.error) {
        if (submitted != null && state.active && !state.loading && state.error == null && submitted == state.choice.key) onDismiss()
        state.error?.let(container.errorNotices::show)
    }
    LaunchedEffect(settings) { onDialogActive(settings) }
    DisposableEffect(Unit) { onDispose { onDialogActive(false) } }
    SettingsUiScope {
    if (settings) MusicDialog("Cài đặt khám phá", { settings = false }, footer = {
        MusicDialogButton("Quay lại chọn chế độ", { settings = false }, Modifier.testTag("roaming_settings_done"))
    }) {
        SettingsSwitch("Tải trước đề xuất", state.settings.preload,
            { station.updateSettings(state.settings.copy(preload = it)) }, Modifier.testTag("roaming_preload"))
        SettingsSwitch("Khôi phục hàng đợi khi kết thúc", state.settings.restoreQueue,
            { station.updateSettings(state.settings.copy(restoreQueue = it)) }, Modifier.testTag("roaming_restore_queue"))
        Text(if (state.settings.restoreQueue) "Khôi phục hàng đợi và tiến trình trước đó sau khi kết thúc, đồng thời giữ trạng thái tạm dừng." else "Giữ lại bài hát và hàng đợi hiện tại sau khi kết thúc.",
            fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
    }
    val footer: @Composable () -> Unit = {
        val primary: @Composable (Modifier) -> Unit = { modifier ->
         MusicDialogButton(if (!loggedIn) "Đăng nhập Nhạc Vân NetEase" else if (state.loading) "Đang tìm đề xuất…"
             else if (changed) if (state.active) "Chuyển sang ${choice.title}" else "Bắt đầu khám phá"
             else if (state.error != null) "Thử lại đề xuất" else "Tiếp tục nghe",
            onClick = {
                when {
                    !loggedIn -> { onDismiss(); onBinding() }
                    changed -> { submitted = choice.key; station.start(choice) }
                    state.error != null -> { submitted = choice.key; station.retry() }
                    else -> onDismiss()
                }
            }, modifier.testTag(if (loggedIn) "roaming_start" else "roaming_login"),
            enabled = state.ready && !state.loading, cornerRadius = 14.dp,
            colors = ButtonDefaults.textButtonColors(color = colors.primary, textColor = colors.onPrimary))
        }
        if (state.active) MusicDialogActions {
             MusicDialogButton("Kết thúc khám phá", { station.stop(); onDismiss() },
                Modifier.weight(1f).testTag("roaming_stop"), cornerRadius = 14.dp,
                colors = ButtonDefaults.textButtonColors(textColor = colors.error))
            primary(Modifier.weight(1f))
        } else primary(Modifier.fillMaxWidth())
    }
    val body: @Composable () -> Unit = {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
             Text("Khám phá cá nhân", Modifier.weight(1f).testTag("music_dialog_title"),
                fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
             MusicTextAction("Cài đặt", { settings = true }, Modifier.testTag("roaming_settings"))
        }
        Column(Modifier.fillMaxWidth().then(if (embedded) Modifier else
            Modifier.heightIn(max = bodyHeight).verticalScroll(rememberScrollState()))
            .testTag("roaming_choices"), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SettingsGroup {
                state.catalog.modes.forEachIndexed { index, mode ->
                    SettingsChoiceRow(mode.title, choice.key == mode.key, { selectChoice(mode) },
                        Modifier.testTag("roaming_mode_${mode.code}"), summary = mode.subtitle,
                        enabled = state.ready && !state.loading)
                    if (index < state.catalog.modes.lastIndex) SettingsDivider()
                }
            }
             Text("Chế độ bối cảnh", Modifier.padding(top = 8.dp, bottom = 4.dp), fontSize = 13.sp, color = colors.onSurfaceVariantSummary)
            state.catalog.scenes.chunked(4).forEach { group ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    group.forEach { scene ->
                        val selected = choice.key == scene.key
                        Column(Modifier.weight(1f).clip(RoundedCornerShape(14.dp))
                            .background(if (selected) colors.primary.copy(alpha = .1f) else Color.Transparent)
                            .selectable(selected, enabled = state.ready && !state.loading, role = Role.RadioButton,
                                onClick = { selectChoice(scene) }).testTag("roaming_scene_${scene.code}")
                            .padding(horizontal = 2.dp, vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (scene.icon.isNotBlank()) AsyncImage(scene.icon, null, Modifier.size(24.dp),
                                colorFilter = ColorFilter.tint(if (selected) colors.primary else colors.onSurfaceVariantSummary))
                            else Icon(MusicIcons.Headphones, null, Modifier.size(24.dp), tint = colors.onSurfaceVariantSummary)
                            Text(scene.title, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
                                color = if (selected) colors.primary else colors.onSurface)
                        }
                    }
                    repeat(4 - group.size) { Spacer(Modifier.weight(1f)) }
                }
            }
             if (state.catalogLoading) Text("Đang làm mới bối cảnh…", fontSize = 12.sp, color = colors.onSurfaceVariantSummary)
             if (state.catalogError != null) MusicTextAction("Tải lại bối cảnh", station::loadCatalog, Modifier.testTag("roaming_reload_catalog"))
        }
    }
    if (embedded) Column(modifier.verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        header()
        SettingsGroup { Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            body()
            footer()
        } }
    } else if (!settings) MusicDialog(null, onDismiss, footer = { footer() }) { body() }

    }
}
