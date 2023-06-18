package com.apsl.glideapp.features.user

import com.apsl.glideapp.common.dto.UserDto
import com.apsl.glideapp.common.models.RideStatus
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.features.ride.RideDao
import com.apsl.glideapp.features.transaction.TransactionDao
import com.apsl.glideapp.utils.UserNotFoundException

class UserController(
    private val userDao: UserDao,
    private val rideDao: RideDao,
    private val transactionDao: TransactionDao
) {

    suspend fun getUserById(id: String?) = runCatching {
        requireNotNull(id)

        val userUuid = UUID.fromString(id)

        val userEntity = userDao.getUserById(userUuid) ?: throw UserNotFoundException()
        val finishedRideEntities = rideDao.getAllRidesByStatusAndUserId(RideStatus.Finished, userUuid)

        val balance = transactionDao.getAllTransactionsAmountSumByUserId(userUuid)

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
