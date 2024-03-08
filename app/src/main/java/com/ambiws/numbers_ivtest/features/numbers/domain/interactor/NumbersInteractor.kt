package com.ambiws.numbers_ivtest.features.numbers.domain.interactor

import com.ambiws.numbers_ivtest.features.numbers.data.repository.NumbersRepository

interface NumbersInteractor {
    suspend fun getNumberFact(number: Int): String
}

class NumbersInteractorImpl(
    private val repository: NumbersRepository
) : NumbersInteractor {

    override suspend fun getNumberFact(number: Int): String {
        return repository.getNumberFact(number)
    }
}
