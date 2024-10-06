package com.apsl.glideapp.api.di

import com.apsl.glideapp.api.features.vehicle.VehicleService
import com.apsl.glideapp.api.features.vehicle.VehicleServiceImpl
import io.ktor.util.logging.KtorSimpleLogger
import io.ktor.util.logging.error
import org.koin.dsl.module

val serviceModule = module {
    single<VehicleService>(createdAtStart = true) {
        VehicleServiceImpl(
            vehicleDao = get(),
            zoneDao = get(),
            zoneCoordinatesDao = get(),
            isGenerationModeEnabled = isGenerationModeEnabled()
        )
    }
}

private fun isGenerationModeEnabled(): Boolean {
    var isGenerationModeEnabled = false
    val logger = KtorSimpleLogger("ServiceModule.isGenerationModeEnabled")
    try {
        isGenerationModeEnabled = System.getenv()["GENERATE_MODE"].toBoolean()
    } catch (e: Exception) {
        logger.error(e)
    } finally {
        logger.info("isGenerationModeEnabled: $isGenerationModeEnabled")
    }
    return isGenerationModeEnabled
}
