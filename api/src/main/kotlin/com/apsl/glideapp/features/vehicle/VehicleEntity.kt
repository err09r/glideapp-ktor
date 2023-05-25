package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class VehicleEntity(
    val id: UUID,
    val code: Int,
    val zoneCode: Int,
    val batteryCharge: Int,
    val type: VehicleType,
    val status: VehicleStatus,
    val coordinates: Coordinates,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
