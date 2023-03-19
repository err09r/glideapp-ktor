package com.apsl.glide.di

import com.apsl.glide.controllers.AuthController
import com.apsl.glide.controllers.UserController
import org.koin.dsl.module

val controllerModule = module {
    single { UserController(get()) }
    single { AuthController(get(), get(), get()) }
}
