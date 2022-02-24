package gecko.ui.screen.detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import gecko.android.model.GeckoData
import gecko.ui.R
import gecko.ui.component.call.CallOverview
import gecko.ui.component.tab.Tab
import gecko.ui.component.tab.TabDefaults
import gecko.ui.component.tab.TabRow
import gecko.ui.component.toolbar.NavigationToolbar
import gecko.ui.presentation.SimplePresenter
import gecko.ui.presentation.detail.DetailPresenter
import gecko.ui.presentation.navigation.NavigationDefaults

@OptIn(ExperimentalMaterial3Api::class)
class DetailContent(
    private val link: SimplePresenter<Uri>,
    private val copy: SimplePresenter<Uri>
) : DetailPresenter {

    override fun present(model: DetailViewModel): @Composable () -> Unit = {
        val metadata by model.metadata.collectAsState()
        var selectedTabPosition by rememberSaveable { mutableStateOf(0) }
        LazyColumn(
            contentPadding = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.navigationBars)
        ) {
            item {
                DetailToolbar(
                    metadata = metadata,
                    onLinkClick = { link.present(it) },
                    onCopyClick = { copy.present(it) }
                )
            }

            val metadata = metadata
            if (metadata != null) item {
                Box(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                    CallOverview(
                        timestamp = metadata.createdAt,
                        method = metadata.requestMethod,
                        code = metadata.responseCode,
                        url = metadata.requestUrl
                    )
                }
            }

            item {
                DetailTabs(
                    modifier = Modifier.padding(top = 16.dp),
                    selectedTab = selectedTabPosition,
                    onSelectedTabChange = { selectedTabPosition = it }
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }


            metadata ?: return@LazyColumn
            val (headers, body) = when (selectedTabPosition) {
                0 -> metadata.run { requestHeaders to requestBody }
                1 -> metadata.run { responseHeaders to responseBody }
                else -> throw IllegalArgumentException()
            }

            item {
                TitledSection(title = "Headers") {
                    PreformattedBody(
                        modifier = Modifier.fillMaxWidth(),
                        text = headers.joinToString(separator = "\n")
                    )
                }
            }

            item {
                TitledSection(title = "Body") {
                    PreformattedBody(
                        modifier = Modifier.fillMaxWidth(),
                        text = body
                    )
                }
            }
        }
    }

}

@Composable
private fun DetailToolbar(
    metadata: GeckoData?,
    onCopyClick: (Uri) -> Unit,
    onLinkClick: (Uri) -> Unit,
    onNavigationClick: () -> Unit = NavigationDefaults.backClick()
) {
    NavigationToolbar(
        title = "",
        onNavigationClick = onNavigationClick,
        actions = {
            Row {
                IconButton(onClick = {
                    onLinkClick.invoke(metadata?.link?.toUri() ?: return@IconButton)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gecko_link),
                        contentDescription = "Visit Link"
                    )
                }
                IconButton(onClick = {
                    onCopyClick.invoke(metadata?.link?.toUri() ?: return@IconButton)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gecko_copy),
                        contentDescription = "Copy"
                    )
                }
            }
        }
    )
}

@Composable
private fun DetailTabs(
    selectedTab: Int,
    onSelectedTabChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        modifier = modifier.padding(horizontal = 16.dp),
        selectedPosition = selectedTab,
        indicator = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    )
            )
        }
    ) {
        Tab(
            modifier = Modifier
                .clickable { onSelectedTabChange(0) }
                .padding(16.dp, 4.dp),
            text = "Request",
            selected = selectedTab == 0,
            styles = TabDefaults.textStyles(
                selectedTextStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        )
        Tab(
            modifier = Modifier
                .clickable { onSelectedTabChange(1) }
                .padding(16.dp, 4.dp),
            text = "Response",
            selected = selectedTab == 1,
            styles = TabDefaults.textStyles(
                selectedTextStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        )
    }
}

@Composable
fun TitledSection(
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
fun PreformattedBody(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurface,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = text,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
