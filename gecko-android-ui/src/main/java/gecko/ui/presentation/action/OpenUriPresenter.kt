package gecko.ui.presentation.action

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import gecko.ui.presentation.CallableAction

@Composable
fun actionOpenUri(): CallableAction<Uri> {
    val context = LocalContext.current
    return CallableAction {
        var intent = Intent(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .addCategory(Intent.CATEGORY_DEFAULT)
            .setData(it)

        intent = Intent.createChooser(intent, null)

        context.startActivity(intent)
    }
}