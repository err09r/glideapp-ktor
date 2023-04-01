package com.apsl.glideapp.di

import com.apsl.glideapp.MainService
import org.koin.dsl.module

val appModule = module {
    single { MainService(get(), get()) }
}
