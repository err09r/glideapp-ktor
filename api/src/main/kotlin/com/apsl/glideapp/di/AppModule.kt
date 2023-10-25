package com.apsl.glideapp.di

import com.apsl.glideapp.features.config.GlideConfig
import com.apsl.glideapp.features.config.HoconGlideConfig
import org.koin.dsl.module

val appModule = module {
    single<GlideConfig> { HoconGlideConfig(filename = "glideapp.conf") }
}
