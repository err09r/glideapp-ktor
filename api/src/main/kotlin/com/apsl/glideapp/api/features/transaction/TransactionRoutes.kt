package com.apsl.glideapp.api.features.transaction

import com.apsl.glideapp.api.features.auth.security.JwtUtils
import com.apsl.glideapp.api.utils.InvalidVoucherCodeException
import com.apsl.glideapp.api.utils.getErrorResponse
import com.apsl.glideapp.common.dto.TransactionRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.transactionRoutes() {
    val transactionController: TransactionController by inject()
    route("transaction") {
        getTransactionsByUserIdRoute(transactionController)
        createTransactionRoute(transactionController)
    }
}

private fun Route.getTransactionsByUserIdRoute(transactionController: TransactionController) {
    get {
        val userId = JwtUtils.getUserId(call)
        val page = call.request.queryParameters["page"]
        val limit = call.request.queryParameters["limit"]

        transactionController.getTransactionsByUserId(userId = userId, page = page, limit = limit)
            .onSuccess { call.respond(it) }
            .onFailure { throwable ->
                call.respond(
                    message = throwable.getErrorResponse(),
                    status = when (throwable) {
                        is IllegalArgumentException -> HttpStatusCode.BadRequest
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}

private fun Route.createTransactionRoute(transactionController: TransactionController) {
    post {
        val userId = JwtUtils.getUserId(call)
        val request = call.receiveNullable<TransactionRequest>()

        transactionController.createTransaction(userId = userId, request = request)
            .onSuccess { call.respond(status = HttpStatusCode.Created, message = "") }
            .onFailure { throwable ->
                call.respond(
                    message = throwable.getErrorResponse(),
                    status = when (throwable) {
                        is IllegalArgumentException, is InvalidVoucherCodeException -> HttpStatusCode.BadRequest
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}
