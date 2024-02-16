@file:Suppress("Unused")

package com.apsl.glideapp.utils

abstract class GlideException(message: String?) : Exception(message) {
    abstract val code: Int
}

class InvalidVoucherCodeException(message: String? = "Invalid voucher code") : GlideException(message) {
    override val code: Int = GlideErrorCodes.INVALID_VOUCHER_CODE
}

class UserTooFarFromVehicleException(message: String? = "User is too far from vehicle") : GlideException(message) {
    override val code: Int = GlideErrorCodes.USER_TOO_FAR_FROM_VEHICLE
}

class UserInsideNoParkingZoneException(message: String? = "User is inside no-parking zone") : GlideException(message) {
    override val code: Int = GlideErrorCodes.USER_INSIDE_NO_PARKING_ZONE
}

class NoActiveRidesException(
    message: String? = "User does not have any active rides at the moment"
) : GlideException(message) {
    override val code: Int = GlideErrorCodes.NO_ACTIVE_RIDES
}

class NotEnoughFundsException(
    message: String? = "User does not have enough funds to start a ride"
) : GlideException(message) {
    override val code: Int = GlideErrorCodes.NOT_ENOUGH_FUNDS
}
