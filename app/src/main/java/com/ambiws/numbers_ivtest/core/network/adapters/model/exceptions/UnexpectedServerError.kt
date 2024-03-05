package com.ambiws.numbers_ivtest.core.network.adapters.model.exceptions

class UnexpectedServerError(code: Int) : ServerError(
    message = "Unexpected server error with code: $code",
    code = code
)
