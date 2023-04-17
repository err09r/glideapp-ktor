package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType

interface VehicleDao {
    suspend fun getAllVehicles(): List<VehicleEntity>
    suspend fun getAllVehiclesByZoneCode(code: Int): List<VehicleEntity>
    suspend fun getAllAvailableVehicles(): List<VehicleEntity>
    suspend fun getVehicleByCode(code: Int): VehicleEntity?
    suspend fun insertVehicle(
        code: Int,
        zoneCode: Int,
        batteryCharge: Int,
        type: VehicleType,
        status: VehicleStatus,
        coordinates: Coordinates
    ): VehicleEntity?

    suspend fun updateVehicle(code: Int, batteryCharge: Int, status: VehicleStatus, coordinates: Coordinates): Boolean
}
