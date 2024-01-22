package com.apsl.glideapp.plugins

import com.apsl.glideapp.di.appModule
import com.apsl.glideapp.di.controllerModule
import com.apsl.glideapp.di.daoModule
import com.apsl.glideapp.di.securityModule
import com.apsl.glideapp.di.serviceModule
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
