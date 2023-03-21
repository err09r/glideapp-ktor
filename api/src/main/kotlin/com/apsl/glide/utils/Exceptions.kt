package com.apsl.glide.utils

class UserNotFoundException(override val message: String? = "User not found") : Exception(message)

class UserAlreadyExistsException(override val message: String? = "User already exists") : Exception(message)

class IncorrectUsernameFormatException(override val message: String? = "Username is incorrect") : Exception(message)

class IncorrectPasswordFormatException(override val message: String? = "Password is incorrect") : Exception(message)

class RegistrationFailedException(override val message: String? = "User registration failed") : Exception(message)

class InvalidPasswordException(override val message: String? = "Password is invalid") : Exception(message)
