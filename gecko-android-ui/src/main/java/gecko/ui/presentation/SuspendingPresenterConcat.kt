package gecko.ui.presentation

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class SuspendingPresenterConcat<Model>(
    private vararg val presenters: SuspendingPresenter<Model>
) : SuspendingPresenter<Model> {

    override fun present(model: Model): suspend () -> Unit = {
        coroutineScope {
            val jobs = mutableSetOf<Deferred<*>>()
            for (presenter in presenters)
                jobs += async { presenter.present(model).invoke() }
            jobs.awaitAll()
        }
    }

}