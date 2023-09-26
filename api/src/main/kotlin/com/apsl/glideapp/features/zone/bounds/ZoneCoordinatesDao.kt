package com.apsl.glideapp.features.zone.bounds

interface ZoneCoordinatesDao {
    suspend fun getAllZoneCoordinatesByZoneCode(zoneCode: Int): List<ZoneCoordinatesEntity>
    suspend fun getLatestZoneCoordinatesByZoneCode(zoneCode: Int): ZoneCoordinatesEntity?
    suspend fun insertZoneCoordinates(zoneCode: Int, latitude: Double, longitude: Double): ZoneCoordinatesEntity?
}
