package gecko.ui.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun SystemUIEffect() {
    val controller = rememberSystemUiController()
    val isDarkTheme = isSystemInDarkTheme()
    SideEffect {
        controller.setSystemBarsColor(Color.Transparent, darkIcons = !isDarkTheme)
    }
}