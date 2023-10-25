package com.apsl.glideapp.plugins

import io.ktor.server.application.Application
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun Application.configureHttp() {
    routing {
        swaggerUI(path = "api/docs", swaggerFile = "openapi/documentation.yaml")
    }
}
