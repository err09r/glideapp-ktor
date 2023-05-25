package com.apsl.glideapp.features.route

import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class RideCoordinatesEntity(
    val id: UUID,
    val rideId: UUID,
    val latitude: Double,
    val longitude: Double,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
