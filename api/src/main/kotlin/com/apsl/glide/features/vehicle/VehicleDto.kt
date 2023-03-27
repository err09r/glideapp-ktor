package com.apsl.glide.features.vehicle

import VehicleStatus
import com.apsl.glide.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class VehicleDto(
    val code: Int,
    val batteryCharge: Int,
    val status: VehicleStatus,
    val coordinates: Coordinates
)
