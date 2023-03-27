package com.apsl.glide.features.zone

import ZoneType
import com.apsl.glide.Coordinates

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
