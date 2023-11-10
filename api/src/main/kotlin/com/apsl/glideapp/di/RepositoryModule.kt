package com.apsl.glideapp.di

import com.apsl.glideapp.features.ride.RideRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::RideRepository)
}
