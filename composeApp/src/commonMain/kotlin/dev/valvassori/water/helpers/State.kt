package dev.valvassori.water.helpers

sealed interface State<out T, out E> {
    data object Idle : State<Nothing, Nothing>

    data object Loading : State<Nothing, Nothing>

    data class Error<E>(
        val error: E,
    ) : State<Nothing, E>

    data class Success<T>(
        val data: T,
    ) : State<T, Nothing>
}
