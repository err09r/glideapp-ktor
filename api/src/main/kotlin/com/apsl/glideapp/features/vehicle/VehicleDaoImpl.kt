package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import com.apsl.glideapp.database.converters.CoordinatesConverter
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class VehicleDaoImpl : VehicleDao {

    private fun ResultRow.toVehicleEntity(): VehicleEntity {
        return VehicleEntity(
            id = this[VehiclesTable.id],
            code = this[VehiclesTable.code],
            zoneCode = this[VehiclesTable.zoneCode],
            batteryCharge = this[VehiclesTable.batteryCharge],
            type = this[VehiclesTable.type],
            status = this[VehiclesTable.status],
            coordinates = CoordinatesConverter.toValue(this[VehiclesTable.coordinates]),
            createdAt = this[VehiclesTable.createdAt],
            updatedAt = this[VehiclesTable.updatedAt]
        )
    }

    override suspend fun getAllVehicles(): List<VehicleEntity> = query {
        VehiclesTable.selectAll().map { it.toVehicleEntity() }
    }

    override suspend fun getAllVehiclesByZoneCode(code: Int): List<VehicleEntity> = query {
        VehiclesTable
            .select { VehiclesTable.zoneCode eq code }
            .map { it.toVehicleEntity() }
    }

    override suspend fun getAllAvailableVehicles(): List<VehicleEntity> = query {
        VehiclesTable
            .select { VehiclesTable.status eq VehicleStatus.Available }
            .map { it.toVehicleEntity() }
    }

    override suspend fun getVehicleById(id: UUID): VehicleEntity? = query {
        VehiclesTable
            .select { VehiclesTable.id eq id }
            .map { it.toVehicleEntity() }
            .singleOrNull()
    }

    override suspend fun insertVehicle(
        code: Int,
        zoneCode: Int,
        batteryCharge: Int,
        type: VehicleType,
        status: VehicleStatus,
        coordinates: Coordinates
    ): VehicleEntity? = query {
        val insertStatement = VehiclesTable.insert {
            it[VehiclesTable.code] = code
            it[VehiclesTable.zoneCode] = zoneCode
            it[VehiclesTable.batteryCharge] = batteryCharge
            it[VehiclesTable.type] = type
            it[VehiclesTable.status] = status
            it[VehiclesTable.coordinates] = CoordinatesConverter.fromValue(coordinates)
            it[VehiclesTable.createdAt] = LocalDateTime.now()
            it[VehiclesTable.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toVehicleEntity()
    }

    override suspend fun updateVehicle(
        id: UUID,
        batteryCharge: Int,
        status: VehicleStatus,
        coordinates: Coordinates
    ): Boolean = query {
        VehiclesTable.update({ VehiclesTable.id eq id }) {
            it[VehiclesTable.batteryCharge] = batteryCharge
            it[VehiclesTable.status] = status
            it[VehiclesTable.coordinates] = CoordinatesConverter.fromValue(coordinates)
            it[VehiclesTable.updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun updateVehicle(id: UUID, status: VehicleStatus): Boolean = query {
        VehiclesTable.update({ VehiclesTable.id eq id }) {
            it[VehiclesTable.status] = status
            it[VehiclesTable.updatedAt] = LocalDateTime.now()
        } > 0
    }
}
