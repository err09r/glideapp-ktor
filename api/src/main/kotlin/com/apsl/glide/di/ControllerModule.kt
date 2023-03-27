package com.apsl.glide.di

import com.apsl.glide.features.auth.AuthController
import com.apsl.glide.features.user.UserController
import com.apsl.glide.features.vehicle.VehicleController
import com.apsl.glide.features.zone.ZoneController
import org.koin.dsl.module

val controllerModule = module {
    single { UserController(get()) }
    single { AuthController(get(), get(), get()) }
    single { ZoneController(get()) }
    single { VehicleController(get()) }
}
