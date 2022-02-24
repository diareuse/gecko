package gecko.ui.presentation.action

import android.content.Context
import android.content.Intent
import android.net.Uri
import gecko.ui.presentation.SimplePresenter

class OpenUriPresenter(
    private val context: Context
) : SimplePresenter<Uri> {

    override fun present(model: Uri) {
        var intent = Intent(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .addCategory(Intent.CATEGORY_DEFAULT)
            .setData(model)

        intent = Intent.createChooser(intent, null)

        context.startActivity(intent)
    }

}