package gecko.ui.presentation.theme

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import gecko.ui.theme.GeckoTheme

@Composable
fun ThemeEffect(content: @Composable () -> Unit) {
    GeckoTheme {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
            content()
        }
    }
}