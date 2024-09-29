package com.apsl.glideapp.api.features.user

import com.apsl.glideapp.api.database.DatabaseFactory.query
import com.apsl.glideapp.common.util.now
import java.util.UUID
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class UserDaoImpl : UserDao {

    override suspend fun getUserByUsername(username: String): UserEntity? = query {
        UsersTable
            .selectAll()
            .where { UsersTable.username eq username }
            .map { it.toUserEntity() }
            .singleOrNull()
    }

    override suspend fun getUserById(id: UUID): UserEntity? = query {
        UsersTable
            .selectAll()
            .where { UsersTable.id eq id }
            .map { it.toUserEntity() }
            .singleOrNull()
    }

    override suspend fun insertUser(
        username: String,
        password: String,
        salt: String,
        firstName: String,
        lastName: String,
    ): UserEntity? = query {
        val insertStatement = UsersTable.insert {
            it[UsersTable.username] = username
            it[UsersTable.password] = password
            it[UsersTable.salt] = salt
            it[UsersTable.firstName] = firstName
            it[UsersTable.lastName] = lastName
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toUserEntity()
    }

    override suspend fun updateUser(
        id: UUID,
        username: String,
        firstName: String,
        lastName: String,
    ): Boolean = query {
        UsersTable.update({ UsersTable.id eq id }) {
            it[UsersTable.username] = username
            it[UsersTable.firstName] = firstName
            it[UsersTable.lastName] = lastName
            it[updatedAt] = LocalDateTime.now()
        } > 0
    }

    private fun ResultRow.toUserEntity(): UserEntity {
        return UserEntity(
            id = this[UsersTable.id],
            username = this[UsersTable.username],
            password = this[UsersTable.password],
            salt = this[UsersTable.salt],
            firstName = this[UsersTable.firstName],
            lastName = this[UsersTable.lastName],
            createdAt = this[UsersTable.createdAt],
            updatedAt = this[UsersTable.updatedAt]
        )
    }
}
