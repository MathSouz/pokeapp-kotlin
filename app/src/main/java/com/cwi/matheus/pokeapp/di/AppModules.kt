package com.cwi.matheus.pokeapp.di

import org.koin.core.module.Module

val appModules: List<Module> = listOf(dataModule, presentationModule)