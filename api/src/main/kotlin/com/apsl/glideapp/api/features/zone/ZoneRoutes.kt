package com.apsl.glideapp.api.features.zone

import com.apsl.glideapp.api.utils.getErrorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.zoneRoutes() {
    val zoneController: ZoneController by inject()
    route("zone") {
        getAllZonesRoute(zoneController)
    }
}

private fun Route.getAllZonesRoute(zoneController: ZoneController) {
    get {
        zoneController.getAllZones()
            .onSuccess { call.respond(it) }
            .onFailure { throwable ->
                call.respond(
                    message = throwable.getErrorResponse(),
                    status = HttpStatusCode.InternalServerError
                )
            }
    }
}
