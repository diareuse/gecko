package gecko.ui.presentation

import androidx.annotation.CheckResult
import androidx.compose.runtime.Composable

interface ViewPresenter<Model> : Presenter<Model, @Composable () -> Unit> {

    @CheckResult
    override fun present(model: Model): @Composable () -> Unit

}