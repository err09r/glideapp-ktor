package com.apsl.glideapp.features.vehicle

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.vehicleRoutes() {
    val vehicleController: VehicleController by inject()
    route("vehicles") {
        getAllVehiclesRoute(vehicleController)
    }
}

fun Route.getAllVehiclesRoute(vehicleController: VehicleController) {
    get {
        vehicleController.getAllVehicles()
            .onSuccess { vehicleDtos -> call.respond(vehicleDtos) }
            .onFailure { call.respond(HttpStatusCode.InternalServerError) }
    }
}
