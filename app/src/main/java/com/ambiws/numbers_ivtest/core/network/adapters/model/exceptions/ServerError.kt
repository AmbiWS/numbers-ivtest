package com.ambiws.numbers_ivtest.core.network.adapters.model.exceptions

import okio.IOException

open class ServerError(
    override val message: String,
    val code: Int,
    cause: Throwable? = null,
    val errorsList: List<String>? = null,
    val headers: Map<String, String>? = null
) : IOException(message, cause)
