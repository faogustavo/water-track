package dev.valvassori.water.validator

import org.jetbrains.compose.resources.StringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.username_validation_empty
import watertrack.composeapp.generated.resources.username_validation_invalid_char
import watertrack.composeapp.generated.resources.username_validation_long
import watertrack.composeapp.generated.resources.username_validation_short

interface UsernameValidator {
    fun validate(username: String): ValidationState

    sealed class ValidationState {
        private interface Error {
            val message: StringResource
        }

        val messageOrNull: StringResource?
            get() = if (this is Error) message else null

        val isValid: Boolean
            get() = this is Valid

        object Valid : ValidationState()

        object Empty : ValidationState(), Error {
            override val message: StringResource
                get() = Res.string.username_validation_empty
        }

        object TooShort : ValidationState(), Error {
            override val message: StringResource
                get() = Res.string.username_validation_short
        }

        object TooLong : ValidationState(), Error {
            override val message: StringResource
                get() = Res.string.username_validation_long
        }

        object InvalidCharacters : ValidationState(), Error {
            override val message: StringResource
                get() = Res.string.username_validation_invalid_char
        }
    }
}

class LoginUsernameValidator : UsernameValidator {
    override fun validate(username: String): UsernameValidator.ValidationState =
        when {
            username.isBlank() -> {
                UsernameValidator.ValidationState.Empty
            }

            username.length < 2 -> {
                UsernameValidator.ValidationState.TooShort
            }

            username.contains("&") -> {
                UsernameValidator.ValidationState.InvalidCharacters
            }

            else -> UsernameValidator.ValidationState.Valid
        }
}
