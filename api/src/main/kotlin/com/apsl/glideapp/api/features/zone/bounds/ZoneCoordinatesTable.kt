package com.apsl.glideapp.api.features.zone.bounds

import com.apsl.glideapp.api.database.InitializableTable
import com.apsl.glideapp.api.features.zone.ZonesTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ZoneCoordinatesTable : Table("zone_coordinates"), InitializableTable {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val zoneCode = reference("zone_code", ZonesTable.code)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)

    override val initSqlFilePath = "sql/zone_coords.sql"
}
