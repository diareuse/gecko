package gecko.ui.presentation

import androidx.annotation.CheckResult

interface SuspendingPresenter<Model> : Presenter<Model, suspend () -> Unit> {

    @CheckResult
    override fun present(model: Model): suspend () -> Unit

}