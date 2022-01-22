package com.cwi.matheus.pokeapp.domain.entity

data class SimplePokemon(
    val pokemonId : Int,
    val name : String,
    val imageUrl : String,
    var count : Int = 0
)