package com.apsl.glideapp.features.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.appConfigRoutes() {
    route("config") {
        getAppConfigRoute()
    }
}

private fun Route.getAppConfigRoute() {
    get {
        AppConfigController.getAppConfig()
            .onSuccess { call.respond(it) }
            .onFailure { call.respondNullable(message = it.message, status = HttpStatusCode.InternalServerError) }
    }
}