package com.ambiws.numbers_ivtest.features.numbers.domain.interactor

import com.ambiws.numbers_ivtest.features.numbers.data.repository.NumbersRepository
import com.ambiws.numbers_ivtest.features.numbers.domain.model.FactData
import com.ambiws.numbers_ivtest.features.numbers.domain.model.toEntity

interface NumbersInteractor {
    suspend fun getNumberFact(number: Int): String
    suspend fun getSearchHistory(): List<FactData>
    suspend fun insertFact(fact: FactData)
}

class NumbersInteractorImpl(
    private val repository: NumbersRepository
) : NumbersInteractor {

    override suspend fun getNumberFact(number: Int): String {
        return repository.getNumberFact(number)
    }

    override suspend fun getSearchHistory(): List<FactData> {
        return repository.getSearchHistory()
    }

    override suspend fun insertFact(fact: FactData) {
        repository.insertFact(fact.toEntity())
    }
}
