package gecko.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import gecko.android.model.GeckoMetadata
import gecko.ui.R
import gecko.ui.component.call.CallOverview
import gecko.ui.component.navigation.Destinations
import gecko.ui.component.toolbar.IconifiedToolbar
import gecko.ui.presentation.navigation.LocalNavController

@Composable
internal fun DashboardContent(viewModel: DashboardViewModel) {
    val controller = LocalNavController.current
    val appName by viewModel.appName.collectAsState()
    val items = viewModel.pagingData.collectAsLazyPagingItems()
    var topOffset by remember { mutableStateOf(0) }
    var bottomOffset by remember { mutableStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        DashboardList(
            padding = with(LocalDensity.current) {
                PaddingValues(top = topOffset.toDp(), bottom = bottomOffset.toDp())
            },
            items = items,
            onItemClick = { controller.navigate(Destinations.CallDetail(it.toString())) }
        )
        DashboardToolbar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface.copy(alpha = .9f))
                .onSizeChanged { topOffset = it.height },
            appName = appName,
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(16.dp)
                .onSizeChanged { bottomOffset = it.height },
            onClick = { viewModel.submitRefreshRequest() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_gecko_refresh),
                contentDescription = "Refresh"
            )
        }
    }
}

@Composable
private fun DashboardToolbar(
    appName: String,
    modifier: Modifier = Modifier
) {
    IconifiedToolbar(
        modifier = modifier,
        title = "Gecko!",
        subtitle = appName.takeUnless { it.isBlank() },
        icon = painterResource(id = R.mipmap.ic_launcher_gecko_foreground)
    )
}

@Composable
private fun DashboardList(
    padding: PaddingValues,
    items: LazyPagingItems<GeckoMetadata>,
    onItemClick: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = rememberInsetsPaddingValues(
            LocalWindowInsets.current.navigationBars,
            additionalTop = padding.calculateTopPadding(),
            additionalBottom = padding.calculateBottomPadding() + 16.dp,
            additionalStart = padding.calculateStartPadding(LocalLayoutDirection.current),
            additionalEnd = padding.calculateEndPadding(LocalLayoutDirection.current)
        )
    ) {
        items(items, key = { it.id }) {
            it ?: return@items
            CallOverview(
                modifier = Modifier
                    .clickable { onItemClick(it.id) }
                    .padding(horizontal = 16.dp),
                timestamp = it.createdAt,
                method = it.requestMethod,
                code = it.responseCode,
                url = it.requestUrl
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}