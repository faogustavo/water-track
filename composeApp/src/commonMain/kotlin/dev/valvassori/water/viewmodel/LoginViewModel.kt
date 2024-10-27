package dev.valvassori.water.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.valvassori.water.helpers.State
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _state =
        MutableSharedFlow<State<Unit, LoginError>>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )

    val username = _username.asStateFlow()
    val password = _password.asStateFlow()
    val state = _state.asSharedFlow()

    fun updateUsername(value: String) {
        _username.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun authenticate() {
        viewModelScope.launch {
            _state.emit(State.Loading)
            delay(2500)
            _state.emit(State.Success(Unit))
        }
    }
}

sealed class LoginError {
    data object NetworkError : LoginError()

    data object ProfileNotFound : LoginError()

    data object UnknownError : LoginError()
}
