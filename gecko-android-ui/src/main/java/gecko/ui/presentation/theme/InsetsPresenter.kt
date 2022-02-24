package gecko.ui.presentation.theme

import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets
import gecko.ui.presentation.ViewPresenter

class InsetsPresenter<Model>(
    private val presenter: ViewPresenter<Model>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        ProvideWindowInsets {
            presenter.present(model).invoke()
        }
    }

}