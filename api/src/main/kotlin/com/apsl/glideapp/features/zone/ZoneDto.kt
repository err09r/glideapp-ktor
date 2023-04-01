package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class ZoneDto(
    val code: Int,
    val title: String,
    val coordinates: List<Coordinates>
)
