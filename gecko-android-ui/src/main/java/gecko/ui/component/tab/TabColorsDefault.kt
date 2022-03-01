package gecko.ui.component.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal data class TabColorsDefault(
    private val selectedTextColor: Color,
    private val defaultTextColor: Color
) : TabColors {

    @Composable
    override fun textColor(selected: Boolean) = when (selected) {
        true -> selectedTextColor
        false -> defaultTextColor
    }

}