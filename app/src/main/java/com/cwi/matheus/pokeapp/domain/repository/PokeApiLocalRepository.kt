package com.cwi.matheus.pokeapp.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.domain.entity.Pokemon

interface PokeApiLocalRepository {
    fun add(pokemonEntity: Pokemon)
    fun remove(id : Int)
    fun getAll(): List<Pokemon>
    fun getAll(page : Int): List<Pokemon>
}