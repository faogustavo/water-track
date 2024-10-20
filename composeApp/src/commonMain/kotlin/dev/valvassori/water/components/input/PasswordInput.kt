package dev.valvassori.water.components.input

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.password_confirmation_input_label
import watertrack.composeapp.generated.resources.password_input_label

@Composable
fun PasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) = PasswordInputImpl(
    value = value,
    onValueChange = onValueChange,
    label = stringResource(Res.string.password_input_label),
    modifier = modifier,
)

@Composable
fun ConfirmPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) = PasswordInputImpl(
    value = value,
    onValueChange = onValueChange,
    label = stringResource(Res.string.password_confirmation_input_label),
    modifier = modifier,
)

@Composable
private fun PasswordInputImpl(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = {
            Icon(
                Icons.Outlined.Lock,
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Outlined.Visibility,
                    contentDescription = null,
                )
            }
        },
        modifier = Modifier.then(modifier),
    )
}
