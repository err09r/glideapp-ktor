package com.apsl.glide.database.dao

import com.apsl.glide.database.entities.ZoneEntity
import com.apsl.glide.models.Coordinates
import com.apsl.glide.models.ZoneType
import java.util.UUID

interface ZoneDao {
    suspend fun getAllZones(): List<ZoneEntity>
    suspend fun getAllRidingZones(): List<ZoneEntity>
    suspend fun getAllNoParkingZones(): List<ZoneEntity>
    suspend fun getZoneById(id: UUID): ZoneEntity?
    suspend fun insertZone(
        title: String,
        type: ZoneType,
        coordinates: List<Coordinates>
    ): ZoneEntity?
}
