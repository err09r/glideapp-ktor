package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.now
import com.apsl.glideapp.database.DatabaseFactory.query
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class RideDaoImpl : RideDao {

    override suspend fun getRidesByStatusAndUserId(status: RideStatus, userId: UUID): List<RideEntity> = query {
        RidesTable
            .select { (RidesTable.userId eq userId) and (RidesTable.status eq status) }
            .orderBy(column = RidesTable.updatedAt, order = SortOrder.DESC)
            .map { it.toRideEntity() }
    }

    override suspend fun getRidesByStatusAndUserId(
        status: RideStatus,
        userId: UUID,
        limit: Int?,
        offset: Long
    ): List<RideEntity> = query {
        RidesTable
            .select { (RidesTable.userId eq userId) and (RidesTable.status eq status) }
            .orderBy(column = RidesTable.updatedAt, order = SortOrder.DESC)
            .apply {
                if (limit != null) {
                    this.limit(n = limit, offset = offset)
                }
            }
            .map { it.toRideEntity() }
    }

    override suspend fun getRideById(id: UUID): RideEntity? = query {
        RidesTable
            .select { RidesTable.id eq id }
            .map { it.toRideEntity() }
            .singleOrNull()
    }

    override suspend fun insertRide(
        userId: UUID,
        vehicleId: UUID,
        startAddress: String?,
        startDateTime: LocalDateTime
    ): RideEntity? = query {
        val insertStatement = RidesTable.insert {
            it[RidesTable.userId] = userId
            it[RidesTable.vehicleId] = vehicleId
            it[RidesTable.startAddress] = startAddress
            it[RidesTable.startDateTime] = startDateTime
            it[status] = RideStatus.Started
            it[createdAt] = LocalDateTime.now()
            it[updatedAt] = LocalDateTime.now()
        }
        insertStatement.resultedValues?.singleOrNull()?.toRideEntity()
    }

    override suspend fun updateRide(id: UUID, distance: Double): Boolean = query {
        RidesTable.update({ RidesTable.id eq id }) {
            it[RidesTable.distance] = distance
            it[updatedAt] = LocalDateTime.now()
        } > 0
    }

    override suspend fun updateRide(
        id: UUID,
        finishAddress: String?,
        finishDateTime: LocalDateTime,
        status: RideStatus,
        averageSpeed: Double
    ): Boolean = query {
        RidesTable.update({ RidesTable.id eq id }) {
            it[RidesTable.finishAddress] = finishAddress
            it[RidesTable.finishDateTime] = finishDateTime
            it[RidesTable.status] = status
            it[RidesTable.averageSpeed] = averageSpeed
            it[updatedAt] = LocalDateTime.now()
        } > 0
    }

    private fun ResultRow.toRideEntity(): RideEntity {
        return RideEntity(
            id = this[RidesTable.id],
            userId = this[RidesTable.userId],
            vehicleId = this[RidesTable.vehicleId],
            startAddress = this[RidesTable.startAddress],
            finishAddress = this[RidesTable.finishAddress],
            startDateTime = this[RidesTable.startDateTime],
            finishDateTime = this[RidesTable.finishDateTime],
            status = this[RidesTable.status],
            distance = this[RidesTable.distance],
            averageSpeed = this[RidesTable.averageSpeed],
            createdAt = this[RidesTable.createdAt],
            updatedAt = this[RidesTable.updatedAt]
        )
    }
}
