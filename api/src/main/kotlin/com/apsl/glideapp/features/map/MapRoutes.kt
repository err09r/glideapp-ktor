package com.apsl.glideapp.features.map

import CoordinatesBounds
import io.ktor.server.routing.Route
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject

fun Route.mapRoutes() {
    val mapController: MapController by inject()
    webSocket("map") {
        for (frame in incoming) {
            frame as? Frame.Text ?: continue

            val coordinatesBounds = runCatching {
                Json.decodeFromString<CoordinatesBounds>(frame.readText())
            }.getOrNull()

            if (coordinatesBounds != null) {
                mapController.observeMapStateWithinZoneBounds(coordinatesBounds).collectLatest(::sendSerialized)
            }
        }
    }
}
