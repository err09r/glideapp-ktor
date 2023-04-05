package com.apsl.glideapp.features.user

import java.util.UUID

interface UserDao {
    suspend fun getUserByUsername(username: String): UserEntity?
    suspend fun getUserById(id: UUID): UserEntity?
    suspend fun updateUser(id: UUID, username: String, firstName: String, lastName: String): Boolean
    suspend fun insertUser(
        username: String,
        password: String,
        salt: String,
        firstName: String,
        lastName: String
    ): UserEntity?
}
