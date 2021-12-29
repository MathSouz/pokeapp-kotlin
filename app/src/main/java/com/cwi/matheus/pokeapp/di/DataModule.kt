package com.cwi.matheus.pokeapp.di

import com.cwi.matheus.pokeapp.data.repository.PokeApiRepositoryImpl
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitConfig.service }

    factory<PokeApiRepository> { PokeApiRepositoryImpl(get()) }
}