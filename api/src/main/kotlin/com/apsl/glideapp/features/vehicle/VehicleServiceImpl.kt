package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.common.util.isInsidePolygon
import com.apsl.glideapp.features.zone.ZoneDao
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

class VehicleServiceImpl(private val vehicleDao: VehicleDao, private val zoneDao: ZoneDao) : VehicleService {

    private val scope = CoroutineScope(Dispatchers.IO)

    override val vehicleListChanges = flow {
        delay(15.seconds)
        while (currentCoroutineContext().isActive) {
            val vehicles = vehicleDao.getAllVehicles()
            val newVehicles = vehicles.shuffled().take(vehicles.size / 10)

            val vehicleStatuses = VehicleStatus.entries
            val ridingZones = zoneDao.getZonesByType(ZoneType.Riding)

            newVehicles.forEach {
                vehicleDao.updateVehicle(
                    id = it.id,
                    batteryCharge = Random.nextInt(40, 101),
                    status = vehicleStatuses.random(),
                    coordinates = generateCoordinatesWithinZoneBounds(ridingZones[it.zoneCode - 1].coordinates)
                )
            }

            emit(Unit)
//            delay(Random.nextInt(5, 20).seconds)
            delay(Int.MAX_VALUE.hours)
        }
    }
        .flowOn(Dispatchers.IO)
        .shareIn(scope = scope, started = SharingStarted.Eagerly)

    private fun generateCoordinatesWithinZoneBounds(zoneBounds: List<Coordinates>): Coordinates {
        val leftmostPoint = zoneBounds.minOf { it.longitude }
        val rightmostPoint = zoneBounds.maxOf { it.longitude }
        val highestPoint = zoneBounds.minOf { it.latitude }
        val lowestPont = zoneBounds.maxOf { it.latitude }

        var coordinates: Coordinates

        do {
            val generatedLatitude = Random.nextDouble(highestPoint, lowestPont)
            val generatedLongitude = Random.nextDouble(leftmostPoint, rightmostPoint)
            coordinates = Coordinates(latitude = generatedLatitude, longitude = generatedLongitude)
        } while (!coordinates.isInsidePolygon(zoneBounds))

        return coordinates
    }
}
