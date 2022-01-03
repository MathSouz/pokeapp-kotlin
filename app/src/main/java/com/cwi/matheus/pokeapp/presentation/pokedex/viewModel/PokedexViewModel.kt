package com.cwi.matheus.pokeapp.presentation.pokedex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cwi.matheus.pokeapp.base.POKEDEX_PAGE
import com.cwi.matheus.pokeapp.data.database.mapper.PokemonEntityMapper
import com.cwi.matheus.pokeapp.data.repository.PokeApiLocalRepositoryImpl
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel
import kotlin.math.min

class PokedexViewModel(
    private val localRepository: PokeApiLocalRepository) : BaseViewModel() {
    
    private val _pokedex = MutableLiveData<List<Pokemon>>()
    val pokedex : LiveData<List<Pokemon>> = _pokedex
    
    fun fetchPokedex() {
        launch {
            val pokedexList = localRepository.getAll()
            _pokedex.postValue(pokedexList)
        }
    }
}