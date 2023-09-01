package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.features.auth.security.JwtUtils
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import io.ktor.util.logging.KtorSimpleLogger
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Route.rideRoutes() {
    val rideController: RideController by inject()
    route("ride") {
        observeRideRoute(rideController)
        getAllRidesByStatusAndUserIdRoute(rideController)
        getRideByIdRoute(rideController)
    }
}

fun Route.observeRideRoute(rideController: RideController) {
    webSocket {
        for (frame in incoming) {
            frame as? Frame.Text ?: continue

            val action = runCatching {
                Json.decodeFromString<RideAction>(frame.readText())
            }.getOrNull()

            val userId = JwtUtils.getUserId(call)

            if (action != null && userId != null) {
                rideController.handleRideAction(action = action, userId = userId)
                    .onSuccess { sendSerialized(it) }
                    .onFailure { throwable ->
                        //TODO: Handle closing reasons depending on exception type
                        KtorSimpleLogger("RideRoutes").error("handleRideAction failure: $throwable")
                        close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, throwable.toString()))
                    }
            } else {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, ""))
            }
        }
    }
}

fun Route.getAllRidesByStatusAndUserIdRoute(rideController: RideController) {
    get {
        val userId = JwtUtils.getUserId(call)
        val rideStatus = call.request.queryParameters["status"]
        val page = call.request.queryParameters["page"]
        val limit = call.request.queryParameters["limit"]

        rideController.getAllRidesByStatusAndUserId(
            status = rideStatus,
            userId = userId,
            page = page,
            limit = limit
        )
            .onSuccess { call.respond(it) }
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

fun Route.getRideByIdRoute(rideController: RideController) {
    get("{id}") {
        val rideId = call.parameters["id"]
        rideController.getRideById(rideId)
            .onSuccess { call.respond(it) }
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
