package com.apsl.glideapp.features.route

import com.apsl.glideapp.common.util.UUID

interface RideCoordinatesDao {
    suspend fun getAllRideCoordinatesByRideId(rideId: UUID): List<RideCoordinatesEntity>
    suspend fun insertRideCoordinates(rideId: UUID, latitude: Double, longitude: Double): RideCoordinatesEntity?
}
