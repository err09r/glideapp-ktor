package com.apsl.glideapp.features.map

import com.apsl.glideapp.common.dto.MapStateDto
import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.dto.ZoneDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.CoordinatesBounds
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.vehicle.VehicleService
import com.apsl.glideapp.features.zone.ZoneDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MapController(
    private val vehicleDao: VehicleDao,
    private val zoneDao: ZoneDao,
    private val vehicleService: VehicleService
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var mapStateObserveJob: Job? = null

    private var visibleBounds: CoordinatesBounds? = null

    fun updateVisibleBounds(bounds: CoordinatesBounds) {
        this.visibleBounds = bounds
    }

    suspend fun startObservingMapState(onEach: suspend (MapStateDto) -> Unit) {
        mapStateObserveJob?.cancelAndJoin()
        mapStateObserveJob = scope.launch {
            observeMapStateWithinVisibleBounds().collectLatest(onEach)
        }
    }

    private fun observeMapStateWithinVisibleBounds() = vehicleService.vehicleListChanges.map {
        getCurrentMapState()
    }
        .onStart {
            emit(getCurrentMapState())
        }
        .filterNot { it.availableVehicles.isEmpty() && it.ridingZones.isEmpty() }
        .flowOn(Dispatchers.IO)
        .distinctUntilChanged()

    private suspend fun getCurrentMapState(): MapStateDto {
        val bounds = visibleBounds ?: CoordinatesBounds.Undefined

        val availableVehicles = vehicleDao.getAllAvailableVehicles()
            .filter { it.coordinates within bounds } //TODO: Change to filtering in database
            .map { entity ->
                VehicleDto(
                    id = entity.id.toString(),
                    code = entity.code,
                    batteryCharge = entity.batteryCharge,
                    status = entity.status,
                    coordinates = entity.coordinates
                )
            }

        val ridingZones = zoneDao.getZonesByType(ZoneType.Riding).map { entity ->
            ZoneDto(
                id = entity.id.toString(),
                code = entity.code,
                title = entity.title,
                coordinates = entity.coordinates
            )
        }

        val noParkingZones = zoneDao.getZonesByType(ZoneType.NoParking).map { entity ->
            ZoneDto(
                id = entity.id.toString(),
                code = entity.code,
                title = entity.title,
                coordinates = entity.coordinates
            )
        }

        return MapStateDto(
            ridingZones = ridingZones,
            noParkingZones = noParkingZones,
            availableVehicles = availableVehicles
        )
    }
}

private infix fun Coordinates.within(bounds: CoordinatesBounds): Boolean {
    return this.latitude >= bounds.southwest.latitude && this.latitude <= bounds.northeast.latitude &&
            this.longitude >= bounds.southwest.longitude && this.longitude <= bounds.northeast.latitude
}

private val CoordinatesBounds.Companion.Undefined
    get() = CoordinatesBounds(
        Coordinates(0.0, 0.0),
        Coordinates(0.0, 0.0)
    )

private val Coordinates.Companion.Undefined get() = Coordinates(0.0, 0.0)
