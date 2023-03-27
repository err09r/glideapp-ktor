package com.apsl.glide.features.vehicle

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
