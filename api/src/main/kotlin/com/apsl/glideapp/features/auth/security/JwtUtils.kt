package com.apsl.glideapp.features.auth.security

import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal

object JwtUtils {

    fun getUserId(call: ApplicationCall): String? {
        return call.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asString()
    }
}