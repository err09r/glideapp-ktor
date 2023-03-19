package com.apsl.glide.database.converters

interface TypeConverter<T> {
    fun from(value: T): String
    fun to(string: String): T
}