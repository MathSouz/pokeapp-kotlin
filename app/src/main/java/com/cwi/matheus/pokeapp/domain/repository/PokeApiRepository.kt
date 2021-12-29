package com.cwi.matheus.pokeapp.domain.repository

import com.cwi.matheus.pokeapp.data.network.entity.PokemonResponse
import com.cwi.matheus.pokeapp.data.network.entity.SimplePokemonResponse
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiRepository {
    suspend fun getPokemonList(page : Int) : List<SimplePokemon>
    suspend fun getPokemonByName(name : String) : Pokemon
    suspend fun getPokemonByID(id : Int) : Pokemon
}