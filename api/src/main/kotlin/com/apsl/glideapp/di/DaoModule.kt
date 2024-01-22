package com.apsl.glideapp.di

import com.apsl.glideapp.features.ride.RideDao
import com.apsl.glideapp.features.ride.RideDaoImpl
import com.apsl.glideapp.features.ride.route.RideCoordinatesDao
import com.apsl.glideapp.features.ride.route.RideCoordinatesDaoImpl
import com.apsl.glideapp.features.transaction.TransactionDao
import com.apsl.glideapp.features.transaction.TransactionDaoImpl
import com.apsl.glideapp.features.user.UserDao
import com.apsl.glideapp.features.user.UserDaoImpl
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleDaoImpl
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.ZoneDaoImpl
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesDao
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesDaoImpl
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
