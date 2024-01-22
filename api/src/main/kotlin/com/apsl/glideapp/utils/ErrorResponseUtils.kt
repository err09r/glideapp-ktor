@file:Suppress("Unused", "UnusedReceiverParameter")

package com.apsl.glideapp.utils

import com.apsl.glideapp.common.dto.ErrorResponse

fun Throwable.getErrorResponse(): ErrorResponse {
    val code = if (this is GlideException) this.code else null
    return ErrorResponse(code = code, description = this.message.toString())
}

fun Throwable.getErrorResponse(message: String?): ErrorResponse {
    val code = if (this is GlideException) this.code else null
    return ErrorResponse(code = code, description = message.toString())
}

fun Throwable.getErrorResponse(message: String?, code: Int): ErrorResponse {
    return ErrorResponse(code = code, description = message.toString())
}
