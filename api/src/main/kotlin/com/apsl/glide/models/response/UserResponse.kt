package com.apsl.glide.models.response

import com.apsl.glide.utils.UUID
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: UUID,
    val username: String,
    val firstName: String,
    val lastName: String
)
