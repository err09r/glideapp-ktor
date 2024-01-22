package com.apsl.glideapp.features.auth.security

interface TokenService {
    fun generate(vararg claims: TokenClaim): String
}
