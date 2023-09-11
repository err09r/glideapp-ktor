package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.dto.ZoneDto

class ZoneController(private val zoneDao: ZoneDao) {

    suspend fun getAllZones(): Result<List<ZoneDto>> = runCatching {
        zoneDao.getAllZones().mapToDtos()
    }

    private fun Iterable<ZoneEntity>.mapToDtos(): List<ZoneDto> {
        return this.map { it.toDto() }
    }

    private fun ZoneEntity.toDto(): ZoneDto {
        return ZoneDto(
            id = this.id.toString(),
            code = this.code,
            title = this.title,
            type = this.type,
            coordinates = this.coordinates
        )
    }
}