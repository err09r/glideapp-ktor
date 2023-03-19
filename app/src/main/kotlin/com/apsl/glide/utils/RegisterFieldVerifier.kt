package com.apsl.glide.utils

object RegisterFieldVerifier {

    fun verifyUsername(username: String): Boolean {
        return username.matches(Regex("^[a-z0-9_-]{4,20}\$"))
    }

    fun verifyPassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}\$"))
    }
}
