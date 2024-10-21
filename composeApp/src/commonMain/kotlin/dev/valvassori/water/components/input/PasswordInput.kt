package dev.valvassori.water.components.input

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.password_confirmation_input_label
import watertrack.composeapp.generated.resources.password_input_hide_password
import watertrack.composeapp.generated.resources.password_input_label
import watertrack.composeapp.generated.resources.password_input_show_password

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
    var isPasswordHidden by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            Icon(
                Icons.Outlined.Lock,
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                AnimatedContent(
                    targetState = isPasswordHidden,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                ) {
                    Icon(
                        imageVector = if (it) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription =
                            stringResource(
                                if (it) {
                                    Res.string.password_input_show_password
                                } else {
                                    Res.string.password_input_hide_password
                                },
                            ),
                    )
                }
            }
        },
        modifier = Modifier.then(modifier),
    )
}
