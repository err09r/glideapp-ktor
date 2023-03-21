package com.apsl.glide

import com.apsl.glide.database.DatabaseFactory
import com.apsl.glide.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("Unused")
fun Application.module() {
    DatabaseFactory.init(environment.config)
    configureInjection()
    configureAuthentication()
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureHttp()
}
