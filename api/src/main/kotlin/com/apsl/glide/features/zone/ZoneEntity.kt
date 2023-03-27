package com.apsl.glide.features.zone

import ZoneType
import com.apsl.glide.Coordinates
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ZoneEntity(
    val code: Int,
    val title: String,
    val type: ZoneType,
    val coordinates: List<Coordinates>,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
