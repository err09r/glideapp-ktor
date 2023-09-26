package com.apsl.glideapp.features.zone.bounds

import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class ZoneCoordinatesDaoImpl : ZoneCoordinatesDao {

    private fun ResultRow.toZoneCoordinatesEntity(): ZoneCoordinatesEntity {
        return ZoneCoordinatesEntity(
            id = this[ZoneCoordinatesTable.id],
            zoneCode = this[ZoneCoordinatesTable.zoneCode],
            latitude = this[ZoneCoordinatesTable.latitude],
            longitude = this[ZoneCoordinatesTable.longitude],
            createdAt = this[ZoneCoordinatesTable.createdAt],
            updatedAt = this[ZoneCoordinatesTable.updatedAt]
        )
    }

    override suspend fun getLatestZoneCoordinatesByZoneCode(zoneCode: Int): ZoneCoordinatesEntity? = query {
        ZoneCoordinatesTable
            .select { ZoneCoordinatesTable.zoneCode eq zoneCode }
            .orderBy(column = ZoneCoordinatesTable.updatedAt, order = SortOrder.DESC)
            .limit(n = 1, offset = 1)
            .map { it.toZoneCoordinatesEntity() }
            .singleOrNull()
    }

    override suspend fun getAllZoneCoordinatesByZoneCode(zoneCode: Int): List<ZoneCoordinatesEntity> = query {
        ZoneCoordinatesTable
            .select { ZoneCoordinatesTable.zoneCode eq zoneCode }
            .map { it.toZoneCoordinatesEntity() }
    }

    override suspend fun insertZoneCoordinates(
        zoneCode: Int,
        latitude: Double,
        longitude: Double
    ): ZoneCoordinatesEntity? = query {
        val insertStatement = ZoneCoordinatesTable.insert {
            it[ZoneCoordinatesTable.zoneCode] = zoneCode
            it[ZoneCoordinatesTable.latitude] = latitude
            it[ZoneCoordinatesTable.longitude] = longitude
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toZoneCoordinatesEntity()
    }
}
