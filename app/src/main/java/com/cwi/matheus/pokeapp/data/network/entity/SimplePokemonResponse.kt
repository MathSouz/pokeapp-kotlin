package com.cwi.matheus.pokeapp.data.network.entity

import com.squareup.moshi.Json

data class SimplePokemonResponse(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)