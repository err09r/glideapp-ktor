package com.apsl.glideapp.features.ride

import com.apsl.glideapp.common.dto.RideStateDto
import com.apsl.glideapp.common.models.RideAction
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import kotlinx.datetime.LocalDateTime

class RideController(private val rideDao: RideDao) {

    suspend fun handleRideAction(action: RideAction, userId: String) = runCatching {
        val userUuid = UUID.fromString(userId)
        when (action) {
            is RideAction.Start -> {
                val rideId = startRide(userUuid, action.address, action.dateTime)
                RideStateDto.Started(rideId = rideId)
            }

            is RideAction.Pause -> RideStateDto.Paused

            is RideAction.Finish -> {
                finishRide(rideId = action.rideId, address = action.address, dateTime = action.dateTime)
                RideStateDto.Finished
            }
        }
    }

    private suspend fun startRide(userId: UUID, address: String, dateTime: LocalDateTime): String {
        if (rideDao.getUserHasActiveRides(userId)) {
            //TODO: Send message that user already has active ride
            error("")
        }

        val rideEntity =
            rideDao.insertRide(userId = userId, startAddress = address, startDateTime = dateTime) ?: error("")
        return rideEntity.id.toString()
    }

    private suspend fun finishRide(rideId: String, address: String, dateTime: LocalDateTime) {
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
    }
}
