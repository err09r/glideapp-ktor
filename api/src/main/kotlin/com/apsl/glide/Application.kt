package com.apsl.glide

import com.apsl.glide.database.DatabaseFactory
import com.apsl.glide.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

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

    val mainService: MainService by inject()
    mainService.start()
}
