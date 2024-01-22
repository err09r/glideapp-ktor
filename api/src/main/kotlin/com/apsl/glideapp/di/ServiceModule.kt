package com.apsl.glideapp.di

import com.apsl.glideapp.features.vehicle.VehicleService
import com.apsl.glideapp.features.vehicle.VehicleServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<VehicleService>(createdAtStart = true) { VehicleServiceImpl(get(), get(), get()) }
}
