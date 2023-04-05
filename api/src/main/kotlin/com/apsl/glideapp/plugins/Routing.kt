package com.apsl.glideapp.plugins

import com.apsl.glideapp.features.auth.authRoutes
import com.apsl.glideapp.features.map.mapRoutes
import com.apsl.glideapp.features.user.userRoutes
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        route("/") {
            handle {
                call.respondRedirect("api/docs")
            }
        }
        route("api") {
            authRoutes()
            authenticate {
                userRoutes()
            }
            mapRoutes()
        }
    }
}
