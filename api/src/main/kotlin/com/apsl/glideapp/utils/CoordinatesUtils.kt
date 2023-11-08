@file:Suppress("Unused")

package com.apsl.glideapp.utils

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.features.ride.route.RideCoordinatesEntity
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesEntity

fun RideCoordinatesEntity.toCoordinates(): Coordinates {
    return Coordinates(latitude = this.latitude, longitude = this.longitude)
}

@JvmName("mapRideCoordinatesToCoordinates")
fun List<RideCoordinatesEntity>.mapToCoordinates(): List<Coordinates> {
    return this.map { it.toCoordinates() }
}

fun ZoneCoordinatesEntity.toCoordinates(): Coordinates {
    return Coordinates(latitude = this.latitude, longitude = this.longitude)
}

@JvmName("mapZoneCoordinatesToCoordinates")
fun List<ZoneCoordinatesEntity>.mapToCoordinates(): List<Coordinates> {
    return this.map { it.toCoordinates() }
}
