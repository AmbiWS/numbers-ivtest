package com.ambiws.numbers_ivtest.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ambiws.numbers_ivtest.core.database.entity.NumberEntity

@Dao
interface NumbersDao {

    @Query("SELECT * FROM NumberEntity")
    suspend fun getSearchHistory(): List<NumberEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(entity: NumberEntity)
}
