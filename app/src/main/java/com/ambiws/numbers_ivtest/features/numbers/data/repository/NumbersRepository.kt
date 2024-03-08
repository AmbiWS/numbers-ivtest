package com.ambiws.numbers_ivtest.features.numbers.data.repository

import com.ambiws.numbers_ivtest.core.database.dao.NumbersDao
import com.ambiws.numbers_ivtest.core.database.entity.NumberEntity
import com.ambiws.numbers_ivtest.core.network.api.NumbersApi
import com.ambiws.numbers_ivtest.features.numbers.domain.model.FactData
import com.ambiws.numbers_ivtest.features.numbers.domain.model.toDomain

interface NumbersRepository {
    suspend fun getNumberFact(number: Int): String
    suspend fun getSearchHistory(): List<FactData>
    suspend fun insertFact(entity: NumberEntity)
}

class NumbersRepositoryImpl(
    private val api: NumbersApi,
    private val dao: NumbersDao,
) : NumbersRepository {

    override suspend fun getNumberFact(number: Int): String {
        return api.getNumber(number)
    }

    override suspend fun getSearchHistory(): List<FactData> {
        return dao.getSearchHistory().map {
            it.toDomain()
        }
    }

    override suspend fun insertFact(entity: NumberEntity) {
        dao.insertFact(entity)
    }
}
