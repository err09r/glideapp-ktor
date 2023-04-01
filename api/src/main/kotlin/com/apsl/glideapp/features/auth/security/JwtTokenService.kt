package com.apsl.glideapp.features.auth.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class JwtTokenService(private val config: TokenConfig) : TokenService {

    override fun generate(vararg claims: TokenClaim): String {
        val token = JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))

        claims.forEach { claim ->
            token.apply {
                withClaim(claim.name, claim.value)
            }
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }
}
