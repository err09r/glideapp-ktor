package com.apsl.glide.features.zone

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
    route("zones") {
        getAllZonesByTypeRoute(zoneController)
        getZoneByIdRoute(zoneController)
    }
}

fun Route.getAllZonesByTypeRoute(zoneController: ZoneController) {
    get {
        val zoneType = call.request.queryParameters["type"]
        zoneController.getAllZonesByType(zoneType)
            .onSuccess { zoneResponse -> call.respond(zoneResponse) }
            .onFailure { throwable ->
                call.respondNullable(
                    message = throwable.message,
                    status = when (throwable) {
                        is IllegalArgumentException -> HttpStatusCode.BadRequest
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}

fun Route.getZoneByIdRoute(zoneController: ZoneController) {
    get("{id}") {

    }
}
