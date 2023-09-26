package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.util.UUID

interface VehicleDao {
    suspend fun getAllVehicles(): List<VehicleEntity>
    suspend fun getAllVehiclesByZoneCode(code: Int): List<VehicleEntity>
    suspend fun getAllAvailableVehicles(): List<VehicleEntity>
    suspend fun getVehicleById(id: UUID): VehicleEntity?
    suspend fun updateVehicle(id: UUID, status: VehicleStatus): Boolean
    suspend fun updateVehicle(
        id: UUID,
        batteryCharge: Int,
        status: VehicleStatus,
        latitude: Double,
        longitude: Double
    ): Boolean

    suspend fun insertVehicle(
        code: Int,
        zoneCode: Int,
        batteryCharge: Int,
        type: VehicleType,
        status: VehicleStatus,
        latitude: Double,
        longitude: Double
    ): VehicleEntity?
}
