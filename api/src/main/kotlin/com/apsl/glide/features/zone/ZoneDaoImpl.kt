package com.apsl.glide.features.zone

import ZoneType
import com.apsl.glide.Coordinates
import com.apsl.glide.database.DatabaseFactory.query
import com.apsl.glide.database.converters.CoordinatesConverter
import com.apsl.glide.utils.now
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class ZoneDaoImpl : ZoneDao {

    private fun ResultRow.toZoneEntity(): ZoneEntity {
        return ZoneEntity(
            code = this[Zones.code],
            title = this[Zones.title],
            type = this[Zones.type],
            coordinates = CoordinatesConverter.toValues(this[Zones.coordinates]),
            createdAt = this[Zones.createdAt],
            updateAt = this[Zones.updatedAt]
        )
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
