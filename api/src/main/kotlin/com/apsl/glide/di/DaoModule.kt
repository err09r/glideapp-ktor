package com.apsl.glide.di

import com.apsl.glide.database.dao.UserDao
import com.apsl.glide.database.dao.UserDaoImpl
import com.apsl.glide.database.dao.ZoneDao
import com.apsl.glide.database.dao.ZoneDaoImpl
import com.apsl.glide.models.Coordinates
import com.apsl.glide.models.ZoneType
import kotlinx.coroutines.runBlocking
import org.koin.dsl.module

val daoModule = module(createdAtStart = true) {
    single<UserDao> { UserDaoImpl() }
    single<ZoneDao> {
        ZoneDaoImpl().apply {
            runBlocking {
                insertZone(
                    title = "Słupsk",
                    type = ZoneType.Riding,
                    coordinates = listOf(
                        Coordinates(54.450024, 17.009596),
                        Coordinates(54.459005, 16.950201),
                        Coordinates(54.493815, 16.977839),
                        Coordinates(54.484941, 17.000327),
                        Coordinates(54.476663, 17.023501),
                        Coordinates(54.487833, 17.044100),
                        Coordinates(54.470977, 17.061438),
                        Coordinates(54.450024, 17.009596)
                    )
                )
            }
        }
    }
}
