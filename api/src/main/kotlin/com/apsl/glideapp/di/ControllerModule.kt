package com.apsl.glideapp.di

import com.apsl.glideapp.features.auth.AuthController
import com.apsl.glideapp.features.map.MapController
import com.apsl.glideapp.features.ride.RideController
import com.apsl.glideapp.features.user.UserController
import org.koin.dsl.module

val controllerModule = module {
    single { UserController(get()) }
    single { AuthController(get(), get(), get()) }
    single { MapController(get(), get(), get()) }
    single { RideController(get()) }
}
