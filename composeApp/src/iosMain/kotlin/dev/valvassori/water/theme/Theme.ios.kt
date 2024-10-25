package dev.valvassori.water.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun buildColorScheme(
    darkTheme: Boolean,
    materialYou: Boolean,
): ColorScheme =
    when {
        darkTheme -> darkScheme
        else -> lightScheme
    }
