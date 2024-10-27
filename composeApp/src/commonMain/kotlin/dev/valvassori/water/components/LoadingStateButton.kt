package dev.valvassori.water.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingStateButton(
    text: String,
    isLoading: Boolean,
    onClick: () -> Unit,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = { if (!isLoading) onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = colors,
        modifier = modifier,
    ) {
        AnimatedContent(isLoading, transitionSpec = { fadeIn() togetherWith fadeOut() }) {
            if (it) {
                LoadingIndicator()
            } else {
                Text(text)
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    var count by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        var goingUp = true
        while (true) {
            count += if (goingUp) 1 else -1

            if (count == 5) {
                goingUp = false
            } else if (count == 1) {
                goingUp = true
            }

            delay(150)
        }
    }

    Text(
        text = ".".repeat(count + 1),
        fontWeight = FontWeight.Black,
        modifier = Modifier.testTag("LoadingIndicator"),
    )
}
