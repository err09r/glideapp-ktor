package com.apsl.glideapp.api

import com.apsl.glideapp.api.database.DatabaseFactory
import com.apsl.glideapp.api.plugins.configureAuthentication
import com.apsl.glideapp.api.plugins.configureHttp
import com.apsl.glideapp.api.plugins.configureInjection
import com.apsl.glideapp.api.plugins.configureMonitoring
import com.apsl.glideapp.api.plugins.configureRouting
import com.apsl.glideapp.api.plugins.configureSerialization
import com.apsl.glideapp.api.plugins.configureSockets
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureInjection()
    configureAuthentication()
    configureSockets()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureHttp()
}
