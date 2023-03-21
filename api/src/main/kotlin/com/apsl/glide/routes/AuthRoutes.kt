package com.apsl.glide.routes

import com.apsl.glide.controllers.AuthController
import com.apsl.glide.models.request.AuthRequest
import com.apsl.glide.utils.IncorrectPasswordFormatException
import com.apsl.glide.utils.IncorrectUsernameFormatException
import com.apsl.glide.utils.InvalidPasswordException
import com.apsl.glide.utils.UserAlreadyExistsException
import com.apsl.glide.utils.UserNotFoundException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    val authController: AuthController by inject()
    registerRoute(authController)
    loginRoute(authController)
}

private fun Route.registerRoute(authController: AuthController) {
    post("register") {
        val request = call.receiveNullable<AuthRequest>()
        authController.register(request = request)
            .onSuccess { authResponse -> call.respond(authResponse) }
            .onFailure { throwable ->
                call.respondNullable(
                    message = throwable.message,
                    status = when (throwable) {
                        is IllegalArgumentException, is IncorrectPasswordFormatException, is IncorrectUsernameFormatException -> HttpStatusCode.BadRequest
                        is UserAlreadyExistsException -> HttpStatusCode.Conflict
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}

private fun Route.loginRoute(authController: AuthController) {
    post("login") {
        val request = call.receiveNullable<AuthRequest>()
        authController.login(request = request)
            .onSuccess { authResponse -> call.respond(authResponse) }
            .onFailure { throwable ->
                call.respondNullable(
                    message = throwable.message,
                    status = when (throwable) {
                        is IllegalArgumentException -> HttpStatusCode.BadRequest
                        is InvalidPasswordException -> HttpStatusCode.Unauthorized
                        is UserNotFoundException -> HttpStatusCode.NotFound
                        else -> HttpStatusCode.InternalServerError
                    }
                )
            }
    }
}
