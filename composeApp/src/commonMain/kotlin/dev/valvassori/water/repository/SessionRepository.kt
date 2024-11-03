package dev.valvassori.water.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import dev.valvassori.water.data.Profile
import dev.valvassori.water.error.LoginError
import kotlinx.coroutines.delay

interface SessionRepository {
    suspend fun login(
        username: String,
        password: String,
    ): Either<LoginError, Profile>
}

class SessionRepositoryImpl : SessionRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): Either<LoginError, Profile> {
        return when (username) {
            "john" -> {
                if (password == "doe") {
                    delay(2500)
                    return Profile(username, "$username@email.com").right()
                }

                delay(1500)
                LoginError.InvalidCredentials.left()
            }

            "network_error" -> {
                delay(5000)
                LoginError.NetworkError.left()
            }

            else -> {
                delay((1000..5000).random().toLong())
                LoginError.UnknownError.left()
            }
        }
    }
}
