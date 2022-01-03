package com.cwi.matheus.pokeapp.data.repository

import com.cwi.matheus.pokeapp.base.POKEDEX_PAGE
import com.cwi.matheus.pokeapp.data.database.AppDatabase
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.data.database.mapper.PokemonEntityMapper
import com.cwi.matheus.pokeapp.data.network.mapper.PokemonMapper
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository
import com.cwi.matheus.pokeapp.extension.toEntity

class PokeApiLocalRepositoryImpl(
    database: AppDatabase
) : PokeApiLocalRepository {

    private val dataAccessObject = database.getPokemonDao()

    override fun add(pokemon: Pokemon) =
        dataAccessObject.add(pokemon.toEntity())

    override fun remove(id : Int) =
        dataAccessObject.remove(id)

    override fun getAll(): List<Pokemon> =
        dataAccessObject.getAll().map { PokemonEntityMapper().toDomain(it) }

    override fun getAll(page : Int): List<Pokemon> =
        dataAccessObject.getAll(POKEDEX_PAGE, POKEDEX_PAGE * page)
            .map { PokemonEntityMapper().toDomain(it) }
}