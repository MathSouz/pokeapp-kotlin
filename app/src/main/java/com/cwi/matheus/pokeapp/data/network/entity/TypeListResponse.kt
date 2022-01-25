package com.cwi.matheus.pokeapp.data.network.entity

import com.squareup.moshi.Json

data class TypeListResponse(
    @Json(name = "slot") val slot: Int,
    @Json(name = "type") val type: TypeResponse
)
