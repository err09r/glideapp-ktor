package com.apsl.glideapp.api.utils

import io.ktor.util.logging.KtorSimpleLogger
import java.io.InputStream
import java.io.InputStreamReader

fun readTextFromResourcesFile(filepath: String): String? {
    var result: String? = null
    return try {
        val classLoader: ClassLoader = Thread.currentThread().contextClassLoader
        val inputStream: InputStream? = classLoader.getResourceAsStream(filepath)
        if (inputStream != null) {
            InputStreamReader(inputStream).use { result = it.readText() }
        }
        result
    } catch (e: Exception) {
        KtorSimpleLogger("readFileFromResources").error(e.message)
        result
    }
}
