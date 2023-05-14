package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

interface RideDao {
    suspend fun getUserHasActiveRides(userId: UUID): Boolean
    suspend fun insertRide(userId: UUID, startAddress: String, startDateTime: LocalDateTime): RideEntity?
    suspend fun updateRide(id: UUID, newDistance: Double): Boolean
    suspend fun updateRide(
        id: UUID,
        finishAddress: String,
        finishDateTime: LocalDateTime,
        status: RideStatus
    ): Boolean
}
