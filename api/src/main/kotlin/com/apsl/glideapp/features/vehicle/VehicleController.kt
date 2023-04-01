package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.dto.VehicleDto

class VehicleController(private val vehicleDao: VehicleDao) {

    suspend fun getAllVehicles() = runCatching {
        vehicleDao.getAllAvailableVehicles().map { vehicleEntity ->
            VehicleDto(
                code = vehicleEntity.code,
                batteryCharge = vehicleEntity.batteryCharge,
                status = vehicleEntity.status,
                coordinates = vehicleEntity.coordinates
            )
        }
    }
}
