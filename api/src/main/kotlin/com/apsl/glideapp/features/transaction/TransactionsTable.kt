package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.features.user.UsersTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TransactionsTable : Table("transactions") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = reference("user_id", UsersTable.id)
    val amount = double("amount")
    val type = enumeration<TransactionType>("type")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
