package com.ambiws.numbers_ivtest.features.numbers.di

import com.ambiws.numbers_ivtest.features.numbers.data.repository.NumbersRepository
import com.ambiws.numbers_ivtest.features.numbers.data.repository.NumbersRepositoryImpl
import com.ambiws.numbers_ivtest.features.numbers.domain.interactor.NumbersInteractor
import com.ambiws.numbers_ivtest.features.numbers.domain.interactor.NumbersInteractorImpl
import com.ambiws.numbers_ivtest.features.numbers.ui.NumbersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val numbersModule = module {
    single<NumbersRepository> { NumbersRepositoryImpl(get()) }
    factory<NumbersInteractor> { NumbersInteractorImpl(get()) }
    viewModel { NumbersViewModel(get()) }
}
