package gecko.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

data class ExtendedColors(val colors: List<CustomColor>)

val initializeExtended = ExtendedColors(listOf())
val LocalExtendedColors = staticCompositionLocalOf {
    initializeExtended
}