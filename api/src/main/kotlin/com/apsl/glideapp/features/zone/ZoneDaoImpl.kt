package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import com.apsl.glideapp.database.converters.CoordinatesConverter
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ZoneDaoImpl : ZoneDao {

    private fun ResultRow.toZoneEntity(): ZoneEntity {
        return ZoneEntity(
            id = this[ZonesTable.id],
            code = this[ZonesTable.code],
            title = this[ZonesTable.title],
            type = this[ZonesTable.type],
            coordinates = CoordinatesConverter.toValues(this[ZonesTable.coordinates]),
            createdAt = this[ZonesTable.createdAt],
            updatedAt = this[ZonesTable.updatedAt]
        )
    }

    override suspend fun getAllZones(): List<ZoneEntity> = query {
        ZonesTable
            .selectAll()
            .map { it.toZoneEntity() }
    }

    override suspend fun getZonesByType(type: ZoneType): List<ZoneEntity> = query {
        ZonesTable
            .select { ZonesTable.type eq type }
            .map { it.toZoneEntity() }
    }

    override suspend fun getZoneByCode(code: Int): ZoneEntity? = query {
        ZonesTable
            .select { ZonesTable.code eq code }
            .map { it.toZoneEntity() }
            .singleOrNull()
    }

    override suspend fun insertZone(
        code: Int,
        title: String,
        type: ZoneType,
        coordinates: List<Coordinates>
    ): ZoneEntity? = query {
        val insertStatement = ZonesTable.insert {
            it[ZonesTable.code] = code
            it[ZonesTable.title] = title
            it[ZonesTable.type] = type
            it[ZonesTable.coordinates] = CoordinatesConverter.fromValues(coordinates)
            it[ZonesTable.createdAt] = LocalDateTime.now()
            it[ZonesTable.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toZoneEntity()
    }
}
