package dev.valvassori.water.di

import dev.valvassori.water.analytics.AnalyticsLogger
import dev.valvassori.water.analytics.AnalyticsLoggerImpl
import org.koin.dsl.module

val analyticsModule =
    module {
        single<AnalyticsLogger> { args -> AnalyticsLoggerImpl(args.get()) }
    }
