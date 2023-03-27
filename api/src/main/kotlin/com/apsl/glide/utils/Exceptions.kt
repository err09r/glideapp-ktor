package com.apsl.glide.utils

class UserNotFoundException(override val message: String? = "User not found") : Exception(message)

class UserAlreadyExistsException(override val message: String? = "User already exists") : Exception(message)
