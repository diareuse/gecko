package gecko.ui.presentation.navigation

import androidx.compose.runtime.Composable

internal object NavigationDefaults {

    @Composable
    fun backClick(): () -> Unit {
        val controller = LocalNavController.current
        return { controller.navigateUp() }
    }

}