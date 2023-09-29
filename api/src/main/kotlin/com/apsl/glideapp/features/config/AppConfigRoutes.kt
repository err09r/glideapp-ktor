package com.apsl.glideapp.features.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.appConfigRoutes() {
    val appConfigController: AppConfigController by inject()
    route("config") {
        getAppConfigRoute(appConfigController)
    }
}

private fun Route.getAppConfigRoute(appConfigController: AppConfigController) {
    get {
        appConfigController.getAppConfig()
            .onSuccess { call.respond(it) }
            .onFailure { call.respondNullable(message = it.message, status = HttpStatusCode.InternalServerError) }
    }
}
