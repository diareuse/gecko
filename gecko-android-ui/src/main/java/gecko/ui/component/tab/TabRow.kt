package gecko.ui.component.tab

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gecko.ui.theme.GeckoTheme
import kotlin.math.max

@Composable
fun TabRow(
    selectedPosition: Int,
    modifier: Modifier = Modifier,
    indicator: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(8.dp)
                )
        )
    },
    content: @Composable () -> Unit
) {
    var indicatorOffset by remember { mutableStateOf(0) }
    val indicatorOffsetAnimated by animateIntAsState(targetValue = indicatorOffset)
    var indicatorWidth by remember { mutableStateOf(0) }
    val indicatorWidthAnimated by animateIntAsState(targetValue = indicatorWidth)
    Layout(
        modifier = modifier,
        content = {
            Box(
                modifier = Modifier
                    .layoutId("indicator")
                    .fillMaxSize()
            ) {
                indicator()
            }
            content()
        }
    ) { measurables, constraints ->
        var largestPlaceable = constraints.minHeight
        val placeables = mutableListOf<Placeable>()
        for (measurable in measurables) {
            if (measurable.layoutId == "indicator") continue
            val placeable = measurable.measure(constraints)
            placeables += placeable
            if (largestPlaceable < placeable.height) {
                largestPlaceable = placeable.height
            }
        }

        val height = max(constraints.minHeight, largestPlaceable)

        indicatorOffset = placeables.take(selectedPosition).sumOf { it.width }
        indicatorWidth = placeables[selectedPosition].width

        val indicatorPlaceable = measurables
            .first { it.layoutId == "indicator" }
            .measure(
                constraints.copy(
                    minWidth = indicatorWidthAnimated,
                    maxWidth = indicatorWidthAnimated,
                    minHeight = height,
                    maxHeight = height
                )
            )

        layout(constraints.maxWidth, height) {
            indicatorPlaceable.place(
                x = indicatorOffsetAnimated,
                y = (height - indicatorPlaceable.height) / 2
            )
            var offsetLeft = 0
            for (placeable in placeables) {
                placeable.place(
                    x = offsetLeft,
                    y = (height - placeable.height) / 2
                )
                offsetLeft += placeable.width
            }
        }
    }
}

@Preview
@Composable
private fun TabRowPreview() {
    GeckoTheme {
        var selectedPosition by remember { mutableStateOf(0) }
        TabRow(
            selectedPosition = selectedPosition,
            content = {
                Tab(
                    text = "First",
                    selected = selectedPosition == 0,
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .clickable { selectedPosition = 0 })
                Tab(
                    text = "Second",
                    selected = selectedPosition == 1,
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .clickable { selectedPosition = 1 })
                Tab(
                    text = "Third",
                    selected = selectedPosition == 2,
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                        .clickable { selectedPosition = 2 })
            },
            indicator = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            RoundedCornerShape(4.dp)
                        )
                )
            }
        )
    }
}