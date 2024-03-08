package com.ambiws.numbers_ivtest.core.di

import com.ambiws.numbers_ivtest.features.home.di.homeModule
import com.ambiws.numbers_ivtest.features.numbers.di.numbersModule

object AppModules {
    val applicationModules = listOf(
        coreModule,
        utilsModule,
        networkModule,
        databaseModule,
        homeModule,
        numbersModule,
    )
}
