package com.apsl.glideapp.api.features.ride.route

import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class RideCoordinatesEntity(
    val id: UUID,
    val rideId: UUID,
    val latitude: Double,
    val longitude: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
