package com.apsl.glideapp.api.features.config

import com.apsl.glideapp.common.models.VehicleType

interface GlideConfig {
    val unlockDistance: Double
    val unlockingFees: Map<VehicleType, Double>
    val faresPerMinute: Map<VehicleType, Double>
    val ranges: Map<VehicleType, Double>
    val voucherCodes: Map<String, Double>
}
