package com.cwi.matheus.pokeapp.data.database.mapper

import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.data.network.mapper.DomainMapper
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.extension.parseToString
import com.cwi.matheus.pokeapp.extension.toPokemonStatsList

class PokemonEntityMapper : DomainMapper<PokemonEntity, Pokemon> {

    override fun toDomain(from: PokemonEntity): Pokemon =
        Pokemon(
            id = from.id,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = from.imageUrl,
            stats = from.stats.toPokemonStatsList(),
        )

    override fun toDomain(from: List<PokemonEntity>): List<Pokemon> =
        from.map { toDomain(it) }

    fun toEntity(from: Pokemon) : PokemonEntity =
        PokemonEntity(
            id = from.id,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = from.imageUrl,
            stats = from.stats.parseToString()
        )
}