package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.models.Route
import com.apsl.glideapp.features.ride.route.RideCoordinatesDao
import com.apsl.glideapp.utils.mapToCoordinates
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class RideRepository(
    private val rideDao: RideDao,
    private val rideCoordinatesDao: RideCoordinatesDao
) {

    suspend fun getAllRides(): List<Ride> = coroutineScope {
        val rides = rideDao.getAllRides()
        val rideCoordinatesMap = rides
            .map { rideEntity ->
                async { rideCoordinatesDao.getRideCoordinatesByRideId(rideEntity.id) }
            }
            .awaitAll()
            .flatten()
            .groupBy { it.rideId.toString() }
            .mapValues { Route(it.value.mapToCoordinates()) }

        rides.map { rideEntity ->
            Ride(
                id = rideEntity.id,
                userId = rideEntity.userId,
                vehicleId = rideEntity.vehicleId,
                startAddress = rideEntity.startAddress,
                finishAddress = rideEntity.finishAddress,
                startDateTime = rideEntity.startDateTime,
                finishDateTime = rideEntity.finishDateTime,
                status = rideEntity.status,
                distance = rideEntity.distance,
                averageSpeed = rideEntity.averageSpeed,
                route = requireNotNull(rideCoordinatesMap[rideEntity.id.toString()]),
                createdAt = rideEntity.createdAt,
                updatedAt = rideEntity.updatedAt
            )
        }
    }
}
