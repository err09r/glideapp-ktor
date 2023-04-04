package com.apsl.glideapp.di

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.features.user.UserDao
import com.apsl.glideapp.features.user.UserDaoImpl
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleDaoImpl
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.ZoneDaoImpl
import kotlin.random.Random
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

val daoModule = module(createdAtStart = true) {
    single<UserDao> { UserDaoImpl() }

    single<ZoneDao> {
        ZoneDaoImpl().apply {
            runBlocking {
                if (getAllZones().isEmpty()) {
                    launch {
                        insertZone(
                            code = 1,
                            title = "Słupsk",
                            type = ZoneType.Riding,
                            coordinates = emptyList()
                        )
                        insertZone(
                            code = 2,
                            title = "Koszalin",
                            type = ZoneType.Riding,
                            coordinates = emptyList()
                        )
                    }
                }
            }
        }
    }

    single<VehicleDao> {
        VehicleDaoImpl().apply {
            runBlocking {
                if (getAllVehicles().isEmpty()) {
                    repeat(100) {
                        launch {
                            insertVehicle(
                                code = it + 1,
                                zoneCode = if (it in 0..50) 1 else 2,
                                batteryCharge = Random.nextInt(40, 100),
                                type = VehicleType.Scooter,
                                status = VehicleStatus.Available,
                                coordinates = Coordinates(0.0, 0.0)
                            )
                        }
                    }
                }
            }
        }
    }
}
