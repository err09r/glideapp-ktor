package com.apsl.glideapp.api.di

import com.apsl.glideapp.api.features.vehicle.VehicleService
import com.apsl.glideapp.api.features.vehicle.VehicleServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<VehicleService>(createdAtStart = true) { VehicleServiceImpl(get(), get(), get()) }
}
