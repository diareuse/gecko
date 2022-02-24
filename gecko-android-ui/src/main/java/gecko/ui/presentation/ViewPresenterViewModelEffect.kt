package gecko.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewPresenterViewModelEffect<Model : ViewModel>(
    private val presenter: SuspendingPresenter<Model>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        DisposableEffect(model) {
            model.viewModelScope.launch {
                presenter.present(model).invoke()
            }
            // we've got nothing to dispose of here, since the viewModelScope will handle 
            // the disposition as viewModel gets cleared, hopefully
            onDispose { /* no-op */ }
        }
    }

}