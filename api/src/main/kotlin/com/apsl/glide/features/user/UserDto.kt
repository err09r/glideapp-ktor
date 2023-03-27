package com.apsl.glide.features.user

import com.apsl.glide.utils.UUID
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: UUID,
    val username: String,
    val firstName: String,
    val lastName: String
)
