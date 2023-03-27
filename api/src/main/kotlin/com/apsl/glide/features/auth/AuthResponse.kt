package com.apsl.glide.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val token: String)
