package com.apsl.glideapp.database.converters

import com.apsl.glideapp.common.models.Coordinates
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CoordinatesConverter : TypeConverter<Coordinates> {

    override fun fromValue(value: Coordinates): String = Json.encodeToString(value)

    override fun fromValues(values: List<Coordinates>): String = Json.encodeToString(values)

    override fun toValue(string: String): Coordinates = Json.decodeFromString(string)

    override fun toValues(string: String): List<Coordinates> = Json.decodeFromString(string)
}
