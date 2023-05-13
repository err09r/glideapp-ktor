package com.apsl.glideapp.features.route

import com.apsl.glideapp.features.ride.RidesTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RideCoordinatesTable : Table("ride_coordinates") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val rideId = reference("rideId", RidesTable.id)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(id)
}
