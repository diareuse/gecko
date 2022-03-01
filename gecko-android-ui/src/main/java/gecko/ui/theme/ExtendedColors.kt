package gecko.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf

internal data class ExtendedColors(val colors: List<CustomColor>)

internal val initializeExtended = ExtendedColors(listOf())
internal val LocalExtendedColors = staticCompositionLocalOf {
    initializeExtended
}