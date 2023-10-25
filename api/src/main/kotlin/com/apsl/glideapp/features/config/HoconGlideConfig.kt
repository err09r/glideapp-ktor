package com.apsl.glideapp.features.config

import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.util.capitalized
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.HoconConfigLoader

class HoconGlideConfig(filename: String) : GlideConfig {

    private val config: ApplicationConfig = requireNotNull(HoconConfigLoader().load(filename))

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

    override val ranges: Map<VehicleType, Double>
        get() = config.config("range").toMap()
            .mapKeys { VehicleType.valueOf(it.key.capitalized()) }
            .mapValues { it.value.toString().toDouble() }

    override val voucherCodes: Map<String, Double>
        get() = config.config("voucherCode").toMap()
            .mapValues { it.value.toString().toDouble() }
}
