package com.apsl.glideapp.plugins

import com.apsl.glideapp.utils.Constants
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.httpsredirect.HttpsRedirect
import io.ktor.server.plugins.swagger.swaggerUI
import io.ktor.server.routing.routing

fun Application.configureHttp() {
    install(HttpsRedirect) {
        sslPort = getSslPort()
        permanentRedirect = true
    }
    routing {
        swaggerUI(path = "api/docs", swaggerFile = "openapi/documentation.yaml")
    }
}

private fun getSslPort(): Int {
    return try {
        System.getenv()["SSL_PORT"]?.toInt() ?: Constants.DEFAULT_SSL_PORT
    } catch (e: Exception) {
        Constants.DEFAULT_SSL_PORT
    }
}
