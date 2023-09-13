package com.apsl.glideapp.utils

class UserNotFoundException(message: String? = "User not found") : Exception(message)

class UserAlreadyExistsException(message: String? = "User already exists") : Exception(message)

class InvalidVoucherCodeException(message: String? = "Invalid voucher code") : Exception(message)

class UserTooFarFromVehicleException(message: String? = "User is too far from vehicle") : Exception(message)

class UserInsideNoParkingZoneException(message: String? = "User is inside no-parking zone") : Exception(message)

class NoActiveRidesException(
    message: String? = "User does not have any active rides at the moment"
) : Exception(message)
