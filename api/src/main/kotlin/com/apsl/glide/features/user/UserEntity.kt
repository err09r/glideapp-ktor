package com.apsl.glide.features.user

import com.apsl.glide.utils.UUID
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
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
