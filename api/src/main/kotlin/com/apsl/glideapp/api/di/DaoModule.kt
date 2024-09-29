package com.apsl.glideapp.api.di

import com.apsl.glideapp.api.features.ride.RideDao
import com.apsl.glideapp.api.features.ride.RideDaoImpl
import com.apsl.glideapp.api.features.ride.route.RideCoordinatesDao
import com.apsl.glideapp.api.features.ride.route.RideCoordinatesDaoImpl
import com.apsl.glideapp.api.features.transaction.TransactionDao
import com.apsl.glideapp.api.features.transaction.TransactionDaoImpl
import com.apsl.glideapp.api.features.user.UserDao
import com.apsl.glideapp.api.features.user.UserDaoImpl
import com.apsl.glideapp.api.features.vehicle.VehicleDao
import com.apsl.glideapp.api.features.vehicle.VehicleDaoImpl
import com.apsl.glideapp.api.features.zone.ZoneDao
import com.apsl.glideapp.api.features.zone.ZoneDaoImpl
import com.apsl.glideapp.api.features.zone.bounds.ZoneCoordinatesDao
import com.apsl.glideapp.api.features.zone.bounds.ZoneCoordinatesDaoImpl
import org.koin.dsl.module

val daoModule = module {
    single<ZoneDao> { ZoneDaoImpl() }
    single<ZoneCoordinatesDao> { ZoneCoordinatesDaoImpl() }
    single<VehicleDao> { VehicleDaoImpl() }
    single<UserDao> { UserDaoImpl() }
    single<RideDao> { RideDaoImpl() }
    single<RideCoordinatesDao> { RideCoordinatesDaoImpl() }
    single<TransactionDao> { TransactionDaoImpl() }
}
