package gecko.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import gecko.ui.component.navigation.Destinations
import gecko.ui.presentation.SimplePresenter
import gecko.ui.presentation.ViewPresenter

class NavigationPresenter<Model>(
    private val router: SimplePresenter<NavGraphBuilder>
) : ViewPresenter<Model> {

    override fun present(model: Model): @Composable () -> Unit = {
        val controller = rememberNavController()
        val startDestination = Destinations.Dashboard()
        CompositionLocalProvider(LocalNavController provides controller) {
            NavHost(navController = controller, startDestination = startDestination) {
                router.present(model = this)
            }
        }
    }

}

val LocalNavController = compositionLocalOf<NavController> { throw IllegalStateException() }