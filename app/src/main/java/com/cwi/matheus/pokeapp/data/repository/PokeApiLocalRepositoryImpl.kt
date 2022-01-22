package com.cwi.matheus.pokeapp.data.repository

import com.cwi.matheus.pokeapp.data.database.AppDatabase
import com.cwi.matheus.pokeapp.data.database.mapper.PokemonEntityMapper
import com.cwi.matheus.pokeapp.data.network.mapper.PokemonMapper
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository

class PokeApiLocalRepositoryImpl(
    database: AppDatabase
) : PokeApiLocalRepository {

    private val dataAccessObject = database.getPokemonDao()
    private val pokemonEntityMapper = PokemonEntityMapper()

    override fun add(pokemon: Pokemon) {
        val pokemonEntity = PokemonMapper().toEntity(pokemon)
        dataAccessObject.add(pokemonEntity)
    }

    override fun remove(id: Int) =
        dataAccessObject.remove(id)

    override fun getAll(): List<Pokemon> =
        dataAccessObject.getAll().map { pokemonEntityMapper.toDomain(it) }

    override fun existsById(id: Int): Boolean =
        dataAccessObject.existsById(id)

    override fun countByPokemonId(pokemonId: Int): Int =
        dataAccessObject.countByPokemonId(pokemonId)

    override fun findById(id: Int): Pokemon =
        pokemonEntityMapper.toDomain(dataAccessObject.findById(id))

}