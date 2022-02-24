package gecko.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

class ViewPresenterModelEffect<Model>(
    private val presenter: SuspendingPresenter<Model>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        LaunchedEffect(model) {
            presenter.present(model).invoke()
        }
    }

}