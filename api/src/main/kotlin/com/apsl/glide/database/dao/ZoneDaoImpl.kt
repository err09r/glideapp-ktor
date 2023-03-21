package com.apsl.glide.database.dao

import com.apsl.glide.database.DatabaseFactory.query
import com.apsl.glide.database.converters.CoordinatesConverter
import com.apsl.glide.database.entities.ZoneEntity
import com.apsl.glide.database.tables.Zones
import com.apsl.glide.models.Coordinates
import com.apsl.glide.models.ZoneType
import com.apsl.glide.utils.now
import java.util.UUID
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ZoneDaoImpl : ZoneDao {

    private fun ResultRow.toZoneEntity(): ZoneEntity {
        return ZoneEntity(
            id = this[Zones.id],
            title = this[Zones.title],
            type = this[Zones.type],
            coordinates = CoordinatesConverter.toList(this[Zones.coordinates]),
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

    override suspend fun getZoneById(id: UUID): ZoneEntity? = query {
        Zones
            .select { Zones.id eq id }
            .map { it.toZoneEntity() }
            .singleOrNull()
    }

    override suspend fun insertZone(
        title: String,
        type: ZoneType,
        coordinates: List<Coordinates>
    ): ZoneEntity? = query {
        val insertStatement = Zones.insert {
            it[Zones.title] = title
            it[Zones.type] = type
            it[Zones.coordinates] = CoordinatesConverter.fromList(coordinates)
            it[Zones.createdAt] = LocalDateTime.now()
            it[Zones.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toZoneEntity()
    }
}
