package dev.valvassori.water.di

import dev.valvassori.water.viewmodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModel { LoginViewModel(get()) }
    }
