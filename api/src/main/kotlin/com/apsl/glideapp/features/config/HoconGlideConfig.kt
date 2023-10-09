package com.apsl.glideapp.features.config

import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.util.capitalized
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.HoconConfigLoader

object HoconGlideConfig : GlideConfig {

    private const val FILE_NAME = "glideapp.conf"

    private val config: ApplicationConfig = requireNotNull(HoconConfigLoader().load(FILE_NAME))

    override val unlockDistance: Double
        get() = config.property("unlockDistance").getString().toDouble()

    override val unlockingFees: Map<VehicleType, Double>
        get() = config.config("unlockingFee").toMap()
            .mapKeys { VehicleType.valueOf(it.key.capitalized()) }
            .mapValues { it.value.toString().toDouble() }

    override val faresPerMinute: Map<VehicleType, Double>
        get() = config.config("farePerMinute").toMap()
            .mapKeys { VehicleType.valueOf(it.key.capitalized()) }
            .mapValues { it.value.toString().toDouble() }
}
