package gecko.ui.component.toolbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import gecko.ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavigationToolbar(
    title: String,
    icon: Painter = painterResource(id = R.drawable.ic_gecko_back),
    onNavigationClick: () -> Unit,
    behavior: TopAppBarScrollBehavior,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Toolbar(
        title = {
            Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        behavior = behavior,
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(painter = icon, contentDescription = "Navigate Back")
            }
        },
        actions = actions,
        color = Color.Transparent
    )
}