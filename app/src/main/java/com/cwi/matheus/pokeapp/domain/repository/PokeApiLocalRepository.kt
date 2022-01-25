package com.cwi.matheus.pokeapp.domain.repository

import com.cwi.matheus.pokeapp.domain.entity.Pokemon

interface PokeApiLocalRepository {
    fun add(pokemon: Pokemon)
    fun remove(id: Int)
    fun getAll(): List<Pokemon>
    fun existsById(id: Int): Boolean
    fun countByPokemonId(pokemonId: Int): Int
    fun findById(id: Int): Pokemon
    fun update(pokemon: Pokemon)
}