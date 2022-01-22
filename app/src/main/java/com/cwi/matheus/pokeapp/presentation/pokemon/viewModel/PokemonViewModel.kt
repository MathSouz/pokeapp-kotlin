package com.cwi.matheus.pokeapp.presentation.pokemon.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel

class PokemonViewModel(
    private val repository: PokeApiRepository,
    private val localRepository: PokeApiLocalRepository
) : BaseViewModel() {

    private val _data = MutableLiveData<List<SimplePokemon>>()
    val data: LiveData<List<SimplePokemon>> = _data

    private var page = 0

    fun fetchSimplePokemons(nextPage: Boolean) {
        launch {
            val pokemonList = repository.getPokemonList(page).map {
                it.count = localRepository.countByPokemonId(it.pokemonId)
                it
            }

            _data.value?.let { simplePokemonList ->
                _data.postValue(simplePokemonList + pokemonList)
            } ?: run {
                _data.postValue(pokemonList)
            }
            if (nextPage) page++
        }
    }
}


