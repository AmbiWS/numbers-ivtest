package com.ambiws.numbers_ivtest.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ambiws.numbers_ivtest.core.database.entity.NumberEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumbersDao {

    @Query("SELECT * FROM NumberEntity ORDER BY timestamp DESC")
    fun getSearchHistory(): Flow<List<NumberEntity>>

    @Query("SELECT * FROM NumberEntity WHERE timestamp = :ts LIMIT 1")
    fun getFact(ts: Long): NumberEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(entity: NumberEntity)
}
