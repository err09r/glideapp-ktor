package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class TransactionEntity(
    val id: UUID,
    val userId: UUID,
    val amount: Double,
    val type: TransactionType,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
