package com.apsl.glideapp.api.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

interface RideDao {
    suspend fun getAllRides(): List<RideEntity>
    suspend fun getRidesByStatusAndUserId(status: RideStatus, userId: UUID): List<RideEntity>
    suspend fun getRidesByStatusAndUserId(
        status: RideStatus,
        userId: UUID,
        limit: Int? = null,
        offset: Long = 0L
    ): List<RideEntity>

    suspend fun getRideById(id: UUID): RideEntity?
    suspend fun insertRide(
        userId: UUID,
        vehicleId: UUID,
        startAddress: String?,
        startDateTime: LocalDateTime
    ): RideEntity?
    suspend fun updateRide(id: UUID, distance: Double): Boolean
    suspend fun updateRide(
        id: UUID,
        finishAddress: String?,
        finishDateTime: LocalDateTime,
        status: RideStatus,
        averageSpeed: Double
    ): Boolean
}
