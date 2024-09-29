package com.apsl.glideapp.api.plugins

import com.apsl.glideapp.api.features.auth.security.TokenConfig
import com.apsl.glideapp.api.utils.GlideErrorCodes
import com.apsl.glideapp.common.dto.ErrorResponse
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.response.respond
import org.koin.ktor.ext.inject

fun Application.configureAuthentication() {
    val tokenConfig: TokenConfig by inject()

    install(Authentication) {
        jwt {
            realm = tokenConfig.realm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(tokenConfig.secret))
                    .withAudience(tokenConfig.audience)
                    .withIssuer(tokenConfig.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(tokenConfig.audience)) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = ErrorResponse(
                        code = GlideErrorCodes.INVALID_TOKEN,
                        description = "Token is invalid or expired"
                    )
                )
            }
        }
    }
}
