package gecko.ui.presentation.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import gecko.ui.presentation.ViewPresenter
import gecko.ui.theme.GeckoTheme

class ThemePresenter<Model>(
    private val presenter: ViewPresenter<Model>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        GeckoTheme {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
                presenter.present(model).invoke()
            }
        }
    }

}