package com.cwi.matheus.pokeapp.data.network.entity

import com.squareup.moshi.Json

data class PokemonListResponse(
    @Json(name = "count") val count: Int,
    @Json(name = "results") val pokemons: List<SimplePokemonResponse>
)