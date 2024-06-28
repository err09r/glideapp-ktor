@file:Suppress("Unused")

package com.apsl.glideapp.api.utils

import com.apsl.glideapp.api.features.ride.route.RideCoordinatesEntity
import com.apsl.glideapp.api.features.zone.bounds.ZoneCoordinatesEntity
import com.apsl.glideapp.common.models.Coordinates

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
