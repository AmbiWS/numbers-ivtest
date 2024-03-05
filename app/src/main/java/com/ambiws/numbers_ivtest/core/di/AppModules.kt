package com.ambiws.numbers_ivtest.core.di

import com.ambiws.numbers_ivtest.features.home.di.homeModule

object AppModules {
    val applicationModules = listOf(
        coreModule,
        utilsModule,
        networkModule,
        homeModule,
    )
}
