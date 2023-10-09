package com.apsl.glideapp.features.ride.route

import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
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
            updatedAt = this[RideCoordinatesTable.updatedAt]
        )
    }

    override suspend fun getLatestRideCoordinatesByRideId(rideId: UUID): RideCoordinatesEntity? = query {
        RideCoordinatesTable
            .select { RideCoordinatesTable.rideId eq rideId }
            .orderBy(column = RideCoordinatesTable.updatedAt, order = SortOrder.DESC)
            .limit(n = 1, offset = 1)
            .map { it.toRideCoordinatesEntity() }
            .singleOrNull()
    }

    override suspend fun getRideCoordinatesByRideId(rideId: UUID): List<RideCoordinatesEntity> = query {
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
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toRideCoordinatesEntity()
    }
}
