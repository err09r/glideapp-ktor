@file:Suppress("Unused", "SpellCheckingInspection")

package com.apsl.glideapp.utils

import io.ktor.util.logging.KtorSimpleLogger

/* SLF4J logging levels
    TRACE
    DEBUG
    INFO
    WARN
    ERROR
 */

fun Any.logt(message: String) {
    KtorSimpleLogger(this::class.java.simpleName).trace(message)
}

fun Any.logt(lazyMessage: () -> String) {
    KtorSimpleLogger(this::class.java.simpleName).trace(lazyMessage())
}

fun Any.logd(message: String) {
    KtorSimpleLogger(this::class.java.simpleName).debug(message)
}

fun Any.logd(lazyMessage: () -> String) {
    KtorSimpleLogger(this::class.java.simpleName).debug(lazyMessage())
}

fun Any.logi(message: String) {
    KtorSimpleLogger(this::class.java.simpleName).info(message)
}

fun Any.logi(lazyMessage: () -> String) {
    KtorSimpleLogger(this::class.java.simpleName).info(lazyMessage())
}

fun Any.logw(message: String) {
    KtorSimpleLogger(this::class.java.simpleName).warn(message)
}

fun Any.logw(lazyMessage: () -> String) {
    KtorSimpleLogger(this::class.java.simpleName).warn(lazyMessage())
}

fun Any.loge(message: String) {
    KtorSimpleLogger(this::class.java.simpleName).error(message)
}

fun Any.loge(lazyMessage: () -> String) {
    KtorSimpleLogger(this::class.java.simpleName).error(lazyMessage())
}
