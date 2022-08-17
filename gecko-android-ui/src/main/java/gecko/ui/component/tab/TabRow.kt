package gecko.ui.component.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gecko.ui.theme.GeckoTheme

@Composable
internal fun GeckoTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            divider = {},
            indicator = {
                Box(
                    Modifier
                        .tabIndicatorOffset(it[selectedTabIndex])
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = MaterialTheme.shapes.small.copy(
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                )
            },
            tabs = tabs
        )
        Divider()
    }
}

@Preview
@Composable
private fun TabRowPreview() {
    GeckoTheme {
        var selectedPosition by remember { mutableStateOf(0) }
        GeckoTabRow(
            selectedTabIndex = selectedPosition,
            tabs = {
                GeckoTab(
                    text = "First",
                    selected = selectedPosition == 0,
                    onClick = { selectedPosition = 1 }
                )
                GeckoTab(
                    text = "Second",
                    selected = selectedPosition == 1,
                    onClick = { selectedPosition = 1 }
                )
                GeckoTab(
                    text = "Third",
                    selected = selectedPosition == 2,
                    onClick = { selectedPosition = 2 }
                )
            }
        )
    }
}