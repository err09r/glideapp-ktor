package com.apsl.glide.controllers

import com.apsl.glide.database.dao.ZoneDao
import com.apsl.glide.models.Zone

class ZoneController(private val zoneDao: ZoneDao) {

    suspend fun getAllZonesByType(zoneType: String?) = runCatching {
        when (zoneType) {
            "riding" -> {
                zoneDao.getAllRidingZones().map { entity ->
                    Zone(
                        id = entity.id,
                        title = entity.title,
                        type = entity.type,
                        coordinates = entity.coordinates
                    )
                }
            }

            "no_parking" -> {
                zoneDao.getAllNoParkingZones().map { entity ->
                    Zone(
                        id = entity.id,
                        title = entity.title,
                        type = entity.type,
                        coordinates = entity.coordinates
                    )
                }
            }

            else -> throw IllegalArgumentException()
        }
    }
}
