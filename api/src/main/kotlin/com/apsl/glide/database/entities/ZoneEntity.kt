package com.apsl.glide.database.entities

import com.apsl.glide.models.Coordinates
import com.apsl.glide.models.ZoneType
import com.apsl.glide.utils.UUID
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ZoneEntity(
    val id: UUID,
    val title: String,
    val type: ZoneType,
    val coordinates: List<Coordinates>,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
