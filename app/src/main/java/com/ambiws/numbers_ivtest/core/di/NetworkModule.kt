package com.ambiws.numbers_ivtest.core.di

import com.ambiws.numbers_ivtest.BuildConfig
import com.google.gson.GsonBuilder
import com.ambiws.numbers_ivtest.core.network.adapters.ErrorCallAdapterFactory
import com.ambiws.numbers_ivtest.core.network.adapters.ExceptionParser
import com.ambiws.numbers_ivtest.core.network.api.NumbersApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        GsonBuilder().setLenient().serializeNulls().create()
    }

    single {
        GsonConverterFactory.create(get())
    }

    single {
        ScalarsConverterFactory.create()
    }

    single {
        ErrorCallAdapterFactory()
    }

    single {
        ExceptionParser(get())
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(get<ScalarsConverterFactory>())
            .addCallAdapterFactory(get<ErrorCallAdapterFactory>())
            .client(get())
            .build()
    }

    single {
         get<Retrofit>().create(NumbersApi::class.java)
    }
}
