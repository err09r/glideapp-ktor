package com.apsl.glideapp.api.features.transaction

import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.util.UUID

interface TransactionDao {
    suspend fun getTransactionsByUserId(userId: UUID): List<TransactionEntity>
    suspend fun getTransactionsByUserId(userId: UUID, limit: Int?, offset: Long): List<TransactionEntity>
    suspend fun getTransactionsAmountSumByUserId(userId: UUID): Double
    suspend fun insertTransaction(userId: UUID, type: TransactionType, amount: Double): TransactionEntity?
}
