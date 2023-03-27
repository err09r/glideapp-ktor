package com.apsl.glide.di

import com.apsl.glide.features.auth.security.JwtTokenService
import com.apsl.glide.features.auth.security.TokenConfig
import com.apsl.glide.features.auth.security.TokenService
import com.apsl.glide.features.auth.security.hashing.HashingService
import com.apsl.glide.features.auth.security.hashing.SHA256HashingService
import io.ktor.server.config.ApplicationConfig
import org.koin.dsl.module

fun securityModule(config: ApplicationConfig) = module {
    single<TokenService> { JwtTokenService(get()) }
    single<HashingService> { SHA256HashingService() }
    single {
        TokenConfig(
            issuer = config.property("jwt.issuer").getString(),
            audience = config.property("jwt.audience").getString(),
            secret = config.property("jwt.secret").getString(),
            realm = config.property("jwt.realm").getString(),
            expiresIn = 6L * 30L * 24L * 60L * 60L * 1000L // 6 months
        )
    }
}
