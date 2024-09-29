package com.apsl.glideapp.api.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class RideEntity(
    val id: UUID,
    val userId: UUID,
    val vehicleId: UUID,
    val startAddress: String?,
    val finishAddress: String?,
    val startDateTime: LocalDateTime,
    val finishDateTime: LocalDateTime?,
    val status: RideStatus,
    val distance: Double,
    val averageSpeed: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
