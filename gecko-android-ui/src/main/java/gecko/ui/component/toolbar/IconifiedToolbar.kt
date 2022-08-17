package gecko.ui.component.toolbar

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gecko.ui.R
import gecko.ui.theme.GeckoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun IconifiedToolbar(
    title: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    subtitle: String? = null
) {
    val spec = rememberSplineBasedDecay<Float>()
    val state = rememberTopAppBarState()
    val behavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(spec, state)
    LargeTopAppBar(
        modifier = modifier,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = icon,
                        contentDescription = null
                    )
                    Text(text = title)
                }
                if (subtitle != null) Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface
        ),
        scrollBehavior = behavior
    )
}

@Preview(showBackground = true)
@Composable
private fun IconifiedToolbarPreview() {
    GeckoTheme {
        IconifiedToolbar(
            title = "Gecko!",
            icon = painterResource(id = R.mipmap.ic_launcher_gecko_foreground)
        )
    }
}