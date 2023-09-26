package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.dto.RideDto
import com.apsl.glideapp.common.dto.RideEventDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.models.asPairs
import com.apsl.glideapp.common.util.Geometry
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.capitalized
import com.apsl.glideapp.features.config.GlideConfiguration
import com.apsl.glideapp.features.ride.route.RideCoordinatesDao
import com.apsl.glideapp.features.transaction.TransactionDao
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesDao
import com.apsl.glideapp.utils.NoActiveRidesException
import com.apsl.glideapp.utils.PaginationData
import com.apsl.glideapp.utils.UserInsideNoParkingZoneException
import com.apsl.glideapp.utils.UserTooFarFromVehicleException
import io.ktor.util.logging.KtorSimpleLogger
import kotlin.time.Duration.Companion.seconds
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class RideController(
    private val rideDao: RideDao,
    private val rideCoordinatesDao: RideCoordinatesDao,
    private val zoneDao: ZoneDao,
    private val zoneCoordinatesDao: ZoneCoordinatesDao,
    private val vehicleDao: VehicleDao,
    private val transactionDao: TransactionDao
) {

    suspend fun handleRideAction(action: RideAction, userId: String): Result<RideEventDto> = runCatching {
        KtorSimpleLogger("RideController").error("action: $action")
        when (action) {
            is RideAction.RequestCurrentState -> {
                val (rideId, dateTime) = getActiveRideData(userId = userId)
                RideEventDto.Restored(rideId = rideId, dateTime = dateTime)
            }

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
            is NoActiveRidesException -> RideEventDto.SessionCancelled(throwable.message)
            else -> throw throwable
        }
    }

    private suspend fun getActiveRideData(userId: String?): Pair<String, LocalDateTime> {
        requireNotNull(userId)
        val userUuid = UUID.fromString(userId)

        val activeRide = rideDao
            .getRidesByStatusAndUserId(status = RideStatus.Started, userId = userUuid)
            .singleOrNull() ?: throw NoActiveRidesException()

        return (activeRide.id.toString() to activeRide.startDateTime)
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
        val distanceFromVehicle =
            Geometry.calculateDistance(userLocation.asPair(), vehicle.latitude to vehicle.longitude)

        if (distanceFromVehicle > GlideConfiguration.UNLOCK_DISTANCE) {
            throw UserTooFarFromVehicleException()
        }

        val rideEntity = rideDao.insertRide(
            userId = userUuid,
            vehicleId = vehicleUuid,
            startAddress = address,
            startDateTime = dateTime
        ) ?: error("")

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
            val newDistance = Geometry.calculateDistance(latestSavedCoordinates.asPair(), coordinates.asPair())
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
        userLocation: Coordinates,
        address: String?,
        dateTime: LocalDateTime
    ) {
        val rideUuid = UUID.fromString(rideId)

        val ride = rideDao.getRideById(rideUuid) ?: error("")

        val noParkingZones = zoneDao.getZonesByType(ZoneType.NoParking)
        val isInsideNoParkingZone = noParkingZones.any { zoneEntity ->
            val zoneBounds = zoneCoordinatesDao
                .getAllZoneCoordinatesByZoneCode(zoneEntity.code)
                .map { Coordinates(it.latitude, it.longitude) }

            Geometry.isInsidePolygon(vertices = zoneBounds.asPairs(), point = userLocation.asPair())
        }

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
        val vehicle = vehicleDao.getVehicleById(ride.vehicleId) ?: error("")
        val unlockingFee = GlideConfiguration.unlockingFees[vehicle.type] ?: error("")
        val farePerMinute = GlideConfiguration.faresPerMinute[vehicle.type] ?: error("")
        val minutes = durationInSeconds.seconds.inWholeMinutes
        //TODO: replace with 'unlockingFee' and 'farePerMinute' of each vehicle/zone
        val amount = -(unlockingFee + (farePerMinute * minutes))

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
        vehicleDao.updateVehicle(id = ride.vehicleId, status = VehicleStatus.Available)
    }

    suspend fun getAllRidesByStatusAndUserId(
        status: String?,
        userId: String?,
        page: String? = null,
        limit: String? = null
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
