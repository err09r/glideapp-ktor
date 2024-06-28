package com.apsl.glideapp.api.plugins

import com.apsl.glideapp.api.features.auth.authRoutes
import com.apsl.glideapp.api.features.config.appConfigRoutes
import com.apsl.glideapp.api.features.map.mapRoutes
import com.apsl.glideapp.api.features.ride.rideRoutes
import com.apsl.glideapp.api.features.transaction.transactionRoutes
import com.apsl.glideapp.api.features.user.userRoutes
import com.apsl.glideapp.api.features.zone.zoneRoutes
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
            zoneRoutes()
            appConfigRoutes()
            authenticate {
                userRoutes()
                mapRoutes()
                rideRoutes()
                transactionRoutes()
            }
        }
    }
}
