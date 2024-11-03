package dev.valvassori.water.validator

import org.jetbrains.compose.resources.StringResource
import watertrack.composeapp.generated.resources.Res
import watertrack.composeapp.generated.resources.password_validation_empty
import watertrack.composeapp.generated.resources.password_validation_short

interface PasswordValidator {
    fun validate(password: String): ValidationState

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
                get() = Res.string.password_validation_empty
        }

        object TooShort : ValidationState(), Error {
            override val message: StringResource
                get() = Res.string.password_validation_short
        }
    }
}

class LoginPasswordValidator : PasswordValidator {
    override fun validate(password: String): PasswordValidator.ValidationState =
        when {
            password.isBlank() -> {
                PasswordValidator.ValidationState.Empty
            }

            password.length < 8 -> {
                PasswordValidator.ValidationState.TooShort
            }

            else -> PasswordValidator.ValidationState.Valid
        }
}
