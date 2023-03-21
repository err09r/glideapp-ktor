package com.apsl.glide.plugins

import com.apsl.glide.routes.authRoutes
import com.apsl.glide.routes.userRoutes
import com.apsl.glide.routes.zoneRoutes
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
                call.respondRedirect("/api/v1/docs")
            }
        }
        route("api/v1") {
            authRoutes()
            authenticate {
                userRoutes()
                zoneRoutes()
            }
        }
    }
}
