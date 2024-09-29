package com.apsl.glideapp.api.features.auth.security

interface TokenService {
    fun generate(vararg claims: TokenClaim): String
}
