package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.models.asPairs
import com.apsl.glideapp.common.util.Geometry
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.features.zone.bounds.ZoneCoordinatesDao
import kotlin.random.Random
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds
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

    override val vehicleListChanges = flow {
        delay(15.seconds)
        while (currentCoroutineContext().isActive) {
            val vehicles = vehicleDao.getAllVehicles()
            val newVehicles = vehicles.shuffled().take(6)

            val ridingZones = zoneDao.getZonesByType(ZoneType.Riding)
            val zoneBounds = ridingZones.flatMap {
                zoneCoordinatesDao.getAllZoneCoordinatesByZoneCode(it.code)
            }

            newVehicles.forEach { vehicleEntity ->
                val (latitude, longitude) = generateCoordinatesWithinZoneBounds(
                    bounds = zoneBounds.map { Coordinates(it.latitude, it.longitude) }
                )
                vehicleDao.updateVehicle(
                    id = vehicleEntity.id,
                    batteryCharge = Random.nextInt(40, 101),
                    status = VehicleStatus.entries.random(),
                    latitude = latitude,
                    longitude = longitude
                )
            }

            emit(Unit)
//            delay(Random.nextInt(5, 20).seconds)
            delay(Int.MAX_VALUE.hours)
        }
    }
        .flowOn(Dispatchers.IO)
        .shareIn(scope = scope, started = SharingStarted.Eagerly)

    private fun generateCoordinatesWithinZoneBounds(bounds: List<Coordinates>): Coordinates {
        val leftmostPoint = bounds.minOf { it.longitude }
        val rightmostPoint = bounds.maxOf { it.longitude }
        val highestPoint = bounds.minOf { it.latitude }
        val lowestPont = bounds.maxOf { it.latitude }

        var coordinates: Coordinates

        do {
            val generatedLatitude = Random.nextDouble(highestPoint, lowestPont)
            val generatedLongitude = Random.nextDouble(leftmostPoint, rightmostPoint)
            coordinates = Coordinates(latitude = generatedLatitude, longitude = generatedLongitude)
        } while (!Geometry.isInsidePolygon(vertices = bounds.asPairs(), point = coordinates.asPair()))

        return coordinates
    }
}
