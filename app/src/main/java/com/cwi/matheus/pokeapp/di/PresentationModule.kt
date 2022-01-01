package com.cwi.matheus.pokeapp.di

import com.cwi.matheus.pokeapp.presentation.pokemon.PokemonViewModel
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.PokemonDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PokemonViewModel(get(), get()) }
    viewModel { PokemonDetailViewModel(get(), get()) }
}