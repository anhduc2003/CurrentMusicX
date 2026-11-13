package io.github.currencortex.music.feature.about

import io.github.currencortex.music.ui.component.nativeui.SettingsSection
import io.github.currencortex.music.ui.component.nativeui.SettingsDivider
import io.github.currencortex.music.ui.component.MusicIcons

import io.github.currencortex.music.ui.component.nativeui.SettingsTopBar
import io.github.currencortex.music.ui.component.nativeui.SettingsUiScope

import io.github.currencortex.music.ui.component.musicScrollPadding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.currencortex.music.core.config.AppMetadata
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Text

enum class LegalDocument(val title: String, val asset: String) {
    LICENSE("Giấy phép mã nguồn mở", "legal/GPL-3.0.txt"),
    NOTICES("Thông báo của bên thứ ba", "legal/NOTICES.md"),
    APACHE("Apache License 2.0", "legal/APACHE-2.0.txt"),
    AGPL("GNU AGPL 3.0", "legal/AGPL-3.0.txt"),
    LGPL("GNU LGPL 3.0", "legal/LGPL-3.0.txt"),
    PRIVACY("Thông tin quyền riêng tư", "legal/PRIVACY.md"),
}

@Composable
fun LegalDocumentScreen(document: LegalDocument, onBack: () -> Unit, onOpenDocument: (LegalDocument) -> Unit) {
    SettingsUiScope {
    val context = LocalContext.current
    var text by remember(document) { mutableStateOf<String?>(null) }
    var error by remember(document) { mutableStateOf<String?>(null) }
    var attempt by remember(document) { mutableStateOf(0) }
    LaunchedEffect(document, attempt) {
        error = null
        try {
            text = withContext(Dispatchers.IO) {
                context.assets.open(document.asset).bufferedReader().use { it.readText() }
                    .replace("\r\n", "\n").replace('\r', '\n')
                    .replace("@@APP_NAME@@", AppMetadata.APP_NAME)
                    .replace("@@ISSUES_URL@@", AppMetadata.ISSUES_URL)
            }
        } catch (cancelled: CancellationException) { throw cancelled
        } catch (_: Exception) { error = "Không thể đọc tài liệu lúc này, vui lòng thử lại." }
    }
    val paragraphs = remember(text) { text?.split(Regex("\\n\\s*\\n")) ?: emptyList() }
    io.github.currencortex.music.ui.component.nativeui.SettingsScrollLayout(Modifier.fillMaxSize(),topBar = {
        SettingsTopBar(document.title,onBack)
    }) { topInset ->
    LazyColumn(Modifier.fillMaxSize().testTag("legal_document"), contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset+24.dp, 16.dp, 48.dp))) {
        if (document == LegalDocument.LICENSE) item {
            SettingsSection("Giấy phép thành phần", MusicIcons.File) {
            io.github.currencortex.music.ui.component.PageNavigationPreference(title = "Apache License 2.0",
                summary = "Giấy phép của các thành phần như MIUIX, AndroidX", onClick = { onOpenDocument(LegalDocument.APACHE) })
            io.github.currencortex.music.ui.component.PageNavigationPreference(title = "GNU LGPL 3.0",
                summary = "Giấy phép của thành phần gắn thẻ bài hát Jaudiotagger", onClick = { onOpenDocument(LegalDocument.LGPL) })
            SettingsDivider()
            io.github.currencortex.music.ui.component.PageNavigationPreference(title = "GNU AGPL 3.0",
summary = "Giấy phép của thành phần chuyển thể hoạt ảnh lời bài hát Folia Monet", onClick = { onOpenDocument(LegalDocument.AGPL) })            }
        }
        if (error != null) item { BasicComponent(title = error, summary = "Nhấn để thử lại", onClick = { attempt++ }) }
        else if (text == null) item { Text("Đang đọc…") }
        items(paragraphs) { paragraph ->
            val heading = paragraph.startsWith('#')
            SelectionContainer {
                Text(if (heading) paragraph.trimStart('#', ' ') else paragraph,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = if (heading) 20.sp else 14.sp,
                    fontWeight = if (heading) FontWeight.SemiBold else FontWeight.Normal,
                    fontFamily = if (document == LegalDocument.LICENSE || document == LegalDocument.APACHE) FontFamily.Monospace else FontFamily.Default)
            }
        }
    }
}

    }
}
