package com.apsl.glideapp.features.map

import CoordinatesBounds
import io.ktor.server.routing.Route
import io.ktor.server.websocket.receiveDeserialized
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.isActive
import org.koin.ktor.ext.inject

fun Route.mapRoutes() {
    val mapController: MapController by inject()
    webSocket("map") {
        while (isActive) {
            val coordinatesBounds = runCatching {
                receiveDeserialized<CoordinatesBounds>()
            }.getOrNull()

            if (coordinatesBounds != null) {
                mapController.updateVisibleBounds(coordinatesBounds)
                mapController.startObservingMapState { sendSerialized(it) }
            }
        }
    }
}
