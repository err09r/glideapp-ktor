package com.apsl.glide.database.converters

import com.apsl.glide.models.Coordinates
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CoordinatesConverter : TypeConverter<Coordinates> {

    override fun from(value: Coordinates): String = Json.encodeToString(value)

    override fun fromList(values: List<Coordinates>): String = Json.encodeToString(values)

    override fun to(string: String): Coordinates = Json.decodeFromString(string)

    override fun toList(string: String): List<Coordinates> = Json.decodeFromString(string)
}
