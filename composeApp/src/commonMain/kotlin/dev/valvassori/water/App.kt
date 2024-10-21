package dev.valvassori.water

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.valvassori.water.screen.LoginScreen
import dev.valvassori.water.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.back_button

@Composable
@Preview
fun App() {
    AppTheme(
        materialYou = false, // TODO: Move to flag
    ) {
        Navigator(LoginScreen) { navigator ->
            Scaffold {
                Surface(modifier = Modifier.padding(it)) {
                    SlideTransition(navigator)
                    BackButton(navigator)
                }
            }
        }
    }
}

@Composable
private fun BackButton(navigator: Navigator) {
    AnimatedVisibility(
        visible = navigator.canPop,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        IconButton(onClick = { navigator.pop() }) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = stringResource(Res.string.back_button),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier =
                    Modifier
                        .size(24.dp)
                        .padding(top = 4.dp, start = 4.dp),
            )
        }
    }
}
