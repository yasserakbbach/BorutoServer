package com.yasserakbbach.borutoserver.di

import com.yasserakbbach.borutoserver.repository.HeroRepository
import com.yasserakbbach.borutoserver.repository.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}