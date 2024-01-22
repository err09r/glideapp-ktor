package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

data class ZoneEntity(
    val id: UUID,
    val code: Int,
    val title: String,
    val type: ZoneType,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
