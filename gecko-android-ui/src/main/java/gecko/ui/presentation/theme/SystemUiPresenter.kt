package gecko.ui.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import gecko.ui.presentation.ViewPresenter

class SystemUiPresenter<Model>(
    private val presenter: ViewPresenter<Model>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        val controller = rememberSystemUiController()
        val isDarkTheme = isSystemInDarkTheme()
        SideEffect {
            controller.setSystemBarsColor(Color.Transparent, darkIcons = !isDarkTheme)
        }
        presenter.present(model).invoke()
    }

}