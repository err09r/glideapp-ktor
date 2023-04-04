package com.apsl.glideapp.features.map

import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.dto.ZoneDto
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.zone.ZoneDao

class MapController(private val vehicleDao: VehicleDao, private val zoneDao: ZoneDao) {

    suspend fun getMapState() = runCatching {

        //TODO: execute in parallel (scope async x2)

        val availableVehicles = vehicleDao.getAllAvailableVehicles().map { entity ->
            VehicleDto(
                code = entity.code,
                batteryCharge = entity.batteryCharge,
                status = entity.status,
                coordinates = entity.coordinates
            )
        }

        val ridingZones = zoneDao.getAllRidingZones().map { entity ->
            ZoneDto(code = entity.code, title = entity.title, coordinates = entity.coordinates)
        }

        MapStateDto(ridingZones = ridingZones, availableVehicles = availableVehicles)
    }
}
