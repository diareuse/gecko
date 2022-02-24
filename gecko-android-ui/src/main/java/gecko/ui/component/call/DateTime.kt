package gecko.ui.component.call

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gecko.ui.theme.GeckoTheme

@Composable
fun DateTime(
    time: String,
    date: String,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.End
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = time,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun DateTimePreview() {
    GeckoTheme {
        DateTime(time = "12:34", date = "Feb 1, 3045")
    }
}