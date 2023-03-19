package com.apsl.glide.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
