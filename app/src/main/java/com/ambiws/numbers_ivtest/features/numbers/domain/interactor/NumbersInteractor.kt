package com.ambiws.numbers_ivtest.features.numbers.domain.interactor

import com.ambiws.numbers_ivtest.features.numbers.data.repository.NumbersRepository
import com.ambiws.numbers_ivtest.features.numbers.domain.model.FactData
import com.ambiws.numbers_ivtest.features.numbers.domain.model.toEntity
import kotlinx.coroutines.flow.Flow

interface NumbersInteractor {
    suspend fun getNumberFact(number: Int): String
    suspend fun getFactByTimestamp(timestamp: Long): String
    suspend fun getRandomNumberFact(): String
    suspend fun getSearchHistory(): Flow<List<FactData>>
    suspend fun insertFact(fact: FactData)
}

class NumbersInteractorImpl(
    private val repository: NumbersRepository
) : NumbersInteractor {

    override suspend fun getNumberFact(number: Int): String {
        return repository.getNumberFact(number)
    }

    override suspend fun getFactByTimestamp(timestamp: Long): String {
        return repository.getFactByTimestamp(timestamp)
    }

    override suspend fun getRandomNumberFact(): String {
        return repository.getRandomNumberFact()
    }

    override suspend fun getSearchHistory(): Flow<List<FactData>> {
        return repository.getSearchHistory()
    }

    override suspend fun insertFact(fact: FactData) {
        repository.insertFact(fact.toEntity())
    }
}
