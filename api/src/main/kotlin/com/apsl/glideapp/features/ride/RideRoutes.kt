package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideAction
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.routing.Route
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Route.rideRoutes() {
    val rideController: RideController by inject()
    webSocket("ride") {
        for (frame in incoming) {
            frame as? Frame.Text ?: continue

            val action = runCatching {
                Json.decodeFromString<RideAction>(frame.readText())
            }.getOrNull()

            val userId =
                call.parameters["id"] ?: call.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asString()

            if (action != null && userId != null) {
                rideController.handleRideAction(action = action, userId = userId)
                    .onSuccess { sendSerialized(it) }
                    .onFailure { throwable ->
                        //TODO: Handle closing reasons depending on exception type
                    }
            }
        }
    }
}
