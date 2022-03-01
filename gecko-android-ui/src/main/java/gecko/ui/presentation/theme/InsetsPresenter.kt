package gecko.ui.presentation.theme

import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun InsetsEffect(content: @Composable () -> Unit) {
    ProvideWindowInsets(content = content)
}