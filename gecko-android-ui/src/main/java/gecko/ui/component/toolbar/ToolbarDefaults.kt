package gecko.ui.component.toolbar

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

internal object ToolbarDefaults {
    val navigationIconColor @Composable get() = LocalContentColor.current
    val titleColor @Composable get() = LocalContentColor.current
    val actionsColor @Composable get() = LocalContentColor.current
    val color @Composable get() = MaterialTheme.colorScheme.surface
}