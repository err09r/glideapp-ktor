package com.apsl.glideapp.features.route

import com.apsl.glideapp.features.ride.RidesTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RideCoordinatesTable : Table("ride_coordinates") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val rideId = reference("ride_id", RidesTable.id)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
