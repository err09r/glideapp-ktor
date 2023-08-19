package com.apsl.glideapp.features.vehicle

import kotlinx.coroutines.flow.Flow

interface VehicleService {
    val vehicleListChanges: Flow<Unit>
}
