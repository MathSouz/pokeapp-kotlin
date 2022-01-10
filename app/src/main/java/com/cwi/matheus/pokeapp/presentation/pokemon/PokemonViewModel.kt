package com.cwi.matheus.pokeapp.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokeApiRepository,
    private val localRepository: PokeApiLocalRepository
) : BaseViewModel() {

    private val _data = MutableLiveData<List<SimplePokemon>>()
    val data: LiveData<List<SimplePokemon>> = _data

    private var page = 0

    fun fetchSimplePokemons(nextPage: Boolean) {
        launch {
            val pokemonList = repository.getPokemonList(page).map { updatePokemonCaptureState(it) }

            _data.value?.let { simplePokemonList ->
                _data.postValue(simplePokemonList + pokemonList)
            } ?: run {
                _data.postValue(pokemonList)
            }
            if (nextPage) page++
        }
    }

    private fun updatePokemonCaptureState(simplePokemon: SimplePokemon): SimplePokemon {
        simplePokemon.captured =
            (localRepository.getAll().find { it.id == simplePokemon.id } != null)
        return simplePokemon
    }

    fun updateLocalRepositoryFromPokemonCaptureState(simplePokemon: SimplePokemon) {

        if (!simplePokemon.captured) {
            localRepository.remove(simplePokemon.id)
        } else {
            viewModelScope.launch {
                val pokemon = repository.getPokemonByID(simplePokemon.id)
                localRepository.add(pokemon)
            }
        }
    }

}


