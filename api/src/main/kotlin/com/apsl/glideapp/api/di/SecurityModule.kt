package com.apsl.glideapp.api.di

import com.apsl.glideapp.api.features.auth.security.JwtTokenService
import com.apsl.glideapp.api.features.auth.security.TokenConfig
import com.apsl.glideapp.api.features.auth.security.TokenService
import com.apsl.glideapp.api.features.auth.security.hashing.HashingService
import com.apsl.glideapp.api.features.auth.security.hashing.SHA256HashingService
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
            expiresIn = 6L * 30L * 24L * 60L * 60L * 1000L // 6 months
        )
    }
}
