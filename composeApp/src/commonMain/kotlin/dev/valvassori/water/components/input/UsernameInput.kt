package dev.valvassori.water.components.input

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import org.jetbrains.compose.resources.stringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.username_input_label

@Composable
fun UsernameInput(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = UsernameInputDefaults.keyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it.trim().toLowerCase(Locale.current)) },
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
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = Modifier.then(modifier),
    )
}

object UsernameInputDefaults {
    fun keyboardOptions(imeAction: ImeAction = ImeAction.Next) =
        KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
        )
}
