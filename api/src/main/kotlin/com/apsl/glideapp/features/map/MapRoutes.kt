package com.apsl.glideapp.features.map

import io.ktor.server.routing.Route
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject

fun Route.mapRoutes() {
    val mapController: MapController by inject()
    webSocket("map") {
        incoming.consumeEach {
            launch {
                while (isActive) {
                    mapController.getMapState()
                        .onSuccess { sendSerialized(it) }
                        .onFailure { }
                    delay(2000)
                }
            }
        }
    }
}
