package com.apsl.glide.routes

import com.apsl.glide.controllers.UserController
import com.apsl.glide.utils.UserNotFoundException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userController: UserController by inject()
    route("user") {
        getUserByIdRoute(userController)
        updateUserRoute(userController)
    }
}

private fun Route.getUserByIdRoute(userController: UserController) {
    get("{id?}") {
        val id = call.parameters["id"] ?: call.principal<JWTPrincipal>()?.payload?.getClaim("userId")?.asString()
        userController.getUserById(id)
            .onSuccess { userDto -> call.respond(userDto) }
            .onFailure { throwable ->
                call.respondNullable(
                    message = throwable.message,
                    status = when (throwable) {
                        is IllegalArgumentException -> HttpStatusCode.BadRequest
                        is UserNotFoundException -> HttpStatusCode.NotFound
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}

private fun Route.updateUserRoute(userController: UserController) {
    put("{id}") {
    }
}
