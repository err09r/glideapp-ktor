package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.dto.RideDto
import com.apsl.glideapp.common.dto.RideEventDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.features.route.RideCoordinatesDao
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.utils.calculateDistance
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class RideController(
    private val rideDao: RideDao,
    private val rideCoordinatesDao: RideCoordinatesDao,
    private val vehicleDao: VehicleDao
) {

    suspend fun handleRideAction(action: RideAction, userId: String) = runCatching {
        when (action) {
            is RideAction.Start -> {
                val rideId = startRide(
                    userId = userId,
                    vehicleId = action.vehicleId,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideEventDto.Started(rideId = rideId, dateTime = action.dateTime)
            }

            is RideAction.UpdateRoute -> {
                val route = updateRoute(rideId = action.rideId, coordinates = action.coordinates)
                RideEventDto.RouteUpdated(currentRoute = route)
            }

            is RideAction.Finish -> {
                finishRide(
                    rideId = action.rideId,
                    vehicleId = action.vehicleId,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideEventDto.Finished
            }
        }
    }

    private suspend fun startRide(
        userId: String,
        vehicleId: String,
        address: String?,
        dateTime: LocalDateTime
    ): String {
        val userUuid = UUID.fromString(userId)
        //        if (rideDao.getUserHasActiveRides(userId)) {
//            //TODO: Send message that user already has active ride
//            KtorSimpleLogger("RideController").error("User already has active ride.")
//            error("")
//        }

        val rideEntity =
            rideDao.insertRide(userId = userUuid, startAddress = address, startDateTime = dateTime) ?: error("")

        val wasUpdated = vehicleDao.updateVehicle(id = UUID.fromString(vehicleId), status = VehicleStatus.InUse)
        if (!wasUpdated) {
            //TODO: Handle
            error("")
        }

        return rideEntity.id.toString()
    }

    private suspend fun updateRoute(rideId: String, coordinates: Coordinates): List<Coordinates> {
        val rideUuid = UUID.fromString(rideId)
        rideCoordinatesDao.insertRideCoordinates(
            rideId = rideUuid,
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )

        val latestSavedCoordinates = rideCoordinatesDao.getLatestRideCoordinatesByRideId(rideUuid)?.let {
            Coordinates(latitude = it.latitude, longitude = it.longitude)
        }

        if (latestSavedCoordinates != null) {
            val newDistance = calculateDistance(latestSavedCoordinates, coordinates)
            val previousTotalDistance = rideDao.getRideById(rideUuid)?.distance ?: 0.0
            val newTotalDistance = previousTotalDistance + newDistance
            rideDao.updateRide(id = rideUuid, distance = newTotalDistance)
        }

        return rideCoordinatesDao.getAllRideCoordinatesByRideId(rideId = rideUuid).map { entity ->
            Coordinates(latitude = entity.latitude, longitude = entity.longitude)
        }
    }

    private suspend fun finishRide(rideId: String, vehicleId: String, address: String?, dateTime: LocalDateTime) {
        val rideUuid = UUID.fromString(rideId)
        val vehicleUuid = UUID.fromString(vehicleId)

        val ride = rideDao.getRideById(rideUuid)
        requireNotNull(ride)

        //TODO: move to separate function
        val startInstant = ride.startDateTime.toInstant(TimeZone.currentSystemDefault())
        val finishInstant = dateTime.toInstant(TimeZone.currentSystemDefault())

        val durationInSeconds = finishInstant.minus(startInstant).inWholeSeconds
        val distanceInMeters = ride.distance

        val averageSpeed = distanceInMeters / durationInSeconds * 3.6

        val wasUpdated = rideDao.updateRide(
            id = rideUuid,
            finishAddress = address,
            finishDateTime = dateTime,
            status = RideStatus.Finished,
            averageSpeed = averageSpeed
        )
        if (!wasUpdated) {
            //TODO: Handle
            error("")
        }

        //TODO: Add logic to change 'batteryCharge' and 'vehicleStatus' depending on ride distance etc.
        vehicleDao.updateVehicle(id = vehicleUuid, status = VehicleStatus.Available)
    }

    suspend fun getAllFinishedRidesByUserId(userId: String?) = runCatching {
        if (userId == null) {
            throw IllegalArgumentException()
        }

        rideDao.getAllFinishedRidesByUserId(UUID.fromString(userId)).map { entity ->
            RideDto(
                id = entity.id.toString(),
                startAddress = entity.startAddress,
                finishAddress = entity.finishAddress,
                startDateTime = entity.startDateTime,
                finishDateTime = entity.finishDateTime ?: entity.startDateTime,
                distance = entity.distance,
                averageSpeed = entity.averageSpeed
            )
        }
    }
}
