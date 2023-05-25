package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.common.util.UUID

interface TransactionDao {
    suspend fun getAllTransactionsByUserId(userId: UUID): List<TransactionEntity>
    suspend fun insertTransaction(userId: UUID, amount: Double): TransactionEntity?
}
