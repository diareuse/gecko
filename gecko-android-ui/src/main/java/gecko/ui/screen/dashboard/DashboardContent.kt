package gecko.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import gecko.android.model.GeckoMetadata
import gecko.ui.R
import gecko.ui.component.call.CallOverview
import gecko.ui.component.navigation.Destinations
import gecko.ui.component.toolbar.IconifiedToolbar
import gecko.ui.presentation.dashboard.DashboardPresenter
import gecko.ui.presentation.navigation.LocalNavController

@OptIn(ExperimentalMaterial3Api::class)
class DashboardContent : DashboardPresenter {

    override fun present(model: DashboardViewModel): @Composable () -> Unit = {
        val controller = LocalNavController.current
        val appName by model.appName.collectAsState()
        Box {
            var offset by remember { mutableStateOf(0) }
            DashboardList(
                offset = offset,
                items = model.pagingData.collectAsLazyPagingItems(),
                onItemClick = { controller.navigate(Destinations.CallDetail(it.toString())) }
            )
            DashboardToolbar(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = .9f))
                    .onSizeChanged { offset = it.height },
                appName = appName
            )
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
        offset: Int,
        items: LazyPagingItems<GeckoMetadata>,
        onItemClick: (Long) -> Unit
    ) {
        LazyColumn(
            contentPadding = rememberInsetsPaddingValues(
                LocalWindowInsets.current.navigationBars,
                additionalTop = with(LocalDensity.current) { offset.toDp() }
            )
        ) {
            items(items) {
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

}