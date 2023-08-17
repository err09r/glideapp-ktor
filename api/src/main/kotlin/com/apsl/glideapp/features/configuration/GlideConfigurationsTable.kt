package com.apsl.glideapp.features.configuration

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object GlideConfigurationsTable : Table("configurations") {
    val countryCode = char("countryCode", 2)
    val unlockingFee = double("unlockingFee")
    val farePerMinute = double("farePerMinute")
    val createdAt = datetime("createdAt")
    val updatedAt = datetime("updatedAt")

    override val primaryKey = PrimaryKey(countryCode)
}
