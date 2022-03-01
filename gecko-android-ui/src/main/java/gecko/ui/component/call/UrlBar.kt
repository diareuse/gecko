package gecko.ui.component.call

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import gecko.ui.component.color.ColorResolver
import gecko.ui.component.color.rememberColorOnColorResolver
import gecko.ui.component.color.rememberHttpCodeColorResolver
import gecko.ui.component.color.rememberHttpMethodColorResolver
import gecko.ui.theme.GeckoTheme

@Composable
internal fun UrlBar(
    uri: Uri,
    method: String,
    code: Int,
    modifier: Modifier = Modifier,
    backgroundResolver: ColorResolver<String> = rememberHttpMethodColorResolver(),
    textColorResolver: ColorResolver<Color> = rememberColorOnColorResolver(),
    codeColorResolver: ColorResolver<Int> = rememberHttpCodeColorResolver()
) {
    Row(modifier = modifier) {
        val backgroundColor = backgroundResolver.getColor(method)
        val codeColor = codeColorResolver.getColor(code)
        Text(
            modifier = Modifier
                .background(codeColor, RoundedCornerShape(4.dp))
                .padding(4.dp, 0.dp),
            text = code.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Black,
            color = textColorResolver.getColor(codeColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(4.dp))
                .padding(4.dp, 0.dp),
            text = method,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Black,
            color = textColorResolver.getColor(backgroundColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier
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
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun UrlBarPreview() {
    GeckoTheme {
        UrlBar(uri = "https://google.com/v1/foo/bar".toUri(), method = "DELETE", code = 200)
    }
}