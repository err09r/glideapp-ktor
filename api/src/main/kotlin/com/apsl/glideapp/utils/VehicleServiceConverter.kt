package com.apsl.glideapp.utils

import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.common.models.ZoneType

object VehicleServiceConverter {

    fun fromTypeCodeToZoneType(type: Int): ZoneType {
        return when (type) {
            0 -> ZoneType.Riding
            else -> ZoneType.NoParking
        }
    }

    fun fromTypeCodeToVehicleType(type: Int): VehicleType {
        return when (type) {
            0 -> VehicleType.Scooter
            else -> VehicleType.Bike
        }
    }

    fun fromStatusCodeToVehicleStatus(status: Int): VehicleStatus {
        return when (status) {
            0 -> VehicleStatus.Available
            1 -> VehicleStatus.InUse
            else -> VehicleStatus.LowBattery
        }
    }
}
