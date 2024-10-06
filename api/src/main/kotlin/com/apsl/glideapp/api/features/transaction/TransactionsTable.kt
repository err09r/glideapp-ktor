package com.apsl.glideapp.api.features.transaction

import com.apsl.glideapp.api.features.user.UsersTable
import com.apsl.glideapp.common.models.TransactionType
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TransactionsTable : UUIDTable("transactions") {
    val userId = reference("user_id", UsersTable.id)
    val amount = double("amount")
    val type = enumeration<TransactionType>("type")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
