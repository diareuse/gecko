package gecko.ui.component.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Tab(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    colors: TabColors = TabDefaults.colors(),
    styles: TabTextStyles = TabDefaults.textStyles()
) {
    Box(
        modifier = modifier.heightIn(min = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = styles.textStyle(selected = selected),
            color = colors.textColor(selected = selected)
        )
    }
}