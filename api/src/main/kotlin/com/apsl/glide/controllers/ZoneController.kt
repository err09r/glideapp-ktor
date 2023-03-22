package com.apsl.glide.controllers

import com.apsl.glide.database.dao.ZoneDao
import com.apsl.glide.models.ZoneDto

class ZoneController(private val zoneDao: ZoneDao) {

    suspend fun getAllZonesByType(zoneType: String?) = runCatching {
        when (zoneType) {
            "riding" -> {
                zoneDao.getAllRidingZones().map { entity ->
                    ZoneDto(
                        id = entity.id,
                        title = entity.title,
                        coordinates = entity.coordinates
                    )
                }
            }

            "no_parking" -> {
                zoneDao.getAllNoParkingZones().map { entity ->
                    ZoneDto(
                        id = entity.id,
                        title = entity.title,
                        coordinates = entity.coordinates
                    )
                }
            }

            else -> throw IllegalArgumentException()
        }
    }
}
