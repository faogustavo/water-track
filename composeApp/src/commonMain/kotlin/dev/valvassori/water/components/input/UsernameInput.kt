package dev.valvassori.water.components.input

import androidx.compose.animation.AnimatedContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.username_input_label

@Composable
fun UsernameInput(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(Res.string.username_input_label)) },
        isError = errorMessage != null,
        supportingText = {
            AnimatedContent(errorMessage) {
                if (it != null) {
                    Text(it)
                }
            }
        },
        leadingIcon = {
            Icon(
                Icons.Outlined.Person,
                contentDescription = null,
            )
        },
        modifier = Modifier.then(modifier),
    )
}
