package gecko.ui.composition

import androidx.compose.runtime.Composable
import gecko.ui.component.navigation.Destinations
import gecko.ui.presentation.navigation.ScreenNavigation
import gecko.ui.presentation.navigation.composable
import gecko.ui.presentation.theme.SystemUIEffect
import gecko.ui.presentation.theme.ThemeEffect

@Composable
internal fun MainView() {
    SystemUIEffect()

    @Composable
    fun Navigation() = ScreenNavigation(
        startDestination = Destinations.Dashboard,
        screens = arrayOf(
            { composable(Destinations.Dashboard) { DashboardView(it) } },
            { composable(Destinations.CallDetail) { DetailView(it) } },
        )
    )

    @Composable
    fun Theme() = ThemeEffect {
        Navigation()
    }

    Theme()
}