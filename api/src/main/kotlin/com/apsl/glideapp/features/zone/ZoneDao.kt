package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.ZoneType

interface ZoneDao {
    suspend fun getAllRidingZones(): List<ZoneEntity>
    suspend fun getAllNoParkingZones(): List<ZoneEntity>
    suspend fun getZoneByCode(code: Int): ZoneEntity?
    suspend fun insertZone(
        code: Int,
        title: String,
        type: ZoneType,
        coordinates: List<Coordinates>
    ): ZoneEntity?
}
