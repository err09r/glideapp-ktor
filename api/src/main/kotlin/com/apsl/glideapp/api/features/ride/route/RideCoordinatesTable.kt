package com.apsl.glideapp.api.features.ride.route

import com.apsl.glideapp.api.features.ride.RidesTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object RideCoordinatesTable : UUIDTable("ride_coordinates") {
    val rideId = reference("ride_id", RidesTable.id)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
