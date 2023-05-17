package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideAction
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
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
        getAllFinishedRidesByUserIdRoute(rideController)
    }
}

fun Route.observeRideRoute(rideController: RideController) {
    webSocket {
        for (frame in incoming) {
            frame as? Frame.Text ?: continue

            val action = runCatching {
                Json.decodeFromString<RideAction>(frame.readText())
            }.getOrNull()

            val userId = call.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asString()

            if (action != null && userId != null) {
                rideController.handleRideAction(action = action, userId = userId)
                    .onSuccess { sendSerialized(it) }
                    .onFailure { throwable ->
                        //TODO: Handle closing reasons depending on exception type
                        KtorSimpleLogger("RideRoutes").error("handleRideAction failure: $throwable")
                        close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, ""))
                    }
            } else {
                close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, ""))
            }
        }
    }
}

fun Route.getAllFinishedRidesByUserIdRoute(rideController: RideController) {
    get("{userId?}") {
        val userId =
            call.parameters["userId"] ?: call.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asString()
        rideController.getAllFinishedRidesByUserId(userId)
            .onSuccess { rideDto -> call.respond(rideDto) }
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
