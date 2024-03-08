package com.ambiws.numbers_ivtest.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberEntity(
    @PrimaryKey
    val timestamp: Long,
    val number: Int,
    val fact: String,
)
