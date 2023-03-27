package com.apsl.glide.di

import com.apsl.glide.features.user.UserDao
import com.apsl.glide.features.user.UserDaoImpl
import com.apsl.glide.features.vehicle.VehicleDao
import com.apsl.glide.features.vehicle.VehicleDaoImpl
import com.apsl.glide.features.zone.ZoneDao
import com.apsl.glide.features.zone.ZoneDaoImpl
import org.koin.dsl.module

val daoModule = module(createdAtStart = true) {
    single<UserDao> { UserDaoImpl() }
    single<ZoneDao> { ZoneDaoImpl() }
    single<VehicleDao> { VehicleDaoImpl() }
}
