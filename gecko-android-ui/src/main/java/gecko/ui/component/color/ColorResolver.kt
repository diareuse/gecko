package gecko.ui.component.color

import androidx.compose.ui.graphics.*

internal interface ColorResolver<Factor> {
    fun getColor(factor: Factor): Color
}

internal fun <Factor> ColorResolver(body: (Factor) -> Color) = object : ColorResolver<Factor> {
    override fun getColor(factor: Factor): Color {
        return body(factor)
    }
}