package com.cwi.matheus.pokeapp.domain.entity

data class SimplePokemon(
    val id : Int,
    val name : String,
    val imageUrl : String,
    var captured : Boolean = false
)