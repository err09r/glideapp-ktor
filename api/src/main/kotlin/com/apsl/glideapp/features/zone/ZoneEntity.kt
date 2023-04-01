package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.ZoneType
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
