package com.apsl.glideapp.features.user

import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class UserEntity(
    val id: UUID,
    val username: String,
    val password: String,
    val salt: String,
    val firstName: String,
    val lastName: String,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
