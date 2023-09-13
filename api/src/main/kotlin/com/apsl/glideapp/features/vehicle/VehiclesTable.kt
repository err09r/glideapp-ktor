package com.apsl.glideapp.features.vehicle

import com.apsl.glideapp.common.models.VehicleStatus
import com.apsl.glideapp.common.models.VehicleType
import com.apsl.glideapp.features.zone.ZonesTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object VehiclesTable : Table("vehicles") {
    val id = uuid("id").autoGenerate().autoGenerate()
    val code = integer("code").uniqueIndex()
    val zoneCode = reference("zone_code", ZonesTable.code)
    val batteryCharge = integer("battery_charge")
    val type = enumeration<VehicleType>("type")
    val status = enumeration<VehicleStatus>("status")
    val coordinates = text("coordinates")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
