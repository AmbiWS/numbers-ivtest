package com.ambiws.numbers_ivtest.features.numbers.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NumbersParams(
    val number: Int?
): Parcelable
