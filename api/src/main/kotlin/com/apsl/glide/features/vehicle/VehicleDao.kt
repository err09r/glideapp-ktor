package com.apsl.glide.features.vehicle

import VehicleStatus
import VehicleType
import com.apsl.glide.Coordinates

interface VehicleDao {
    suspend fun getAllVehiclesByZoneCode(code: Int): List<VehicleEntity>
    suspend fun getAllAvailableVehicles(): List<VehicleEntity>
    suspend fun getVehicleByCode(code: Int): VehicleEntity?
    suspend fun updateVehicle(code: Int, batteryCharge: Int, status: VehicleStatus, coordinates: Coordinates): Boolean
    suspend fun insertVehicle(
        code: Int,
        zoneCode: Int,
        batteryCharge: Int,
        type: VehicleType,
        status: VehicleStatus,
        coordinates: Coordinates
    ): VehicleEntity?
}
