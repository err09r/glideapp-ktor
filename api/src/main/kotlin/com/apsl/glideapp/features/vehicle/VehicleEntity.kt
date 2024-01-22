package com.apsl.glideapp.features.vehicle

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
    val latitude: Double,
    val longitude: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
