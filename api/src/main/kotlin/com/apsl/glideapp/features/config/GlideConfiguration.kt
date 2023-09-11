package com.apsl.glideapp.features.config

import com.apsl.glideapp.common.models.VehicleType

object GlideConfiguration {
    const val UNLOCK_DISTANCE = 50.0
    val unlockingFees = mapOf(
        VehicleType.Scooter to 3.3,
        VehicleType.Bike to 2.9
    )
    val faresPerMinute = mapOf(
        VehicleType.Scooter to 0.6,
        VehicleType.Bike to 0.7
    )
}
