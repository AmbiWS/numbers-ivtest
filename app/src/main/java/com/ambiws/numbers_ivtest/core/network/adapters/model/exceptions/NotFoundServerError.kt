package com.ambiws.numbers_ivtest.core.network.adapters.model.exceptions

import com.ambiws.numbers_ivtest.core.network.adapters.model.StatusCode

class NotFoundServerError(
    override val message: String,
    cause: Throwable,
    errorsList: List<String>? = null
) : ServerError(
    message = message,
    code = StatusCode.NOT_FOUND.code,
    cause = cause,
    errorsList = errorsList
)
