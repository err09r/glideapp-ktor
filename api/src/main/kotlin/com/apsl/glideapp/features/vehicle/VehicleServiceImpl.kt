package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.models.asPairs
import com.apsl.glideapp.common.util.Geometry
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.ZoneEntity
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesDao
import com.apsl.glideapp.utils.logi
import kotlin.random.Random
import kotlin.time.Duration.Companion.hours
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.isActive

class VehicleServiceImpl(
    private val vehicleDao: VehicleDao,
    private val zoneDao: ZoneDao,
    private val zoneCoordinatesDao: ZoneCoordinatesDao
) : VehicleService {

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        logi("Vehicle service created")
    }

    override val vehicleListChanges = flow {
        delay(INITIAL_DELAY)

        while (currentCoroutineContext().isActive) {
            updateVehicles()
            emit(Unit)
//            delay(Random.nextInt(5, 20).seconds)
            delay(Int.MAX_VALUE.hours)
        }
    }
        .flowOn(Dispatchers.IO)
        .shareIn(scope = scope, started = SharingStarted.Eagerly)

    private suspend fun updateVehicles() {
        val noParkingZoneCodes = zoneDao.getZonesByType(ZoneType.NoParking).map(ZoneEntity::code)
        val noParkingZoneCoordinates = noParkingZoneCodes.associateWith { zoneCode ->
            zoneCoordinatesDao
                .getZoneCoordinatesByZoneCode(zoneCode)
                .map { Coordinates(it.latitude, it.longitude) }
        }

        val vehicles = vehicleDao.getAllVehicles()
        val newVehicles = vehicles.shuffled().take(VEHICLES_TO_UPDATE)

        newVehicles.forEach { vehicleEntity ->
            val zoneBounds = zoneCoordinatesDao
                .getZoneCoordinatesByZoneCode(vehicleEntity.zoneCode)
                .map { Coordinates(it.latitude, it.longitude) }

            val (latitude, longitude) = generateCoordinatesWithinZoneBounds(
                zoneBounds = zoneBounds,
                exclusionZoneBounds = noParkingZoneCoordinates
            )

            vehicleDao.updateVehicle(
                id = vehicleEntity.id,
                batteryCharge = Random.nextInt(BATTERY_CHARGE_MIN, BATTERY_CHARGE_MAX - 1),
                status = VehicleStatus.entries.random(),
                latitude = latitude,
                longitude = longitude
            )
        }
    }

    private fun generateCoordinatesWithinZoneBounds(
        zoneBounds: List<Coordinates>,
        exclusionZoneBounds: Map<Int, List<Coordinates>>
    ): Coordinates {
        val topmostLatitude = zoneBounds.maxOf { it.latitude }
        val bottommostLatitude = zoneBounds.minOf { it.latitude }
        val leftmostLongitude = zoneBounds.minOf { it.longitude }
        val rightmostLongitude = zoneBounds.maxOf { it.longitude }

        var result: Coordinates

        do {
            val generatedLatitude = Random.nextDouble(from = bottommostLatitude, until = topmostLatitude)
            val generatedLongitude = Random.nextDouble(from = leftmostLongitude, until = rightmostLongitude)

            result = Coordinates(latitude = generatedLatitude, longitude = generatedLongitude)
            val resultPair = result.asPair()

            val shouldGenerate = !Geometry.isInsidePolygon(
                vertices = zoneBounds.asPairs(),
                point = resultPair
            ) || exclusionZoneBounds.any { (_, zoneBorder) ->
                Geometry.isInsidePolygon(vertices = zoneBorder.asPairs(), point = resultPair)
            }

        } while (shouldGenerate)

        return result
    }

    private companion object {
        private const val INITIAL_DELAY = 15000L
        private const val VEHICLES_TO_UPDATE = 6
        private const val BATTERY_CHARGE_MIN = 40
        private const val BATTERY_CHARGE_MAX = 100
    }
}
