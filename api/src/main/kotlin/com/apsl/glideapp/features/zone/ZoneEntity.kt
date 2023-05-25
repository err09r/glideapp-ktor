package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class ZoneEntity(
    val id: UUID,
    val code: Int,
    val title: String,
    val type: ZoneType,
    val coordinates: List<Coordinates>,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
