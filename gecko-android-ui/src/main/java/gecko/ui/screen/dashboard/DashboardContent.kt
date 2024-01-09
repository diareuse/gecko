package gecko.ui.screen.dashboard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.input.nestedscroll.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import gecko.android.model.GeckoMetadata
import gecko.ui.R
import gecko.ui.component.call.CallOverview
import gecko.ui.component.navigation.Destinations
import gecko.ui.component.toolbar.Toolbar
import gecko.ui.component.toolbar.canScroll
import gecko.ui.component.toolbar.rememberScrollBehavior
import gecko.ui.presentation.navigation.LocalNavController

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun DashboardContent(viewModel: DashboardViewModel) {
    val controller = LocalNavController.current
    val appName by viewModel.appName.collectAsState()
    val items = viewModel.pagingData.collectAsLazyPagingItems()
    val state = rememberLazyListState()
    val behavior = rememberScrollBehavior { state.canScroll }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(behavior.nestedScrollConnection),
        topBar = {
            DashboardToolbar(
                appName = appName,
                behavior = behavior,
                modifier = Modifier,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(16.dp),
                onClick = { viewModel.submitRefreshRequest() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_gecko_refresh),
                    contentDescription = "Refresh"
                )
            }
        }
    ) {
        DashboardList(
            state = state,
            padding = it,
            items = items,
            onItemClick = { controller.navigate(Destinations.CallDetail(it.toString())) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardToolbar(
    appName: String,
    modifier: Modifier = Modifier,
    behavior: TopAppBarScrollBehavior
) {
    Toolbar(
        modifier = modifier,
        title = {
            var title = "Gecko!"
            if (appName.isNotBlank()) {
                title += " ($appName)"
            }
            Text(title)
        },
        behavior = behavior
    )
}

@Composable
private fun DashboardList(
    padding: PaddingValues,
    items: LazyPagingItems<GeckoMetadata>,
    onItemClick: (Long) -> Unit,
    state: LazyListState
) {
    LazyColumn(
        state = state,
        contentPadding = padding + PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items.itemCount, key = items.itemKey { it.id }) {
            val it = items[it] ?: return@items
            CallOverview(
                modifier = Modifier
                    .clickable { onItemClick(it.id) },
                timestamp = it.createdAt,
                method = it.requestMethod,
                code = it.responseCode,
                url = it.requestUrl
            )
        }
    }
}

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    val dir = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(dir) + other.calculateStartPadding(dir),
        end = calculateEndPadding(dir) + other.calculateEndPadding(dir),
        top = calculateTopPadding() + other.calculateTopPadding(),
        bottom = calculateTopPadding() + other.calculateTopPadding()
    )
}
