package gecko.ui.composition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import gecko.ui.presentation.Worker
import gecko.ui.screen.dashboard.DashboardContent
import gecko.ui.screen.dashboard.DashboardContentLoader
import gecko.ui.screen.dashboard.DashboardNameLoader
import gecko.ui.screen.dashboard.DashboardViewModel

@Composable
internal fun DashboardView(@Suppress("UNUSED_PARAMETER") entry: NavBackStackEntry) {
    val viewModel: DashboardViewModel = viewModel()
    val context = LocalContext.current
    DashboardContent(viewModel)
    Worker(
        viewModel,
        remember(viewModel, context) { DashboardContentLoader(context) },
        remember(viewModel, context) { DashboardNameLoader(context) }
    )
}