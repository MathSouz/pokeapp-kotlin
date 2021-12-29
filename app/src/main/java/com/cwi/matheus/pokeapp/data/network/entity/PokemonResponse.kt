package com.cwi.matheus.pokeapp.data.network.entity

import com.squareup.moshi.Json

data class PokemonResponse(
    @Json(name="id") val id : Int,
    @Json(name="name") val name : String,
    @Json(name="height") val height : Int,
    @Json(name="weight") val weight : Int,
    @Json(name="stats") val stats : List<PokemonStatResponse>
)