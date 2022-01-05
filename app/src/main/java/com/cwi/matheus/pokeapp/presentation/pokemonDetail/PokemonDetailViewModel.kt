package com.cwi.matheus.pokeapp.presentation.pokemonDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val repository: PokeApiRepository,
    private val localRepository: PokeApiLocalRepository
    ) : BaseViewModel() {

    private val _currentPokemon = MutableLiveData<Pokemon>()
    val currentPokemon : LiveData<Pokemon> = _currentPokemon

    fun fetchPokemonDetail(id : Int) {
        launch {
            val pokemonData = repository.getPokemonByID(id)
            pokemonData.captured = isPokemonCaptured(pokemonData)
            _currentPokemon.postValue(pokemonData)
        }
    }

    private fun isPokemonCaptured(pokemon : Pokemon) : Boolean {
        return localRepository.getAll().find { it.id == pokemon.id } != null
    }

    fun setCaptured(pokemon: Pokemon) {

        if(!pokemon.captured) {
            localRepository.remove(pokemon.id)
        } else {
            viewModelScope.launch {
                val fetchedPokemon = repository.getPokemonByID(pokemon.id)
                localRepository.add(fetchedPokemon)
            }
        }
    }
}