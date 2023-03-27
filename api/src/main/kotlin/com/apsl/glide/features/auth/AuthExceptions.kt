package com.apsl.glide.features.auth

class IncorrectUsernameFormatException(override val message: String? = "Username is incorrect") : Exception(message)

class IncorrectPasswordFormatException(override val message: String? = "Password is incorrect") : Exception(message)

class RegistrationFailedException(override val message: String? = "User registration failed") : Exception(message)

class InvalidPasswordException(override val message: String? = "Password is invalid") : Exception(message)
