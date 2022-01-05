package com.cwi.matheus.pokeapp.domain.entity

data class Pokemon(
    val id : Int,
    val name : String,
    val height : Int,
    val weight : Int,
    val imageUrl : String,
    val stats : List<PokemonStat>,
    var captured : Boolean = false
)
