package com.cwi.matheus.pokeapp.data.database.entity

import androidx.room.*

@Entity
data class PokemonEntity(
    @PrimaryKey val id : Int,
    val name : String,
    val height : Int,
    val weight : Int,
    val imageUrl : String,
    val stats : String
)
