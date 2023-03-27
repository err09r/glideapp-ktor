package com.apsl.glide.database

import com.apsl.glide.features.user.Users
import com.apsl.glide.features.vehicle.Vehicles
import com.apsl.glide.features.zone.Zones
import io.ktor.server.config.ApplicationConfig
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(config: ApplicationConfig) {
        val jdbcUrl = config.property("storage.jdbcUrl").getString()
        val driverClassName = config.property("storage.driverClassName").getString()
        val database = Database.connect(jdbcUrl, driverClassName)
        transaction(database) {
            SchemaUtils.create(Users, Zones, Vehicles)
        }
    }

    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}
