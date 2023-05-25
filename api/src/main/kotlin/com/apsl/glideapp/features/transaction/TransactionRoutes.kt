package com.apsl.glideapp.features.transaction

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.transactionRoutes() {
    val transactionController: TransactionController by inject()
    route("transaction") {
        getAllTransactionsByUserIdRoute(transactionController)
    }
}

fun Route.getAllTransactionsByUserIdRoute(transactionController: TransactionController) {
    get {
        val userId = call.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asString()
        transactionController.getAllTransactionsByUserId(userId)
            .onSuccess { call.respond(it) }
            .onFailure { throwable ->
                call.respondNullable(
                    message = throwable.message,
                    status = when (throwable) {
                        is IllegalArgumentException -> HttpStatusCode.BadRequest
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}


