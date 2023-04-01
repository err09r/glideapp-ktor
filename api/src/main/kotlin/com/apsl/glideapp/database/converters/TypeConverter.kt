package com.apsl.glideapp.database.converters

interface TypeConverter<T> {
    fun fromValue(value: T): String
    fun fromValues(values: List<T>): String
    fun toValue(string: String): T
    fun toValues(string: String): List<T>
}
