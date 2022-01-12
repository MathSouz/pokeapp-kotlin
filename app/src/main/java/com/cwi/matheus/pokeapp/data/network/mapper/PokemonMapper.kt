package com.cwi.matheus.pokeapp.data.network.mapper

import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.data.network.entity.PokemonResponse
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.domain.entity.Stat

class PokemonMapper : DomainMapper<PokemonResponse, Pokemon>{

    fun toEntity(from : Pokemon): PokemonEntity =
        PokemonEntity(
            id = from.id,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = from.imageUrl,
            stats = from.stats
        )

    override fun toDomain(from: PokemonResponse): Pokemon {
        val statList = from.stats.map { PokemonStat(it.baseStat, it.baseStat, Stat(it.stat.name)) }

        return Pokemon(
            id = from.id,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = getArtworkUrlByPokemonId(from.id),
            stats = statList)
    }


    override fun toDomain(from: List<PokemonResponse>): List<Pokemon> = from.map { toDomain(it) }

    /**
     * Estou buscando a imagem do pokemon desta forma por que a API não entrega a URL pelo endpoint,
     * no caso, eu teria que fazer duas requisições diferentes apenas para buscar a imagem.
     */
    private fun getArtworkUrlByPokemonId(id : Int) : String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}