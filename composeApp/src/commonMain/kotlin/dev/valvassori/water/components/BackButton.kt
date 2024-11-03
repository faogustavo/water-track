package dev.valvassori.water.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.back_button

@Composable
fun BackButton(
    visible: Boolean,
    onClick: () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.testTag("Navigation.BackButton"),
        ) {
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
