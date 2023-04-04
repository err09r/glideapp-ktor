package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.dto.VehicleDto

class VehicleController(private val vehicleDao: VehicleDao) {

    suspend fun getAllVehicles() = runCatching {
        vehicleDao.getAllAvailableVehicles().map { entity ->
            VehicleDto(
                code = entity.code,
                batteryCharge = entity.batteryCharge,
                status = entity.status,
                coordinates = entity.coordinates
            )
        }
    }
}
