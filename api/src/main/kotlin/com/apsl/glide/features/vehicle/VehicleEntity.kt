package com.apsl.glide.features.vehicle

import VehicleStatus
import VehicleType
import com.apsl.glide.Coordinates
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class VehicleEntity(
    val code: Int,
    val zoneCode: Int,
    val batteryCharge: Int,
    val type: VehicleType,
    val status: VehicleStatus,
    val coordinates: Coordinates,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
