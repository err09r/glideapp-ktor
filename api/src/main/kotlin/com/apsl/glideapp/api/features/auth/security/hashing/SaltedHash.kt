package com.apsl.glideapp.api.features.auth.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
