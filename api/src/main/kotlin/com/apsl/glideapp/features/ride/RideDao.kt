package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

interface RideDao {
    suspend fun getAllRidesByStatusAndUserId(status: RideStatus, userId: UUID): List<RideEntity>
    suspend fun getRideById(id: UUID): RideEntity?
    suspend fun insertRide(userId: UUID, startAddress: String?, startDateTime: LocalDateTime): RideEntity?
    suspend fun updateRide(id: UUID, distance: Double): Boolean
    suspend fun updateRide(
        id: UUID,
        finishAddress: String?,
        finishDateTime: LocalDateTime,
        status: RideStatus,
        averageSpeed: Double
    ): Boolean
}
