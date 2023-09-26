package com.apsl.glideapp.features.zone.bounds

import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class ZoneCoordinatesEntity(
    val id: UUID,
    val zoneCode: Int,
    val latitude: Double,
    val longitude: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
