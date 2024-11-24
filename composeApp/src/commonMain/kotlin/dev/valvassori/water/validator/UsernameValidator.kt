package dev.valvassori.water.validator

interface UsernameValidator {
    fun validate(username: String): ValidationState

    sealed class ValidationState {
        val isValid: Boolean
            get() = this is Valid

        data object Valid : ValidationState()

        data object Empty : ValidationState()

        data object TooShort : ValidationState()

        data object TooLong : ValidationState()

        data object InvalidCharacters : ValidationState()
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
