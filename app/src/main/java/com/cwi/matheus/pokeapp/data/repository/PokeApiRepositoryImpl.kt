package com.cwi.matheus.pokeapp.data.repository

import com.cwi.matheus.pokeapp.base.SIMPLE_POKEMONS_PER_PAGE
import com.cwi.matheus.pokeapp.data.network.PokeApi
import com.cwi.matheus.pokeapp.data.network.mapper.SimplePokemonMapper
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon
import com.cwi.matheus.pokeapp.domain.repository.PokeApiRepository
import com.cwi.matheus.pokeapp.extension.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokeApiRepositoryImpl(private val api: PokeApi) : PokeApiRepository {

    override suspend fun getPokemonList(page: Int): List<SimplePokemon> {
        return withContext(Dispatchers.IO) {
            api.getPokemonList(page * SIMPLE_POKEMONS_PER_PAGE).pokemons.map {
                SimplePokemonMapper().toDomain(it)
            }
        }
    }

    override suspend fun getPokemonByName(name: String): Pokemon {
        return withContext(Dispatchers.IO) {
            api.getPokemonByName(name).map()
        }
    }

    override suspend fun getPokemonByID(id: Int): Pokemon {
        return withContext(Dispatchers.IO) {
            api.getPokemonByID(id).map()
        }
    }

}