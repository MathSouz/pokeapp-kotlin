package com.cwi.matheus.pokeapp.domain.entity

import com.cwi.matheus.pokeapp.data.network.entity.StatResponse
import com.squareup.moshi.Json
import java.time.LocalDateTime

data class Pokemon(
    val id : Int,
    val name : String,
    val height : Int,
    val weight : Int,
    val imageUrl : String,
    val stats : List<PokemonStat>,
    val captured : Boolean = false,
    val captureDate : LocalDateTime? = null
)
