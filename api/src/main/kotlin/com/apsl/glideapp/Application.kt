package com.apsl.glideapp

import com.apsl.glideapp.database.DatabaseFactory
import com.apsl.glideapp.plugins.configureAuthentication
import com.apsl.glideapp.plugins.configureHttp
import com.apsl.glideapp.plugins.configureInjection
import com.apsl.glideapp.plugins.configureMonitoring
import com.apsl.glideapp.plugins.configureRouting
import com.apsl.glideapp.plugins.configureSerialization
import com.apsl.glideapp.plugins.configureSockets
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureInjection()
    configureAuthentication()
    configureSockets()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureHttp()

    val mainService: MainService by inject()
    mainService.start()
}
