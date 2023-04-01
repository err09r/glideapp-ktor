package com.apsl.glideapp.di

import com.apsl.glideapp.features.user.UserDao
import com.apsl.glideapp.features.user.UserDaoImpl
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleDaoImpl
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.ZoneDaoImpl
import org.koin.dsl.module

val daoModule = module(createdAtStart = true) {
    single<UserDao> { UserDaoImpl() }
    single<ZoneDao> { ZoneDaoImpl() }
    single<VehicleDao> { VehicleDaoImpl() }
}
