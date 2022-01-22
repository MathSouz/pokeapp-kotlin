package com.cwi.matheus.pokeapp.di

import com.cwi.matheus.pokeapp.data.database.AppDatabase
import com.cwi.matheus.pokeapp.data.repository.PokeApiLocalRepositoryImpl
import com.cwi.matheus.pokeapp.data.repository.PokeApiRepositoryImpl
import com.cwi.matheus.pokeapp.data.sharedPreferences.SharedPreferencesRepositoryImpl
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.domain.repository.SharedPreferencesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitConfig.service }
    single { AppDatabase.create(androidApplication()) }

    factory<PokeApiRepository> { PokeApiRepositoryImpl(get()) }
    factory<PokeApiLocalRepository> { PokeApiLocalRepositoryImpl(get()) }
    factory<SharedPreferencesRepository> { SharedPreferencesRepositoryImpl(androidApplication()) }
}