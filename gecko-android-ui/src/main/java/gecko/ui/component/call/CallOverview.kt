package gecko.ui.component.call

import android.net.Uri
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gecko.ui.component.color.rememberHttpCodeColorResolver
import gecko.ui.component.color.rememberHttpMethodColorResolver
import gecko.ui.component.container.GradientHeadlineContainer
import gecko.ui.theme.GeckoTheme
import java.text.DateFormat
import java.util.*

@Composable
internal fun CallOverview(
    timestamp: Date,
    method: String,
    code: Int,
    url: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    val uri = remember(url) { Uri.parse(url) }
    val dateFormat = remember { DateFormat.getDateInstance() }
    val timeFormat = remember { DateFormat.getTimeInstance(DateFormat.SHORT) }
    val date = remember(timestamp) { dateFormat.format(timestamp) }
    val time = remember(timestamp) { timeFormat.format(timestamp) }
    val methodColorResolver = rememberHttpMethodColorResolver()
    val codeColorResolver = rememberHttpCodeColorResolver()

    GradientHeadlineContainer(
        modifier = modifier,
        startColor = methodColorResolver.getColor(method),
        endColor = codeColorResolver.getColor(code),
        headlineLeft = {
            HeadlineText(
                text = method,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        },
        shape = RoundedCornerShape(8.dp),
        headlineRight = {
            HeadlineText(
                text = code.toString(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Row(
                modifier = Modifier.padding(16.dp, 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                UriBar(
                    modifier = Modifier.weight(1f),
                    uri = uri,
                    maxLines = maxLines
                )
                DateTime(time = time, date = date)
            }
        }
    }
}

@Composable
internal fun HeadlineText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.padding(vertical = 4.dp),
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Black
    )
}

@Preview
@Composable
private fun CallOverviewPreview() {
    GeckoTheme {
        CallOverview(
            Date(),
            "DELETE",
            200,
            "https://google.com/v1/foo/bar/foo/foo/bar/foo/foo/bar/foo/foo/bar/foo"
        )
    }
}