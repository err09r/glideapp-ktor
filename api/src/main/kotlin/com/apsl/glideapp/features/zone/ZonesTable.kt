package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.ZoneType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ZonesTable : Table("zones") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val code = integer("code").uniqueIndex()
    val title = varchar("title", 20)
    val type = enumeration<ZoneType>("type")
    val coordinates = text("coordinates")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)
}
