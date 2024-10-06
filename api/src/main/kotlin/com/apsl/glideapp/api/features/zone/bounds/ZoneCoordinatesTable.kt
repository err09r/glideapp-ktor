package com.apsl.glideapp.api.features.zone.bounds

import com.apsl.glideapp.api.features.zone.ZonesTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ZoneCoordinatesTable : UUIDTable("zone_coordinates") {
    val zoneCode = reference("zone_code", ZonesTable.code)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}
