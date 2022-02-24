package gecko.ui.component.toolbar

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpacedComponents(
    left: @Composable RowScope.() -> Unit,
    right: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    spaceSize: Dp = 16.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        left()
        Spacer(modifier = Modifier.size(spaceSize))
        right()
    }
}