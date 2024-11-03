package dev.valvassori.water.di

import dev.valvassori.water.repository.SessionRepository
import dev.valvassori.water.repository.SessionRepositoryImpl
import org.koin.dsl.module

val repositoryModule =
    module {
        factory<SessionRepository> { SessionRepositoryImpl() }
    }
