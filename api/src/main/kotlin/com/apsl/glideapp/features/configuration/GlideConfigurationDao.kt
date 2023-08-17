package com.apsl.glideapp.features.configuration

interface GlideConfigurationDao {
    suspend fun getAllGlideConfigurations(): List<GlideConfigurationEntity>
    suspend fun getGlideConfigurationByCountryCode(countryCode: String): GlideConfigurationEntity?
    suspend fun insertGlideConfiguration(
        countryCode: String,
        unlockingFee: Double,
        farePerMinute: Double
    ): GlideConfigurationEntity?
}
