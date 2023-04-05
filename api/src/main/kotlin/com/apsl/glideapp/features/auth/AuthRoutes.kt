package com.apsl.glideapp.features.auth

import com.apsl.glideapp.common.dto.LoginRequest
import com.apsl.glideapp.common.dto.RegisterRequest
import com.apsl.glideapp.utils.UserAlreadyExistsException
import com.apsl.glideapp.utils.UserNotFoundException
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
        val request = call.receiveNullable<RegisterRequest>()
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
        val request = call.receiveNullable<LoginRequest>()
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
