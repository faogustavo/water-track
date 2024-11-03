package dev.valvassori.water.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.valvassori.water.error.LoginError
import dev.valvassori.water.ext.asState
import dev.valvassori.water.ext.mapRightToUnit
import dev.valvassori.water.helpers.State
import dev.valvassori.water.repository.SessionRepository
import dev.valvassori.water.validator.LoginPasswordValidator
import dev.valvassori.water.validator.LoginUsernameValidator
import dev.valvassori.water.validator.PasswordValidator
import dev.valvassori.water.validator.UsernameValidator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val sessionRepository: SessionRepository,
    private val usernameValidator: UsernameValidator = LoginUsernameValidator(),
    private val passwordValidator: PasswordValidator = LoginPasswordValidator(),
) : ViewModel() {
    private val _username = MutableStateFlow("")
    private val _usernameValidation =
        MutableStateFlow<UsernameValidator.ValidationState>(UsernameValidator.ValidationState.Valid)

    private val _password = MutableStateFlow("")
    private val _passwordValidation =
        MutableStateFlow<PasswordValidator.ValidationState>(PasswordValidator.ValidationState.Valid)

    private val _state =
        MutableSharedFlow<State<Unit, LoginError>>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )

    val username = _username.asStateFlow()
    val usernameValidation = _usernameValidation.asStateFlow()

    val password = _password.asStateFlow()
    val passwordValidation = _passwordValidation.asStateFlow()

    val state = _state.asSharedFlow()

    fun updateUsername(value: String) {
        _username.value = value
        _usernameValidation.value = usernameValidator.validate(value)
    }

    fun updatePassword(value: String) {
        _password.value = value
        _passwordValidation.value = passwordValidator.validate(value)
    }

    fun authenticate() {
        val username = _username.value
        val password = _password.value

        _usernameValidation.value = usernameValidator.validate(username)
        _passwordValidation.value = passwordValidator.validate(password)

        if (_usernameValidation.value.isValid.not() || _passwordValidation.value.isValid.not()) {
            return
        }

        viewModelScope.launch {
            _state.emit(State.Loading)

            _state.emit(
                sessionRepository
                    .login(username, password)
                    .mapRightToUnit()
                    .asState(),
            )
        }
    }
}
