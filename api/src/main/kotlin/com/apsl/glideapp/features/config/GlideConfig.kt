package com.apsl.glideapp.features.config

import com.apsl.glideapp.common.models.VehicleType

interface GlideConfig {
    val unlockDistance: Double
    val unlockingFees: Map<VehicleType, Double>
    val faresPerMinute: Map<VehicleType, Double>
    val voucherCodes: Map<String, Double>
}
