package com.cwi.matheus.pokeapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert
    fun add(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM PokemonEntity WHERE id=:id")
    fun remove(id: Int)

    @Update
    fun update(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE id=:id")
    fun findById(id: Int): PokemonEntity

    @Query("SELECT EXISTS (SELECT * FROM PokemonEntity WHERE id=:id)")
    fun existsById(id: Int): Boolean

    @Query("SELECT COUNT(pokemonId) FROM PokemonEntity WHERE pokemonId=:pokemonId")
    fun countByPokemonId(pokemonId: Int): Int
}
