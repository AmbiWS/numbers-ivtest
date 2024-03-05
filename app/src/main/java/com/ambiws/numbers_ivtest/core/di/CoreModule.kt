package com.ambiws.numbers_ivtest.core.di

import com.ambiws.numbers_ivtest.MainViewModel
import com.ambiws.numbers_ivtest.base.EmptyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coreModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        EmptyViewModel()
    }
}
