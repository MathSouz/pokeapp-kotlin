package com.cwi.matheus.pokeapp.di

import com.cwi.matheus.pokeapp.presentation.pokedex.viewModel.PokedexViewModel
import com.cwi.matheus.pokeapp.presentation.pokemon.viewModel.PokemonViewModel
import com.cwi.matheus.pokeapp.presentation.pokemonDetail.viewModel.PokemonDetailViewModel
import com.cwi.matheus.pokeapp.presentation.preferences.viewModel.PreferencesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PokemonViewModel(get(), get()) }
    viewModel { PokemonDetailViewModel(get(), get()) }
    viewModel { PokedexViewModel(get()) }
    viewModel { PreferencesViewModel(get()) }
}