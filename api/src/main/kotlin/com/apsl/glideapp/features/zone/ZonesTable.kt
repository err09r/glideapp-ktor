package com.apsl.glideapp.features.zone

import com.apsl.glideapp.common.models.ZoneType
import com.apsl.glideapp.database.InitializableTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object ZonesTable : Table("zones"), InitializableTable {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val code = integer("code").uniqueIndex()
    val title = varchar("title", 20)
    val type = enumeration<ZoneType>("type")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")

    override val primaryKey = PrimaryKey(id)

    override val initSqlFilePath = "sql/zones.sql"
}
