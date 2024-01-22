package com.apsl.glideapp.features.auth

import com.apsl.glideapp.common.dto.AuthResponse
import com.apsl.glideapp.common.dto.LoginRequest
import com.apsl.glideapp.common.dto.RegisterRequest
import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.features.auth.security.TokenClaim
import com.apsl.glideapp.features.auth.security.TokenService
import com.apsl.glideapp.features.auth.security.hashing.HashingService
import com.apsl.glideapp.features.auth.security.hashing.SaltedHash
import com.apsl.glideapp.features.transaction.TransactionDao
import com.apsl.glideapp.features.user.UserDao

class AuthController(
    private val userDao: UserDao,
    private val hashingService: HashingService,
    private val tokenService: TokenService,
    private val transactionDao: TransactionDao
) {

    suspend fun register(request: RegisterRequest?) = runCatching {
        requireNotNull(request)

        if (userDao.getUserByUsername(request.username) != null) {
            throw UserAlreadyExistsException()
        }

        RegisterFieldVerifier
            .verifyUsername(request.username)
            .onFailure { throw IncorrectUsernameFormatException() }

        RegisterFieldVerifier
            .verifyPassword(request.password)
            .onFailure { throw IncorrectPasswordFormatException() }

        val saltedHash = hashingService.generateSaltedHash(request.password)

        val userEntity = userDao.insertUser(
            username = request.username,
            password = saltedHash.hash,
            salt = saltedHash.salt,
            firstName = "Test",
            lastName = "Test"
        ) ?: throw RegistrationFailedException()

        transactionDao.insertTransaction(userId = userEntity.id, amount = 10.0, type = TransactionType.StartBonus)

        val token = tokenService.generate(TokenClaim("userId", userEntity.id.toString()))
        AuthResponse(token = token)
    }

    suspend fun login(request: LoginRequest?) = runCatching {
        requireNotNull(request)

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
