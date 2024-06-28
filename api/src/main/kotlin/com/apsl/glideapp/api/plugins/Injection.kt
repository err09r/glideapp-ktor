package com.apsl.glideapp.api.plugins

import com.apsl.glideapp.api.di.appModule
import com.apsl.glideapp.api.di.controllerModule
import com.apsl.glideapp.api.di.daoModule
import com.apsl.glideapp.api.di.securityModule
import com.apsl.glideapp.api.di.serviceModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureInjection() {
    install(Koin) {
        slf4jLogger()
        properties(jwtProperties)
        modules(appModule, daoModule, controllerModule, serviceModule, securityModule)
    }
}

private val Application.jwtProperties: Map<String, String>
    get() = this.environment.config
        .config("jwt")
        .toMap()
        .mapValues { it.value.toString() }
