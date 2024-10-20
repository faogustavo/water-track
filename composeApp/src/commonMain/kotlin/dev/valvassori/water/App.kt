package dev.valvassori.water

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.valvassori.water.screen.CreateProfileScreen
import dev.valvassori.water.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme(
        materialYou = false, // TODO: Move to flag
    ) {
        Scaffold {
            Surface(modifier = Modifier.padding(it)) {
                CreateProfileScreen()
            }
        }
    }
}
