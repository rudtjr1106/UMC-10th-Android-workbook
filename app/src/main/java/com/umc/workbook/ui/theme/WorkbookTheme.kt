package com.umc.workbook.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val WorkbookColorScheme = lightColorScheme(
    background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black,
    primary = Black,
    onPrimary = White
)

@Composable
fun WorkbookTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = WorkbookColorScheme,
        content = content
    )
}
