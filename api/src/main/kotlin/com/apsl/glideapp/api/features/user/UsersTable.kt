package com.apsl.glideapp.api.features.user

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object UsersTable : UUIDTable("users") {
    val username = varchar("username", 20).uniqueIndex()
    val password = char("password", 64)
    val salt = char("salt", 64)
    val firstName = varchar("first_name", 32)
    val lastName = varchar("last_name", 32)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
