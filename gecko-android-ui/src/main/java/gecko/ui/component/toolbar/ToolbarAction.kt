package gecko.ui.component.toolbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun ToolbarAction(
    onClick: () -> Unit,
    resourceId: Int,
    contentDescription: String = ""
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = resourceId),
            contentDescription = contentDescription
        )
    }
}