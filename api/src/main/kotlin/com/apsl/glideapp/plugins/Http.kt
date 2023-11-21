package com.apsl.glideapp.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.plugins.httpsredirect.HttpsRedirect
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun Application.configureHttp() {
    val applicationConfig: ApplicationConfig = environment.config
    install(HttpsRedirect) {
        sslPort = getSslPort(config = applicationConfig)
        permanentRedirect = true
    }
    routing {
        swaggerUI(path = "api/docs", swaggerFile = "openapi/documentation.yaml")
    }
}

private fun getSslPort(config: ApplicationConfig): Int {
    return config.property("ktor.deployment.sslPort").getString().toInt()
}
