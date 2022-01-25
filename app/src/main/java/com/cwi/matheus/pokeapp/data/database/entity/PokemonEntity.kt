package com.cwi.matheus.pokeapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val pokemonId: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val stats: PokemonStatList,
    val types: PokemonTypeList,
    val createdAt: Long = Calendar.getInstance().timeInMillis
)
