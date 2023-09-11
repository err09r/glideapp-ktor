@file:Suppress("FunctionName")

package com.apsl.glideapp.database.converters

interface TypeConverter<T> {
    fun ValueToString(value: T): String
    fun ValueListToString(values: List<T>): String
    fun StringToValue(string: String): T
    fun StringToValueList(string: String): List<T>
}
