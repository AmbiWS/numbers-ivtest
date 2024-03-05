package com.ambiws.numbers_ivtest.core.network.adapters.model

import com.google.gson.annotations.SerializedName

data class ErrorBodyResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("errors")
    val errorsList: List<String>? = null
)
