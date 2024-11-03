package dev.valvassori.water.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

interface Tab {
    val title: String
        @Composable get

    val icon: Painter
        @Composable get
}
