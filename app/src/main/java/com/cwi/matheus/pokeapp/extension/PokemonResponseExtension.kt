package com.cwi.matheus.pokeapp.extension

import com.cwi.matheus.pokeapp.data.network.entity.PokemonResponse
import com.cwi.matheus.pokeapp.data.network.mapper.PokemonMapper
import com.cwi.matheus.pokeapp.domain.entity.Pokemon

fun PokemonResponse.map() : Pokemon = PokemonMapper().toDomain(this)
