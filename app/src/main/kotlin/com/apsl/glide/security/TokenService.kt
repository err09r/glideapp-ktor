package com.apsl.glide.security

interface TokenService {
    fun generate(vararg claims: TokenClaim): String
}
