package com.ambiws.numbers_ivtest.features.numbers.domain.model

import com.ambiws.numbers_ivtest.core.database.entity.NumberEntity
import com.ambiws.numbers_ivtest.features.home.ui.list.HistoryItemModel

data class FactData(
    val timestamp: Long,
    val number: Int,
    val fact: String,
)

fun NumberEntity.toDomain() = FactData(
    timestamp = timestamp,
    number = number,
    fact = fact,
)

fun FactData.toEntity() = NumberEntity(
    timestamp = timestamp,
    number = number,
    fact = fact,
)

fun FactData.toItem() = HistoryItemModel(
    number = number,
    timestamp = timestamp,
)
