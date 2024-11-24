package dev.valvassori.water.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

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
    val ballsRange = (0..2).toSet()
    val ballSize = 12.dp
    val infiniteTransition = rememberInfiniteTransition("BouncingDots")
    val animators =
        ballsRange.map { index ->
            infiniteTransition.animateFloat(
                // Official API
                initialValue = 0.75f,
                targetValue = 1f,
                animationSpec =
                    infiniteRepeatable(
                        animation =
                            tween(
                                durationMillis = 100 * ballsRange.size,
                            ),
                        repeatMode = RepeatMode.Reverse,
                        initialStartOffset = StartOffset((1 + index) * 70, StartOffsetType.Delay), // Setting the Delay
                    ),
            )
        }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ballsRange.forEach { index ->
            val value = animators[index].value
            Box(
                modifier =
                    Modifier
                        .padding(horizontal = 2.dp)
                        .scale(value)
                        .size(12.dp)
                        .background(LocalContentColor.current, CircleShape),
            )
        }
    }
}
