package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.dto.ZoneDto

class ZoneController(private val zoneDao: ZoneDao) {

    suspend fun getAllZonesByType(zoneType: String?) = runCatching {
        when (zoneType) {
            "riding" -> {
                zoneDao.getAllRidingZones().map { entity ->
                    ZoneDto(
                        code = entity.code,
                        title = entity.title,
                        coordinates = entity.coordinates
                    )
                }
            }

            "no_parking" -> {
                zoneDao.getAllNoParkingZones().map { entity ->
                    ZoneDto(
                        code = entity.code,
                        title = entity.title,
                        coordinates = entity.coordinates
                    )
                }
            }

            else -> throw IllegalArgumentException()
        }
    }
}
