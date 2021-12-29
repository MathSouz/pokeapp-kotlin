package com.cwi.matheus.pokeapp.presentation.pokemonDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cwi.matheus.pokeapp.data.network.PokeApi
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel

class PokemonDetailViewModel(
    private val repository: PokeApiRepository
    ) : BaseViewModel() {

    private val _data = MutableLiveData<Pokemon>()
    val data : LiveData<Pokemon> = _data

    fun fetchPokemonDetail(id : Int) {
        launch {
            val pokemonData = repository.getPokemonByID(id)
            _data.postValue(pokemonData)
        }
    }
}