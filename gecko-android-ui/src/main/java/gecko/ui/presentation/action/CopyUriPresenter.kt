package gecko.ui.presentation.action

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import androidx.core.content.getSystemService
import gecko.ui.presentation.SimplePresenter

class CopyUriPresenter(
    private val context: Context
) : SimplePresenter<Uri> {

    override fun present(model: Uri) {
        val manager = context.getSystemService<ClipboardManager>() ?: return
        val data = ClipData.newRawUri("Gecko Link", model)
        manager.setPrimaryClip(data)
    }

}