package com.apsl.glideapp.api.features.ride

import com.apsl.glideapp.api.features.config.GlideConfig
import com.apsl.glideapp.api.features.ride.route.RideCoordinatesDao
import com.apsl.glideapp.api.features.transaction.TransactionDao
import com.apsl.glideapp.api.features.vehicle.VehicleDao
import com.apsl.glideapp.api.features.vehicle.VehicleEntity
import com.apsl.glideapp.api.features.zone.ZoneDao
import com.apsl.glideapp.api.features.zone.bounds.ZoneCoordinatesDao
import com.apsl.glideapp.api.utils.NoActiveRidesException
import com.apsl.glideapp.api.utils.NotEnoughFundsException
import com.apsl.glideapp.api.utils.PaginationData
import com.apsl.glideapp.api.utils.UserInsideNoParkingZoneException
import com.apsl.glideapp.api.utils.UserTooFarFromVehicleException
import com.apsl.glideapp.api.utils.logd
import com.apsl.glideapp.common.dto.RideDto
import com.apsl.glideapp.common.dto.RideEventDto
import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.models.Route
import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.models.asPairs
import com.apsl.glideapp.common.util.Geometry
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.common.util.capitalized
import kotlin.math.roundToInt
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
    private val transactionDao: TransactionDao,
    private val glideConfig: GlideConfig
) {

    suspend fun handleRideAction(action: RideAction, userId: String): Result<RideEventDto> = runCatching {
        logd("action: $action")
        when (action) {
            is RideAction.RequestCurrentState -> {
                val (rideId, vehicle, dateTime) = getActiveRideData(userId = userId)
                RideEventDto.Restored(rideId = rideId, vehicle = vehicle, startDateTime = dateTime)
            }

            is RideAction.Start -> {
                val (rideId, vehicle) = startRide(
                    userId = userId,
                    vehicleId = action.vehicleId,
                    userLocation = action.coordinates,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideEventDto.Started(rideId = rideId, vehicle = vehicle, dateTime = action.dateTime)
            }

            is RideAction.UpdateRoute -> {
                val route = updateRoute(rideId = action.rideId, coordinates = action.coordinates)
                RideEventDto.RouteUpdated(currentRoute = route)
            }

            is RideAction.Finish -> {
                val (distance, averageSpeed) = finishRide(
                    rideId = action.rideId,
                    userLocation = action.coordinates,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideEventDto.Finished(distance, averageSpeed)
            }
        }
    }.recover { throwable ->
        when (throwable) {
            is UserInsideNoParkingZoneException -> RideEventDto.Error.UserInsideNoParkingZone
            is UserTooFarFromVehicleException -> RideEventDto.Error.UserTooFarFromVehicle
            is NotEnoughFundsException -> RideEventDto.Error.NotEnoughFunds
            is NoActiveRidesException -> RideEventDto.SessionCancelled(throwable.message)
            else -> throw throwable
        }
    }

    private suspend fun getActiveRideData(userId: String?): Triple<String, VehicleDto, LocalDateTime> {
        requireNotNull(userId)
        val userUuid = UUID.fromString(userId)

        val activeRide = rideDao
            .getRidesByStatusAndUserId(status = RideStatus.Started, userId = userUuid)
            .singleOrNull() ?: throw NoActiveRidesException()

        val vehicleEntity = vehicleDao.getVehicleById(activeRide.vehicleId) ?: error("")

        return Triple(
            first = activeRide.id.toString(),
            second = vehicleEntity.toDto(),
            third = activeRide.startDateTime
        )
    }

    private suspend fun startRide(
        userId: String,
        vehicleId: String,
        userLocation: Coordinates,
        address: String?,
        dateTime: LocalDateTime
    ): Pair<String, VehicleDto> {
        val userUuid = UUID.fromString(userId)
        val vehicleUuid = UUID.fromString(vehicleId)

        checkIfUserHasActiveRide(userId = userUuid)
        checkIfUserHasEnoughFunds(userId = userUuid)

        val vehicleEntity = vehicleDao.getVehicleById(vehicleUuid) ?: error("")
        val distanceFromVehicle =
            Geometry.calculateDistance(userLocation.asPair(), vehicleEntity.latitude to vehicleEntity.longitude)

        if (distanceFromVehicle > glideConfig.unlockDistance) {
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

        return rideEntity.id.toString() to vehicleEntity.toDto()
    }

    private suspend fun checkIfUserHasActiveRide(userId: UUID) {
        if (rideDao.getRidesByStatusAndUserId(userId = userId, status = RideStatus.Started).isNotEmpty()) {
            error("User already has an active ride")
        }
    }

    private suspend fun checkIfUserHasEnoughFunds(userId: UUID) {
        val averageUnlockingFee = glideConfig.unlockingFees.values.sum() / glideConfig.unlockingFees.size
        val averageFarePerMinute = glideConfig.faresPerMinute.values.sum() / glideConfig.faresPerMinute.size
        val minimumAmountToStartRide = averageUnlockingFee + averageFarePerMinute
        if (transactionDao.getTransactionsAmountSumByUserId(userId) < minimumAmountToStartRide) {
            throw NotEnoughFundsException()
        }
    }

    private suspend fun updateRoute(rideId: String, coordinates: Coordinates): Route {
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

        val rideCoordinates = rideCoordinatesDao.getRideCoordinatesByRideId(rideId = rideUuid).map { entity ->
            Coordinates(latitude = entity.latitude, longitude = entity.longitude)
        }

        return Route(rideCoordinates)
    }

    private suspend fun finishRide(
        rideId: String,
        userLocation: Coordinates,
        address: String?,
        dateTime: LocalDateTime
    ): Pair<Double, Double> {
        val rideUuid = UUID.fromString(rideId)

        val ride = rideDao.getRideById(rideUuid) ?: error("")

        val noParkingZones = zoneDao.getZonesByType(ZoneType.NoParking)
        val isInsideNoParkingZone = noParkingZones.any { zoneEntity ->
            val zoneBounds = zoneCoordinatesDao
                .getZoneCoordinatesByZoneCode(zoneEntity.code)
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
        val vehicle = vehicleDao.getVehicleById(ride.vehicleId) ?: error("")
        val unlockingFee = glideConfig.unlockingFees[vehicle.type] ?: error("")
        val farePerMinute = glideConfig.faresPerMinute[vehicle.type] ?: error("")
        val minutes = durationInSeconds.seconds.inWholeMinutes
        val amount = -(unlockingFee + (farePerMinute * minutes))

        transactionDao.insertTransaction(userId = ride.userId, amount = amount, type = TransactionType.Ride)

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

        updateVehicleAfterRide(vehicle = vehicle, distance = ride.distance, coordinates = userLocation)

        val updatedRide = checkNotNull(rideDao.getRideById(rideUuid))
        return updatedRide.distance to updatedRide.averageSpeed
    }

    private suspend fun updateVehicleAfterRide(vehicle: VehicleEntity, distance: Double, coordinates: Coordinates) {
        val fullChargedScooterRange = requireNotNull(glideConfig.ranges[vehicle.type]) // in meters
        val percentSpentOnRide = (distance / fullChargedScooterRange).roundToInt()
        val percentAfterRide = (vehicle.batteryCharge - percentSpentOnRide).coerceIn(0..100)
        val newStatus = if (percentAfterRide < 40) VehicleStatus.LowBattery else VehicleStatus.Available

        vehicleDao.updateVehicle(
            id = vehicle.id,
            batteryCharge = percentAfterRide,
            status = newStatus,
            latitude = coordinates.latitude,
            longitude = coordinates.longitude
        )
    }

    suspend fun getRidesByStatusAndUserId(
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
            val route = rideCoordinatesDao.getRideCoordinatesByRideId(entity.id).map {
                Coordinates(latitude = it.latitude, longitude = it.longitude)
            }
            RideDto(
                id = entity.id.toString(),
                key = index + offset.toInt() + 1,
                startAddress = entity.startAddress,
                finishAddress = entity.finishAddress,
                startDateTime = entity.startDateTime,
                finishDateTime = entity.finishDateTime ?: entity.startDateTime,
                route = Route(route),
                averageSpeed = entity.averageSpeed
            )
        }
    }

    suspend fun getRideById(rideId: String?) = runCatching {
        requireNotNull(rideId)
        val entity = rideDao.getRideById(UUID.fromString(rideId)) ?: error("")
        val route = rideCoordinatesDao.getRideCoordinatesByRideId(entity.id).map {
            Coordinates(latitude = it.latitude, longitude = it.longitude)
        }
        RideDto(
            id = entity.id.toString(),
            key = null,
            startAddress = entity.startAddress,
            finishAddress = entity.finishAddress,
            startDateTime = entity.startDateTime,
            finishDateTime = entity.finishDateTime ?: entity.startDateTime,
            route = Route(route),
            averageSpeed = entity.averageSpeed
        )
    }

    private fun VehicleEntity.toDto(): VehicleDto {
        val unlockingFee = glideConfig.unlockingFees[this.type] ?: error("")
        val farePerMinute = glideConfig.faresPerMinute[this.type] ?: error("")
        return VehicleDto(
            id = this.id.toString(),
            code = this.code,
            batteryCharge = this.batteryCharge,
            type = this.type,
            status = this.status,
            coordinates = Coordinates(this.latitude, this.longitude),
            unlockingFee = unlockingFee,
            farePerMinute = farePerMinute
        )
    }
}
