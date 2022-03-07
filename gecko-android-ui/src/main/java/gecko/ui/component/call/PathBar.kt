package gecko.ui.component.call

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import gecko.ui.theme.GeckoTheme

private val Uri.pathWithQuery: String
    get() {
        val builder = StringBuilder(path.orEmpty())
        if (!encodedQuery.isNullOrEmpty()) {
            builder.append('?')
            builder.append(encodedQuery)
        }
        return builder.toString()
    }

@Composable
internal fun PathBar(
    uri: Uri,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    Row(modifier = modifier.height(IntrinsicSize.Max)) {
        Text(
            text = uri.pathWithQuery,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = maxLines.coerceAtLeast(1),
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun PathBarPreview() {
    GeckoTheme {
        Column {
            PathBar(uri = "https://google.com/v1/foo".toUri())
            PathBar(uri = "https://google.com/v1/foo?bar=foo".toUri())
            PathBar(uri = "https://google.com/v1/foo?bar=foo&barfoo=1".toUri())
        }
    }
}
