package com.apsl.glideapp.features.auth.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
