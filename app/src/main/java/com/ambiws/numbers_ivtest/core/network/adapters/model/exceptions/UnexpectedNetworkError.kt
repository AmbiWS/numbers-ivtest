package com.ambiws.numbers_ivtest.core.network.adapters.model.exceptions

import okio.IOException

class UnexpectedNetworkError(cause: Throwable) : IOException(cause)
