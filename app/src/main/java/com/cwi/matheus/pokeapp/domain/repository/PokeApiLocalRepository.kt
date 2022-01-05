package com.cwi.matheus.pokeapp.domain.repository

import com.cwi.matheus.pokeapp.domain.entity.Pokemon

interface PokeApiLocalRepository {
    fun add(pokemon: Pokemon)
    fun remove(id : Int)
    fun getAll(): List<Pokemon>
    fun getAll(page : Int): List<Pokemon>
}