package gecko.ui.screen.detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import gecko.android.model.GeckoData
import gecko.ui.R
import gecko.ui.component.call.CallOverview
import gecko.ui.component.tab.GeckoTab
import gecko.ui.component.tab.GeckoTabRow
import gecko.ui.component.toolbar.NavigationToolbar
import gecko.ui.component.toolbar.ToolbarAction
import gecko.ui.component.toolbar.canScroll
import gecko.ui.component.toolbar.rememberScrollBehavior
import gecko.ui.presentation.action.actionCopyUri
import gecko.ui.presentation.action.actionOpenUri
import gecko.ui.presentation.navigation.NavigationDefaults
import gecko.ui.presentation.unwrap
import org.json.JSONArray
import org.json.JSONObject

internal const val TabRequest = 0
internal const val TabResponse = 1

@Composable
internal fun rememberHeaders(
    metadata: GeckoData?,
    position: Int
) = remember(metadata, position) {
    when (position) {
        TabRequest -> metadata?.requestHeaders
        TabResponse -> metadata?.responseHeaders
        else -> emptySet()
    }.orEmpty()
}

private fun GeckoData.resolveRequestBody() = when (requestContentType) {
    "application/json" -> when {
        requestBody.startsWith("[") -> JSONArray(requestBody).toString(2)
        requestBody.startsWith("{") -> JSONObject(requestBody).toString(2)
        else -> requestBody
    }

    else -> requestBody
}

private fun GeckoData.resolveResponseBody() = when (responseContentType) {
    "application/json" -> when {
        responseBody.startsWith("[") -> JSONArray(responseBody).toString(2)
        responseBody.startsWith("{") -> JSONObject(responseBody).toString(2)
        else -> responseBody
    }

    else -> responseBody
}

@Composable
internal fun rememberBody(
    metadata: GeckoData?,
    position: Int
) = remember(metadata, position) {
    when (position) {
        TabRequest -> metadata?.resolveRequestBody()
        TabResponse -> metadata?.resolveResponseBody()
        else -> ""
    }.orEmpty()
}

@Composable
private fun rememberContentType(
    metadata: GeckoData?,
    position: Int
) = remember(metadata, position) {
    when (position) {
        TabRequest -> metadata?.requestContentType
        TabResponse -> metadata?.responseContentType
        else -> ""
    }.orEmpty()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailContent(viewModel: DetailViewModel) {
    val metadata by viewModel.metadata.collectAsState()
    var selectedTabPosition by rememberSaveable { mutableStateOf(0) }
    val state = rememberLazyListState()
    val behavior = rememberScrollBehavior { state.canScroll }

    @Composable
    fun Toolbar() = DetailToolbar(
        metadata = metadata,
        onCopyClick = actionCopyUri().unwrap(),
        onLinkClick = actionOpenUri().unwrap(),
        behavior = behavior
    )

    @Composable
    fun Overview(metadata: GeckoData) = Box(
        modifier = Modifier.padding(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        CallOverview(
            timestamp = metadata.createdAt,
            method = metadata.requestMethod,
            code = metadata.responseCode,
            url = metadata.requestUrl,
            maxLines = Int.MAX_VALUE
        )
    }

    @Composable
    fun Tabs() {
        DetailTabs(
            modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp),
            selectedTab = selectedTabPosition,
            onSelectedTabChange = { selectedTabPosition = it }
        )
    }

    @Composable
    fun Headers(headers: Set<String>) = TitledSection(title = "Headers") {
        PreformattedBody(
            modifier = Modifier.fillMaxWidth(),
            text = headers.joinToString(separator = "\n")
        )
    }

    @Composable
    fun ContentType(type: String) = TitledSection(title = "Content Type") {
        PreformattedBody(
            modifier = Modifier.fillMaxWidth(),
            text = type
        )
    }

    @Composable
    fun Body(body: String) = TitledSection(title = "Body") {
        PreformattedBody(
            modifier = Modifier.fillMaxWidth(),
            text = body
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(behavior.nestedScrollConnection),
        topBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.surface)) {
                Toolbar()

                val metadata = metadata
                if (metadata != null)
                    Overview(metadata)

                Tabs()
            }
        }
    ) {
        LazyColumn(
            state = state,
            contentPadding = it
        ) {
            item { Headers(rememberHeaders(metadata, selectedTabPosition)) }
            item { ContentType(rememberContentType(metadata, selectedTabPosition)) }
            item { Body(rememberBody(metadata, selectedTabPosition)) }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailToolbar(
    metadata: GeckoData?,
    onCopyClick: (Uri) -> Unit,
    onLinkClick: (Uri) -> Unit,
    onNavigationClick: () -> Unit = NavigationDefaults.backClick(),
    behavior: TopAppBarScrollBehavior
) {
    NavigationToolbar(
        title = "Detail",
        onNavigationClick = onNavigationClick,
        actions = {
            DetailToolbarActions(
                link = metadata?.link?.toUri() ?: return@NavigationToolbar,
                onCopyClick = onCopyClick,
                onLinkClick = onLinkClick
            )
        },
        behavior = behavior
    )
}

@Composable
private fun DetailToolbarActions(
    link: Uri,
    onCopyClick: (Uri) -> Unit,
    onLinkClick: (Uri) -> Unit
) = Row {
    ToolbarAction(
        onClick = { onLinkClick.invoke(link) },
        resourceId = R.drawable.ic_gecko_link
    )
    ToolbarAction(
        onClick = { onCopyClick.invoke(link) },
        resourceId = R.drawable.ic_gecko_copy
    )
}

@Composable
private fun DetailTabs(
    selectedTab: Int,
    onSelectedTabChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    GeckoTabRow(
        selectedTabIndex = selectedTab,
        modifier = modifier,
    ) {
        GeckoTab(
            text = "Request",
            onClick = { onSelectedTabChange(TabRequest) },
            selected = selectedTab == TabRequest
        )
        GeckoTab(
            text = "Response",
            onClick = { onSelectedTabChange(TabResponse) },
            selected = selectedTab == TabResponse
        )
    }
}

@Composable
internal fun TitledSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title.uppercase(),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
internal fun PreformattedBody(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = text,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
