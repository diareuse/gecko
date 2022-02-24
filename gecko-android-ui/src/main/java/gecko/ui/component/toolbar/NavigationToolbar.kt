package gecko.ui.component.toolbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.insets.statusBarsPadding
import gecko.ui.R

@Composable
fun NavigationToolbar(
    title: String,
    icon: Painter = painterResource(id = R.drawable.ic_gecko_back),
    onNavigationClick: () -> Unit,
    actions: @Composable () -> Unit = {}
) {
    Toolbar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(painter = icon, contentDescription = "Navigate Back")
            }
        },
        actions = actions,
        color = Color.Transparent
    )
}