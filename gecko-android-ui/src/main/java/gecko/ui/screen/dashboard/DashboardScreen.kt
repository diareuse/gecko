package gecko.ui.screen.dashboard

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import gecko.ui.presentation.ViewPresenter

class DashboardScreen(
    private val content: ViewPresenter<DashboardViewModel>
) : ViewPresenter<NavBackStackEntry> {

    override fun present(model: NavBackStackEntry): @Composable () -> Unit = {
        val viewModel: DashboardViewModel = viewModel()
        content.present(viewModel).invoke()
    }

}