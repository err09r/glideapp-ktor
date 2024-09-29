package com.apsl.glideapp.api.features.zone

import com.apsl.glideapp.api.database.DatabaseFactory.query
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.util.now
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class ZoneDaoImpl : ZoneDao {

    override suspend fun getAllZones(): List<ZoneEntity> = query {
        ZonesTable
            .selectAll()
            .map { it.toZoneEntity() }
    }

    override suspend fun getZonesByType(type: ZoneType): List<ZoneEntity> = query {
        ZonesTable
            .selectAll()
            .where { ZonesTable.type eq type }
            .map { it.toZoneEntity() }
    }

    override suspend fun getZoneByCode(code: Int): ZoneEntity? = query {
        ZonesTable
            .selectAll()
            .where { ZonesTable.code eq code }
            .map { it.toZoneEntity() }
            .singleOrNull()
    }

    override suspend fun insertZone(code: Int, title: String, type: ZoneType): ZoneEntity? = query {
        val insertStatement = ZonesTable.insert {
            it[ZonesTable.code] = code
            it[ZonesTable.title] = title
            it[ZonesTable.type] = type
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toZoneEntity()
    }

    private fun ResultRow.toZoneEntity(): ZoneEntity {
        return ZoneEntity(
            id = this[ZonesTable.id],
            code = this[ZonesTable.code],
            title = this[ZonesTable.title],
            type = this[ZonesTable.type],
            createdAt = this[ZonesTable.createdAt],
            updatedAt = this[ZonesTable.updatedAt]
        )
    }
}
