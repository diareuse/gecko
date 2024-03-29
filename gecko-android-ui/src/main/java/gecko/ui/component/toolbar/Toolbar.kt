package gecko.ui.component.toolbar

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import gecko.ui.R
import gecko.ui.theme.GeckoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(
    title: @Composable () -> Unit,
    behavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    navigationIconColor: Color = ToolbarDefaults.navigationIconColor,
    titleColor: Color = ToolbarDefaults.titleColor,
    actionsColor: Color = ToolbarDefaults.actionsColor,
    color: Color = ToolbarDefaults.color
) {
    LargeTopAppBar(
        title = title,
        modifier = modifier
            .background(color)
            .statusBarsPadding(),
        navigationIcon = navigationIcon,
        actions = actions,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = color,
            scrolledContainerColor = color,
            navigationIconContentColor = navigationIconColor,
            titleContentColor = titleColor,
            actionIconContentColor = actionsColor
        ),
        scrollBehavior = behavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberScrollBehavior(
    spec: DecayAnimationSpec<Float> = rememberSplineBasedDecay(),
    state: TopAppBarState = rememberTopAppBarState(),
    canScroll: () -> Boolean
) = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
    flingAnimationSpec = spec,
    canScroll = canScroll,
    state = state
)

val LazyListState.canScroll
    get() = layoutInfo.totalItemsCount > layoutInfo.visibleItemsInfo.size

// ---

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ToolbarPreview() {
    GeckoTheme {
        Toolbar(
            title = {
                Text(text = "Woo-hoo!")
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gecko_back),
                        contentDescription = ""
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gecko_copy),
                        contentDescription = ""
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gecko_copy),
                        contentDescription = ""
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gecko_copy),
                        contentDescription = ""
                    )
                }
            },
            behavior = rememberScrollBehavior { false }
        )
    }
}