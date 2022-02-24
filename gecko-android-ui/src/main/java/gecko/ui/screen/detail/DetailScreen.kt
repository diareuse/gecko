package gecko.ui.screen.detail

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import gecko.ui.presentation.ViewPresenter

class DetailScreen(
    private val content: ViewPresenter<DetailViewModel>
) : ViewPresenter<NavBackStackEntry> {

    override fun present(model: NavBackStackEntry): @Composable () -> Unit = {
        val id = model.arguments?.getString("id")
            ?: throw IllegalStateException("Detail requires an \"id\" argument")
        val viewModel: DetailViewModel = viewModel(key = id, factory = DetailViewModelFactory(id))
        content.present(viewModel).invoke()
    }

    private class DetailViewModelFactory(
        private val id: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailViewModel(id) as T
        }
    }

}