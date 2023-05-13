package com.apsl.glideapp.features.route

import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class RideCoordinatesDaoImpl : RideCoordinatesDao {

    private fun ResultRow.toRideCoordinatesEntity(): RideCoordinatesEntity {
        return RideCoordinatesEntity(
            id = this[RideCoordinatesTable.id],
            rideId = this[RideCoordinatesTable.rideId],
            latitude = this[RideCoordinatesTable.latitude],
            longitude = this[RideCoordinatesTable.longitude],
            createdAt = this[RideCoordinatesTable.createdAt],
            updateAt = this[RideCoordinatesTable.updatedAt]
        )
    }

    override suspend fun getAllRideCoordinatesByRideId(rideId: UUID): List<RideCoordinatesEntity> = query {
        RideCoordinatesTable
            .select { RideCoordinatesTable.rideId eq rideId }
            .map { it.toRideCoordinatesEntity() }
    }

    override suspend fun insertRideCoordinates(
        rideId: UUID,
        latitude: Double,
        longitude: Double
    ): RideCoordinatesEntity? = query {
        val insertStatement = RideCoordinatesTable.insert {
            it[RideCoordinatesTable.rideId] = rideId
            it[RideCoordinatesTable.latitude] = latitude
            it[RideCoordinatesTable.longitude] = longitude
            it[RideCoordinatesTable.createdAt] = LocalDateTime.now()
            it[RideCoordinatesTable.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toRideCoordinatesEntity()
    }
}
