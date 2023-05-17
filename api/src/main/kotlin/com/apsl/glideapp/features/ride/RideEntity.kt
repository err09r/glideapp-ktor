package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class RideEntity(
    val id: UUID,
    val userId: UUID,
    val startAddress: String?,
    val finishAddress: String?,
    val startDateTime: LocalDateTime,
    val finishDateTime: LocalDateTime?,
    val status: RideStatus,
    val distance: Double,
    val averageSpeed: Double,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
)
