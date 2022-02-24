package gecko.ui.component.call

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import gecko.ui.component.color.ColorResolver
import gecko.ui.component.color.rememberHttpCodeColorResolver
import gecko.ui.theme.GeckoTheme

@Composable
fun PathBar(
    uri: Uri,
    code: Int,
    modifier: Modifier = Modifier,
    resolver: ColorResolver<Int> = rememberHttpCodeColorResolver()
) {
    Row(modifier = modifier.height(IntrinsicSize.Max)) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
                .background(resolver.getColor(code), RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = code.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = uri.path.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun LargePathBar(
    uri: Uri,
    code: Int,
    modifier: Modifier = Modifier,
    resolver: ColorResolver<Int> = rememberHttpCodeColorResolver()
) {
    Row(modifier = modifier.height(IntrinsicSize.Max)) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
                .background(resolver.getColor(code), RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = code.toString(),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = uri.path.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun PathBarPreview() {
    GeckoTheme {
        Column {
            PathBar(uri = "https://google.com/v1/foo".toUri(), code = 200)
            PathBar(uri = "https://google.com/v1/foo".toUri(), code = 300)
            PathBar(uri = "https://google.com/v1/foo".toUri(), code = 400)
            PathBar(uri = "https://google.com/v1/foo".toUri(), code = 500)
        }
    }
}


@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun LargePathBarPreview() {
    GeckoTheme {
        Column {
            LargePathBar(uri = "https://google.com/v1/foo".toUri(), code = 200)
            LargePathBar(uri = "https://google.com/v1/foo".toUri(), code = 300)
            LargePathBar(uri = "https://google.com/v1/foo".toUri(), code = 400)
            LargePathBar(uri = "https://google.com/v1/foo".toUri(), code = 500)
        }
    }
}