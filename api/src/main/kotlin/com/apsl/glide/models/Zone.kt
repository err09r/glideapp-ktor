package com.apsl.glide.models

import com.apsl.glide.utils.UUID
import kotlinx.serialization.Serializable

@Serializable
data class Zone(
    val id: UUID,
    val title: String,
    val type: ZoneType,
    val coordinates: List<Coordinates>
)
