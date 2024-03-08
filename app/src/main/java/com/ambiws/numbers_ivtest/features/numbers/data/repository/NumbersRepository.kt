package com.ambiws.numbers_ivtest.features.numbers.data.repository

import com.ambiws.numbers_ivtest.core.network.api.NumbersApi

interface NumbersRepository {
    suspend fun getNumberFact(number: Int): String
}

class NumbersRepositoryImpl(
    private val api: NumbersApi
) : NumbersRepository {

    override suspend fun getNumberFact(number: Int): String {
        return api.getNumber(number)
    }
}
