package com.apsl.glideapp.di

import com.apsl.glideapp.features.auth.AuthController
import com.apsl.glideapp.features.map.MapController
import com.apsl.glideapp.features.user.UserController
import com.apsl.glideapp.features.vehicle.VehicleController
import com.apsl.glideapp.features.zone.ZoneController
import org.koin.dsl.module

val controllerModule = module {
    single { UserController(get()) }
    single { AuthController(get(), get(), get()) }
    single { ZoneController(get()) }
    single { VehicleController(get()) }
    single { MapController(get(), get()) }
}
