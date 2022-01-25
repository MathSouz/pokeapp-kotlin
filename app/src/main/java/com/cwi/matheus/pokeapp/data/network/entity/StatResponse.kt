package com.cwi.matheus.pokeapp.data.network.entity

import com.squareup.moshi.Json

data class StatResponse(
    @Json(name = "name") val name: String
)
