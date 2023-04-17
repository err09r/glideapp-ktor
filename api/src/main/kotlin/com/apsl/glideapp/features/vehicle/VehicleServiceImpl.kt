package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.utils.isInsideOfPolygon
import kotlin.random.Random
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

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override val vehicleListChangesFlow = flow {
        while (currentCoroutineContext().isActive) {
            val vehicles = vehicleDao.getAllVehicles()
            val newVehicles = vehicles.shuffled().take(vehicles.size / 10)

            val vehicleStatuses = VehicleStatus.values()
            val ridingZones = zoneDao.getAllRidingZones()

            newVehicles.forEach {
                vehicleDao.updateVehicle(
                    code = it.code,
                    batteryCharge = Random.nextInt(40, 101),
                    status = vehicleStatuses.random(),
                    coordinates = generateCoordinatesWithinZoneBounds(ridingZones[it.zoneCode - 1].coordinates)
                )
            }

            emit(Unit)
//            delay(Random.nextLong(5000, 20000))
            delay(5.seconds)
        }
    }
        .flowOn(Dispatchers.IO)
        .shareIn(scope = coroutineScope, started = SharingStarted.Eagerly)

    private fun generateCoordinatesWithinZoneBounds(zoneBounds: List<Coordinates>): Coordinates {
        val leftmostPoint = zoneBounds.minOf { it.longitude }
        val rightmostPoint = zoneBounds.maxOf { it.longitude }
        val highestPoint = zoneBounds.minOf { it.latitude }
        val lowestPont = zoneBounds.maxOf { it.latitude }

        var generatedLongitude: Double
        var generatedLatitude: Double

        do {
            generatedLatitude = Random.nextDouble(highestPoint, lowestPont)
            generatedLongitude = Random.nextDouble(leftmostPoint, rightmostPoint)
        } while (!isInsideOfPolygon(
                numberOfVertices = zoneBounds.size,
                vertX = zoneBounds.map { it.longitude },
                vertY = zoneBounds.map { it.latitude },
                pointX = generatedLongitude,
                pointY = generatedLatitude
            )
        )

        return Coordinates(latitude = generatedLatitude, longitude = generatedLongitude)
    }
}
