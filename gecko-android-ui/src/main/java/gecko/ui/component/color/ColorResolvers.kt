package gecko.ui.component.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

@Composable
internal fun rememberHttpCodeColorResolver() = remember {
    ColorResolver<Int> {
        when (it) {
            in 200..299 -> Color(0xFFB9F6CA)
            in 300..399 -> Color(0xFF84FFFF)
            in 400..499 -> Color(0xFFFFE57F)
            in 500..599 -> Color(0xFFFF8A80)
            else -> Color(0xFF000000)
        }
    }
}

@Composable
internal fun rememberHttpMethodColorResolver() = remember {
    ColorResolver<String> {
        when {
            "get".equals(it, true) -> Color(0xFF89D1B6)
            "head".equals(it, true) -> Color(0xFF5658D3)
            "post".equals(it, true) -> Color(0xFF4E9FFA)
            "put".equals(it, true) -> Color(0xFF9EA4B4)
            "delete".equals(it, true) -> Color(0xFFFD7D78)
            "connect".equals(it, true) -> Color(0xFFD68EF0)
            "options".equals(it, true) -> Color(0xFFE2CA8C)
            "trace".equals(it, true) -> Color(0xFF91E074)
            "patch".equals(it, true) -> Color(0xFF9F7685)
            else -> Color.Transparent
        }
    }
}

@Composable
internal fun rememberColorOnColorResolver() = remember {
    ColorResolver<Color> {
        if (it.luminance() <= 0.5f) Color.White
        else Color.Black
    }
}