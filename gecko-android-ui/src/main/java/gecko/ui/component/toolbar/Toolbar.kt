package gecko.ui.component.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import gecko.ui.R
import gecko.ui.theme.GeckoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Toolbar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    navigationIconColor: Color = ToolbarDefaults.navigationIconColor,
    titleColor: Color = ToolbarDefaults.titleColor,
    actionsColor: Color = ToolbarDefaults.actionsColor,
    titleStyle: TextStyle = ToolbarDefaults.titleStyle,
    shape: Shape = ToolbarDefaults.shape,
    elevation: Dp = ToolbarDefaults.elevation,
    color: Color = ToolbarDefaults.color
) {
    Box(modifier = modifier) {
        Surface(
            shape = shape,
            tonalElevation = elevation,
            color = color
        ) {
            StyledToolbarLayout(
                navigationIcon = navigationIcon,
                title = title,
                actions = actions,
                navigationIconColor = navigationIconColor,
                titleColor = titleColor,
                actionsColor = actionsColor,
                titleStyle = titleStyle
            )
        }
    }
}

@Composable
private fun StyledToolbarLayout(
    navigationIcon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    navigationIconColor: Color = LocalContentColor.current,
    titleColor: Color = LocalContentColor.current,
    actionsColor: Color = LocalContentColor.current,
    titleStyle: TextStyle = MaterialTheme.typography.headlineMedium
) {
    ToolbarLayout(
        navigationIcon = {
            CompositionLocalProvider(LocalContentColor provides navigationIconColor) {
                navigationIcon()
            }
        },
        title = {
            ProvideTextStyle(value = titleStyle) {
                CompositionLocalProvider(LocalContentColor provides titleColor) {
                    title()
                }
            }
        },
        actions = {
            CompositionLocalProvider(LocalContentColor provides actionsColor) {
                actions()
            }
        }
    )
}

@Composable
@Suppress("unused", "UNUSED_PARAMETER")
private fun ToolbarLayout(
    navigationIcon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable () -> Unit
) {
    fun Placeable.centerYIn(containerHeight: Int): Int = (containerHeight - height) / 2
    fun Placeable.centerXIn(containerWidth: Int): Int = (containerWidth - width) / 2
    fun Placeable.startXIn(containerWidth: Int): Int = 0
    fun Placeable.endXIn(containerWidth: Int): Int = containerWidth - width

    Layout(
        content = {
            Box(modifier = Modifier.layoutId(ToolbarLayoutConstants.NavigationIcon)) {
                navigationIcon.invoke()
            }
            Box(modifier = Modifier.layoutId(ToolbarLayoutConstants.Title)) {
                title.invoke()
            }
            Box(modifier = Modifier.layoutId(ToolbarLayoutConstants.Actions)) {
                actions.invoke()
            }
        },
        measurePolicy = { measurables, constraints ->
            val navigationIconPlaceable = measurables
                .first { it.layoutId == ToolbarLayoutConstants.NavigationIcon }
                .measure(constraints)
            val actionsPlaceable = measurables
                .first { it.layoutId == ToolbarLayoutConstants.Actions }
                .measure(constraints)

            val titleMaxWidth =
                constraints.maxWidth - navigationIconPlaceable.width - actionsPlaceable.width
            val titlePlaceable = measurables
                .first { it.layoutId == ToolbarLayoutConstants.Title }
                .measure(constraints.copy(maxWidth = titleMaxWidth))

            val height = maxOf(
                navigationIconPlaceable.height,
                actionsPlaceable.height,
                titlePlaceable.height
            )

            layout(constraints.maxWidth, height) {
                navigationIconPlaceable.placeRelative(
                    x = navigationIconPlaceable.startXIn(constraints.maxWidth),
                    y = navigationIconPlaceable.centerYIn(height)
                )
                titlePlaceable.placeRelative(
                    x = titlePlaceable.centerXIn(constraints.maxWidth),
                    y = titlePlaceable.centerYIn(height)
                )
                actionsPlaceable.placeRelative(
                    x = actionsPlaceable.endXIn(constraints.maxWidth),
                    y = actionsPlaceable.centerYIn(height)
                )
            }
        }
    )
}

// ---

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
            }
        )
    }
}