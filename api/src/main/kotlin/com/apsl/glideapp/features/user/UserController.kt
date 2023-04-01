package com.apsl.glideapp.features.user

import com.apsl.glideapp.common.dto.UserDto
import com.apsl.glideapp.utils.UserNotFoundException
import java.util.UUID

class UserController(private val userDao: UserDao) {

    suspend fun getUserById(id: String?) = runCatching {
        if (id == null) {
            throw IllegalArgumentException()
        }
        val entity = userDao.getUserById(UUID.fromString(id)) ?: throw UserNotFoundException()
        UserDto(
            id = entity.id,
            username = entity.username,
            firstName = entity.firstName,
            lastName = entity.lastName
        )
    }
}
