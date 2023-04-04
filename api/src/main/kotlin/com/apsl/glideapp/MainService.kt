package com.apsl.glideapp

import VehicleService
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.utils.VehicleServiceConverter
import isInsideOfPolygon
import kotlin.random.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class MainService(private val zoneDao: ZoneDao, private val vehicleDao: VehicleDao) {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val ridingZones = VehicleService.ridingZones
    private val availableVehicles = VehicleService.availableVehicles

    fun start() {
        saveAllRidingZones()
        observeAvailableVehicles()
    }

    private fun saveAllRidingZones() {
        coroutineScope.launch {
            if (zoneDao.getAllRidingZones().isEmpty()) {
                ridingZones.forEach { zoneInfo ->
                    zoneDao.insertZone(
                        code = zoneInfo.code,
                        title = zoneInfo.title,
                        type = VehicleServiceConverter.fromTypeCodeToZoneType(type = zoneInfo.type),
                        coordinates = zoneInfo.coordinates.map { Coordinates(it.first, it.second) }
                    )
                }
            }
        }
    }

    private fun observeAvailableVehicles() {
        coroutineScope.launch {
            while (isActive) {
                val vehicles = vehicleDao.getAllVehicles()
                val newVehicles = vehicles.shuffled().take(vehicles.size / 10)

                val vehicleStatuses = VehicleStatus.values()
                val ridingZones = zoneDao.getAllRidingZones()
                newVehicles.map {
                    launch {
                        vehicleDao.updateVehicle(
                            code = it.code,
                            batteryCharge = Random.nextInt(40, 101),
                            status = vehicleStatuses.random(),
                            coordinates = generateCoordinatesWithinZoneBounds(ridingZones[it.zoneCode - 1].coordinates)
                        )
                    }
                }.joinAll()

                delay(10000)
            }

//            availableVehicles.collectLatest { vehicles ->
//                if (zoneDao.getAllRidingZones().isEmpty()) {
//                    return@collectLatest
//                }
//
//                vehicles.forEach { vehicleInfo ->
//                    if (vehicleDao.getVehicleByCode(vehicleInfo.code) == null) {
//                        vehicleDao.insertVehicle(
//                            code = vehicleInfo.code,
//                            zoneCode = vehicleInfo.zoneCode,
//                            batteryCharge = vehicleInfo.batteryCharge,
//                            type = VehicleServiceConverter.fromTypeCodeToVehicleType(type = vehicleInfo.type),
//                            status = VehicleServiceConverter.fromStatusCodeToVehicleStatus(status = vehicleInfo.status),
//                            coordinates = Coordinates(
//                                latitude = vehicleInfo.latitude,
//                                longitude = vehicleInfo.longitude
//                            )
//                        )
//                    } else {
//                        vehicleDao.updateVehicle(
//                            code = vehicleInfo.code,
//                            batteryCharge = vehicleInfo.batteryCharge,
//                            status = VehicleServiceConverter.fromStatusCodeToVehicleStatus(status = vehicleInfo.status),
//                            coordinates = Coordinates(
//                                latitude = vehicleInfo.latitude,
//                                longitude = vehicleInfo.longitude
//                            )
//                        )
//                    }
//                }
//            }
        }
    }

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
