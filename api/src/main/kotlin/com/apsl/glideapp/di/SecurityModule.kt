package com.apsl.glideapp.di

import com.apsl.glideapp.features.auth.security.JwtTokenService
import com.apsl.glideapp.features.auth.security.TokenConfig
import com.apsl.glideapp.features.auth.security.TokenService
import com.apsl.glideapp.features.auth.security.hashing.HashingService
import com.apsl.glideapp.features.auth.security.hashing.SHA256HashingService
import com.apsl.glideapp.utils.Constants
import org.koin.dsl.module

val securityModule = module {
    single<HashingService> { SHA256HashingService }
    single<TokenService> { JwtTokenService(get()) }
    single {
        TokenConfig(
            issuer = getProperty("issuer"),
            audience = getProperty("audience"),
            secret = getProperty("secret"),
            realm = getProperty("realm"),
            expiresIn = Constants.TOKEN_EXPIRATION_TIME
        )
    }
}
