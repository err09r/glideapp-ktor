package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.features.user.UsersTable
import com.apsl.glideapp.features.vehicle.VehiclesTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RidesTable : Table("rides") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val userId = reference("user_id", UsersTable.id)
    val vehicleId = reference("vehicle_id", VehiclesTable.id)
    val startAddress = text("start_address").nullable().default(null)
    val finishAddress = text("finish_address").nullable().default(null)
    val startDateTime = datetime("start_date_time")
    val finishDateTime = datetime("finish_date_time").nullable().default(null)
    val status = enumeration<RideStatus>("status")
    val distance = double("distance").default(0.0)
    val averageSpeed = double("average_speed").default(0.0)
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
