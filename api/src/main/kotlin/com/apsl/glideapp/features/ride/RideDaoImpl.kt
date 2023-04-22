package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import com.apsl.glideapp.features.ride.Rides.userId
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class RideDaoImpl : RideDao {

    private fun ResultRow.toRideEntity(): RideEntity {
        return RideEntity(
            id = this[Rides.id],
            userId = this[Rides.userId],
            startAddress = this[Rides.startAddress],
            finishAddress = this[Rides.finishAddress],
            startDateTime = this[Rides.startDateTime],
            finishDateTime = this[Rides.finishDateTime],
            status = this[Rides.status],
            createdAt = this[Rides.createdAt],
            updateAt = this[Rides.updatedAt]
        )
    }

    override suspend fun getUserHasActiveRides(userId: UUID): Boolean = query {
        Rides
            .select { (Rides.userId eq userId) and (Rides.status neq RideStatus.Finished) }
            .count() > 0
    }

    override suspend fun insertRide(
        userId: UUID,
        startAddress: String,
        startDateTime: LocalDateTime
    ): RideEntity? = query {
        val insertStatement = Rides.insert {
            it[Rides.userId] = userId
            it[Rides.startAddress] = startAddress
            it[Rides.startDateTime] = startDateTime
            it[Rides.status] = RideStatus.Started
            it[Rides.createdAt] = LocalDateTime.now()
            it[Rides.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toRideEntity()
    }

    override suspend fun updateRide(
        id: UUID,
        finishAddress: String,
        finishDateTime: LocalDateTime,
        status: RideStatus
    ): Boolean = query {
        Rides.update({ Rides.userId eq userId }) {
            it[Rides.finishAddress] = finishAddress
            it[Rides.finishDateTime] = finishDateTime
            it[Rides.status] = status
            it[Rides.updatedAt] = LocalDateTime.now()
        } > 0
    }
}
