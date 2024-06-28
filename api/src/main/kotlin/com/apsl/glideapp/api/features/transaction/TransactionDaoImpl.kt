package com.apsl.glideapp.api.features.transaction

import com.apsl.glideapp.api.database.DatabaseFactory.query
import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class TransactionDaoImpl : TransactionDao {

    override suspend fun getTransactionsByUserId(userId: UUID): List<TransactionEntity> = query {
        TransactionsTable
            .selectAll()
            .where { TransactionsTable.userId eq userId }
            .orderBy(column = TransactionsTable.createdAt, order = SortOrder.DESC)
            .map { it.toTransactionEntity() }
    }

    override suspend fun getTransactionsByUserId(
        userId: UUID,
        limit: Int?,
        offset: Long
    ): List<TransactionEntity> = query {
        TransactionsTable
            .selectAll()
            .where { TransactionsTable.userId eq userId }
            .orderBy(column = TransactionsTable.createdAt, order = SortOrder.DESC)
            .apply {
                if (limit != null) {
                    this.limit(n = limit, offset = offset)
                }
            }
            .map { it.toTransactionEntity() }
    }

    override suspend fun getTransactionsAmountSumByUserId(userId: UUID): Double = query {
        TransactionsTable
            .selectAll()
            .where { TransactionsTable.userId eq userId }
            .sumOf { it.toTransactionEntity().amount }
    }

    override suspend fun insertTransaction(
        userId: UUID,
        type: TransactionType,
        amount: Double
    ): TransactionEntity? = query {
        val insertStatement = TransactionsTable.insert {
            it[TransactionsTable.userId] = userId
            it[TransactionsTable.amount] = amount
            it[TransactionsTable.type] = type
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toTransactionEntity()
    }

    private fun ResultRow.toTransactionEntity(): TransactionEntity {
        return TransactionEntity(
            id = this[TransactionsTable.id],
            userId = this[TransactionsTable.userId],
            amount = this[TransactionsTable.amount],
            type = this[TransactionsTable.type],
            createdAt = this[TransactionsTable.createdAt],
            updatedAt = this[TransactionsTable.updatedAt]
        )
    }
}
