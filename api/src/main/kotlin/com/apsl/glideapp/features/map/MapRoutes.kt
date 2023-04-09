package com.apsl.glideapp.features.map

import io.ktor.server.routing.Route
import io.ktor.server.websocket.receiveDeserialized
import io.ktor.server.websocket.sendSerialized
import io.ktor.server.websocket.webSocket
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collectLatest
import org.koin.ktor.ext.inject

fun Route.mapRoutes() {
    val mapController: MapController by inject()
    webSocket("map") {
        incoming.consumeEach {
            val zoneBoundsDto = receiveDeserialized<ZoneBoundsDto>()
            mapController.observeMapStateWithinZoneBounds(zoneBoundsDto).collectLatest(::sendSerialized)
        }
    }
}
