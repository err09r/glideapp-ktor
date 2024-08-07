package com.apsl.glideapp.api.features.map

import com.apsl.glideapp.api.features.config.GlideConfig
import com.apsl.glideapp.api.features.vehicle.VehicleDao
import com.apsl.glideapp.api.features.vehicle.VehicleService
import com.apsl.glideapp.common.dto.MapContentDto
import com.apsl.glideapp.common.dto.VehicleDto
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.CoordinatesBounds
import com.apsl.glideapp.common.models.VehicleStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapController(
    private val vehicleService: VehicleService,
    private val vehicleDao: VehicleDao,
    private val glideConfig: GlideConfig
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var mapContentJob: Job? = null

    private var contentBounds = MutableStateFlow<CoordinatesBounds?>(null)

    fun updateContentBounds(bounds: CoordinatesBounds) {
        contentBounds.update { bounds }
    }

    suspend fun startObservingMapContent(onEach: suspend (MapContentDto) -> Unit) {
        mapContentJob?.cancelAndJoin()
        mapContentJob = scope.launch {
            observeMapContentWithinBounds().collectLatest(onEach)
        }
    }

    private fun observeMapContentWithinBounds() = vehicleService.vehicleListChanges
        .map { getCurrentMapContent() }
        .onStart { emit(getCurrentMapContent()) }
        .debounce(UPDATE_INTERVAL_MS)
        .flowOn(Dispatchers.IO)
        .distinctUntilChanged()

    private suspend fun getCurrentMapContent(): MapContentDto {
        val bounds = contentBounds.value ?: return MapContentDto(emptyList())

        val availableVehicles = vehicleDao.getVehiclesByStatus(VehicleStatus.Available)
            .filter { bounds.contains(Coordinates(it.latitude, it.longitude)) }
            .map { entity ->
                val unlockingFee = glideConfig.unlockingFees[entity.type] ?: error("")
                val farePerMinute = glideConfig.faresPerMinute[entity.type] ?: error("")
                VehicleDto(
                    id = entity.id.toString(),
                    code = entity.code,
                    batteryCharge = entity.batteryCharge,
                    type = entity.type,
                    status = entity.status,
                    coordinates = Coordinates(entity.latitude, entity.longitude),
                    unlockingFee = unlockingFee,
                    farePerMinute = farePerMinute
                )
            }

        return MapContentDto(availableVehicles = availableVehicles)
    }

    private companion object {
        private const val UPDATE_INTERVAL_MS = 500L
    }
}
