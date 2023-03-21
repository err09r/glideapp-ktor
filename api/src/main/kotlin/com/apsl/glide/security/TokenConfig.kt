package com.apsl.glide.security

data class TokenConfig(
    val issuer: String,
    val audience: String,
    val expiresIn: Long,
    val secret: String,
    val realm: String
)
