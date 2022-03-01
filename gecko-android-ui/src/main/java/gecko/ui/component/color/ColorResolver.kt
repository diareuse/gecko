package gecko.ui.component.color

import androidx.compose.ui.graphics.Color

internal fun interface ColorResolver<Factor> {
    fun getColor(factor: Factor): Color
}