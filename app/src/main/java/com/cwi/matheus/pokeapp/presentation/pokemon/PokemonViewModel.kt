package com.cwi.matheus.pokeapp.presentation.pokemon

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.extension.capitalize
import com.cwi.matheus.pokeapp.extension.parseToString
import com.cwi.matheus.pokeapp.extension.toEntity
import com.cwi.matheus.pokeapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class PokemonViewModel(
    private val repository: PokeApiRepository,
    private val localRepository: PokeApiLocalRepository
) : BaseViewModel() {

    private val _data = MutableLiveData<List<SimplePokemon>>()
    val data: LiveData<List<SimplePokemon>> = _data

    fun fetchSimplePokemons(page : Int = 0) {
        launch {
            val pokemonList = repository.getPokemonList(page).map { updatePokemonCaptureState(it) }
            _data.postValue(pokemonList)
        }
    }
    
    private fun updatePokemonCaptureState(simplePokemon: SimplePokemon) : SimplePokemon {
        simplePokemon.captured = (localRepository.getAll().find { it.id == simplePokemon.id } != null)
        return simplePokemon
    }

    fun setCaptured(simplePokemon: SimplePokemon) {

        if(!simplePokemon.captured) {
            localRepository.remove(simplePokemon.id)
        } else {
            viewModelScope.launch {
                val pokemon = repository.getPokemonByID(simplePokemon.id)
                localRepository.add(pokemon)
            }
        }
    }

}


