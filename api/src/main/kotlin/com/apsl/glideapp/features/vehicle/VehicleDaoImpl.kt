package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
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
            id = this[Vehicles.id],
            code = this[Vehicles.code],
            zoneCode = this[Vehicles.zoneCode],
            batteryCharge = this[Vehicles.batteryCharge],
            type = this[Vehicles.type],
            status = this[Vehicles.status],
            coordinates = CoordinatesConverter.toValue(this[Vehicles.coordinates]),
            createdAt = this[Vehicles.createdAt],
            updateAt = this[Vehicles.updatedAt]
        )
    }

    override suspend fun getAllVehicles(): List<VehicleEntity> = query {
        Vehicles.selectAll().map { it.toVehicleEntity() }
    }

    override suspend fun getAllVehiclesByZoneCode(code: Int): List<VehicleEntity> = query {
        Vehicles
            .select { Vehicles.zoneCode eq code }
            .map { it.toVehicleEntity() }
    }

    override suspend fun getAllAvailableVehicles(): List<VehicleEntity> = query {
        Vehicles
            .select { Vehicles.status eq VehicleStatus.Available }
            .map { it.toVehicleEntity() }
    }

    override suspend fun getVehicleByCode(code: Int): VehicleEntity? = query {
        Vehicles
            .select { Vehicles.code eq code }
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
        val insertStatement = Vehicles.insert {
            it[Vehicles.code] = code
            it[Vehicles.zoneCode] = zoneCode
            it[Vehicles.batteryCharge] = batteryCharge
            it[Vehicles.type] = type
            it[Vehicles.status] = status
            it[Vehicles.coordinates] = CoordinatesConverter.fromValue(coordinates)
            it[Vehicles.createdAt] = LocalDateTime.now()
            it[Vehicles.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toVehicleEntity()
    }

    override suspend fun updateVehicle(
        code: Int,
        batteryCharge: Int,
        status: VehicleStatus,
        coordinates: Coordinates
    ): Boolean = query {
        Vehicles.update({ Vehicles.code eq code }) {
            it[Vehicles.batteryCharge] = batteryCharge
            it[Vehicles.status] = status
            it[Vehicles.coordinates] = CoordinatesConverter.fromValue(coordinates)
            it[Vehicles.updatedAt] = LocalDateTime.now()
        } > 0
    }
}
