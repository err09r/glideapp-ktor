package com.apsl.glide.database.tables

import com.apsl.glide.models.ZoneType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Zones : Table() {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val title = varchar("title", 20)
    val type = enumeration<ZoneType>("type")
    val coordinates = text("coordinates")
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(id)
}
