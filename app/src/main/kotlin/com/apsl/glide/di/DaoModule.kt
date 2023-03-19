package com.apsl.glide.di

import com.apsl.glide.database.dao.UserDao
import com.apsl.glide.database.dao.UserDaoImpl
import org.koin.dsl.module

val daoModule = module {
    single<UserDao> { UserDaoImpl() }
}
