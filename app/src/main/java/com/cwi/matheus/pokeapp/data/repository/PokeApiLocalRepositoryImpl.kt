package com.cwi.matheus.pokeapp.data.repository

import com.cwi.matheus.pokeapp.data.database.AppDatabase
import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.domain.repository.PokeApiLocalRepository

class PokeApiLocalRepositoryImpl(
    database: AppDatabase
) : PokeApiLocalRepository {

    private val dataAccessObject = database.getPokemonDao()

    override fun add(pokemonEntity: PokemonEntity) =
        dataAccessObject.add(pokemonEntity)

    override fun remove(id : Int) =
        dataAccessObject.remove(id)

    override fun update(pokemonEntity: PokemonEntity) =
        dataAccessObject.update(pokemonEntity)

    override fun getAll(): List<PokemonEntity> =
        dataAccessObject.getAll()
}