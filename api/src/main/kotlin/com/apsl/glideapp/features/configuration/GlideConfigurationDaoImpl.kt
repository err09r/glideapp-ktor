package com.apsl.glideapp.features.configuration

import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class GlideConfigurationDaoImpl : GlideConfigurationDao {

    private fun ResultRow.toGlideConfigurationEntity(): GlideConfigurationEntity {
        return GlideConfigurationEntity(
            countryCode = this[GlideConfigurationsTable.countryCode],
            unlockingFee = this[GlideConfigurationsTable.unlockingFee],
            farePerMinute = this[GlideConfigurationsTable.farePerMinute],
            createdAt = this[GlideConfigurationsTable.createdAt],
            updatedAt = this[GlideConfigurationsTable.updatedAt]
        )
    }

    override suspend fun getAllGlideConfigurations(): List<GlideConfigurationEntity> = query {
        GlideConfigurationsTable
            .selectAll()
            .map { it.toGlideConfigurationEntity() }
    }

    override suspend fun getGlideConfigurationByCountryCode(countryCode: String): GlideConfigurationEntity? = query {
        GlideConfigurationsTable
            .select { GlideConfigurationsTable.countryCode eq countryCode }
            .map { it.toGlideConfigurationEntity() }
            .singleOrNull()
    }

    override suspend fun insertGlideConfiguration(
        countryCode: String,
        unlockingFee: Double,
        farePerMinute: Double
    ): GlideConfigurationEntity? = query {
        val insertStatement = GlideConfigurationsTable.insert {
            it[GlideConfigurationsTable.countryCode] = countryCode
            it[GlideConfigurationsTable.unlockingFee] = unlockingFee
            it[GlideConfigurationsTable.farePerMinute] = farePerMinute
            it[GlideConfigurationsTable.createdAt] = LocalDateTime.now()
            it[GlideConfigurationsTable.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toGlideConfigurationEntity()
    }
}
