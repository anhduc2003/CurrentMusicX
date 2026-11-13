package io.github.currencortex.music.feature.youtube

import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import io.github.currencortex.music.ui.component.musicScrollPadding
import io.github.currencortex.music.ui.component.nativeui.*
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun YouTubeAccountScreen(vm: YouTubeAccountViewModel, onBack: () -> Unit) {
    val account by vm.account.collectAsStateWithLifecycle()
    val state by vm.state.collectAsStateWithLifecycle()
    var browser by remember { mutableStateOf(false) }
    var cookie by rememberSaveable { mutableStateOf("") }
    MusicSubpage("Tài khoản YouTube", "youtube_account_page", onBack, backTag = "youtube_account_back") { topInset ->
        LazyColumn(Modifier.fillMaxSize().testTag("youtube_account_screen"),
            contentPadding = musicScrollPadding(PaddingValues(16.dp, topInset + 8.dp, 16.dp, 32.dp)),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            item("account") {
                SettingsGroup(Modifier.fillMaxWidth().testTag("youtube_account_group")) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(if (account.loggedIn) "Đã đăng nhập YouTube" else "Chưa đăng nhập YouTube",
                            fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        Text("Đăng nhập tài khoản Google để phát video giới hạn độ tuổi, video riêng tư và tăng độ ổn định của luồng YouTube / YouTube Music.",
                            fontSize = 14.sp, lineHeight = 22.sp, color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                        if (account.loggedIn) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                                val avatar = account.profile?.avatar.orEmpty()
                                if (avatar.isNotBlank())
                                    AsyncImage(avatar, "Ảnh đại diện YouTube", Modifier.size(48.dp).clip(CircleShape),
                                        contentScale = ContentScale.Crop)
                                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                                    Text(account.profile?.name?.ifBlank { "Tài khoản YouTube" } ?: "Tài khoản YouTube",
                                        fontSize = 16.sp, fontWeight = FontWeight.Medium)
                                    Text(account.profile?.email.orEmpty(), fontSize = 13.sp,
                                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary)
                                }
                            }
                        }
                        GlassActionButton(if (account.loggedIn) "Đăng xuất YouTube" else "Đăng nhập bằng Google",
                            onClick = { if (account.loggedIn) vm.logout() else browser = true },
                            primary = !account.loggedIn, destructive = account.loggedIn,
                            enabled = !state.busy, modifier = Modifier.fillMaxWidth().testTag("youtube_login_action"))
                        if (!account.loggedIn) {
                            TextField(cookie, { cookie = it }, label = "Hoặc dán cookie youtube.com",
                                modifier = Modifier.fillMaxWidth().testTag("youtube_cookie_input"))
                            GlassActionButton("Dùng cookie đã dán", { vm.importCookies(cookie) },
                                enabled = cookie.isNotBlank() && !state.busy,
                                modifier = Modifier.fillMaxWidth().testTag("youtube_cookie_submit"))
                        }
                        state.message?.let { SettingsHint(it, error = state.failed) }
                    }
                }
            }
        }
    }
    if (browser) YouTubeLoginBrowser(onDismiss = { browser = false },
        onCookies = { jar -> browser = false; vm.login(jar) })
}

@Composable private fun YouTubeLoginBrowser(onDismiss: () -> Unit, onCookies: (Map<String, String>) -> Unit) {
    var reported by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false, decorFitsSystemWindows = false)) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.databaseEnabled = true
                settings.userAgentString = USER_AGENT
                val manager = CookieManager.getInstance()
                manager.setAcceptCookie(true)
                manager.setAcceptThirdPartyCookies(this, true)
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        if (reported) return
                        val jar = readCookies()
                        if (!jar["SAPISID"].isNullOrBlank() || !jar["__Secure-1PAPISID"].isNullOrBlank() ||
                            !jar["__Secure-3PAPISID"].isNullOrBlank()) {
                            reported = true
                            onCookies(jar)
                        }
                    }
                    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean = false
                }
                loadUrl(LOGIN_URL)
            }
        }, modifier = Modifier.fillMaxSize(), onRelease = { it.destroy() })
    }
}

private fun readCookies(): Map<String, String> {
    val manager = CookieManager.getInstance()
    manager.flush()
    val raw = listOf("https://music.youtube.com", "https://www.youtube.com", "https://accounts.google.com")
        .joinToString("; ") { manager.getCookie(it).orEmpty() }
    return YouTubeAccountViewModel.parseCookies(raw)
}

private const val LOGIN_URL = "https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fmusic.youtube.com&hl=vi"
private const val USER_AGENT = "Mozilla/5.0 (Linux; Android 14; Pixel 8) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Mobile Safari/537.36"