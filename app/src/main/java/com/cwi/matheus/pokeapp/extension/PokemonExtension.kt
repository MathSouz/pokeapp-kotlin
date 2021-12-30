package com.cwi.matheus.pokeapp.extension

import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.domain.entity.Pokemon

fun Pokemon.toEntity(): PokemonEntity =
    PokemonEntity(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
        imageUrl = this.imageUrl,
        stats = this.stats.parseToString()
    )