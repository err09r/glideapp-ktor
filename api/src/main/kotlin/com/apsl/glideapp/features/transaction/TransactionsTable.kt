package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.features.user.UsersTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object TransactionsTable : Table("transactions") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = reference("userId", UsersTable.id)
    val amount = double("amount")
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(id)
}
