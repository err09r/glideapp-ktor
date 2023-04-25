package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.dto.RideStateDto
import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.features.vehicle.VehicleDao
import kotlinx.datetime.LocalDateTime

class RideController(private val rideDao: RideDao, private val vehicleDao: VehicleDao) {

    suspend fun handleRideAction(action: RideAction, userId: String) = runCatching {
        when (action) {
            is RideAction.Start -> {
                val rideId = startRide(
                    userId = userId,
                    vehicleId = action.vehicleId,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideStateDto.Started(rideId = rideId, dateTime = action.dateTime)
            }

            is RideAction.Pause -> RideStateDto.Paused

            is RideAction.Finish -> {
                finishRide(
                    rideId = action.rideId,
                    vehicleId = action.vehicleId,
                    address = action.address,
                    dateTime = action.dateTime
                )
                RideStateDto.Finished
            }
        }
    }

    private suspend fun startRide(userId: String, vehicleId: String, address: String, dateTime: LocalDateTime): String {
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

    private suspend fun finishRide(rideId: String, vehicleId: String, address: String, dateTime: LocalDateTime) {
        val wasUpdated = rideDao.updateRide(
            id = UUID.fromString(rideId),
            finishAddress = address,
            finishDateTime = dateTime,
            status = RideStatus.Finished
        )
        if (!wasUpdated) {
            //TODO: Handle
            error("")
        }

        val vehicleUuid = UUID.fromString(vehicleId)

        //TODO: Add logic to change 'batteryCharge' and 'vehicleStatus' depending on ride distance etc.
        vehicleDao.updateVehicle(id = UUID.fromString(vehicleId), status = VehicleStatus.Available)
    }
}
