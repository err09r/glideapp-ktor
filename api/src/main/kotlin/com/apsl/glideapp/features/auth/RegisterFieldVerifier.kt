package com.apsl.glideapp.features.auth

object RegisterFieldVerifier {

    private val emptyException = Exception()

    fun verifyUsername(username: String): Result<Unit> {
        val isValid = username.matches(Regex("^[a-z0-9_.-]{4,20}\$"))
        return if (isValid) Result.success(Unit) else Result.failure(emptyException)
    }

    fun verifyPassword(password: String): Result<Unit> {
        val isValid = password.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}\$"))
        return if (isValid) Result.success(Unit) else Result.failure(emptyException)
    }
}
