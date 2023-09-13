@file:Suppress("FunctionName")

package com.apsl.glideapp.database.converters

import com.apsl.glideapp.common.models.Coordinates
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CoordinatesConverter : TypeConverter<Coordinates> {

    override fun ValueToString(value: Coordinates): String = Json.encodeToString(value)

    override fun ValueListToString(values: List<Coordinates>): String = Json.encodeToString(values)

    override fun StringToValue(string: String): Coordinates = Json.decodeFromString(string)

    override fun StringToValueList(string: String): List<Coordinates> = Json.decodeFromString(string)
}
