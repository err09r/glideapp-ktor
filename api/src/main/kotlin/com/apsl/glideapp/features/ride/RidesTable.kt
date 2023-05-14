package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.features.user.UsersTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RidesTable : Table("rides") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = reference("userId", UsersTable.id)
    val startAddress = text("startAddress")
    val finishAddress = text("finishAddress").nullable().default(null)
    val startDateTime = datetime("startDateTime")
    val finishDateTime = datetime("finishDateTime").nullable().default(null)
    val distance = double("distance")
    val status = enumeration<RideStatus>("status")
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(id)
}
