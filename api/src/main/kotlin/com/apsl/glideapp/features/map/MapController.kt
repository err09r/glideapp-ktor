package com.apsl.glideapp.features.map

import CoordinatesBounds
import com.apsl.glideapp.common.dto.MapStateDto
import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.dto.ZoneDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleService
import com.apsl.glideapp.features.zone.ZoneDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class MapController(
    private val vehicleDao: VehicleDao,
    private val zoneDao: ZoneDao,
    private val vehicleService: VehicleService
) {

    fun observeMapStateWithinZoneBounds(coordinatesBounds: CoordinatesBounds): Flow<MapStateDto> {
        return vehicleService.vehicleListChangesFlow.map {
            val zoneBounds = coordinatesBounds.northeastBound to coordinatesBounds.southwestBound
            val availableVehicles = vehicleDao.getAllAvailableVehicles()
                .filter { it.coordinates within zoneBounds }
                .map { entity ->
                    VehicleDto(
                        id = entity.id.toString(),
                        code = entity.code,
                        batteryCharge = entity.batteryCharge,
                        status = entity.status,
                        coordinates = entity.coordinates
                    )
                }

            val ridingZones = zoneDao.getAllRidingZones()
                .filter { entity -> entity.coordinates.any { it within zoneBounds } }
                .map { entity ->
                    ZoneDto(
                        id = entity.id.toString(),
                        code = entity.code,
                        title = entity.title,
                        coordinates = entity.coordinates
                    )
                }

            MapStateDto(ridingZones = ridingZones, availableVehicles = availableVehicles)
        }
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
    }

    private infix fun Coordinates.within(bounds: Pair<Coordinates, Coordinates>): Boolean {
        return this.latitude in bounds.second.latitude..bounds.first.latitude &&
                this.longitude in bounds.second.longitude..bounds.first.longitude
    }
}
