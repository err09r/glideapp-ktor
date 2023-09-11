package com.apsl.glideapp.database

import com.apsl.glideapp.features.ride.RidesTable
import com.apsl.glideapp.features.route.RideCoordinatesTable
import com.apsl.glideapp.features.transaction.TransactionsTable
import com.apsl.glideapp.features.user.UsersTable
import com.apsl.glideapp.features.vehicle.VehiclesTable
import com.apsl.glideapp.features.zone.ZonesTable
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
            SchemaUtils.create(
                ZonesTable,
                VehiclesTable,
                UsersTable,
                RidesTable,
                RideCoordinatesTable,
                TransactionsTable
            )
        }
    }

    suspend fun <T> query(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}
