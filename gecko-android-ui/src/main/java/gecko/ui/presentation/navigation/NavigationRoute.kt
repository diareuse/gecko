package gecko.ui.presentation.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import gecko.ui.component.navigation.Destination
import gecko.ui.presentation.SimplePresenter
import gecko.ui.presentation.ViewPresenter

class NavigationRoute(
    private val destination: Destination,
    private val screen: ViewPresenter<NavBackStackEntry>
) : SimplePresenter<NavGraphBuilder> {

    override fun present(model: NavGraphBuilder) {
        model.composable(
            route = destination.route,
            arguments = destination.namedArguments.toList()
        ) {
            screen.present(it).invoke()
        }
    }

}