package com.apsl.glideapp.features.auth

import com.apsl.glideapp.common.dto.AuthRequest
import com.apsl.glideapp.common.dto.AuthResponse
import com.apsl.glideapp.features.auth.security.TokenClaim
import com.apsl.glideapp.features.auth.security.TokenService
import com.apsl.glideapp.features.auth.security.hashing.HashingService
import com.apsl.glideapp.features.auth.security.hashing.SaltedHash
import com.apsl.glideapp.features.user.UserDao
import com.apsl.glideapp.utils.UserAlreadyExistsException
import com.apsl.glideapp.utils.UserNotFoundException

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
