package com.ambiws.numbers_ivtest.features.home.di

import com.ambiws.numbers_ivtest.features.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel()
    }
}
