package gecko.ui.component.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

interface TabColors {

    @Composable
    fun textColor(selected: Boolean): Color

}