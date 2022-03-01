package gecko.ui.presentation.action

import android.content.ClipData
import android.content.ClipboardManager
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.getSystemService
import gecko.ui.presentation.CallableAction

@Composable
internal fun actionCopyUri(): CallableAction<Uri> {
    val context = LocalContext.current
    return CallableAction {
        val manager = context.getSystemService<ClipboardManager>() ?: return@CallableAction
        val data = ClipData.newRawUri("Gecko Link", it)
        manager.setPrimaryClip(data)
    }
}