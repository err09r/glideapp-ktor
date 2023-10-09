package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.dto.ZoneDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.ZoneBorder
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesDao
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class ZoneController(
    private val zoneDao: ZoneDao,
    private val zoneCoordinatesDao: ZoneCoordinatesDao
) {

    suspend fun getAllZones(): Result<List<ZoneDto>> = runCatching {
        coroutineScope {
            val zoneEntities = zoneDao.getAllZones()
            zoneEntities.map { zoneEntity ->
                async {
                    val zoneCoordinatesEntity = zoneCoordinatesDao.getZoneCoordinatesByZoneCode(zoneEntity.code)
                    val zoneBorder = zoneCoordinatesEntity.map { Coordinates(it.latitude, it.longitude) }
                    ZoneDto(
                        id = zoneEntity.id.toString(),
                        code = zoneEntity.code,
                        title = zoneEntity.title,
                        type = zoneEntity.type,
                        border = ZoneBorder(zoneBorder)
                    )
                }
            }.awaitAll()
        }
    }
}
