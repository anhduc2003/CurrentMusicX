package io.github.currencortex.music.feature.settings

import io.github.currencortex.music.ui.component.nativeui.*

import io.github.currencortex.music.ui.component.nativeui.SettingsPage
import io.github.currencortex.music.ui.component.nativeui.SettingsUiScope
import io.github.currencortex.music.ui.component.nativeui.SettingsOptionsRow
import io.github.currencortex.music.ui.component.nativeui.SettingsOption

import android.media.AudioDeviceInfo
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.currencortex.music.AppContainer
import io.github.currencortex.music.core.media.outputKey
import io.github.currencortex.music.core.media.outputLabel
import io.github.currencortex.music.core.media.CurrentMusicAudioDiagnostics
import io.github.currencortex.music.data.settings.AudioOutputSettings
import io.github.currencortex.music.data.settings.AudioOutputMode
import io.github.currencortex.music.data.settings.AudioBufferPolicy
import io.github.currencortex.music.core.media.engine.BitPerfectEvidence
import io.github.currencortex.music.ui.component.*
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.*
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable fun AudioOutputScreen(container: AppContainer) {
    SettingsUiScope {
    val settings by container.audioOutput.state.collectAsStateWithLifecycle()
    val outputStatus by container.audioOutput.status.collectAsStateWithLifecycle()
    val diagnostics by container.audioDiagnostics.state.collectAsStateWithLifecycle()
    val devices by container.audioDevices.devices.collectAsStateWithLifecycle()
    val clipboard = LocalClipboardManager.current
    val context = LocalContext.current
    val manager = remember(context) { context.getSystemService(AudioManager::class.java) }
    val supportedRates = remember { AudioOutputSettings.rates.filter { it == 0 ||
        AudioTrack.getMinBufferSize(it, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT) > 0 } }
    val selectedDevice = devices.firstOrNull { it.outputKey() == settings.deviceKey }
    var bitPerfectDevices by remember { mutableStateOf("Đang đọc khả năng xuất bit-perfect qua USB…") }
    LaunchedEffect(devices, settings.deviceKey) {
        bitPerfectDevices = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            if (android.os.Build.VERSION.SDK_INT < 34) "Hệ thống hiện tại thấp hơn Android 14, không thể bật Bit-perfect"
            else {
                val usb = devices.filter { it.type == AudioDeviceInfo.TYPE_USB_DEVICE || it.type == AudioDeviceInfo.TYPE_USB_HEADSET }
                    .filter { settings.deviceKey.isEmpty() || it.outputKey() == settings.deviceKey }
                if (usb.isEmpty()) "Hiện chưa kết nối hoặc chưa chọn USB DAC"
                else usb.joinToString("\n") { device ->
                    CurrentMusicAudioDiagnostics.capabilities(manager, device)
                }
            }
        }
    }
    var error by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    var diagnosticMessage by remember { mutableStateOf<String?>(null) }
    suspend fun diagnosticReport(): String {
        val structured = container.audioDiagnostics.text()
        val capability = bitPerfectDevices
        return kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            "$structured\nTruy vấn khả năng hệ thống USB:\n$capability\n\nChẩn đoán gốc đầy đủ:\n${CurrentMusicAudioDiagnostics.fullSnapshot()}"
        }
    }
    val exportDiagnostics = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("text/plain")) { uri ->
        if (uri != null) scope.launch {
            try {
                val report = diagnosticReport()
                kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                    requireNotNull(context.contentResolver.openOutputStream(uri)) { "Không thể mở tệp" }.bufferedWriter(Charsets.UTF_8).use { it.write(report) }
                }
                diagnosticMessage = "Đã xuất chẩn đoán đầy đủ"
            } catch (e: kotlinx.coroutines.CancellationException) { throw e }
            catch (_: Exception) { diagnosticMessage = "Xuất thất bại, vui lòng thử lại" }
        }
    }
    fun save(action: suspend () -> Unit) { scope.launch {
        try { action(); error = null } catch (e: kotlinx.coroutines.CancellationException) { throw e }
        catch (_: Exception) { error = "Lưu thất bại, vui lòng thử lại" }
    } }
    val hint = MiuixTheme.colorScheme.onSurfaceVariantSummary

    val modeDescriptions = mapOf(
        AudioOutputMode.AUDIO_TRACK to "Âm thanh hệ thống, hỗ trợ hiệu ứng âm thanh hệ thống",
        AudioOutputMode.OPENSL_ES to "Cố định OpenSL ES, định tuyến thiết bị theo hệ thống",
        AudioOutputMode.AAUDIO to "Cố định AAudio, xuất âm thanh độ trễ thấp nguyên bản",
        AudioOutputMode.OBOE to "Tự động chọn AAudio hoặc OpenSL ES",
        AudioOutputMode.DIRECT to "PCM 16 bit, yêu cầu AAudio độc quyền theo tần số lấy mẫu của nguồn âm thanh, bỏ qua hiệu ứng âm thanh và lấy mẫu lại",
        AudioOutputMode.BIT_PERFECT to "Android 14+ và USB DAC tương thích, giữ nguyên độ sâu bit, tần số lấy mẫu và số kênh của PCM đã giải mã",
        AudioOutputMode.NETEASE_USB to "Trình điều khiển USB Host tích hợp, cần cấp quyền độc quyền cho thiết bị; đầu ra thử nghiệm"
    )
    var capabilitiesOpen by remember { mutableStateOf(false) }
     SettingsPage("Đầu ra âm thanh", "audio_output_settings") {
         item("mode") { SettingsSection("Phương thức đầu ra", MusicIcons.Headphones) {
             SettingsOptionsRow("Chế độ đầu ra", settings.mode.label, AudioOutputMode.entries.map { mode ->
                SettingsOption("output_mode_${mode.name}", mode.label, settings.mode == mode,
                    summary = modeDescriptions.getValue(mode), onSelect = { save { container.audioOutput.setMode(mode) } })
            }, Modifier.testTag("output_mode"))
            SettingsDivider()
             SettingsPreference("Trạng thái hoạt động", outputStatus, Modifier.testTag("output_status"))
            diagnostics.fallbackReason?.let { reason ->
                SettingsHint(reason, Modifier.testTag("output_fallback_reason"))
                 SettingsPreference("Thử lại chế độ đầu ra đã chọn", "Kiểm tra lại thiết bị, quyền và định dạng", Modifier.testTag("output_retry"), onClick = {
                     diagnosticMessage = if (container.audioOutputRetry.tryEmit(Unit)) "Đã yêu cầu thử lại chế độ đầu ra" else "Đang xử lý, vui lòng thử lại sau"
                })
            }
            if (settings.mode == AudioOutputMode.BIT_PERFECT)
                SettingsHint(bitPerfectDevices, Modifier.testTag("bit_perfect_capability"))
        } }
         item("format") { SettingsSection("Định dạng âm thanh", MusicIcons.File) {
             SettingsOptionsRow("Tần số lấy mẫu đầu ra", if (settings.mode.bypassProcessing || settings.sampleRate == 0) "Theo nguồn âm thanh" else "${settings.sampleRate} Hz",
                 supportedRates.map { rate -> SettingsOption("output_rate_$rate", if (rate == 0) "Theo nguồn âm thanh" else "$rate Hz",
                    settings.sampleRate == rate, onSelect = { save { container.audioOutput.setSampleRate(rate) } }) },
                Modifier.testTag("output_sample_rate"),
                summary = if (settings.mode.bypassProcessing) "${settings.mode.label} Sử dụng tần số lấy mẫu của nguồn âm thanh" else null,
                enabled = !settings.mode.bypassProcessing)
            SettingsDivider()
            SettingsOptionsRow("Chính sách bộ đệm", settings.bufferPolicy.label, AudioBufferPolicy.entries.map { policy ->
                SettingsOption("output_buffer_${policy.name}", policy.label, settings.bufferPolicy == policy,
                    summary = when (policy) {
                        AudioBufferPolicy.BALANCED -> "Cân bằng tốc độ bắt đầu phát và độ ổn định khi phát"
                        AudioBufferPolicy.RESPONSIVE -> "Bắt đầu phát nhanh hơn, giảm bộ đệm trước"
                        AudioBufferPolicy.RESILIENT -> "Tăng bộ đệm trước để ứng phó với biến động mạng"
                    }, onSelect = { save { container.audioOutput.setBufferPolicy(policy) } })
            }, Modifier.testTag("output_buffer_policy"), summary = "Điều chỉnh bộ đệm giải mã và mạng; bộ đệm thiết bị tùy thuộc đầu ra thực tế"
        } }
        if (settings.mode == AudioOutputMode.NETEASE_USB) item("usb") {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SettingsInfoHeader("Thiết bị đầu ra", MusicIcons.Device, horizontalInset = 0.dp)
                NeteaseUsbOutputSettings(settings) { key -> save { container.audioOutput.setUsbDevice(key) } }
            }
        } else item("device") { SettingsSection("Thiết bị đầu ra", MusicIcons.Device) {
            SettingsOptionsRow("Thiết bị ưu tiên", selectedDevice?.outputLabel() ?: if (settings.deviceKey.isEmpty()) "Theo hệ thống" else "Thiết bị chưa kết nối",
                listOf(SettingsOption("output_device_system", "Theo hệ thống", settings.deviceKey.isEmpty(),
                    onSelect = { save { container.audioOutput.setDevice("") } })) + devices.map { device ->
                    SettingsOption("output_device_${device.id}", device.outputLabel(), settings.deviceKey == device.outputKey(),
                        onSelect = { save { container.audioOutput.setDevice(device.outputKey()) } })
                }, Modifier.testTag("output_device"))
        } }
        item("live_diagnostics") { SettingsSection("Chẩn đoán phát nhạc", MusicIcons.Headphones) {
            val rows = listOf(
                "Chế độ yêu cầu của engine" to diagnostics.requestedMode.label,
                "Backend âm thanh thực tế" to (diagnostics.backend ?: "Hệ thống không cung cấp"),
                "Trạng thái phát" to if (diagnostics.playing) "Đang phát" else "Đã tạm dừng hoặc không phát",
                "Thông tin âm thanh đầu vào" to diagnostics.source.describe(),
                "PCM sau giải mã" to diagnostics.decoded.describe(),
                "Tần số lấy mẫu đầu ra được yêu cầu" to (diagnostics.requestedRate?.let { "$it Hz" } ?: "Theo nguồn âm thanh"),
                "Định dạng đầu ra có thể xác minh" to diagnostics.output.describe(),
                "Bằng chứng về định dạng đầu ra" to diagnostics.formatEvidence,
                "Thiết bị đầu ra hiện tại" to ((diagnostics.device ?: "Hệ thống không cung cấp") + (diagnostics.deviceId?.let { " · ID $it" } ?: "")),
                "Khả năng USB DAC được khai báo" to (diagnostics.usbCapabilities ?: "Hệ thống không cung cấp"),
                "Khung bộ đệm phần mềm / thiết bị" to "${diagnostics.softwareBufferFrames ?: "Hệ thống không cung cấp"} / ${diagnostics.deviceBufferFrames ?: "Hệ thống không cung cấp"}",
                "Dung lượng bộ đệm thiết bị" to (diagnostics.capacityFrames?.let { "$it khung" } ?: "Hệ thống không cung cấp"),
                "Số lần thiếu dữ liệu bộ đệm" to (diagnostics.underruns?.toString() ?: "Hệ thống không cung cấp"),
                "Độ trễ đầu ra" to ((diagnostics.latencyMs?.let { "%.2f ms".format(java.util.Locale.ROOT, it) } ?: "Không thể xác minh") + " · ${diagnostics.latencyEvidence}"),
                "DSP được yêu cầu" to diagnostics.requestedEffects.joinToString("、").ifBlank { "Không có" },
                "DSP đã quan sát" to diagnostics.appliedEffects.joinToString("、").ifBlank { "Không quan sát thấy xử lý" },
                "Chiến lược xử lý" to (diagnostics.processingBypass ?: "Chuỗi xử lý bình thường"),
                "Trạng thái xác minh Bit-perfect" to when (diagnostics.bitPerfect) {
                    BitPerfectEvidence.INACTIVE -> "Chưa bật"
                    BitPerfectEvidence.REQUESTED -> "Đã yêu cầu, đang chờ xác minh"
                    BitPerfectEvidence.ANDROID_CONFIGURATION_VERIFIED -> "Đã xác minh cấu hình định tuyến và định dạng Android, chưa xác minh phần cứng cuối"
                    BitPerfectEvidence.UNVERIFIED -> "Không thể xác minh"
                    BitPerfectEvidence.FALLBACK -> "Đã chuyển về đường dẫn tiêu chuẩn"
                },
                "Định dạng mã hóa Bluetooth hiện tại" to "Hệ thống không cung cấp",
                "Độ trong suốt từng bit của DAC cuối" to "Chưa xác minh"
            )
            rows.forEachIndexed { index, (title, value) ->
                if (index > 0) SettingsDivider()
                SettingsPreference(title, value, Modifier.testTag("audio_diagnostic_$index"))
            }
        } }
        item("diagnostics") { SettingsSection("Thiết bị và chẩn đoán", MusicIcons.Info) {
            SettingsPreference("Khả năng do thiết bị báo cáo", if (capabilitiesOpen) "Thu gọn thông tin thiết bị" else "Tốc độ lấy mẫu và cấu hình ưu tiên của hệ thống",
                Modifier.testTag("output_capabilities"), onClick = { capabilitiesOpen = !capabilitiesOpen },
                trailing = { Icon(MusicIcons.Forward, null, Modifier.size(18.dp).rotate(if (capabilitiesOpen) 90f else 0f)) })
            androidx.compose.animation.AnimatedVisibility(capabilitiesOpen) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Tốc độ lấy mẫu ưu tiên của hệ thống: ${manager.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE)?.plus(" Hz") ?: "Không được báo cáo"}")
                    Text("Giá trị mặc định của hệ thống không đại diện cho tốc độ lấy mẫu thực tế của AudioTrack hoặc DAC.", fontSize = 13.sp, color = hint)
                    (selectedDevice?.let(::listOf) ?: devices).forEach { device ->
                        Text(device.outputLabel())
                        Text(if (device.sampleRates.isEmpty()) "Không giới hạn tốc độ lấy mẫu" else device.sampleRates.sorted().joinToString(" / ") { "$it Hz" },
                            fontSize = 13.sp, color = hint)
                    }
                }
            }
            SettingsDivider()
            SettingsPreference("Sao chép chẩn đoán âm thanh", "Trạng thái trực tiếp và nhật ký gốc đầy đủ",
                Modifier.testTag("copy_audio_diagnostics"), onClick = {
                    scope.launch {
                        try {
                            clipboard.setText(AnnotatedString(diagnosticReport()))
                            diagnosticMessage = "Đã sao chép đầy đủ thông tin chẩn đoán âm thanh"
                        } catch (e: kotlinx.coroutines.CancellationException) { throw e }
                        catch (_: Exception) { diagnosticMessage = "Sao chép thất bại, vui lòng thử lại" }
                    }
                })
            SettingsDivider()
            SettingsPreference("Xuất đầy đủ thông tin chẩn đoán âm thanh", "Lưu nhật ký đầy đủ để chia sẻ và khắc phục sự cố",
                Modifier.testTag("export_audio_diagnostics"), onClick = { exportDiagnostics.launch("CurrentMusicAudio.txt") })
            diagnosticMessage?.let { SettingsHint(it) }
        } }
        error?.let { message -> item { SettingsHint(message, error = true) } }
    }
    }
}
