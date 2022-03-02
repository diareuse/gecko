package gecko.ui.component.call

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import gecko.ui.theme.GeckoTheme

@Composable
internal fun UrlBar(
    uri: Uri,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .background(
                LocalContentColor.current.copy(alpha = .1f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp, 0.dp),
        text = uri.host.orEmpty(),
        color = LocalContentColor.current.copy(alpha = .6f),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun UrlBarPreview() {
    GeckoTheme {
        UrlBar(uri = "https://google.com/v1/foo/bar".toUri())
    }
}