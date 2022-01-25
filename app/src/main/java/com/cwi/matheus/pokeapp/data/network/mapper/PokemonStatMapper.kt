package com.cwi.matheus.pokeapp.data.network.mapper

import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.domain.entity.Stat

class PokemonStatMapper {

    private fun fromStringToDomain(from: String): PokemonStat {
        val split = from.split(":")
        val name = split[0]
        val attrs = split[1]
        val attrsSplit = attrs.split(",").map { it.split("=")[1].toInt() }
        val baseStat = attrsSplit[0]
        val effort = attrsSplit[1]
        return PokemonStat(baseStat, effort, Stat(name))
    }

    fun fromStringToDomainList(from: String): List<PokemonStat> =
        from.split("&").map { fromStringToDomain(it) }

}