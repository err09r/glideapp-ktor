package com.apsl.glideapp.features.ride.route

import com.apsl.glideapp.common.util.UUID

interface RideCoordinatesDao {
    suspend fun getAllRideCoordinatesByRideId(rideId: UUID): List<RideCoordinatesEntity>
    suspend fun getLatestRideCoordinatesByRideId(rideId: UUID): RideCoordinatesEntity?
    suspend fun insertRideCoordinates(rideId: UUID, latitude: Double, longitude: Double): RideCoordinatesEntity?
}
