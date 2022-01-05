package com.cwi.matheus.pokeapp.data.network

import com.cwi.matheus.pokeapp.base.SIMPLE_POKEMONS_PER_PAGE
import com.cwi.matheus.pokeapp.data.network.entity.PokemonListResponse
import com.cwi.matheus.pokeapp.data.network.entity.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon?limit=${SIMPLE_POKEMONS_PER_PAGE}")
    suspend fun getPokemonList(@Query("offset") offset : Int) : PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name : String) : PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonByID(@Path("id") id : Int) : PokemonResponse
}
