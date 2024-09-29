package com.apsl.glideapp.api.features.vehicle

import kotlinx.coroutines.flow.Flow

interface VehicleService {
    val vehicleListChanges: Flow<Unit>
}
