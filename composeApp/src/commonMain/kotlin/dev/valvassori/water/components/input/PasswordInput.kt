package dev.valvassori.water.components.input

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
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
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = PasswordInputDefaults.keyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier,
) = PasswordInputImpl(
    value = value,
    onValueChange = onValueChange,
    label = stringResource(Res.string.password_input_label),
    errorMessage = errorMessage,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    modifier = modifier,
)

@Composable
fun ConfirmPasswordInput(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = PasswordInputDefaults.keyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier,
) = PasswordInputImpl(
    value = value,
    onValueChange = onValueChange,
    label = stringResource(Res.string.password_confirmation_input_label),
    errorMessage = errorMessage,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    modifier = modifier,
)

@Composable
private fun PasswordInputImpl(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier,
) {
    var isPasswordHidden by remember { mutableStateOf(true) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        isError = errorMessage != null,
        supportingText = {
            AnimatedContent(errorMessage) {
                if (it != null) {
                    Text(it)
                }
            }
        },
        visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            Icon(
                Icons.Outlined.Lock,
                contentDescription = null,
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { isPasswordHidden = !isPasswordHidden },
                modifier = Modifier.testTag("PasswordVisibilityToggle"),
            ) {
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
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = Modifier.then(modifier),
    )
}

object PasswordInputDefaults {
    fun keyboardOptions(imeAction: ImeAction = ImeAction.Done) =
        KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        )
}
