package com.apsl.glide.features.zone

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
