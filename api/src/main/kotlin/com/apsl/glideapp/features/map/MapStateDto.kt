package com.apsl.glideapp.features.map

import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.dto.ZoneDto
import kotlinx.serialization.Serializable

@Serializable
data class MapStateDto(
    val ridingZones: List<ZoneDto>,
    val availableVehicles: List<VehicleDto>
)
