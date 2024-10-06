package com.apsl.glideapp.api.features.vehicle

import com.apsl.glideapp.api.features.zone.ZonesTable
import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object VehiclesTable : UUIDTable("vehicles") {
    val code = integer("code").uniqueIndex()
    val zoneCode = reference("zone_code", ZonesTable.code)
    val batteryCharge = integer("battery_charge")
    val type = enumeration<VehicleType>("type")
    val status = enumeration<VehicleStatus>("status")
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
