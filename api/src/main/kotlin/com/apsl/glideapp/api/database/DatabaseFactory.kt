@file:Suppress("Unused", "SameParameterValue", "MemberVisibilityCanBePrivate")

package com.apsl.glideapp.api.database

import com.apsl.glideapp.api.features.ride.RidesTable
import com.apsl.glideapp.api.features.ride.route.RideCoordinatesTable
import com.apsl.glideapp.api.features.transaction.TransactionsTable
import com.apsl.glideapp.api.features.user.UsersTable
import com.apsl.glideapp.api.features.vehicle.VehiclesTable
import com.apsl.glideapp.api.features.zone.ZonesTable
import com.apsl.glideapp.api.features.zone.bounds.ZoneCoordinatesTable
import com.apsl.glideapp.api.utils.readTextFromResourcesFile
import io.ktor.server.config.ApplicationConfig
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import org.jetbrains.exposed.sql.ExperimentalKeywordApi
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

@OptIn(ExperimentalKeywordApi::class)
object DatabaseFactory {

    fun init(config: ApplicationConfig) {
        val jdbcUrl = config.property("storage.jdbcUrl").getString()
        val driverClassName = config.property("storage.driverClassName").getString()
        init(jdbcUrl = jdbcUrl, driverClassName = driverClassName)
    }

    fun init(jdbcUrl: String, driverClassName: String) {
        val database = Database.connect(
            url = jdbcUrl,
            driver = driverClassName,
            databaseConfig = DatabaseConfig { preserveKeywordCasing = true }
        )
        transaction(database) {
            SchemaUtils.create(
                ZonesTable,
                ZoneCoordinatesTable,
                VehiclesTable,
                RidesTable,
                RideCoordinatesTable,
                UsersTable,
                TransactionsTable
            )

            initializeTables(ZonesTable, ZoneCoordinatesTable, VehiclesTable)
        }
    }

    private fun Transaction.initializeTables(vararg tables: InitializableTable) {
        tables.forEach {
            readTextFromResourcesFile(filepath = it.initSqlFilePath)?.let { statement ->
                exec(statement)
            }
        }
    }

    suspend fun <T> query(block: suspend Transaction.() -> T): T {
        return newSuspendedTransaction(context = Dispatchers.IO, statement = block)
    }
}
