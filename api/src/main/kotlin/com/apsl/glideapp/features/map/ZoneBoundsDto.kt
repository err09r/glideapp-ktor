package com.apsl.glideapp.features.map

import com.apsl.glideapp.common.models.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class ZoneBoundsDto(
    val topLeftBound: Coordinates,
    val bottomLeftBound: Coordinates
)
