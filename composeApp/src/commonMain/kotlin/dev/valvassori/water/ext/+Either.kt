package dev.valvassori.water.ext

import arrow.core.Either
import dev.valvassori.water.helpers.State

fun <T, E> Either<E, T>.mapRightToUnit() = map { Unit }

fun <T, E> Either<E, T>.asState() =
    when (this) {
        is Either.Left -> State.Error(value)
        is Either.Right -> State.Success(value)
    }
