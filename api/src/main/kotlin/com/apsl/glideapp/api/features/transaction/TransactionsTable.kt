package com.apsl.glideapp.api.features.transaction

import com.apsl.glideapp.api.features.user.UsersTable
import com.apsl.glideapp.common.models.TransactionType
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
