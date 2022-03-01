package gecko.ui.component.toolbar

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

internal object ToolbarDefaults {
    val navigationIconColor @Composable get() = LocalContentColor.current
    val titleColor @Composable get() = LocalContentColor.current
    val actionsColor @Composable get() = LocalContentColor.current
    val titleStyle @Composable get() = MaterialTheme.typography.titleMedium
    val shape @Composable get() = RectangleShape
    val elevation @Composable get() = 4.dp
    val color @Composable get() = MaterialTheme.colorScheme.surfaceVariant
}