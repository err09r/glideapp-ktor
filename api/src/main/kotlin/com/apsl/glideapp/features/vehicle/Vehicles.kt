package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.features.zone.Zones
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Vehicles : Table() {
    val code = integer("code").uniqueIndex()
    val zoneCode = reference("zoneCode", Zones.code)
    val batteryCharge = integer("batteryCharge")
    val type = enumeration<VehicleType>("type")
    val status = enumeration<VehicleStatus>("status")
    val coordinates = text("coordinates")
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(code)
}
