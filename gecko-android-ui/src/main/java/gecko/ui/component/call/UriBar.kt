package gecko.ui.component.call

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import gecko.ui.theme.GeckoTheme

@Composable
fun UriBar(
    uri: Uri,
    method: String,
    code: Int,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        UrlBar(uri = uri, method = method, code = code)
        Spacer(modifier = Modifier.height(4.dp))
        PathBar(uri = uri, maxLines = maxLines)
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun UriBarPreview() {
    GeckoTheme {
        UriBar(
            uri = "https://google.google.google.google.google.google.com/v1/foo/bar/foo/bar/foo/bar/foo/bar/foo/bar/foo/bar".toUri(),
            method = "DELETE",
            200
        )
    }
}