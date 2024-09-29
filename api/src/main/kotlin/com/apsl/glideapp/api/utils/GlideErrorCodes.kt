@file:Suppress("Unused")

package com.apsl.glideapp.api.utils

object GlideErrorCodes {

    /*
    Code range: 1000 - 1999
    Structure:
        1) 1100 - 1199 - Authentication errors
        2) 1200 - 1299 - Ride errors
        3) 1300 - 1399 - Transaction errors
        ...
     */

    const val INVALID_TOKEN = 1101
    const val INCORRECT_USERNAME_FORMAT = 1102
    const val INCORRECT_PASSWORD_FORMAT = 1103
    const val INVALID_PASSWORD = 1104
    const val REGISTRATION_FAILED = 1105
    const val USER_NOT_FOUND = 1106
    const val USER_ALREADY_EXISTS = 1107

    const val NO_ACTIVE_RIDES = 1201
    const val USER_TOO_FAR_FROM_VEHICLE = 1202
    const val USER_INSIDE_NO_PARKING_ZONE = 1203
    const val NOT_ENOUGH_FUNDS = 1204

    const val INVALID_VOUCHER_CODE = 1301
}
