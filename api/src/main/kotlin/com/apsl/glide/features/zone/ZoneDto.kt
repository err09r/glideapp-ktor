package com.apsl.glide.features.zone

import com.apsl.glide.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class ZoneDto(
    val code: Int,
    val title: String,
    val coordinates: List<Coordinates>
)
