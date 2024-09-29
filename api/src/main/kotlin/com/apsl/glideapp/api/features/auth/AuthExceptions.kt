package com.apsl.glideapp.api.features.auth

import com.apsl.glideapp.api.utils.GlideErrorCodes
import com.apsl.glideapp.api.utils.GlideException

class IncorrectUsernameFormatException(message: String? = "Username format is incorrect") : GlideException(message) {
    override val code: Int = GlideErrorCodes.INCORRECT_USERNAME_FORMAT
}

class IncorrectPasswordFormatException(message: String? = "Password format is incorrect") : GlideException(message) {
    override val code: Int = GlideErrorCodes.INCORRECT_PASSWORD_FORMAT
}

class RegistrationFailedException(message: String? = "User registration failed") : GlideException(message) {
    override val code: Int = GlideErrorCodes.REGISTRATION_FAILED
}

class InvalidPasswordException(message: String? = "Password is invalid") : GlideException(message) {
    override val code: Int = GlideErrorCodes.INVALID_PASSWORD
}

class UserNotFoundException(message: String? = "User not found") : GlideException(message) {
    override val code: Int = GlideErrorCodes.USER_NOT_FOUND
}

class UserAlreadyExistsException(message: String? = "User already exists") : GlideException(message) {
    override val code: Int = GlideErrorCodes.USER_ALREADY_EXISTS
}
