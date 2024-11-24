package dev.valvassori.water.ext

import dev.valvassori.water.validator.PasswordValidator
import dev.valvassori.water.validator.UsernameValidator
import org.jetbrains.compose.resources.StringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.password_validation_empty
import watertrack.composeapp.generated.resources.password_validation_short
import watertrack.composeapp.generated.resources.username_validation_empty
import watertrack.composeapp.generated.resources.username_validation_invalid_char
import watertrack.composeapp.generated.resources.username_validation_long
import watertrack.composeapp.generated.resources.username_validation_short

val UsernameValidator.ValidationState.messageOrNull: StringResource?
    get() =
        when (this) {
            UsernameValidator.ValidationState.Empty -> Res.string.username_validation_empty
            UsernameValidator.ValidationState.InvalidCharacters -> Res.string.username_validation_invalid_char
            UsernameValidator.ValidationState.TooLong -> Res.string.username_validation_long
            UsernameValidator.ValidationState.TooShort -> Res.string.username_validation_short
            else -> null
        }

val PasswordValidator.ValidationState.messageOrNull: StringResource?
    get() =
        when (this) {
            PasswordValidator.ValidationState.Empty -> Res.string.password_validation_empty
            PasswordValidator.ValidationState.TooShort -> Res.string.password_validation_short
            else -> null
        }
