package com.cwi.matheus.pokeapp.presentation.pokemonDetail.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel

class PokemonDetailViewModel(
    private val repository: PokeApiRepository,
    private val localRepository: PokeApiLocalRepository
) : BaseViewModel() {

    private val _currentPokemon = MutableLiveData<Pokemon>()
    val currentPokemon: LiveData<Pokemon> = _currentPokemon

    fun fetchLocalPokemonDetail(id: Int) {
        launch {
            val pokemonData = localRepository.findById(id)
            _currentPokemon.postValue(pokemonData)
        }
    }

    fun fetchPokemonDetail(id: Int) {
        launch {
            val pokemonData = repository.getPokemonByID(id)
            _currentPokemon.postValue(pokemonData)
        }
    }

    fun capturePokemon(pokemon: Pokemon) {
        launch {
            localRepository.add(pokemon)
        }
    }
}