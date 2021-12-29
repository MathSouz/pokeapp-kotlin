package com.cwi.matheus.pokeapp.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val repository: PokeApiRepository
) : BaseViewModel() {

    private val _data = MutableLiveData<List<SimplePokemon>>()
    val data: LiveData<List<SimplePokemon>> = _data

    private var currentPage : Int = 0

    fun fetchSimplePokemon(page : Int = currentPage) {
        launch {
            val pokemonList = repository.getPokemonList(page)
            _data.postValue(pokemonList)
            currentPage++
        }
    }
}