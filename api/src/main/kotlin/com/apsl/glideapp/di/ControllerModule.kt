package com.apsl.glideapp.di

import com.apsl.glideapp.features.auth.AuthController
import com.apsl.glideapp.features.map.MapController
import com.apsl.glideapp.features.ride.RideController
import com.apsl.glideapp.features.transaction.TransactionController
import com.apsl.glideapp.features.user.UserController
import com.apsl.glideapp.features.zone.ZoneController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val controllerModule = module {
    singleOf(::UserController)
    singleOf(::ZoneController)
    singleOf(::AuthController)
    singleOf(::MapController)
    singleOf(::RideController)
    singleOf(::TransactionController)
}
