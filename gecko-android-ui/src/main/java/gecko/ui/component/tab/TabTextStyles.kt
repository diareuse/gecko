package gecko.ui.component.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

interface TabTextStyles {

    @Composable
    fun textStyle(selected: Boolean): TextStyle

}