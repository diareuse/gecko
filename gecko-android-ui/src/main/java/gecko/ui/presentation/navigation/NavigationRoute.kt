package gecko.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import gecko.ui.component.navigation.Destination

fun NavGraphBuilder.composable(
    destination: Destination,
    screen: @Composable (NavBackStackEntry) -> Unit
) = composable(
    route = destination.route,
    arguments = destination.namedArguments.toList(),
    content = screen
)