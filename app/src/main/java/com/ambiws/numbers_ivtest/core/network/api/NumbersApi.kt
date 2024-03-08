package com.ambiws.numbers_ivtest.core.network.api

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("{num}")
    suspend fun getNumber(
        @Path("num") number: Int
    ): String
}
