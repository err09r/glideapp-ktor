package com.apsl.glide.di

import com.apsl.glide.MainService
import org.koin.dsl.module

val appModule = module {
    single { MainService(get(), get()) }
}
