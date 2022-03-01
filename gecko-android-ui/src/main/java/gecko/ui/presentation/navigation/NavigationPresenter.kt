package gecko.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import gecko.ui.component.navigation.Destination

internal val LocalNavController =
    compositionLocalOf<NavController> { throw IllegalStateException() }

@Composable
internal fun ScreenNavigation(
    startDestination: Destination,
    vararg screens: NavGraphBuilder.() -> Unit
) {
    val controller = rememberNavController()
    CompositionLocalProvider(LocalNavController provides controller) {
        NavHost(navController = controller, startDestination = startDestination.route) {
            for (screen in screens)
                screen.invoke(this)
        }
    }
}