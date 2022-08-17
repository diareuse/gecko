package gecko.ui.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gecko.ui.component.color.ColorResolver
import gecko.ui.component.color.rememberColorOnColorResolver
import gecko.ui.theme.GeckoTheme

@Composable
internal fun GradientHeadlineContainer(
    startColor: Color,
    endColor: Color,
    headlineLeft: @Composable () -> Unit,
    headlineRight: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    resolver: ColorResolver<Color> = rememberColorOnColorResolver(),
    content: @Composable () -> Unit
) {
    val gradient = Brush.horizontalGradient(listOf(startColor, endColor))
    Column(
        modifier = modifier
            .background(gradient, shape)
            .clip(shape)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CompositionLocalProvider(
                LocalContentColor provides resolver.getColor(startColor),
                content = headlineLeft
            )
            Spacer(modifier = Modifier.weight(1f))
            CompositionLocalProvider(
                LocalContentColor provides resolver.getColor(endColor),
                content = headlineRight
            )
        }
        Box(Modifier.padding(start = 4.dp, bottom = 4.dp, end = 4.dp)) {
            content()
        }
    }
}

@Preview
@Composable
private fun GradientHeadlineContainerPreview() {
    GeckoTheme {
        GradientHeadlineContainer(
            startColor = MaterialTheme.colorScheme.primaryContainer,
            endColor = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.medium,
            headlineLeft = { Text(text = "Left") },
            headlineRight = { Text(text = "Right") }) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Spacer(
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}