package com.apsl.glideapp.api.di

import com.apsl.glideapp.api.features.auth.AuthController
import com.apsl.glideapp.api.features.config.AppConfigController
import com.apsl.glideapp.api.features.map.MapController
import com.apsl.glideapp.api.features.ride.RideController
import com.apsl.glideapp.api.features.transaction.TransactionController
import com.apsl.glideapp.api.features.user.UserController
import com.apsl.glideapp.api.features.zone.ZoneController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val controllerModule = module {
    singleOf(::UserController)
    singleOf(::ZoneController)
    singleOf(::AuthController)
    singleOf(::MapController)
    singleOf(::RideController)
    singleOf(::TransactionController)
    singleOf(::AppConfigController)
}
