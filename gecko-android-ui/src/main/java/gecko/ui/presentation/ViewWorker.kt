package gecko.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

internal interface ViewWorker<Model> {

    suspend fun execute(model: Model)

}

internal suspend fun <Model> Iterable<ViewWorker<Model>>.execute(model: Model) {
    forEach { it.execute(model) }
}

internal suspend fun <Model> Array<out ViewWorker<Model>>.execute(model: Model) {
    forEach { it.execute(model) }
}

@Composable
internal fun <Model> Worker(model: Model, vararg workers: ViewWorker<Model>) {
    LaunchedEffect(model) {
        workers.execute(model)
    }
}

@Composable
internal fun <Model : ViewModel> Worker(model: Model, vararg workers: ViewWorker<Model>) {
    DisposableEffect(model) {
        model.viewModelScope.launch {
            workers.execute(model)
        }
        // we've got nothing to dispose of here, since the viewModelScope will handle
        // the disposition as viewModel gets cleared, hopefully
        onDispose { /* no-op */ }
    }
}
