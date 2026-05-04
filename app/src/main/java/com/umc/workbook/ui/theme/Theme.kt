package com.umc.workbook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Migrated from Theme.Material3.DayNight.NoActionBar (themes.xml + colors.xml)
// Light: black/white base, gray100 for inactive elements, gray000 for surface variants
private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = Black,
    onPrimaryContainer = White,
    background = White,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    onSurfaceVariant = Gray100,   // bottom nav unselected (was @color/gray100)
    surfaceVariant = Gray000,
    outline = Gray100,
)

// Dark scheme inverts primary/background; gray values are reused as-is
private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = Gray100,
    onPrimaryContainer = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    onSurfaceVariant = Gray100,
    surfaceVariant = Gray000,
    outline = Gray100,
)

@Composable
fun WorkbookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
