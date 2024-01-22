package com.apsl.glideapp.features.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UsersTable : Table("users") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val username = varchar("username", 20).uniqueIndex()
    val password = char("password", 64)
    val salt = char("salt", 64)
    val firstName = varchar("first_name", 32)
    val lastName = varchar("last_name", 32)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
