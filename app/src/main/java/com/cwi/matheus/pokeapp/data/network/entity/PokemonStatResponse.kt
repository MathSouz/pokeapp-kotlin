package com.cwi.matheus.pokeapp.data.network.entity

import com.squareup.moshi.Json

data class PokemonStatResponse(
    @Json(name = "base_stat") val baseStat: Int,
    @Json(name = "effort") val effort: Int,
    @Json(name = "stat") val stat: StatResponse
)
