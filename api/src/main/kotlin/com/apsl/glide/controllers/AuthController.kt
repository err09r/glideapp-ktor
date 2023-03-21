package com.apsl.glide.controllers

import com.apsl.glide.database.dao.UserDao
import com.apsl.glide.models.request.AuthRequest
import com.apsl.glide.models.response.AuthResponse
import com.apsl.glide.security.TokenClaim
import com.apsl.glide.security.TokenService
import com.apsl.glide.security.hashing.HashingService
import com.apsl.glide.security.hashing.SaltedHash
import com.apsl.glide.utils.IncorrectPasswordFormatException
import com.apsl.glide.utils.IncorrectUsernameFormatException
import com.apsl.glide.utils.InvalidPasswordException
import com.apsl.glide.utils.RegisterFieldVerifier
import com.apsl.glide.utils.RegistrationFailedException
import com.apsl.glide.utils.UserAlreadyExistsException
import com.apsl.glide.utils.UserNotFoundException

class AuthController(
    private val userDao: UserDao,
    private val hashingService: HashingService,
    private val tokenService: TokenService
) {

    suspend fun register(request: AuthRequest?) = runCatching {
        if (request == null) {
            throw IllegalArgumentException()
        }

        if (userDao.getUserByUsername(request.username) != null) {
            throw UserAlreadyExistsException()
        }

        val isUsernameCorrect = RegisterFieldVerifier.verifyUsername(request.username)
        if (!isUsernameCorrect) {
            throw IncorrectUsernameFormatException()
        }

        val isPasswordCorrect = RegisterFieldVerifier.verifyPassword(request.password)
        if (!isPasswordCorrect) {
            throw IncorrectPasswordFormatException()
        }

        val saltedHash = hashingService.generateSaltedHash(request.password)

        val userEntity = userDao.insertUser(
            username = request.username,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            firstName = "Test",
            lastName = "Test"
        ) ?: throw RegistrationFailedException()

        val token = tokenService.generate(TokenClaim("userId", userEntity.id.toString()))
        AuthResponse(token = token)
    }

    suspend fun login(request: AuthRequest?) = runCatching {
        if (request == null) {
            throw IllegalArgumentException()
        }

        val userEntity = userDao.getUserByUsername(request.username) ?: throw UserNotFoundException()

        val isPasswordValid = hashingService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash = userEntity.password,
                salt = userEntity.salt
            )
        )

        if (!isPasswordValid) {
            throw InvalidPasswordException()
        }

        val token = tokenService.generate(TokenClaim("userId", userEntity.id.toString()))
        AuthResponse(token = token)
    }
}
