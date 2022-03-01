package gecko.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.color.MaterialColors

private val LightThemeColors = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    primaryContainer = light_primaryContainer,
    onPrimaryContainer = light_onPrimaryContainer,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    secondaryContainer = light_secondaryContainer,
    onSecondaryContainer = light_onSecondaryContainer,
    tertiary = light_tertiary,
    onTertiary = light_onTertiary,
    tertiaryContainer = light_tertiaryContainer,
    onTertiaryContainer = light_onTertiaryContainer,
    error = light_error,
    errorContainer = light_errorContainer,
    onError = light_onError,
    onErrorContainer = light_onErrorContainer,
    background = light_background,
    onBackground = light_onBackground,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    onSurfaceVariant = light_onSurfaceVariant,
    outline = light_outline,
    inverseOnSurface = light_inverseOnSurface,
    inverseSurface = light_inverseSurface,
    inversePrimary = light_inversePrimary,
)

private val DarkThemeColors = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    primaryContainer = dark_primaryContainer,
    onPrimaryContainer = dark_onPrimaryContainer,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    secondaryContainer = dark_secondaryContainer,
    onSecondaryContainer = dark_onSecondaryContainer,
    tertiary = dark_tertiary,
    onTertiary = dark_onTertiary,
    tertiaryContainer = dark_tertiaryContainer,
    onTertiaryContainer = dark_onTertiaryContainer,
    error = dark_error,
    errorContainer = dark_errorContainer,
    onError = dark_onError,
    onErrorContainer = dark_onErrorContainer,
    background = dark_background,
    onBackground = dark_onBackground,
    surface = dark_surface,
    onSurface = dark_onSurface,
    surfaceVariant = dark_surfaceVariant,
    onSurfaceVariant = dark_onSurfaceVariant,
    outline = dark_outline,
    inverseOnSurface = dark_inverseOnSurface,
    inverseSurface = dark_inverseSurface,
    inversePrimary = dark_inversePrimary,
)

@Composable
internal fun GeckoTheme(content: @Composable () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        HarmonizedTheme(content = content)
    } else {
        BasicTheme(content = content)
    }
}

// ---

@Composable
private fun BasicTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        !useDarkTheme -> LightThemeColors
        else -> DarkThemeColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        content = content
    )
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
private fun HarmonizedTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamic: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (isDynamic) {
        val context = LocalContext.current
        if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (useDarkTheme) DarkThemeColors else LightThemeColors
    }

    val colorsWithHarmonizedError = setupErrorColors(colors, !useDarkTheme)

    MaterialTheme(
        colorScheme = colorsWithHarmonizedError,
        typography = AppTypography,
        content = content
    )
}

// ---

private fun setupErrorColors(colorScheme: ColorScheme, isLight: Boolean): ColorScheme {
    val harmonizedError =
        MaterialColors.harmonize(error.toArgb(), colorScheme.primary.toArgb())
    val roles = MaterialColors.getColorRoles(harmonizedError, isLight)
    //returns a colorScheme with newly harmonized error colors
    return colorScheme.copy(
        error = Color(roles.accent),
        onError = Color(roles.onAccent),
        errorContainer = Color(roles.accentContainer),
        onErrorContainer = Color(roles.onAccentContainer)
    )
}