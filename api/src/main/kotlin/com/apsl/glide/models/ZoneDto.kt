package com.apsl.glide.models

import com.apsl.glide.utils.UUID
import kotlinx.serialization.Serializable

@Serializable
data class ZoneDto(
    val id: UUID,
    val title: String,
    val coordinates: List<Coordinates>
)
