package com.cwi.matheus.pokeapp.data.database.dao

import androidx.room.*
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert
    fun add(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM PokemonEntity WHERE id=:id")
    fun remove(id : Int)

    @Update
    fun update(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonEntity>
}
