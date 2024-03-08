package com.ambiws.numbers_ivtest.core.di

import androidx.room.Room
import com.ambiws.numbers_ivtest.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { (get<AppDatabase>()).numbersDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "local_database.db"
        ).build()
    }
}
