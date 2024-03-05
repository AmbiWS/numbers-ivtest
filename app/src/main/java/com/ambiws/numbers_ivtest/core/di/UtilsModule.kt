package com.ambiws.numbers_ivtest.core.di

import com.ambiws.numbers_ivtest.utils.providers.PreferencesProvider
import com.ambiws.numbers_ivtest.utils.providers.PreferencesProviderImpl
import com.ambiws.numbers_ivtest.utils.providers.ResourceProvider
import com.ambiws.numbers_ivtest.utils.providers.ResourceProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilsModule = module {
    factory<PreferencesProvider> { PreferencesProviderImpl(androidContext()) }
    factory<ResourceProvider> { ResourceProviderImpl(androidContext()) }
}
