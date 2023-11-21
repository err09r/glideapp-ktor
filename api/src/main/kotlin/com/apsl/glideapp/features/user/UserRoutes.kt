package com.apsl.glideapp.features.user

import com.apsl.glideapp.features.auth.UserNotFoundException
import com.apsl.glideapp.features.auth.security.JwtUtils
import com.apsl.glideapp.utils.getErrorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userController: UserController by inject()
    route("user") {
        getUserByIdRoute(userController)
    }
}

private fun Route.getUserByIdRoute(userController: UserController) {
    get("{id?}") {
        val userId = call.parameters["id"] ?: JwtUtils.getUserId(call)
        userController.getUserById(userId)
            .onSuccess { call.respond(it) }
            .onFailure { throwable ->
                call.respond(
                    message = throwable.getErrorResponse(),
                    status = when (throwable) {
                        is IllegalArgumentException -> HttpStatusCode.BadRequest
                        is UserNotFoundException -> HttpStatusCode.NotFound
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}
