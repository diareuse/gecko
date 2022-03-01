package gecko.ui.component.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

internal data class TabTextStylesDefault(
    private val selectedTextStyle: TextStyle,
    private val defaultTextStyle: TextStyle
) : TabTextStyles {

    @Composable
    override fun textStyle(selected: Boolean) = when (selected) {
        true -> selectedTextStyle
        false -> defaultTextStyle
    }

}
