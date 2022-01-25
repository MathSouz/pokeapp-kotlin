package com.cwi.matheus.pokeapp.domain.repository

import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon

interface PokeApiRepository {
    suspend fun getPokemonList(page: Int): List<SimplePokemon>
    suspend fun getPokemonByName(name: String): Pokemon
    suspend fun getPokemonByID(id: Int): Pokemon
}