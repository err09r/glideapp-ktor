package com.apsl.glideapp.api.di

import com.apsl.glideapp.api.features.config.GlideConfig
import com.apsl.glideapp.api.features.config.HoconGlideConfig
import org.koin.dsl.module

val appModule = module {
    single<GlideConfig> { HoconGlideConfig(filename = "glideapp.conf") }
}
