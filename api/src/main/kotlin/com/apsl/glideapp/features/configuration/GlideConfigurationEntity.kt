package com.apsl.glideapp.features.configuration

import kotlinx.datetime.LocalDateTime

data class GlideConfigurationEntity(
    val countryCode: String,
    val unlockingFee: Double,
    val farePerMinute: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
