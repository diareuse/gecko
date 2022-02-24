package gecko.ui.presentation.navigation

import androidx.compose.runtime.Composable

object NavigationDefaults {

    @Composable
    fun backClick(): () -> Unit {
        val controller = LocalNavController.current
        return { controller.navigateUp() }
    }

}