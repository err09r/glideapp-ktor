package com.apsl.glide.features.auth.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
