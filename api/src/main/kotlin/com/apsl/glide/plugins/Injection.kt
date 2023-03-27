package com.apsl.glide.plugins

import com.apsl.glide.di.appModule
import com.apsl.glide.di.controllerModule
import com.apsl.glide.di.daoModule
import com.apsl.glide.di.securityModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureInjection() {
    install(Koin) {
        slf4jLogger()
        modules(daoModule, controllerModule, appModule, securityModule(environment.config))
    }
}
