package dev.valvassori.water.screen.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun HomeTab() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.testTag("HomeScreen").fillMaxSize()) {
        Text("Home")
    }
}
