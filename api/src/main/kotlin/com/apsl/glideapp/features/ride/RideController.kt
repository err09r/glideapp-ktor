package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.dto.RideDto
import com.apsl.glideapp.common.dto.RideEventDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.util.Geometry.calculateDistance
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.capitalized
import com.apsl.glideapp.common.util.isInsidePolygon
import com.apsl.glideapp.features.configuration.GlideConfigurationDao
import com.apsl.glideapp.features.route.RideCoordinatesDao
import com.apsl.glideapp.features.transaction.TransactionDao
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.utils.PaginationData
import com.apsl.glideapp.utils.UserInsideNoParkingZoneException
import com.apsl.glideapp.utils.UserTooFarFromVehicleException
import kotlin.math.roundToInt
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class RideController(
    private val rideDao: RideDao,
    private val rideCoordinatesDao: RideCoordinatesDao,
    private val zoneDao: ZoneDao,
    private val vehicleDao: VehicleDao,
    private val transactionDao: TransactionDao,
    private val glideConfigurationDao: GlideConfigurationDao
) {

    suspend fun handleRideAction(action: RideAction, userId: String): Result<RideEventDto> = runCatching {
        when (action) {
            is RideAction.Start -> {
                val rideId = startRide(
                    userId = userId,
                    vehicleId = action.vehicleId,
                    userLocation = action.coordinates,
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
                    userLocation = action.coordinates,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideEventDto.Finished
            }
        }
    }.recover { throwable ->
        when (throwable) {
            is UserInsideNoParkingZoneException -> RideEventDto.Error.UserInsideNoParkingZone
            else -> throw throwable
        }
    }


    private suspend fun startRide(
        userId: String,
        vehicleId: String,
        userLocation: Coordinates,
        address: String?,
        dateTime: LocalDateTime
    ): String {
        val userUuid = UUID.fromString(userId)
        val vehicleUuid = UUID.fromString(vehicleId)

        //TODO: Send message that user already has active ride
        //TODO: And also check for user's balance
        //TODO: And move it to a separate function
//        if (rideDao.getRidesByStatusAndUserId(userId = userUuid, status = RideStatus.Started).isNotEmpty()) {
//            KtorSimpleLogger("RideController").error("User already has active ride.")
//            error("")
//        }

        val vehicle = vehicleDao.getVehicleById(vehicleUuid) ?: error("")
        val distanceFromVehicle = calculateDistance(userLocation, vehicle.coordinates)

        if (distanceFromVehicle > 25.0) {
            throw UserTooFarFromVehicleException()
        }

        val rideEntity =
            rideDao.insertRide(userId = userUuid, startAddress = address, startDateTime = dateTime) ?: error("")

        val wasUpdated = vehicleDao.updateVehicle(id = vehicleUuid, status = VehicleStatus.InUse)
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

    private suspend fun finishRide(
        rideId: String,
        vehicleId: String,
        userLocation: Coordinates,
        address: String?,
        dateTime: LocalDateTime
    ) {
        val rideUuid = UUID.fromString(rideId)
        val vehicleUuid = UUID.fromString(vehicleId)

        val ride = rideDao.getRideById(rideUuid) ?: error("")

        val noParkingZones = zoneDao.getZonesByType(ZoneType.NoParking)
        val isInsideNoParkingZone = noParkingZones.any { userLocation.isInsidePolygon(it.coordinates) }

        if (isInsideNoParkingZone) {
            throw UserInsideNoParkingZoneException()
        }

        //TODO: move to separate function
        val startInstant = ride.startDateTime.toInstant(TimeZone.currentSystemDefault())
        val finishInstant = dateTime.toInstant(TimeZone.currentSystemDefault())

        val durationInSeconds = finishInstant.minus(startInstant).inWholeSeconds
        val distanceInMeters = ride.distance

        val averageSpeed = distanceInMeters / durationInSeconds * 3.6
        //

        //TODO: move to separate function (unlock price + price per minute)
        //Remove hardcoded countryCode and calculate amount based on user location
        val configuration = glideConfigurationDao.getGlideConfigurationByCountryCode("PL") ?: error("")
        val amount =
            -(configuration.unlockingFee + (configuration.farePerMinute * (durationInSeconds / 60.0).roundToInt()))

        transactionDao.insertTransaction(userId = ride.userId, amount = amount, type = TransactionType.Ride)
        //

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

    suspend fun getAllRidesByStatusAndUserId(
        status: String?,
        userId: String?,
        page: String?,
        limit: String?
    ) = runCatching {
        requireNotNull(status)
        requireNotNull(userId)

        val (rideLimit, offset) = PaginationData(page = page, limit = limit)

        rideDao.getRidesByStatusAndUserId(
            status = RideStatus.valueOf(status.capitalized()),
            userId = UUID.fromString(userId),
            limit = rideLimit,
            offset = offset
        ).mapIndexed { index, entity ->
            RideDto(
                id = entity.id.toString(),
                key = index + offset.toInt() + 1,
                startAddress = entity.startAddress,
                finishAddress = entity.finishAddress,
                startDateTime = entity.startDateTime,
                finishDateTime = entity.finishDateTime ?: entity.startDateTime,
                route = rideCoordinatesDao.getAllRideCoordinatesByRideId(entity.id).map {
                    Coordinates(latitude = it.latitude, longitude = it.longitude)
                },
                distance = entity.distance,
                averageSpeed = entity.averageSpeed
            )
        }
    }

    suspend fun getRideById(rideId: String?) = runCatching {
        requireNotNull(rideId)
        val entity = rideDao.getRideById(UUID.fromString(rideId)) ?: error("")
        RideDto(
            id = entity.id.toString(),
            key = null,
            startAddress = entity.startAddress,
            finishAddress = entity.finishAddress,
            startDateTime = entity.startDateTime,
            finishDateTime = entity.finishDateTime ?: entity.startDateTime,
            route = rideCoordinatesDao.getAllRideCoordinatesByRideId(entity.id).map {
                Coordinates(latitude = it.latitude, longitude = it.longitude)
            },
            distance = entity.distance,
            averageSpeed = entity.averageSpeed
        )
    }
}
