package com.cwi.matheus.pokeapp.extension

import com.cwi.matheus.pokeapp.domain.entity.PokemonStat

fun List<PokemonStat>.parseToString() : String =
    this.joinToString(separator = "&", transform = { it.parseToString() })

fun PokemonStat.parseToString() : String =
    "${this.stat.name}:baseStat=${this.baseStat},effort=${this.effort}"