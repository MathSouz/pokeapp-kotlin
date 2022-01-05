package com.cwi.matheus.pokeapp.data.network.mapper

import com.cwi.matheus.pokeapp.data.network.entity.SimplePokemonResponse
import com.cwi.matheus.pokeapp.domain.entity.SimplePokemon

class SimplePokemonMapper : DomainMapper<SimplePokemonResponse, SimplePokemon> {
    override fun toDomain(from: SimplePokemonResponse): SimplePokemon {
        val splitBySlash = from.url.split("/")
        val pokemonIdInURL = splitBySlash[splitBySlash.size - 2]
        return SimplePokemon(
            id = pokemonIdInURL.toInt(),
            name = from.name,
            imageUrl = parsePokemonIDToSimpleSpriteURL(pokemonIdInURL))
    }

    override fun toDomain(from: List<SimplePokemonResponse>): List<SimplePokemon> =
        from.map { toDomain(it) }

    private fun parsePokemonIDToSimpleSpriteURL(id : String) : String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"

}