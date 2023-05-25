package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class TransactionDaoImpl : TransactionDao {

    private fun ResultRow.toTransactionEntity(): TransactionEntity {
        return TransactionEntity(
            id = this[TransactionsTable.id],
            userId = this[TransactionsTable.userId],
            amount = this[TransactionsTable.amount],
            createdAt = this[TransactionsTable.createdAt],
            updateAt = this[TransactionsTable.updatedAt]
        )
    }

    override suspend fun getAllTransactionsByUserId(userId: UUID): List<TransactionEntity> = query {
        TransactionsTable
            .select { TransactionsTable.userId eq userId }
            .map { it.toTransactionEntity() }
    }

    override suspend fun insertTransaction(userId: UUID, amount: Double): TransactionEntity? = query {
        val insertStatement = TransactionsTable.insert {
            it[TransactionsTable.userId] = userId
            it[TransactionsTable.amount] = amount
            it[TransactionsTable.createdAt] = LocalDateTime.now()
            it[TransactionsTable.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toTransactionEntity()
    }
}
