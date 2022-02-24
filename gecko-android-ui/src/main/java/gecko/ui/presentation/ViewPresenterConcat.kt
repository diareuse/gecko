package gecko.ui.presentation

import androidx.compose.runtime.Composable

class ViewPresenterConcat<Model>(
    private vararg val presenters: ViewPresenter<Model>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        presenters.forEach {
            it.present(model).invoke()
        }
    }

}