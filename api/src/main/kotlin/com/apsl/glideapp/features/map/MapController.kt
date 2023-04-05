package com.apsl.glideapp.features.map

import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.dto.ZoneDto
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleService
import com.apsl.glideapp.features.zone.ZoneDao
import kotlinx.coroutines.flow.map

class MapController(private val vehicleDao: VehicleDao, private val zoneDao: ZoneDao, vehicleService: VehicleService) {

    val mapStateFlow = vehicleService.vehicleListChangesFlow.map {
        val availableVehicles = vehicleDao.getAllAvailableVehicles().map { entity ->
            VehicleDto(
                id = entity.id.toString(),
                code = entity.code,
                batteryCharge = entity.batteryCharge,
                status = entity.status,
                coordinates = entity.coordinates
            )
        }

        val ridingZones = zoneDao.getAllRidingZones().map { entity ->
            ZoneDto(
                id = entity.id.toString(),
                code = entity.code,
                title = entity.title,
                coordinates = entity.coordinates
            )
        }

        MapStateDto(ridingZones = ridingZones, availableVehicles = availableVehicles)
    }
}
