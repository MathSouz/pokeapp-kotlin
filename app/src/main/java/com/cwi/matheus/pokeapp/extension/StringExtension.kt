package com.cwi.matheus.pokeapp.extension

import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.domain.entity.Stat

fun String.capitalize() : String {
    return this.split(" ").joinToString { it.substring(0, 1).uppercase() + it.substring(1) }
}

fun String.toPokemonStats() : PokemonStat {
    val split = this.split(":")
    val name = split[0]
    val attrs = split[1]
    val attrsSplit = attrs.split(",").map { it.split("=")[1].toInt() }
    val baseStat = attrsSplit[0]
    val effort = attrsSplit[1]
    return PokemonStat(baseStat, effort, Stat(name))
}

fun String.toPokemonStatsList() : List<PokemonStat> =
    this.split("&").map { it.toPokemonStats() }
