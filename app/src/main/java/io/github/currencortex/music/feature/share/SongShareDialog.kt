package io.github.currencortex.music.feature.share

import io.github.currencortex.music.ui.component.MusicDialogActions
import io.github.currencortex.music.ui.component.MusicDialogButton
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import io.github.currencortex.music.ui.util.showMusicMessage
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.currencortex.music.ui.util.showMusicError
import io.github.currencortex.music.data.song.Song
import io.github.currencortex.music.ui.component.MusicDialog
import io.github.currencortex.music.ui.component.LocalDocumentRequests
import io.github.currencortex.music.ui.component.nativeui.SettingsSegmentedControl
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.CircularProgressIndicator
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable internal fun SongShareDialog(song: Song, onDismiss: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var style by rememberSaveable { mutableStateOf(SongShareStyle.SQUARE) }
    var attempt by remember(song) { mutableIntStateOf(0) }
    var saving by remember { mutableStateOf(false) }
    val documents = LocalDocumentRequests.current
    // A new state holder immediately hides the old file on a song/style/retry change.
    // Otherwise produceState retains its previous value until its new coroutine starts.
    val result by key(song, style, attempt) { produceState<Result<SongShareFile>?>(null) {
        value = null
        value = try { Result.success(SongShareFiles.create(context.applicationContext, song, style)) }
            catch (cancelled: CancellationException) { throw cancelled }
            catch (error: Exception) { Result.failure(error) }
    } }
    val poster = result?.getOrNull()
    fun message(text: String, error: Boolean = false) {
        if (error) context.showMusicError(text) else context.showMusicMessage(text)
    }
    val previewHeight = (LocalConfiguration.current.screenHeightDp * .42f).coerceIn(170f, 420f).dp
    MusicDialog("Chia sẻ bài hát", onDismiss) {
        Column(Modifier.fillMaxWidth().heightIn(max = (LocalConfiguration.current.screenHeightDp * .72f).dp)
            .verticalScroll(rememberScrollState()).testTag("song_share_dialog"),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SettingsSegmentedControl(SongShareStyle.entries.map { it.label }, style.ordinal,
                { style = SongShareStyle.entries[it] }, tag = "song_share_style")
             if (poster != null) AsyncImage(poster.file, "${poster.content.title} Ảnh áp phích chia sẻ",
                Modifier.fillMaxWidth().height(previewHeight).testTag("song_share_preview"), contentScale = ContentScale.Fit)
            else Box(Modifier.fillMaxWidth().height(previewHeight), contentAlignment = Alignment.Center) {
                if (result == null) Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    CircularProgressIndicator()
                    Text("Đang tạo áp phích…", fontSize = 13.sp)
                } else Text("Tạo áp phích thất bại, vui lòng thử lại", Modifier.testTag("song_share_error"))
            }
            if (poster != null && !poster.coverLoaded) Text("Ảnh bìa chưa tải, bạn có thể tạo lại áp phích", fontSize = 12.sp,
                color = MiuixTheme.colorScheme.onSurface.copy(alpha = .6f))
            MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MusicDialogButton("Lưu ảnh", onClick = {
                    if (poster != null) scope.launch {
                        saving = true
                        try { documents.exportFile(poster.file, poster.content.filename) }
                        catch (cancelled: CancellationException) { throw cancelled }
                        catch (_: Exception) { message("Không thể mở vị trí lưu", error = true) }
                        finally { saving = false }
                    }
                }, enabled = poster != null && !saving && !documents.busy, modifier = Modifier.weight(1f).testTag("song_share_save"))
                MusicDialogButton("Chia sẻ ảnh", onClick = {
                    if (poster != null) try { context.startActivity(Intent.createChooser(SongShareFiles.shareIntent(context, poster), "Chia sẻ áp phích bài hát")) }
                    catch (cancelled: CancellationException) { throw cancelled }
                    catch (_: Exception) { message("Không thể mở bảng chia sẻ, vui lòng lưu ảnh rồi chia sẻ", error = true) }
                }, enabled = poster != null, colors = ButtonDefaults.textButtonColors(
                    color = MiuixTheme.colorScheme.primary, textColor = MiuixTheme.colorScheme.onPrimary),
                    modifier = Modifier.weight(1f).testTag("song_share_send"))
            }
            MusicDialogActions(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MusicDialogButton(if (SongShareContent.from(song).publicUrl == null) "Sao chép thông tin bài hát" else "Sao chép liên kết", onClick = {
                    val content = SongShareContent.from(song)
                    context.getSystemService(ClipboardManager::class.java).setPrimaryClip(
                        ClipData.newPlainText("Chia sẻ bài hát", content.publicUrl ?: content.text))
                    message(if (content.publicUrl == null) "Đã sao chép thông tin bài hát" else "Đã sao chép liên kết")
                }, modifier = Modifier.weight(1f).testTag("song_share_copy"))
                if (result?.isFailure == true || poster?.coverLoaded == false) MusicDialogButton("Tạo lại", onClick = { attempt++ },
                    modifier = Modifier.weight(1f).testTag("song_share_retry"))
            }
        }
    }
}
