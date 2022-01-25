package com.cwi.matheus.pokeapp.extension

import com.cwi.matheus.pokeapp.base.Colors
import com.cwi.matheus.pokeapp.domain.entity.PokemonType

fun PokemonType.getMappedColor(): Int? =
    Colors.getColor(this.name)
