package com.cwi.matheus.pokeapp.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity

interface PokeApiLocalRepository {
    fun add(pokemonEntity: PokemonEntity)
    fun remove(id : Int)
    fun update(pokemonEntity: PokemonEntity)
    fun getAll(): List<PokemonEntity>
}