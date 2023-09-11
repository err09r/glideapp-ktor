package com.apsl.glideapp.features.zone

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
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
            .onFailure { call.respondNullable(message = it.message, status = HttpStatusCode.InternalServerError) }
    }
}