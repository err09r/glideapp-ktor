package com.apsl.glideapp.plugins

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
        modules(daoModule, controllerModule, serviceModule, securityModule(environment.config))
    }
}
