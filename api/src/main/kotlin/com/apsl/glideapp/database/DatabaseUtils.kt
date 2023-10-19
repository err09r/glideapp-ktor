@file:Suppress("Unused")

package com.apsl.glideapp.database

import com.apsl.glideapp.database.DatabaseFactory.query
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll

suspend fun Table.isEmpty(): Boolean {
    return query { selectAll().count() <= 0L }
}
