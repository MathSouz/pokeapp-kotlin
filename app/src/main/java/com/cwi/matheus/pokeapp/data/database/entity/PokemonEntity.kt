package com.cwi.matheus.pokeapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val pokemonId: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val stats: List<PokemonStat>,
    val createdAt: Long = System.currentTimeMillis()
)
