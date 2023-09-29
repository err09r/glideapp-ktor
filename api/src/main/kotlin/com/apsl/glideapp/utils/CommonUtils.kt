package com.apsl.glideapp.utils

import io.ktor.util.logging.KtorSimpleLogger
import java.io.File

fun Any.readFileFromResources(path: String): String {
    return try {
        val uri = this.javaClass.classLoader.getResource(path)?.toURI()
        checkNotNull(uri)
        File(uri).readText()
    } catch (e: Exception) {
        KtorSimpleLogger(this::class.java.simpleName).error(e.message)
        ""
    }
}
