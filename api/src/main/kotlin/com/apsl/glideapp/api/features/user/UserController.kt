package com.apsl.glideapp.api.features.user

import com.apsl.glideapp.api.features.auth.UserNotFoundException
import com.apsl.glideapp.api.features.ride.RideDao
import com.apsl.glideapp.api.features.transaction.TransactionDao
import com.apsl.glideapp.common.dto.UserDto
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID

class UserController(
    private val userDao: UserDao,
    private val rideDao: RideDao,
    private val transactionDao: TransactionDao
) {

    suspend fun getUserById(id: String?) = runCatching {
        requireNotNull(id)

        val userUuid = UUID.fromString(id)

        val userEntity = userDao.getUserById(userUuid) ?: throw UserNotFoundException()
        val finishedRideEntities = rideDao.getRidesByStatusAndUserId(RideStatus.Finished, userUuid)

        val balance = transactionDao.getTransactionsAmountSumByUserId(userUuid)

        UserDto(
            id = userEntity.id.toString(),
            username = userEntity.username,
            firstName = userEntity.firstName,
            lastName = userEntity.lastName,
            totalDistance = finishedRideEntities.sumOf { it.distance },
            totalRides = finishedRideEntities.size,
            balance = balance
        )
    }
}
