package gecko.ui.component.tab

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

internal object TabDefaults {

    @Composable
    fun colors(
        selectedTextColor: Color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
        defaultTextColor: Color = LocalContentColor.current
    ): TabColors = TabColorsDefault(
        selectedTextColor = selectedTextColor,
        defaultTextColor = defaultTextColor
    )

    @Composable
    fun textStyles(
        selectedTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
        defaultTextStyle: TextStyle = MaterialTheme.typography.bodyMedium
    ): TabTextStyles = TabTextStylesDefault(
        selectedTextStyle = selectedTextStyle,
        defaultTextStyle = defaultTextStyle
    )

}