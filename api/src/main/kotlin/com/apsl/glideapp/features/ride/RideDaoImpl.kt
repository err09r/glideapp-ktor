package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class RideDaoImpl : RideDao {

    private fun ResultRow.toRideEntity(): RideEntity {
        return RideEntity(
            id = this[RidesTable.id],
            userId = this[RidesTable.userId],
            startAddress = this[RidesTable.startAddress],
            finishAddress = this[RidesTable.finishAddress],
            startDateTime = this[RidesTable.startDateTime],
            finishDateTime = this[RidesTable.finishDateTime],
            distance = this[RidesTable.distance],
            status = this[RidesTable.status],
            createdAt = this[RidesTable.createdAt],
            updateAt = this[RidesTable.updatedAt]
        )
    }

    override suspend fun getUserHasActiveRides(userId: UUID): Boolean = query {
        RidesTable
            .select { (RidesTable.userId eq userId) and (RidesTable.status neq RideStatus.Finished) }
            .count() > 0
    }

    override suspend fun insertRide(
        userId: UUID,
        startAddress: String,
        startDateTime: LocalDateTime
    ): RideEntity? = query {
        val insertStatement = RidesTable.insert {
            it[RidesTable.userId] = userId
            it[RidesTable.startAddress] = startAddress
            it[RidesTable.startDateTime] = startDateTime
            it[RidesTable.status] = RideStatus.Started
            it[RidesTable.createdAt] = LocalDateTime.now()
            it[RidesTable.updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toRideEntity()
    }

    override suspend fun updateRide(id: UUID, newDistance: Double): Boolean = query {
        RidesTable.update({ RidesTable.id eq id }) {
            it[RidesTable.distance] = newDistance
            it[RidesTable.updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun updateRide(
        id: UUID,
        finishAddress: String,
        finishDateTime: LocalDateTime,
        status: RideStatus
    ): Boolean = query {
        RidesTable.update({ RidesTable.id eq id }) {
            it[RidesTable.finishAddress] = finishAddress
            it[RidesTable.finishDateTime] = finishDateTime
            it[RidesTable.status] = status
            it[RidesTable.updatedAt] = LocalDateTime.now()
        } > 0
    }
}
