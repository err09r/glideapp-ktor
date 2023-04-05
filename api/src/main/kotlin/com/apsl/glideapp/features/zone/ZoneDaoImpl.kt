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
            id = this[Zones.id],
            code = this[Zones.code],
            title = this[Zones.title],
            type = this[Zones.type],
            coordinates = CoordinatesConverter.toValues(this[Zones.coordinates]),
            createdAt = this[Zones.createdAt],
            updateAt = this[Zones.updatedAt]
        )
    }

    override suspend fun getAllZones(): List<ZoneEntity> = query {
        Zones.selectAll().map { it.toZoneEntity() }
    }

    override suspend fun getAllRidingZones(): List<ZoneEntity> = query {
        Zones
            .select { Zones.type eq ZoneType.Riding }
            .map { it.toZoneEntity() }
    }

    override suspend fun getAllNoParkingZones(): List<ZoneEntity> = query {
        Zones
            .select { Zones.type eq ZoneType.NoParking }
            .map { it.toZoneEntity() }
    }

    override suspend fun getZoneByCode(code: Int): ZoneEntity? = query {
        Zones
            .select { Zones.code eq code }
            .map { it.toZoneEntity() }
            .singleOrNull()
    }

    override suspend fun insertZone(
        code: Int,
        title: String,
        type: ZoneType,
        coordinates: List<Coordinates>
    ): ZoneEntity? = query {
        val insertStatement = Zones.insert {
            it[Zones.code] = code
            it[Zones.title] = title
            it[Zones.type] = type
            it[Zones.coordinates] = CoordinatesConverter.fromValues(coordinates)
            it[Zones.createdAt] = LocalDateTime.now()
            it[Zones.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toZoneEntity()
    }
}
