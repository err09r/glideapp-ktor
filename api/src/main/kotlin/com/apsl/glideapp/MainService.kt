package com.apsl.glideapp

import VehicleService
import com.apsl.glideapp.common.models.Coordinates
import com.apsl.glideapp.features.vehicle.VehicleDao
import com.apsl.glideapp.features.zone.ZoneDao
import com.apsl.glideapp.utils.VehicleServiceConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainService(private val zoneDao: ZoneDao, private val vehicleDao: VehicleDao) {

    private val coroutineScope = CoroutineScope(Job())

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
            availableVehicles.collectLatest { vehicles ->
                if (zoneDao.getAllRidingZones().isEmpty()) {
                    return@collectLatest
                }

                vehicles.forEach { vehicleInfo ->
                    if (vehicleDao.getVehicleByCode(vehicleInfo.code) == null) {
                        vehicleDao.insertVehicle(
                            code = vehicleInfo.code,
                            zoneCode = vehicleInfo.zoneCode,
                            batteryCharge = vehicleInfo.batteryCharge,
                            type = VehicleServiceConverter.fromTypeCodeToVehicleType(type = vehicleInfo.type),
                            status = VehicleServiceConverter.fromStatusCodeToVehicleStatus(status = vehicleInfo.status),
                            coordinates = Coordinates(
                                latitude = vehicleInfo.latitude,
                                longitude = vehicleInfo.longitude
                            )
                        )
                    } else {
                        vehicleDao.updateVehicle(
                            code = vehicleInfo.code,
                            batteryCharge = vehicleInfo.batteryCharge,
                            status = VehicleServiceConverter.fromStatusCodeToVehicleStatus(status = vehicleInfo.status),
                            coordinates = Coordinates(
                                latitude = vehicleInfo.latitude,
                                longitude = vehicleInfo.longitude
                            )
                        )
                    }
                }
            }
        }
    }
}
