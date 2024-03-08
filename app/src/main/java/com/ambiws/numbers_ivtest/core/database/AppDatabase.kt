package com.ambiws.numbers_ivtest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ambiws.numbers_ivtest.core.database.dao.NumbersDao
import com.ambiws.numbers_ivtest.core.database.entity.NumberEntity

@Database(
    entities = [NumberEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun numbersDao(): NumbersDao
}
