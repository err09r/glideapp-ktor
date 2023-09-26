package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.ZoneType

interface ZoneDao {
    suspend fun getAllZones(): List<ZoneEntity>
    suspend fun getZonesByType(type: ZoneType): List<ZoneEntity>
    suspend fun getZoneByCode(code: Int): ZoneEntity?
    suspend fun insertZone(code: Int, title: String, type: ZoneType): ZoneEntity?
}
