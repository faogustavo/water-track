package dev.valvassori.water.error

sealed class LoginError {
    data object NetworkError : LoginError()

    data object IncorrectCredentials : LoginError()

    data object UnknownError : LoginError()
}
