package dev.valvassori.water.validator

interface PasswordValidator {
    fun validate(password: String): ValidationState

    sealed class ValidationState {
        val isValid: Boolean
            get() = this is Valid

        data object Valid : ValidationState()

        data object Empty : ValidationState()

        data object TooShort : ValidationState()
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
