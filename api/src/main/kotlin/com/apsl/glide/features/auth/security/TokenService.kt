package com.apsl.glide.features.auth.security

interface TokenService {
    fun generate(vararg claims: TokenClaim): String
}
