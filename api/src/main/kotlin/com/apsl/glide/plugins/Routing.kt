package com.apsl.glide.plugins

import com.apsl.glide.features.auth.authRoutes
import com.apsl.glide.features.user.userRoutes
import com.apsl.glide.features.vehicle.vehicleRoutes
import com.apsl.glide.features.zone.zoneRoutes
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
                zoneRoutes()
                vehicleRoutes()
            }
        }
    }
}
