package com.apsl.glide.database.converters

interface TypeConverter<T> {
    fun from(value: T): String
    fun fromList(values: List<T>): String
    fun to(string: String): T
    fun toList(string: String): List<T>
}
