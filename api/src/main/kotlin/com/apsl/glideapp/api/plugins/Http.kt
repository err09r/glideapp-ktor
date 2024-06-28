@file:Suppress("Unused")

package com.apsl.glideapp.api.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.plugins.httpsredirect.HttpsRedirect
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun Application.configureHttp() {
    val applicationConfig: ApplicationConfig = environment.config
    install(HttpsRedirect) {
        sslPort = applicationConfig.sslPort
        permanentRedirect = true
    }
    routing {
        swaggerUI(path = "api/docs", swaggerFile = "openapi/documentation.yaml")
    }
}

/**
 * The HTTPS port the current application is running on, as defined by the configuration at start-up.
 **/
private val ApplicationConfig.sslPort: Int get() = property("ktor.deployment.sslPort").getString().toInt()
