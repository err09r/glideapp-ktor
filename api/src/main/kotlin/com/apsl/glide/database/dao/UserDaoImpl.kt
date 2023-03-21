package com.apsl.glide.database.dao

import com.apsl.glide.database.DatabaseFactory.query
import com.apsl.glide.database.entities.UserEntity
import com.apsl.glide.database.tables.Users
import com.apsl.glide.utils.now
import java.util.UUID
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class UserDaoImpl : UserDao {

    private fun ResultRow.toUserEntity(): UserEntity {
        return UserEntity(
            id = this[Users.id],
            username = this[Users.username],
            password = this[Users.password],
            salt = this[Users.salt],
            firstName = this[Users.firstName],
            lastName = this[Users.lastName],
            createdAt = this[Users.createdAt],
            updateAt = this[Users.updatedAt]
        )
    }

    override suspend fun getAllUsers(): List<UserEntity> = query {
        Users.selectAll().map { it.toUserEntity() }
    }

    override suspend fun getUserByUsername(username: String): UserEntity? = query {
        Users
            .select { Users.username eq username }
            .map { it.toUserEntity() }
            .singleOrNull()
    }

    override suspend fun getUserById(id: UUID): UserEntity? = query {
        Users
            .select { Users.id eq id }
            .map { it.toUserEntity() }
            .singleOrNull()
    }

    override suspend fun insertUser(
        username: String,
        password: String,
        salt: String,
        firstName: String,
        lastName: String
    ): UserEntity? = query {
        val insertStatement = Users.insert {
            it[Users.username] = username
            it[Users.password] = password
            it[Users.salt] = salt
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.createdAt] = LocalDateTime.now()
            it[Users.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toUserEntity()
    }

    override suspend fun updateUser(
        id: UUID,
        username: String,
        firstName: String,
        lastName: String
    ): Boolean = query {
        Users.update({ Users.id eq id }) {
            it[Users.username] = username
            it[Users.firstName] = firstName
            it[Users.lastName] = lastName
            it[Users.updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun deleteUser(id: UUID): Boolean = query {
        Users.deleteWhere { Users.id eq id } > 0
    }
}
