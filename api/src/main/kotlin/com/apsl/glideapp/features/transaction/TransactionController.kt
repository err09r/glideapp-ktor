package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.common.util.UUID

class TransactionController(private val transactionDao: TransactionDao) {

    suspend fun getAllTransactionsByUserId(userId: String?) = runCatching {
        requireNotNull(userId)
        transactionDao.getAllTransactionsByUserId(UUID.fromString(userId))
    }
}
