package com.apsl.glide.features.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Users : Table() {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val username = varchar("username", 20).uniqueIndex()
    val password = char("password", 64)
    val salt = char("salt", 64)
    val firstName = varchar("firstName", 32)
    val lastName = varchar("lastName", 32)
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(id)
}
