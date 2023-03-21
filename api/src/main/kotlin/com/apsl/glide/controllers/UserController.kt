package com.apsl.glide.controllers

import com.apsl.glide.database.dao.UserDao
import com.apsl.glide.models.response.UserResponse
import com.apsl.glide.utils.UserNotFoundException
import java.util.UUID

class UserController(private val userDao: UserDao) {

    suspend fun getUserById(id: String?) = runCatching {
        if (id == null) {
            throw IllegalArgumentException()
        }
        val entity = userDao.getUserById(UUID.fromString(id)) ?: throw UserNotFoundException()
        UserResponse(
            id = entity.id,
            username = entity.username,
            firstName = entity.firstName,
            lastName = entity.lastName
        )
    }
}
